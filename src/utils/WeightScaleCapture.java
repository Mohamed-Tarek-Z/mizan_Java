package utils;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.regex.Pattern;
import javax.swing.text.BadLocationException;

/**
 * Captures a weight reading from a USB scale that emulates a keyboard (HID
 * "keyboard wedge"), no matter which Swing component currently has focus, and
 * writes the parsed value into a designated target field.
 *
 * Background: a keyboard-wedge device just "types" characters into whatever has
 * focus. Since the character Windows actually delivers for the decimal point
 * depends on the active keyboard layout (hence '.' in English vs 'ز' in Arabic
 * here), and you can't predict which field has focus when the scale sends a
 * reading, this class intercepts every keystroke at the KeyboardFocusManager
 * level -- before any component sees it -- and:
 *
 * 1. Detects the device by typing speed: it sends characters far faster than a
 * human can type. 2. Only treats it as the device once it sees a SECOND fast
 * character in a row (not the very first one), so an ordinary human keystroke
 * is never mistaken for the device. Because of that one-character delay, the
 * very first character of a real burst will have already been typed into
 * whatever field had focus -- this class retracts that one character once the
 * burst is confirmed, so no stray character is left behind. 3. Swallows the
 * rest of the burst (so it never lands in the wrong field) until it sees the
 * terminating Enter/CR that the device sends after a full reading, then
 * validates the buffer looks like a number and delivers it to the target field,
 * replacing the device's decimal marker with the real Arabic decimal separator
 * (٫, U+066B).
 *
 * IMPORTANT: this assumes the scale is a HID keyboard-emulating device (check
 * Device Manager -- it should show up as a keyboard/HID device). If it instead
 * shows up under "Ports (COM & LPT)" as a serial device, don't use this class
 * at all: read the COM port directly (e.g. with jSerialComm). That's simpler
 * and avoids focus/keystroke handling entirely, and the locale-dependent
 * decimal character problem disappears because you're reading the raw bytes the
 * device sent rather than OS-translated keystrokes.
 *
 * TUNING: MAX_GAP_MS must sit comfortably above your device's own
 * inter-character gap and comfortably below a human's fastest realistic typing
 * gap. Log the real gaps you see (print the `gap` value below while triggering
 * a real reading) and adjust if readings are missed or normal typing gets
 * swallowed.
 */
public class WeightScaleCapture implements KeyEventDispatcher {

    private final long maxGapMs;
    private final char deviceDecimalMarker;
    private final JTextField targetField;
    private static final Pattern WEIGHT_PATTERN = Pattern.compile("\\d+([.\u0632]\\d+{2})?");

    private final StringBuilder buffer = new StringBuilder();
    private long lastEventTime = 0L;
    private boolean confirmed = false;
    private JTextComponent candidateOwner;

    public WeightScaleCapture(JTextField targetField) {
        this(targetField, 30L, 'ز');
    }

    public WeightScaleCapture(JTextField targetField, long maxGapMs, char deviceDecimalMarker) {
        this.targetField = targetField;
        this.maxGapMs = maxGapMs;
        this.deviceDecimalMarker = deviceDecimalMarker;
    }

    public void install() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this);
    }


    public void uninstall() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(this);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        if (e.getID() != KeyEvent.KEY_TYPED) {
            return false;
        }

        char c = e.getKeyChar();
        long now = System.currentTimeMillis();
        long gap = now - lastEventTime;
        lastEventTime = now;

        boolean isTerminator = c == '\n';
        boolean isBufferable = Character.isDigit(c) || c == '.' || c == deviceDecimalMarker;

        if (isTerminator) {
            boolean swallow = confirmed && WEIGHT_PATTERN.matcher(buffer.toString()).matches();
            if (swallow) {
                deliverToTargetField(buffer.toString());
            }
            reset();
            return swallow;
        }

        if (!isBufferable) {
            reset();
            return false;
        }

        boolean fastFollowUp = buffer.length() > 0 && gap <= maxGapMs;

        if (!confirmed && fastFollowUp) {
            confirmed = true;
            retractLastCharacterFrom(candidateOwner);
        }

        if (confirmed) {
            buffer.append(c);
            return true;
        }

        if (buffer.length() > 0 && !fastFollowUp) {
            reset();
        }

        buffer.append(c);
        candidateOwner = currentTextFocusOwner();
        return false;
    }

    private JTextComponent currentTextFocusOwner() {
        Component owner = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
        return (owner instanceof JTextComponent) ? (JTextComponent) owner : null;
    }

    private void retractLastCharacterFrom(JTextComponent owner) {
        if (owner == null) {
            return;
        }
        try {
            int pos = owner.getCaretPosition();
            if (pos > 0) {
                owner.getDocument().remove(pos - 1, 1);
            }
        } catch (BadLocationException ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "ex", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void reset() {
        buffer.setLength(0);
        confirmed = false;
        candidateOwner = null;
    }

    private void deliverToTargetField(String raw) {
        String display = ArabicDigits.toArabicDigits(raw.replace(deviceDecimalMarker, ArabicDigits.ARABIC_DECIMAL_SEPARATOR));
        SwingUtilities.invokeLater(() -> targetField.setText(display));
    }
}

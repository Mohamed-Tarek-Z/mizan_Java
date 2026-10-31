package utils;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.regex.Pattern;

/**
 * Captures a weight reading from a USB scale that behaves like a keyboard, whichever
 * component has focus, and writes it into {@code targetField}.
 *
 * How a reading is recognised: the scale types far faster than a human. The first character
 * is let through (we can't know yet), the second FAST character confirms a burst, the first
 * character is then retracted from the field it landed in, and the rest are swallowed until the
 * scale's Enter.
 *
 * A confirmed burst must keep streaming. If it goes quiet without its Enter (a human who
 * happened to hit two keys within the gap), the swallowed keystrokes are handed back to the
 * field they were meant for, so nothing is lost and no stale state is left behind.
 */
public class WeightScaleCapture implements KeyEventDispatcher {

    // digits, optionally followed by '.' or the device's decimal marker and EXACTLY two digits
    private static final Pattern WEIGHT_PATTERN = Pattern.compile("\\d+([.\u0632]\\d{2})?");

    private final long maxGapMs;
    private final char deviceDecimalMarker;
    private final JTextField targetField;
    private final Timer idleTimer;

    private final StringBuilder buffer = new StringBuilder();
    private long lastEventTime = 0L;
    private boolean confirmed = false;
    private JTextComponent candidateOwner;
    private int candidateLengthBefore = -1;
    private boolean swallowRestOfEnter = false;

    public WeightScaleCapture(JTextField targetField) {
        this(targetField, 30L, 'ز');
    }

    public WeightScaleCapture(JTextField targetField, long maxGapMs, char deviceDecimalMarker) {
        this.targetField = targetField;
        this.maxGapMs = maxGapMs;
        this.deviceDecimalMarker = deviceDecimalMarker;
        // Safety net: a confirmed burst that goes quiet without its Enter is not a reading.
        this.idleTimer = new Timer((int) Math.max(100, maxGapMs * 3), ev -> abortBurst());
        this.idleTimer.setRepeats(false);
    }

    public void install() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this);
    }

    public void uninstall() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(this);
        reset();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {

        // ---- KEY_PRESSED / KEY_RELEASED: only Enter matters -------------------------------
        if (e.getID() != KeyEvent.KEY_TYPED) {
            if (e.getKeyCode() != KeyEvent.VK_ENTER) {
                return false;
            }
            if (e.getID() == KeyEvent.KEY_PRESSED) {
                // Swing fires a field's ActionListener on Enter's KEY_PRESSED, so the
                // decision has to be made here, not on KEY_TYPED.
                swallowRestOfEnter = false;
                if (!confirmed) {
                    return false; // an ordinary Enter from the user
                }
                if (System.currentTimeMillis() - lastEventTime > maxGapMs) {
                    abortBurst(); // stale burst: it was not the device
                    return false; // the user's keystrokes are back; let their Enter act normally
                }
                boolean reading = finishBurst();
                swallowRestOfEnter = true;
                // A valid reading + target focused: let Enter through so the target's own action
                // runs with the weight in place. Otherwise swallow it so no other field fires.
                return !(reading && targetField.hasFocus());
            }
            if (e.getID() == KeyEvent.KEY_RELEASED && swallowRestOfEnter) {
                swallowRestOfEnter = false;
                return true;
            }
            return false;
        }

        // ---- KEY_TYPED --------------------------------------------------------------------
        char c = e.getKeyChar();
        boolean isTerminator = c == '\n';
        if (isTerminator && swallowRestOfEnter) {
            return true; // this Enter was already handled on KEY_PRESSED
        }

        long now = System.currentTimeMillis();
        long gap = now - lastEventTime;
        lastEventTime = now;

        // A confirmed burst must keep streaming; a pause means it wasn't the device.
        if (confirmed && gap > maxGapMs) {
            abortBurst();
        }

        if (isTerminator) {
            if (confirmed) { // fallback, in case KEY_PRESSED wasn't seen
                finishBurst();
                return true;
            }
            reset();
            return false;
        }

        boolean isBufferable = Character.isDigit(c) || c == '.' || c == deviceDecimalMarker;
        if (!isBufferable) {
            reset();
            return false;
        }

        boolean fastFollowUp = buffer.length() > 0 && gap <= maxGapMs;

        if (!confirmed && fastFollowUp) {
            confirmed = true;
            retractFirstCharacter();
        }

        if (confirmed) {
            buffer.append(c);
            idleTimer.restart();
            return true; // swallow
        }

        if (buffer.length() > 0 && !fastFollowUp) {
            reset(); // the previous lone character was just normal typing
        }

        buffer.append(c);
        candidateOwner = currentTextFocusOwner();
        // dispatchKeyEvent runs BEFORE the key reaches the field, so this is the "before" length
        candidateLengthBefore = candidateOwner != null ? candidateOwner.getDocument().getLength() : -1;
        return false; // not confirmed yet: let it type normally
    }

    /** Ends a confirmed burst on Enter. Returns true if it was a valid reading (and delivered). */
    private boolean finishBurst() {
        String text = buffer.toString();
        reset();
        boolean valid = WEIGHT_PATTERN.matcher(text).matches();
        if (valid) {
            deliverToTargetField(text);
        } else {
            Toolkit.getDefaultToolkit().beep(); // the device sent something we don't recognise
        }
        return valid;
    }

    /** The swallowed characters were NOT a scale reading: give them back to the field. */
    private void abortBurst() {
        if (!confirmed) {
            return;
        }
        String text = buffer.toString();
        JTextComponent owner = candidateOwner;
        reset();
        if (owner != null && owner.isShowing() && owner.isEditable() && !text.isEmpty()) {
            owner.replaceSelection(text);
        }
    }

    private JTextComponent currentTextFocusOwner() {
        Component owner = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
        return (owner instanceof JTextComponent) ? (JTextComponent) owner : null;
    }

    private void retractFirstCharacter() {
        JTextComponent owner = candidateOwner;
        if (owner == null) {
            return;
        }
        Document doc = owner.getDocument();
        // Only undo it if the field really took that one character. Its filter may have refused it
        // (field full, odd number...) and then we'd be deleting something the user typed earlier.
        if (doc.getLength() != candidateLengthBefore + 1) {
            return;
        }
        try {
            int pos = owner.getCaretPosition();
            if (pos > 0) {
                doc.remove(pos - 1, 1);
            }
        } catch (BadLocationException ignored) {
            // best effort
        }
    }

    private void reset() {
        idleTimer.stop();
        buffer.setLength(0);
        confirmed = false;
        candidateOwner = null;
        candidateLengthBefore = -1;
    }

    private void deliverToTargetField(String raw) {
        targetField.setText(ArabicDigits.toArabicDigits(
                raw.replace(deviceDecimalMarker, ArabicDigits.ARABIC_DECIMAL_SEPARATOR)));
    }
}

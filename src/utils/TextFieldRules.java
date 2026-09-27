package utils;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * One place to wire up all the per-field rules discussed: - digits only
 * (Western and/or Arabic-Indic), optionally forced to display as Arabic-Indic -
 * a maximum length - Enter moves on (to the next field, or runs whatever action
 * you give it, e.g. "compute") - Delete clears just this field (replaces the
 * default "delete next character" behavior -- that's intentional, per the
 * requested behavior) - Shift+Delete clears a whole group of fields you specify
 * (can be a different group per field)
 *
 * Uses Swing's Key Bindings API (InputMap/ActionMap) rather than a KeyListener,
 * which is the recommended way to bind specific keystrokes to actions: it's not
 * affected by focus traversal quirks and doesn't conflict with
 * WeightScaleCapture's KeyEventDispatcher, which only inspects KEY_TYPED events
 * and leaves Delete/Shift+Delete (KEY_PRESSED) untouched.
 */
public final class TextFieldRules {

    private TextFieldRules() {
    }

    /**
     * @param field the field to configure
     * @param maxLength maximum number of characters allowed
     * @param isDigitsOnly true to accept numbers only
     * @param isDouble true to allow floating point
     * @param onEnter runs when Enter is pressed in this field (e.g. () ->
     * next.requestFocusInWindow(), or () -> compute()); pass null to leave
     * Enter 's default behavior alone
     * @param clearGroupOnShiftDelete the field(s) to clear when Shift+Delete is
     * pressed in this field; if empty, only this field is cleared
     */
    public static void apply(JTextField field,
            int maxLength,
            boolean isDigitsOnly, boolean isDouble,
            Runnable onEnter,
            JTextField... clearGroupOnShiftDelete) {

        // 1) digits only, length limit, optional forced Arabic-Indic display
        ((AbstractDocument) field.getDocument())
                .setDocumentFilter(new NumericDocumentFilter(maxLength, isDigitsOnly, isDouble));

        // 2) Enter -> caller-supplied action (typically "go to next field" or "compute")
        if (onEnter != null) {
            field.addActionListener(e -> onEnter.run());
        }

        // 3) Delete -> clear just this field
        clearOnDelet(field);

        // 4) Shift+Delete -> clear the given group (defaults to just this field)
        JTextField[] group = (clearGroupOnShiftDelete != null && clearGroupOnShiftDelete.length > 0)
                ? clearGroupOnShiftDelete
                : new JTextField[]{field};
        bindKey(field, KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, InputEvent.SHIFT_DOWN_MASK), group);
    }

    public static void apply(JTextField field,
            int maxLength,
            boolean isDigitsOnly, boolean isDouble,
            JComponent next,
            JTextField... clearGroupOnShiftDelete) {
        apply(field, maxLength, isDigitsOnly, isDouble,
                next != null ? () -> {
                            goAndSelect(next);
                        } : field::transferFocus,
                clearGroupOnShiftDelete);
    }

    private static void clearOnDelet(JTextField field) {
        KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0);
        String actionKey = field.getName() + "-clear-" + keyStroke;
        field.getInputMap(JComponent.WHEN_FOCUSED).put(keyStroke, actionKey);
        field.getActionMap().put(actionKey, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                field.setText("");
            }
        });
    }

    private static void bindKey(JTextField field, KeyStroke keyStroke, JTextField... fieldsToClear) {
        String actionKey = field.getName() + "-clear-" + keyStroke;
        field.getInputMap(JComponent.WHEN_FOCUSED).put(keyStroke, actionKey);
        field.getActionMap().put(actionKey, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int firsteditable = -1;
                for (int i = 0; i < fieldsToClear.length; i++) {
                    if (fieldsToClear[i].isEditable()) {
                        firsteditable = (firsteditable == -1) ? i : firsteditable;
                        if (i != 0) {
                            fieldsToClear[i].setText("");
                        }
                    }
                }
                fieldsToClear[firsteditable].requestFocus();
                fieldsToClear[firsteditable].selectAll();
            }
        });
    }

    public static void goAndSelect(JComponent next) {
        if (((JTextField) next).isEditable()) {
            next.requestFocus();
            ((JTextField) next).selectAll();
        } else {
            next.transferFocus();
        }
    }

    public static void changeMaxLength(JTextField field, int newMaxLength) {
        ((NumericDocumentFilter) ((AbstractDocument) field.getDocument()).getDocumentFilter()).setMaxLength(newMaxLength);
    }
}

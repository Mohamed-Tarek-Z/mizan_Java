package utils;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public final class TextFieldRules {

    private TextFieldRules() {
    }

    /**
     * @param field the field to configure
     * @param maxLength maximum number of characters allowed
     * @param isDigitsOnly true to accept numbers only
     * @param isEven true to accept even numbers only
     * @param onEnter runs when Enter is pressed in this field (e.g. () ->
     * next.requestFocusInWindow(), or () -> compute()); pass null to leave
     * Enter 's default behavior alone
     * @param onShiftDelete the field(s) to clear when Shift+Delete is pressed
     * in this field; if empty, only this field is cleared
     */
    public static void apply(JTextField field,
            int maxLength,
            boolean isDigitsOnly,
            boolean isEven,
            Runnable onEnter,
            Runnable onShiftDelete) {

        ((AbstractDocument) field.getDocument())
                .setDocumentFilter(new NumericDocumentFilter(maxLength, isDigitsOnly, isEven));

        if (onEnter != null) {
            field.addActionListener(e -> {
                onEnter.run();
            });
        }

        clearOnDelet(field);

        if (onShiftDelete != null) {
            bindKey(field, KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, InputEvent.SHIFT_DOWN_MASK), onShiftDelete);
        }
    }

    public static void apply(JTextField field,
            int maxLength,
            boolean isDigitsOnly,
            Runnable onEnter,
            Runnable onShiftDelete) {

        apply(field, maxLength, isDigitsOnly, false, onEnter, onShiftDelete);
    }

    public static void apply(JTextField field,
            int maxLength,
            boolean isDigitsOnly,
            JComponent next,
            Runnable onShiftDelete) {
        apply(field, maxLength, isDigitsOnly, false, next != null
                ? () -> {
                    goAndSelect(next);
                } : field::transferFocus, onShiftDelete);
    }

    public static void apply(JTextField field,
            int maxLength,
            boolean isDigitsOnly,
            boolean isEven,
            JComponent next,
            Runnable onShiftDelete) {
        apply(field, maxLength, isDigitsOnly, isEven, next != null
                ? () -> {
                    goAndSelect(next);
                } : field::transferFocus, onShiftDelete);
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

    private static void bindKey(JTextField field, KeyStroke keyStroke, Runnable onShiftDelete) {
        String actionKey = field.getName() + "-clear-" + keyStroke;
        field.getInputMap(JComponent.WHEN_FOCUSED).put(keyStroke, actionKey);
        field.getActionMap().put(actionKey, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onShiftDelete.run();
            }
        });
    }

    public static void goAndSelect(JComponent next) {
        if (((JTextField) next).isEditable()) {
            next.requestFocusInWindow();
            ((JTextField) next).selectAll();
        } else {
            next.transferFocus();
        }
    }

    public static void changeMaxLength(JTextField field, int newMaxLength) {
        ((NumericDocumentFilter) ((AbstractDocument) field.getDocument()).getDocumentFilter()).setMaxLength(newMaxLength);
    }
}

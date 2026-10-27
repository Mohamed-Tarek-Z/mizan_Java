package utils;

import docFilters.IntDocumentFilter;
import exceptions.*;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;
import model.Product;

public class Helper {

    /**
     * Takes a text and add style to it to be used in jOptionpane
     *
     * @param text String to be styled for jOptionpane
     * @return styled String to use in jOptionpane
     */
    public static String addStyle(String text) {
        return "<html><body style='width: 300px'><h1 style='font-family: Arial; font-size: 17pt; text-align: center; '>"
                + text.strip() + "</h1></body></html>";
    }

    /**
     * Takes a fileName and add style to it to be used in jOptionpane
     *
     * @param fileName file name to be check if exist create new file with same
     * name but add number to it like >>> filename(1)
     * @return File object create with the new name
     */
    public static File NewName(String fileName) {
        int idxOfDot = fileName.lastIndexOf('.');
        String extension = fileName.substring(idxOfDot + 1);
        String fullname = fileName.substring(0, idxOfDot);
        String[] Splt = fullname.split("~");
        Path path = Paths.get(fileName);
        for (int i = 1; Files.exists(path); i++) {
            fileName = Splt[0] + " (" + i + ") " + Splt[1] + "." + extension;
            path = Paths.get(fileName);
        }
        return new File(fileName);
    }

    public static void openPanel(JPanel panel, Component comp) {
        panel.removeAll();
        panel.add(comp);
        panel.revalidate();
        panel.repaint();
    }

    public static void fillComboBoxWihProducts(JComboBox<Product> Combo, List<Product> products) throws DatabaseException {
        Combo.removeAllItems();
        Combo.setModel(new DefaultComboBoxModel<>(products.toArray(Product[]::new)));
        Combo.setSelectedIndex(-1);
    }

    /**
     * @param field the field to configure
     * @param documentFilter
     * @param onEnter runs when Enter is pressed in this field (e.g. () ->
     * next.requestFocusInWindow(), or () -> compute()); pass null to leave
     */
    public static void applyDocFilter(JTextField field,
            DocumentFilter documentFilter,
            Runnable onEnter
    ) {

        ((AbstractDocument) field.getDocument())
                .setDocumentFilter(documentFilter);

        if (onEnter != null) {
            field.addActionListener(e -> {
                onEnter.run();
            });
        }
        clearOnDelet(field);
    }

    /**
     * @param field the field to configure
     * @param documentFilter
     */
    public static void applyDocFilter(JTextField field, DocumentFilter documentFilter) {
        applyDocFilter(field, documentFilter, () -> {
        });
    }

    public static void applyDocFilter(JTextField field, DocumentFilter documentFilter, JComponent next) {
        applyDocFilter(field, documentFilter, () -> {
            goAndSelect(next);
        });
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

    public static void bindKey(JComponent component, int when, KeyStroke keyStroke, Runnable action) {
        String actionKey = component.getName() + keyStroke;
        component.getInputMap(when).put(keyStroke, actionKey);
        component.getActionMap().put(actionKey, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action.run();
            }
        });
    }

    public static void goAndSelect(JComponent next) {
        next.getName();
        next.requestFocusInWindow();
        ((JTextField) next).selectAll();
    }

    public static void changeMaxLength(JTextField field, int newMaxLength) {
        ((IntDocumentFilter) ((AbstractDocument) field.getDocument()).getDocumentFilter()).setMaxLength(newMaxLength);
    }
}

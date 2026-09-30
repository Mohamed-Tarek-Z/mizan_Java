package formController;

import controller.ProductController;
import controller.StorageController;
import exceptions.DatabaseException;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Product;
import utils.ArabicDigits;
import utils.ErrorListener;
import utils.TextFieldRules;
import utils.utils;

public class StockFormController {

    private final JTextField stockProSearch;
    private final JTable stockTable;
    private final JComboBox<Product> stockPros;
    private final JButton formOpenerBtn;
    private final JPanel stockPanel;
    private final JPanel leftPanel;

    private final StorageController storController;
    private final ProductController proController;

    private final ErrorListener errorListener;

    public StockFormController(ErrorListener errorListener, JPanel leftPanel, JPanel stockPanel, JButton formOpenerBtn, JTextField stockProSearch, JTable stockTable, JComboBox<Product> stockPros, StorageController storageController, ProductController productController) {
        this.stockProSearch = stockProSearch;
        this.stockTable = stockTable;
        this.stockPros = stockPros;
        this.formOpenerBtn = formOpenerBtn;
        this.stockPanel = stockPanel;
        this.leftPanel = leftPanel;
        this.storController = storageController;
        this.proController = productController;
        this.errorListener = errorListener;
    }

    public void init() {
        TextFieldRules.apply(stockProSearch, 20, false, false, () -> {
            try {
                utils.fillComboBoxWihProducts(stockPros, proController.getAvailableStockProductsLike(stockProSearch.getText()));
            } catch (DatabaseException ex) {
                errorListener.onError(ex);
            }
        }, null);

        this.stockPros.addItemListener((java.awt.event.ItemEvent evt) -> {
            fillStockTable();
        });

        this.formOpenerBtn.addActionListener((ActionEvent evt) -> {
            try {
                utils.openPanel(leftPanel, stockPanel);
                utils.fillComboBoxWihProducts(stockPros, proController.getAvailableStockProductsLike(stockProSearch.getText()));
            } catch (DatabaseException ex) {
                errorListener.onError(ex);
            }
        });
    }

    private void fillStockTable() {
        try {
            if (stockPros.getSelectedIndex() != -1 && stockPros.hasFocus()) {
                DefaultTableModel model = (DefaultTableModel) stockTable.getModel();
                model.setRowCount(0);
                List<String[]> stock = storController.getStockOfProduct(((Product) stockPros.getSelectedItem()).getId());

                for (String[] row : stock) {
                    model.addRow(new Object[]{ArabicDigits.toArabicDigits(row[0]), ArabicDigits.toArabicDigits(row[1]),
                        ArabicDigits.toArabicDigits(row[2]), ArabicDigits.toArabicDigits(row[3]), Boolean.valueOf(row[4])});
                }
            }
        } catch (DatabaseException ex) {
            errorListener.onError(ex);
        }
    }

}

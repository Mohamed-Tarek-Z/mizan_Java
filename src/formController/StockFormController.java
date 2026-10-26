package formController;

import controller.ProductController;
import controller.StorageController;
import docFilters.StringDocumentFilter;
import exceptions.BusinessException;
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
import exceptions.ErrorListener;
import utils.ExcelManager;
import utils.Helper;

public class StockFormController {

    private final JTextField stockProSearch;
    private final JTable stockTable;
    private final JComboBox<Product> stockPros;
    private final JButton stockCreateExcelBtn;
    private final JButton formOpenerBtn;
    private final JPanel stockPanel;
    private final JPanel leftPanel;

    private final StorageController storController;
    private final ProductController proController;

    private final ExcelManager excelManager;

    private final ErrorListener errorListener;

    public StockFormController(ErrorListener errorListener, JPanel leftPanel, JPanel stockPanel, JButton formOpenerBtn, JTextField stockProSearch, JButton stockCreateExcelBtn, JTable stockTable, JComboBox<Product> stockPros, StorageController storageController, ProductController productController, ExcelManager excelManager) {
        this.stockProSearch = stockProSearch;
        this.stockTable = stockTable;
        this.stockPros = stockPros;
        this.storController = storageController;
        this.proController = productController;
        this.stockCreateExcelBtn = stockCreateExcelBtn;
        this.formOpenerBtn = formOpenerBtn;
        this.stockPanel = stockPanel;
        this.leftPanel = leftPanel;
        this.excelManager = excelManager;
        this.errorListener = errorListener;
    }

    public void init() {
        Helper.applyDocFilter(stockProSearch, new StringDocumentFilter(20), () -> {
            try {
                Helper.fillComboBoxWihProducts(stockPros, proController.getAvailableStockProductsLike(stockProSearch.getText()));
            } catch (DatabaseException ex) {
                errorListener.onError(ex);
            }
        });

        this.stockPros.addItemListener((java.awt.event.ItemEvent evt) -> {
            fillStockTable();
        });

        this.stockCreateExcelBtn.addActionListener((ActionEvent evt) -> {
            createExcel();
        });
        this.formOpenerBtn.addActionListener((ActionEvent evt) -> {
            try {
                Helper.openPanel(leftPanel, stockPanel);
                Helper.fillComboBoxWihProducts(stockPros, proController.getAvailableStockProductsLike(stockProSearch.getText()));
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

    private void createExcel() {
        try {
            if (excelManager.stockExcel(storController.getAllStock())) {
                errorListener.onWarning("please print the Execl", "Done");
            }
        } catch (DatabaseException | BusinessException ex) {
            errorListener.onError(ex);
        }
    }

}

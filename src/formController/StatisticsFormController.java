package formController;

import com.toedter.calendar.JDateChooser;
import controller.ExportController;
import controller.ProductController;
import exceptions.BusinessException;
import exceptions.DatabaseException;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
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
import utils.ExcelManager;

public class StatisticsFormController {

    private final JTextField statTotalProduced;
    private final JTextField statProSearch;
    private final JComboBox<Product> statPros;
    private final JButton statSearchBtn;
    private final JButton statCreateExcelBtn;
    private final JTable statTable;

    private final JDateChooser statFromDate;
    private final JDateChooser statToDate;

    private final JButton formOpenerBtn;
    private final JPanel statPanel;
    private final JPanel leftPanel;

    private final ExportController exportController;
    private final ProductController proController;
    private final ExcelManager excelManager;

    private final ErrorListener errorListener;

    public StatisticsFormController(ErrorListener errorListener, JPanel leftPanel, JPanel statPanel, JButton formOpenerBtn, JTextField statProSearch, JComboBox<Product> statPros, JButton statSearchBtn, JButton statCreateExcelBtn, JTable statTable, JDateChooser statFromDate, JDateChooser statToDate, JTextField statTotalProduced, ExportController exportController, ProductController proController, ExcelManager excelManager) {
        this.statProSearch = statProSearch;
        this.statPros = statPros;
        this.statSearchBtn = statSearchBtn;
        this.statCreateExcelBtn = statCreateExcelBtn;
        this.statTable = statTable;
        this.statFromDate = statFromDate;
        this.statToDate = statToDate;
        this.statTotalProduced = statTotalProduced;
        this.exportController = exportController;
        this.proController = proController;
        this.excelManager = excelManager;
        this.formOpenerBtn = formOpenerBtn;
        this.statPanel = statPanel;
        this.leftPanel = leftPanel;
        this.errorListener = errorListener;
    }

    public void init() {
        TextFieldRules.apply(statProSearch, 20, false, () -> {
            try {
                utils.fillComboBoxWihProducts(statPros, proController.getAvailableProductsLike(statProSearch.getText()));
            } catch (DatabaseException ex) {
                errorListener.onError(ex);
            }
        }, null);

        TextFieldRules.apply(statTotalProduced, 10, true, () -> {
        }, null);

        this.formOpenerBtn.addActionListener((ActionEvent evt) -> {
            openerClicked();
        });

        this.statSearchBtn.addActionListener((ActionEvent evt) -> {
            fillStatTable();
        });

        this.statCreateExcelBtn.addActionListener((ActionEvent evt) -> {
            createExcelClicked();
        });
    }

    private void fillStatTable() {
        if (statFromDate.getCalendar() != null && statToDate.getCalendar() != null) {
            DefaultTableModel model = (DefaultTableModel) statTable.getModel();
            model.setRowCount(0);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                List<Object[]> statistics = exportController.getstatistics(dateFormat.format(statFromDate.getCalendar().getTime()), dateFormat.format(statToDate.getCalendar().getTime()), statPros.getSelectedIndex() != -1 ? (Product) statPros.getSelectedItem() : null);
                for (Object[] row : statistics) {
                    model.addRow(new Object[]{row[0], ArabicDigits.toArabicDigits(row[1].toString()),
                        ArabicDigits.toArabicDigits((Double) row[2]), ArabicDigits.toArabicDigits((Double) row[3])});
                }
                double tot = 0.0;
                for (int i = 0; i < model.getRowCount(); i++) {
                    tot += ArabicDigits.parseDouble(model.getValueAt(i, 3).toString());
                }
                statTotalProduced.setText(ArabicDigits.toArabicDigits(tot));
            } catch (DatabaseException ex) {
                errorListener.onError(ex);
            }
        }
    }

    private void openerClicked() {
        utils.openPanel(leftPanel, statPanel);
        reset();
    }

    private void createExcelClicked() {
        if (statFromDate.getCalendar() != null && statToDate.getCalendar() != null) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String fromDate = dateFormat.format(statFromDate.getCalendar().getTime());
                String toDate = dateFormat.format(statToDate.getCalendar().getTime());
                List<Object[]> statistics = exportController.getstatistics(fromDate, toDate, statPros.getSelectedIndex() != -1 ? (Product) statPros.getSelectedItem() : null);
                if (excelManager.staticsticsExcel(statistics, fromDate, toDate)) {
                    errorListener.onWarning("please print the Execl", "Done");
                }
            } catch (DatabaseException | BusinessException ex) {
                errorListener.onError(ex);
            }
        }
    }

    private void reset() {
        try {
            ((DefaultTableModel) statTable.getModel()).setRowCount(0);
            statTable.setAutoCreateRowSorter(true);
            statFromDate.setCalendar(null);
            statToDate.setCalendar(null);
            utils.fillComboBoxWihProducts(statPros, proController.getAvailableProductsLike(""));
            statTotalProduced.setText("");
        } catch (DatabaseException ex) {
            errorListener.onError(ex);
        }
    }

}

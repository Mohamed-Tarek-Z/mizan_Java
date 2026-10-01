package formController;

import com.toedter.calendar.JDateChooser;
import controller.ClientController;
import controller.ExportController;
import controller.ProductController;
import exceptions.BusinessException;
import exceptions.DatabaseException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.Client;
import model.Product;
import utils.ArabicDigits;
import utils.ErrorListener;
import utils.ExcelManager;
import utils.TextFieldRules;
import utils.utils;

public class SoldFormController {

    private final JTextField soldClientSearch;
    private final JTextField soldProSearch;
    private final JComboBox<Product> soldPros;
    private final JButton soldSearchBtn;
    private final JButton soldDeleteBtn;
    private final JButton soldClientSearchBtn;
    private final JButton soldCreateExcelBtn;
    private final JTable soldTable;
    private final JTable soldClientTable;

    private final JDateChooser soldFromDate;
    private final JDateChooser soldToDate;

    private final JButton formOpenerBtn;
    private final JPanel soldPanel;
    private final JPanel leftPanel;

    private final ExportController exportController;
    private final ProductController proController;
    private final ClientController cliController;
    private final ExcelManager excelManager;

    private final ErrorListener errorListener;

    public SoldFormController(ErrorListener errorListener, JPanel leftPanel, JPanel soldPanel, JButton formOpenerBtn, JTextField soldClientSearch, JTextField soldProSearch, JComboBox<Product> soldPros, JButton soldSearchBtn, JButton soldDeleteBtn, JButton soldClientSearchBtn, JButton soldCreateExcelBtn, JTable soldTable, JTable soldClientTable, JDateChooser soldFromDate, JDateChooser soldToDate, ExportController exportController, ProductController proController, ClientController cliController, ExcelManager excelManager) {
        this.soldClientSearch = soldClientSearch;
        this.soldProSearch = soldProSearch;
        this.soldPros = soldPros;
        this.soldSearchBtn = soldSearchBtn;
        this.soldDeleteBtn = soldDeleteBtn;
        this.soldClientSearchBtn = soldClientSearchBtn;
        this.soldCreateExcelBtn = soldCreateExcelBtn;
        this.soldTable = soldTable;
        this.soldClientTable = soldClientTable;
        this.soldFromDate = soldFromDate;
        this.soldToDate = soldToDate;
        this.exportController = exportController;
        this.proController = proController;
        this.cliController = cliController;
        this.excelManager = excelManager;
        this.formOpenerBtn = formOpenerBtn;
        this.soldPanel = soldPanel;
        this.leftPanel = leftPanel;
        this.errorListener = errorListener;
    }

    public void init() {
        TextFieldRules.apply(soldProSearch, 20, false, false, () -> {
            try {
                utils.fillComboBoxWihProducts(soldPros, proController.getAvailableProductsLike(soldProSearch.getText()));
            } catch (DatabaseException ex) {
                errorListener.onError(ex);
            }
        }, null);

        TextFieldRules.apply(soldClientSearch, 20, false, false, () -> {
        }, null);

        this.formOpenerBtn.addActionListener((ActionEvent evt) -> {
            openerClicked();
        });

        this.soldSearchBtn.addActionListener((ActionEvent evt) -> {
            fillSoldTable();
        });

        this.soldCreateExcelBtn.addActionListener((ActionEvent evt) -> {
            createExcelClicked();
        });
        this.soldClientSearchBtn.addActionListener((ActionEvent evt) -> {
            fillSoldClientTable();
        });
        this.soldDeleteBtn.addActionListener((ActionEvent evt) -> {
            deleteSoldClicked();
        });

        this.soldTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                try {
                    if (evt.getClickCount() == 3) {
                        TableModel model = soldTable.getModel();
                        String temp = exportController.getDetailsForOrder(Integer.parseInt(model.getValueAt(soldTable.getSelectedRow(), 6).toString()));
                        errorListener.onWarning(temp, "Exported Pallets");
                    }
                } catch (DatabaseException ex) {
                    errorListener.onError(ex);
                }
            }
        });

    }

    private void fillSoldTable() {
        try {
            String selectedCIDs = "";
            if (soldClientTable.getSelectedRowCount() > 0) {
                for (int selectedRow : soldClientTable.getSelectedRows()) {
                    selectedCIDs += soldClientTable.getValueAt(selectedRow, 0) + ",";
                }
                selectedCIDs = selectedCIDs.substring(0, selectedCIDs.length() - 1);
            }
            if (soldFromDate.getCalendar() != null && soldToDate.getCalendar() != null) {
                DefaultTableModel model = (DefaultTableModel) soldTable.getModel();
                model.setRowCount(0);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                List<Object[]> yumya = exportController.getYuwmya(dateFormat.format(soldFromDate.getCalendar().getTime()),
                        dateFormat.format(soldToDate.getCalendar().getTime()), selectedCIDs,
                        soldPros.getSelectedItem() != null ? (Product) soldPros.getSelectedItem() : null);
                for (Object[] row : yumya) {
                    model.addRow(new Object[]{row[2], ArabicDigits.toArabicDigits(row[1].toString()),
                        ArabicDigits.toArabicDigits(row[3].toString()), ArabicDigits.toArabicDigits(row[5].toString()),
                        ArabicDigits.toArabicDigits(row[0].toString()), ArabicDigits.toArabicDigits(row[4].toString()), row[6]});
                }
            }
        } catch (DatabaseException ex) {
            errorListener.onError(ex);
        }
    }

    private void fillSoldClientTable() {
        try {
            DefaultTableModel model = (DefaultTableModel) soldClientTable.getModel();
            model.setRowCount(0);
            List<Client> clients = cliController.getClientLike(soldClientSearch.getText());
            for (Client client : clients) {
                model.addRow(new Object[]{client.getId(), client.getName()});
            }
        } catch (DatabaseException ex) {
            errorListener.onError(ex);
        }
    }

    private void deleteSoldClicked() {
        try {

            if (soldTable.getSelectedRow() != -1) {
                if (JOptionPane.showConfirmDialog(null, utils.addStyle(
                        "هل تريد استرجاع أزن " + soldTable.getValueAt(soldTable.getSelectedRow(), 0) + " لصنف"
                        + soldTable.getValueAt(soldTable.getSelectedRow(), 1) + " في يوم"
                        + soldTable.getValueAt(soldTable.getSelectedRow(), 5) + " "),
                        "تنبيه",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    exportController.moveBagFromExportToStorage((int) soldTable.getValueAt(soldTable.getSelectedRow(), 6));
                    soldSearchBtn.doClick();
                }
            } else {
                errorListener.onWarning(" يجب اختيار بيان من الجدول ", "إنتبه");
            }

        } catch (DatabaseException | BusinessException ex) {
            errorListener.onError(ex);
        }
    }

    private void createExcelClicked() {
        if (soldClientTable.getSelectedRowCount() > 0) {
            if (excelManager.youmiaExcel(getRows((DefaultTableModel) soldClientTable.getModel()))) {
                errorListener.onWarning("please print the Execl", "Done");
            }
        } else {
            errorListener.onWarning("select Client please", "input Error");
        }
    }

    private void reset() {
        soldFromDate.setCalendar(null);
        soldToDate.setCalendar(null);
        soldClientTable.setAutoCreateRowSorter(true);
        soldTable.setAutoCreateRowSorter(true);
        try {
            utils.fillComboBoxWihProducts(soldPros, proController.getAvailableProductsLike(""));
        } catch (DatabaseException ex) {
            errorListener.onError(ex);
        }
    }

    private void openerClicked() {
        utils.openPanel(leftPanel, soldPanel);
        reset();
    }

    private List<Object[]> getRows(DefaultTableModel model) {
        List<Object[]> data = new ArrayList<>();
        Object[] rw = new Object[model.getColumnCount()];
        for (int row = 0; row < model.getRowCount(); row++) {
            for (int col = 0; col < model.getColumnCount(); col++) {
                rw[col] = model.getValueAt(row, col);
            }
            data.add(rw);
        }
        return data;
    }

}

package formController;

import controller.ProductController;
import exceptions.BusinessException;
import exceptions.DatabaseException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Product;
import utils.ArabicDigits;
import utils.ErrorListener;
import utils.TextFieldRules;

public class ProductFormController {

    private final JTextField proName;
    private final JTextField proConeWeight;
    private final JTextField proConeColor;
    private final JCheckBox proIsBox;

    private final JTable proTable;
    private final JButton addProBtn;
    private final JButton editProBtn;
    private final JButton delProBtn;

    private final JButton formOpenerBtn;
    private final JPanel proPanel;

    private final ProductController prosController;
    private final ErrorListener errorListener;

    public ProductFormController(ErrorListener errorListener, JButton formOpenerBtn, JPanel proPanel, JTextField proName, JTextField proConeWeight, JTextField proConeColor, JCheckBox proIsBox, JTable proTable, JButton addProBtn, JButton editProBtn, JButton delProBtn, ProductController prosController) {
        this.proName = proName;
        this.proConeWeight = proConeWeight;
        this.proConeColor = proConeColor;
        this.proIsBox = proIsBox;
        this.proTable = proTable;
        this.addProBtn = addProBtn;
        this.editProBtn = editProBtn;
        this.delProBtn = delProBtn;
        this.prosController = prosController;
        this.errorListener = errorListener;
        this.formOpenerBtn = formOpenerBtn;
        this.proPanel = proPanel;
    }

    public void init() throws DatabaseException {

        TextFieldRules.apply(proName, 35, false, false, () -> {
        }, this::clearForm);
        TextFieldRules.apply(proConeWeight, 4, true, false, () -> {
        }, this::clearForm);

        this.proTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                proTableClicked();
            }
        });
        this.addProBtn.addActionListener((ActionEvent evt) -> {
            addPro();
        });
        this.editProBtn.addActionListener((ActionEvent evt) -> {
            editPro();
        });
        this.delProBtn.addActionListener((ActionEvent evt) -> {
            deletePro();
        });

        this.formOpenerBtn.addActionListener((ActionEvent evt) -> {
            openerClicked();
        });

        this.fillProTable();
    }

    private void addPro() {
        try {
            if (!proName.getText().isBlank() && !proConeWeight.getText().isBlank()) {
                if (prosController.getProduct(proName.getText()) != null) {
                    errorListener.onWarning("هذا الصنف موجود بالفعل", "إنتبه");
                } else {
                    prosController.addNewProduct(proName.getText().trim(), proConeWeight.getText(), proConeColor.getText(), proIsBox.isSelected());
                    errorListener.onWarning("تم الإضافة", "عملية ناجحة");
                    reset();
                }
            } else {
                errorListener.onWarning("أدخل بيانات كاملة", "عملية غير ناجحة");
            }
        } catch (DatabaseException | BusinessException ex) {
            errorListener.onError(ex);
        }
    }

    private void editPro() {
        try {
            if (!proName.getText().isBlank() && !proConeWeight.getText().isBlank() && proTable.getSelectedRow() > -1) {
                prosController.updateProduct(Integer.parseInt(proTable.getModel().getValueAt(proTable.getSelectedRow(), 0).toString()), proName.getText().trim(),
                        proConeWeight.getText(), proConeColor.getText(), proIsBox.isSelected());
                errorListener.onWarning("تم التعديل", "عملية ناجحة");
                reset();
            } else {
                errorListener.onWarning("أختر صنف أو أدخل بيانات كاملة", "عملية غير ناجحة");
            }
        } catch (DatabaseException | BusinessException ex) {
            errorListener.onError(ex);
        }
    }

    private void deletePro() {
        try {
            if (proTable.getSelectedRow() > -1) {
                if (prosController.removeProduct(Integer.parseInt(proTable.getModel().getValueAt(proTable.getSelectedRow(), 0).toString()))) {
                    errorListener.onWarning("تم حذف الصنف بنجاح ", "ناجح");
                    reset();
                } else {
                    errorListener.onWarning("لا يمكن حذف هذا الصنف ", "إنتبه");
                }
            } else {
                errorListener.onWarning(" برجاء أختيار صنف من الجدول أولا", "إنتبه");
            }
        } catch (DatabaseException | BusinessException ex) {
            errorListener.onError(ex);
        }
    }

    public void fillProTable() throws DatabaseException {
        DefaultTableModel model = (DefaultTableModel) proTable.getModel();
        model.setRowCount(0);
        List<Product> pros = prosController.getAvailableProductsLike("");
        for (Product pro : pros) {
            model.addRow(new Object[]{pro.getId(), pro.getName(), ArabicDigits.toArabicDigits(pro.getWeight_of_con()),
                pro.getColor(), pro.isBox()});
        }
    }

    private void proTableClicked() {
        proName.setText(proTable.getModel().getValueAt(proTable.getSelectedRow(), 1).toString());
        proConeWeight.setText(proTable.getModel().getValueAt(proTable.getSelectedRow(), 2).toString());
        proConeColor.setText(proTable.getModel().getValueAt(proTable.getSelectedRow(), 3).toString());
        proIsBox.setSelected(proTable.getModel().getValueAt(proTable.getSelectedRow(), 4).toString().equals("true"));
    }

    private void openerClicked() {
        try {
            open_panel(proPanel);
            fillProTable();
        } catch (DatabaseException ex) {
            errorListener.onError(ex);
        }
    }

    private void clearForm() {
        proName.setText("");
        proConeWeight.setText("");
        proConeColor.setText("");

    }

    private void reset() throws DatabaseException {
        fillProTable();
        clearForm();
    }
}

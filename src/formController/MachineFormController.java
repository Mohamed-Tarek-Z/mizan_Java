package formController;

import controller.MachineController;
import controller.ProductController;
import docFilters.StringDocumentFilter;
import exceptions.BusinessException;
import exceptions.DatabaseException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.Machine;
import model.Product;
import exceptions.ErrorListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import utils.Helper;

public final class MachineFormController {

    private final JTextField machName;
    private final JTextField machLot;
    private final JComboBox<Product> machPros;
    private final JTable machTable;
    private final JButton addMachBtn;
    private final JButton editMachBtn;
    private final JButton delMachBtn;
    private final MachineController machController;
    private final ProductController prosController;
    private final JPanel machPanel;
    private final ErrorListener errorListener;
    private final JTabbedPane settingsTapps;
    private final StorageFormController sfc;

    public MachineFormController(ErrorListener errorListener, JTabbedPane settingsTapps,
            JPanel machPanel, JTextField machName, JTextField machLot, JComboBox<Product> machPros,
            JButton addMachBtn, JButton editMachBtn, JButton delMachBtn,
            JTable machTable, MachineController machController, ProductController prosController, StorageFormController sfc) {
        this.machName = machName;
        this.machLot = machLot;
        this.machPros = machPros;
        this.addMachBtn = addMachBtn;
        this.editMachBtn = editMachBtn;
        this.delMachBtn = delMachBtn;
        this.machTable = machTable;
        this.machController = machController;
        this.prosController = prosController;
        this.settingsTapps = settingsTapps;
        this.sfc = sfc;
        this.machPanel = machPanel;
        this.errorListener = errorListener;
    }

    public void init() throws DatabaseException {
        Helper.applyDocFilter(machName, new StringDocumentFilter(15));
        Helper.applyDocFilter(machLot, new StringDocumentFilter(15));

        Helper.bindKey(machPanel, JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT,
                KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, InputEvent.SHIFT_DOWN_MASK), this::clearForm);

        machTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                machTableClicked(evt);
            }
        });

        addMachBtn.addActionListener(e -> {
            addMach();
        });
        editMachBtn.addActionListener(e -> {
            editMach();
        });
        delMachBtn.addActionListener(e -> {
            deleteMach();
        });
        settingsTapps.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                settingsTapsClicked();
            }
        });
        reset();
    }

    private void addMach() {
        try {
            if (!machName.getText().isBlank() && !machLot.getText().isBlank() && machPros.getSelectedIndex() != -1) {
                machController.addMachine(machName.getText(), machPros.getSelectedItem().toString(), machLot.getText());
                reset();
                errorListener.onWarning("تم الإضافة", "عملية ناجحة");
            } else {
                errorListener.onWarning("أدخل بيانات كاملة", "عملية غير ناجحة");
            }
        } catch (DatabaseException | BusinessException ex) {
            errorListener.onError(ex);
        }
    }

    private void editMach() {
        try {
            if (!machName.getText().isBlank() && !machLot.getText().isBlank() && machPros.getSelectedIndex() != -1 && machTable.getSelectedRow() > -1) {
                machController.editMachine(new Machine(
                        (int) machTable.getModel().getValueAt(machTable.getSelectedRow(), 0),
                        machName.getText(),
                        prosController.getProduct(machPros.getSelectedItem().toString()).getId(),
                        machLot.getText(), new Date()));
                reset();
                errorListener.onWarning("تم التعديل", "عملية ناجحة");
            } else {
                errorListener.onWarning("أختر ماكينة أو أدخل بيانات كاملة", "عملية غير ناجحة");
            }
        } catch (DatabaseException | BusinessException ex) {
            errorListener.onError(ex);
        }
    }

    private void deleteMach() {
        if (machTable.getSelectedRow() > -1) {
            try {
                if (machController.removeMachine((int) machTable.getModel().getValueAt(machTable.getSelectedRow(), 0))) {
                    reset();
                } else {
                    errorListener.onWarning("لا يمكن حذف هذه الماكينة ", "إنتبه");
                }
            } catch (DatabaseException ex) {
                errorListener.onError(ex);
            }
        } else {
            errorListener.onWarning(" برجاء أختيار من الجدول أولا", "إنتبه");
        }
    }

    public void fillMachTable() throws DatabaseException {

        DefaultTableModel model = (DefaultTableModel) machTable.getModel();
        model.setRowCount(0);
        List<Machine> machs = machController.getMachines();
        for (Machine mach : machs) {
            Product p;
            try {
                p = prosController.getProduct(mach.getProId());
                model.addRow(new Object[]{mach.getMachId(), mach.getMachName(), p.getName(), mach.getLot(), mach.getUpdatedAt()});
            } catch (BusinessException ex) {
                model.addRow(new Object[]{mach.getMachId(), mach.getMachName(), "ممسوح", mach.getLot(),
                    mach.getUpdatedAt()});
            }
        }

    }

    private void machTableClicked(MouseEvent evt) {
        try {
            TableModel model = machTable.getModel();
            if (evt.getClickCount() < 3) {
                machName.setText(((String) model.getValueAt(machTable.getSelectedRow(), 1)).strip());
                machLot.setText(((String) model.getValueAt(machTable.getSelectedRow(), 3)).strip());
                try {
                    machPros.setSelectedItem(prosController.getProduct(
                            (String) machTable.getModel().getValueAt(machTable.getSelectedRow(), 2)));
                } catch (BusinessException ex) {
                    machPros.setSelectedIndex(-1);
                }
            } else {
                sfc.setFromMachine((String) machTable.getModel().getValueAt(machTable.getSelectedRow(), 2),
                        ((String) model.getValueAt(machTable.getSelectedRow(), 3)).strip());
            }
        } catch (DatabaseException ex) {
            errorListener.onError(ex);
        }
    }

    private void settingsTapsClicked() {
        try {
            if (settingsTapps.getSelectedIndex() == 0) {
                fillMachTable();
                Helper.fillComboBoxWihProducts(machPros, prosController.getAvailableProductsLike(""));
            }
        } catch (DatabaseException ex) {
            errorListener.onError(ex);
        }
    }

    private void reset() throws DatabaseException {
        fillMachTable();
        clearForm();
        machPros.setSelectedIndex(-1);
        Helper.fillComboBoxWihProducts(machPros, prosController.getAvailableProductsLike(""));
    }

    private void clearForm() {
        machName.setText("");
        machLot.setText("");
    }
}

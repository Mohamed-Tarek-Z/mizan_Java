package formController;

import controller.ProductController;
import controller.StorageController;
import exceptions.BusinessException;
import exceptions.DatabaseException;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Bag;
import model.Product;
import model.Ticket;
import utils.ArabicDigits;
import utils.ErrorListener;
import utils.PrinterManager;
import utils.TextFieldRules;
import utils.WeightScaleCapture;
import utils.utils;
import view.Mainform;
import view.MultiEdit;
import view.SingleEdit;

public class StorageFormController {

    private final JTextField stConeColor;
    private final JTextField stPalletWeight;
    private final JTextField stConeWeight;
    private final JTextField stNetWeight;
    private final JTextField stTotalWeight;
    private final JTextField stEmptyBag;
    private final JTextField stConeCount;
    private final JTextField stPalletNumber;
    private final JTextField stLot;
    private final JTextField stFilterPros;
    private final JTextField stError;

    private final JCheckBox stPrintTicket;
    private final JCheckBox stIgnoreLimit;
    private final JCheckBox stIsBox;
    private final JCheckBox stMarkBag;
    private final JCheckBox stDisableNumberOfCone;
    private final JCheckBox stDisableEmptyBagWeight;
    private final JCheckBox stDonotReadConeWeight;

    private final JLabel stEmptyBagLabel;

    private final JComboBox<Product> stPros;

    private final JButton stAdd;
    private final JButton stDelete;
    private final JButton stReprint;
    private final JButton stClear;

    private final JTable stTable;

    private final JProgressBar stPalletCount;

    private final StorageController storageController;
    private final ProductController productController;
    private final PrinterManager printerManager;

    private final JButton formOpenerBtn;
    private final JPanel stPanel;
    private final JPanel leftPanel;
    private final JPanel printPanel;
    private final SettingsFormController sfc;
    private final ErrorListener errorListener;

    public StorageFormController(ErrorListener errorListener, JPanel leftPanel, JPanel printPanel, SettingsFormController sfc, JPanel stPanel, JButton formOpenerBtn,
            JTextField stConeColor, JTextField stPalletWeight, JTextField stConeWeight, JTextField stNetWeight, JTextField stTotalWeight,
            JTextField stEmptyBag, JTextField stConeCount, JTextField stPalletNumber, JTextField stLot, JTextField stFilterPros,
            JTextField stError, JLabel stEmptyBagLabel, JCheckBox stPrintTicket, JCheckBox stIgnoreLimit, JCheckBox stIsBox, JCheckBox stMarkBag,
            JCheckBox stDisableNumberOfCone, JCheckBox stDisableEmptyBagWeight, JCheckBox stDonotReadConeWeight, JComboBox<Product> stPros,
            JButton stAdd, JButton stDelete, JButton stReprint, JButton stClear, JTable stTable, JProgressBar stPalletCount,
            StorageController storageController, ProductController productController, PrinterManager printerManager) {
        this.stConeColor = stConeColor;
        this.stPalletWeight = stPalletWeight;
        this.stConeWeight = stConeWeight;
        this.stNetWeight = stNetWeight;
        this.stTotalWeight = stTotalWeight;
        this.stEmptyBag = stEmptyBag;
        this.stConeCount = stConeCount;
        this.stPalletNumber = stPalletNumber;
        this.stLot = stLot;
        this.stFilterPros = stFilterPros;
        this.stError = stError;
        this.stEmptyBagLabel = stEmptyBagLabel;
        this.stPrintTicket = stPrintTicket;
        this.stIgnoreLimit = stIgnoreLimit;
        this.stIsBox = stIsBox;
        this.stMarkBag = stMarkBag;
        this.stDisableNumberOfCone = stDisableNumberOfCone;
        this.stDisableEmptyBagWeight = stDisableEmptyBagWeight;
        this.stDonotReadConeWeight = stDonotReadConeWeight;
        this.stPros = stPros;
        this.stAdd = stAdd;
        this.stDelete = stDelete;
        this.stReprint = stReprint;
        this.stClear = stClear;
        this.stTable = stTable;
        this.stPalletCount = stPalletCount;
        this.storageController = storageController;
        this.productController = productController;
        this.printerManager = printerManager;
        this.formOpenerBtn = formOpenerBtn;
        this.stPanel = stPanel;
        this.leftPanel = leftPanel;
        this.printPanel = printPanel;
        this.sfc = sfc;
        this.errorListener = errorListener;
    }

    public void init() {

        TextFieldRules.apply(stLot, 15, false, stPalletNumber, this::clearStorageForm);
        TextFieldRules.apply(stConeCount, 3, true, stEmptyBag, this::clearStorageForm);
        TextFieldRules.apply(stEmptyBag, 2, true, stTotalWeight, this::clearStorageForm);
        TextFieldRules.apply(stPalletNumber, 3, true, () -> {
            TextFieldRules.goAndSelect(stConeCount);
            try {
                calc_pallet_weight();
            } catch (DatabaseException ex) {
                errorListener.onError(ex);
            }
        }, null);
        TextFieldRules.apply(stTotalWeight, 8, true, () -> {
            stNetWeight.requestFocusInWindow();
            try {
                calc_net_weight();
            } catch (BusinessException e) {
                showMessageInlable(true);
            }
        }, this::clearStorageForm);
        TextFieldRules.apply(stNetWeight, 8, true, () -> {
            stAdd.doClick();
            stConeCount.requestFocusInWindow();
        }, this::clearStorageForm);
        TextFieldRules.apply(stConeWeight, 4, true, stConeCount, null);
        TextFieldRules.apply(stFilterPros, 20, false, () -> {
            try {
                utils.fillComboBoxWihProducts(stPros, productController.getAvailableProductsLike(stFilterPros.getText()));
            } catch (DatabaseException ex) {
                errorListener.onError(ex);
            }
        }, null);

        stTable.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                if (evt.getKeyChar() == KeyEvent.VK_DELETE && stTable.hasFocus()) {
                    stDelete.doClick();
                }
            }
        });
        stTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });

        stReprint.addActionListener((ActionEvent evt) -> {
            try {
                printerManager.printPanelToImage(printPanel);
                stConeCount.requestFocusInWindow();
            } catch (BusinessException ex) {
                errorListener.onError(ex);
            }
        });

        stPros.addItemListener((ItemEvent evt) -> {
            proChanged();
        });

        stClear.addActionListener((ActionEvent evt) -> {
            clearStorageForm();
        });
        stDelete.addActionListener((ActionEvent evt) -> {
            deleteStorage();
        });
        stAdd.addActionListener((ActionEvent evt) -> {
            addStorage();
        });

        formOpenerBtn.addActionListener((ActionEvent evt) -> {
            utils.openPanel(leftPanel, stPanel);
            if (stPros.getSelectedIndex() == -1) {
                DefaultTableModel model = (DefaultTableModel) stTable.getModel();
                model.setRowCount(0);
            }
        });
        new WeightScaleCapture(stTotalWeight).install();

    }

    private void calc_net_weight() throws BusinessException {
        if (!stConeWeight.getText().isBlank()) {
            int num_of_con = ArabicDigits.parseInt(requireText(stConeCount, "عدد الكون"));
            double weight_of_con = ArabicDigits.parseInt(requireText(stConeWeight, "وزن الكون")) / 1000.00,
                    bag_weight = ArabicDigits.parseInt(requireText(stEmptyBag, "وزن الفارغ")) / 100.00,
                    weight = ArabicDigits.parseDouble(requireText(stTotalWeight, "وزن القائم"));

            stNetWeight.setText(ArabicDigits.toArabicDigits(weight - (bag_weight + (num_of_con * weight_of_con))));
        }

    }

    private void calc_pallet_weight() throws DatabaseException {
        if (!stPalletNumber.getText().isEmpty() && !stLot.getText().isEmpty()
                && stPros.getSelectedIndex() != -1) {
            stPalletWeight.setText(ArabicDigits.toArabicDigits(storageController.calc_pallet_weight(stPalletNumber.getText(),
                    stLot.getText(), ((Product) stPros.getSelectedItem()).getId())));
        } else {
            stPalletWeight.setText("");
        }
    }

    protected void fill_storage_table() throws DatabaseException {
        DefaultTableModel model = (DefaultTableModel) stTable.getModel();
        model.setRowCount(0);
        List<Bag> bags = storageController.getBags(((Product) stPros.getSelectedItem()).getId());

        for (Bag bag : bags) {
            model.addRow(new Object[]{ArabicDigits.toArabicDigits(bag.getWeight()), ArabicDigits.toArabicDigits(bag.getNum_of_con()),
                ArabicDigits.toArabicDigits(bag.getLot()), ArabicDigits.toArabicDigits(bag.getPallet_numb()), bag.getId(), "",
                bag.isUsed()});
        }
        Product pro = productController.getProduct(stPros.getSelectedItem().toString());
        if (pro != null) {
            if (!stDonotReadConeWeight.isSelected()) {
                stConeWeight.setText(ArabicDigits.toArabicDigits(pro.getWeight_of_con()));
            }
            stConeColor.setText(pro.getColor());
            stIsBox.setSelected(pro.isBox());
            TextFieldRules.changeMaxLength(stEmptyBag, pro.isBox() ? 3 : 2);
            stEmptyBagLabel.setText(!pro.isBox() ? "فارغ الشيكاره" : "فارغ الصندوق");
            stEmptyBag.setBackground(pro.isBox() ? Color.pink : Color.WHITE);
        }

        if (model.getRowCount() != 0) {
            String lott = ArabicDigits.normalizeForParsing(model.getValueAt(model.getRowCount() - 1, 2).toString()),
                    pallet_num = ArabicDigits.normalizeForParsing(model.getValueAt(model.getRowCount() - 1, 3).toString());
            int cunt = 0;
            for (int i = model.getRowCount() - 1; i >= 0; i--) {
                if (!lott.equals(ArabicDigits.normalizeForParsing(model.getValueAt(i, 2).toString()))
                        || !pallet_num.equals(ArabicDigits.normalizeForParsing(model.getValueAt(i, 3).toString()))) {
                    lott = ArabicDigits.normalizeForParsing(model.getValueAt(i, 2).toString());
                    pallet_num = ArabicDigits.normalizeForParsing(model.getValueAt(i, 3).toString());
                    cunt = 0;
                }

                model.setValueAt(ArabicDigits.toArabicDigits(++cunt), i, 5);
            }
        }
    }

    private void showMessageInlable(boolean red) {
        stError.setBackground(red ? Color.red : Color.white);
    }

    private String requireText(JTextField field, String name) {
        String value = field.getText().trim();
        if (value.isEmpty()) {
            field.requestFocusInWindow();
            throw new BusinessException("برجاء إدخال قيمة صحيحة " + name);
        }
        return value;
    }

    private Bag buildRequestFromUI() {
        try {
            if (stPros.getSelectedIndex() == -1) {
                throw new BusinessException("يجب إختيار صنف");
            }
            return new Bag(((Product) stPros.getSelectedItem()).getId(),
                    ArabicDigits.parseInt(requireText(stConeCount, "عدد الكون")),
                    ArabicDigits.parseInt(requireText(stPalletNumber, "رقم البالتة")),
                    ArabicDigits.parseDouble(requireText(stTotalWeight, "الوزن")),
                    ArabicDigits.parseDouble(requireText(stNetWeight, "الوزن الصافي")),
                    ArabicDigits.parseInt(requireText(stEmptyBag, "الوزن الفارغ")) / 100,
                    ArabicDigits.normalizeForParsing(requireText(stLot, "اللوط")),
                    stMarkBag.isSelected()
            );

        } catch (BusinessException e) {
            errorListener.onError(e);
        }
        return null;
    }

    private void tableMouseClicked(MouseEvent evt) {
        try {
            if (evt.getClickCount() == 3 && stTable.getSelectedRowCount() > 0) {
                if (stTable.getSelectedRowCount() == 1) {
                    Bag bag = storageController.getBagById((int) stTable.getModel().getValueAt(stTable.getSelectedRow(), 4));
                    Product product = productController.getProduct(bag.getPro_id());
                    new SingleEdit(bag, product, storageController, sfc, this, errorListener);

                    ((Mainform) errorListener).setEnabled(false);

                } else if (stTable.getSelectedRowCount() > 1) {
                    MultiEdit me = new MultiEdit((Product) stPros.getSelectedItem(),
                            stTable.getModel().getValueAt(stTable.getSelectedRow(), 3).toString(),
                            stTable.getModel().getValueAt(stTable.getSelectedRow(), 2).toString(),
                            (boolean) stTable.getModel().getValueAt(stTable.getSelectedRow(), 6)
                    );
                    
                    ((Mainform) errorListener).setEnabled(false);
                }
            }
        } catch (DatabaseException | BusinessException ex) {
            errorListener.onError(ex);
        }
    }

    private void proChanged() {
        try {
            stLot.setText("");

            if (stPros.hasFocus()) {
                stDonotReadConeWeight.setSelected(false);
                stIgnoreLimit.setSelected(false);
                stDisableNumberOfCone.setSelected(false);
                stDisableEmptyBagWeight.setSelected(false);
                stMarkBag.setSelected(false);
                fill_storage_table();
            }
            if (stTable.getRowCount() != 0) {
                stLot.setText((String) stTable.getValueAt(0, 2));
                if (!stTable.getValueAt(0, 5).equals("٢٠")) {
                    stPalletNumber.setText((String) stTable.getValueAt(0, 3));
                } else {
                    stPalletNumber.setText(ArabicDigits.toArabicDigits(ArabicDigits.parseInt((String) stTable.getValueAt(0, 3)) + 1));
                }
                calc_pallet_weight();
            }
        } catch (DatabaseException | BusinessException ex) {
            errorListener.onError(ex);
        }
    }

    private void clearStorageForm() {
        if (!stDisableEmptyBagWeight.isSelected()) {
            stEmptyBag.setText("");
        }
        stTotalWeight.setText("");
        stNetWeight.setText("");
        stConeCount.requestFocusInWindow();
        if (!stDisableNumberOfCone.isSelected()) {
            stConeCount.selectAll();
        }
    }

    private void deleteStorage() {
        try {
            if (errorListener.onQuest("هل تريد الحذف ؟", "تنبيه") == JOptionPane.YES_OPTION) {
                if (stTable.getSelectedRowCount() > 0) {
                    for (int row : stTable.getSelectedRows()) {
                        storageController.removeBag(stTable.getModel().getValueAt(row, 4).toString());
                    }
                    fill_storage_table();
                    errorListener.onWarning("تم حذف البيــانات بنجاح", "ناجح");
                } else {
                    errorListener.onWarning("برجاء أختيار بيان من الجدول أولا", "إنتبه");
                }
            }
        } catch (DatabaseException | BusinessException ex) {
            errorListener.onError(ex);
        }
    }

    private void addStorage() {
        try {
            calc_net_weight();
            Bag req = buildRequestFromUI();

            if (req == null) {
                return;
            }

            int[] result = storageController.addStorage(req, stIgnoreLimit.isSelected());

            stPalletNumber.setText(ArabicDigits.toArabicDigits(result[0]));
            stPalletCount.setValue(result[1]);
            calc_pallet_weight();

            sfc.PreparePrintingPanel(new Ticket(((Product) stPros.getSelectedItem()).getName(),
                    stPalletNumber.getText(),
                    ((Product) stPros.getSelectedItem()).getColor(),
                    stLot.getText(),
                    stConeCount.getText(),
                    stTotalWeight.getText(),
                    stNetWeight.getText()
            ), stPrintTicket.isSelected());
            clearStorageForm();
            fill_storage_table();
            showMessageInlable(false);
        } catch (DatabaseException ex) {
            errorListener.onError(ex);
        } catch (BusinessException ex) {
            showMessageInlable(true);
        }
    }
}

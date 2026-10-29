package formController;

import controller.ProductController;
import controller.StorageController;
import docFilters.DoubleDocumentFilter;
import docFilters.IntDocumentFilter;
import docFilters.StringDocumentFilter;
import exceptions.BusinessException;
import exceptions.DatabaseException;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import model.Bag;
import model.Product;
import model.Ticket;
import utils.ArabicDigits;
import exceptions.ErrorListener;
import java.awt.event.InputEvent;
import utils.Config;
import utils.PrinterManager;
import utils.WeightScaleCapture;
import utils.Helper;
import view.Main;
import view.MultiEdit;
import view.PrintPanel;
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
    private final PrintPanel printPanel;
    private final ErrorListener errorListener;
    private final Main mainform;

    public StorageFormController(ErrorListener errorListener, Main mainform, JPanel leftPanel, PrintPanel printPanel, JPanel stPanel, JButton formOpenerBtn,
            JTextField stConeColor, JTextField stPalletWeight, JTextField stConeWeight, JTextField stNetWeight, JTextField stTotalWeight,
            JTextField stEmptyBag, JTextField stConeCount, JTextField stPalletNumber, JTextField stLot, JTextField stFilterPros,
            JTextField stError, JLabel stEmptyBagLabel, JCheckBox stPrintTicket, JCheckBox stIgnoreLimit, JCheckBox stIsBox, JCheckBox stMarkBag,
            JCheckBox stDisableNumberOfCone, JCheckBox stDisableEmptyBagWeight, JCheckBox stDonotReadConeWeight, JComboBox<Product> stPros,
            JButton stAdd, JButton stDelete, JButton stReprint, JButton stClear, JTable stTable, JProgressBar stPalletCount,
            StorageController storageController, ProductController productController, PrinterManager printerManager) {
        this.mainform = mainform;
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
        this.errorListener = errorListener;
    }

    public void init() {

        Helper.applyDocFilter(stLot, new StringDocumentFilter(15), stPalletNumber);
        Helper.applyDocFilter(stConeCount, new IntDocumentFilter(3), () -> {
            Helper.goAndSelect(stDisableEmptyBagWeight.isSelected() ? stTotalWeight : stEmptyBag);
        });
        Helper.applyDocFilter(stEmptyBag, new IntDocumentFilter(2, true), stTotalWeight);
        Helper.applyDocFilter(stPalletNumber, new IntDocumentFilter(3), () -> {
            Helper.goAndSelect(stDisableNumberOfCone.isSelected() ? (stDisableEmptyBagWeight.isSelected() ? stTotalWeight : stEmptyBag) : stConeCount);
            try {
                calc_pallet_weight();
            } catch (DatabaseException ex) {
                errorListener.onError(ex);
            }
        });
        Helper.applyDocFilter(stTotalWeight, new DoubleDocumentFilter(8, true), () -> {
            try {
                calc_net_weight();
                Helper.goAndSelect(stNetWeight);
            } catch (BusinessException e) {
                errorListener.onError(e);
                showMessageInlable(true);
            }
        });
        Helper.applyDocFilter(stNetWeight, new DoubleDocumentFilter(8), () -> {
            stAdd.doClick();
            stConeCount.requestFocusInWindow();
        });
        Helper.applyDocFilter(stConeWeight, new IntDocumentFilter(4), () -> {
            Helper.goAndSelect(stDisableNumberOfCone.isSelected() ? (stDisableEmptyBagWeight.isSelected() ? stTotalWeight : stEmptyBag) : stConeCount);
        });
        Helper.applyDocFilter(stFilterPros, new StringDocumentFilter(20), () -> {
            try {
                Helper.fillComboBoxWihProducts(stPros, productController.getAvailableProductsLike(stFilterPros.getText()));
            } catch (DatabaseException ex) {
                errorListener.onError(ex);
            }
        });

        stTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });

        Helper.bindKey(stTable, JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT, KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), stDelete::doClick);

        stReprint.addActionListener(e -> {
            try {
                if (printPanel == null) {
                    throw new BusinessException("print panel is null");
                }
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
            Helper.openPanel(leftPanel, stPanel);
            if (stPros.getItemCount() == 0) {
                try {
                    Helper.fillComboBoxWihProducts(stPros, productController.getAvailableProductsLike(stFilterPros.getText()));
                } catch (DatabaseException ex) {
                    errorListener.onError(ex);
                }
            }
            if (stPros.getSelectedIndex() == -1) {
                DefaultTableModel model = (DefaultTableModel) stTable.getModel();
                model.setRowCount(0);
            }
        });
        stDisableEmptyBagWeight.addActionListener(e -> {
            stEmptyBag.setEnabled(!stDisableEmptyBagWeight.isSelected());
        });
        stDisableNumberOfCone.addActionListener(e -> {
            stConeCount.setEnabled(!stDisableNumberOfCone.isSelected());
        });
        stDonotReadConeWeight.addActionListener(e -> {
            stConeWeight.setEnabled(!stDonotReadConeWeight.isSelected());
        });
        stPrintTicket.addActionListener(e -> {
            Config.set("isPrintTicket", stPrintTicket.isSelected() ? "True" : "False");
        });

        Helper.bindKey(stPanel, JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT,
                KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, InputEvent.SHIFT_DOWN_MASK), this::clearStorageForm);

        Helper.bindKey(stNetWeight, JComponent.WHEN_FOCUSED, KeyStroke.getKeyStroke(KeyEvent.VK_ADD, 0), () -> {
            if (!stTotalWeight.getText().isBlank()) {
                double weight = ArabicDigits.parseDouble(stTotalWeight.getText());
                stTotalWeight.setText(ArabicDigits.toArabicDigits(weight + 0.02));
                try {
                    calc_net_weight();
                } catch (BusinessException e) {
                    showMessageInlable(true);
                }
            }
        });
        Helper.bindKey(stNetWeight, JComponent.WHEN_FOCUSED, KeyStroke.getKeyStroke(KeyEvent.VK_SUBTRACT, 0), () -> {
            if (!stTotalWeight.getText().isBlank()) {
                double weight = ArabicDigits.parseDouble(stTotalWeight.getText());
                stTotalWeight.setText(ArabicDigits.toArabicDigits(weight - 0.02));
                try {
                    calc_net_weight();
                } catch (BusinessException e) {
                    showMessageInlable(true);
                }
            }
        });

        stPrintTicket.setSelected(Config.getBoolean("isPrintTicket", true));
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
            model.addRow(new Object[]{"", ArabicDigits.toArabicDigits(bag.getPallet_numb()), ArabicDigits.toArabicDigits(bag.getLot()),
                ArabicDigits.toArabicDigits(bag.getNum_of_con()), ArabicDigits.toArabicDigits(bag.getWeight()), bag.getId(),
                bag.isUsed()});
        }
        Product pro = productController.getProduct(stPros.getSelectedItem().toString());
        if (pro != null) {
            if (!stDonotReadConeWeight.isSelected()) {
                stConeWeight.setText(ArabicDigits.toArabicDigits(pro.getWeight_of_con()));
            }
            stConeColor.setText(pro.getColor());
            stIsBox.setSelected(pro.isBox());
            Helper.changeMaxLength(stEmptyBag, pro.isBox() ? 3 : 2);
            stEmptyBagLabel.setText(!pro.isBox() ? "فارغ الشيكاره" : "فارغ الصندوق");
            stEmptyBag.setBackground(pro.isBox() ? Color.pink : Color.WHITE);
        }

        if (model.getRowCount() != 0) {
            String lott = ArabicDigits.normalizeForParsing(model.getValueAt(model.getRowCount() - 1, 2).toString()),
                    pallet_num = ArabicDigits.normalizeForParsing(model.getValueAt(model.getRowCount() - 1, 1).toString());
            int cunt = 0;
            for (int i = model.getRowCount() - 1; i >= 0; i--) {
                if (!lott.equals(ArabicDigits.normalizeForParsing(model.getValueAt(i, 2).toString()))
                        || !pallet_num.equals(ArabicDigits.normalizeForParsing(model.getValueAt(i, 1).toString()))) {
                    lott = ArabicDigits.normalizeForParsing(model.getValueAt(i, 2).toString());
                    pallet_num = ArabicDigits.normalizeForParsing(model.getValueAt(i, 1).toString());
                    cunt = 0;
                }

                model.setValueAt(ArabicDigits.toArabicDigits(++cunt), i, 0);
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
                    Bag bag = storageController.getBagById((int) stTable.getModel().getValueAt(stTable.getSelectedRow(), 5));
                    new SingleEdit(bag, mainform, (Product) stPros.getSelectedItem(), productController, storageController, printPanel.formController, this, errorListener);

                } else if (stTable.getSelectedRowCount() > 1) {
                    List<Bag> bags = new ArrayList<>();
                    for (int i = stTable.getSelectedRows().length - 1; i > -1; i--) {
                        Bag bag = storageController.getBagById((int) stTable.getModel().getValueAt(stTable.getSelectedRows()[i], 5));
                        if (bag != null) {
                            bags.add(bag);
                        }
                    }
                    new MultiEdit(mainform, bags, (Product) stPros.getSelectedItem(),
                            stTable.getModel().getValueAt(stTable.getSelectedRow(), 1).toString(),
                            stTable.getModel().getValueAt(stTable.getSelectedRow(), 2).toString(),
                            (boolean) stTable.getModel().getValueAt(stTable.getSelectedRow(), 6), productController, storageController,
                            this, errorListener);
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
                    stPalletNumber.setText((String) stTable.getValueAt(0, 1));
                } else {
                    stPalletNumber.setText(ArabicDigits.toArabicDigits(ArabicDigits.parseInt((String) stTable.getValueAt(0, 1)) + 1));
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
        Helper.goAndSelect(stDisableNumberOfCone.isSelected() ? (stDisableEmptyBagWeight.isSelected() ? stTotalWeight : stEmptyBag) : stConeCount);
        if (!stDisableNumberOfCone.isSelected()) {
            stConeCount.selectAll();
        }
        showMessageInlable(false);
    }

    private void deleteStorage() {
        try {
            if (errorListener.onQuest("هل تريد الحذف ؟", "تنبيه") == JOptionPane.YES_OPTION) {
                if (stTable.getSelectedRowCount() > 0) {
                    for (int row : stTable.getSelectedRows()) {
                        storageController.removeBag(stTable.getModel().getValueAt(row, 5).toString());
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

            printPanel.formController.PreparePrintingPanel(new Ticket(((Product) stPros.getSelectedItem()).getName(),
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
            errorListener.onError(ex);
            showMessageInlable(true);
        }
    }

    public void setFromMachine(String ProName, String Lot) {
        try {
            stDonotReadConeWeight.setSelected(false);
            stIgnoreLimit.setSelected(false);
            stDisableNumberOfCone.setSelected(false);
            stDisableEmptyBagWeight.setSelected(false);
            stEmptyBag.setEnabled(true);
            stConeCount.setEnabled(true);
            stConeWeight.setEnabled(true);
            stMarkBag.setSelected(false);
            stFilterPros.setText("");
            Helper.fillComboBoxWihProducts(stPros, productController.getAvailableProductsLike(stFilterPros.getText()));
            stPros.setSelectedItem(productController.getProduct(ProName));
            fill_storage_table();
        } catch (BusinessException ex) {
            stPros.setSelectedIndex(-1);
        } catch (DatabaseException ex) {
            errorListener.onError(ex);
        }

        stLot.setText(Lot);
        formOpenerBtn.doClick();
        stPalletNumber.setText("");
    }
}

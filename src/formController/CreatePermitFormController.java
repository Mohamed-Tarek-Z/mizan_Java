package formController;

import controller.ClientController;
import controller.ExportController;
import controller.OrderController;
import controller.ProductController;
import controller.StorageController;
import exceptions.BusinessException;
import exceptions.DatabaseException;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Bag;
import model.Client;
import model.Product;
import utils.ArabicDigits;
import utils.Config;
import utils.ErrorListener;
import utils.ExcelManager;
import utils.TextFieldRules;
import utils.utils;

public class CreatePermitFormController {

    private final JTextField permitClientName;
    private final JTextField permitOrderWeight;
    private final JTextField permitProSearch;
    private final JTextField permitConeCount;
    private final JTextField permitTotalWeight;

    private final JComboBox<Product> permitPros;
    private final JComboBox<String> permitPallets;

    private final JCheckBox permit2in1;
    private final JCheckBox permitMark;
    private final JCheckBox permitByWeight;
    private final JLabel permitOrderCountLabel;

    private final JButton permitCreateBtn;
    private final JButton permitClearFormBtn;

    private final JTable permitPreviewTable;
    private final JTable permitPalletsTable;

    private final JButton formOpenerBtn;
    private final JPanel permitPanel;
    private final JPanel leftPanel;

    private final StorageController storController;
    private final ProductController proController;
    private final ClientController cliController;
    private final OrderController orderController;
    private final ExportController exportController;

    private final ExcelManager excelManager;

    private final ErrorListener errorListener;

    private final int orderDifference;

    private final List<Bag> orderBags = new ArrayList<>();
    private List<Bag> fOrderBags = new ArrayList<>();
    private Product typeFOrder;
    private boolean second = false, highLightFirst = false;

    public CreatePermitFormController(ErrorListener errorListener, JPanel leftPanel, JPanel permitPanel, JButton formOpenerBtn,
            JTextField permitClientName, JTextField permitOrderWeight, JTextField permitProSearch, JTextField permitConeCount,
            JTextField permitTotalWeight, JComboBox<Product> permitPros, JComboBox<String> permitPallets, JLabel permitOrderCountLabel,
            JCheckBox permit2in1, JCheckBox permitMark, JCheckBox permitByWeight, JButton permitCreateBtn, JButton permitClearFormBtn,
            JTable permitPerviewTable, JTable permitPalletsTable, StorageController storController, ProductController proController,
            ClientController cliController, OrderController orderController, ExportController exportController, ExcelManager excelManager) {
        this.permitClientName = permitClientName;
        this.permitOrderWeight = permitOrderWeight;
        this.permitProSearch = permitProSearch;
        this.permitConeCount = permitConeCount;
        this.permitTotalWeight = permitTotalWeight;
        this.permitPros = permitPros;
        this.permitPallets = permitPallets;
        this.permitOrderCountLabel = permitOrderCountLabel;
        this.permit2in1 = permit2in1;
        this.permitMark = permitMark;
        this.permitByWeight = permitByWeight;
        this.permitCreateBtn = permitCreateBtn;
        this.permitClearFormBtn = permitClearFormBtn;
        this.permitPreviewTable = permitPerviewTable;
        this.permitPalletsTable = permitPalletsTable;
        this.storController = storController;
        this.proController = proController;
        this.cliController = cliController;
        this.orderController = orderController;
        this.exportController = exportController;
        this.excelManager = excelManager;
        this.formOpenerBtn = formOpenerBtn;
        this.permitPanel = permitPanel;
        this.leftPanel = leftPanel;
        this.errorListener = errorListener;
        this.orderDifference = Config.getInt("orderDifference", 15);

    }

    public void init() {

        TextFieldRules.apply(permitOrderWeight, 3, true, () -> {
        }, null);

        TextFieldRules.apply(permitProSearch, 20, false, () -> {
            try {
                utils.fillComboBoxWihProducts(permitPros, proController.getAvailableStockProductsLike(permitProSearch.getText()));
            } catch (DatabaseException ex) {
                errorListener.onError(ex);
            }
        }, null);

        this.formOpenerBtn.addActionListener((ActionEvent evt) -> {
            openerClicked();
        });

        this.permitCreateBtn.addActionListener((ActionEvent evt) -> {
            createPermit();
        });

        this.permitClearFormBtn.addActionListener((ActionEvent evt) -> {
            reset();
        });

        permitOrderWeight.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                productSelected();
            }
        });

        this.permitPalletsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent evt) {
                palletTableClicked(evt);
            }
        });

        this.permitPros.addItemListener((ItemEvent evt) -> {
            if (permitPros.hasFocus()) {
                productSelected();
            }
        });

        permitByWeight.addActionListener((ActionEvent evt) -> {
            productSelected();
            permitOrderCountLabel.setText(!permitByWeight.isSelected() ? "عدد الشكاير" : "الوزن المطلوب");
            TextFieldRules.changeMaxLength(permitOrderWeight, permitByWeight.isSelected() ? 4 : 3);
        });
    }

    private void palletTableClicked(MouseEvent evt) {
        try {
            if (evt.getButton() != MouseEvent.BUTTON1) {
                return;
            }
            if (!permitOrderWeight.getText().isBlank()) {
                double weight_sum = 0.0;
                double currentTotalWeight = permitTotalWeight.getText().isEmpty() ? 0.0
                        : ArabicDigits.parseDouble(permitTotalWeight.getText());

                int coneCount = 0;
                int currentConeCount = permitConeCount.getText().isEmpty() ? 0
                        : ArabicDigits.parseInt(permitConeCount.getText());

                boolean isSameLot = true;
                int bagsTakenFromPallet = 0;
                if (permitByWeight.isSelected()) {
                    double wantedOrderWeight = ArabicDigits.parseDouble(permitOrderWeight.getText());
                    if (wantedOrderWeight > 7000.0) {
                        errorListener.onWarning("رجاء ادخل  وزن أقل من  ٧٠٠٠", "إنتبه");
                        return;
                    }
                    if (wantedOrderWeight >= currentTotalWeight + orderDifference) {
                        if (permitPreviewTable.getRowCount() > 0) {
                            if (!permitPreviewTable.getValueAt(0, 2).toString()
                                    .equals(permitPalletsTable.getValueAt(permitPalletsTable.getSelectedRow(), 2))) {
                                isSameLot = false;
                            }
                        }
                        if (isSameLot) {
                            if (errorListener.onQuest("هل تريد إضافه البالته رقم  " + permitPalletsTable.getValueAt(permitPalletsTable.getSelectedRow(), 3),
                                    "تنبيه") == JOptionPane.YES_OPTION) {

                                List<Bag> bags = storController.getBagsToReport(0, ((Product) permitPros.getSelectedItem()).getId(), permitPalletsTable.getModel()
                                        .getValueAt(permitPalletsTable.getSelectedRow(), 3).toString(), permitPalletsTable.getModel()
                                        .getValueAt(permitPalletsTable.getSelectedRow(), 2).toString());

                                boolean bagOutOfOrder = false;
                                ArrayList<String> OutOfOrderBags = new ArrayList<>();
                                for (Bag bag : bags) {
                                    if (wantedOrderWeight + orderDifference > currentTotalWeight + weight_sum + bag.getWeight()) {

                                        orderBags.add(bag);
                                        bagsTakenFromPallet++;
                                        weight_sum += bag.getWeight();
                                        coneCount += bag.getNum_of_con();

                                        ((DefaultTableModel) permitPreviewTable.getModel()).addRow(new Object[]{
                                            ArabicDigits.toArabicDigits(permitPreviewTable.getRowCount() + 1),
                                            ArabicDigits.toArabicDigits(bag.getWeight()), ArabicDigits.toArabicDigits(bag.getLot()),
                                            ArabicDigits.toArabicDigits(bag.getPallet_numb()), bag.isUsed()});

                                        if (((DefaultComboBoxModel<String>) permitPallets.getModel())
                                                .getIndexOf(ArabicDigits.toArabicDigits(bag.getPallet_numb())) == -1) {
                                            permitPallets.addItem(ArabicDigits.toArabicDigits(bag.getPallet_numb()));
                                        }
                                        if (bagOutOfOrder) {
                                            OutOfOrderBags.add(ArabicDigits.toArabicDigits(bag.getWeight()));
                                        }
                                    } else {
                                        if (!bagOutOfOrder) {
                                            if (errorListener.onQuest("هل تريد إضافه شكائر خارج الترتيب إن أمكن؟", "خارج الترتيب") == JOptionPane.NO_OPTION) {
                                                break;
                                            } else {
                                                bagOutOfOrder = true;
                                            }
                                        }
                                    }
                                }
                                if (bagOutOfOrder && !OutOfOrderBags.isEmpty()) {
                                    errorListener.onWarning("الشكائر هى: " + OutOfOrderBags.toString(), "ملحوظة");
                                }

                                currentTotalWeight += weight_sum;
                                permitTotalWeight.setText(ArabicDigits.toArabicDigits(currentTotalWeight));

                                currentConeCount += coneCount;
                                permitConeCount.setText(ArabicDigits.toArabicDigits(currentConeCount));

                                if (wantedOrderWeight >= ArabicDigits.parseDouble(permitPalletsTable.getModel()
                                        .getValueAt(permitPalletsTable.getSelectedRow(), 1).toString()) + currentTotalWeight
                                        || (Integer.parseInt(permitPalletsTable.getModel().getValueAt(permitPalletsTable.getSelectedRow(), 0).toString()) - bagsTakenFromPallet) <= 0
                                        || ArabicDigits.parseDouble(permitPalletsTable.getModel().getValueAt(permitPalletsTable.getSelectedRow(), 1).toString()) - weight_sum <= 0.0) {

                                    ((DefaultTableModel) permitPalletsTable.getModel()).removeRow(permitPalletsTable.getSelectedRow());

                                } else {
                                    permitPalletsTable.getModel().setValueAt(ArabicDigits.toArabicDigits(ArabicDigits.parseDouble(permitPalletsTable.getModel()
                                            .getValueAt(permitPalletsTable.getSelectedRow(), 1).toString()) - weight_sum),
                                            permitPalletsTable.getSelectedRow(), 1);
                                    permitPalletsTable.getModel().setValueAt(ArabicDigits.toArabicDigits((Integer.parseInt(permitPalletsTable.getModel()
                                            .getValueAt(permitPalletsTable.getSelectedRow(), 0).toString()) - bagsTakenFromPallet)), permitPalletsTable.getSelectedRow(), 0);

                                }
                                permitPreviewTable.changeSelection(permitPreviewTable.getRowCount() - 1, 0, false, false);
                            }
                        } else {
                            errorListener.onWarning("لا يمكن ادخال اكثر من لوط", "إنتبه");
                        }
                    } else {
                        errorListener.onWarning("لقد اكتمل الوزن", "إنتبه");
                    }
                } else {
                    if (ArabicDigits.parseInt(permitOrderWeight.getText()) > 200) {
                        errorListener.onWarning("رجاء ادخل  عدد أقل من  ٢٠١", "إنتبه");
                        return;
                    }
                    if (ArabicDigits.parseInt(permitOrderWeight.getText()) != permitPreviewTable.getRowCount()) {
                        isSameLot = true;
                        if (permitPreviewTable.getRowCount() > 0) {
                            if (!permitPreviewTable.getValueAt(0, 2).toString()
                                    .equals(permitPalletsTable.getValueAt(permitPalletsTable.getSelectedRow(), 2))) {
                                isSameLot = false;
                            }
                        }
                        if (isSameLot) {
                            if (errorListener.onQuest("هل تريد إضافه البالته رقم  " + permitPalletsTable.getValueAt(permitPalletsTable.getSelectedRow(), 3),
                                    "تنبيه") == JOptionPane.YES_OPTION) {
                                int wantedOrderQuantity = ArabicDigits.parseInt(permitOrderWeight.getText());
                                if (wantedOrderQuantity > 0 && permitPreviewTable.getRowCount() < wantedOrderQuantity) {
                                    List<Bag> bags = storController.getBagsToReport((wantedOrderQuantity - permitPreviewTable.getRowCount()),
                                            ((Product) permitPros.getSelectedItem()).getId(),
                                            permitPalletsTable.getModel().getValueAt(permitPalletsTable.getSelectedRow(), 3).toString(),
                                            permitPalletsTable.getModel().getValueAt(permitPalletsTable.getSelectedRow(), 2).toString());
                                    orderBags.addAll(bags);
                                    for (Bag bag : bags) {
                                        bagsTakenFromPallet++;
                                        weight_sum += bag.getWeight();
                                        coneCount += bag.getNum_of_con();
                                        ((DefaultTableModel) permitPreviewTable.getModel()).addRow(new Object[]{
                                            ArabicDigits.toArabicDigits(permitPreviewTable.getRowCount() + 1), ArabicDigits.toArabicDigits(bag.getWeight()),
                                            ArabicDigits.toArabicDigits(bag.getLot()), ArabicDigits.toArabicDigits(bag.getPallet_numb()), bag.isUsed()});

                                        if (((DefaultComboBoxModel<String>) permitPallets.getModel())
                                                .getIndexOf(ArabicDigits.toArabicDigits(bag.getPallet_numb())) == -1) {
                                            permitPallets.addItem(ArabicDigits.toArabicDigits(bag.getPallet_numb()));
                                        }

                                    }

                                    currentTotalWeight += weight_sum;
                                    permitTotalWeight.setText(ArabicDigits.toArabicDigits(currentTotalWeight));

                                    currentConeCount += coneCount;
                                    permitConeCount.setText(ArabicDigits.toArabicDigits("" + currentConeCount));

                                    permitPalletsTable.getModel().setValueAt(ArabicDigits.toArabicDigits(ArabicDigits.parseDouble(permitPalletsTable.getModel()
                                            .getValueAt(permitPalletsTable.getSelectedRow(), 1).toString()) - weight_sum),
                                            permitPalletsTable.getSelectedRow(), 1);
                                    permitPalletsTable.getModel().setValueAt(ArabicDigits.toArabicDigits((Integer.parseInt(permitPalletsTable.getModel()
                                            .getValueAt(permitPalletsTable.getSelectedRow(), 0).toString()) - bagsTakenFromPallet)), permitPalletsTable.getSelectedRow(), 0);

                                    if (wantedOrderQuantity >= ArabicDigits.parseDouble(permitPalletsTable.getModel().getValueAt(permitPalletsTable.getSelectedRow(), 0).toString())
                                            && permitPreviewTable.getRowCount() < wantedOrderQuantity
                                            || ArabicDigits.parseDouble(permitPalletsTable.getValueAt(permitPalletsTable.getSelectedRow(), 0).toString()) == 0.0
                                            || ArabicDigits.parseDouble(permitPalletsTable.getValueAt(permitPalletsTable.getSelectedRow(), 1).toString()) == 0.0) {

                                        ((DefaultTableModel) permitPalletsTable.getModel()).removeRow(permitPalletsTable.getSelectedRow());
                                    }
                                }
                                permitPreviewTable.changeSelection(permitPreviewTable.getRowCount() - 1, 0, false, false);
                            }
                        } else {
                            errorListener.onWarning("لا يمكن ادخال اكثر من لوط  ", "إنتبه");
                        }
                    } else {
                        errorListener.onWarning(" لقد اكتمل العدد ", "إنتبه");
                    }
                }
            } else {
                errorListener.onWarning("برجاء ادخال عدد الشكاير", "إنتبه");
            }
        } catch (DatabaseException ex) {
            errorListener.onError(ex);
        }
    }

    private void productSelected() { // fill pallets table
        try {
            if (permitPros.getSelectedIndex() != -1) {
                ((DefaultTableModel) permitPalletsTable.getModel()).setRowCount(0);
                List<String[]> pallets = storController.getPalletsForReport(((Product) permitPros.getSelectedItem()).getId());

                for (String[] pallet : pallets) {
                    ((DefaultTableModel) permitPalletsTable.getModel())
                            .addRow(new Object[]{ArabicDigits.toArabicDigits(pallet[0]), ArabicDigits.toArabicDigits(pallet[1]),
                        ArabicDigits.toArabicDigits(pallet[2]), ArabicDigits.toArabicDigits(pallet[3]),
                        Boolean.valueOf(pallet[4])});
                }
                ((DefaultTableModel) permitPreviewTable.getModel()).setRowCount(0);
                orderBags.clear();
                permitTotalWeight.setText("");
                permitConeCount.setText("");
                permitPallets.removeAllItems();
            }
        } catch (DatabaseException ex) {
            errorListener.onError(ex);
        }
    }

    private void createPermit() {
        JFileChooser fileChooser = new JFileChooser("P:\\");
        fileChooser.setFileSelectionMode(javax.swing.JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser.setDialogType(JFileChooser.CUSTOM_DIALOG);
        fileChooser.setFont(new java.awt.Font("Tahoma", 0, 14));

        double ss = permitTotalWeight.getText().isEmpty() ? 0.0
                : ArabicDigits.parseDouble(permitTotalWeight.getText());
        try {
            if (permitPreviewTable.getRowCount() >= 0 && !permitClientName.getText().isBlank()
                    && (permitPreviewTable.getRowCount() == ArabicDigits.parseInt(permitOrderWeight.getText())
                    || !(ArabicDigits.parseDouble(permitOrderWeight.getText()) >= ss + orderDifference))) {

                if (errorListener.onQuest("سيتم التصدير للأكسل ", "تنبيه") == JOptionPane.YES_OPTION) {

                    Client client = ClientNaming(permitClientName.getText().split("تسليم")[0].strip());
                    boolean useNameFeild = permitClientName.getText().split("تسليم")[0].strip().equalsIgnoreCase(client.getName());

                    if (permitPreviewTable.getRowCount() > 60 && permit2in1.isSelected()) {
                        errorListener.onWarning("لا يمن عمل إذنين و عدد الشكائر أكثر من ٦٠ في الإذن الواحد", "إنتبه");
                        return;
                    }
                    if (permitPreviewTable.getRowCount() <= 60 && permit2in1.isSelected()) {
                        if (!second) {

                            typeFOrder = (Product) permitPros.getSelectedItem();
                            highLightFirst = permitMark.isSelected();
                            fOrderBags = new ArrayList<>(orderBags);
                            ((DefaultTableModel) permitPreviewTable.getModel()).setRowCount(0);
                            permitOrderWeight.setText("");
                            permitTotalWeight.setText("");
                            permitConeCount.setText("");
                            permitByWeight.setSelected(false);
                            permitMark.setSelected(false);
                            permitPallets.removeAllItems();

                            errorListener.onWarning(" ادخل الأذن الثاني  ", "إنتبه");
                        } else {
                            if (typeFOrder == (Product) permitPros.getSelectedItem() && fOrderBags.getFirst().getLot().equals(orderBags.getFirst().getLot())) {
                                errorListener.onWarning(" برجاء تغير الصنف أو اللوط  ", "إنتبه");
                                second = !second;
                            } else {
                                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                                fileChooser.showOpenDialog(null);
                                String excelBackupPath = fileChooser.getSelectedFile().getAbsolutePath();
                                if (excelManager.excel_60_60(fOrderBags, orderBags,
                                        useNameFeild ? permitClientName.getText() : client.getName(),
                                        typeFOrder, (Product) permitPros.getSelectedItem(),
                                        excelBackupPath, highLightFirst, permitMark.isSelected())) {
                                    accessDataBase(client, orderBags);
                                    accessDataBase(client, fOrderBags);

                                    ((DefaultTableModel) permitPreviewTable.getModel()).setRowCount(0);
                                    fOrderBags.clear();

                                    permit2in1.setSelected(false);
                                    permitByWeight.setSelected(false);
                                    permitMark.setSelected(false);
                                    permitClientName.setText("");
                                    permitOrderWeight.setText("");
                                    permitTotalWeight.setText("");
                                    permitConeCount.setText("");
                                } else {
                                    errorListener.onWarning(" حدث خطأ في عمل الاذن", "إنتبه");
                                }
                            }
                        }
                        second = !second;
                    } else if (permitPreviewTable.getRowCount() <= 120) {
                        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                        fileChooser.showOpenDialog(null);
                        String excelBackupPath = fileChooser.getSelectedFile().getAbsolutePath();
                        if (excelManager.excel_120(orderBags,
                                useNameFeild ? permitClientName.getText() : client.getName(),
                                (Product) permitPros.getSelectedItem(),
                                excelBackupPath, permitMark.isSelected())) {
                            accessDataBase(client, orderBags);
                            ((DefaultTableModel) permitPreviewTable.getModel()).setRowCount(0);

                            permitClientName.setText("");
                            permitOrderWeight.setText("");
                            permitTotalWeight.setText("");
                            permitConeCount.setText("");
                            permitByWeight.setSelected(false);
                            permitMark.setSelected(false);
                        } else {
                            errorListener.onWarning(" حدث خطأ في عمل الاذن", "إنتبه");
                        }

                    } else if (permitPreviewTable.getRowCount() <= 160) {
                        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                        fileChooser.showOpenDialog(null);
                        String excelBackupPath = fileChooser.getSelectedFile().getAbsolutePath();
                        if (excelManager.excel_160(orderBags,
                                useNameFeild ? permitClientName.getText() : client.getName(),
                                (Product) permitPros.getSelectedItem(),
                                excelBackupPath, permitMark.isSelected())) {
                            accessDataBase(client, orderBags);
                            ((DefaultTableModel) permitPreviewTable.getModel()).setRowCount(0);
                            permitClientName.setText("");
                            permitOrderWeight.setText("");
                            permitTotalWeight.setText("");
                            permitConeCount.setText("");
                            permitByWeight.setSelected(false);
                            permitMark.setSelected(false);
                        } else {
                            errorListener.onWarning(" حدث خطأ في عمل الاذن", "إنتبه");
                        }
                    } else if (permitPreviewTable.getRowCount() <= 200) {
                        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                        fileChooser.showOpenDialog(null);
                        String excelBackupPath = fileChooser.getSelectedFile().getAbsolutePath();
                        if (excelManager.excel_200(orderBags,
                                useNameFeild ? permitClientName.getText() : client.getName(),
                                (Product) permitPros.getSelectedItem(),
                                excelBackupPath, permitMark.isSelected())) {
                            accessDataBase(client, orderBags);
                            ((DefaultTableModel) permitPreviewTable.getModel()).setRowCount(0);
                            permitClientName.setText("");
                            permitOrderWeight.setText("");
                            permitTotalWeight.setText("");
                            permitConeCount.setText("");
                            permitByWeight.setSelected(false);
                            permitMark.setSelected(false);
                        } else {
                            errorListener.onWarning(" حدث خطأ في عمل الاذن", "إنتبه");
                        }
                    }
                    permitPallets.removeAllItems();
                }
            } else {
                errorListener.onWarning(" تدخل البيانات كامله أولا", "إنتبه");
            }
        } catch (DatabaseException | BusinessException ex) {
            errorListener.onError(ex);
        }

    }

    private void reset() {
        ((DefaultTableModel) permitPalletsTable.getModel()).setRowCount(0);
        ((DefaultTableModel) permitPreviewTable.getModel()).setRowCount(0);
        permitPros.setSelectedIndex(-1);
        second = false;
        permitTotalWeight.setText("");
        permitConeCount.setText("");
        permitPallets.removeAllItems();
    }

    private void openerClicked() {
        utils.openPanel(leftPanel, permitPanel);
        if (permitPros.getItemCount() == 0) {
            try {
                utils.fillComboBoxWihProducts(permitPros, proController.getAvailableStockProductsLike(permitProSearch.getText()));
            } catch (DatabaseException ex) {
                errorListener.onError(ex);
            }
        }
    }

    private Client ClientNaming(String name) throws DatabaseException, BusinessException {
        try {
            if (cliController.clientExists(name)) {
                return cliController.getClient(name);
            }
            if (errorListener.onQuest("سيتم إضافة عميل جديد ", "تنبيه") == JOptionPane.NO_OPTION) {

                Client c = (Client) errorListener.onQuest(cliController.getClients().toArray(), "إختر عميل", "إختر عميل");
                if (c != null) {
                    return c;
                }
                throw new BusinessException("تم إلغاء الإذن رجاء إدخال/إختيار عميل");
            } else {
                return cliController.addClientByName(name);
            }
        } catch (DatabaseException | BusinessException ex) {
            errorListener.onError(ex);
        }
        return null;
    }

    private void accessDataBase(Client client, List<Bag> FromStorageToExport) throws DatabaseException {
        try {
            if (client == null) {
                throw new BusinessException("تم إلغاء الإذن رجاء إدخال/إختيار عميل");
            }
            String ordId = orderController.addOrder();
            double totalWeight = 0.0;
            for (Bag bag : FromStorageToExport) {
                totalWeight += bag.getWeight();
                exportController.moveBagFromStorageToExport(bag.getId(), client.getId(), ordId);
                storController.removeBag(bag.getId() + "");
            }
            orderController.updateOrder(ordId, totalWeight);
        } catch (DatabaseException | BusinessException ex) {
            errorListener.onError(ex);
        }
    }

}

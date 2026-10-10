package formController;

import controller.StorageController;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import model.Product;
import utils.ErrorListener;
import utils.TextFieldRules;
import view.Mainform;
import view.MultiEdit;

public class MultiEditFormController {

    private final JComboBox<Product> mePros;
    private final JTextField mePalletNumber;
    private final JTextField meLot;
    private final JCheckBox meMark;
    private final JButton meEdit;

    private final MultiEdit me;

    private final Product oProduct;
    private final StorageFormController storageFormController;
    private final StorageController storageController;
    private final ErrorListener errorListener;

    private final String OLot, OPallet;
    private final boolean Omark;

    public MultiEditFormController(JComboBox<Product> mePros, JTextField mePalletNumber, JTextField meLot, JCheckBox meMark, JButton meEdit, MultiEdit me, Product oProduct, StorageFormController storageFormController, StorageController storageController, ErrorListener errorListener, String OLot, String OPallet, boolean Omark) {
        this.mePros = mePros;
        this.mePalletNumber = mePalletNumber;
        this.meLot = meLot;
        this.meMark = meMark;
        this.meEdit = meEdit;
        this.me = me;
        this.oProduct = oProduct;
        this.storageFormController = storageFormController;
        this.storageController = storageController;
        this.errorListener = errorListener;
        this.OLot = OLot;
        this.OPallet = OPallet;
        this.Omark = Omark;
    }

    public void init() {

        mePros.setSelectedItem(oProduct);
        mePalletNumber.setText(OPallet);
        meLot.setText(OLot);
        meMark.setSelected(Omark);

        TextFieldRules.apply(meLot, 15, false, () -> {
        }, null);
        TextFieldRules.apply(mePalletNumber, 3, true, () -> {
        }, null);

        meEdit.addActionListener((ActionEvent evt) -> {
            editPressed();
        });
        me.setVisible(true);
    }

    private void editPressed() {
        try {
            if (!mePalletNumber.getText().isBlank() || !meLot.getText().isBlank()) {
                for (int i = jTable_storage.getSelectedRows().length - 1; i > -1; i--) {
                    Bag bag = storageController.getBagById(Integer.parseInt(jTable_storage.getModel().getValueAt(jTable_storage.getSelectedRows()[i], 4).toString()));
                    storageController.updateStorage(bag.getId(),
                            jComboBox_ME_type.getSelectedItem().toString(), bag.getTot_wight() + "", bag.getWeight() + "",
                            jTextField_ME_lot.getText(), bag.getNum_of_con() + "", jTextField_ME_PaltNum.getText(), jCheckBox_ME_MarkBag.isSelected(), "0.000");
                }
                MultiEdit.dispose();
                JOptionPane.showMessageDialog(MultiEdit, utils.addStyle(" تم تعديل البيانات بنجاح  "), "إنتبه",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(MultiEdit, utils.addStyle("رجاء أدخل بيانات كاملة"), "إنتبه",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            this.setEnabled(true);
            fill_storage_table();
            MultiEdit.dispose();
        } catch (DatabaseException ex) {
            Logger.getLogger(Mainform.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(MultiEdit, utils.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
        } catch (BusinessException ex) {
            JOptionPane.showMessageDialog(MultiEdit, utils.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}

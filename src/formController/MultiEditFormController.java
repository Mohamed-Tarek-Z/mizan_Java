package formController;

import controller.StorageController;
import exceptions.BusinessException;
import exceptions.DatabaseException;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import model.Bag;
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

    private final List<Bag> bags;

    public MultiEditFormController(List<Bag> bags, JComboBox<Product> mePros, JTextField mePalletNumber, JTextField meLot, JCheckBox meMark, JButton meEdit, MultiEdit me, Product oProduct, StorageFormController storageFormController, StorageController storageController, ErrorListener errorListener, String OLot, String OPallet, boolean Omark) {
        this.bags = bags;
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
            if (!mePalletNumber.getText().isBlank() || !meLot.getText().isBlank() || !bags.isEmpty()) {
                for (Bag bag : bags) {
                    storageController.updateStorage(bag.getId(),
                            ((Product) mePros.getSelectedItem()).getId(), bag.getTot_wight() + "", bag.getWeight() + "",
                            meLot.getText(), bag.getNum_of_con() + "", mePalletNumber.getText(),
                            meMark.isSelected(), bag.getEmpty_pack() + "");
                }
                me.dispose();
                errorListener.onWarning(" تم تعديل البيانات بنجاح  ", "إنتبه");
            } else {
                errorListener.onWarning("رجاء أدخل بيانات كاملة", "إنتبه");
            }
            ((Mainform) errorListener).setEnabled(true);
            storageFormController.fill_storage_table();
            me.dispose();
        } catch (DatabaseException | BusinessException ex) {
            errorListener.onError(ex);
        }
    }
}

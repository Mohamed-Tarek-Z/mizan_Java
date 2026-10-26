package formController;

import controller.ProductController;
import controller.StorageController;
import docFilters.IntDocumentFilter;
import docFilters.StringDocumentFilter;
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
import exceptions.ErrorListener;
import utils.Helper;
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
    private final ProductController productController;
    private final ErrorListener errorListener;

    private final String OLot, OPallet;
    private final boolean Omark;

    private final List<Bag> bags;
    private final Mainform mainform;

    public MultiEditFormController(Mainform mainform, List<Bag> bags, JComboBox<Product> mePros, JTextField mePalletNumber, JTextField meLot, JCheckBox meMark, JButton meEdit, MultiEdit me, Product oProduct, StorageFormController storageFormController, ProductController productController, StorageController storageController, ErrorListener errorListener, String OLot, String OPallet, boolean Omark) {
        this.mainform = mainform;
        this.bags = bags;
        this.mePros = mePros;
        this.mePalletNumber = mePalletNumber;
        this.meLot = meLot;
        this.meMark = meMark;
        this.meEdit = meEdit;
        this.me = me;
        this.oProduct = oProduct;
        this.storageFormController = storageFormController;
        this.productController = productController;
        this.storageController = storageController;
        this.errorListener = errorListener;
        this.OLot = OLot;
        this.OPallet = OPallet;
        this.Omark = Omark;
    }

    public void init() {

        try {
            Helper.fillComboBoxWihProducts(mePros, productController.getAvailableProductsLike(""));
            mePros.setSelectedItem(oProduct);
            mePalletNumber.setText(OPallet);
            meLot.setText(OLot);
            meMark.setSelected(Omark);

            Helper.applyDocFilter(meLot, new StringDocumentFilter(15));
            Helper.applyDocFilter(mePalletNumber, new IntDocumentFilter(3));

            meEdit.addActionListener((ActionEvent evt) -> {
                editPressed();
            });
            me.setVisible(true);
        } catch (DatabaseException ex) {
            errorListener.onError(ex);
        }
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
            mainform.setEnabled(true);
            storageFormController.fill_storage_table();
            me.dispose();
        } catch (DatabaseException | BusinessException ex) {
            errorListener.onError(ex);
        }
    }
}

package formController;

import controller.ProductController;
import controller.StorageController;
import exceptions.BusinessException;
import exceptions.DatabaseException;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import model.Bag;
import model.Product;
import model.Ticket;
import utils.ArabicDigits;
import utils.ErrorListener;
import utils.TextFieldRules;
import utils.utils;
import view.Mainform;
import view.SingleEdit;

public class SingleEditFormController {

    private final Bag bag;
    private final Product product;

    private final JButton seEdit;
    private final JButton sePrint;
    private final JCheckBox seMark;
    private final JCheckBox seOMark;
    private final JCheckBox seDoPrint;
    private final PrintFormController settingsFormController;
    private final StorageFormController storageFormController;
    private final JComboBox<Product> seOPros;
    private final JComboBox<Product> sePros;
    private final JTextField seColor;
    private final JTextField seConNum;
    private final JTextField seOConNum;
    private final JTextField seOPaltNum;
    private final JTextField seOTotWight;
    private final JTextField seOWight;
    private final JTextField seOLot;
    private final JTextField sePaltNum;
    private final JTextField seTotWight;
    private final JTextField seWight;
    private final JTextField seLot;

    private final StorageController storageController;
    private final ProductController productController;
    private final SingleEdit se;
    private final ErrorListener errorListener;
    private final Mainform mainform;

    public SingleEditFormController(SingleEdit se, Mainform mainform, Bag bag, Product product, JButton seEdit, JButton sePrint, JCheckBox seMark, JCheckBox seOMark,
            JCheckBox seDoPrint, JComboBox<Product> seOPros, JComboBox<Product> sePros, JTextField seColor, JTextField seConNum,
            JTextField seOConNum, JTextField seOPaltNum, JTextField seOTotWight, JTextField seOWight, JTextField seOlot, JTextField sePaltNum,
            JTextField seTotWight, JTextField seWight, JTextField seLot, StorageController storageController, ProductController productController,
            PrintFormController settingsFormController, StorageFormController storageFormController, ErrorListener errorListener) {
        this.mainform = mainform;
        this.se = se;
        this.bag = bag;
        this.product = product;
        this.seEdit = seEdit;
        this.sePrint = sePrint;
        this.seMark = seMark;
        this.seOMark = seOMark;
        this.seDoPrint = seDoPrint;
        this.seOPros = seOPros;
        this.sePros = sePros;
        this.seColor = seColor;
        this.seConNum = seConNum;
        this.seOConNum = seOConNum;
        this.seOPaltNum = seOPaltNum;
        this.seOTotWight = seOTotWight;
        this.seOWight = seOWight;
        this.seOLot = seOlot;
        this.sePaltNum = sePaltNum;
        this.seTotWight = seTotWight;
        this.seWight = seWight;
        this.seLot = seLot;
        this.storageController = storageController;
        this.productController = productController;
        this.storageFormController = storageFormController;
        this.settingsFormController = settingsFormController;
        this.errorListener = errorListener;
    }

    public void init() {
        try {
            se.setVisible(true);
            se.setSize(780, 400);
            seOMark.setSelected(bag.isUsed());
            seMark.setSelected(bag.isUsed());
            seOTotWight.setText(ArabicDigits.toArabicDigits(bag.getTot_wight()));
            seTotWight.setText(ArabicDigits.toArabicDigits(bag.getTot_wight()));
            utils.fillComboBoxWihProducts(seOPros, productController.getAvailableProductsLike(""));
            utils.fillComboBoxWihProducts(sePros, productController.getAvailableProductsLike(""));
            seOPros.setSelectedItem(product);
            sePros.setSelectedItem(product);
            seOLot.setText(ArabicDigits.toArabicDigits(bag.getLot()));
            seLot.setText(ArabicDigits.toArabicDigits(bag.getLot()));
            seOConNum.setText(ArabicDigits.toArabicDigits(bag.getNum_of_con()));
            seConNum.setText(ArabicDigits.toArabicDigits(bag.getNum_of_con()));
            seOPaltNum.setText(ArabicDigits.toArabicDigits(bag.getPallet_numb()));
            sePaltNum.setText(ArabicDigits.toArabicDigits(bag.getPallet_numb()));
            seOWight.setText(ArabicDigits.toArabicDigits(bag.getWeight()));
            seWight.setText(ArabicDigits.toArabicDigits(bag.getWeight()));
            seColor.setText(product.getColor());

            TextFieldRules.apply(seWight, 8, true, () -> {
            }, null);
            TextFieldRules.apply(sePaltNum, 3, true, () -> {
            }, null);
            TextFieldRules.apply(seConNum, 3, true, () -> {
            }, null);
            TextFieldRules.apply(seLot, 15, false, () -> {
            }, null);
            TextFieldRules.apply(seTotWight, 8, true, () -> {
            }, null);

            seEdit.addActionListener((ActionEvent evt) -> {
                editPressed();
            });
            sePrint.addActionListener((ActionEvent evt) -> {
                printPressed();
            });
        } catch (DatabaseException ex) {
            errorListener.onError(ex);
        }
    }

    private void editPressed() {
        try {
            if (!seWight.getText().isBlank() && !sePaltNum.getText().isBlank()
                    && !seConNum.getText().isBlank() && !seLot.getText().isBlank()) {

                storageController.updateStorage(bag.getId(),
                        ((Product) sePros.getSelectedItem()).getId(), seTotWight.getText(),
                        seWight.getText(), seLot.getText(), seConNum.getText(),
                        sePaltNum.getText(), seMark.isSelected(), "0.000");
                mainform.setEnabled(true);
                storageFormController.fill_storage_table();
                se.dispose();
                errorListener.onWarning(" تم تعديل البيانات بنجاح  ", "إنتبه");
            } else {
                errorListener.onWarning("برجاء ادخال البيانات صحيحه ", "إنتبه");
            }
        } catch (DatabaseException | BusinessException ex) {
            errorListener.onError(ex);
        }
    }

    private void printPressed() {
        settingsFormController.PreparePrintingPanel(new Ticket(((Product) sePros.getSelectedItem()).getName(),
                sePaltNum.getText(),
                ((Product) sePros.getSelectedItem()).getColor(),
                seLot.getText(),
                seConNum.getText(),
                seTotWight.getText(),
                seWight.getText()), seDoPrint.isSelected());
    }
}

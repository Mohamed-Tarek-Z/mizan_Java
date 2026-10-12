package formController;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Ticket;
import utils.PrinterManager;

public class PrintFormController {

    private final JLabel printPalletValue;
    private final JLabel printConeCountValue;
    private final JLabel printConeColorValue;
    private final JLabel printTypeDenierValue;
    private final JLabel printTypeNameValue;
    private final JLabel printLotValue;
    private final JLabel printTotalWeightValue;
    private final JLabel printNetWeightValue;

    private final JPanel printPanel;

    private final PrinterManager printerManager;

    public PrintFormController(JLabel printPalletValue, JLabel printConeCountValue, JLabel printConeColorValue, JLabel printTypeDenierValue, JLabel printTypeNameValue, JLabel printLotValue, JLabel printTotalWeightValue, JLabel printNetWeightValue, JPanel printPanel, PrinterManager printerManager) {
        this.printPalletValue = printPalletValue;
        this.printConeCountValue = printConeCountValue;
        this.printConeColorValue = printConeColorValue;
        this.printTypeDenierValue = printTypeDenierValue;
        this.printTypeNameValue = printTypeNameValue;
        this.printLotValue = printLotValue;
        this.printTotalWeightValue = printTotalWeightValue;
        this.printNetWeightValue = printNetWeightValue;
        this.printPanel = printPanel;
        this.printerManager = printerManager;
    }

    public void centerPrint() {
        printPalletValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        printLotValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        printNetWeightValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    }

    public void changePostionPrint() {
        changeLablePostion(printPalletValue);
        changeLablePostion(printLotValue);
        changeLablePostion(printNetWeightValue);
    }

    private void changeLablePostion(JLabel label) {
        switch (label.getHorizontalAlignment()) {
            case javax.swing.SwingConstants.CENTER ->
                label.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
            case javax.swing.SwingConstants.LEADING ->
                label.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
            case javax.swing.SwingConstants.TRAILING ->
                label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            default ->
                label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        }
    }

    public void PreparePrintingPanel(Ticket t, boolean doPrint) {
        try {
            printTypeNameValue.setFont(new Font("Arial", Font.PLAIN, 30));
            printTypeDenierValue.setFont(new Font("Arial", Font.PLAIN, 30));
            printTypeDenierValue.setSize(190, 28);
            printTypeDenierValue.setText(t.getProductName().split(" ", 2)[0]);
            printTypeNameValue.setText(t.getProductName().split(" ", 2)[1]);

        } catch (java.lang.ArrayIndexOutOfBoundsException ex) {
            printTypeDenierValue.setText(t.getProductName());
            printTypeDenierValue.setSize(190, 60);
            printTypeNameValue.setText(" ");
        }

        printPalletValue.setText(t.getPalletNumber());
        printConeColorValue.setText(t.getColor());
        printLotValue.setText(t.getLot());
        printConeCountValue.setText(t.getConeNumber());
        printTotalWeightValue.setText(t.getTotalWeight());
        printNetWeightValue.setText(t.getNetWeight());

        if (doPrint) {
            printerManager.printPanelToImage(printPanel);
        }
    }

}

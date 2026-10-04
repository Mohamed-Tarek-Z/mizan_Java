package formController;

import exceptions.BusinessException;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Ticket;
import utils.PrinterManager;
import utils.Config;
import utils.ErrorListener;

public class SettingsFormController {

    private final JPanel printPanel;

    private final JButton settingsCenterPrintBtn;
    private final JButton settingsChangePrintpostionBtn;
    private final JButton settingsSelectPrinterBtn;
    private final JButton settingsTicketCounterResetBtn;
    private final JButton settingsReloadSettingsBtn;

    private final JLabel printPalletValue;
    private final JLabel printConeCountValue;
    private final JLabel printConeColorValue;
    private final JLabel printTypeDenierValue;
    private final JLabel printTypeNameValue;
    private final JLabel printLotValue;
    private final JLabel printTotalWeightValue;
    private final JLabel printNetWeightValue;

    private final ErrorListener errorListener;

    private final PrinterManager printerManager;

    public SettingsFormController(ErrorListener errorListener, JPanel printPanel, PrinterManager printerManager, JButton settingsCenterPrintBtn, JButton settingsChangePrintpostionBtn, JButton settingsSelectPrinterBtn, JButton settingsTicketCounterResetBtn, JButton settingsReloadSettingsBtn, JLabel printPalletValue, JLabel printConeCountValue, JLabel printConeColorValue, JLabel printTypeDenierValue, JLabel printTypeNameValue, JLabel printLotValue, JLabel printTotalWeightValue, JLabel printNetWeightValue) {
        this.settingsCenterPrintBtn = settingsCenterPrintBtn;
        this.settingsChangePrintpostionBtn = settingsChangePrintpostionBtn;
        this.settingsSelectPrinterBtn = settingsSelectPrinterBtn;
        this.settingsTicketCounterResetBtn = settingsTicketCounterResetBtn;
        this.settingsReloadSettingsBtn = settingsReloadSettingsBtn;
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
        this.errorListener = errorListener;
    }

    public void init() {
        settingsCenterPrintBtn.addActionListener((ActionEvent evt) -> {
            centerPrint();
        });
        settingsChangePrintpostionBtn.addActionListener((ActionEvent evt) -> {
            changePostionPrint();
        });
        settingsSelectPrinterBtn.addActionListener((ActionEvent evt) -> {
            changePrinter();
        });
        settingsTicketCounterResetBtn.addActionListener((ActionEvent evt) -> {
            resetTicketCounter();
        });
        settingsReloadSettingsBtn.addActionListener((ActionEvent evt) -> {
            reloadSettingsFile();
        });
    }

    private void centerPrint() {
        printPalletValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        printLotValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        printNetWeightValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    }

    private void changePostionPrint() {
        changeLablePostion(printPalletValue);
        changeLablePostion(printLotValue);
        changeLablePostion(printNetWeightValue);
    }

    private void changePrinter() {
        try {
            PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
            String ticketPrinterName = ((PrintService) javax.swing.JOptionPane.showInputDialog(
                    null, "Select Printer For Tickets:", "Printer Selection",
                    javax.swing.JOptionPane.QUESTION_MESSAGE, null,
                    printServices, printServices[0])).getName();
            Config.set("ticketPrinterName", ticketPrinterName);
        } catch (BusinessException ex) {
            errorListener.onError(ex);
        }
    }

    private void resetTicketCounter() {
        try {
            Config.set("ticket10x10", "0");
            saveConfig();
            jLabel_Ticket10x10Counter.setText("" + tick10x10);
        } catch (BusinessException ex) {
            errorListener.onError(ex);
        }
    }

    private void reloadSettingsFile() {
        try {
            Config.load();
        } catch (BusinessException ex) {
            errorListener.onError(ex);
        }
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

    private void PreparePrintingPanel(Ticket t, boolean doPrint) {

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
            Config.set("ticket10x10", Config.getInt("ticket10x10", 0) + 1 + "");
        }

    }

}

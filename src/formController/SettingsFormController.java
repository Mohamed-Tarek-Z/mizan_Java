package formController;

import controller.MachineController;
import controller.ProductController;
import exceptions.BusinessException;
import exceptions.DatabaseException;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import model.Product;
import model.Ticket;
import utils.PrinterManager;
import utils.Config;
import utils.ErrorListener;
import utils.utils;

public class SettingsFormController {

    private final JPanel printPanel;

    private final JButton settingsCenterPrintBtn;
    private final JButton settingsChangePrintpostionBtn;
    private final JButton settingsSelectPrinterBtn;
    private final JButton settingsTicketCounterResetBtn;
    private final JButton settingsReloadSettingsBtn;

    private final JLabel settingsTicketCounter;
    private final JLabel settingsIP;

    private final JLabel printPalletValue;
    private final JLabel printConeCountValue;
    private final JLabel printConeColorValue;
    private final JLabel printTypeDenierValue;
    private final JLabel printTypeNameValue;
    private final JLabel printLotValue;
    private final JLabel printTotalWeightValue;
    private final JLabel printNetWeightValue;

    private final JButton formOpenerBtn;
    private final JPanel settingsPanel;
    private final JPanel leftPanel;

    private final ErrorListener errorListener;

    private final PrinterManager printerManager;

    public SettingsFormController(ErrorListener errorListener, JPanel leftPanel, JPanel settingsPanel, JButton formOpenerBtn, JTabbedPane settingsTapps, JTextField machName, JTextField machLot,
            JComboBox<Product> machPros, JButton addMachBtn, JButton editMachBtn, JButton delMachBtn,
            JTable machTable,
            JPanel printPanel, JButton settingsCenterPrintBtn, JButton settingsChangePrintpostionBtn,
            JButton settingsSelectPrinterBtn, JButton settingsTicketCounterResetBtn, JButton settingsReloadSettingsBtn,
            JLabel settingsTicketCounter, JLabel settingsIP, JLabel printPalletValue, JLabel printConeCountValue, JLabel printConeColorValue,
            JLabel printTypeDenierValue, JLabel printTypeNameValue, JLabel printLotValue, JLabel printTotalWeightValue, JLabel printNetWeightValue,
            MachineController machController, ProductController prosController, PrinterManager printerManager
    ) {
        this.settingsCenterPrintBtn = settingsCenterPrintBtn;
        this.settingsChangePrintpostionBtn = settingsChangePrintpostionBtn;
        this.settingsSelectPrinterBtn = settingsSelectPrinterBtn;
        this.settingsTicketCounterResetBtn = settingsTicketCounterResetBtn;
        this.settingsReloadSettingsBtn = settingsReloadSettingsBtn;
        this.settingsTicketCounter = settingsTicketCounter;
        this.settingsIP = settingsIP;
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

        this.formOpenerBtn = formOpenerBtn;
        this.settingsPanel = settingsPanel;
        this.leftPanel = leftPanel;
        this.errorListener = errorListener;

        try {
            new MachineFormController(errorListener, settingsTapps, machName, machLot, machPros,
                    addMachBtn, editMachBtn, delMachBtn, machTable, machController,
                    prosController).init();
        } catch (DatabaseException ex) {
            errorListener.onError(ex);
        }
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

        this.formOpenerBtn.addActionListener((ActionEvent evt) -> {
            openerClicked();
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
        printerManager.changePrinter();
    }

    private void resetTicketCounter() {
        try {
            Config.set("ticketCount", "0");
            Config.save();
            settingsTicketCounter.setText("" + 0);
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

    private void openerClicked() {
        utils.openPanel(leftPanel, settingsPanel);
        settingsTicketCounter.setText(Config.get("ticketCount"));
        settingsIP.setText(Config.get("ip"));
        Config.save();
    }

}

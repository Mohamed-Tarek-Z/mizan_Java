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
import exceptions.ErrorListener;
import utils.Helper;
import view.PrintPanel;

public class SettingsFormController {

    private final PrintPanel printPanel;

    private final JButton settingsCenterPrintBtn;
    private final JButton settingsChangePrintpostionBtn;
    private final JButton settingsSelectPrinterBtn;
    private final JButton settingsTicketCounterResetBtn;
    private final JButton settingsReloadSettingsBtn;

    private final JLabel settingsTicketCounter;
    private final JLabel settingsIP;

    private final JButton formOpenerBtn;
    private final JPanel settingsPanel;
    private final JPanel leftPanel;

    private final ErrorListener errorListener;

    private final PrinterManager printerManager;

    public SettingsFormController(ErrorListener errorListener, JPanel leftPanel, JPanel settingsPanel, PrintPanel printPanel, JButton formOpenerBtn, JTabbedPane settingsTapps, JTextField machName, JTextField machLot,
            JComboBox<Product> machPros, JButton addMachBtn, JButton editMachBtn, JButton delMachBtn,
            JTable machTable, JButton settingsCenterPrintBtn, JButton settingsChangePrintpostionBtn,
            JButton settingsSelectPrinterBtn, JButton settingsTicketCounterResetBtn, JButton settingsReloadSettingsBtn,
            JLabel settingsTicketCounter, JLabel settingsIP,
            MachineController machController, ProductController prosController, PrinterManager printerManager, StorageFormController sfc
    ) {
        this.settingsCenterPrintBtn = settingsCenterPrintBtn;
        this.settingsChangePrintpostionBtn = settingsChangePrintpostionBtn;
        this.settingsSelectPrinterBtn = settingsSelectPrinterBtn;
        this.settingsTicketCounterResetBtn = settingsTicketCounterResetBtn;
        this.settingsReloadSettingsBtn = settingsReloadSettingsBtn;
        this.settingsTicketCounter = settingsTicketCounter;
        this.settingsIP = settingsIP;
        this.printerManager = printerManager;

        this.formOpenerBtn = formOpenerBtn;
        this.settingsPanel = settingsPanel;
        this.leftPanel = leftPanel;
        this.errorListener = errorListener;

        this.printPanel = printPanel;
        settingsTapps.addTab("Print", printPanel);

        try {
            new MachineFormController(errorListener, settingsTapps, machName, machLot, machPros,
                    addMachBtn, editMachBtn, delMachBtn, machTable, machController,
                    prosController, sfc).init();
        } catch (DatabaseException ex) {
            errorListener.onError(ex);
        }
    }

    public void init() {
        settingsCenterPrintBtn.addActionListener((ActionEvent evt) -> {
            printPanel.formController.centerPrint();
        });
        settingsChangePrintpostionBtn.addActionListener((ActionEvent evt) -> {
            printPanel.formController.changePostionPrint();
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

    private void openerClicked() {
        Helper.openPanel(leftPanel, settingsPanel);
        settingsTicketCounter.setText(Config.get("ticketCount"));
        settingsIP.setText(Config.get("ip"));
        Config.save();
    }

}

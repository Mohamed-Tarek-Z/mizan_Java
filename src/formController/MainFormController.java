package formController;

import controller.ClientController;
import controller.ExportController;
import controller.MachineController;
import controller.OrderController;
import controller.ProductController;
import controller.StorageController;
import exceptions.BusinessException;
import java.sql.SQLException;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JSplitPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import dataBase.sqlcon;
import repository.ClientRepository;
import repository.ExportRepository;
import repository.MachineRepository;
import repository.OrderRepository;
import repository.ProductRepository;
import repository.StorageRepository;
import utils.Config;
import exceptions.ErrorListener;
import utils.ExcelManager;
import utils.PrinterManager;
import utils.Helper;
import view.CreatePermitPanel;
import view.Main;
import view.NavPanel;
import view.OrderHistoryPanel;
import view.PausePanel;
import view.ProductPanel;
import view.SettingsPanel;
import view.StatisticsPanel;
import view.StockPanel;
import view.StoragePanel;
import java.sql.Statement;
import view.PrintPanel;

public class MainFormController implements ErrorListener {

    private final sqlcon opj;

    private final ProductController productController;
    private final StorageController storageController;
    private final ExportController exportController;
    private final ClientController clientController;
    private final OrderController orderController;
    private final MachineController machineController;

    private final ExcelManager excelManager;
    private final PrinterManager printerManager;

    private final NavPanel navPanel;

    private final StoragePanel storagePanel;
    private final SettingsPanel settingsPanel;
    private final PrintPanel printPanel;

    private final Main mainform;

    public MainFormController(Main mainform, String Version, JSplitPane splitPane, JPanel leftPanel, JRootPane rootPane, Statement st) throws SQLException, BusinessException {

        this.mainform = mainform;
        Config.load();
        if (!Config.get("ip", "localhost").equalsIgnoreCase("localhost")) {
            showMessage("your DB IP :" + Config.get("ip"), "Not localHost");
        }
        opj = new sqlcon(st);

        excelManager = new ExcelManager();
        printerManager = new PrinterManager(this);
        productController = new ProductController(new ProductRepository(opj));
        storageController = new StorageController(new StorageRepository(opj), new ProductRepository(opj));
        exportController = new ExportController(new ExportRepository(opj), new StorageRepository(opj), new OrderRepository(opj));
        clientController = new ClientController(new ClientRepository(opj));
        orderController = new OrderController(new OrderRepository(opj));
        machineController = new MachineController(new MachineRepository(opj), new ProductRepository(opj));

        navPanel = new NavPanel(this, Version, opj);
        splitPane.setRightComponent(navPanel);
        new PausePanel(navPanel.jButton_Emp_opener, leftPanel);
        new StockPanel(this, leftPanel, navPanel.jButton_Stock_opener, storageController, productController, excelManager);
        new OrderHistoryPanel(this, leftPanel, navPanel.jButton_youm_opener, exportController, productController, clientController, excelManager);
        new StatisticsPanel(this, leftPanel, navPanel.jButton_Statics_opener, exportController, productController, excelManager);
        new ProductPanel(this, leftPanel, navPanel.jButton_pro_opener, productController);
        new CreatePermitPanel(this, leftPanel, navPanel.jButton_Ezn_opener, storageController, productController, clientController, orderController, exportController, excelManager);

        printPanel = new PrintPanel(printerManager);

        storagePanel = new StoragePanel(this, this.mainform, leftPanel, printPanel, navPanel.jButton_Mizan_opener, storageController, productController, printerManager);
        settingsPanel = new SettingsPanel(this, leftPanel, printPanel, navPanel.jButton_Settings_opener, machineController, productController, printerManager, Version, storagePanel.formController);

        setupKeyBindings(rootPane);
        navPanel.jButton_Settings_opener.doClick();
    }

    private void setupKeyBindings(JRootPane rootPane) {
        InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = rootPane.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("F1"), "openWznPanel");
        actionMap.put("openWznPanel", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                navPanel.jButton_Mizan_opener.doClick();
            }
        });

        inputMap.put(KeyStroke.getKeyStroke("F2"), "OpenReportPanel");
        actionMap.put("OpenReportPanel", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                navPanel.jButton_Ezn_opener.doClick();
            }
        });

        inputMap.put(KeyStroke.getKeyStroke("F3"), "OpenAddProPanel");
        actionMap.put("OpenAddProPanel", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                navPanel.jButton_pro_opener.doClick();
            }
        });

        inputMap.put(KeyStroke.getKeyStroke("F4"), "OpenEmptyPanel");
        actionMap.put("OpenEmptyPanel", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                navPanel.jButton_Emp_opener.doClick();
            }
        });

        inputMap.put(KeyStroke.getKeyStroke("F5"), "OpenStockPanel");
        actionMap.put("OpenStockPanel", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                navPanel.jButton_Stock_opener.doClick();
            }
        });
    }

    @Override
    public void onError(Exception ex) {
        SwingUtilities.invokeLater(() -> {
            showException(ex);
        });
    }

    @Override
    public void onWarning(String msg, String title) {
        SwingUtilities.invokeLater(() -> {
            showMessage(msg, title);
        });
    }

    @Override
    public int onQuest(String msg, String title) {
        return showQuest(msg, title);
    }

    @Override
    public Object onQuest(Object[] quest, String msg, String title) {
        return showQuest(quest, msg, title);
    }

    private void showError(String msg, String title) {
        JOptionPane.showMessageDialog(mainform, Helper.addStyle(msg), title, JOptionPane.WARNING_MESSAGE);
    }

    private void showMessage(String msg, String title) {
        JOptionPane.showMessageDialog(mainform, Helper.addStyle(msg), title, JOptionPane.INFORMATION_MESSAGE);
    }

    private int showQuest(String msg, String title) {
        return JOptionPane.showConfirmDialog(mainform, Helper.addStyle(msg), title, JOptionPane.YES_NO_OPTION);
    }

    private Object showQuest(Object[] quest, String msg, String title) {
        return JOptionPane.showInputDialog(mainform, Helper.addStyle(msg), title, JOptionPane.QUESTION_MESSAGE, null, quest, null);
    }

    private void showException(Exception ex) {
        if (ex != null) {
            if (ex.getMessage() != null) {
                if (ex instanceof SQLException) {
                    showError(ex.getMessage(), "SQL Exception");
                } else {
                    if (!ex.getMessage().isEmpty()) {
                        showMessage(ex.getMessage(), "Exception");
                    } 
                }
            } else {
                showMessage("No Message: " + ex.toString(), "Exception");
            }
        }
    }
}

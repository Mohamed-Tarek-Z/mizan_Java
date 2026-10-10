package view;

import repository.*;
import controller.*;
import model.*;
import utils.*;
import exceptions.*;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Mainform extends javax.swing.JFrame implements ErrorListener {

    private final sqlcon opj;

    private final ProductController productController;
    private final StorageController storageController;
    private final ExportController exportController;
    private final ClientController clientController;
    private final OrderController orderController;
    private final MachineController machineController;

    private final ExcelManager excelManager;
    private final PrinterManager printerManager;

    private final String Version = "V 4.0.0";

    private final NavPanel navPanel;
    private final StoragePanel storagePanel;
    private final CreatePermitPanel createPermitPanel;
    private final ProductPanel productPanel;
    private final StockPanel stockPanel;
    private final StatisticsPanel statisticsPanel;
    private final OrderHistoryPanel orderHistoryPanel;
    private final PausePanel pausePanel;
    private final SettingsPanel settingsPanel;

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

    public Mainform() throws DatabaseException, BusinessException, SQLException {

        initComponents();

        Config.load();
        if (!Config.get("ip", "localhost").equalsIgnoreCase("localhost")) {
            showMessage("your DB IP :" + Config.get("ip"), "Not localHost");
        }
        opj = new sqlcon(Config.get("ip", "localhost"));

        excelManager = new ExcelManager();
        printerManager = new PrinterManager(this);
        productController = new ProductController(new ProductRepository(opj));
        storageController = new StorageController(new StorageRepository(opj), new ProductRepository(opj));
        exportController = new ExportController(new ExportRepository(opj), new StorageRepository(opj), new OrderRepository(opj));
        clientController = new ClientController(new ClientRepository(opj));
        orderController = new OrderController(new OrderRepository(opj));
        machineController = new MachineController(new MachineRepository(opj), new ProductRepository(opj));

        navPanel = new NavPanel(this, Version, opj);
        jSplitPane1.setRightComponent(navPanel);

        pausePanel = new PausePanel(navPanel.jButton_Emp_opener, left_panel);
        stockPanel = new StockPanel(this, left_panel, navPanel.jButton_Stock_opener, storageController, productController, excelManager);
        orderHistoryPanel = new OrderHistoryPanel(this, left_panel, navPanel.jButton_youm_opener, exportController, productController, clientController, excelManager);
        statisticsPanel = new StatisticsPanel(this, left_panel, navPanel.jButton_Statics_opener, exportController, productController, excelManager);
        productPanel = new ProductPanel(this, left_panel, navPanel.jButton_pro_opener, productController);
        createPermitPanel = new CreatePermitPanel(this, left_panel, navPanel.jButton_Ezn_opener, storageController, productController, clientController, orderController, exportController, excelManager);

        settingsPanel = new SettingsPanel(this, left_panel, navPanel.jButton_Settings_opener, machineController, productController, printerManager, Version);
        
        storagePanel = new StoragePanel(this, left_panel, settingsPanel.jPanel_print, settingsPanel.formController, navPanel.jButton_Mizan_opener, storageController, productController, printerManager);



 

        setupKeyBindings();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        left_panel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("mizan program " + Version);
        setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        setLocation(new java.awt.Point(0, 0));
        setLocationByPlatform(true);
        setMaximumSize(new java.awt.Dimension(1020, 700));
        setMinimumSize(new java.awt.Dimension(1020, 700));
        setName("Main_Frame"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1020, 700));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jSplitPane1.setDividerLocation(840);
        jSplitPane1.setEnabled(false);
        jSplitPane1.setFocusable(false);
        jSplitPane1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jSplitPane1.setMaximumSize(new java.awt.Dimension(1020, 655));
        jSplitPane1.setMinimumSize(new java.awt.Dimension(1020, 655));
        jSplitPane1.setName(""); // NOI18N
        jSplitPane1.setPreferredSize(new java.awt.Dimension(1010, 650));

        left_panel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        left_panel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        left_panel.setMaximumSize(new java.awt.Dimension(895, 650));
        left_panel.setMinimumSize(new java.awt.Dimension(840, 645));
        left_panel.setPreferredSize(new java.awt.Dimension(840, 645));
        left_panel.setLayout(new java.awt.CardLayout());
        jSplitPane1.setLeftComponent(left_panel);
        left_panel.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1020, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 660, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getAccessibleContext().setAccessibleDescription("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        evt.getID();
        try {
            Config.save();
            System.exit(NORMAL);
        } catch (BusinessException ex) {
            this.onError(ex);
        }
    }//GEN-LAST:event_formWindowClosing

    private void setupKeyBindings() {
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

    private void showError(String msg, String title) {
        JOptionPane.showMessageDialog(this, utils.addStyle(msg), title, JOptionPane.WARNING_MESSAGE);
    }

    private void showMessage(String msg, String title) {
        JOptionPane.showMessageDialog(this, utils.addStyle(msg), title, JOptionPane.INFORMATION_MESSAGE);
    }

    private int showQuest(String msg, String title) {
        return JOptionPane.showConfirmDialog(this, utils.addStyle(msg), title, JOptionPane.YES_NO_OPTION);
    }

    private Object showQuest(Object[] quest, String msg, String title) {
        return JOptionPane.showInputDialog(this, utils.addStyle(msg), title, JOptionPane.QUESTION_MESSAGE, null, quest, null);
    }

    private void showException(Exception ex) {
        if (ex instanceof SQLException) {
            showError(ex.getLocalizedMessage(), "SQL Exception");
        } else {
            if (ex != null) {
                showMessage(ex.getLocalizedMessage(), "Exception");
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Mainform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new Mainform().setVisible(true);
            } catch (BusinessException | DatabaseException | SQLException ex) {
                Logger.getLogger(Mainform.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, utils.addStyle(ex.getLocalizedMessage()), "إنتبه", JOptionPane.PLAIN_MESSAGE);
                System.exit(NORMAL);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JPanel left_panel;
    // End of variables declaration//GEN-END:variables
}

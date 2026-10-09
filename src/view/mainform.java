package view;

import repository.*;
import controller.*;
import model.*;
import utils.*;
import exceptions.*;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class Mainform extends javax.swing.JFrame implements ErrorListener {

    private final sqlcon opj;

    private final NavPanel navPanel;

    private final ProductController productController;
    private final StorageController storageController;
    private final ExportController exportController;
    private final ClientController clientController;
    private final OrderController orderController;
    private final MachineController machineController;

    private final ExcelManager excelManager;
    private final PrinterManager printerManager;

    private final String Version = "V 4.0.0";

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
        this.opj = new sqlcon(Config.get("ip", "localhost"));

        this.excelManager = new ExcelManager();
        this.printerManager = new PrinterManager(this);
        this.productController = new ProductController(new ProductRepository(this.opj));
        this.storageController = new StorageController(new StorageRepository(this.opj), new ProductRepository(this.opj));
        this.exportController = new ExportController(new ExportRepository(this.opj), new StorageRepository(this.opj), new OrderRepository(this.opj));
        this.clientController = new ClientController(new ClientRepository(this.opj));
        this.orderController = new OrderController(new OrderRepository(this.opj));
        this.machineController = new MachineController(new MachineRepository(this.opj), new ProductRepository(this.opj));

        this.navPanel = new NavPanel(Version);
        this.jSplitPane1.setRightComponent(navPanel);

        new PausePanel(navPanel.jButton_Emp_opener, left_panel);
        new StockPanel(this, left_panel, navPanel.jButton_Stock_opener, storageController, productController, excelManager);
        new OrderHistoryPanel(this, left_panel, navPanel.jButton_youm_opener, exportController, productController, clientController, excelManager);
        new StatisticsPanel(this, left_panel, navPanel.jButton_Statics_opener, exportController, productController, excelManager);
        new ProductPanel(this, left_panel, navPanel.jButton_pro_opener, productController);
        new CreatePermitPanel(this, left_panel, navPanel.jButton_Ezn_opener, storageController, productController, clientController, orderController, exportController, excelManager);

        new SettingsPanel(this, left_panel, navPanel.jButton_Settings_opener, machineController, productController, printerManager, Version);

        TextFieldRules.apply(jTextField_E_Wight, 8, true, () -> {
        }, null);
        TextFieldRules.apply(jTextField_E_PaltNum, 3, true, () -> {
        }, null);
        TextFieldRules.apply(jTextField_E_ConNum, 3, true, () -> {
        }, null);
        TextFieldRules.apply(jTextField_E_lot, 15, false, () -> {
        }, null);
        TextFieldRules.apply(jTextField_E_TotWight, 8, true, () -> {
        }, null);
        TextFieldRules.apply(jTextField_ME_lot, 15, false, () -> {
        }, null);
        TextFieldRules.apply(jTextField_ME_PaltNum, 3, true, () -> {
        }, null);

        populateCombos();
        setupKeyBindings();

        new WeightScaleCapture(jTextField_storage_TotalWeight).install();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        SingleEdit = new javax.swing.JFrame();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jTextField_E_O_lot = new javax.swing.JTextField();
        jTextField_E_O_ConNum = new javax.swing.JTextField();
        jTextField_E_O_PaltNum = new javax.swing.JTextField();
        jTextField_E_O_Wight = new javax.swing.JTextField();
        jComboBox_E_O_proName = new javax.swing.JComboBox<>();
        jLabel30 = new javax.swing.JLabel();
        jTextField_E_lot = new javax.swing.JTextField();
        jTextField_E_ConNum = new javax.swing.JTextField();
        jTextField_E_PaltNum = new javax.swing.JTextField();
        jTextField_E_Wight = new javax.swing.JTextField();
        jComboBox_E_proName = new javax.swing.JComboBox<>();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jButton_E_Edit = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jCheckBox_E_O_Mark = new javax.swing.JCheckBox();
        jCheckBox_E_Mark = new javax.swing.JCheckBox();
        jButton_E_print = new javax.swing.JButton();
        jTextField_E_TotWight = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jCheckBox_E_P = new javax.swing.JCheckBox();
        jTextField_E_Color = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jTextField_E_O_TotWight = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        MultiEdit = new javax.swing.JFrame();
        jTextField_ME_lot = new javax.swing.JTextField();
        jTextField_ME_PaltNum = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jButton_ME_Edit = new javax.swing.JButton();
        jLabel53 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jComboBox_ME_type = new javax.swing.JComboBox<>();
        jCheckBox_ME_MarkBag = new javax.swing.JCheckBox();
        jSplitPane1 = new javax.swing.JSplitPane();
        left_panel = new javax.swing.JPanel();
        storage_panel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_storage = new javax.swing.JTable()

        {

            @Override

            public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int columnIndex) {
                Component componenet = super.prepareRenderer(renderer, rowIndex, columnIndex);

                boolean value = (boolean) getModel().getValueAt(rowIndex, 6);

                if (value) {
                    componenet.setBackground(isRowSelected(rowIndex) ? Color.YELLOW : Color.GREEN);
                    componenet.setForeground(Color.BLACK);

                } else {
                    componenet.setBackground(isRowSelected(rowIndex) ? componenet.getBackground() : Color.WHITE);
                    //componenet.setForeground(Color.BLACK);
                }

                return componenet;
            }

        }

        ;
        jButton_storage_addData = new javax.swing.JButton();
        jButton_storage_delData = new javax.swing.JButton();
        jComboBox_storage_products = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jTextField_storage_lot = new javax.swing.JTextField();
        jTextField_storage_TotalWeight = new javax.swing.JTextField();
        jTextField_storage_EmptyBagWeight = new javax.swing.JTextField();
        jTextField_storage_coneNumber = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField_storage_palletNumber = new javax.swing.JTextField();
        jTextField_storage_EmptyConeWeight = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField_storage_NetWeight = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextField_storage_PalletWeight = new javax.swing.JTextField();
        jCheckBox_storage_MarkBag = new javax.swing.JCheckBox();
        jButton_storage_Clear = new javax.swing.JButton();
        jCheckBox_storage_printLTicket = new javax.swing.JCheckBox();
        jTextField_storage_Color = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jCheckBox_storage_Box = new javax.swing.JCheckBox();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jButton_storage_RePrintLastTicket = new javax.swing.JButton();
        jProgressBar_storage_pallet = new javax.swing.JProgressBar();
        jCheckBox_storage_freezeConeNumber = new javax.swing.JCheckBox();
        jCheckBox_storage_FreezeEmptyBagWight = new javax.swing.JCheckBox();
        jCheckBox_storage_FreezeConeWeightChange = new javax.swing.JCheckBox();
        jCheckBox_storage_ignoreLimits = new javax.swing.JCheckBox();
        jSeparator7 = new javax.swing.JSeparator();
        jTextField_storage_SearchProducts = new javax.swing.JTextField();
        jTextField_inputExeption = new javax.swing.JTextField();

        jFileChooser1.setDialogType(javax.swing.JFileChooser.CUSTOM_DIALOG);
        jFileChooser1.setCurrentDirectory(new java.io.File("P:\\"));
            jFileChooser1.setDialogTitle("");
            jFileChooser1.setFileSelectionMode(javax.swing.JFileChooser.FILES_AND_DIRECTORIES);
            jFileChooser1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            jFileChooser1.getAccessibleContext().setAccessibleParent(this);

            SingleEdit.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            SingleEdit.setTitle("Single Edit");
            SingleEdit.setAlwaysOnTop(true);
            SingleEdit.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            SingleEdit.setLocation(new java.awt.Point(100, 100));
            SingleEdit.setLocationByPlatform(true);
            SingleEdit.setMinimumSize(new java.awt.Dimension(780, 400));
            SingleEdit.setName("Single Edit"); // NOI18N
            SingleEdit.setResizable(false);
            SingleEdit.setType(java.awt.Window.Type.POPUP);
            SingleEdit.addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowClosing(java.awt.event.WindowEvent evt) {
                    SingleEditWindowClosing(evt);
                }
            });
            SingleEdit.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            jLabel25.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jLabel25.setText("رقم البالته");
            SingleEdit.getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, -1, -1));

            jLabel26.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jLabel26.setText("رقم اللوط");
            SingleEdit.getContentPane().add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, -1, -1));

            jLabel27.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jLabel27.setText("الصنف");
            SingleEdit.getContentPane().add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, -1, -1));

            jLabel28.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jLabel28.setText("عدد الكون");
            SingleEdit.getContentPane().add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, -1, -1));

            jLabel29.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jLabel29.setText("الوزن");
            SingleEdit.getContentPane().add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 10, -1, -1));

            jTextField_E_O_lot.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jTextField_E_O_lot.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            jTextField_E_O_lot.setEnabled(false);
            SingleEdit.getContentPane().add(jTextField_E_O_lot, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 50, 90, -1));

            jTextField_E_O_ConNum.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jTextField_E_O_ConNum.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            jTextField_E_O_ConNum.setEnabled(false);
            SingleEdit.getContentPane().add(jTextField_E_O_ConNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 50, 90, -1));

            jTextField_E_O_PaltNum.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jTextField_E_O_PaltNum.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            jTextField_E_O_PaltNum.setEnabled(false);
            SingleEdit.getContentPane().add(jTextField_E_O_PaltNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 50, 90, -1));

            jTextField_E_O_Wight.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jTextField_E_O_Wight.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            jTextField_E_O_Wight.setEnabled(false);
            SingleEdit.getContentPane().add(jTextField_E_O_Wight, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 50, 90, -1));

            jComboBox_E_O_proName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jComboBox_E_O_proName.setEnabled(false);
            SingleEdit.getContentPane().add(jComboBox_E_O_proName, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, 143, -1));

            jLabel30.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jLabel30.setText("الوزن");
            SingleEdit.getContentPane().add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 180, -1, -1));

            jTextField_E_lot.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jTextField_E_lot.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            SingleEdit.getContentPane().add(jTextField_E_lot, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 220, 90, -1));

            jTextField_E_ConNum.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jTextField_E_ConNum.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            SingleEdit.getContentPane().add(jTextField_E_ConNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 220, 90, -1));

            jTextField_E_PaltNum.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jTextField_E_PaltNum.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            SingleEdit.getContentPane().add(jTextField_E_PaltNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 220, 90, -1));

            jTextField_E_Wight.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jTextField_E_Wight.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            SingleEdit.getContentPane().add(jTextField_E_Wight, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 220, 90, -1));

            jComboBox_E_proName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            SingleEdit.getContentPane().add(jComboBox_E_proName, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 220, 143, -1));

            jLabel31.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jLabel31.setText("رقم البالته");
            SingleEdit.getContentPane().add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 180, -1, -1));

            jLabel32.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jLabel32.setText("رقم اللوط");
            SingleEdit.getContentPane().add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 180, -1, -1));

            jLabel33.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jLabel33.setText("الصنف");
            SingleEdit.getContentPane().add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 180, -1, 20));

            jLabel34.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jLabel34.setForeground(new java.awt.Color(255, 51, 51));
            jLabel34.setText("عدد الكون");
            SingleEdit.getContentPane().add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 180, -1, -1));

            jButton_E_Edit.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jButton_E_Edit.setText("تعديل");
            jButton_E_Edit.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_E_EditActionPerformed(evt);
                }
            });
            SingleEdit.getContentPane().add(jButton_E_Edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 270, 141, 39));

            jLabel35.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jLabel35.setText("ـــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــ");
            SingleEdit.getContentPane().add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 750, 20));

            jLabel36.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jLabel36.setText("القيم الجديده");
            SingleEdit.getContentPane().add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 130, -1, -1));

            jCheckBox_E_O_Mark.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jCheckBox_E_O_Mark.setText("تعليم الشيكاره");
            jCheckBox_E_O_Mark.setEnabled(false);
            SingleEdit.getContentPane().add(jCheckBox_E_O_Mark, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 85, -1, -1));

            jCheckBox_E_Mark.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jCheckBox_E_Mark.setText("تعليم الشيكاره");
            SingleEdit.getContentPane().add(jCheckBox_E_Mark, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 280, -1, -1));

            jButton_E_print.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
            jButton_E_print.setText("Print");
            jButton_E_print.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_E_printActionPerformed(evt);
                }
            });
            SingleEdit.getContentPane().add(jButton_E_print, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 100, 40));

            jTextField_E_TotWight.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            SingleEdit.getContentPane().add(jTextField_E_TotWight, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, 90, -1));

            jLabel43.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jLabel43.setForeground(new java.awt.Color(255, 0, 0));
            jLabel43.setText("الوزن قائم");
            SingleEdit.getContentPane().add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, 80, 20));

            jCheckBox_E_P.setText("print");
            SingleEdit.getContentPane().add(jCheckBox_E_P, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, -1, -1));

            jTextField_E_Color.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            SingleEdit.getContentPane().add(jTextField_E_Color, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 90, -1));

            jLabel44.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jLabel44.setText("اللون");
            SingleEdit.getContentPane().add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 50, 20));

            jTextField_E_O_TotWight.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jTextField_E_O_TotWight.setEnabled(false);
            jTextField_E_O_TotWight.setFocusable(false);
            jTextField_E_O_TotWight.setRequestFocusEnabled(false);
            SingleEdit.getContentPane().add(jTextField_E_O_TotWight, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 90, -1));

            jLabel39.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jLabel39.setText("قائم");
            SingleEdit.getContentPane().add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, -1, -1));

            jLabel13.setForeground(new java.awt.Color(255, 0, 0));
            jLabel13.setText("الأماكن الحمراء لايمكن تعديلها في نفس العملية*");
            SingleEdit.getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 120, 280, 20));

            SingleEdit.getAccessibleContext().setAccessibleParent(this);

            MultiEdit.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            MultiEdit.setTitle("Multi Edit");
            MultiEdit.setAlwaysOnTop(true);
            MultiEdit.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            MultiEdit.setLocation(new java.awt.Point(100, 100));
            MultiEdit.setLocationByPlatform(true);
            MultiEdit.setMaximumSize(new java.awt.Dimension(500, 400));
            MultiEdit.setMinimumSize(new java.awt.Dimension(500, 400));
            MultiEdit.setName("Multi Edit"); // NOI18N
            MultiEdit.setPreferredSize(new java.awt.Dimension(500, 400));
            MultiEdit.setResizable(false);
            MultiEdit.setType(java.awt.Window.Type.POPUP);
            MultiEdit.addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowClosing(java.awt.event.WindowEvent evt) {
                    MultiEditWindowClosing(evt);
                }
            });
            MultiEdit.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            jTextField_ME_lot.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jTextField_ME_lot.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            MultiEdit.getContentPane().add(jTextField_ME_lot, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 90, -1));

            jTextField_ME_PaltNum.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jTextField_ME_PaltNum.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            MultiEdit.getContentPane().add(jTextField_ME_PaltNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 170, 90, -1));

            jLabel47.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jLabel47.setText("رقم البالته");
            MultiEdit.getContentPane().add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 130, -1, -1));

            jLabel48.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jLabel48.setText("رقم اللوط");
            MultiEdit.getContentPane().add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

            jButton_ME_Edit.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jButton_ME_Edit.setText("تعديل");
            jButton_ME_Edit.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_ME_EditActionPerformed(evt);
                }
            });
            MultiEdit.getContentPane().add(jButton_ME_Edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 260, 170, 60));

            jLabel53.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jLabel53.setText("القيم الجديده");
            MultiEdit.getContentPane().add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, -1, -1));

            jLabel21.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jLabel21.setText("الصنف");
            MultiEdit.getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, -1, -1));

            jComboBox_ME_type.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            MultiEdit.getContentPane().add(jComboBox_ME_type, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 220, -1));

            jCheckBox_ME_MarkBag.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jCheckBox_ME_MarkBag.setText("تعليم الشائر");
            MultiEdit.getContentPane().add(jCheckBox_ME_MarkBag, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 140, -1, -1));

            MultiEdit.getAccessibleContext().setAccessibleName("Multiedit_Window");
            MultiEdit.getAccessibleContext().setAccessibleParent(this);

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

            storage_panel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            storage_panel.setMaximumSize(new java.awt.Dimension(835, 640));
            storage_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            jTable_storage.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jTable_storage.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                    "الوزن", "عدد الكون", "رقم اللوط", "رقم البالتة", "م", "مسلسل ", "s"
                }
            ) {
                Class[] types = new Class [] {
                    java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
                };
                boolean[] canEdit = new boolean [] {
                    false, false, false, false, false, false, false
                };

                public Class getColumnClass(int columnIndex) {
                    return types [columnIndex];
                }

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            });
            jTable_storage.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            jTable_storage.getTableHeader().setFont(new Font("Tahoma", 1, 16));
            jTable_storage.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jTable_storage.setRowHeight(25);
            jTable_storage.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            jTable_storage.setShowGrid(true);
            jTable_storage.getTableHeader().setReorderingAllowed(false);
            jTable_storage.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseReleased(java.awt.event.MouseEvent evt) {
                    jTable_storageMouseReleased(evt);
                }
            });
            jTable_storage.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTable_storageKeyTyped(evt);
                }
            });
            jScrollPane1.setViewportView(jTable_storage);
            if (jTable_storage.getColumnModel().getColumnCount() > 0) {
                jTable_storage.getColumnModel().getColumn(0).setResizable(false);
                jTable_storage.getColumnModel().getColumn(1).setResizable(false);
                jTable_storage.getColumnModel().getColumn(2).setResizable(false);
                jTable_storage.getColumnModel().getColumn(3).setResizable(false);
                jTable_storage.getColumnModel().getColumn(4).setMinWidth(0);
                jTable_storage.getColumnModel().getColumn(4).setPreferredWidth(0);
                jTable_storage.getColumnModel().getColumn(4).setMaxWidth(0);
                jTable_storage.getColumnModel().getColumn(5).setResizable(false);
                jTable_storage.getColumnModel().getColumn(6).setMinWidth(0);
                jTable_storage.getColumnModel().getColumn(6).setPreferredWidth(0);
                jTable_storage.getColumnModel().getColumn(6).setMaxWidth(0);
            }

            storage_panel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 590, 620));

            jButton_storage_addData.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
            jButton_storage_addData.setText("إضافة");
            jButton_storage_addData.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_storage_addDataActionPerformed(evt);
                }
            });
            storage_panel.add(jButton_storage_addData, new org.netbeans.lib.awtextra.AbsoluteConstraints(745, 590, 90, 50));

            jButton_storage_delData.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
            jButton_storage_delData.setText("حذف");
            jButton_storage_delData.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_storage_delDataActionPerformed(evt);
                }
            });
            storage_panel.add(jButton_storage_delData, new org.netbeans.lib.awtextra.AbsoluteConstraints(605, 590, 80, 50));

            jComboBox_storage_products.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
            jComboBox_storage_products.addItemListener(new java.awt.event.ItemListener() {
                public void itemStateChanged(java.awt.event.ItemEvent evt) {
                    jComboBox_storage_productsItemStateChanged(evt);
                }
            });
            storage_panel.add(jComboBox_storage_products, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 100, 210, 30));

            jLabel6.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
            jLabel6.setText("الصنف");
            storage_panel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 70, -1, 20));

            jTextField_storage_lot.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
            jTextField_storage_lot.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
            jTextField_storage_lot.setMaximumSize(new java.awt.Dimension(7, 38));
            storage_panel.add(jTextField_storage_lot, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 140, 120, 36));

            jTextField_storage_TotalWeight.setBackground(new java.awt.Color(255, 204, 204));
            jTextField_storage_TotalWeight.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
            jTextField_storage_TotalWeight.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
            jTextField_storage_TotalWeight.setMaximumSize(new java.awt.Dimension(7, 38));
            storage_panel.add(jTextField_storage_TotalWeight, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 340, 120, -1));

            jTextField_storage_EmptyBagWeight.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
            jTextField_storage_EmptyBagWeight.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
            jTextField_storage_EmptyBagWeight.setMaximumSize(new java.awt.Dimension(7, 38));
            storage_panel.add(jTextField_storage_EmptyBagWeight, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 290, 120, -1));

            jTextField_storage_coneNumber.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
            jTextField_storage_coneNumber.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
            jTextField_storage_coneNumber.setMaximumSize(new java.awt.Dimension(7, 38));
            storage_panel.add(jTextField_storage_coneNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 240, 120, -1));

            jLabel7.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
            jLabel7.setText("الوزن القائم");
            jLabel7.setMaximumSize(new java.awt.Dimension(82, 24));
            jLabel7.setMinimumSize(new java.awt.Dimension(82, 24));
            jLabel7.setPreferredSize(new java.awt.Dimension(82, 24));
            storage_panel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(735, 340, -1, 30));

            jLabel8.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
            jLabel8.setText("اللوط");
            jLabel8.setMaximumSize(new java.awt.Dimension(82, 24));
            jLabel8.setMinimumSize(new java.awt.Dimension(82, 24));
            jLabel8.setPreferredSize(new java.awt.Dimension(82, 24));
            storage_panel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 140, -1, 30));

            jLabel9.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
            jLabel9.setText("عدد الكون");
            jLabel9.setMaximumSize(new java.awt.Dimension(82, 24));
            jLabel9.setMinimumSize(new java.awt.Dimension(82, 24));
            jLabel9.setPreferredSize(new java.awt.Dimension(82, 24));
            storage_panel.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(735, 240, -1, 30));

            jLabel10.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
            jLabel10.setText("وزن الكونه");
            jLabel10.setMaximumSize(new java.awt.Dimension(82, 24));
            jLabel10.setMinimumSize(new java.awt.Dimension(82, 24));
            jLabel10.setPreferredSize(new java.awt.Dimension(82, 24));
            storage_panel.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 440, -1, 30));

            jTextField_storage_palletNumber.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
            jTextField_storage_palletNumber.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
            jTextField_storage_palletNumber.setMaximumSize(new java.awt.Dimension(7, 38));
            storage_panel.add(jTextField_storage_palletNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 180, 120, -1));

            jTextField_storage_EmptyConeWeight.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
            jTextField_storage_EmptyConeWeight.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
            jTextField_storage_EmptyConeWeight.setMaximumSize(new java.awt.Dimension(7, 38));
            storage_panel.add(jTextField_storage_EmptyConeWeight, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 440, 120, -1));

            jLabel11.setFont(new java.awt.Font("sansserif", 1, 16)); // NOI18N
            jLabel11.setText("فارغ الشيكاره");
            jLabel11.setMaximumSize(new java.awt.Dimension(82, 24));
            jLabel11.setMinimumSize(new java.awt.Dimension(82, 24));
            jLabel11.setPreferredSize(new java.awt.Dimension(82, 24));
            storage_panel.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(735, 290, -1, 30));

            jLabel12.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
            jLabel12.setText("الوزن الصافي");
            storage_panel.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(735, 390, -1, 30));

            jTextField_storage_NetWeight.setEditable(false);
            jTextField_storage_NetWeight.setBackground(new java.awt.Color(204, 255, 204));
            jTextField_storage_NetWeight.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
            jTextField_storage_NetWeight.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
            jTextField_storage_NetWeight.setMaximumSize(new java.awt.Dimension(7, 38));
            storage_panel.add(jTextField_storage_NetWeight, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 390, 120, -1));

            jLabel2.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
            jLabel2.setText("رقم البالتة");
            jLabel2.setMaximumSize(new java.awt.Dimension(82, 24));
            jLabel2.setMinimumSize(new java.awt.Dimension(82, 24));
            jLabel2.setPreferredSize(new java.awt.Dimension(82, 24));
            storage_panel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 180, -1, 30));

            jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jLabel14.setText("وزن البالتة");
            storage_panel.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 490, -1, 30));

            jTextField_storage_PalletWeight.setEditable(false);
            jTextField_storage_PalletWeight.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jTextField_storage_PalletWeight.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
            storage_panel.add(jTextField_storage_PalletWeight, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 490, 140, -1));

            jCheckBox_storage_MarkBag.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            jCheckBox_storage_MarkBag.setText("تعليم البالته");
            jCheckBox_storage_MarkBag.setFocusable(false);
            storage_panel.add(jCheckBox_storage_MarkBag, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 210, -1, 20));

            jButton_storage_Clear.setBackground(new java.awt.Color(240, 0, 0));
            jButton_storage_Clear.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
            jButton_storage_Clear.setForeground(new java.awt.Color(255, 255, 255));
            jButton_storage_Clear.setText("X");
            jButton_storage_Clear.setToolTipText("Clear");
            jButton_storage_Clear.setBorderPainted(false);
            jButton_storage_Clear.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
            jButton_storage_Clear.setFocusable(false);
            jButton_storage_Clear.setName(""); // NOI18N
            jButton_storage_Clear.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_storage_ClearActionPerformed(evt);
                }
            });
            storage_panel.add(jButton_storage_Clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(695, 610, 40, -1));

            jCheckBox_storage_printLTicket.setSelected(true);
            jCheckBox_storage_printLTicket.setText("طباعة");
            jCheckBox_storage_printLTicket.setFocusable(false);
            storage_panel.add(jCheckBox_storage_printLTicket, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, -1, -1));

            jTextField_storage_Color.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jTextField_storage_Color.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            storage_panel.add(jTextField_storage_Color, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 540, 140, 40));

            jLabel41.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
            jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel41.setText("اللون");
            storage_panel.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 540, 70, 40));

            jCheckBox_storage_Box.setText("صندوق");
            jCheckBox_storage_Box.setEnabled(false);
            storage_panel.add(jCheckBox_storage_Box, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 70, -1, -1));

            jSeparator4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
            storage_panel.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(602, 232, 230, -1));
            storage_panel.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 135, 210, -1));

            jSeparator6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
            storage_panel.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(602, 482, 230, -1));

            jButton_storage_RePrintLastTicket.setBackground(new java.awt.Color(153, 153, 255));
            jButton_storage_RePrintLastTicket.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
            jButton_storage_RePrintLastTicket.setText("Print");
            jButton_storage_RePrintLastTicket.setToolTipText("Re-Print ticket");
            jButton_storage_RePrintLastTicket.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            jButton_storage_RePrintLastTicket.setFocusable(false);
            jButton_storage_RePrintLastTicket.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_storage_RePrintLastTicketActionPerformed(evt);
                }
            });
            storage_panel.add(jButton_storage_RePrintLastTicket, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 70, 60, 20));

            jProgressBar_storage_pallet.setMaximum(20);
            jProgressBar_storage_pallet.setToolTipText("Pallet");
            jProgressBar_storage_pallet.setFocusable(false);
            jProgressBar_storage_pallet.setMaximumSize(new java.awt.Dimension(10, 14));
            jProgressBar_storage_pallet.setRequestFocusEnabled(false);
            storage_panel.add(jProgressBar_storage_pallet, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 2, 590, -1));

            jCheckBox_storage_freezeConeNumber.setFocusable(false);
            storage_panel.add(jCheckBox_storage_freezeConeNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(815, 250, -1, -1));

            jCheckBox_storage_FreezeEmptyBagWight.setFocusable(false);
            storage_panel.add(jCheckBox_storage_FreezeEmptyBagWight, new org.netbeans.lib.awtextra.AbsoluteConstraints(815, 300, -1, -1));

            jCheckBox_storage_FreezeConeWeightChange.setFocusable(false);
            storage_panel.add(jCheckBox_storage_FreezeConeWeightChange, new org.netbeans.lib.awtextra.AbsoluteConstraints(815, 450, -1, -1));

            jCheckBox_storage_ignoreLimits.setText("سماح الوزن");
            jCheckBox_storage_ignoreLimits.setFocusable(false);
            storage_panel.add(jCheckBox_storage_ignoreLimits, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 10, -1, -1));

            jSeparator7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
            jSeparator7.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
            jSeparator7.setMaximumSize(new java.awt.Dimension(37, 3));
            jSeparator7.setMinimumSize(new java.awt.Dimension(37, 3));
            jSeparator7.setPreferredSize(new java.awt.Dimension(37, 55));
            storage_panel.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(605, 10, 110, 20));
            storage_panel.add(jTextField_storage_SearchProducts, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 40, 150, 20));

            jTextField_inputExeption.setEditable(false);
            jTextField_inputExeption.setBackground(new java.awt.Color(255, 255, 255));
            jTextField_inputExeption.setAutoscrolls(false);
            jTextField_inputExeption.setBorder(null);
            jTextField_inputExeption.setFocusable(false);
            jTextField_inputExeption.setRequestFocusEnabled(false);
            jTextField_inputExeption.setVerifyInputWhenFocusTarget(false);
            storage_panel.add(jTextField_inputExeption, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 40, 40, 30));

            left_panel.add(storage_panel, "Mizan");

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

    private void jButton_storage_addDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_storage_addDataActionPerformed
        evt.getID();
        try {
            calc_net_weight();
            Bag req = buildRequestFromUI();

            if (req == null) {
                return;
            }

            int[] result = storageController.addStorage(req, jCheckBox_storage_ignoreLimits.isSelected());

            jTextField_storage_palletNumber.setText(ArabicDigits.toArabicDigits(result[0]));
            jProgressBar_storage_pallet.setValue(result[1]);
            calc_pallet_weight();

//            PreparePrintingPanel(new Ticket(((Product) jComboBox_storage_products.getSelectedItem()).getName(),
//                    jTextField_storage_palletNumber.getText(),
//                    ((Product) jComboBox_storage_products.getSelectedItem()).getColor(),
//                    jTextField_storage_lot.getText(),
//                    jTextField_storage_coneNumber.getText(),
//                    jTextField_storage_TotalWeight.getText(),
//                    jTextField_storage_NetWeight.getText()
//            ), jCheckBox_storage_printLTicket.isSelected());
            jButton_storage_Clear.doClick();
            fill_storage_table();
            showMessageInlable(false);
        } catch (DatabaseException ex) {
            this.onError(ex);
        } catch (BusinessException ex) {
            showMessageInlable(true);
        }
    }//GEN-LAST:event_jButton_storage_addDataActionPerformed

    private void jButton_storage_delDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_storage_delDataActionPerformed
        try {
            evt.getID();
            if (JOptionPane.showConfirmDialog(this, utils.addStyle("هل تريد الحذف ؟"), "تنبيه",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                if (jTable_storage.getSelectedRowCount() == 1) {

                    storageController.removeBag(
                            jTable_storage.getModel().getValueAt(jTable_storage.getSelectedRow(), 4).toString());
                    this.onWarning(" تم حذف البيان بنجاح ", "ناجح");
                    fill_storage_table();
                } else if (jTable_storage.getSelectedRowCount() > 1) {
                    for (int row : jTable_storage.getSelectedRows()) {
                        storageController.removeBag(jTable_storage.getModel().getValueAt(row, 4).toString());
                    }
                    fill_storage_table();
                    this.onWarning("تم حذف البيــانات بنجاح", "ناجح");
                } else {
                    this.onWarning("برجاء أختيار بيان من الجدول أولا", "إنتبه");
                }
            }
        } catch (DatabaseException | BusinessException ex) {
            this.onError(ex);
        }
    }//GEN-LAST:event_jButton_storage_delDataActionPerformed

    private void jComboBox_storage_productsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_storage_productsItemStateChanged
        evt.getID();
        try {
            jTextField_storage_lot.setText("");

            if (jComboBox_storage_products.hasFocus()) {
                jCheckBox_storage_FreezeConeWeightChange.setSelected(false);
                jCheckBox_storage_ignoreLimits.setSelected(false);
                jCheckBox_storage_freezeConeNumber.setSelected(false);
                jCheckBox_storage_FreezeEmptyBagWight.setSelected(false);
                jCheckBox_storage_MarkBag.setSelected(false);
                fill_storage_table();
            }
            if (jTable_storage.getRowCount() != 0) {
                jTextField_storage_lot.setText((String) jTable_storage.getValueAt(0, 2));
                if (!jTable_storage.getValueAt(0, 5).equals("٢٠")) {
                    jTextField_storage_palletNumber.setText((String) jTable_storage.getValueAt(0, 3));
                } else {
                    jTextField_storage_palletNumber.setText(ArabicDigits.toArabicDigits(ArabicDigits.parseInt((String) jTable_storage.getValueAt(0, 3)) + 1));
                }
                calc_pallet_weight();
            }
        } catch (DatabaseException | BusinessException ex) {
            this.onError(ex);
        }
    }//GEN-LAST:event_jComboBox_storage_productsItemStateChanged

    private void jTable_storageKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable_storageKeyTyped
        if (evt.getKeyChar() == KeyEvent.VK_DELETE && jTable_storage.hasFocus()) {
            jButton_storage_delData.doClick();
        }
    }//GEN-LAST:event_jTable_storageKeyTyped

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        evt.getID();
        try {
            Config.save();
            System.exit(NORMAL);
        } catch (BusinessException ex) {
            this.onError(ex);
        }
    }//GEN-LAST:event_formWindowClosing

    private void SingleEditWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_SingleEditWindowClosing
        evt.getID();
        this.setEnabled(true);
    }//GEN-LAST:event_SingleEditWindowClosing

    private void jButton_E_EditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_E_EditActionPerformed
        evt.getID();
        try {
            if (!jTextField_E_Wight.getText().isBlank() && !jTextField_E_PaltNum.getText().isBlank()
                    && !jTextField_E_ConNum.getText().isBlank() && !jTextField_E_lot.getText().isBlank()) {

                storageController.updateStorage(Integer.parseInt(jTable_storage.getModel().getValueAt(jTable_storage.getSelectedRow(), 4).toString()),
                        jComboBox_E_proName.getSelectedItem().toString(), jTextField_E_TotWight.getText(),
                        jTextField_E_Wight.getText(), jTextField_E_lot.getText(), jTextField_E_ConNum.getText(),
                        jTextField_E_PaltNum.getText(), jCheckBox_E_Mark.isSelected(), "0.000");
                this.setEnabled(true);
                fill_storage_table();
                SingleEdit.dispose();
                this.onWarning(" تم تعديل البيانات بنجاح  ", "إنتبه");
            } else {
                this.onWarning("برجاء ادخال البيانات صحيحه ", "إنتبه");
            }
        } catch (DatabaseException | BusinessException ex) {
            this.onError(ex);
        }
    }//GEN-LAST:event_jButton_E_EditActionPerformed

    private void jTable_storageMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_storageMouseReleased
        evt.getID();
        try {
            if (evt.getClickCount() == 3 && jTable_storage.getSelectedRowCount() > 0) {
                if (jTable_storage.getSelectedRowCount() == 1) {
                    SingleEdit.setVisible(true);
                    SingleEdit.setSize(780, 400);
                    this.setEnabled(false);
                    Bag bag = storageController.getBagById(
                            Integer.parseInt(jTable_storage.getModel().getValueAt(jTable_storage.getSelectedRow(), 4).toString()));
                    Product product = productController.getProduct(bag.getPro_id());
                    jCheckBox_E_O_Mark.setSelected(bag.isUsed());
                    jCheckBox_E_Mark.setSelected(bag.isUsed());
                    jTextField_E_O_TotWight.setText(ArabicDigits.toArabicDigits(bag.getTot_wight()));
                    jTextField_E_TotWight.setText(ArabicDigits.toArabicDigits(bag.getTot_wight()));
                    jComboBox_E_O_proName.setSelectedItem(product);
                    jComboBox_E_proName.setSelectedItem(product);
                    jTextField_E_O_lot.setText(ArabicDigits.toArabicDigits(bag.getLot()));
                    jTextField_E_lot.setText(ArabicDigits.toArabicDigits(bag.getLot()));
                    jTextField_E_O_ConNum.setText(ArabicDigits.toArabicDigits(bag.getNum_of_con()));
                    jTextField_E_ConNum.setText(ArabicDigits.toArabicDigits(bag.getNum_of_con()));
                    jTextField_E_O_PaltNum.setText(ArabicDigits.toArabicDigits(bag.getPallet_numb()));
                    jTextField_E_PaltNum.setText(ArabicDigits.toArabicDigits(bag.getPallet_numb()));
                    jTextField_E_O_Wight.setText(ArabicDigits.toArabicDigits(bag.getWeight()));
                    jTextField_E_Wight.setText(ArabicDigits.toArabicDigits(bag.getWeight()));
                    jTextField_E_Color.setText(product.getColor());

                } else if (jTable_storage.getSelectedRowCount() > 1) {
                    MultiEdit.setVisible(true);
                    this.setEnabled(false);
                    jComboBox_ME_type.setSelectedItem(jComboBox_storage_products.getSelectedItem());
                    jTextField_ME_PaltNum
                            .setText(jTable_storage.getModel().getValueAt(jTable_storage.getSelectedRow(), 3).toString());
                    jTextField_ME_lot
                            .setText(jTable_storage.getModel().getValueAt(jTable_storage.getSelectedRow(), 2).toString());
                    jCheckBox_ME_MarkBag.setSelected((boolean) jTable_storage.getModel()
                            .getValueAt(jTable_storage.getSelectedRow(), 6));
                }
            }
        } catch (DatabaseException | BusinessException ex) {
            this.onError(ex);
        }
    }//GEN-LAST:event_jTable_storageMouseReleased

    private void jButton_storage_ClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_storage_ClearActionPerformed
        evt.getID();
        clearStorageForm();
    }//GEN-LAST:event_jButton_storage_ClearActionPerformed

    private void jButton_E_printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_E_printActionPerformed
        evt.getID();
//        PreparePrintingPanel(new Ticket(((Product) jComboBox_E_proName.getSelectedItem()).getName(),
//                jTextField_E_PaltNum.getText(),
//                ((Product) jComboBox_E_proName.getSelectedItem()).getColor(),
//                jTextField_E_lot.getText(),
//                jTextField_E_ConNum.getText(),
//                jTextField_E_TotWight.getText(),
//                jTextField_E_Wight.getText()), jCheckBox_E_P.isSelected());
    }//GEN-LAST:event_jButton_E_printActionPerformed

    private void jButton_ME_EditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ME_EditActionPerformed
        evt.getID();
        try {
            if (!jTextField_ME_PaltNum.getText().isBlank() || !jTextField_ME_lot.getText().isBlank()) {
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
    }//GEN-LAST:event_jButton_ME_EditActionPerformed

    private void MultiEditWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_MultiEditWindowClosing
        evt.getID();
        this.setEnabled(true);
    }//GEN-LAST:event_MultiEditWindowClosing

    private void jButton_storage_RePrintLastTicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_storage_RePrintLastTicketActionPerformed
        evt.getID();
        try {
            // printerManager.printPanelToImage(jPanel_print);
            this.jTextField_storage_coneNumber.requestFocusInWindow();
        } catch (BusinessException ex) {
            this.onError(ex);
        }
    }//GEN-LAST:event_jButton_storage_RePrintLastTicketActionPerformed

    private void setupKeyBindings() {
        InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = rootPane.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("F1"), "openWznPanel");
        actionMap.put("openWznPanel", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                navPanel.jButton_Mizan_opener.doClick();
                jTextField_storage_TotalWeight.requestFocusInWindow();
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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///                                                                                                              ///
    ///                                       Form Extractors                                                        ///
    ///                                                                                                              ///
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private Bag buildRequestFromUI() {
        Bag req;
        try {
            if (jComboBox_storage_products.getSelectedIndex() == -1) {
                throw new BusinessException("يجب إختيار صنف");
            }
            req = new Bag(((Product) jComboBox_storage_products.getSelectedItem()).getId(),
                    ArabicDigits.parseInt(requireText(jTextField_storage_coneNumber, "عدد الكون")),
                    ArabicDigits.parseInt(requireText(jTextField_storage_palletNumber, "رقم البالتة")),
                    ArabicDigits.parseDouble(requireText(jTextField_storage_TotalWeight, "الوزن")),
                    ArabicDigits.parseDouble(requireText(jTextField_storage_NetWeight, "الوزن الصافي")),
                    ArabicDigits.parseInt(requireText(jTextField_storage_EmptyBagWeight, "الوزن الفارغ")) / 100,
                    ArabicDigits.normalizeForParsing(requireText(jTextField_storage_lot, "اللوط")),
                    jCheckBox_storage_MarkBag.isSelected()
            );

        } catch (BusinessException e) {
            this.onError(e);
            return null;
        }

        return req;
    }

    private String requireText(JTextField field, String name) {
        String value = field.getText().trim();
        if (value.isEmpty()) {
            throw new BusinessException("برجاء إدخال قيمة صحيحة " + name);
        }
        return value;
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


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///                                                                                                              ///
    ///                                               Helpers                                                        ///
    ///                                                                                                              ///
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    

    private void populateCombos() throws DatabaseException {
        List<Product> pros = productController.getAvailableProductsLike("");
        utils.fillComboBoxWihProducts(jComboBox_storage_products, pros);
        utils.fillComboBoxWihProducts(jComboBox_E_O_proName, pros);
        utils.fillComboBoxWihProducts(jComboBox_E_proName, pros);
        utils.fillComboBoxWihProducts(jComboBox_ME_type, pros);
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
    private javax.swing.JFrame MultiEdit;
    private javax.swing.JFrame SingleEdit;
    private javax.swing.JButton jButton_E_Edit;
    private javax.swing.JButton jButton_E_print;
    private javax.swing.JButton jButton_ME_Edit;
    private javax.swing.JButton jButton_storage_Clear;
    private javax.swing.JButton jButton_storage_RePrintLastTicket;
    private javax.swing.JButton jButton_storage_addData;
    private javax.swing.JButton jButton_storage_delData;
    private javax.swing.JCheckBox jCheckBox_E_Mark;
    private javax.swing.JCheckBox jCheckBox_E_O_Mark;
    private javax.swing.JCheckBox jCheckBox_E_P;
    private javax.swing.JCheckBox jCheckBox_ME_MarkBag;
    private javax.swing.JCheckBox jCheckBox_storage_Box;
    private javax.swing.JCheckBox jCheckBox_storage_FreezeConeWeightChange;
    private javax.swing.JCheckBox jCheckBox_storage_FreezeEmptyBagWight;
    private javax.swing.JCheckBox jCheckBox_storage_MarkBag;
    private javax.swing.JCheckBox jCheckBox_storage_freezeConeNumber;
    private javax.swing.JCheckBox jCheckBox_storage_ignoreLimits;
    private javax.swing.JCheckBox jCheckBox_storage_printLTicket;
    private javax.swing.JComboBox<Product> jComboBox_E_O_proName;
    private javax.swing.JComboBox<Product> jComboBox_E_proName;
    private javax.swing.JComboBox<Product> jComboBox_ME_type;
    private javax.swing.JComboBox<Product> jComboBox_storage_products;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JProgressBar jProgressBar_storage_pallet;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTable jTable_storage;
    private javax.swing.JTextField jTextField_E_Color;
    private javax.swing.JTextField jTextField_E_ConNum;
    private javax.swing.JTextField jTextField_E_O_ConNum;
    private javax.swing.JTextField jTextField_E_O_PaltNum;
    private javax.swing.JTextField jTextField_E_O_TotWight;
    private javax.swing.JTextField jTextField_E_O_Wight;
    private javax.swing.JTextField jTextField_E_O_lot;
    private javax.swing.JTextField jTextField_E_PaltNum;
    private javax.swing.JTextField jTextField_E_TotWight;
    private javax.swing.JTextField jTextField_E_Wight;
    private javax.swing.JTextField jTextField_E_lot;
    private javax.swing.JTextField jTextField_ME_PaltNum;
    private javax.swing.JTextField jTextField_ME_lot;
    private javax.swing.JTextField jTextField_inputExeption;
    private javax.swing.JTextField jTextField_storage_Color;
    private javax.swing.JTextField jTextField_storage_EmptyBagWeight;
    private javax.swing.JTextField jTextField_storage_EmptyConeWeight;
    private javax.swing.JTextField jTextField_storage_NetWeight;
    private javax.swing.JTextField jTextField_storage_PalletWeight;
    private javax.swing.JTextField jTextField_storage_SearchProducts;
    private javax.swing.JTextField jTextField_storage_TotalWeight;
    private javax.swing.JTextField jTextField_storage_coneNumber;
    private javax.swing.JTextField jTextField_storage_lot;
    private javax.swing.JTextField jTextField_storage_palletNumber;
    private javax.swing.JPanel left_panel;
    private javax.swing.JPanel storage_panel;
    // End of variables declaration//GEN-END:variables
}

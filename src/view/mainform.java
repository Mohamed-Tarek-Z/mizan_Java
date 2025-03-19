package view;

import controller.*;
import dao.*;
import model.*;
import utils.*;
import exceptions.*;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellCopyPolicy;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//import org.vosk.LibVosk;
//import org.vosk.LogLevel;
public class mainform extends javax.swing.JFrame {

    private final sqlcon opj;
    private final ProductController productController;
    private final StorageController storageController;
    private final ExportController exportController;
    private final ClientController clientController;
    private final OrderController orderController;
    private final MachineController machineController;

    private utils util;
    private final ExcelManager excelManager;
    private final PrinterManager printerManager;

    private int pro_Table_SelectedID = 0;

    private short tick10x10, tick2x2;
    private int BagMax = 2, repDiff;
    private final String Version = "V 1.1 MVC";
    private String ticketPrinterName, qrPrinterName;

    private final JButton jButton_Settings = new javax.swing.JButton();

    private long lastInputTime;
    private final StringBuilder mizanInputBuilder = new StringBuilder();
    private String savedText = "";
    private char firstChar;
    private boolean enterFromMizan = false;

    public mainform(boolean admin, sqlcon dbConnection) throws DatabaseException, BusinessException {
        initComponents();
        this.opj = dbConnection;
        this.util = new utils();
        this.excelManager = new ExcelManager();
        this.printerManager = new PrinterManager();
        this.productController = new ProductController(new ProductDAO(dbConnection));
        this.storageController = new StorageController(new StorageDAO(dbConnection), new ProductDAO(dbConnection));
        this.exportController = new ExportController(new ExportDAO(dbConnection), new StorageDAO(dbConnection), new OrderDAO(dbConnection));
        this.clientController = new ClientController(new ClientDAO(dbConnection));
        this.orderController = new OrderController(new OrderDAO(dbConnection));
        this.machineController = new MachineController(new MachineDAO(dbConnection), new ProductDAO(dbConnection));

        this.setDefaultCloseOperation(mainform.DO_NOTHING_ON_CLOSE);
        if (!admin) {
            jButton_addPro_opener.setEnabled(false);
            jButton_Ezn_opener.setEnabled(false);
            jButton_Outs_opener.setEnabled(false);
        } else {
            jButton_Settings.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jButton_Settings.setText("Setting");
            jButton_Settings.addActionListener((java.awt.event.ActionEvent evt) -> {
                try {
                    jButton_SettingsActionPerformed();
                } catch (IOException ex) {
                    Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
                } catch (BusinessException ex) {
                    JOptionPane.showMessageDialog(this, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
                }
            });
            right_panel_menu.add(jButton_Settings, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 520, 110, 40));
        }

        this.jTable_storage.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.jTable_storage.getTableHeader().setFont(new Font("Tahoma", 1, 16));
        this.jTable_stock.getTableHeader().setFont(new Font("Tahoma", 1, 16));
        this.jTable_rep_preview.getTableHeader().setFont(new Font("Tahoma", 1, 16));
        this.jTable_rep_select.getTableHeader().setFont(new Font("Tahoma", 1, 16));
        this.jTable_statis.getTableHeader().setFont(new Font("Tahoma", 1, 16));

        populateCombos();
        readConfig();

//        LibVosk.setLogLevel(LogLevel.WARNINGS);
//        startRecognition();
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
        jCheckBox_E_QR = new javax.swing.JCheckBox();
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
        right_panel_menu = new javax.swing.JPanel();
        jButton_Mizan_opener = new javax.swing.JButton();
        jButton_addPro_opener = new javax.swing.JButton();
        jButton_Ezn_opener = new javax.swing.JButton();
        jButton_DoBack = new javax.swing.JButton();
        jButton_Stock_opener = new javax.swing.JButton();
        jButton_Statics_opener = new javax.swing.JButton();
        jButton_Outs_opener = new javax.swing.JButton();
        jButton_Emp_opener = new javax.swing.JButton();
        jLabel_version = new javax.swing.JLabel();
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
        jButton_add_data = new javax.swing.JButton();
        jButton_del_data = new javax.swing.JButton();
        jComboBox_pro_in_storage = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jTextField_lot = new javax.swing.JTextField();
        jTextField_weight = new javax.swing.JTextField();
        jTextField_bag_weight = new javax.swing.JTextField();
        jTextField_num_of_con = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField_pallet_num = new javax.swing.JTextField();
        jTextField_weight_of_con = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField_net_weight = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        pallet_weight = new javax.swing.JTextField();
        jCheckBox_M_Markpage = new javax.swing.JCheckBox();
        jButton_clear = new javax.swing.JButton();
        jCheckBox_print = new javax.swing.JCheckBox();
        jTextField_Color = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jCheckBox_QR = new javax.swing.JCheckBox();
        jCheckBox_Box = new javax.swing.JCheckBox();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jButton_reprintLastTicket = new javax.swing.JButton();
        jProgressBar_pallet = new javax.swing.JProgressBar();
        jCheckBox_freeze_cone = new javax.swing.JCheckBox();
        jCheckBox_freeze_wight = new javax.swing.JCheckBox();
        jCheckBox_ConeWeightChange = new javax.swing.JCheckBox();
        jCheckBox_ignore_limits = new javax.swing.JCheckBox();
        jSeparator7 = new javax.swing.JSeparator();
        jTextField_Search_pros = new javax.swing.JTextField();
        makePermit = new javax.swing.JPanel();
        jComboBox_rep_Pros = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField_rep_numOfBag = new javax.swing.JTextField();
        jButton_rep_printRep = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable_rep_preview = new javax.swing.JTable()
        {
            @Override

            public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int columnIndex) {
                Component componenet = super.prepareRenderer(renderer, rowIndex, columnIndex);

                boolean value = (boolean) getModel().getValueAt(rowIndex, 4);

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
        jTextField_rep_clientName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable_rep_select = new javax.swing.JTable()
        {
            @Override

            public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int columnIndex) {
                Component componenet = super.prepareRenderer(renderer, rowIndex, columnIndex);

                boolean value = (boolean) getModel().getValueAt(rowIndex, 4);

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
        jTextField_rep_totweight = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel_Order_num = new javax.swing.JLabel();
        jCheckBox_rep_2n1 = new javax.swing.JCheckBox();
        jComboBox_rep_palletsNrep = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        jButton_reps_clear = new javax.swing.JButton();
        jCheckBox_rep_wzn = new javax.swing.JCheckBox();
        products_panel = new javax.swing.JPanel();
        jTextField_pro_name = new javax.swing.JTextField();
        jButton_add_pro = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_pro = new javax.swing.JTable();
        jButton_del_pro = new javax.swing.JButton();
        jTextField_Pros_conWight = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jTextField_Pros_color = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jCheckBox_Pros_IsBox = new javax.swing.JCheckBox();
        stock_panel = new javax.swing.JPanel();
        jComboBox_stock_Pros = new javax.swing.JComboBox<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable_stock = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        jButton_stock_createExcl = new javax.swing.JButton();
        statistics_panel = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable_statis = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jDateChooser_statis_fromDate = new com.toedter.calendar.JDateChooser();
        jDateChooser_statis_toDate = new com.toedter.calendar.JDateChooser();
        jLabel20 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jTextField_statis_tot = new javax.swing.JTextField();
        jButton_statistics_search = new javax.swing.JButton();
        jButton_statis_createExcl = new javax.swing.JButton();
        showPermit_panel = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable_yumia = new javax.swing.JTable();
        jDateChooser_yum_fromDate = new com.toedter.calendar.JDateChooser();
        jDateChooser_yum_ToDate = new com.toedter.calendar.JDateChooser();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jButton_youm_search = new javax.swing.JButton();
        jButton_youm_refund = new javax.swing.JButton();
        jTextField_youm_ClientFilter = new javax.swing.JTextField();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable_youm_clinets = new javax.swing.JTable();
        jButton_youm_getClients = new javax.swing.JButton();
        jCheckBox_youm_old = new javax.swing.JCheckBox();
        jButton_youm_createExcel = new javax.swing.JButton();
        pause_panel = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTextArea_emp = new javax.swing.JTextArea();
        jLabel38 = new javax.swing.JLabel();
        jTabbedPane_settings = new javax.swing.JTabbedPane();
        jTab_set_Counter = new javax.swing.JPanel();
        jButton_Reset_TicketCount2x2 = new javax.swing.JButton();
        jButton_Reset_TicketCount10x10 = new javax.swing.JButton();
        jLabel_Ticket10x10Counter = new javax.swing.JLabel();
        jLabel_Ticket2x2Counter = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jTab_set_Printing = new javax.swing.JPanel();
        jButton_set_changePos = new javax.swing.JButton();
        jCheckBox_set_printExcel = new javax.swing.JCheckBox();
        jSeparator14 = new javax.swing.JSeparator();
        jLabel46 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jButton_set_printValueToCenter = new javax.swing.JButton();
        jTab_set_order = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jTextField_setting_repsDiff = new javax.swing.JTextField();
        jPanel_Machines = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable_machines = new javax.swing.JTable();
        jTextField_mach_MName = new javax.swing.JTextField();
        jComboBox_mach_pros = new javax.swing.JComboBox<>();
        jTextField_mach_lot = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jButton_mach_addMach = new javax.swing.JButton();
        jButton_mach_Delete = new javax.swing.JButton();
        jTab_set_about = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jLabel_ip = new javax.swing.JLabel();
        jButton_set_reloadSettingFile = new javax.swing.JButton();
        jPanel_print = new javax.swing.JPanel();
        jLabel_print_header = new javax.swing.JLabel();
        jLabel_print_ValPallet = new javax.swing.JLabel()
        {
            @Override
            protected void paintComponent(Graphics g) {
                adjustFontSize(g);
                super.paintComponent(g);
            }

            private void adjustFontSize(Graphics g) {
                if (getText() == null || getText().isEmpty()) {
                    return;
                }

                int labelWidth = getWidth();
                int labelHeight = getHeight();

                if (labelWidth <= 0 || labelHeight <= 0) {
                    return;
                }

                Graphics2D g2d = (Graphics2D) g;
                Font font = getFont();
                FontMetrics fm;
                int fontSize = font.getSize();
                int textWidth;
                int textHeight;

                do {
                    font = font.deriveFont((float) fontSize);
                    fm = g2d.getFontMetrics(font);
                    textWidth = fm.stringWidth(getText());
                    textHeight = fm.getHeight();
                    fontSize--;
                } while (textWidth > labelWidth && fontSize > 5); // Stop at minimum font size of 5

                setFont(font);
            }
        };
        jLabel_print_pallet = new javax.swing.JLabel();
        jLabel_print_ValColor = new javax.swing.JLabel()
        {
            @Override
            protected void paintComponent(Graphics g) {
                adjustFontSize(g);
                super.paintComponent(g);
            }

            private void adjustFontSize(Graphics g) {
                if (getText() == null || getText().isEmpty()) {
                    return;
                }

                int labelWidth = getWidth();
                int labelHeight = getHeight();

                if (labelWidth <= 0 || labelHeight <= 0) {
                    return;
                }

                Graphics2D g2d = (Graphics2D) g;
                Font font = getFont();
                FontMetrics fm;
                int fontSize = font.getSize();
                int textWidth;
                int textHeight;

                do {
                    font = font.deriveFont((float) fontSize);
                    fm = g2d.getFontMetrics(font);
                    textWidth = fm.stringWidth(getText());
                    textHeight = fm.getHeight();
                    fontSize--;
                } while (textWidth > labelWidth && fontSize > 5); // Stop at minimum font size of 5

                setFont(font);
            }
        };
        jLabel_print_color = new javax.swing.JLabel();
        jLabel_print_ValType = new javax.swing.JLabel()
        {
            @Override
            protected void paintComponent(Graphics g) {
                adjustFontSize(g);
                super.paintComponent(g);
            }

            private void adjustFontSize(Graphics g) {
                if (getText() == null || getText().isEmpty()) {
                    return;
                }

                int labelWidth = getWidth();
                int labelHeight = getHeight();

                if (labelWidth <= 0 || labelHeight <= 0) {
                    return;
                }

                Graphics2D g2d = (Graphics2D) g;
                Font font = getFont();
                FontMetrics fm;
                int fontSize = font.getSize();
                int textWidth;
                int textHeight;

                do {
                    font = font.deriveFont((float) fontSize);
                    fm = g2d.getFontMetrics(font);
                    textWidth = fm.stringWidth(getText());
                    textHeight = fm.getHeight();
                    fontSize--;
                } while (textWidth > labelWidth && fontSize > 5); // Stop at minimum font size of 5

                setFont(font);
            }
        };
        jLabel_print_type = new javax.swing.JLabel();
        jLabel_print_ValLot = new javax.swing.JLabel()
        {
            @Override
            protected void paintComponent(Graphics g) {
                adjustFontSize(g);
                super.paintComponent(g);
            }

            private void adjustFontSize(Graphics g) {
                if (getText() == null || getText().isEmpty()) {
                    return;
                }

                int labelWidth = getWidth();
                int labelHeight = getHeight();

                if (labelWidth <= 0 || labelHeight <= 0) {
                    return;
                }

                Graphics2D g2d = (Graphics2D) g;
                Font font = getFont();
                FontMetrics fm;
                int fontSize = font.getSize();
                int textWidth;
                int textHeight;

                do {
                    font = font.deriveFont((float) fontSize);
                    fm = g2d.getFontMetrics(font);
                    textWidth = fm.stringWidth(getText());
                    textHeight = fm.getHeight();
                    fontSize--;
                } while (textWidth > labelWidth && fontSize > 5); // Stop at minimum font size of 5

                setFont(font);
            }
        };
        jLabel_print_lot = new javax.swing.JLabel();
        jLabel_print_ValNCone = new javax.swing.JLabel()
        {
            @Override
            protected void paintComponent(Graphics g) {
                adjustFontSize(g);
                super.paintComponent(g);
            }

            private void adjustFontSize(Graphics g) {
                if (getText() == null || getText().isEmpty()) {
                    return;
                }

                int labelWidth = getWidth();
                int labelHeight = getHeight();

                if (labelWidth <= 0 || labelHeight <= 0) {
                    return;
                }

                Graphics2D g2d = (Graphics2D) g;
                Font font = getFont();
                FontMetrics fm;
                int fontSize = font.getSize();
                int textWidth;
                int textHeight;

                do {
                    font = font.deriveFont((float) fontSize);
                    fm = g2d.getFontMetrics(font);
                    textWidth = fm.stringWidth(getText());
                    textHeight = fm.getHeight();
                    fontSize--;
                } while (textWidth > labelWidth && fontSize > 5); // Stop at minimum font size of 5

                setFont(font);
            }
        };
        jLabel_print_NCone = new javax.swing.JLabel();
        jLabel_print_ValTotalWeight = new javax.swing.JLabel()
        {
            @Override
            protected void paintComponent(Graphics g) {
                adjustFontSize(g);
                super.paintComponent(g);
            }

            private void adjustFontSize(Graphics g) {
                if (getText() == null || getText().isEmpty()) {
                    return;
                }

                int labelWidth = getWidth();
                int labelHeight = getHeight();

                if (labelWidth <= 0 || labelHeight <= 0) {
                    return;
                }

                Graphics2D g2d = (Graphics2D) g;
                Font font = getFont();
                FontMetrics fm;
                int fontSize = font.getSize();
                int textWidth;
                int textHeight;

                do {
                    font = font.deriveFont((float) fontSize);
                    fm = g2d.getFontMetrics(font);
                    textWidth = fm.stringWidth(getText());
                    textHeight = fm.getHeight();
                    fontSize--;
                } while (textWidth > labelWidth && fontSize > 5); // Stop at minimum font size of 5

                setFont(font);
            }
        };
        jLabel_print_TotalWeight = new javax.swing.JLabel();
        jLabel_print_ValNetWeight = new javax.swing.JLabel()
        {
            @Override
            protected void paintComponent(Graphics g) {
                adjustFontSize(g);
                super.paintComponent(g);
            }

            private void adjustFontSize(Graphics g) {
                if (getText() == null || getText().isEmpty()) {
                    return;
                }

                int labelWidth = getWidth();
                int labelHeight = getHeight();

                if (labelWidth <= 0 || labelHeight <= 0) {
                    return;
                }

                Graphics2D g2d = (Graphics2D) g;
                Font font = getFont();
                FontMetrics fm;
                int fontSize = font.getSize();
                int textWidth;
                int textHeight;

                do {
                    font = font.deriveFont((float) fontSize);
                    fm = g2d.getFontMetrics(font);
                    textWidth = fm.stringWidth(getText());
                    textHeight = fm.getHeight();
                    fontSize--;
                } while (textWidth > labelWidth && fontSize > 5); // Stop at minimum font size of 5

                setFont(font);
            }
        };
        jLabel_print_NetWeight = new javax.swing.JLabel();
        jLabel_print_footer = new javax.swing.JLabel();
        jLabel_print_number = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator13 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator12 = new javax.swing.JSeparator();

        jFileChooser1.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
        jFileChooser1.setCurrentDirectory(new java.io.File("F:\\"));
            jFileChooser1.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);
            jFileChooser1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

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
            jTextField_E_lot.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTextField_E_lotKeyTyped(evt);
                }
            });
            SingleEdit.getContentPane().add(jTextField_E_lot, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 220, 90, -1));

            jTextField_E_ConNum.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jTextField_E_ConNum.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            jTextField_E_ConNum.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTextField_E_ConNumKeyTyped(evt);
                }
            });
            SingleEdit.getContentPane().add(jTextField_E_ConNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 220, 90, -1));

            jTextField_E_PaltNum.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jTextField_E_PaltNum.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            jTextField_E_PaltNum.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTextField_E_PaltNumKeyTyped(evt);
                }
            });
            SingleEdit.getContentPane().add(jTextField_E_PaltNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 220, 90, -1));

            jTextField_E_Wight.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jTextField_E_Wight.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            jTextField_E_Wight.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTextField_E_WightKeyTyped(evt);
                }
            });
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
            jTextField_E_TotWight.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTextField_E_TotWightKeyTyped(evt);
                }
            });
            SingleEdit.getContentPane().add(jTextField_E_TotWight, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, 90, -1));

            jLabel43.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jLabel43.setForeground(new java.awt.Color(255, 0, 0));
            jLabel43.setText("الوزن قائم");
            SingleEdit.getContentPane().add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, 80, 20));

            jCheckBox_E_QR.setText("QR");
            SingleEdit.getContentPane().add(jCheckBox_E_QR, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, -1));

            jCheckBox_E_P.setText("print");
            SingleEdit.getContentPane().add(jCheckBox_E_P, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, -1, -1));

            jTextField_E_Color.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jTextField_E_Color.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTextField_E_ColorKeyTyped(evt);
                }
            });
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
            jTextField_ME_lot.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTextField_ME_lotKeyTyped(evt);
                }
            });
            MultiEdit.getContentPane().add(jTextField_ME_lot, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 90, -1));

            jTextField_ME_PaltNum.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jTextField_ME_PaltNum.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            jTextField_ME_PaltNum.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTextField_ME_PaltNumKeyTyped(evt);
                }
            });
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

            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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

            right_panel_menu.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            right_panel_menu.setMaximumSize(new java.awt.Dimension(110, 650));
            right_panel_menu.setMinimumSize(new java.awt.Dimension(110, 650));
            right_panel_menu.setPreferredSize(new java.awt.Dimension(110, 650));
            right_panel_menu.setRequestFocusEnabled(false);
            right_panel_menu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            jButton_Mizan_opener.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jButton_Mizan_opener.setText("ميزان");
            jButton_Mizan_opener.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_Mizan_openerActionPerformed(evt);
                }
            });
            right_panel_menu.add(jButton_Mizan_opener, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 110, 40));

            jButton_addPro_opener.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jButton_addPro_opener.setText("إضافه صنف");
            jButton_addPro_opener.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_addPro_openerActionPerformed(evt);
                }
            });
            right_panel_menu.add(jButton_addPro_opener, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 110, 40));

            jButton_Ezn_opener.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jButton_Ezn_opener.setText("إذن غزل");
            jButton_Ezn_opener.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_Ezn_openerActionPerformed(evt);
                }
            });
            right_panel_menu.add(jButton_Ezn_opener, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 110, 40));

            jButton_DoBack.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jButton_DoBack.setText("باك أب");
            jButton_DoBack.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_DoBackActionPerformed(evt);
                }
            });
            right_panel_menu.add(jButton_DoBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 110, 40));

            jButton_Stock_opener.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jButton_Stock_opener.setText("رصيد");
            jButton_Stock_opener.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_Stock_openerActionPerformed(evt);
                }
            });
            right_panel_menu.add(jButton_Stock_opener, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 110, 40));

            jButton_Statics_opener.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jButton_Statics_opener.setText("إحصائيات");
            jButton_Statics_opener.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_Statics_openerActionPerformed(evt);
                }
            });
            right_panel_menu.add(jButton_Statics_opener, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 110, 40));

            jButton_Outs_opener.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jButton_Outs_opener.setText("يوميه");
            jButton_Outs_opener.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_Outs_openerActionPerformed(evt);
                }
            });
            right_panel_menu.add(jButton_Outs_opener, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 110, 40));

            jButton_Emp_opener.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jButton_Emp_opener.setText("أي");
            jButton_Emp_opener.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_Emp_openerActionPerformed(evt);
                }
            });
            right_panel_menu.add(jButton_Emp_opener, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, 110, 40));

            jLabel_version.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jLabel_version.setText(Version);
            jLabel_version.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 51, 51), 3, true));
            jLabel_version.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
            jLabel_version.setFocusable(false);
            jLabel_version.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel_version.setRequestFocusEnabled(false);
            jLabel_version.setVerifyInputWhenFocusTarget(false);
            right_panel_menu.add(jLabel_version, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 596, 110, 40));
            jLabel_version.getAccessibleContext().setAccessibleName("Right_version");

            jSplitPane1.setRightComponent(right_panel_menu);

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

            jButton_add_data.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
            jButton_add_data.setText("إضافة");
            jButton_add_data.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_add_dataActionPerformed(evt);
                }
            });
            storage_panel.add(jButton_add_data, new org.netbeans.lib.awtextra.AbsoluteConstraints(745, 590, 90, 50));

            jButton_del_data.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
            jButton_del_data.setText("حذف");
            jButton_del_data.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_del_dataActionPerformed(evt);
                }
            });
            storage_panel.add(jButton_del_data, new org.netbeans.lib.awtextra.AbsoluteConstraints(605, 590, 80, 50));

            jComboBox_pro_in_storage.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
            jComboBox_pro_in_storage.addItemListener(new java.awt.event.ItemListener() {
                public void itemStateChanged(java.awt.event.ItemEvent evt) {
                    jComboBox_pro_in_storageItemStateChanged(evt);
                }
            });
            storage_panel.add(jComboBox_pro_in_storage, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 100, 210, 30));

            jLabel6.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
            jLabel6.setText("الصنف");
            storage_panel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 70, -1, 20));

            jTextField_lot.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
            jTextField_lot.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
            jTextField_lot.setMaximumSize(new java.awt.Dimension(7, 38));
            jTextField_lot.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTextField_lotKeyTyped(evt);
                }
            });
            storage_panel.add(jTextField_lot, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 140, 120, 36));

            jTextField_weight.setBackground(new java.awt.Color(255, 204, 204));
            jTextField_weight.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
            jTextField_weight.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
            jTextField_weight.setMaximumSize(new java.awt.Dimension(7, 38));
            jTextField_weight.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    jTextField_weightFocusGained(evt);
                }
            });
            jTextField_weight.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTextField_weightKeyTyped(evt);
                }
            });
            storage_panel.add(jTextField_weight, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 340, 120, -1));

            jTextField_bag_weight.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
            jTextField_bag_weight.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
            jTextField_bag_weight.setMaximumSize(new java.awt.Dimension(7, 38));
            jTextField_bag_weight.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    jTextField_bag_weightFocusGained(evt);
                }
            });
            jTextField_bag_weight.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTextField_bag_weightKeyTyped(evt);
                }
            });
            storage_panel.add(jTextField_bag_weight, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 290, 120, -1));

            jTextField_num_of_con.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
            jTextField_num_of_con.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
            jTextField_num_of_con.setMaximumSize(new java.awt.Dimension(7, 38));
            jTextField_num_of_con.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    jTextField_num_of_conFocusGained(evt);
                }
            });
            jTextField_num_of_con.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTextField_num_of_conKeyTyped(evt);
                }
            });
            storage_panel.add(jTextField_num_of_con, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 240, 120, -1));

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

            jTextField_pallet_num.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
            jTextField_pallet_num.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
            jTextField_pallet_num.setMaximumSize(new java.awt.Dimension(7, 38));
            jTextField_pallet_num.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    jTextField_pallet_numFocusGained(evt);
                }
            });
            jTextField_pallet_num.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTextField_pallet_numKeyTyped(evt);
                }
            });
            storage_panel.add(jTextField_pallet_num, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 180, 120, -1));

            jTextField_weight_of_con.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
            jTextField_weight_of_con.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
            jTextField_weight_of_con.setMaximumSize(new java.awt.Dimension(7, 38));
            jTextField_weight_of_con.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTextField_weight_of_conKeyTyped(evt);
                }
            });
            storage_panel.add(jTextField_weight_of_con, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 440, 120, -1));

            jLabel11.setFont(new java.awt.Font("sansserif", 1, 16)); // NOI18N
            jLabel11.setText("فارغ الشيكاره");
            jLabel11.setMaximumSize(new java.awt.Dimension(82, 24));
            jLabel11.setMinimumSize(new java.awt.Dimension(82, 24));
            jLabel11.setPreferredSize(new java.awt.Dimension(82, 24));
            storage_panel.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(735, 290, -1, 30));

            jLabel12.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
            jLabel12.setText("الوزن الصافي");
            storage_panel.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(735, 390, -1, 30));

            jTextField_net_weight.setEditable(false);
            jTextField_net_weight.setBackground(new java.awt.Color(204, 255, 204));
            jTextField_net_weight.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
            jTextField_net_weight.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
            jTextField_net_weight.setMaximumSize(new java.awt.Dimension(7, 38));
            jTextField_net_weight.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTextField_net_weightKeyTyped(evt);
                }
            });
            storage_panel.add(jTextField_net_weight, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 390, 120, -1));

            jLabel2.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
            jLabel2.setText("رقم البالتة");
            jLabel2.setMaximumSize(new java.awt.Dimension(82, 24));
            jLabel2.setMinimumSize(new java.awt.Dimension(82, 24));
            jLabel2.setPreferredSize(new java.awt.Dimension(82, 24));
            storage_panel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 180, -1, 30));

            jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jLabel14.setText("وزن البالتة");
            storage_panel.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 490, -1, 30));

            pallet_weight.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            pallet_weight.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
            storage_panel.add(pallet_weight, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 490, 140, -1));

            jCheckBox_M_Markpage.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            jCheckBox_M_Markpage.setText("تعليم البالته");
            jCheckBox_M_Markpage.setFocusable(false);
            jCheckBox_M_Markpage.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    jCheckBox_M_MarkpageKeyPressed(evt);
                }
            });
            storage_panel.add(jCheckBox_M_Markpage, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 210, -1, 20));

            jButton_clear.setBackground(new java.awt.Color(240, 0, 0));
            jButton_clear.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
            jButton_clear.setForeground(new java.awt.Color(255, 255, 255));
            jButton_clear.setText("X");
            jButton_clear.setToolTipText("Clear");
            jButton_clear.setBorderPainted(false);
            jButton_clear.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
            jButton_clear.setFocusable(false);
            jButton_clear.setName(""); // NOI18N
            jButton_clear.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_clearActionPerformed(evt);
                }
            });
            storage_panel.add(jButton_clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(695, 610, 40, -1));

            jCheckBox_print.setSelected(true);
            jCheckBox_print.setText("طباعة");
            jCheckBox_print.setFocusable(false);
            storage_panel.add(jCheckBox_print, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, -1, -1));

            jTextField_Color.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jTextField_Color.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            jTextField_Color.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTextField_ColorKeyTyped(evt);
                }
            });
            storage_panel.add(jTextField_Color, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 540, 140, 40));

            jLabel41.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
            jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel41.setText("اللون");
            storage_panel.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 540, 70, 40));

            jCheckBox_QR.setText("QR");
            jCheckBox_QR.setFocusable(false);
            storage_panel.add(jCheckBox_QR, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 10, -1, -1));

            jCheckBox_Box.setText("صندوق");
            jCheckBox_Box.setEnabled(false);
            storage_panel.add(jCheckBox_Box, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 70, -1, -1));

            jSeparator4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
            storage_panel.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(602, 232, 230, -1));
            storage_panel.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 135, 210, -1));

            jSeparator6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
            storage_panel.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(602, 482, 230, -1));

            jButton_reprintLastTicket.setBackground(new java.awt.Color(153, 153, 255));
            jButton_reprintLastTicket.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
            jButton_reprintLastTicket.setText("Print");
            jButton_reprintLastTicket.setToolTipText("Re-Print ticket");
            jButton_reprintLastTicket.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            jButton_reprintLastTicket.setFocusable(false);
            jButton_reprintLastTicket.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_reprintLastTicketActionPerformed(evt);
                }
            });
            storage_panel.add(jButton_reprintLastTicket, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 70, 60, 20));

            jProgressBar_pallet.setMaximum(20);
            jProgressBar_pallet.setToolTipText("Pallet");
            jProgressBar_pallet.setFocusable(false);
            jProgressBar_pallet.setMaximumSize(new java.awt.Dimension(10, 14));
            jProgressBar_pallet.setRequestFocusEnabled(false);
            storage_panel.add(jProgressBar_pallet, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 2, 590, -1));

            jCheckBox_freeze_cone.setFocusable(false);
            storage_panel.add(jCheckBox_freeze_cone, new org.netbeans.lib.awtextra.AbsoluteConstraints(815, 250, -1, -1));

            jCheckBox_freeze_wight.setFocusable(false);
            storage_panel.add(jCheckBox_freeze_wight, new org.netbeans.lib.awtextra.AbsoluteConstraints(815, 300, -1, -1));

            jCheckBox_ConeWeightChange.setFocusable(false);
            storage_panel.add(jCheckBox_ConeWeightChange, new org.netbeans.lib.awtextra.AbsoluteConstraints(815, 450, -1, -1));

            jCheckBox_ignore_limits.setText("سماح الوزن");
            jCheckBox_ignore_limits.setFocusable(false);
            storage_panel.add(jCheckBox_ignore_limits, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 10, -1, -1));

            jSeparator7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
            jSeparator7.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
            jSeparator7.setMaximumSize(new java.awt.Dimension(37, 3));
            jSeparator7.setMinimumSize(new java.awt.Dimension(37, 3));
            jSeparator7.setPreferredSize(new java.awt.Dimension(37, 55));
            storage_panel.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(605, 10, 110, 20));

            jTextField_Search_pros.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTextField_Search_prosKeyTyped(evt);
                }
            });
            storage_panel.add(jTextField_Search_pros, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 40, 150, 20));

            left_panel.add(storage_panel, "Mizan");

            makePermit.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            makePermit.setMaximumSize(new java.awt.Dimension(835, 640));
            makePermit.setMinimumSize(new java.awt.Dimension(835, 640));
            makePermit.setPreferredSize(new java.awt.Dimension(835, 640));
            makePermit.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            jComboBox_rep_Pros.setFont(new java.awt.Font("sansserif", 0, 20)); // NOI18N
            jComboBox_rep_Pros.addItemListener(new java.awt.event.ItemListener() {
                public void itemStateChanged(java.awt.event.ItemEvent evt) {
                    jComboBox_rep_ProsItemStateChanged(evt);
                }
            });
            makePermit.add(jComboBox_rep_Pros, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 300, -1));

            jLabel1.setFont(new java.awt.Font("sansserif", 0, 20)); // NOI18N
            jLabel1.setText("الصنف");
            makePermit.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, -1, -1));

            jLabel4.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
            jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel4.setText("_________________________________________________________");
            makePermit.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 750, 30));

            jTextField_rep_numOfBag.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jTextField_rep_numOfBag.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
            jTextField_rep_numOfBag.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    jTextField_rep_numOfBagKeyReleased(evt);
                }
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTextField_rep_numOfBagKeyTyped(evt);
                }
            });
            makePermit.add(jTextField_rep_numOfBag, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 80, -1));

            jButton_rep_printRep.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
            jButton_rep_printRep.setText("طباعة");
            jButton_rep_printRep.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_rep_printRepActionPerformed(evt);
                }
            });
            makePermit.add(jButton_rep_printRep, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 500, 120, 50));

            jTable_rep_preview.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jTable_rep_preview.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                    "مسلسل", "وزن", "لوط", "رقم البالتة", "s"
                }
            ) {
                Class[] types = new Class [] {
                    java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
                };
                boolean[] canEdit = new boolean [] {
                    false, false, false, false, false
                };

                public Class getColumnClass(int columnIndex) {
                    return types [columnIndex];
                }

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            });
            jTable_rep_preview.setGridColor(new java.awt.Color(0, 0, 0));
            jTable_rep_preview.setRowHeight(25);
            jTable_rep_preview.setShowGrid(true);
            jTable_rep_preview.getTableHeader().setReorderingAllowed(false);
            jScrollPane3.setViewportView(jTable_rep_preview);
            if (jTable_rep_preview.getColumnModel().getColumnCount() > 0) {
                jTable_rep_preview.getColumnModel().getColumn(4).setMinWidth(0);
                jTable_rep_preview.getColumnModel().getColumn(4).setPreferredWidth(0);
                jTable_rep_preview.getColumnModel().getColumn(4).setMaxWidth(0);
            }

            makePermit.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 278, 610, 360));

            jTextField_rep_clientName.setFont(new java.awt.Font("sansserif", 0, 20)); // NOI18N
            jTextField_rep_clientName.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
            jTextField_rep_clientName.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    jTextField_rep_clientNameFocusGained(evt);
                }
            });
            jTextField_rep_clientName.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTextField_rep_clientNameKeyTyped(evt);
                }
            });
            makePermit.add(jTextField_rep_clientName, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, 450, 40));

            jLabel5.setFont(new java.awt.Font("sansserif", 0, 20)); // NOI18N
            jLabel5.setText("أسم العميل");
            makePermit.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 200, -1, -1));

            jTable_rep_select.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
            jTable_rep_select.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                    "شيكاره", "وزن", "لوط", "رقم البالتة", "s"
                }
            ) {
                Class[] types = new Class [] {
                    java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
                };
                boolean[] canEdit = new boolean [] {
                    false, false, false, false, false
                };

                public Class getColumnClass(int columnIndex) {
                    return types [columnIndex];
                }

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            });
            jTable_rep_select.setGridColor(new java.awt.Color(0, 0, 0));
            jTable_rep_select.setRowHeight(25);
            jTable_rep_select.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
            jTable_rep_select.setShowGrid(true);
            jTable_rep_select.getTableHeader().setReorderingAllowed(false);
            jTable_rep_select.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jTable_rep_selectMouseClicked(evt);
                }
            });
            jScrollPane4.setViewportView(jTable_rep_select);
            if (jTable_rep_select.getColumnModel().getColumnCount() > 0) {
                jTable_rep_select.getColumnModel().getColumn(3).setMinWidth(100);
                jTable_rep_select.getColumnModel().getColumn(3).setPreferredWidth(100);
                jTable_rep_select.getColumnModel().getColumn(3).setMaxWidth(100);
                jTable_rep_select.getColumnModel().getColumn(4).setMinWidth(0);
                jTable_rep_select.getColumnModel().getColumn(4).setPreferredWidth(0);
                jTable_rep_select.getColumnModel().getColumn(4).setMaxWidth(0);
            }

            makePermit.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, 500, 170));

            jTextField_rep_totweight.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jTextField_rep_totweight.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            jTextField_rep_totweight.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    jTextField_rep_totweightKeyPressed(evt);
                }
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTextField_rep_totweightKeyTyped(evt);
                }
            });
            makePermit.add(jTextField_rep_totweight, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 430, 140, 50));

            jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jLabel3.setText("إجمالي الوزن");
            makePermit.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 380, -1, -1));

            jLabel_Order_num.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jLabel_Order_num.setText("عدد الشكاير");
            makePermit.add(jLabel_Order_num, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, -1, -1));

            jCheckBox_rep_2n1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jCheckBox_rep_2n1.setText(" صنفين في اذن واحد");
            makePermit.add(jCheckBox_rep_2n1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, -1, -1));

            jComboBox_rep_palletsNrep.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            makePermit.add(jComboBox_rep_palletsNrep, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 210, 80, -1));

            jLabel16.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
            jLabel16.setText("رقم البالته");
            makePermit.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 210, -1, -1));

            jButton_reps_clear.setBackground(new java.awt.Color(255, 0, 0));
            jButton_reps_clear.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
            jButton_reps_clear.setForeground(new java.awt.Color(255, 255, 255));
            jButton_reps_clear.setText("Clear All");
            jButton_reps_clear.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_reps_clearActionPerformed(evt);
                }
            });
            makePermit.add(jButton_reps_clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 580, 130, 40));

            jCheckBox_rep_wzn.setText("وزن");
            jCheckBox_rep_wzn.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jCheckBox_rep_wznActionPerformed(evt);
                }
            });
            makePermit.add(jCheckBox_rep_wzn, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 130, -1, -1));

            left_panel.add(makePermit, "Ezn");

            products_panel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            products_panel.setMaximumSize(new java.awt.Dimension(835, 640));
            products_panel.setMinimumSize(new java.awt.Dimension(835, 640));
            products_panel.setPreferredSize(new java.awt.Dimension(835, 640));
            products_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            jTextField_pro_name.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
            jTextField_pro_name.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
            jTextField_pro_name.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTextField_pro_nameKeyTyped(evt);
                }
            });
            products_panel.add(jTextField_pro_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 340, -1));

            jButton_add_pro.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
            jButton_add_pro.setText("إضافه/تعديل");
            jButton_add_pro.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_add_proActionPerformed(evt);
                }
            });
            products_panel.add(jButton_add_pro, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 20, 130, 60));

            jTable_pro.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
            jTable_pro.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                    "مسلسل", "أسم الصنف", "وزن الكونه", "اللون", "صندوق"
                }
            ) {
                boolean[] canEdit = new boolean [] {
                    false, false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            });
            jTable_pro.setColumnSelectionAllowed(true);
            jTable_pro.setGridColor(new java.awt.Color(0, 0, 0));
            jTable_pro.setRowHeight(25);
            jTable_pro.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jTable_proMouseClicked(evt);
                }
            });
            jScrollPane2.setViewportView(jTable_pro);
            jTable_pro.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            if (jTable_pro.getColumnModel().getColumnCount() > 0) {
                jTable_pro.getColumnModel().getColumn(0).setMinWidth(100);
                jTable_pro.getColumnModel().getColumn(0).setPreferredWidth(100);
                jTable_pro.getColumnModel().getColumn(0).setMaxWidth(100);
                jTable_pro.getColumnModel().getColumn(1).setResizable(false);
                jTable_pro.getColumnModel().getColumn(1).setPreferredWidth(150);
                jTable_pro.getColumnModel().getColumn(2).setResizable(false);
                jTable_pro.getColumnModel().getColumn(2).setPreferredWidth(2);
                jTable_pro.getColumnModel().getColumn(3).setResizable(false);
                jTable_pro.getColumnModel().getColumn(4).setResizable(false);
                jTable_pro.getColumnModel().getColumn(4).setPreferredWidth(2);
            }

            products_panel.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 126, 740, 470));

            jButton_del_pro.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
            jButton_del_pro.setText("حذف");
            jButton_del_pro.setPreferredSize(new java.awt.Dimension(75, 28));
            jButton_del_pro.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_del_proActionPerformed(evt);
                }
            });
            products_panel.add(jButton_del_pro, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 20, 80, 60));

            jTextField_Pros_conWight.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
            jTextField_Pros_conWight.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
            jTextField_Pros_conWight.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTextField_Pros_conWightKeyTyped(evt);
                }
            });
            products_panel.add(jTextField_Pros_conWight, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 90, 140, 30));

            jLabel24.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jLabel24.setText("الأسم");
            products_panel.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, -1, -1));

            jLabel37.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jLabel37.setText("وزن الكونه");
            products_panel.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, -1, -1));

            jTextField_Pros_color.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jTextField_Pros_color.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            jTextField_Pros_color.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTextField_Pros_colorKeyTyped(evt);
                }
            });
            products_panel.add(jTextField_Pros_color, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, 140, 30));

            jLabel49.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel49.setText("اللون");
            products_panel.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 50, 70, 30));

            jCheckBox_Pros_IsBox.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jCheckBox_Pros_IsBox.setText("صندوق");
            products_panel.add(jCheckBox_Pros_IsBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 80, 110, -1));

            left_panel.add(products_panel, "add Pro");

            stock_panel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            stock_panel.setMaximumSize(new java.awt.Dimension(835, 640));
            stock_panel.setMinimumSize(new java.awt.Dimension(835, 640));
            stock_panel.setPreferredSize(new java.awt.Dimension(835, 640));
            stock_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            jComboBox_stock_Pros.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jComboBox_stock_Pros.addItemListener(new java.awt.event.ItemListener() {
                public void itemStateChanged(java.awt.event.ItemEvent evt) {
                    jComboBox_stock_ProsItemStateChanged(evt);
                }
            });
            stock_panel.add(jComboBox_stock_Pros, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 34, 433, -1));

            jTable_stock.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jTable_stock.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                    "اللوط", "عدد البالت", "عدد الشكاير", "الوزن الأجمالي"
                }
            ) {
                boolean[] canEdit = new boolean [] {
                    false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            });
            jTable_stock.setRowHeight(25);
            jTable_stock.setShowGrid(true);
            jTable_stock.getTableHeader().setResizingAllowed(false);
            jTable_stock.getTableHeader().setReorderingAllowed(false);
            jScrollPane6.setViewportView(jTable_stock);

            stock_panel.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 109, 712, 477));

            jLabel15.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
            jLabel15.setText("الصنف");
            stock_panel.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(616, 26, -1, -1));

            jButton_stock_createExcl.setText("Create Excel");
            jButton_stock_createExcl.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_stock_createExclActionPerformed(evt);
                }
            });
            stock_panel.add(jButton_stock_createExcl, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 600, -1, -1));

            left_panel.add(stock_panel, "stock");

            statistics_panel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            statistics_panel.setMaximumSize(new java.awt.Dimension(835, 640));
            statistics_panel.setMinimumSize(new java.awt.Dimension(835, 640));
            statistics_panel.setPreferredSize(new java.awt.Dimension(835, 640));
            statistics_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            jTable_statis.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jTable_statis.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                    "الصنف", "اللوط", "عدد الشكاير", "إجمالي الوزن"
                }
            ) {
                boolean[] canEdit = new boolean [] {
                    false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            });
            jTable_statis.setRowHeight(25);
            jTable_statis.setShowGrid(true);
            jScrollPane7.setViewportView(jTable_statis);
            if (jTable_statis.getColumnModel().getColumnCount() > 0) {
                jTable_statis.getColumnModel().getColumn(1).setResizable(false);
                jTable_statis.getColumnModel().getColumn(2).setResizable(false);
                jTable_statis.getColumnModel().getColumn(3).setResizable(false);
            }

            statistics_panel.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 790, 420));

            jLabel18.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jLabel18.setText("إلي");
            statistics_panel.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, -1, -1));

            jLabel19.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jLabel19.setText("من");
            statistics_panel.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 20, -1, -1));
            statistics_panel.add(jDateChooser_statis_fromDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 70, 140, 30));
            statistics_panel.add(jDateChooser_statis_toDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, 140, 30));

            jLabel20.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jLabel20.setText("التاريخ:");
            statistics_panel.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 30, -1, -1));

            jLabel42.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jLabel42.setText("المجموع");
            statistics_panel.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 580, -1, -1));

            jTextField_statis_tot.setEditable(false);
            jTextField_statis_tot.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            statistics_panel.add(jTextField_statis_tot, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 572, 200, 50));

            jButton_statistics_search.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jButton_statistics_search.setText("بحث");
            jButton_statistics_search.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_statistics_searchActionPerformed(evt);
                }
            });
            statistics_panel.add(jButton_statistics_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 85, 80, 40));

            jButton_statis_createExcl.setText("Create Excel");
            jButton_statis_createExcl.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_statis_createExclActionPerformed(evt);
                }
            });
            statistics_panel.add(jButton_statis_createExcl, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 590, -1, -1));

            left_panel.add(statistics_panel, "statis");

            showPermit_panel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            showPermit_panel.setMaximumSize(new java.awt.Dimension(835, 640));
            showPermit_panel.setMinimumSize(new java.awt.Dimension(835, 640));
            showPermit_panel.setPreferredSize(new java.awt.Dimension(835, 640));
            showPermit_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            jTable_yumia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            jTable_yumia.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                    "الأسم", "الصنف", "اللوط", "عدد الشكاير", "الوزن", "التاريخ"
                }
            ) {
                boolean[] canEdit = new boolean [] {
                    false, false, false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            });
            jTable_yumia.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
            jTable_yumia.setColumnSelectionAllowed(true);
            jTable_yumia.setRowHeight(25);
            jTable_yumia.getTableHeader().setReorderingAllowed(false);
            jTable_yumia.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseReleased(java.awt.event.MouseEvent evt) {
                    jTable_yumiaMouseReleased(evt);
                }
            });
            jScrollPane8.setViewportView(jTable_yumia);
            jTable_yumia.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
            if (jTable_yumia.getColumnModel().getColumnCount() > 0) {
                jTable_yumia.getColumnModel().getColumn(0).setPreferredWidth(250);
                jTable_yumia.getColumnModel().getColumn(1).setPreferredWidth(250);
                jTable_yumia.getColumnModel().getColumn(2).setPreferredWidth(50);
                jTable_yumia.getColumnModel().getColumn(3).setPreferredWidth(50);
                jTable_yumia.getColumnModel().getColumn(4).setPreferredWidth(120);
                jTable_yumia.getColumnModel().getColumn(5).setPreferredWidth(120);
            }

            showPermit_panel.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 307, 810, 330));
            showPermit_panel.add(jDateChooser_yum_fromDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 220, 180, 30));
            showPermit_panel.add(jDateChooser_yum_ToDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 160, 30));

            jLabel22.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jLabel22.setText("من");
            showPermit_panel.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 180, -1, -1));

            jLabel23.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jLabel23.setText("إلي");
            showPermit_panel.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, -1, -1));

            jButton_youm_search.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jButton_youm_search.setText("بحث");
            jButton_youm_search.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_youm_searchActionPerformed(evt);
                }
            });
            showPermit_panel.add(jButton_youm_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 260, -1, -1));

            jButton_youm_refund.setBackground(new java.awt.Color(255, 51, 51));
            jButton_youm_refund.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
            jButton_youm_refund.setText("أسترجاع الأزن");
            jButton_youm_refund.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_youm_refundActionPerformed(evt);
                }
            });
            showPermit_panel.add(jButton_youm_refund, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 230, 150, 40));

            jTextField_youm_ClientFilter.setToolTipText("client Filter");
            jTextField_youm_ClientFilter.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTextField_youm_ClientFilterKeyTyped(evt);
                }
            });
            showPermit_panel.add(jTextField_youm_ClientFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 20, 170, 40));

            jTable_youm_clinets.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                    "ID", "اسم"
                }
            ) {
                boolean[] canEdit = new boolean [] {
                    false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            });
            jTable_youm_clinets.setColumnSelectionAllowed(true);
            jScrollPane10.setViewportView(jTable_youm_clinets);
            jTable_youm_clinets.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
            if (jTable_youm_clinets.getColumnModel().getColumnCount() > 0) {
                jTable_youm_clinets.getColumnModel().getColumn(0).setPreferredWidth(50);
                jTable_youm_clinets.getColumnModel().getColumn(1).setPreferredWidth(300);
            }

            showPermit_panel.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 430, 150));

            jButton_youm_getClients.setBackground(new java.awt.Color(153, 153, 255));
            jButton_youm_getClients.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            jButton_youm_getClients.setText("عملاء");
            jButton_youm_getClients.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_youm_getClientsActionPerformed(evt);
                }
            });
            showPermit_panel.add(jButton_youm_getClients, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 20, 120, 40));

            jCheckBox_youm_old.setText("Old");
            showPermit_panel.add(jCheckBox_youm_old, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 140, -1, -1));

            jButton_youm_createExcel.setBackground(new java.awt.Color(255, 255, 0));
            jButton_youm_createExcel.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
            jButton_youm_createExcel.setText("طباعة البيان");
            jButton_youm_createExcel.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_youm_createExcelActionPerformed(evt);
                }
            });
            showPermit_panel.add(jButton_youm_createExcel, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 230, 150, 40));

            left_panel.add(showPermit_panel, "yomia");

            pause_panel.setEnabled(false);
            pause_panel.setFocusable(false);
            pause_panel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            pause_panel.setMaximumSize(new java.awt.Dimension(835, 640));
            pause_panel.setMinimumSize(new java.awt.Dimension(835, 640));
            pause_panel.setName("emp"); // NOI18N
            pause_panel.setPreferredSize(new java.awt.Dimension(835, 640));
            pause_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            jTextArea_emp.setColumns(20);
            jTextArea_emp.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
            jTextArea_emp.setRows(5);
            jTextArea_emp.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTextArea_empKeyTyped(evt);
                }
            });
            jScrollPane9.setViewportView(jTextArea_emp);

            pause_panel.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 820, 540));

            jLabel38.setFont(new java.awt.Font("Tahoma", 3, 48)); // NOI18N
            jLabel38.setText("اي حاجة");
            jLabel38.setEnabled(false);
            jLabel38.setFocusable(false);
            pause_panel.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, 290, 50));

            left_panel.add(pause_panel, "emp");

            jTabbedPane_settings.setBorder(javax.swing.BorderFactory.createTitledBorder("Settings"));
            jTabbedPane_settings.setMaximumSize(new java.awt.Dimension(835, 640));
            jTabbedPane_settings.setMinimumSize(new java.awt.Dimension(835, 640));
            jTabbedPane_settings.setPreferredSize(new java.awt.Dimension(835, 640));
            jTabbedPane_settings.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jTabbedPane_settingsMouseClicked(evt);
                }
            });

            jTab_set_Counter.setMaximumSize(new java.awt.Dimension(830, 635));
            jTab_set_Counter.setMinimumSize(new java.awt.Dimension(830, 635));
            jTab_set_Counter.setPreferredSize(new java.awt.Dimension(830, 635));
            jTab_set_Counter.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            jButton_Reset_TicketCount2x2.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
            jButton_Reset_TicketCount2x2.setText("2 X 2");
            jButton_Reset_TicketCount2x2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_Reset_TicketCount2x2ActionPerformed(evt);
                }
            });
            jTab_set_Counter.add(jButton_Reset_TicketCount2x2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 230, 110, 60));

            jButton_Reset_TicketCount10x10.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
            jButton_Reset_TicketCount10x10.setText("10 X 10");
            jButton_Reset_TicketCount10x10.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_Reset_TicketCount10x10ActionPerformed(evt);
                }
            });
            jTab_set_Counter.add(jButton_Reset_TicketCount10x10, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 230, 110, 60));
            jTab_set_Counter.add(jLabel_Ticket10x10Counter, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 300, 70, 50));
            jTab_set_Counter.add(jLabel_Ticket2x2Counter, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 300, 70, 50));

            jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
            jLabel17.setText("Reset ");
            jTab_set_Counter.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, 130, 60));

            jTabbedPane_settings.addTab("إعدادات عدد اللزق", jTab_set_Counter);

            jTab_set_Printing.setMaximumSize(new java.awt.Dimension(835, 640));
            jTab_set_Printing.setMinimumSize(new java.awt.Dimension(835, 640));
            jTab_set_Printing.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            jButton_set_changePos.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            jButton_set_changePos.setText("تغيير أماكن القيم في الطابعة");
            jButton_set_changePos.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_set_changePosActionPerformed(evt);
                }
            });
            jTab_set_Printing.add(jButton_set_changePos, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, 70));

            jCheckBox_set_printExcel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            jCheckBox_set_printExcel.setText("طباعة بطريقة الاكسل ؟");
            jCheckBox_set_printExcel.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jCheckBox_set_printExcelActionPerformed(evt);
                }
            });
            jTab_set_Printing.add(jCheckBox_set_printExcel, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 340, 230, 80));

            jSeparator14.setForeground(new java.awt.Color(0, 0, 0));
            jSeparator14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
            jSeparator14.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
            jTab_set_Printing.add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, 690, 140));

            jLabel46.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel46.setForeground(new java.awt.Color(255, 0, 0));
            jLabel46.setText("الاعدادت في الاسفل لا تنطبق علي الاكسل");
            jTab_set_Printing.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 430, -1, -1));

            jLabel50.setForeground(new java.awt.Color(255, 0, 51));
            jLabel50.setText("لا تنس تغير الطابعة الافتراضية ألي الطابة المرغومة");
            jTab_set_Printing.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 430, 270, 30));

            jButton_set_printValueToCenter.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            jButton_set_printValueToCenter.setText("تغيير أماكن القيم في الطابعة إلي المنتصف");
            jButton_set_printValueToCenter.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_set_printValueToCenterActionPerformed(evt);
                }
            });
            jTab_set_Printing.add(jButton_set_printValueToCenter, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 40, 370, 70));

            jTabbedPane_settings.addTab("Printing Options", jTab_set_Printing);

            jTab_set_order.setMaximumSize(new java.awt.Dimension(830, 635));
            jTab_set_order.setMinimumSize(new java.awt.Dimension(830, 635));
            jTab_set_order.setPreferredSize(new java.awt.Dimension(830, 635));
            jTab_set_order.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            jLabel45.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jLabel45.setText("الفرق الوزن في عمل الاذن");
            jTab_set_order.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 170, -1, -1));

            jTextField_setting_repsDiff.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jTextField_setting_repsDiff.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTextField_setting_repsDiffKeyTyped(evt);
                }
            });
            jTab_set_order.add(jTextField_setting_repsDiff, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 220, 160, 70));

            jTabbedPane_settings.addTab("إعدادات الأذن", jTab_set_order);

            jPanel_Machines.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            jTable_machines.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                    "id", "Name", "Type", "Lot", "Date"
                }
            ) {
                Class[] types = new Class [] {
                    java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
                };
                boolean[] canEdit = new boolean [] {
                    false, false, false, false, false
                };

                public Class getColumnClass(int columnIndex) {
                    return types [columnIndex];
                }

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            });
            jTable_machines.setColumnSelectionAllowed(true);
            jTable_machines.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jTable_machinesMouseClicked(evt);
                }
            });
            jScrollPane5.setViewportView(jTable_machines);
            jTable_machines.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
            if (jTable_machines.getColumnModel().getColumnCount() > 0) {
                jTable_machines.getColumnModel().getColumn(0).setMinWidth(0);
                jTable_machines.getColumnModel().getColumn(0).setPreferredWidth(0);
                jTable_machines.getColumnModel().getColumn(0).setMaxWidth(0);
                jTable_machines.getColumnModel().getColumn(3).setResizable(false);
                jTable_machines.getColumnModel().getColumn(4).setResizable(false);
            }

            jPanel_Machines.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 285, 814, 302));

            jTextField_mach_MName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            jTextField_mach_MName.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTextField_mach_MNameKeyTyped(evt);
                }
            });
            jPanel_Machines.add(jTextField_mach_MName, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 70, 230, 50));

            jComboBox_mach_pros.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            jPanel_Machines.add(jComboBox_mach_pros, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 70, 230, 50));

            jTextField_mach_lot.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            jTextField_mach_lot.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTextField_mach_lotKeyTyped(evt);
                }
            });
            jPanel_Machines.add(jTextField_mach_lot, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 230, 50));

            jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel51.setText("أسم الماكينة");
            jPanel_Machines.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 20, 100, 40));

            jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel52.setText("صنف التشغيل");
            jPanel_Machines.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, 100, 40));

            jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel54.setText("اللوط");
            jPanel_Machines.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 100, 40));

            jButton_mach_addMach.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
            jButton_mach_addMach.setText("Add / Edit");
            jButton_mach_addMach.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_mach_addMachActionPerformed(evt);
                }
            });
            jPanel_Machines.add(jButton_mach_addMach, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 150, 170, 70));

            jButton_mach_Delete.setBackground(new java.awt.Color(255, 0, 0));
            jButton_mach_Delete.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            jButton_mach_Delete.setForeground(new java.awt.Color(255, 255, 255));
            jButton_mach_Delete.setText("Delete");
            jButton_mach_Delete.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_mach_DeleteActionPerformed(evt);
                }
            });
            jPanel_Machines.add(jButton_mach_Delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 80, 40));

            jTabbedPane_settings.addTab("Machine", jPanel_Machines);

            jTab_set_about.setMaximumSize(new java.awt.Dimension(830, 635));
            jTab_set_about.setMinimumSize(new java.awt.Dimension(830, 635));
            jTab_set_about.setPreferredSize(new java.awt.Dimension(830, 635));
            jTab_set_about.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            jLabel40.setBackground(new java.awt.Color(204, 255, 204));
            jLabel40.setFont(new java.awt.Font("Segoe UI", 3, 48)); // NOI18N
            jLabel40.setText(Version);
            jLabel40.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(102, 255, 255)));
            jLabel40.setFocusable(false);
            jLabel40.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel40.setName("setting_version"); // NOI18N
            jLabel40.setRequestFocusEnabled(false);
            jLabel40.setVerifyInputWhenFocusTarget(false);
            jTab_set_about.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 690, 140));
            jLabel40.getAccessibleContext().setAccessibleDescription("Setting page Version");

            jLabel_ip.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
            jTab_set_about.add(jLabel_ip, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 370, 560, 90));

            jButton_set_reloadSettingFile.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
            jButton_set_reloadSettingFile.setText("reload Setting File");
            jButton_set_reloadSettingFile.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_set_reloadSettingFileActionPerformed(evt);
                }
            });
            jTab_set_about.add(jButton_set_reloadSettingFile, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 460, 240, 90));

            jTabbedPane_settings.addTab("About", jTab_set_about);

            jPanel_print.setBackground(new java.awt.Color(255, 255, 255));
            jPanel_print.setEnabled(false);
            jPanel_print.setFocusable(false);
            jPanel_print.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
            jPanel_print.setMaximumSize(new java.awt.Dimension(300, 350));
            jPanel_print.setMinimumSize(new java.awt.Dimension(300, 350));
            jPanel_print.setName("printingPanel"); // NOI18N
            jPanel_print.setPreferredSize(new java.awt.Dimension(300, 350));
            jPanel_print.setRequestFocusEnabled(false);
            jPanel_print.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            jLabel_print_header.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
            jLabel_print_header.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel_print_header.setText("الصفا و المروه للغزل و النسيج");
            jLabel_print_header.setFocusable(false);
            jLabel_print_header.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel_print.add(jLabel_print_header, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, -1, -1));

            jLabel_print_ValPallet.setFont(new java.awt.Font("Arial", 0, 30)); // NOI18N
            jLabel_print_ValPallet.setText("Label");
            jLabel_print_ValPallet.setFocusable(false);
            jLabel_print_ValPallet.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel_print.add(jLabel_print_ValPallet, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 24, 190, 25));

            jLabel_print_pallet.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
            jLabel_print_pallet.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel_print_pallet.setText("رقم البالتة");
            jLabel_print_pallet.setFocusable(false);
            jLabel_print_pallet.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel_print.add(jLabel_print_pallet, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 25, 75, 20));

            jLabel_print_ValColor.setFont(new java.awt.Font("Arial", 0, 28)); // NOI18N
            jLabel_print_ValColor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel_print_ValColor.setText("Label");
            jLabel_print_ValColor.setFocusable(false);
            jLabel_print_ValColor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel_print.add(jLabel_print_ValColor, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 50, 190, 25));

            jLabel_print_color.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
            jLabel_print_color.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel_print_color.setText("لون الكونة");
            jLabel_print_color.setFocusable(false);
            jLabel_print_color.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel_print.add(jLabel_print_color, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 55, 75, 20));

            jLabel_print_ValType.setFont(new java.awt.Font("Arial", 0, 40)); // NOI18N
            jLabel_print_ValType.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel_print_ValType.setText("Label");
            jLabel_print_ValType.setFocusable(false);
            jLabel_print_ValType.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel_print.add(jLabel_print_ValType, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 80, 190, 35));

            jLabel_print_type.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
            jLabel_print_type.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel_print_type.setText("الصنف");
            jLabel_print_type.setFocusable(false);
            jLabel_print_type.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel_print.add(jLabel_print_type, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 85, 75, 30));

            jLabel_print_ValLot.setFont(new java.awt.Font("Arial", 0, 40)); // NOI18N
            jLabel_print_ValLot.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
            jLabel_print_ValLot.setText("Label");
            jLabel_print_ValLot.setFocusable(false);
            jLabel_print_ValLot.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel_print.add(jLabel_print_ValLot, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 122, 190, 30));

            jLabel_print_lot.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
            jLabel_print_lot.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel_print_lot.setText("اللوط");
            jLabel_print_lot.setFocusable(false);
            jLabel_print_lot.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel_print.add(jLabel_print_lot, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 125, 75, 25));

            jLabel_print_ValNCone.setFont(new java.awt.Font("Arial", 0, 28)); // NOI18N
            jLabel_print_ValNCone.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel_print_ValNCone.setText("Label");
            jLabel_print_ValNCone.setFocusable(false);
            jLabel_print_ValNCone.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel_print.add(jLabel_print_ValNCone, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 160, 190, 25));

            jLabel_print_NCone.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
            jLabel_print_NCone.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel_print_NCone.setText("عدد الكون");
            jLabel_print_NCone.setFocusable(false);
            jLabel_print_NCone.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel_print.add(jLabel_print_NCone, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 160, 75, 25));

            jLabel_print_ValTotalWeight.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
            jLabel_print_ValTotalWeight.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel_print_ValTotalWeight.setText("Label");
            jLabel_print_ValTotalWeight.setFocusable(false);
            jLabel_print_ValTotalWeight.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel_print.add(jLabel_print_ValTotalWeight, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 190, 190, 45));

            jLabel_print_TotalWeight.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
            jLabel_print_TotalWeight.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel_print_TotalWeight.setText("وزن قائم");
            jLabel_print_TotalWeight.setFocusable(false);
            jLabel_print_TotalWeight.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel_print.add(jLabel_print_TotalWeight, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 195, 75, 40));

            jLabel_print_ValNetWeight.setFont(new java.awt.Font("Arial", 0, 48)); // NOI18N
            jLabel_print_ValNetWeight.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel_print_ValNetWeight.setText("Label");
            jLabel_print_ValNetWeight.setFocusable(false);
            jLabel_print_ValNetWeight.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel_print.add(jLabel_print_ValNetWeight, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 243, 190, 50));

            jLabel_print_NetWeight.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
            jLabel_print_NetWeight.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel_print_NetWeight.setText("وزن صافي");
            jLabel_print_NetWeight.setFocusable(false);
            jLabel_print_NetWeight.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel_print.add(jLabel_print_NetWeight, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 243, 75, 50));

            jLabel_print_footer.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
            jLabel_print_footer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel_print_footer.setText("وجيه عماره ");
            jLabel_print_footer.setAutoscrolls(true);
            jLabel_print_footer.setFocusable(false);
            jLabel_print_footer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel_print.add(jLabel_print_footer, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 300, 100, 20));

            jLabel_print_number.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
            jLabel_print_number.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel_print_number.setText("٠١١٤٨٠٥٥٥٥٨");
            jPanel_print.add(jLabel_print_number, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 320, 110, -1));

            jSeparator2.setForeground(new java.awt.Color(204, 204, 204));
            jSeparator2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
            jSeparator2.setMaximumSize(new java.awt.Dimension(50, 100));
            jSeparator2.setMinimumSize(new java.awt.Dimension(50, 100));
            jSeparator2.setPreferredSize(new java.awt.Dimension(50, 100));
            jPanel_print.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 280, 280));

            jSeparator1.setForeground(new java.awt.Color(204, 204, 204));
            jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
            jSeparator1.setMaximumSize(new java.awt.Dimension(50, 100));
            jSeparator1.setMinimumSize(new java.awt.Dimension(50, 100));
            jSeparator1.setPreferredSize(new java.awt.Dimension(50, 100));
            jPanel_print.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 200, 280));

            jSeparator13.setForeground(new java.awt.Color(204, 204, 204));
            jSeparator13.setOrientation(javax.swing.SwingConstants.VERTICAL);
            jSeparator13.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 0, new java.awt.Color(0, 0, 0)));
            jPanel_print.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(205, 20, 10, 280));

            jSeparator3.setForeground(new java.awt.Color(204, 204, 204));
            jSeparator3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
            jSeparator3.setMaximumSize(new java.awt.Dimension(50, 100));
            jSeparator3.setMinimumSize(new java.awt.Dimension(50, 100));
            jSeparator3.setPreferredSize(new java.awt.Dimension(50, 100));
            jPanel_print.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 280, 30));

            jSeparator11.setForeground(new java.awt.Color(204, 204, 204));
            jSeparator11.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
            jSeparator11.setMaximumSize(new java.awt.Dimension(50, 100));
            jSeparator11.setMinimumSize(new java.awt.Dimension(50, 100));
            jSeparator11.setPreferredSize(new java.awt.Dimension(50, 100));
            jPanel_print.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 280, 60));

            jSeparator8.setForeground(new java.awt.Color(204, 204, 204));
            jSeparator8.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
            jSeparator8.setMaximumSize(new java.awt.Dimension(50, 100));
            jSeparator8.setMinimumSize(new java.awt.Dimension(50, 100));
            jSeparator8.setPreferredSize(new java.awt.Dimension(50, 100));
            jPanel_print.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 280, 100));

            jSeparator9.setForeground(new java.awt.Color(204, 204, 204));
            jSeparator9.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
            jSeparator9.setMaximumSize(new java.awt.Dimension(50, 100));
            jSeparator9.setMinimumSize(new java.awt.Dimension(50, 100));
            jSeparator9.setPreferredSize(new java.awt.Dimension(50, 100));
            jPanel_print.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 280, 135));

            jSeparator10.setForeground(new java.awt.Color(204, 204, 204));
            jSeparator10.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
            jSeparator10.setMaximumSize(new java.awt.Dimension(50, 100));
            jSeparator10.setMinimumSize(new java.awt.Dimension(50, 100));
            jSeparator10.setPreferredSize(new java.awt.Dimension(50, 100));
            jPanel_print.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 280, 170));

            jSeparator12.setForeground(new java.awt.Color(204, 204, 204));
            jSeparator12.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
            jSeparator12.setMaximumSize(new java.awt.Dimension(50, 100));
            jSeparator12.setMinimumSize(new java.awt.Dimension(50, 100));
            jSeparator12.setPreferredSize(new java.awt.Dimension(50, 100));
            jPanel_print.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 280, 220));

            jTabbedPane_settings.addTab("Print Panel", jPanel_print);
            jPanel_print.getAccessibleContext().setAccessibleName("printer_Panel");
            jPanel_print.getAccessibleContext().setAccessibleDescription("Panel_used_for_printing");

            left_panel.add(jTabbedPane_settings, "Settings");

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

    private void jButton_Mizan_openerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Mizan_openerActionPerformed
        evt.getID();
        open_panel(storage_panel);
        if (jComboBox_pro_in_storage.getSelectedIndex() == -1) {
            DefaultTableModel model = (DefaultTableModel) jTable_storage.getModel();
            model.setRowCount(0);
        }
        this.setAlwaysOnTop(jCheckBox_set_printExcel.isSelected());
    }//GEN-LAST:event_jButton_Mizan_openerActionPerformed

    private void jButton_addPro_openerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_addPro_openerActionPerformed
        try {
            evt.getID();
            pro_Table_SelectedID = 0;
            open_panel(products_panel);
            fill_pro_table();
        } catch (DatabaseException ex) {
            Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton_addPro_openerActionPerformed

    private void jButton_Ezn_openerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Ezn_openerActionPerformed
        evt.getID();
        open_panel(makePermit);
        // ((DefaultTableModel) jTable_rep_select.getModel()).setRowCount(0);
        // ((DefaultTableModel) jTable_rep_preview.getModel()).setRowCount(0);
        // jComboBox_rep_Pros.setSelectedIndex(-1);
        // second = false;
        // jTextField_rep_totweight.setText("");
        // jComboBox_rep_palletsNrep.removeAllItems();
    }//GEN-LAST:event_jButton_Ezn_openerActionPerformed

    private void jTextField_lotKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_lotKeyTyped
        evt.getID();
        textbox_length_limiter(evt, jTextField_lot, 5);
        char input = evt.getKeyChar();
        if (Character.isDigit(input)) {
            evt.setKeyChar(util.ToNumArab(input));
        }
        if ((!Character.isDigit(input)) && input != 'أ' && input != 'س' && input != 'و' && input != 'د' && input != 'ط'
                && input != 'ب' && input != 'ا' && input != 'ع' && input != 'ه' && input != 'م' && input != 'ن'
                && input != 'ي' && input != 'ض') {
            evt.consume();
        }
        calc_net_weight();
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            jTextField_pallet_num.requestFocusInWindow();
        }
    }//GEN-LAST:event_jTextField_lotKeyTyped

    private void jTextField_num_of_conKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_num_of_conKeyTyped
        evt.getID();
        sendToWight(jTextField_num_of_con, jTextField_weight, evt);
        textbox_number(evt, jTextField_num_of_con, 3, false);
        calc_net_weight();
        if (evt.getKeyChar() == KeyEvent.VK_ENTER && !enterFromMizan && !jTextField_num_of_con.getText().isEmpty()) {
            jTextField_bag_weight.requestFocusInWindow();
        }
    }//GEN-LAST:event_jTextField_num_of_conKeyTyped

    private void jTextField_pallet_numKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_pallet_numKeyTyped
        try {
            evt.getID();
            textbox_number(evt, jTextField_pallet_num, 5, false);
            calc_pallet_weight();
            jCheckBox_M_Markpage.setSelected(false);
            calc_net_weight();
            if (jTextField_pallet_num.getText().isBlank()) {
                pallet_weight.setText("");
            }
            if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                jTextField_num_of_con.requestFocusInWindow();
            }
        } catch (DatabaseException ex) {
            Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jTextField_pallet_numKeyTyped

    private void jTextField_net_weightKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_net_weightKeyTyped
        evt.getID();
        if (evt.getKeyChar() == KeyEvent.VK_MINUS && (!jTextField_net_weight.getText().isBlank())) {
            double x = util.ToDoubleEnglish(jTextField_weight.getText());
            if ((evt.getModifiersEx() & KeyEvent.SHIFT_DOWN_MASK) != 0) {
                jTextField_weight.setText(util.ToDoubleArabic(x + 0.02));
            } else {
                jTextField_weight.setText(util.ToDoubleArabic(x - 0.02));
            }
            calc_net_weight();
        }
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            calc_net_weight();
            jTextField_num_of_con.requestFocusInWindow();

            if (!jTextField_net_weight.getText().isBlank()) {
                jButton_add_data.doClick();
            }
        }
        if (evt.getKeyChar() == KeyEvent.VK_DELETE) {
            if ((evt.getModifiersEx() & KeyEvent.SHIFT_DOWN_MASK) != 0) {
                jButton_clear.doClick();
            }
        }
    }//GEN-LAST:event_jTextField_net_weightKeyTyped

    private void jTextField_bag_weightKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_bag_weightKeyTyped
        evt.getID();
        sendToWight(jTextField_bag_weight, jTextField_weight, evt);
        textbox_number(evt, jTextField_bag_weight, BagMax, true);
        calc_net_weight();
        if (evt.getKeyChar() == KeyEvent.VK_ENTER && !enterFromMizan && !jTextField_bag_weight.getText().isEmpty()) {
            jTextField_weight.requestFocusInWindow();
        }
    }//GEN-LAST:event_jTextField_bag_weightKeyTyped

    private void jTextField_weightKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_weightKeyTyped
        evt.getID();
        textbox_number_weight(evt, jTextField_weight, 8);
        calc_net_weight();
        if (evt.getKeyChar() == KeyEvent.VK_ENTER && !jTextField_weight.getText().isBlank()) {
            jTextField_net_weight.requestFocusInWindow();
        }
        if (evt.getKeyChar() == KeyEvent.VK_DELETE) {
            if ((evt.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0) {
                jCheckBox_freeze_cone.setSelected(false);
                jButton_clear.doClick();
            }

        }
        calc_net_weight();
    }//GEN-LAST:event_jTextField_weightKeyTyped

    private void jTextField_weight_of_conKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_weight_of_conKeyTyped
        evt.getID();
        textbox_number(evt, jTextField_weight_of_con, 4, false);
        calc_net_weight();
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            jTextField_lot.requestFocusInWindow();
        }
    }//GEN-LAST:event_jTextField_weight_of_conKeyTyped

    private void jButton_add_dataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_add_dataActionPerformed
        evt.getID();
        try {
            if (!jTextField_net_weight.getText().isBlank() && !jTextField_lot.getText().isBlank()
                    && !jTextField_pallet_num.getText().isBlank() && !jTextField_bag_weight.getText().isBlank()
                    && !jTextField_num_of_con.getText().isBlank() && !jTextField_weight.getText().isBlank()
                    && jComboBox_pro_in_storage.getSelectedIndex() != -1) {

                if ((util.ToDoubleEnglish(jTextField_weight.getText()) <= 60.0
                        && util.ToDoubleEnglish(jTextField_net_weight.getText()) > 15.0)
                        || jCheckBox_ignore_limits.isSelected()) {
                    calc_net_weight();

                    int filledPallet = storageController.addStorage(jComboBox_pro_in_storage.getSelectedItem().toString(),
                            jTextField_weight.getText(), jTextField_net_weight.getText(), jTextField_lot.getText(),
                            jTextField_num_of_con.getText(), jTextField_pallet_num.getText(),
                            jCheckBox_M_Markpage.isSelected()
                    );

                    jTextField_pallet_num.setText(util.ToStringArabic(filledPallet + ""));

                    calc_pallet_weight();

                    jProgressBar_pallet.setValue(storageController.countpallet(filledPallet, jTextField_lot.getText(),
                            jComboBox_pro_in_storage.getSelectedItem().toString(), jCheckBox_M_Markpage.isSelected()));
                    if (jCheckBox_print.isSelected() || jCheckBox_QR.isSelected()) {

                        if (jComboBox_pro_in_storage.getSelectedItem().toString().length() > 11) {
                            jLabel_print_ValType.setFont(new Font("Arial", Font.PLAIN, 26));
                        } else {
                            jLabel_print_ValType.setFont(new Font("Arial", Font.PLAIN, 40));
                        }

                        jLabel_print_ValPallet.setText(jTextField_pallet_num.getText());
                        jLabel_print_ValColor.setText(jTextField_Color.getText());
                        jLabel_print_ValType.setText(jComboBox_pro_in_storage.getSelectedItem().toString());
                        jLabel_print_ValLot.setText(jTextField_lot.getText());
                        jLabel_print_ValNCone.setText(jTextField_num_of_con.getText());
                        jLabel_print_ValTotalWeight.setText(jTextField_weight.getText());
                        jLabel_print_ValNetWeight.setText(jTextField_net_weight.getText());

                        printerManager.printTickets(new ArrayList<>(Arrays.asList(
                                jTextField_pallet_num.getText(), jTextField_Color.getText(),
                                jComboBox_pro_in_storage.getSelectedItem().toString(),
                                jTextField_lot.getText(), jTextField_num_of_con.getText(),
                                jTextField_weight.getText(), jTextField_net_weight.getText())),
                                jPanel_print,
                                jCheckBox_print.isSelected(), jCheckBox_QR.isSelected(),
                                jCheckBox_set_printExcel.isSelected());
                        incTicketCounters(jCheckBox_print.isSelected(), jCheckBox_QR.isSelected());
                    }
                    jButton_clear.doClick();
                    fill_storage_table();
                } else {
                    JOptionPane.showMessageDialog(this, util.addStyle("خطأ في وزن الشيكاره "), "إنتبه",
                            JOptionPane.INFORMATION_MESSAGE);
                    jTextField_weight.requestFocus();
                    jTextField_weight.selectAll();
                }
            } else {
                JOptionPane.showMessageDialog(this, util.addStyle(" برجاء إدخال البيانات كامله"), "إنتبه",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (DatabaseException ex) {
            Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
        } catch (BusinessException ex) {
            JOptionPane.showMessageDialog(this, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton_add_dataActionPerformed

    private void jButton_del_dataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_del_dataActionPerformed
        try {
            evt.getID();
            if (JOptionPane.showConfirmDialog(this, util.addStyle("هل تريد الحذف ؟"), "تنبيه",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                if (jTable_storage.getSelectedRowCount() == 1) {

                    storageController.removeBag(
                            jTable_storage.getModel().getValueAt(jTable_storage.getSelectedRow(), 4).toString());
                    JOptionPane.showMessageDialog(this, util.addStyle(" تم حذف البيان بنجاح "), "ناجح",
                            JOptionPane.INFORMATION_MESSAGE);
                    fill_storage_table();
                } else if (jTable_storage.getSelectedRowCount() > 1) {
                    for (int row : jTable_storage.getSelectedRows()) {
                        storageController.removeBag(jTable_storage.getModel().getValueAt(row, 4).toString());
                    }
                    fill_storage_table();
                    JOptionPane.showMessageDialog(this, util.addStyle(" تم حذف البيــانات بنجاح "), "ناجح",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, util.addStyle(" برجاء أختيار بيان من الجدول أولا  "), "إنتبه",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (DatabaseException ex) {
            Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
        } catch (BusinessException ex) {
            JOptionPane.showMessageDialog(this, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton_del_dataActionPerformed

    private void jButton_add_proActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_add_proActionPerformed
        evt.getID();
        try {
            if (pro_Table_SelectedID == 0) {
                if (jTextField_pro_name.getText().isBlank() || jTextField_Pros_conWight.getText().isBlank()) {
                    JOptionPane.showMessageDialog(this, util.addStyle(" برجاء أدخال البيانات كامله  "), "إنتبه",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    if (productController.getProduct(jTextField_pro_name.getText()) != null) {
                        JOptionPane.showMessageDialog(this, util.addStyle("هذا الصنف موجود بالفعل "), "إنتبه",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        productController.addNewProduct(jTextField_pro_name.getText(), jTextField_Pros_conWight.getText(),
                                jTextField_Pros_color.getText(), jCheckBox_Pros_IsBox.isSelected());
                        jTextField_pro_name.setText("");
                        jTextField_Pros_conWight.setText("");
                        jTextField_Pros_color.setText("");
                        jCheckBox_Pros_IsBox.setSelected(false);
                        fill_pro_table();
                        populateCombos();
                        JOptionPane.showMessageDialog(this, util.addStyle(" تم إدخال الصنف بنجاح  "), "ناجح",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } else {
                if (jTextField_pro_name.getText().isBlank()) {
                    JOptionPane.showMessageDialog(this, util.addStyle(" برجاء أدخال اسم الصنف  "), "إنتبه",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    productController.updateProduct(pro_Table_SelectedID, jTextField_pro_name.getText(),
                            jTextField_Pros_conWight.getText(), jTextField_Pros_color.getText(), jCheckBox_Pros_IsBox.isSelected());
                    pro_Table_SelectedID = 0;
                    jTextField_pro_name.setText("");
                    jTextField_Pros_conWight.setText("");
                    jTextField_Pros_color.setText("");
                    jCheckBox_Pros_IsBox.setSelected(false);
                    fill_pro_table();
                    populateCombos();
                    JOptionPane.showMessageDialog(this, util.addStyle("تم تعديل الصنف بنجاح "), "ناجح",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (DatabaseException ex) {
            Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
        } catch (BusinessException ex) {
            JOptionPane.showMessageDialog(this, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton_add_proActionPerformed

    private void jTextField_pro_nameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_pro_nameKeyTyped
        evt.getID();
        sendToWight(jTextField_pro_name, jTextField_Pros_conWight, evt);
        textbox_length_limiter(evt, jTextField_pro_name, 35);
        char input = evt.getKeyChar();
        if (Character.isDigit(input)) {
            evt.setKeyChar(util.ToNumArab(input));
        }
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            jButton_add_pro.doClick();
        }
    }//GEN-LAST:event_jTextField_pro_nameKeyTyped

    private void jTable_proMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_proMouseClicked
        evt.getID();
        try {
            TableModel model = jTable_pro.getModel();
            jTextField_pro_name.setText(model.getValueAt(jTable_pro.getSelectedRow(), 1).toString());
            jTextField_Pros_conWight.setText(model.getValueAt(jTable_pro.getSelectedRow(), 2).toString());
            jTextField_Pros_color.setText(model.getValueAt(jTable_pro.getSelectedRow(), 3).toString());
            jCheckBox_Pros_IsBox.setSelected(model.getValueAt(jTable_pro.getSelectedRow(), 4).toString().equals("true"));
            pro_Table_SelectedID = Integer.parseInt(model.getValueAt(jTable_pro.getSelectedRow(), 0).toString());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, e.getLocalizedMessage(), "exception", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jTable_proMouseClicked

    private void jButton_del_proActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_del_proActionPerformed
        evt.getID();
        if (pro_Table_SelectedID != 0) {
            try {
                if (productController.removeProduct(pro_Table_SelectedID)) {

                    JOptionPane.showMessageDialog(this, util.addStyle("تم حذف الصنف بنجاح "), "ناجح",
                            JOptionPane.INFORMATION_MESSAGE);
                    jTextField_pro_name.setText("");
                    pro_Table_SelectedID = 0;
                    fill_pro_table();
                    populateCombos();
                } else {
                    JOptionPane.showMessageDialog(this, util.addStyle("لا يمكن حذف هذا الصنف "), "إنتبه",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (DatabaseException ex) {
                Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
            } catch (BusinessException ex) {
                JOptionPane.showMessageDialog(this, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, util.addStyle(" برجاء أختيار صنف من الجدول أولا"), "إنتبه",
                    JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_jButton_del_proActionPerformed

    private void jComboBox_pro_in_storageItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_pro_in_storageItemStateChanged
        evt.getID();
        try {
            jTextField_lot.setText("");

            if (jComboBox_pro_in_storage.hasFocus()) {
                jCheckBox_ConeWeightChange.setSelected(false);
                jCheckBox_ignore_limits.setSelected(false);
                jCheckBox_freeze_cone.setSelected(false);
                jCheckBox_freeze_wight.setSelected(false);
                jCheckBox_M_Markpage.setSelected(false);
                fill_storage_table();
            }
            if (jTable_storage.getRowCount() != 0) {
                jTextField_lot.setText(jTable_storage.getValueAt(0, 2) + "");
                if (!jTable_storage.getValueAt(0, 5).equals("٢٠")) {
                    jTextField_pallet_num.setText(jTable_storage.getValueAt(0, 3) + "");
                } else {
                    jTextField_pallet_num.setText(util.ToStringArabic(
                            (int) (util.ToDoubleEnglish((String) jTable_storage.getValueAt(0, 3)) + 1) + ""));
                }
                calc_pallet_weight();
            }
        } catch (DatabaseException ex) {
            Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
        } catch (BusinessException ex) {
            JOptionPane.showMessageDialog(this, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_jComboBox_pro_in_storageItemStateChanged

    private void jComboBox_rep_ProsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_rep_ProsItemStateChanged
        evt.getID();
        try {
            if (jComboBox_rep_Pros.hasFocus()) {
                fill_Table_rep_select();
            }
        } catch (DatabaseException ex) {
            Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
        } catch (BusinessException ex) {
            JOptionPane.showMessageDialog(this, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jComboBox_rep_ProsItemStateChanged

    List<String> orderIds = new ArrayList<>();
    boolean second = false, isBoxesFirst = false;
    String typeFOrder, wieghtFOrder;
    JTable tableFOrder;
    List<String> fOrderIds = new ArrayList<>();

    private void jButton_rep_printRepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_rep_printRepActionPerformed
        evt.getID();
        boolean isBoxesSecond;

        double ss = jTextField_rep_totweight.getText().isEmpty() ? 0.0
                : util.ToDoubleEnglish(jTextField_rep_totweight.getText());
        try {
            if (jTable_rep_preview.getRowCount() >= 0 && !jTextField_rep_clientName.getText().isBlank()
                    && (jTable_rep_preview.getRowCount() == (int) util.ToDoubleEnglish(jTextField_rep_numOfBag.getText())
                    || !(util.ToDoubleEnglish(jTextField_rep_numOfBag.getText()) >= ss + repDiff))) {

                if (JOptionPane.showConfirmDialog(this, util.addStyle("سيتم التصدير للأكسل "), "تنبيه",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    String name = jTextField_rep_clientName.getText().split("تسليم")[0].strip();

                    if (jTable_rep_preview.getRowCount() > 60 && jCheckBox_rep_2n1.isSelected()) {
                        JOptionPane.showMessageDialog(this, util.addStyle("لا يمن عمل إذنين و عدد الشكائر أكثر من ٦٠ في الإذن الواحد"), "إنتبه",
                                JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    if (jTable_rep_preview.getRowCount() <= 60 && jCheckBox_rep_2n1.isSelected()) {
                        if (!second) {

                            typeFOrder = jComboBox_rep_Pros.getSelectedItem().toString();

                            isBoxesFirst = productController.getProduct(typeFOrder).isIsBox();

                            wieghtFOrder = jTextField_rep_totweight.getText();

                            fOrderIds = new ArrayList<>(orderIds);

                            tableFOrder = new JTable();
                            String[] columnNames = {"مسلسل", "وزن", "لوط", "رقم البالتة"};
                            DefaultTableModel d = new DefaultTableModel(columnNames, 0);
                            for (int i = 0; i < jTable_rep_preview.getRowCount(); i++) {
                                Object[] row = {jTable_rep_preview.getValueAt(i, 0),
                                    jTable_rep_preview.getValueAt(i, 1),
                                    jTable_rep_preview.getValueAt(i, 2),
                                    jTable_rep_preview.getValueAt(i, 3)};
                                d.addRow(row);
                            }
                            tableFOrder.setModel(d);
                            ((DefaultTableModel) jTable_rep_preview.getModel()).setRowCount(0);
                            jTextField_rep_numOfBag.setText("");
                            jTextField_rep_totweight.setText("");
                            jCheckBox_rep_wzn.setSelected(false);
                            jComboBox_rep_palletsNrep.removeAllItems();

                            JOptionPane.showMessageDialog(this, util.addStyle(" ادخل الأذن الثاني  "), "إنتبه",
                                    JOptionPane.INFORMATION_MESSAGE);

                        } else {
                            if (typeFOrder == jComboBox_rep_Pros.getSelectedItem() && tableFOrder.getValueAt(0, 2)
                                    .toString().equals(util.ToStringEnglish(jTable_rep_preview.getValueAt(0, 2).toString()))) {
                                JOptionPane.showMessageDialog(this, util.addStyle(" برجاء تغير الصنف أو اللوط  "), "إنتبه",
                                        JOptionPane.INFORMATION_MESSAGE);

                                second = !second;
                            } else {
                                isBoxesSecond = productController.getProduct(jComboBox_rep_Pros.getSelectedItem().toString()).isIsBox();
                                jFileChooser1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                                jFileChooser1.showSaveDialog(this);
                                String excelBackupPath = jFileChooser1.getSelectedFile().getAbsolutePath();
                                if (excelManager.excel_60_60(orderIds, fOrderIds, jTextField_rep_totweight.getText(), wieghtFOrder,
                                        jTextField_rep_clientName.getText(), jComboBox_rep_Pros.getSelectedItem().toString(), typeFOrder,
                                        isBoxesFirst, isBoxesSecond,
                                        jTable_rep_preview, tableFOrder, excelBackupPath)) {
                                    accessDataBase(name, jTextField_rep_totweight.getText(), orderIds);
                                    accessDataBase(name, wieghtFOrder, fOrderIds);

                                    ((DefaultTableModel) jTable_rep_preview.getModel()).setRowCount(0);
                                    ((DefaultTableModel) tableFOrder.getModel()).setRowCount(0);

                                    jCheckBox_rep_2n1.setSelected(false);
                                    jCheckBox_rep_wzn.setSelected(false);
                                    jTextField_rep_clientName.setText("");
                                    jTextField_rep_numOfBag.setText("");
                                    jTextField_rep_totweight.setText("");
                                } else {
                                    JOptionPane.showMessageDialog(this, util.addStyle(" حدث خطأ في عمل الاذن"), "إنتبه",
                                            JOptionPane.INFORMATION_MESSAGE);
                                }
                            }
                        }
                        second = !second;
                    } else if (jTable_rep_preview.getRowCount() <= 120) {
                        isBoxesSecond = productController.getProduct(jComboBox_rep_Pros.getSelectedItem().toString()).isIsBox();
                        jFileChooser1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                        jFileChooser1.showSaveDialog(this);
                        String excelBackupPath = jFileChooser1.getSelectedFile().getAbsolutePath();
                        if (excelManager.excel_120(orderIds, jTextField_rep_totweight.getText(),
                                jTextField_rep_clientName.getText(),
                                jComboBox_rep_Pros.getSelectedItem().toString(),
                                jTable_rep_preview, excelBackupPath, isBoxesSecond)) {
                            accessDataBase(name, jTextField_rep_totweight.getText(), orderIds);
                            ((DefaultTableModel) jTable_rep_preview.getModel()).setRowCount(0);

                            jTextField_rep_clientName.setText("");
                            jTextField_rep_numOfBag.setText("");
                            jTextField_rep_totweight.setText("");
                            jCheckBox_rep_wzn.setSelected(false);
                        } else {
                            JOptionPane.showMessageDialog(this, util.addStyle(" حدث خطأ في عمل الاذن"), "إنتبه",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }

                    } else if (jTable_rep_preview.getRowCount() <= 160) {
                        isBoxesSecond = productController.getProduct(jComboBox_rep_Pros.getSelectedItem().toString()).isIsBox();
                        jFileChooser1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                        jFileChooser1.showSaveDialog(this);
                        String excelBackupPath = jFileChooser1.getSelectedFile().getAbsolutePath();
                        if (excelManager.excel_160(orderIds, jTextField_rep_totweight.getText(),
                                jTextField_rep_clientName.getText(),
                                jComboBox_rep_Pros.getSelectedItem().toString(),
                                jTable_rep_preview, excelBackupPath, isBoxesSecond
                        )) {
                            accessDataBase(name, jTextField_rep_totweight.getText(), orderIds);
                            ((DefaultTableModel) jTable_rep_preview.getModel()).setRowCount(0);
                            jTextField_rep_clientName.setText("");
                            jTextField_rep_numOfBag.setText("");
                            jTextField_rep_totweight.setText("");
                            jCheckBox_rep_wzn.setSelected(false);
                        } else {
                            JOptionPane.showMessageDialog(this, util.addStyle(" حدث خطأ في عمل الاذن"), "إنتبه",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else if (jTable_rep_preview.getRowCount() <= 200) {
                        isBoxesSecond = productController.getProduct(jComboBox_rep_Pros.getSelectedItem().toString()).isIsBox();
                        jFileChooser1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                        jFileChooser1.showSaveDialog(this);
                        String excelBackupPath = jFileChooser1.getSelectedFile().getAbsolutePath();
                        if (excelManager.excel_200(orderIds, jTextField_rep_totweight.getText(),
                                jTextField_rep_clientName.getText(),
                                jComboBox_rep_Pros.getSelectedItem().toString(),
                                jTable_rep_preview, excelBackupPath, isBoxesSecond
                        )) {
                            accessDataBase(name, jTextField_rep_totweight.getText(), orderIds);
                            ((DefaultTableModel) jTable_rep_preview.getModel()).setRowCount(0);
                            jTextField_rep_clientName.setText("");
                            jTextField_rep_numOfBag.setText("");
                            jTextField_rep_totweight.setText("");
                            jCheckBox_rep_wzn.setSelected(false);
                        } else {
                            JOptionPane.showMessageDialog(this, util.addStyle(" حدث خطأ في عمل الاذن"), "إنتبه",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    jComboBox_rep_palletsNrep.removeAllItems();
                }
            } else {
                JOptionPane.showMessageDialog(this, util.addStyle(" تدخل البيانات كامله أولا"), "إنتبه",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (DatabaseException ex) {
            Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
        } catch (BusinessException ex) {
            JOptionPane.showMessageDialog(this, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_jButton_rep_printRepActionPerformed

    private void jTextField_rep_numOfBagKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_rep_numOfBagKeyTyped
        evt.getID();
        textbox_number(evt, jTextField_rep_numOfBag, jCheckBox_rep_wzn.isSelected() ? 4 : 3, false);
        ignorWightFromMizan(jTextField_rep_numOfBag, evt);
    }//GEN-LAST:event_jTextField_rep_numOfBagKeyTyped

    private void jTextField_rep_numOfBagKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_rep_numOfBagKeyReleased
        evt.getID();
        try {
            fill_Table_rep_select();
        } catch (DatabaseException ex) {
            Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
        } catch (BusinessException ex) {
            JOptionPane.showMessageDialog(this, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jTextField_rep_numOfBagKeyReleased

    private void jTextField_rep_totweightKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_rep_totweightKeyTyped
        evt.getID();
        evt.consume();
    }//GEN-LAST:event_jTextField_rep_totweightKeyTyped

    private void jTextField_rep_totweightKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_rep_totweightKeyPressed
        evt.getID();
        evt.consume();
    }//GEN-LAST:event_jTextField_rep_totweightKeyPressed

    private void jTable_rep_selectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_rep_selectMouseClicked
        evt.getID();
        try {
            if (evt.getButton() != MouseEvent.BUTTON1) {
                return;
            }
            if (!jTextField_rep_numOfBag.getText().isBlank()) {
                double weight_sum = 0.0;
                double currentTotalWeight = jTextField_rep_totweight.getText().isEmpty() ? 0.0
                        : util.ToDoubleEnglish(jTextField_rep_totweight.getText());
                boolean isSameLot = true;
                int bagsTakenFromPallet = 0;
                if (jCheckBox_rep_wzn.isSelected()) {
                    double wantedOrderWeight = util.ToDoubleEnglish(jTextField_rep_numOfBag.getText());
                    if (wantedOrderWeight >= currentTotalWeight + repDiff) {
                        if (jTable_rep_preview.getRowCount() > 0) {
                            if (!jTable_rep_preview.getValueAt(0, 2).toString()
                                    .equals(jTable_rep_select.getValueAt(jTable_rep_select.getSelectedRow(), 2))) {
                                isSameLot = false;
                            }
                        }
                        if (isSameLot) {
                            if (JOptionPane.showConfirmDialog(this,
                                    util.addStyle("هل تريد إضافه البالته رقم  "
                                            + jTable_rep_select.getValueAt(jTable_rep_select.getSelectedRow(), 3) + ""),
                                    "تنبيه",
                                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

                                List<Bag> bags = storageController.getBagsToReport(0, jComboBox_rep_Pros.getSelectedItem().toString(), jTable_rep_select.getModel()
                                        .getValueAt(jTable_rep_select.getSelectedRow(), 3).toString(), jTable_rep_select.getModel()
                                        .getValueAt(jTable_rep_select.getSelectedRow(), 2).toString());

                                boolean bagOutOfOrder = false;
                                ArrayList<String> OutOfOrderBags = new ArrayList<>();
                                for (Bag bag : bags) {
                                    if (wantedOrderWeight + repDiff > currentTotalWeight + weight_sum + bag.getWeight()) {

                                        bagsTakenFromPallet++;
                                        weight_sum += bag.getWeight();

                                        ((DefaultTableModel) jTable_rep_preview.getModel()).addRow(new Object[]{
                                            util.ToStringArabic((jTable_rep_preview.getRowCount() + 1) + ""),
                                            util.ToDoubleArabic(bag.getWeight()), util.ToStringArabic(bag.getLot()),
                                            util.ToStringArabic(bag.getPallet_numb() + ""), bag.isUsed()});

                                        orderIds.add(bag.getId() + "");

                                        if (((DefaultComboBoxModel) jComboBox_rep_palletsNrep.getModel())
                                                .getIndexOf(util.ToStringArabic(bag.getPallet_numb() + "")) == -1) {
                                            jComboBox_rep_palletsNrep.addItem(util.ToStringArabic(bag.getPallet_numb() + ""));
                                        }
                                        if (bagOutOfOrder) {
                                            OutOfOrderBags.add(util.ToStringArabic(bag.getWeight() + "") + "");
                                        }
                                    } else {
                                        if (!bagOutOfOrder) {
                                            if (JOptionPane.showConfirmDialog(this,
                                                    util.addStyle("هل تريد إضافه شكائر خارج الترتيب إن أمكن؟"),
                                                    "خارج الترتيب",
                                                    JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
                                                break;
                                            } else {
                                                bagOutOfOrder = true;
                                            }
                                        }
                                    }
                                }
                                if (bagOutOfOrder) {
                                    if (OutOfOrderBags.isEmpty()) {
                                        JOptionPane.showMessageDialog(this, util.addStyle("لم يتم إضافة شكائر"),
                                                "ملحوظة", JOptionPane.INFORMATION_MESSAGE);
                                    } else {
                                        JOptionPane.showMessageDialog(this,
                                                util.addStyle("الشكائر هى: " + OutOfOrderBags.toString()), "ملحوظة",
                                                JOptionPane.INFORMATION_MESSAGE);
                                    }
                                }

                                currentTotalWeight += weight_sum;
                                jTextField_rep_totweight.setText(util.ToDoubleArabic(currentTotalWeight));

                                jTable_rep_select.getModel().setValueAt(util.ToDoubleArabic(util.ToDoubleEnglish(jTable_rep_select.getModel()
                                        .getValueAt(jTable_rep_select.getSelectedRow(), 1).toString()) - weight_sum),
                                        jTable_rep_select.getSelectedRow(), 1);
                                jTable_rep_select.getModel().setValueAt(util.ToStringArabic((Integer.parseInt(jTable_rep_select.getModel()
                                        .getValueAt(jTable_rep_select.getSelectedRow(), 0).toString()) - bagsTakenFromPallet) + ""), jTable_rep_select.getSelectedRow(), 0);

                                if (wantedOrderWeight >= util.ToDoubleEnglish(jTable_rep_select.getModel()
                                        .getValueAt(jTable_rep_select.getSelectedRow(), 1).toString()) + currentTotalWeight
                                        || util.ToDoubleEnglish(jTable_rep_select.getValueAt(jTable_rep_select.getSelectedRow(), 0).toString()) <= 0.0
                                        || util.ToDoubleEnglish(jTable_rep_select.getValueAt(jTable_rep_select.getSelectedRow(), 1).toString()) <= 0.0) {

                                    ((DefaultTableModel) jTable_rep_select.getModel()).removeRow(jTable_rep_select.getSelectedRow());

                                }
                                jTable_rep_preview.changeSelection(jTable_rep_preview.getRowCount() - 1, 0, false, false);
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, util.addStyle("لا يمكن ادخال اكثر من لوط  "), "إنتبه",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, util.addStyle(" لقد اكتمل الوزن "), "إنتبه",
                                JOptionPane.INFORMATION_MESSAGE);
                    }

                } else {
                    if ((int) util.ToDoubleEnglish(jTextField_rep_numOfBag.getText()) > 200) {
                        JOptionPane.showMessageDialog(this, util.addStyle("رجاء ادخل  عدد أقل من  ٢٠١"), "إنتبه",
                                JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    if ((int) util.ToDoubleEnglish(jTextField_rep_numOfBag.getText()) != jTable_rep_preview.getRowCount()) {
                        isSameLot = true;
                        if (jTable_rep_preview.getRowCount() > 0) {
                            if (!jTable_rep_preview.getValueAt(0, 2).toString()
                                    .equals(jTable_rep_select.getValueAt(jTable_rep_select.getSelectedRow(), 2))) {
                                isSameLot = false;
                            }
                        }
                        if (isSameLot) {
                            if (JOptionPane.showConfirmDialog(this,
                                    util.addStyle("هل تريد إضافه البالته رقم  "
                                            + jTable_rep_select.getValueAt(jTable_rep_select.getSelectedRow(), 3) + ""),
                                    "تنبيه",
                                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                                int wantedOrderQuantity = (int) util.ToDoubleEnglish(jTextField_rep_numOfBag.getText());
                                if (wantedOrderQuantity > 0 && jTable_rep_preview.getRowCount() < wantedOrderQuantity) {
                                    List<Bag> bags = storageController.getBagsToReport((wantedOrderQuantity - jTable_rep_preview.getRowCount()),
                                            jComboBox_rep_Pros.getSelectedItem().toString(),
                                            jTable_rep_select.getModel().getValueAt(jTable_rep_select.getSelectedRow(), 3).toString(),
                                            jTable_rep_select.getModel().getValueAt(jTable_rep_select.getSelectedRow(), 2).toString());

                                    for (Bag bag : bags) {
                                        bagsTakenFromPallet++;
                                        weight_sum += bag.getWeight();
                                        ((DefaultTableModel) jTable_rep_preview.getModel()).addRow(new Object[]{
                                            util.ToStringArabic(jTable_rep_preview.getRowCount() + 1 + ""), util.ToDoubleArabic(bag.getWeight()),
                                            util.ToStringArabic(bag.getLot()), util.ToStringArabic(bag.getPallet_numb() + ""), bag.isUsed()});

                                        orderIds.add(bag.getId() + "");

                                        if (((DefaultComboBoxModel) jComboBox_rep_palletsNrep.getModel())
                                                .getIndexOf(util.ToStringArabic(bag.getPallet_numb() + "")) == -1) {
                                            jComboBox_rep_palletsNrep.addItem(util.ToStringArabic(bag.getPallet_numb() + ""));
                                        }

                                    }

                                    currentTotalWeight += weight_sum;
                                    jTextField_rep_totweight.setText(util.ToDoubleArabic(currentTotalWeight));

                                    jTable_rep_select.getModel().setValueAt(util.ToDoubleArabic(util.ToDoubleEnglish(jTable_rep_select.getModel()
                                            .getValueAt(jTable_rep_select.getSelectedRow(), 1).toString()) - weight_sum),
                                            jTable_rep_select.getSelectedRow(), 1);
                                    jTable_rep_select.getModel().setValueAt(util.ToStringArabic((Integer.parseInt(jTable_rep_select.getModel()
                                            .getValueAt(jTable_rep_select.getSelectedRow(), 0).toString()) - bagsTakenFromPallet) + ""), jTable_rep_select.getSelectedRow(), 0);

                                    if (wantedOrderQuantity >= util.ToDoubleEnglish(jTable_rep_select.getModel().getValueAt(jTable_rep_select.getSelectedRow(), 0).toString())
                                            && jTable_rep_preview.getRowCount() < wantedOrderQuantity
                                            || util.ToDoubleEnglish(jTable_rep_select.getValueAt(jTable_rep_select.getSelectedRow(), 0).toString()) == 0.0
                                            || util.ToDoubleEnglish(jTable_rep_select.getValueAt(jTable_rep_select.getSelectedRow(), 1).toString()) == 0.0) {

                                        ((DefaultTableModel) jTable_rep_select.getModel()).removeRow(jTable_rep_select.getSelectedRow());
                                    }
                                }
                                jTable_rep_preview.changeSelection(jTable_rep_preview.getRowCount() - 1, 0, false, false);
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, util.addStyle("لا يمكن ادخال اكثر من لوط  "), "إنتبه",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, util.addStyle(" لقد اكتمل العدد "), "إنتبه",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, util.addStyle("برجاء ادخال عدد الشكاير"), "إنتبه",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (DatabaseException ex) {
            Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jTable_rep_selectMouseClicked

    private void jTable_storageKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable_storageKeyTyped
        evt.getID();
        if (evt.getKeyChar() == KeyEvent.VK_DELETE && jTable_storage.hasFocus()) {
            jButton_del_data.doClick();
        }
    }//GEN-LAST:event_jTable_storageKeyTyped

    private void jButton_DoBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_DoBackActionPerformed
        evt.getID();
        try {
            jFileChooser1.setFileSelectionMode(JFileChooser.FILES_ONLY);
            jFileChooser1.setFileFilter(new FileNameExtensionFilter("BAK file", "bak"));
            jFileChooser1.showSaveDialog(this);
            if (jFileChooser1.getSelectedFile() != null) {
                opj.backup(jFileChooser1.getSelectedFile().getAbsolutePath() + " " + LocalDate.now() + ".bak");
                JOptionPane.showMessageDialog(this, util.addStyle("Back up succes "), "3aaash",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton_DoBackActionPerformed

    private void jTextField_rep_clientNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_rep_clientNameKeyTyped
        evt.getID();
        ignorWightFromMizan(jTextField_rep_clientName, evt);
    }//GEN-LAST:event_jTextField_rep_clientNameKeyTyped

    private void jTextField_rep_clientNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_rep_clientNameFocusGained
        evt.getID();
        jTextField_rep_clientName.selectAll();
    }//GEN-LAST:event_jTextField_rep_clientNameFocusGained

    private void jTextField_pallet_numFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_pallet_numFocusGained
        evt.getID();
        jTextField_pallet_num.selectAll();
    }//GEN-LAST:event_jTextField_pallet_numFocusGained

    private void jButton_Stock_openerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Stock_openerActionPerformed
        evt.getID();
        try {
            open_panel(stock_panel);
            ((DefaultTableModel) jTable_stock.getModel()).setRowCount(0);
            combox_fill(jComboBox_stock_Pros, true);
        } catch (DatabaseException ex) {
            Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton_Stock_openerActionPerformed

    private void jComboBox_stock_ProsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_stock_ProsItemStateChanged
        evt.getID();
        try {
            if (jComboBox_stock_Pros.getSelectedIndex() != -1 && jComboBox_stock_Pros.hasFocus()) {
                DefaultTableModel model = (DefaultTableModel) jTable_stock.getModel();
                model.setRowCount(0);
                List<String[]> stock = storageController.getStockOfProduct(jComboBox_stock_Pros.getSelectedItem().toString());

                for (String[] row : stock) {
                    model.addRow(new Object[]{util.ToStringArabic(row[0]), util.ToStringArabic(row[1]),
                        util.ToStringArabic(row[2]), util.ToStringArabic(row[3])});
                }
            }
        } catch (DatabaseException ex) {
            Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jComboBox_stock_ProsItemStateChanged

    private void jTextField_num_of_conFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_num_of_conFocusGained
        evt.getID();
        if (jCheckBox_freeze_cone.isSelected())
            jTextField_bag_weight.requestFocusInWindow();
        else
            jTextField_num_of_con.selectAll();
    }//GEN-LAST:event_jTextField_num_of_conFocusGained

    private void jButton_Statics_openerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Statics_openerActionPerformed
        evt.getID();
        open_panel(statistics_panel);
        ((DefaultTableModel) jTable_statis.getModel()).setRowCount(0);
        jTable_statis.setAutoCreateRowSorter(true);
        jDateChooser_statis_fromDate.setCalendar(null);
        jDateChooser_statis_toDate.setCalendar(null);
    }//GEN-LAST:event_jButton_Statics_openerActionPerformed

    private void jButton_Outs_openerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Outs_openerActionPerformed
        evt.getID();
        open_panel(showPermit_panel);
        jDateChooser_yum_fromDate.setCalendar(null);
        jDateChooser_yum_ToDate.setCalendar(null);
        jTable_youm_clinets.setAutoCreateRowSorter(true);
        jTable_yumia.setAutoCreateRowSorter(true);
    }//GEN-LAST:event_jButton_Outs_openerActionPerformed

    private void jButton_youm_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_youm_searchActionPerformed
        evt.getID();
        try {
            String selectedCIDs = "";
            if (jTable_youm_clinets.getSelectedRowCount() > 0) {
                for (int selectedRow : jTable_youm_clinets.getSelectedRows()) {
                    selectedCIDs += jTable_youm_clinets.getValueAt(selectedRow, 0) + ",";
                }
                selectedCIDs = selectedCIDs.substring(0, selectedCIDs.length() - 1);
            }
            if (jDateChooser_yum_fromDate.getCalendar() != null && jDateChooser_yum_ToDate.getCalendar() != null) {
                DefaultTableModel model = (DefaultTableModel) jTable_yumia.getModel();
                model.setRowCount(0);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String dateFrom = sdf.format(jDateChooser_yum_fromDate.getCalendar().getTime());
                String dateTo = sdf.format(jDateChooser_yum_ToDate.getCalendar().getTime());

                List<String[]> yumya = exportController.getYuwmya(jCheckBox_youm_old.isSelected(), dateFrom, dateTo, selectedCIDs);
                for (String[] row : yumya) {
                    model.addRow(new Object[]{util.ToStringArabic(row[2]), util.ToStringArabic(row[1]),
                        util.ToStringArabic(row[3]), util.ToStringArabic(row[5]),
                        util.ToStringArabic(row[0]), util.ToStringArabic(row[4])});
                }
            }
        } catch (DatabaseException ex) {
            Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton_youm_searchActionPerformed

    private void jCheckBox_M_MarkpageKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCheckBox_M_MarkpageKeyPressed
        evt.getID();
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            if (!jTextField_net_weight.getText().isBlank()) {
                jButton_add_data.doClick();
            }
        }
    }//GEN-LAST:event_jCheckBox_M_MarkpageKeyPressed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        evt.getID();
        try {
            Object[] options1 = {util.addStyle("تسجيل الخروج "), util.addStyle(" قفل البرنامج ")};
            int result = JOptionPane.showOptionDialog(this, util.addStyle("خد بالك ياجدع"), "انتبه ",
                    JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options1, null);
            if (result == JOptionPane.YES_OPTION) {
                saveConfig();
                //readConfig();
                login_form opj11 = new login_form();
                opj11.setVisible(true);
                this.dispose();
            }
            if (result == JOptionPane.NO_OPTION) {
                saveConfig();
                //readConfig();
                System.exit(NORMAL);
            }
        } catch (HeadlessException | DatabaseException ex) {
            Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
        } catch (BusinessException ex) {
            JOptionPane.showMessageDialog(this, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_formWindowClosing

    private void jButton_youm_refundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_youm_refundActionPerformed
        evt.getID();
        try {
            if (jButton_Ezn_opener.isEnabled() && jButton_addPro_opener.isEnabled()) {
                if (jTable_yumia.getSelectedRow() != -1) {
                    if (JOptionPane.showConfirmDialog(this, util.addStyle(
                            "هل تريد استرجاع أزن " + jTable_yumia.getValueAt(jTable_yumia.getSelectedRow(), 0) + " لصنف"
                            + jTable_yumia.getValueAt(jTable_yumia.getSelectedRow(), 1) + " في يوم"
                            + jTable_yumia.getValueAt(jTable_yumia.getSelectedRow(), 5) + " "),
                            "تنبيه",
                            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        exportController.moveBagFromExportToStorage(jCheckBox_youm_old.isSelected(), jTable_yumia.getValueAt(jTable_yumia.getSelectedRow(), 2) + "",
                                jTable_yumia.getValueAt(jTable_yumia.getSelectedRow(), 1) + "",
                                jTable_yumia.getValueAt(jTable_yumia.getSelectedRow(), 0) + "",
                                jTable_yumia.getValueAt(jTable_yumia.getSelectedRow(), 5) + "");

                        jButton_youm_search.doClick();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, util.addStyle(" يجب اختيار بيان من الجدول "), "إنتبه",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, util.addStyle("لا يمكن اجراء العمليه "), "إنتبه", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (DatabaseException ex) {
            Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
        } catch (BusinessException ex) {
            JOptionPane.showMessageDialog(this, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton_youm_refundActionPerformed

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
                        jTextField_E_PaltNum.getText(), jCheckBox_E_Mark.isSelected());
                this.setEnabled(true);
                fill_storage_table();
                SingleEdit.dispose();
                JOptionPane.showMessageDialog(this, util.addStyle(" تم تعديل البيانات بنجاح  "), "إنتبه",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, util.addStyle("برجاء ادخال البيانات صحيحه "), "إنتبه",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (DatabaseException ex) {
            Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(SingleEdit, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
        } catch (BusinessException ex) {
            JOptionPane.showMessageDialog(SingleEdit, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton_E_EditActionPerformed

    private void jTextField_E_WightKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_E_WightKeyTyped
        evt.getID();
        textbox_number_weight(evt, jTextField_E_Wight, 999);
    }//GEN-LAST:event_jTextField_E_WightKeyTyped

    private void jTextField_E_PaltNumKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_E_PaltNumKeyTyped
        evt.getID();
        textbox_number(evt, jTextField_E_PaltNum, 999, false);
    }//GEN-LAST:event_jTextField_E_PaltNumKeyTyped

    private void jTextField_E_ConNumKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_E_ConNumKeyTyped
        evt.getID();
        textbox_number(evt, jTextField_E_ConNum, 999, false);
        jTextField_E_Wight.setText(util.ToDoubleArabic(
                (((util.ToDoubleEnglish(jTextField_E_O_ConNum.getText()) - util.ToDoubleEnglish(jTextField_E_ConNum.getText()))
                * (util.ToDoubleEnglish(jTextField_weight_of_con.getText()) / 1000))
                + util.ToDoubleEnglish(jTextField_E_O_Wight.getText()))));
    }//GEN-LAST:event_jTextField_E_ConNumKeyTyped

    private void jTextField_E_lotKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_E_lotKeyTyped
        evt.getID();
        textbox_length_limiter(evt, jTextField_E_lot, 5);
        char input = evt.getKeyChar();
        if (Character.isDigit(input)) {
            evt.setKeyChar(util.ToNumArab(input));
        }
        if ((!Character.isDigit(input)) && input != 'أ' && input != 'س' && input != 'و' && input != 'د' && input != 'ط'
                && input != 'ب' && input != 'ا' && input != 'ع' && input != 'ه' && input != 'م' && input != 'ن'
                && input != 'ي' && input != 'ض') {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField_E_lotKeyTyped

    private void jTextField_Pros_conWightKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_Pros_conWightKeyTyped
        evt.getID();
        evt.setKeyChar(util.ToNumArab(evt.getKeyChar()));
        textbox_number(evt, jTextField_Pros_conWight, 4, false);
    }//GEN-LAST:event_jTextField_Pros_conWightKeyTyped

    private void jButton_Emp_openerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Emp_openerActionPerformed
        evt.getID();
        open_panel(pause_panel);
        jTextArea_emp.requestFocusInWindow();
        jTextArea_emp.setText("");
    }//GEN-LAST:event_jButton_Emp_openerActionPerformed

    private void jTextArea_empKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea_empKeyTyped
        evt.getID();
        evt.setKeyChar(util.ToNumArab(evt.getKeyChar()));
    }//GEN-LAST:event_jTextArea_empKeyTyped

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
                    jTextField_E_O_TotWight.setText(util.ToDoubleArabic(bag.getTot_wight()));
                    jTextField_E_TotWight.setText(util.ToDoubleArabic(bag.getTot_wight()));
                    jComboBox_E_O_proName.setSelectedItem(product);
                    jComboBox_E_proName.setSelectedItem(product);
                    jTextField_E_O_lot.setText(util.ToStringArabic(bag.getLot()));
                    jTextField_E_lot.setText(util.ToStringArabic(bag.getLot()));
                    jTextField_E_O_ConNum.setText(util.ToStringArabic(bag.getNum_of_con() + ""));
                    jTextField_E_ConNum.setText(util.ToStringArabic(bag.getNum_of_con() + ""));
                    jTextField_E_O_PaltNum.setText(util.ToStringArabic(bag.getPallet_numb() + ""));
                    jTextField_E_PaltNum.setText(util.ToStringArabic(bag.getPallet_numb() + ""));
                    jTextField_E_O_Wight.setText(util.ToDoubleArabic(bag.getWeight()));
                    jTextField_E_Wight.setText(util.ToDoubleArabic(bag.getWeight()));
                    jTextField_E_Color.setText(product.getColor());

                } else if (jTable_storage.getSelectedRowCount() > 1) {
                    MultiEdit.setVisible(true);
                    this.setEnabled(false);
                    jComboBox_ME_type.setSelectedItem(jComboBox_pro_in_storage.getSelectedItem());
                    jTextField_ME_PaltNum
                            .setText(jTable_storage.getModel().getValueAt(jTable_storage.getSelectedRow(), 3).toString());
                    jTextField_ME_lot
                            .setText(jTable_storage.getModel().getValueAt(jTable_storage.getSelectedRow(), 2).toString());
                    jCheckBox_ME_MarkBag.setSelected((boolean) jTable_storage.getModel()
                            .getValueAt(jTable_storage.getSelectedRow(), 6));
                }
            }
        } catch (DatabaseException ex) {
            Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
        } catch (BusinessException ex) {
            JOptionPane.showMessageDialog(this, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jTable_storageMouseReleased

    private void jButton_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_clearActionPerformed
        evt.getID();
        if (!jCheckBox_freeze_wight.isSelected()) {
            jTextField_bag_weight.setText("");
        }
        jTextField_weight.setText("");
        jTextField_net_weight.setText("");
        jTextField_num_of_con.requestFocus();
        if (!jCheckBox_freeze_cone.isSelected()) {
            jTextField_num_of_con.selectAll();
        }
    }//GEN-LAST:event_jButton_clearActionPerformed

    private void jTextField_bag_weightFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_bag_weightFocusGained
        evt.getID();
        if (jCheckBox_freeze_wight.isSelected())
            jTextField_weight.requestFocusInWindow();
        else
            jTextField_bag_weight.selectAll();
    }//GEN-LAST:event_jTextField_bag_weightFocusGained

    private void jTextField_weightFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_weightFocusGained
        evt.getID();
        jTextField_weight.selectAll();
    }//GEN-LAST:event_jTextField_weightFocusGained

    private void jButton_E_printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_E_printActionPerformed
        evt.getID();
        try {
            if (jComboBox_pro_in_storage.getSelectedItem().toString().length() > 11) {
                jLabel_print_ValType.setFont(new Font("Arial", Font.PLAIN, 14));
            } else {
                jLabel_print_ValType.setFont(new Font("Arial", Font.PLAIN, 32));
            }

            jLabel_print_ValPallet.setText(jTextField_E_PaltNum.getText());
            jLabel_print_ValColor.setText(jTextField_E_Color.getText());
            jLabel_print_ValType.setText(jComboBox_E_proName.getSelectedItem().toString());
            jLabel_print_ValLot.setText(jTextField_E_lot.getText());
            jLabel_print_ValNCone.setText(jTextField_E_ConNum.getText());
            jLabel_print_ValTotalWeight.setText(jTextField_E_TotWight.getText());
            jLabel_print_ValNetWeight.setText(jTextField_E_Wight.getText());
            printerManager.printTickets(new ArrayList<>(Arrays.asList(
                    jTextField_E_PaltNum.getText(),
                    jTextField_E_Color.getText(),
                    jComboBox_E_proName.getSelectedItem().toString(),
                    jTextField_E_lot.getText(),
                    jTextField_E_ConNum.getText(),
                    jTextField_E_TotWight.getText(),
                    jTextField_E_Wight.getText())),
                    jPanel_print,
                    jCheckBox_E_P.isSelected(), jCheckBox_E_QR.isSelected(),
                    jCheckBox_set_printExcel.isSelected());
            incTicketCounters(jCheckBox_E_P.isSelected(), jCheckBox_E_QR.isSelected());
        } catch (BusinessException ex) {
            JOptionPane.showMessageDialog(SingleEdit, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton_E_printActionPerformed

    private void jTextField_ColorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_ColorKeyTyped
        evt.getID();

        if (Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField_ColorKeyTyped

    private void jTextField_E_TotWightKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_E_TotWightKeyTyped
        evt.getID();

        textbox_number_weight(evt, jTextField_E_TotWight, 999);
        try {
            jTextField_E_Wight.setText(util.ToDoubleArabic(
                    ((util.ToDoubleEnglish(jTextField_E_O_TotWight.getText())
                    - util.ToDoubleEnglish(jTextField_E_TotWight.getText()))
                    + util.ToDoubleEnglish(jTextField_E_O_Wight.getText()))));
        } catch (Exception e) {
            System.out.println("exp");
        }

    }//GEN-LAST:event_jTextField_E_TotWightKeyTyped

    private void jButton_Reset_TicketCount2x2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Reset_TicketCount2x2ActionPerformed
        evt.getID();
        try {
            tick2x2 = 0;
            saveConfig();
            readConfig();
            jLabel_Ticket2x2Counter.setText("" + tick2x2);
        } catch (BusinessException ex) {
            JOptionPane.showMessageDialog(this, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_jButton_Reset_TicketCount2x2ActionPerformed

    private void jButton_Reset_TicketCount10x10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Reset_TicketCount10x10ActionPerformed
        evt.getID();
        try {
            tick10x10 = 0;
            saveConfig();
            readConfig();
            jLabel_Ticket10x10Counter.setText("" + tick10x10);
        } catch (BusinessException ex) {
            JOptionPane.showMessageDialog(this, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton_Reset_TicketCount10x10ActionPerformed

    private void jTextField_ME_lotKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_ME_lotKeyTyped
        evt.getID();

        textbox_length_limiter(evt, jTextField_ME_lot, 5);
        char input = evt.getKeyChar();
        if (Character.isDigit(input)) {
            evt.setKeyChar(util.ToNumArab(input));
        }
        if ((!Character.isDigit(input)) && input != 'أ' && input != 'س' && input != 'و' && input != 'د' && input != 'ط'
                && input != 'ب' && input != 'ا' && input != 'ع' && input != 'ه' && input != 'م' && input != 'ن'
                && input != 'ي' && input != 'ض') {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField_ME_lotKeyTyped

    private void jTextField_ME_PaltNumKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_ME_PaltNumKeyTyped
        evt.getID();

        textbox_number(evt, jTextField_E_PaltNum, 999, false);
    }//GEN-LAST:event_jTextField_ME_PaltNumKeyTyped

    private void jButton_ME_EditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ME_EditActionPerformed
        evt.getID();
        try {
            if (!jTextField_ME_PaltNum.getText().isBlank() || !jTextField_ME_lot.getText().isBlank()) {
                for (int i = jTable_storage.getSelectedRows().length - 1; i > -1; i--) {
                    Bag bag = storageController.getBagById(Integer.parseInt(jTable_storage.getModel().getValueAt(jTable_storage.getSelectedRows()[i], 4).toString()));
                    storageController.updateStorage(bag.getId(),
                            jComboBox_ME_type.getSelectedItem().toString(), bag.getTot_wight() + "", bag.getWeight() + "",
                            jTextField_ME_lot.getText(), bag.getNum_of_con() + "", jTextField_ME_PaltNum.getText(), jCheckBox_ME_MarkBag.isSelected());
                }
                MultiEdit.dispose();
                JOptionPane.showMessageDialog(MultiEdit, util.addStyle(" تم تعديل البيانات بنجاح  "), "إنتبه",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(MultiEdit, util.addStyle("رجاء أدخل بيانات كاملة"), "إنتبه",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            this.setEnabled(true);
            fill_storage_table();
            MultiEdit.dispose();
        } catch (DatabaseException ex) {
            Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(MultiEdit, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
        } catch (BusinessException ex) {
            JOptionPane.showMessageDialog(MultiEdit, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton_ME_EditActionPerformed

    private void MultiEditWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_MultiEditWindowClosing
        evt.getID();
        this.setEnabled(true);
    }//GEN-LAST:event_MultiEditWindowClosing

    private void jTextField_E_ColorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_E_ColorKeyTyped
        evt.getID();
        if (Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField_E_ColorKeyTyped

    private void jButton_stock_createExclActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_stock_createExclActionPerformed
        evt.getID();
        try {
            excelManager.stockExcel(storageController.getAllStock());
            JOptionPane.showMessageDialog(this, util.addStyle("please print the Execl"), "Done", JOptionPane.INFORMATION_MESSAGE);
        } catch (DatabaseException ex) {
            Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
        } catch (BusinessException ex) {
            JOptionPane.showMessageDialog(this, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton_stock_createExclActionPerformed

    private void jButton_reprintLastTicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_reprintLastTicketActionPerformed
        evt.getID();
        try {
            if (jCheckBox_set_printExcel.isSelected()) {
                printerManager.print_excel_ticket();
            } else {
                printerManager.printPanelToImage(jPanel_print);
            }
            incTicketCounters(true, false);
            this.jTextField_num_of_con.requestFocusInWindow();
        } catch (BusinessException ex) {
            JOptionPane.showMessageDialog(this, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton_reprintLastTicketActionPerformed

    private void jButton_youm_getClientsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_youm_getClientsActionPerformed
        evt.getID();
        try {
            DefaultTableModel model = (DefaultTableModel) jTable_youm_clinets.getModel();
            model.setRowCount(0);
            List<Client> clients = clientController.getClientLike(jTextField_youm_ClientFilter.getText());
            for (Client client : clients) {
                model.addRow(new Object[]{client.getId(), client.getName()});
            }
        } catch (DatabaseException ex) {
            Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton_youm_getClientsActionPerformed

    private void jTextField_youm_ClientFilterKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_youm_ClientFilterKeyTyped
        evt.getID();
        if (evt.getKeyChar() == '\'') {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField_youm_ClientFilterKeyTyped

    private void jButton_youm_createExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_youm_createExcelActionPerformed
        evt.getID();
        int RowIndex = 4;
        XSSFWorkbook workbook;
        try (FileInputStream EX = new FileInputStream(new File("Donot_Change\\report.xlsx"))) {
            workbook = new XSSFWorkbook(EX);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Cell cell = sheet.getRow(1).getCell(1);
            cell.setCellValue("الاستاذ :   " + jTable_yumia.getValueAt(0, 0) + "");
            jTable_yumia.selectAll();
            for (int selectedRow : jTable_yumia.getSelectedRows()) {
                if (RowIndex >= 6) {
                    CellCopyPolicy poli = new CellCopyPolicy();
                    poli.setCopyCellStyle(true);
                    poli.setCopyCellValue(true);
                    sheet.copyRows(RowIndex - 1, RowIndex, RowIndex, poli);
                }
                cell = sheet.getRow(RowIndex).getCell(1);
                cell.setCellValue(jTable_yumia.getValueAt(selectedRow, 1).toString());

                cell = sheet.getRow(RowIndex).getCell(2);
                cell.setCellValue(jTable_yumia.getValueAt(selectedRow, 2).toString());

                cell = sheet.getRow(RowIndex).getCell(3);
                cell.setCellValue(jTable_yumia.getValueAt(selectedRow, 3).toString());

                cell = sheet.getRow(RowIndex).getCell(4);
                cell.setCellValue(jTable_yumia.getValueAt(selectedRow, 4).toString());

                cell = sheet.getRow(RowIndex).getCell(5);
                cell.setCellValue(jTable_yumia.getValueAt(selectedRow, 5).toString());

                RowIndex++;
            }

            try (FileOutputStream fileOut = new FileOutputStream(
                    System.getProperty("user.dir") + "\\Temp\\report.xlsx")) {
                workbook.write(fileOut);
            }
            JOptionPane.showMessageDialog(this, util.addStyle("please print the Execl"), "Done", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton_youm_createExcelActionPerformed

    private void jTable_yumiaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_yumiaMouseReleased
        evt.getID();
        try {
            if (evt.getClickCount() == 3) {
                TableModel model = jTable_yumia.getModel();
                String temp = exportController.getPalletsForOrder(model.getValueAt(jTable_yumia.getSelectedRow(), 1).toString(),
                        model.getValueAt(jTable_yumia.getSelectedRow(), 0).toString(),
                        model.getValueAt(jTable_yumia.getSelectedRow(), 2).toString(),
                        model.getValueAt(jTable_yumia.getSelectedRow(), 5).toString(),
                        model.getValueAt(jTable_yumia.getSelectedRow(), 4).toString());
                JOptionPane.showMessageDialog(this, temp, "Pallets", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (DatabaseException ex) {
            JOptionPane.showMessageDialog(this, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_jTable_yumiaMouseReleased

    private void jButton_reps_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_reps_clearActionPerformed
        evt.getID();

        ((DefaultTableModel) jTable_rep_select.getModel()).setRowCount(0);
        ((DefaultTableModel) jTable_rep_preview.getModel()).setRowCount(0);
        jComboBox_rep_Pros.setSelectedIndex(-1);
        second = false;
        jTextField_rep_totweight.setText("");
        jComboBox_rep_palletsNrep.removeAllItems();
    }//GEN-LAST:event_jButton_reps_clearActionPerformed

    private void jTextField_setting_repsDiffKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_setting_repsDiffKeyTyped
        evt.getID();

        textbox_number(evt, jTextField_setting_repsDiff, 2, false);
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            repDiff = Integer.parseInt(util.ToStringEnglish(jTextField_setting_repsDiff.getText()));
        }
    }//GEN-LAST:event_jTextField_setting_repsDiffKeyTyped

    private void jButton_statistics_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_statistics_searchActionPerformed
        evt.getID();
        fill_Statistics_table();
    }//GEN-LAST:event_jButton_statistics_searchActionPerformed

    private void jButton_statis_createExclActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_statis_createExclActionPerformed
        evt.getID();

        if (jDateChooser_statis_fromDate.getCalendar() != null && jDateChooser_statis_toDate.getCalendar() != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date1 = sdf.format(jDateChooser_statis_fromDate.getCalendar().getTime());
                String date2 = sdf.format(jDateChooser_statis_toDate.getCalendar().getTime());
                List<String[]> statistics = exportController.getstatistics(date1, date2);
                if (excelManager.staticsticsExcel(statistics, date1, date2)) {

                    JOptionPane.showMessageDialog(this, util.addStyle("please print the Execl"), "Done",
                            JOptionPane.INFORMATION_MESSAGE);
                }

            } catch (DatabaseException ex) {
                JOptionPane.showMessageDialog(this, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
                Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BusinessException ex) {
                JOptionPane.showMessageDialog(this, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton_statis_createExclActionPerformed

    private void jCheckBox_rep_wznActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_rep_wznActionPerformed
        evt.getID();

        try {
            fill_Table_rep_select();
            jLabel_Order_num.setText(!jCheckBox_rep_wzn.isSelected() ? "عدد الشكاير" : "الوزن المطلوب");
        } catch (DatabaseException ex) {
            Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
        } catch (BusinessException ex) {
            JOptionPane.showMessageDialog(this, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jCheckBox_rep_wznActionPerformed

    private void jTextField_Pros_colorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_Pros_colorKeyTyped
        // TODO add your handling code here:
        evt.getID();
        sendToWight(jTextField_Pros_color, jTextField_Pros_conWight, evt);
    }//GEN-LAST:event_jTextField_Pros_colorKeyTyped

    private void jTextField_Search_prosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_Search_prosKeyTyped
        // TODO add your handling code here:
        evt.getID();
        try {
            evt.setKeyChar(util.ToNumArab(evt.getKeyChar()));

            if (evt.getKeyChar() == KeyEvent.VK_DELETE) {
                jTextField_Search_pros.setText("");
            }
            combox_fill(jComboBox_pro_in_storage, false);
        } catch (DatabaseException ex) {
            Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jTextField_Search_prosKeyTyped

    private void jButton_set_changePosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_set_changePosActionPerformed
        // TODO add your handling code here:
        switch (jLabel_print_ValPallet.getHorizontalAlignment()) {
            case javax.swing.SwingConstants.CENTER ->
                jLabel_print_ValPallet.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
            case javax.swing.SwingConstants.LEADING ->
                jLabel_print_ValPallet.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
            case javax.swing.SwingConstants.TRAILING ->
                jLabel_print_ValPallet.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            default ->
                jLabel_print_ValPallet.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        }

        switch (jLabel_print_ValLot.getHorizontalAlignment()) {
            case javax.swing.SwingConstants.CENTER ->
                jLabel_print_ValLot.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
            case javax.swing.SwingConstants.LEADING ->
                jLabel_print_ValLot.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
            case javax.swing.SwingConstants.TRAILING ->
                jLabel_print_ValLot.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            default ->
                jLabel_print_ValLot.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        }

        switch (jLabel_print_ValNetWeight.getHorizontalAlignment()) {
            case javax.swing.SwingConstants.CENTER ->
                jLabel_print_ValNetWeight.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
            case javax.swing.SwingConstants.LEADING ->
                jLabel_print_ValNetWeight.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
            case javax.swing.SwingConstants.TRAILING ->
                jLabel_print_ValNetWeight.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            default ->
                jLabel_print_ValNetWeight.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        }

    }//GEN-LAST:event_jButton_set_changePosActionPerformed

    private void jCheckBox_set_printExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_set_printExcelActionPerformed
        // TODO add your handling code here:
        jButton_set_changePos.setEnabled(!jCheckBox_set_printExcel.isSelected());
    }//GEN-LAST:event_jCheckBox_set_printExcelActionPerformed

    private void jButton_set_printValueToCenterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_set_printValueToCenterActionPerformed
        // TODO add your handling code here:
        jLabel_print_ValPallet.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_print_ValNetWeight.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_print_ValLot.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    }//GEN-LAST:event_jButton_set_printValueToCenterActionPerformed

    private void jTabbedPane_settingsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane_settingsMouseClicked
        try {
            // TODO add your handling code here:
            if (jTabbedPane_settings.getSelectedIndex() == 3) {
                fill_machine();
            }
        } catch (DatabaseException ex) {
            Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jTabbedPane_settingsMouseClicked

    private void jButton_set_reloadSettingFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_set_reloadSettingFileActionPerformed
        try {
            // TODO add your handling code here:
            readConfig();
        } catch (BusinessException ex) {
            Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton_set_reloadSettingFileActionPerformed

    private void jButton_mach_addMachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_mach_addMachActionPerformed
        // TODO add your handling code here:
        try {
            if (!jTextField_mach_MName.getText().isBlank() && !jTextField_mach_lot.getText().isBlank()
                    && jComboBox_mach_pros.getSelectedIndex() != -1) {
                if (jTable_machines.getSelectedRow() > 0) {
                    machineController.editMachine(new Machine(
                            (int) jTable_machines.getModel().getValueAt(jTable_machines.getSelectedRow(), 0),
                            jTextField_mach_MName.getText(),
                            productController.getProduct(jComboBox_mach_pros.getSelectedItem().toString()).getId(),
                            jTextField_mach_lot.getText(), new Date()));
                } else {
                    machineController.addMachine(jTextField_mach_MName.getText(), jComboBox_mach_pros.getSelectedItem().toString(),
                            jTextField_mach_lot.getText());
                }
                fill_machine();
                jTextField_mach_MName.setText("");
                jTextField_mach_lot.setText("");
                jComboBox_mach_pros.setSelectedIndex(-1);
            }
        } catch (DatabaseException | BusinessException ex) {
            Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton_mach_addMachActionPerformed

    private void jTextField_mach_MNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_mach_MNameKeyTyped
        // TODO add your handling code here:
        evt.setKeyChar(util.ToNumArab(evt.getKeyChar()));
    }//GEN-LAST:event_jTextField_mach_MNameKeyTyped

    private void jTextField_mach_lotKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_mach_lotKeyTyped
        // TODO add your handling code here:
        textbox_length_limiter(evt, jTextField_mach_lot, 5);
        char input = evt.getKeyChar();
        if (Character.isDigit(input)) {
            evt.setKeyChar(util.ToNumArab(input));
        }
        if ((!Character.isDigit(input)) && input != 'أ' && input != 'س' && input != 'و' && input != 'د' && input != 'ط'
                && input != 'ب' && input != 'ا' && input != 'ع' && input != 'ه' && input != 'م' && input != 'ن'
                && input != 'ي' && input != 'ض') {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField_mach_lotKeyTyped

    private void jButton_mach_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_mach_DeleteActionPerformed
        // TODO add your handling code here:
        if (jTable_machines.getSelectedRow() > 0) {
            try {
                if (machineController.removeMachine((int) jTable_machines.getModel().getValueAt(jTable_machines.getSelectedRow(), 0))) {

                    JOptionPane.showMessageDialog(this, util.addStyle("تم الحذف  بنجاح "), "ناجح",
                            JOptionPane.INFORMATION_MESSAGE);
                    fill_machine();
                    jTextField_mach_MName.setText("");
                    jTextField_mach_lot.setText("");
                    jComboBox_mach_pros.setSelectedIndex(-1);
                } else {
                    JOptionPane.showMessageDialog(this, util.addStyle("لا يمكن حذف هذا الصنف "), "إنتبه",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (DatabaseException ex) {
                Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, util.addStyle(" برجاء أختيار من الجدول أولا"), "إنتبه",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton_mach_DeleteActionPerformed

    private void jTable_machinesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_machinesMouseClicked
        // TODO add your handling code here:
        try {

            TableModel model = jTable_machines.getModel();
            jTextField_mach_MName.setText((String) model.getValueAt(jTable_machines.getSelectedRow(), 1));
            jTextField_mach_lot.setText((String) model.getValueAt(jTable_machines.getSelectedRow(), 3));
            try {
                jComboBox_mach_pros.setSelectedItem(productController.getProduct(
                        (String) jTable_machines.getModel().getValueAt(jTable_machines.getSelectedRow(), 2)));
            } catch (BusinessException ex) {
                jComboBox_mach_pros.setSelectedIndex(-1);
            }
        } catch (DatabaseException ex) {
            JOptionPane.showMessageDialog(this, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jTable_machinesMouseClicked

    private void jButton_SettingsActionPerformed() throws IOException, BusinessException {
        open_panel(jTabbedPane_settings);
        saveConfig();
        readConfig();
    }

    private void open_panel(Component comp) {
        left_panel.removeAll();
        left_panel.add(comp);
        left_panel.revalidate();
        left_panel.repaint();
        this.setAlwaysOnTop(false);
    }

    private void textbox_number_weight(KeyEvent event, JTextField textboxname, int length) {

        if (event.getKeyChar() == KeyEvent.VK_DELETE) {
            if ((event.getModifiersEx() & KeyEvent.SHIFT_DOWN_MASK) != 0) {
                jButton_clear.doClick();
            }
            textboxname.setText("");
        }
        if (textboxname.getText().length() > length - 1 && event.getKeyChar() != KeyEvent.VK_ENTER
                && event.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
            event.consume();
        } else {
            if ((!Character.isDigit(event.getKeyChar())
                    && (event.getKeyChar() == 'ز' || event.getKeyChar() == '.' || event.getKeyChar() == '٫'))
                    && !(textboxname.getText().contains(".") || textboxname.getText().contains("٫"))) {
                event.setKeyChar(util.ToNumArab(event.getKeyChar()));
            } else {
                if (Character.isDigit(event.getKeyChar())) {
                    if ((textboxname.getText().contains(".")
                            && (textboxname.getText().indexOf(".") == textboxname.getText().length() - 4))
                            || (textboxname.getText().contains("٫")
                            && (textboxname.getText().indexOf("٫") == textboxname.getText().length() - 4))) {
                        event.consume();
                    } else {
                        event.setKeyChar(util.ToNumArab(event.getKeyChar()));
                    }
                } else {
                    event.consume();
                }
            }
        }
    }

    private void textbox_length_limiter(KeyEvent event, JTextField textboxname, int length) {
        if (textboxname.getText().length() > length - 1 && event.getKeyChar() != KeyEvent.VK_ENTER
                && event.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
            event.consume();
        }
        if (event.getKeyChar() == KeyEvent.VK_DELETE) {
            if ((event.getModifiersEx() & KeyEvent.SHIFT_DOWN_MASK) != 0) {
                jButton_clear.doClick();
            }
            textboxname.setText("");
        }
    }

    private void textbox_number(KeyEvent event, JTextField textboxname, int length, boolean lastEven) {

        if (event.getKeyChar() == KeyEvent.VK_DELETE) {
            if ((event.getModifiersEx() & KeyEvent.SHIFT_DOWN_MASK) != 0) {
                jButton_clear.doClick();
            }
            textboxname.setText("");
        }
        if (textboxname.getText().length() > length - 1 && event.getKeyChar() != KeyEvent.VK_ENTER
                && event.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
            event.consume();
        } else {
            if (!Character.isDigit(event.getKeyChar())) {
                event.consume();
            } else {
                if (lastEven && textboxname.getText().length() == length - 1
                        && Integer.parseInt(event.getKeyChar() + "") % 2 != 0) {
                    event.consume();
                }
                event.setKeyChar(util.ToNumArab(event.getKeyChar()));
            }
        }
    }

    private void combox_fill(JComboBox<Product> Combo, boolean resetToNone) throws DatabaseException {

        Combo.removeAllItems();
        Combo.setModel(new DefaultComboBoxModel<>(productController.getAvailableProducts().toArray(Product[]::new)));
        if (resetToNone) {
            Combo.setSelectedIndex(-1);
        }
    }

    private void populateCombos() throws DatabaseException {
        this.combox_fill(jComboBox_pro_in_storage, true);
        this.combox_fill(jComboBox_rep_Pros, true);
        this.combox_fill(jComboBox_E_O_proName, true);
        this.combox_fill(jComboBox_E_proName, true);
        this.combox_fill(jComboBox_ME_type, true);
        this.combox_fill(jComboBox_stock_Pros, true);
        this.combox_fill(jComboBox_mach_pros, true);
    }

    private void fill_pro_table() throws DatabaseException {
        DefaultTableModel model = (DefaultTableModel) jTable_pro.getModel();
        model.setRowCount(0);
        List<Product> pros = productController.getAvailableProducts();
        for (Product pro : pros) {
            model.addRow(new Object[]{pro.getId(), pro.getName(), util.ToStringArabic(pro.getWeight_of_con()),
                pro.getColor(), pro.isIsBox()});
        }

    }

    private void fill_machine() throws DatabaseException {
        DefaultTableModel model = (DefaultTableModel) jTable_machines.getModel();
        model.setRowCount(0);
        List<Machine> machs = machineController.getMachines();
        for (Machine mach : machs) {
            Product p;
            try {
                p = productController.getProduct(mach.getProId());
                model.addRow(new Object[]{mach.getMachId(), mach.getMachName(), p.getName(), mach.getLot(),
                    mach.getUpdatedAt()});
            } catch (BusinessException ex) {
                model.addRow(new Object[]{mach.getMachId(), mach.getMachName(), "ممسوح", mach.getLot(),
                    mach.getUpdatedAt()});
            }
        }

    }

    private void calc_net_weight() {
        if (!jTextField_num_of_con.getText().isBlank() && !jTextField_weight_of_con.getText().isBlank()
                && !jTextField_bag_weight.getText().isBlank()
                && ((jTextField_weight.getText().contains("٫") && jTextField_weight.getText().length() >= 2)
                || (!jTextField_weight.getText().contains("٫") && !jTextField_weight.getText().isBlank()))) {
            double num_of_con = util.ToDoubleEnglish(jTextField_num_of_con.getText()),
                    weight_of_con = util.ToDoubleEnglish(jTextField_weight_of_con.getText()) / 1000,
                    bag_weight = util.ToDoubleEnglish(jTextField_bag_weight.getText()) / 100,
                    weight = util.ToDoubleEnglish(jTextField_weight.getText());

            jTextField_net_weight.setText(util.ToDoubleArabic(weight - (bag_weight + (num_of_con * weight_of_con))));
        }
    }

    private void fill_Table_rep_select() throws DatabaseException, BusinessException {
        try {
            if (jComboBox_rep_Pros.getSelectedIndex() != -1) {
                ((DefaultTableModel) jTable_rep_select.getModel()).setRowCount(0);
                List<String[]> pallets = storageController.getPalletsForReport(jComboBox_rep_Pros.getSelectedItem().toString());

                for (String[] pallet : pallets) {
                    ((DefaultTableModel) jTable_rep_select.getModel())
                            .addRow(new Object[]{util.ToStringArabic(pallet[0]), util.ToStringArabic(pallet[1]),
                        util.ToStringArabic(pallet[2]), util.ToStringArabic(pallet[3]),
                        Boolean.valueOf(pallet[4])});
                }
                ((DefaultTableModel) jTable_rep_preview.getModel()).setRowCount(0);

                orderIds.clear();
                jTextField_rep_totweight.setText("");
                jComboBox_rep_palletsNrep.removeAllItems();
            }
        } catch (DatabaseException ex) {
            Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void fill_storage_table() throws DatabaseException, BusinessException {
        DefaultTableModel model = (DefaultTableModel) jTable_storage.getModel();
        model.setRowCount(0);
        List<Bag> bags = storageController.getBags(jComboBox_pro_in_storage.getSelectedItem().toString());

        for (Bag bag : bags) {
            model.addRow(new Object[]{util.ToDoubleArabic(bag.getWeight()), util.ToStringArabic(bag.getNum_of_con() + ""),
                util.ToStringArabic(bag.getLot()), util.ToStringArabic(bag.getPallet_numb() + ""), bag.getId(), "",
                bag.isUsed()});
        }
        Product pro = productController.getProduct(jComboBox_pro_in_storage.getSelectedItem().toString());
        if (pro != null) {
            if (!jCheckBox_ConeWeightChange.isSelected()) {
                jTextField_weight_of_con.setText(util.ToStringArabic(pro.getWeight_of_con()));
            }
            jTextField_Color.setText(pro.getColor());
            jCheckBox_Box.setSelected(pro.isIsBox());
            BagMax = pro.isIsBox() ? 3 : 2;
            jLabel11.setText(!pro.isIsBox() ? "فارغ الشيكاره" : "فارغ الصندوق");
            jTextField_bag_weight.setBackground(pro.isIsBox() ? Color.pink : Color.WHITE);
        }

        if (model.getRowCount() != 0) {
            String lott = util.ToStringEnglish(model.getValueAt(model.getRowCount() - 1, 2).toString()),
                    pallet_num = util.ToStringEnglish(model.getValueAt(model.getRowCount() - 1, 3).toString());
            int cunt = 0;
            for (int i = model.getRowCount() - 1; i >= 0; i--) {
                if (!lott.equals(util.ToStringEnglish(model.getValueAt(i, 2).toString()))
                        || !pallet_num.equals(util.ToStringEnglish(model.getValueAt(i, 3).toString()))) {
                    lott = util.ToStringEnglish(model.getValueAt(i, 2).toString());
                    pallet_num = util.ToStringEnglish(model.getValueAt(i, 3).toString());
                    cunt = 0;
                }

                model.setValueAt(util.ToStringArabic(++cunt + ""), i, 5);
            }
        }
    }

    private void calc_pallet_weight() throws DatabaseException {
        if (!jTextField_pallet_num.getText().isEmpty() && !jTextField_lot.getText().isEmpty()
                && jComboBox_pro_in_storage.getSelectedIndex() != -1) {
            pallet_weight.setText(util.ToStringArabic(storageController.calc_pallet_weight(jTextField_pallet_num.getText(),
                    jTextField_lot.getText(), jComboBox_pro_in_storage.getSelectedItem().toString())));
        }
    }

    private void fill_Statistics_table() {
        if (jDateChooser_statis_fromDate.getCalendar() != null && jDateChooser_statis_toDate.getCalendar() != null) {
            DefaultTableModel model = (DefaultTableModel) jTable_statis.getModel();
            model.setRowCount(0);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date1 = sdf.format(jDateChooser_statis_fromDate.getCalendar().getTime());
            String date2 = sdf.format(jDateChooser_statis_toDate.getCalendar().getTime());
            try {
                List<String[]> statistics = exportController.getstatistics(date1, date2);
                for (String[] row : statistics) {
                    model.addRow(new Object[]{row[0], util.ToStringArabic(row[1]),
                        util.ToStringArabic(row[2]), util.ToStringArabic(row[3])});

                }
                double tot = 0.0;
                for (int i = 0; i < model.getRowCount(); i++) {
                    tot += util.ToDoubleEnglish(model.getValueAt(i, 3).toString());
                }
                jTextField_statis_tot.setText(util.ToDoubleArabic(tot));
            } catch (DatabaseException ex) {
                Logger.getLogger(mainform.class
                        .getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, util.addStyle(ex.getLocalizedMessage()), "exception", JOptionPane.INFORMATION_MESSAGE);
            }

        }
    }

    private void readConfig() throws BusinessException {
        Properties properties = util.CheckConfigFileAndFolder();

        //"config.properties"
        // Load the properties file
        // Access configuration values
        ticketPrinterName = properties.getProperty("ticketPrinterName", "Honeywell");
        qrPrinterName = properties.getProperty("qrPrinterName", "Xprinter");
        tick10x10 = Short.parseShort(properties.getProperty("ticket10x10", "0"));
        tick2x2 = Short.parseShort(properties.getProperty("ticket2x2", "0"));
        repDiff = Integer.parseInt(properties.getProperty("repdiff", "15"));

        jLabel_ip.setText("Connected To: " + properties.getProperty("ip", "localhost"));
        boolean print10x10 = Boolean.parseBoolean(properties.getProperty("print10x10", "true"));
        boolean print2x2 = Boolean.parseBoolean(properties.getProperty("print2x2", "false"));
        boolean PrintExcel = Boolean.parseBoolean(properties.getProperty("PrintExcel", "false"));

        //set in gui after read
        jLabel_Ticket10x10Counter.setText("" + tick10x10);
        jLabel_Ticket2x2Counter.setText("" + tick2x2);
        jTextField_setting_repsDiff.setText(util.ToStringArabic(repDiff + ""));

        jCheckBox_print.setSelected(print10x10);
        jCheckBox_QR.setSelected(print2x2);
        jCheckBox_set_printExcel.setSelected(PrintExcel);
        saveConfig();

    }

    private void saveConfig() throws BusinessException {
        Properties properties = new Properties();

        // Set properties (key-value pairs)
        properties.setProperty("ip", properties.getProperty("ip", "localhost"));
        properties.setProperty("ticketPrinterName", ticketPrinterName);
        properties.setProperty("qrPrinterName", qrPrinterName);
        properties.setProperty("ticket10x10", "" + tick10x10);
        properties.setProperty("ticket2x2", "" + tick2x2);
        properties.setProperty("repdiff", "" + repDiff);
        properties.setProperty("print10x10", jCheckBox_print.isSelected() ? "True" : "False");
        properties.setProperty("print2x2", jCheckBox_QR.isSelected() ? "True" : "False");
        properties.setProperty("PrintExcel", jCheckBox_set_printExcel.isSelected() ? "True" : "False");
        util.CheckConfigFileAndFolder(); // Save the properties to a file
        try (FileOutputStream output = new FileOutputStream(new File(System.getProperty("user.dir") + "\\Temp\\config.properties"))) {
            // Save the properties with a comment header
            properties.store(output, "Application Configuration");
        } catch (IOException ex) {
            throw new BusinessException("file not found");
        }
    }

    private void sendToWight(JTextField fromTextField, JTextField toTextField, KeyEvent evt) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastInputTime < 50) {
            char keyChar = evt.getKeyChar();
            if (mizanInputBuilder.length() == 0) {
                savedText = fromTextField.getText();
                mizanInputBuilder.append(firstChar);
            }
            mizanInputBuilder.append(keyChar);
            if (keyChar == '\n') {
                enterFromMizan = true;
                String barcodeInput = mizanInputBuilder.toString().trim();
                if (isMizanPatternValid(barcodeInput)) {
                    toTextField.setText(util.ToStringArabic(barcodeInput));
                    fromTextField.setText(
                            savedText.lastIndexOf(util.ToNumArab(firstChar)) > -1
                            ? savedText.substring(0, savedText.length() - 1) : savedText
                    );
                    savedText = "";
                }
                // Clear the input builder for the next scan
                mizanInputBuilder.setLength(0);
            }
            evt.consume();

        } else {
            // Reset if input is too slow (probably user input)
            enterFromMizan = false;
            firstChar = evt.getKeyChar();
            mizanInputBuilder.setLength(0);
        }
        lastInputTime = currentTime;
    }

    private void ignorWightFromMizan(JTextField fromTextField, KeyEvent evt) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastInputTime < 50) {
            char keyChar = evt.getKeyChar();
            if (mizanInputBuilder.length() == 0) {
                savedText = fromTextField.getText();
                mizanInputBuilder.append(firstChar);
            }
            mizanInputBuilder.append(keyChar);
            if (keyChar == '\n') {
                enterFromMizan = true;
                String barcodeInput = mizanInputBuilder.toString().trim();
                if (isMizanPatternValid(barcodeInput)) {
                    fromTextField.setText(
                            savedText.lastIndexOf(util.ToNumArab(firstChar)) > -1 || savedText.lastIndexOf(firstChar) > -1
                            ? savedText.substring(0, savedText.length() - 1) : savedText
                    );
                    savedText = "";
                }
                // Clear the input builder for the next scan
                mizanInputBuilder.setLength(0);
            }
            evt.consume();

        } else {
            // Reset if input is too slow (probably user input)
            enterFromMizan = false;
            firstChar = evt.getKeyChar();
            mizanInputBuilder.setLength(0);
        }
        lastInputTime = currentTime;
    }

    private boolean isMizanPatternValid(String input) {
        // Pattern for two or three digits, followed by a comma, followed by exactly three digits
        String barcodePattern = "[0-9\u0660-\u0669]{1,3}[.,\u060C,\u0632][0-9\u0660-\u0669]{2}";
        Pattern pattern = Pattern.compile(barcodePattern);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    private void accessDataBase(String clientName, String totalWeight, List<String> idsFromStorageToExport) throws DatabaseException {
        try {
            clientController.addClientByName(clientName);
            orderController.addOrder(totalWeight);

            for (String id : idsFromStorageToExport) {
                exportController.moveBagFromStorageToExport(id, clientName, totalWeight);

                storageController.removeBag(id);

            }
        } catch (DatabaseException ex) {
            Logger.getLogger(utils.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new DatabaseException("حدث خطأ أثناء عمل الأكسل", ex);
        }
    }

    private void incTicketCounters(boolean t10x10, boolean t2x2) throws BusinessException {
        if (t10x10) {
            tick10x10++;
        }
        if (t2x2) {
            tick2x2++;
        }
        saveConfig();
        readConfig();
    }
//    private void startRecognition() throws IOException, LineUnavailableException {
//
//        // Load Arabic speech model (make sure you have the correct path to the downloaded model)
//        // Model model = new Model("models/vosk-model-small-ar");
//        Model model = new Model("models/vosk-model-ar");
//        // Model model = new Model("models/vosk-model-small-en-us-0.15");
//
//        // Set up microphone
//        AudioFormat format = new AudioFormat(16000, 16, 1, true, false);
//        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
//        TargetDataLine microphone = (TargetDataLine) AudioSystem.getLine(info);
//        microphone.open(format);
//        microphone.start();
//
//        // Create a recognizer with the model and microphone audio stream
//        Recognizer recognizer = new Recognizer(model, 16000);
//
//        byte[] buffer = new byte[4096];
//
//        // Continuously listen to the microphone and recognize speech
//        new Thread(() -> {
//            while (true) {
//
//                int bytesRead = microphone.read(buffer, 0, buffer.length);
//                if (recognizer.acceptWaveForm(buffer, bytesRead)) {
//                    jTextArea_emp.append(recognizer.getResult() + "\n");
//                }
//            }
//        }).start();
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame MultiEdit;
    private javax.swing.JFrame SingleEdit;
    private javax.swing.JButton jButton_DoBack;
    private javax.swing.JButton jButton_E_Edit;
    private javax.swing.JButton jButton_E_print;
    private javax.swing.JButton jButton_Emp_opener;
    private javax.swing.JButton jButton_Ezn_opener;
    private javax.swing.JButton jButton_ME_Edit;
    private javax.swing.JButton jButton_Mizan_opener;
    private javax.swing.JButton jButton_Outs_opener;
    private javax.swing.JButton jButton_Reset_TicketCount10x10;
    private javax.swing.JButton jButton_Reset_TicketCount2x2;
    private javax.swing.JButton jButton_Statics_opener;
    private javax.swing.JButton jButton_Stock_opener;
    private javax.swing.JButton jButton_addPro_opener;
    private javax.swing.JButton jButton_add_data;
    private javax.swing.JButton jButton_add_pro;
    private javax.swing.JButton jButton_clear;
    private javax.swing.JButton jButton_del_data;
    private javax.swing.JButton jButton_del_pro;
    private javax.swing.JButton jButton_mach_Delete;
    private javax.swing.JButton jButton_mach_addMach;
    private javax.swing.JButton jButton_rep_printRep;
    private javax.swing.JButton jButton_reprintLastTicket;
    private javax.swing.JButton jButton_reps_clear;
    private javax.swing.JButton jButton_set_changePos;
    private javax.swing.JButton jButton_set_printValueToCenter;
    private javax.swing.JButton jButton_set_reloadSettingFile;
    private javax.swing.JButton jButton_statis_createExcl;
    private javax.swing.JButton jButton_statistics_search;
    private javax.swing.JButton jButton_stock_createExcl;
    private javax.swing.JButton jButton_youm_createExcel;
    private javax.swing.JButton jButton_youm_getClients;
    private javax.swing.JButton jButton_youm_refund;
    private javax.swing.JButton jButton_youm_search;
    private javax.swing.JCheckBox jCheckBox_Box;
    private javax.swing.JCheckBox jCheckBox_ConeWeightChange;
    private javax.swing.JCheckBox jCheckBox_E_Mark;
    private javax.swing.JCheckBox jCheckBox_E_O_Mark;
    private javax.swing.JCheckBox jCheckBox_E_P;
    private javax.swing.JCheckBox jCheckBox_E_QR;
    private javax.swing.JCheckBox jCheckBox_ME_MarkBag;
    private javax.swing.JCheckBox jCheckBox_M_Markpage;
    private javax.swing.JCheckBox jCheckBox_Pros_IsBox;
    private javax.swing.JCheckBox jCheckBox_QR;
    private javax.swing.JCheckBox jCheckBox_freeze_cone;
    private javax.swing.JCheckBox jCheckBox_freeze_wight;
    private javax.swing.JCheckBox jCheckBox_ignore_limits;
    private javax.swing.JCheckBox jCheckBox_print;
    private javax.swing.JCheckBox jCheckBox_rep_2n1;
    private javax.swing.JCheckBox jCheckBox_rep_wzn;
    private javax.swing.JCheckBox jCheckBox_set_printExcel;
    private javax.swing.JCheckBox jCheckBox_youm_old;
    private javax.swing.JComboBox<Product> jComboBox_E_O_proName;
    private javax.swing.JComboBox<Product> jComboBox_E_proName;
    private javax.swing.JComboBox<Product> jComboBox_ME_type;
    private javax.swing.JComboBox<Product> jComboBox_mach_pros;
    private javax.swing.JComboBox<Product> jComboBox_pro_in_storage;
    private javax.swing.JComboBox<Product> jComboBox_rep_Pros;
    private javax.swing.JComboBox<String> jComboBox_rep_palletsNrep;
    private javax.swing.JComboBox<Product> jComboBox_stock_Pros;
    private com.toedter.calendar.JDateChooser jDateChooser_statis_fromDate;
    private com.toedter.calendar.JDateChooser jDateChooser_statis_toDate;
    private com.toedter.calendar.JDateChooser jDateChooser_yum_ToDate;
    private com.toedter.calendar.JDateChooser jDateChooser_yum_fromDate;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_Order_num;
    private javax.swing.JLabel jLabel_Ticket10x10Counter;
    private javax.swing.JLabel jLabel_Ticket2x2Counter;
    private javax.swing.JLabel jLabel_ip;
    private javax.swing.JLabel jLabel_print_NCone;
    private javax.swing.JLabel jLabel_print_NetWeight;
    private javax.swing.JLabel jLabel_print_TotalWeight;
    private javax.swing.JLabel jLabel_print_ValColor;
    private javax.swing.JLabel jLabel_print_ValLot;
    private javax.swing.JLabel jLabel_print_ValNCone;
    private javax.swing.JLabel jLabel_print_ValNetWeight;
    private javax.swing.JLabel jLabel_print_ValPallet;
    private javax.swing.JLabel jLabel_print_ValTotalWeight;
    private javax.swing.JLabel jLabel_print_ValType;
    private javax.swing.JLabel jLabel_print_color;
    private javax.swing.JLabel jLabel_print_footer;
    private javax.swing.JLabel jLabel_print_header;
    private javax.swing.JLabel jLabel_print_lot;
    private javax.swing.JLabel jLabel_print_number;
    private javax.swing.JLabel jLabel_print_pallet;
    private javax.swing.JLabel jLabel_print_type;
    private javax.swing.JLabel jLabel_version;
    private javax.swing.JPanel jPanel_Machines;
    private javax.swing.JPanel jPanel_print;
    private javax.swing.JProgressBar jProgressBar_pallet;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JPanel jTab_set_Counter;
    private javax.swing.JPanel jTab_set_Printing;
    private javax.swing.JPanel jTab_set_about;
    private javax.swing.JPanel jTab_set_order;
    private javax.swing.JTabbedPane jTabbedPane_settings;
    private javax.swing.JTable jTable_machines;
    private javax.swing.JTable jTable_pro;
    private javax.swing.JTable jTable_rep_preview;
    private javax.swing.JTable jTable_rep_select;
    private javax.swing.JTable jTable_statis;
    private javax.swing.JTable jTable_stock;
    private javax.swing.JTable jTable_storage;
    private javax.swing.JTable jTable_youm_clinets;
    private javax.swing.JTable jTable_yumia;
    private javax.swing.JTextArea jTextArea_emp;
    private javax.swing.JTextField jTextField_Color;
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
    private javax.swing.JTextField jTextField_Pros_color;
    private javax.swing.JTextField jTextField_Pros_conWight;
    private javax.swing.JTextField jTextField_Search_pros;
    private javax.swing.JTextField jTextField_bag_weight;
    private javax.swing.JTextField jTextField_lot;
    private javax.swing.JTextField jTextField_mach_MName;
    private javax.swing.JTextField jTextField_mach_lot;
    private javax.swing.JTextField jTextField_net_weight;
    private javax.swing.JTextField jTextField_num_of_con;
    private javax.swing.JTextField jTextField_pallet_num;
    private javax.swing.JTextField jTextField_pro_name;
    private javax.swing.JTextField jTextField_rep_clientName;
    private javax.swing.JTextField jTextField_rep_numOfBag;
    private javax.swing.JTextField jTextField_rep_totweight;
    private javax.swing.JTextField jTextField_setting_repsDiff;
    private javax.swing.JTextField jTextField_statis_tot;
    private javax.swing.JTextField jTextField_weight;
    private javax.swing.JTextField jTextField_weight_of_con;
    private javax.swing.JTextField jTextField_youm_ClientFilter;
    private javax.swing.JPanel left_panel;
    private javax.swing.JPanel makePermit;
    private javax.swing.JTextField pallet_weight;
    private javax.swing.JPanel pause_panel;
    private javax.swing.JPanel products_panel;
    private javax.swing.JPanel right_panel_menu;
    private javax.swing.JPanel showPermit_panel;
    private javax.swing.JPanel statistics_panel;
    private javax.swing.JPanel stock_panel;
    private javax.swing.JPanel storage_panel;
    // End of variables declaration//GEN-END:variables
}

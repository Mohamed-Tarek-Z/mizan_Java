package new_mizan;

import com.google.zxing.WriterException;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import javax.swing.JPanel;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DecimalStyle;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import javax.imageio.ImageIO;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.PrinterResolution;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellCopyPolicy;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class mainform extends javax.swing.JFrame {

    sqlcon opj;

    public short tick1num = 0, tick2num = 0;
    private int BagMax = 2, repDiff = 20;
    private float Xx = 0, Yy = 0, width = 19, hight = 19;
    private final JButton jButton_bagmax = new javax.swing.JButton();
    String Version = "V 58.1.0.W";

    public mainform(sqlcon ops) throws IOException {
        initComponents();
        this.setDefaultCloseOperation(mainform.DO_NOTHING_ON_CLOSE);
        if (!login_form.admin) {
            jButton_addPro_opener.setEnabled(false);
            jButton_Ezn_opener.setEnabled(false);
            jButton_Outs_opener.setEnabled(false);
        } else {
            jButton_bagmax.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jButton_bagmax.setText("Setting");
            jButton_bagmax.addActionListener((java.awt.event.ActionEvent evt) -> {
                try {
                    jButton_bagmaxActionPerformed();
                } catch (IOException ex) {
                    Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "exception", JOptionPane.PLAIN_MESSAGE);
                }
            });
            right_panel_menu.add(jButton_bagmax, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 520, 110, 40));
        }

        this.opj = ops;

        this.jTable_storage.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.jTable_storage.getTableHeader().setFont(new Font("Tahoma", 1, 16));
        this.jTable_stock.getTableHeader().setFont(new Font("Tahoma", 1, 16));
        this.jTable_rep_preview.getTableHeader().setFont(new Font("Tahoma", 1, 16));
        this.jTable_rep_select.getTableHeader().setFont(new Font("Tahoma", 1, 16));
        this.jTable_statis.getTableHeader().setFont(new Font("Tahoma", 1, 16));
        combox_fill(this.jComboBox_pro_in_storage, opj.dataRead("pro_name", "products"), true);
        combox_fill(this.jComboBox_rep_Pros, opj.dataRead("pro_name", "products"), true);
        combox_fill(this.jComboBox_E_O_proName, opj.dataRead("pro_name", "products"), true);
        combox_fill(this.jComboBox_E_proName, opj.dataRead("pro_name", "products"), true);
        combox_fill(this.jComboBox_ME_type, opj.dataRead("pro_name", "products"), true);
        tick1num = loadTicknum("TicketNumber1.txt");
        tick2num = loadTicknum("TicketNumber2.txt");
        this.setAlwaysOnTop(true);
    }

    int pro_Table_SelectedID = 0;

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
        jTextField_E_Weight = new javax.swing.JTextField();
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
        in_data = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_storage = new javax.swing.JTable()

        {
            @Override

            public Component prepareRenderer (TableCellRenderer renderer, int rowIndex, int columnIndex){
                Component componenet = super.prepareRenderer(renderer, rowIndex, columnIndex);

                Object value = getModel().getValueAt(rowIndex,6);

                if(columnIndex == 2){
                    if(value.equals("1"))
                    {
                        componenet.setBackground(Color.GREEN);
                        componenet.setForeground(Color.BLACK);

                    }

                }
                else {

                    componenet.setBackground(Color.WHITE);
                    componenet.setForeground(Color.BLACK);
                }
                if(columnIndex == 1){
                    if(value.equals("1"))
                    {
                        componenet.setBackground(Color.GREEN);
                        componenet.setForeground(Color.BLACK);

                    }

                }

                if(columnIndex == 0){
                    if(value.equals("1"))
                    {
                        componenet.setBackground(Color.GREEN);
                        componenet.setForeground(Color.BLACK);

                    }

                }

                if(columnIndex == 3){
                    if(value.equals("1"))
                    {
                        componenet.setBackground(Color.GREEN);
                        componenet.setForeground(Color.BLACK);

                    }

                }
                if(columnIndex == 5){
                    if(value.equals("1"))
                    {
                        componenet.setBackground(Color.GREEN);
                        componenet.setForeground(Color.BLACK);

                    }

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
        jButton5 = new javax.swing.JButton();
        jProgressBar_pallet = new javax.swing.JProgressBar();
        reports = new javax.swing.JPanel();
        jComboBox_rep_Pros = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField_rep_numOfBag = new javax.swing.JTextField();
        jButton_rep_printRep = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable_rep_preview = new javax.swing.JTable();
        jTextField_rep_clientName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable_rep_select = new javax.swing.JTable()
        {
            @Override

            public Component prepareRenderer (TableCellRenderer renderer, int rowIndex, int columnIndex){
                Component componenet = super.prepareRenderer(renderer, rowIndex, columnIndex);

                Object value = getModel().getValueAt(rowIndex,4);
                if(columnIndex == 2){
                    if(value.equals("١"))
                    {
                        componenet.setBackground(Color.GREEN);
                        componenet.setForeground(Color.BLACK);

                    }

                }
                else {

                    componenet.setBackground(Color.WHITE);
                    componenet.setForeground(Color.BLACK);
                }
                if(columnIndex == 1){
                    if(value.equals("١"))
                    {
                        componenet.setBackground(Color.GREEN);
                        componenet.setForeground(Color.BLACK);

                    }

                }

                if(columnIndex == 0){
                    if(value.equals("١"))
                    {
                        componenet.setBackground(Color.GREEN);
                        componenet.setForeground(Color.BLACK);

                    }

                }

                if(columnIndex == 3){
                    if(value.equals("١"))
                    {
                        componenet.setBackground(Color.GREEN);
                        componenet.setForeground(Color.BLACK);

                    }

                }

                return componenet;
            }

        }

        ;
        jTextField_rep_totweight = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jCheckBox_rep_2n1 = new javax.swing.JCheckBox();
        jComboBox_rep_palletsNrep = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        jButton_reps_clear = new javax.swing.JButton();
        jCheckBox_rep_wzn = new javax.swing.JCheckBox();
        add_product = new javax.swing.JPanel();
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
        stock = new javax.swing.JPanel();
        jComboBox_stock_Pros = new javax.swing.JComboBox<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable_stock = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        jButton_stock_createExcl = new javax.swing.JButton();
        statistics = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable_statis = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jDateChooser_statis_fromDate = new com.toedter.calendar.JDateChooser();
        jDateChooser_statis_toDate = new com.toedter.calendar.JDateChooser();
        jLabel20 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jTextField_statis_tot = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton_statis_createExcl = new javax.swing.JButton();
        yomia = new javax.swing.JPanel();
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
        pause = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTextArea_emp = new javax.swing.JTextArea();
        jLabel38 = new javax.swing.JLabel();
        Settings = new javax.swing.JPanel();
        jButton_set_Rest2 = new javax.swing.JButton();
        jButton_set_Rest1 = new javax.swing.JButton();
        jLabel_set_tick1 = new javax.swing.JLabel();
        jLabel_set_tick2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel_set_x = new javax.swing.JLabel();
        jLabel_set_y = new javax.swing.JLabel();
        jLabel_set_width = new javax.swing.JLabel();
        jLabel_set_hight = new javax.swing.JLabel();
        jTextField_set_hight = new javax.swing.JTextField();
        jTextField_set_x = new javax.swing.JTextField();
        jTextField_set_y = new javax.swing.JTextField();
        jTextField_set_width = new javax.swing.JTextField();
        jButton_set_changearea = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        jCheckBox_ignore_limits = new javax.swing.JCheckBox();
        jLabel45 = new javax.swing.JLabel();
        jTextField_setting_repsDiff = new javax.swing.JTextField();
        jCheckBox_rev_order = new javax.swing.JCheckBox();

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

            jTextField_E_Weight.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jTextField_E_Weight.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            jTextField_E_Weight.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTextField_E_WeightKeyTyped(evt);
                }
            });
            SingleEdit.getContentPane().add(jTextField_E_Weight, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 220, 90, -1));

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

            left_panel.setBorder(new javax.swing.border.MatteBorder(null));
            left_panel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            left_panel.setMaximumSize(new java.awt.Dimension(895, 650));
            left_panel.setMinimumSize(new java.awt.Dimension(840, 645));
            left_panel.setPreferredSize(new java.awt.Dimension(840, 645));
            left_panel.setLayout(new java.awt.CardLayout());

            in_data.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            in_data.setMaximumSize(new java.awt.Dimension(835, 640));
            in_data.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            jTable_storage.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jTable_storage.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                    "الوزن", "عدد الكون", "رقم اللوط", "رقم البالتة", "م", "مسلسل ", "s"
                }
            ) {
                boolean[] canEdit = new boolean [] {
                    false, false, false, false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            });
            jTable_storage.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jTable_storage.setRowHeight(25);
            jTable_storage.setSelectionBackground(new java.awt.Color(0, 0, 0));
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

            in_data.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 590, 620));

            jButton_add_data.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
            jButton_add_data.setText("إضافة");
            jButton_add_data.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_add_dataActionPerformed(evt);
                }
            });
            in_data.add(jButton_add_data, new org.netbeans.lib.awtextra.AbsoluteConstraints(745, 590, 90, 50));

            jButton_del_data.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
            jButton_del_data.setText("حذف");
            jButton_del_data.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_del_dataActionPerformed(evt);
                }
            });
            in_data.add(jButton_del_data, new org.netbeans.lib.awtextra.AbsoluteConstraints(605, 590, 80, 50));

            jComboBox_pro_in_storage.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
            jComboBox_pro_in_storage.addItemListener(new java.awt.event.ItemListener() {
                public void itemStateChanged(java.awt.event.ItemEvent evt) {
                    jComboBox_pro_in_storageItemStateChanged(evt);
                }
            });
            in_data.add(jComboBox_pro_in_storage, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 40, 210, 30));

            jLabel6.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
            jLabel6.setText("الصنف");
            in_data.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 12, -1, 20));

            jTextField_lot.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
            jTextField_lot.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
            jTextField_lot.setMaximumSize(new java.awt.Dimension(7, 38));
            jTextField_lot.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTextField_lotKeyTyped(evt);
                }
            });
            in_data.add(jTextField_lot, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 120, 120, 36));

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
            in_data.add(jTextField_weight, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 340, 120, -1));

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
            in_data.add(jTextField_bag_weight, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 290, 120, -1));

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
            in_data.add(jTextField_num_of_con, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 240, 120, -1));

            jLabel7.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
            jLabel7.setText("الوزن القائم");
            jLabel7.setMaximumSize(new java.awt.Dimension(82, 24));
            jLabel7.setMinimumSize(new java.awt.Dimension(82, 24));
            jLabel7.setPreferredSize(new java.awt.Dimension(82, 24));
            in_data.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 340, -1, 30));

            jLabel8.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
            jLabel8.setText("اللوط");
            jLabel8.setMaximumSize(new java.awt.Dimension(82, 24));
            jLabel8.setMinimumSize(new java.awt.Dimension(82, 24));
            jLabel8.setPreferredSize(new java.awt.Dimension(82, 24));
            in_data.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 120, -1, 30));

            jLabel9.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
            jLabel9.setText("عدد الكون");
            jLabel9.setMaximumSize(new java.awt.Dimension(82, 24));
            jLabel9.setMinimumSize(new java.awt.Dimension(82, 24));
            jLabel9.setPreferredSize(new java.awt.Dimension(82, 24));
            in_data.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 240, -1, 30));

            jLabel10.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
            jLabel10.setText("وزن الكونه");
            jLabel10.setMaximumSize(new java.awt.Dimension(82, 24));
            jLabel10.setMinimumSize(new java.awt.Dimension(82, 24));
            jLabel10.setPreferredSize(new java.awt.Dimension(82, 24));
            in_data.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 440, -1, 30));

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
            in_data.add(jTextField_pallet_num, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 162, 120, -1));

            jTextField_weight_of_con.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
            jTextField_weight_of_con.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
            jTextField_weight_of_con.setMaximumSize(new java.awt.Dimension(7, 38));
            jTextField_weight_of_con.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTextField_weight_of_conKeyTyped(evt);
                }
            });
            in_data.add(jTextField_weight_of_con, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 440, 120, -1));

            jLabel11.setFont(new java.awt.Font("sansserif", 1, 16)); // NOI18N
            jLabel11.setText("فارغ الشيكاره");
            jLabel11.setMaximumSize(new java.awt.Dimension(82, 24));
            jLabel11.setMinimumSize(new java.awt.Dimension(82, 24));
            jLabel11.setPreferredSize(new java.awt.Dimension(82, 24));
            in_data.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(735, 290, -1, 30));

            jLabel12.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
            jLabel12.setText("الوزن الصافي");
            in_data.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 390, -1, 30));

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
            in_data.add(jTextField_net_weight, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 390, 120, -1));

            jLabel2.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
            jLabel2.setText("رقم البالتة");
            jLabel2.setMaximumSize(new java.awt.Dimension(82, 24));
            jLabel2.setMinimumSize(new java.awt.Dimension(82, 24));
            jLabel2.setPreferredSize(new java.awt.Dimension(82, 24));
            in_data.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 162, -1, 30));

            jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jLabel14.setText("وزن البالتة");
            in_data.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 490, -1, 30));

            pallet_weight.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            pallet_weight.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
            in_data.add(pallet_weight, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 490, 140, -1));

            jCheckBox_M_Markpage.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            jCheckBox_M_Markpage.setText("تعليم البالته");
            jCheckBox_M_Markpage.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    jCheckBox_M_MarkpageKeyPressed(evt);
                }
            });
            in_data.add(jCheckBox_M_Markpage, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 205, -1, 20));

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
            in_data.add(jButton_clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(695, 610, 40, -1));

            jCheckBox_print.setSelected(true);
            jCheckBox_print.setText("طباعة");
            jCheckBox_print.setFocusable(false);
            in_data.add(jCheckBox_print, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 80, -1, -1));

            jTextField_Color.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jTextField_Color.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            jTextField_Color.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTextField_ColorKeyTyped(evt);
                }
            });
            in_data.add(jTextField_Color, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 540, 140, 40));

            jLabel41.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
            jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel41.setText("اللون");
            in_data.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 540, 70, 40));

            jCheckBox_QR.setSelected(true);
            jCheckBox_QR.setText("QR");
            in_data.add(jCheckBox_QR, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 80, -1, -1));

            jCheckBox_Box.setText("صندوق");
            jCheckBox_Box.addItemListener(new java.awt.event.ItemListener() {
                public void itemStateChanged(java.awt.event.ItemEvent evt) {
                    jCheckBox_BoxItemStateChanged(evt);
                }
            });
            in_data.add(jCheckBox_Box, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 80, -1, -1));
            in_data.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 232, 210, -1));
            in_data.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 110, 210, -1));
            in_data.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 482, 210, -1));

            jButton5.setBackground(new java.awt.Color(153, 153, 255));
            jButton5.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
            jButton5.setText("Print");
            jButton5.setToolTipText("Re-Print ticket");
            jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            jButton5.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton5ActionPerformed(evt);
                }
            });
            in_data.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, 60, 20));

            jProgressBar_pallet.setMaximum(20);
            jProgressBar_pallet.setToolTipText("Pallet");
            jProgressBar_pallet.setFocusable(false);
            jProgressBar_pallet.setMaximumSize(new java.awt.Dimension(10, 14));
            jProgressBar_pallet.setRequestFocusEnabled(false);
            in_data.add(jProgressBar_pallet, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 2, 590, -1));

            left_panel.add(in_data, "Mizan");

            reports.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            reports.setMaximumSize(new java.awt.Dimension(835, 640));
            reports.setMinimumSize(new java.awt.Dimension(835, 640));
            reports.setPreferredSize(new java.awt.Dimension(835, 640));
            reports.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            jComboBox_rep_Pros.setFont(new java.awt.Font("sansserif", 0, 20)); // NOI18N
            jComboBox_rep_Pros.addItemListener(new java.awt.event.ItemListener() {
                public void itemStateChanged(java.awt.event.ItemEvent evt) {
                    jComboBox_rep_ProsItemStateChanged(evt);
                }
            });
            reports.add(jComboBox_rep_Pros, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 300, -1));

            jLabel1.setFont(new java.awt.Font("sansserif", 0, 20)); // NOI18N
            jLabel1.setText("الصنف");
            reports.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, -1, -1));

            jLabel4.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
            jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel4.setText("_________________________________________________________");
            reports.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 750, 30));

            jTextField_rep_numOfBag.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jTextField_rep_numOfBag.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
            jTextField_rep_numOfBag.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusLost(java.awt.event.FocusEvent evt) {
                    jTextField_rep_numOfBagFocusLost(evt);
                }
            });
            jTextField_rep_numOfBag.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    jTextField_rep_numOfBagKeyReleased(evt);
                }
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTextField_rep_numOfBagKeyTyped(evt);
                }
            });
            reports.add(jTextField_rep_numOfBag, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 80, -1));

            jButton_rep_printRep.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
            jButton_rep_printRep.setText("طباعة");
            jButton_rep_printRep.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_rep_printRepActionPerformed(evt);
                }
            });
            reports.add(jButton_rep_printRep, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 500, 120, 50));

            jTable_rep_preview.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jTable_rep_preview.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                    "مسلسل", "وزن", "لوط", "رقم البالتة"
                }
            ) {
                boolean[] canEdit = new boolean [] {
                    false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            });
            jTable_rep_preview.setGridColor(new java.awt.Color(0, 0, 0));
            jTable_rep_preview.setRowHeight(25);
            jTable_rep_preview.setShowGrid(true);
            jTable_rep_preview.getTableHeader().setReorderingAllowed(false);
            jScrollPane3.setViewportView(jTable_rep_preview);

            reports.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 278, 610, 360));

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
            reports.add(jTextField_rep_clientName, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, 450, 40));

            jLabel5.setFont(new java.awt.Font("sansserif", 0, 20)); // NOI18N
            jLabel5.setText("أسم العميل");
            reports.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 200, -1, -1));

            jTable_rep_select.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
            jTable_rep_select.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                    "شيكاره", "وزن", "لوط", "رقم البالتة", "s"
                }
            ) {
                boolean[] canEdit = new boolean [] {
                    false, false, false, false, false
                };

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

            reports.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, 500, 170));

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
            reports.add(jTextField_rep_totweight, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 430, 140, 50));

            jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jLabel3.setText("إجمالي الوزن");
            reports.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 380, -1, -1));

            jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jLabel13.setText("عدد الشكاير");
            reports.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, -1, -1));

            jCheckBox_rep_2n1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jCheckBox_rep_2n1.setText(" صنفين في اذن واحد");
            reports.add(jCheckBox_rep_2n1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, -1, -1));

            jComboBox_rep_palletsNrep.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            reports.add(jComboBox_rep_palletsNrep, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 210, 80, -1));

            jLabel16.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
            jLabel16.setText("رقم البالته");
            reports.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 210, -1, -1));

            jButton_reps_clear.setBackground(new java.awt.Color(255, 0, 0));
            jButton_reps_clear.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
            jButton_reps_clear.setForeground(new java.awt.Color(255, 255, 255));
            jButton_reps_clear.setText("Clear All");
            jButton_reps_clear.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_reps_clearActionPerformed(evt);
                }
            });
            reports.add(jButton_reps_clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 580, 130, 40));

            jCheckBox_rep_wzn.setText("وزن");
            jCheckBox_rep_wzn.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jCheckBox_rep_wznActionPerformed(evt);
                }
            });
            reports.add(jCheckBox_rep_wzn, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 130, -1, -1));

            left_panel.add(reports, "Ezn");

            add_product.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            add_product.setMaximumSize(new java.awt.Dimension(835, 640));
            add_product.setMinimumSize(new java.awt.Dimension(835, 640));
            add_product.setPreferredSize(new java.awt.Dimension(835, 640));
            add_product.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            jTextField_pro_name.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
            jTextField_pro_name.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
            jTextField_pro_name.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTextField_pro_nameKeyTyped(evt);
                }
            });
            add_product.add(jTextField_pro_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 340, -1));

            jButton_add_pro.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
            jButton_add_pro.setText("إضافه/تعديل");
            jButton_add_pro.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_add_proActionPerformed(evt);
                }
            });
            add_product.add(jButton_add_pro, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 20, 130, 60));

            jTable_pro.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
            jTable_pro.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                    "مسلسل", "أسم الصنف", "وزن الكونه", "اللون"
                }
            ) {
                boolean[] canEdit = new boolean [] {
                    false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            });
            jTable_pro.setGridColor(new java.awt.Color(0, 0, 0));
            jTable_pro.setRowHeight(25);
            jTable_pro.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jTable_proMouseClicked(evt);
                }
            });
            jScrollPane2.setViewportView(jTable_pro);
            if (jTable_pro.getColumnModel().getColumnCount() > 0) {
                jTable_pro.getColumnModel().getColumn(0).setMinWidth(100);
                jTable_pro.getColumnModel().getColumn(0).setPreferredWidth(100);
                jTable_pro.getColumnModel().getColumn(0).setMaxWidth(100);
                jTable_pro.getColumnModel().getColumn(1).setResizable(false);
                jTable_pro.getColumnModel().getColumn(1).setPreferredWidth(150);
                jTable_pro.getColumnModel().getColumn(2).setResizable(false);
                jTable_pro.getColumnModel().getColumn(2).setPreferredWidth(2);
                jTable_pro.getColumnModel().getColumn(3).setResizable(false);
            }

            add_product.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 126, 740, 470));

            jButton_del_pro.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
            jButton_del_pro.setText("حذف");
            jButton_del_pro.setPreferredSize(new java.awt.Dimension(75, 28));
            jButton_del_pro.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_del_proActionPerformed(evt);
                }
            });
            add_product.add(jButton_del_pro, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 20, 120, 60));

            jTextField_Pros_conWight.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
            jTextField_Pros_conWight.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
            jTextField_Pros_conWight.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTextField_Pros_conWightKeyTyped(evt);
                }
            });
            add_product.add(jTextField_Pros_conWight, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 90, 140, 30));

            jLabel24.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jLabel24.setText("الأسم");
            add_product.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, -1, -1));

            jLabel37.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jLabel37.setText("وزن الكونه");
            add_product.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, -1, -1));

            jTextField_Pros_color.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jTextField_Pros_color.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            add_product.add(jTextField_Pros_color, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, 140, 30));

            jLabel49.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel49.setText("اللون");
            add_product.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 50, 70, 30));

            left_panel.add(add_product, "add Pro");

            stock.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            stock.setMaximumSize(new java.awt.Dimension(835, 640));
            stock.setMinimumSize(new java.awt.Dimension(835, 640));
            stock.setPreferredSize(new java.awt.Dimension(835, 640));
            stock.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            jComboBox_stock_Pros.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jComboBox_stock_Pros.addItemListener(new java.awt.event.ItemListener() {
                public void itemStateChanged(java.awt.event.ItemEvent evt) {
                    jComboBox_stock_ProsItemStateChanged(evt);
                }
            });
            stock.add(jComboBox_stock_Pros, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 34, 433, -1));

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

            stock.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 109, 712, 477));

            jLabel15.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
            jLabel15.setText("الصنف");
            stock.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(616, 26, -1, -1));

            jButton_stock_createExcl.setText("Create Excel");
            jButton_stock_createExcl.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_stock_createExclActionPerformed(evt);
                }
            });
            stock.add(jButton_stock_createExcl, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 600, -1, -1));

            left_panel.add(stock, "stock");

            statistics.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            statistics.setMaximumSize(new java.awt.Dimension(835, 640));
            statistics.setMinimumSize(new java.awt.Dimension(835, 640));
            statistics.setPreferredSize(new java.awt.Dimension(835, 640));
            statistics.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

            statistics.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 790, 420));

            jLabel18.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jLabel18.setText("إلي");
            statistics.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, -1, -1));

            jLabel19.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jLabel19.setText("من");
            statistics.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 20, -1, -1));
            statistics.add(jDateChooser_statis_fromDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 70, 140, 30));
            statistics.add(jDateChooser_statis_toDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, 140, 30));

            jLabel20.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jLabel20.setText("التاريخ:");
            statistics.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 30, -1, -1));

            jLabel42.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jLabel42.setText("المجموع");
            statistics.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 580, -1, -1));

            jTextField_statis_tot.setEditable(false);
            jTextField_statis_tot.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            statistics.add(jTextField_statis_tot, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 572, 200, 50));

            jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jButton1.setText("بحث");
            jButton1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton1ActionPerformed(evt);
                }
            });
            statistics.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 85, 80, 40));

            jButton_statis_createExcl.setText("Create Excel");
            jButton_statis_createExcl.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_statis_createExclActionPerformed(evt);
                }
            });
            statistics.add(jButton_statis_createExcl, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 590, -1, -1));

            left_panel.add(statistics, "statis");

            yomia.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            yomia.setMaximumSize(new java.awt.Dimension(835, 640));
            yomia.setMinimumSize(new java.awt.Dimension(835, 640));
            yomia.setPreferredSize(new java.awt.Dimension(835, 640));
            yomia.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

            yomia.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 307, 810, 330));
            yomia.add(jDateChooser_yum_fromDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 220, 180, 30));
            yomia.add(jDateChooser_yum_ToDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 160, 30));

            jLabel22.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jLabel22.setText("من");
            yomia.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 180, -1, -1));

            jLabel23.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jLabel23.setText("إلي");
            yomia.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, -1, -1));

            jButton_youm_search.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jButton_youm_search.setText("بحث");
            jButton_youm_search.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_youm_searchActionPerformed(evt);
                }
            });
            yomia.add(jButton_youm_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 260, -1, -1));

            jButton_youm_refund.setBackground(new java.awt.Color(255, 51, 51));
            jButton_youm_refund.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
            jButton_youm_refund.setText("أسترجاع الأزن");
            jButton_youm_refund.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_youm_refundActionPerformed(evt);
                }
            });
            yomia.add(jButton_youm_refund, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 230, 150, 40));

            jTextField_youm_ClientFilter.setToolTipText("client Filter");
            jTextField_youm_ClientFilter.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTextField_youm_ClientFilterKeyTyped(evt);
                }
            });
            yomia.add(jTextField_youm_ClientFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 20, 170, 40));

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
            jScrollPane10.setViewportView(jTable_youm_clinets);
            if (jTable_youm_clinets.getColumnModel().getColumnCount() > 0) {
                jTable_youm_clinets.getColumnModel().getColumn(0).setPreferredWidth(50);
                jTable_youm_clinets.getColumnModel().getColumn(1).setPreferredWidth(300);
            }

            yomia.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 430, 150));

            jButton_youm_getClients.setBackground(new java.awt.Color(153, 153, 255));
            jButton_youm_getClients.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            jButton_youm_getClients.setText("عملاء");
            jButton_youm_getClients.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_youm_getClientsActionPerformed(evt);
                }
            });
            yomia.add(jButton_youm_getClients, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 20, 120, 40));

            jCheckBox_youm_old.setText("Old");
            yomia.add(jCheckBox_youm_old, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 140, -1, -1));

            jButton_youm_createExcel.setBackground(new java.awt.Color(255, 255, 0));
            jButton_youm_createExcel.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
            jButton_youm_createExcel.setText("طباعة البيان");
            jButton_youm_createExcel.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_youm_createExcelActionPerformed(evt);
                }
            });
            yomia.add(jButton_youm_createExcel, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 230, 150, 40));

            left_panel.add(yomia, "yomia");

            pause.setEnabled(false);
            pause.setFocusable(false);
            pause.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            pause.setMaximumSize(new java.awt.Dimension(835, 640));
            pause.setMinimumSize(new java.awt.Dimension(835, 640));
            pause.setPreferredSize(new java.awt.Dimension(835, 640));
            pause.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            jTextArea_emp.setColumns(20);
            jTextArea_emp.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
            jTextArea_emp.setRows(5);
            jTextArea_emp.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTextArea_empKeyTyped(evt);
                }
            });
            jScrollPane9.setViewportView(jTextArea_emp);

            pause.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 820, 540));

            jLabel38.setFont(new java.awt.Font("Tahoma", 3, 48)); // NOI18N
            jLabel38.setText("اي حاجة");
            jLabel38.setEnabled(false);
            jLabel38.setFocusable(false);
            pause.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, 290, 50));

            left_panel.add(pause, "emp");

            Settings.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            Settings.setMaximumSize(new java.awt.Dimension(835, 640));
            Settings.setMinimumSize(new java.awt.Dimension(835, 640));
            Settings.setPreferredSize(new java.awt.Dimension(835, 640));
            Settings.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            jButton_set_Rest2.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
            jButton_set_Rest2.setText("Reset 2");
            jButton_set_Rest2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_set_Rest2ActionPerformed(evt);
                }
            });
            Settings.add(jButton_set_Rest2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 70, 110, 60));

            jButton_set_Rest1.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
            jButton_set_Rest1.setText("Reset");
            jButton_set_Rest1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_set_Rest1ActionPerformed(evt);
                }
            });
            Settings.add(jButton_set_Rest1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 70, 110, 60));
            Settings.add(jLabel_set_tick1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 140, 70, 50));
            Settings.add(jLabel_set_tick2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 140, 70, 50));
            Settings.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 210, 410, 20));
            Settings.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 430, 20));

            jLabel_set_x.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
            jLabel_set_x.setText("X");
            Settings.add(jLabel_set_x, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 340, 20, 20));

            jLabel_set_y.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
            jLabel_set_y.setText("Y");
            Settings.add(jLabel_set_y, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 340, -1, -1));

            jLabel_set_width.setText("Width");
            Settings.add(jLabel_set_width, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 340, -1, -1));

            jLabel_set_hight.setText("hight");
            Settings.add(jLabel_set_hight, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 340, -1, -1));

            jTextField_set_hight.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTextField_set_hightKeyTyped(evt);
                }
            });
            Settings.add(jTextField_set_hight, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 360, 70, -1));

            jTextField_set_x.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTextField_set_xKeyTyped(evt);
                }
            });
            Settings.add(jTextField_set_x, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 70, -1));

            jTextField_set_y.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTextField_set_yKeyTyped(evt);
                }
            });
            Settings.add(jTextField_set_y, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 360, 70, -1));

            jTextField_set_width.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTextField_set_widthKeyTyped(evt);
                }
            });
            Settings.add(jTextField_set_width, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 360, 70, -1));

            jButton_set_changearea.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
            jButton_set_changearea.setText("Sett");
            jButton_set_changearea.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_set_changeareaActionPerformed(evt);
                }
            });
            Settings.add(jButton_set_changearea, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 430, 80, 40));

            jLabel40.setBackground(new java.awt.Color(204, 255, 204));
            jLabel40.setFont(new java.awt.Font("Segoe UI", 3, 48)); // NOI18N
            jLabel40.setText(Version);
            jLabel40.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(102, 255, 255)));
            jLabel40.setFocusable(false);
            jLabel40.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel40.setName("setting_version"); // NOI18N
            jLabel40.setRequestFocusEnabled(false);
            jLabel40.setVerifyInputWhenFocusTarget(false);
            Settings.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 500, 690, 140));
            jLabel40.getAccessibleContext().setAccessibleDescription("Setting page Version");

            jCheckBox_ignore_limits.setText("ignore limits in Wzn");
            Settings.add(jCheckBox_ignore_limits, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 180, 40));

            jLabel45.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jLabel45.setText("الفرق الوزن في عمل الاذن");
            Settings.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 230, -1, -1));

            jTextField_setting_repsDiff.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jTextField_setting_repsDiff.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTextField_setting_repsDiffKeyTyped(evt);
                }
            });
            Settings.add(jTextField_setting_repsDiff, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 280, 160, 70));

            jCheckBox_rev_order.setText("Rev order");
            Settings.add(jCheckBox_rev_order, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 230, -1, -1));

            left_panel.add(Settings, "settings");

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
        open_panel(in_data);
        if (jComboBox_pro_in_storage.getSelectedIndex() == -1) {
            DefaultTableModel model = (DefaultTableModel) jTable_storage.getModel();
            model.setRowCount(0);
        }
        this.setAlwaysOnTop(true);
    }//GEN-LAST:event_jButton_Mizan_openerActionPerformed

    private void jButton_addPro_openerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_addPro_openerActionPerformed
        pro_Table_SelectedID = 0;
        open_panel(add_product);
        fill_pro_table();
    }//GEN-LAST:event_jButton_addPro_openerActionPerformed

    private void jButton_Ezn_openerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Ezn_openerActionPerformed
        open_panel(reports);
//        ((DefaultTableModel) jTable_rep_select.getModel()).setRowCount(0);
//        ((DefaultTableModel) jTable_rep_preview.getModel()).setRowCount(0);
//        jComboBox_rep_Pros.setSelectedIndex(-1);
//        second = false;
//        jTextField_rep_totweight.setText("");
//        jComboBox_rep_palletsNrep.removeAllItems();
    }//GEN-LAST:event_jButton_Ezn_openerActionPerformed

    private void jTextField_lotKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_lotKeyTyped
        textbox_length_limiter(evt, jTextField_lot, 5);
        char input = evt.getKeyChar();
        if (Character.isDigit(input)) {
            evt.setKeyChar(ToNumArab(input));
        }
        if ((!Character.isDigit(input)) && input != 'أ' && input != 'س' && input != 'و' && input != 'د' && input != 'ط' && input != 'ب' && input != 'ا' && input != 'ع' && input != 'ه' && input != 'م' && input != 'ن' && input != 'ي' && input != 'ض') {
            evt.consume();
        }
        calc_net_weight();
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            jTextField_pallet_num.requestFocusInWindow();
        }
    }//GEN-LAST:event_jTextField_lotKeyTyped

    private void jTextField_num_of_conKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_num_of_conKeyTyped
        textbox_number(evt, jTextField_num_of_con, 3);
        calc_net_weight();
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            jTextField_bag_weight.requestFocusInWindow();
        }
    }//GEN-LAST:event_jTextField_num_of_conKeyTyped

    private void jTextField_pallet_numKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_pallet_numKeyTyped
        textbox_number(evt, jTextField_pallet_num, 5);
        calc_pallet_weight();
        jCheckBox_M_Markpage.setSelected(false);
        calc_net_weight();
        if (jTextField_pallet_num.getText().isBlank()) {
            pallet_weight.setText("");
        }
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            jTextField_num_of_con.requestFocusInWindow();
        }
    }//GEN-LAST:event_jTextField_pallet_numKeyTyped

    private void jTextField_net_weightKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_net_weightKeyTyped
        if (evt.getKeyChar() == KeyEvent.VK_MINUS && (!jTextField_net_weight.getText().isBlank())) {
            double x = ToDoubleEnglish(jTextField_weight.getText());
            DecimalFormat df2 = new DecimalFormat("#.##");
            if ((evt.getModifiersEx() & KeyEvent.SHIFT_DOWN_MASK) != 0) {
                jTextField_weight.setText(ToDoubleArabic(df2.format(x + 0.02) + ""));
            } else {
                jTextField_weight.setText(ToDoubleArabic(df2.format(x - 0.02) + ""));
            }
            calc_net_weight();
        }
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            calc_net_weight();
            if (jCheckBox_rev_order.isSelected()) {
                jTextField_weight.requestFocusInWindow();
            } else {
                jTextField_num_of_con.requestFocusInWindow();
            }
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
        textbox_number(evt, jTextField_bag_weight, BagMax);
        calc_net_weight();
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            jTextField_weight.requestFocusInWindow();
        }
    }//GEN-LAST:event_jTextField_bag_weightKeyTyped

    private void jTextField_weightKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_weightKeyTyped
        textbox_number_weight(evt, jTextField_weight, 8);
        calc_net_weight();
        if (evt.getKeyChar() == KeyEvent.VK_ENTER && !jTextField_weight.getText().isBlank()) {
            jTextField_net_weight.requestFocusInWindow();
        }
        calc_net_weight();
    }//GEN-LAST:event_jTextField_weightKeyTyped

    private void jTextField_weight_of_conKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_weight_of_conKeyTyped
        textbox_number(evt, jTextField_weight_of_con, 4);
        calc_net_weight();
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            jTextField_lot.requestFocusInWindow();
        }
    }//GEN-LAST:event_jTextField_weight_of_conKeyTyped

    private void jButton_add_dataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_add_dataActionPerformed
        try {
            if (!jTextField_net_weight.getText().isBlank() && !jTextField_lot.getText().isBlank() && !jTextField_pallet_num.getText().isBlank() && !jTextField_bag_weight.getText().isBlank() && !jTextField_num_of_con.getText().isBlank() && !jTextField_weight.getText().isBlank() && jComboBox_pro_in_storage.getSelectedIndex() != -1) {

                if ((ToDoubleEnglish(jTextField_weight.getText()) <= 60.0 && ToDoubleEnglish(jTextField_net_weight.getText()) > 5.0) || jCheckBox_ignore_limits.isSelected()) {
                    calc_net_weight();

                    ResultSet st = opj.dataRead("count(storage_id) ", "storage",
                            "pallet_numb=" + ToDoubleEnglish(jTextField_pallet_num.getText()) + " and lot=N'"
                            + ToStringEnglish(jTextField_lot.getText())
                            + "' and pro_id=(select pro_id from products where pro_name=N'"
                            + jComboBox_pro_in_storage.getSelectedItem() + "') ");
                    st.next();
                    do {
                        int a = (int) ToDoubleEnglish(jTextField_pallet_num.getText());
                        jTextField_pallet_num.setText(ToDoubleArabic((st.getInt(1) >= 20) ? ++a + "" : a + ""));
                        st = opj.dataRead("count(storage_id) ", "storage",
                                "pallet_numb=" + ToDoubleEnglish(jTextField_pallet_num.getText()) + " and lot=N'"
                                + ToStringEnglish(jTextField_lot.getText())
                                + "' and pro_id=(select pro_id from products where pro_name=N'"
                                + jComboBox_pro_in_storage.getSelectedItem() + "') ");
                        st.next();
                    } while (st.getInt(1) >= 20);

                    st = opj.dataRead("count (used)", "storage", "pallet_numb=" + ToDoubleEnglish(jTextField_pallet_num.getText()) + " and  lot=N'" + ToStringEnglish(jTextField_lot.getText()) + "' and pro_id=(select pro_id from products where pro_name=N'" + jComboBox_pro_in_storage.getSelectedItem() + "') and used =" + 1 + " ");
                    st.next();
                    int UC = st.getInt(1);
                    st = opj.dataRead("count (used)", "storage", "pallet_numb=" + ToDoubleEnglish(jTextField_pallet_num.getText()) + " and  lot=N'" + ToStringEnglish(jTextField_lot.getText()) + "' and pro_id=(select pro_id from products where pro_name=N'" + jComboBox_pro_in_storage.getSelectedItem() + "') and used =" + 0 + " ");
                    st.next();
                    int NUC = st.getInt(1);
                    if (UC > 0 && NUC > 0) {
                        JOptionPane.showMessageDialog(this, addStyle(" رجاء حل مشكلة التعليم اولا"), "إنتبه", JOptionPane.PLAIN_MESSAGE);
                        return;
                    } else if ((UC == 0 && NUC > 0) && jCheckBox_M_Markpage.isSelected()) {
                        JOptionPane.showMessageDialog(this, addStyle(" خطأ في تعليم الشكاره"), "إنتبه", JOptionPane.PLAIN_MESSAGE);
                        return;
                    } else if ((NUC == 0 && UC > 0) && !jCheckBox_M_Markpage.isSelected()) {
                        JOptionPane.showMessageDialog(this, addStyle(" خطأ في تعليم الشكاره"), "إنتبه", JOptionPane.PLAIN_MESSAGE);
                        return;
                    }

                    opj.inData("storage", "pro_id,tot_wight,weight_,lot,pallet_numb,date_,num_of_con,used",
                            " (select pro_id from products where pro_name=N'" + jComboBox_pro_in_storage.getSelectedItem()
                            + "')" + "," + ToDoubleEnglish(jTextField_weight.getText()) + "," + ToDoubleEnglish(jTextField_net_weight.getText()) + ",N'"
                            + ToStringEnglish(jTextField_lot.getText()) + "',"
                            + ToDoubleEnglish(jTextField_pallet_num.getText()) + ",GETDATE(),"
                            + ToDoubleEnglish(jTextField_num_of_con.getText()) + "," + (jCheckBox_M_Markpage.isSelected() ? 1 : 0) + " ");
                    st = opj.dataRead("TOP 1 used", "storage", "pallet_numb=" + ToDoubleEnglish(jTextField_pallet_num.getText()) + " and  lot=N'" + ToStringEnglish(jTextField_lot.getText()) + "' and pro_id=(select pro_id from products where pro_name=N'" + jComboBox_pro_in_storage.getSelectedItem() + "')");
                    st.next();
                    jCheckBox_M_Markpage.setSelected(st.getBoolean(1));

                    calc_pallet_weight();
                    st = opj.dataRead("count(storage_id) ", "storage",
                            "pallet_numb=" + ToDoubleEnglish(jTextField_pallet_num.getText()) + " and lot=N'"
                            + ToStringEnglish(jTextField_lot.getText())
                            + "' and pro_id=(select pro_id from products where pro_name=N'"
                            + jComboBox_pro_in_storage.getSelectedItem() + "') ");
                    st.next();
                    int count = st.getInt(1);
                    jProgressBar_pallet.setValue(count);
                    if (jCheckBox_print.isSelected() || jCheckBox_QR.isSelected()) {
                        if (printex(new ArrayList<>(Arrays.asList(
                                jTextField_pallet_num.getText(), jTextField_Color.getText(),
                                jComboBox_pro_in_storage.getSelectedItem().toString(),
                                jTextField_lot.getText(), jTextField_num_of_con.getText(),
                                jTextField_weight.getText(), jTextField_net_weight.getText())), jCheckBox_print.isSelected(), jCheckBox_QR.isSelected())) {
                            jTextField_net_weight.setText("");
                            jTextField_weight.setText("");
                            jTextField_bag_weight.setText("");
                            fill_storage_table();
                        } else {
                            JOptionPane.showMessageDialog(this, addStyle("printing faild"), "إنتبه", JOptionPane.PLAIN_MESSAGE);
                        }
                    } else {
                        jTextField_net_weight.setText("");
                        jTextField_weight.setText("");
                        jTextField_bag_weight.setText("");
                        fill_storage_table();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, addStyle("خطأ في وزن الشيكاره "), "إنتبه", JOptionPane.PLAIN_MESSAGE);
                    jTextField_weight.requestFocus();
                    jTextField_weight.selectAll();
                }
            } else {
                JOptionPane.showMessageDialog(this, addStyle(" برجاء إدخال البيانات كامله"), "إنتبه", JOptionPane.PLAIN_MESSAGE);
            }
        } catch (WriterException | HeadlessException | IOException | SQLException | PrinterException | InterruptedException ex) {
            Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage(), "exception", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_jButton_add_dataActionPerformed

    private void jButton_del_dataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_del_dataActionPerformed
        if (JOptionPane.showConfirmDialog(this, addStyle("هل تريد الحذف ؟"), "تنبيه", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (jTable_storage.getSelectedRowCount() == 1) {
                opj.delData("storage", "storage_id=" + Integer.valueOf(jTable_storage.getModel().getValueAt(jTable_storage.getSelectedRow(), 4).toString()) + " ");
                JOptionPane.showMessageDialog(this, addStyle(" تم حذف البيان بنجاح "), "ناجح", JOptionPane.PLAIN_MESSAGE);
                fill_storage_table();
            } else if (jTable_storage.getSelectedRowCount() > 1) {
                for (int row : jTable_storage.getSelectedRows()) {
                    opj.delData("storage", "storage_id=" + Integer.valueOf(jTable_storage.getModel().getValueAt(row, 4).toString()) + " ");
                }
                fill_storage_table();
                JOptionPane.showMessageDialog(this, addStyle(" تم حذف البيــانات بنجاح "), "ناجح", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, addStyle(" برجاء أختيار بيان من الجدول أولا  "), "إنتبه", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton_del_dataActionPerformed

    private void jButton_add_proActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_add_proActionPerformed
        if (pro_Table_SelectedID == 0) {
            if (jTextField_pro_name.getText().isBlank() || jTextField_Pros_conWight.getText().isBlank()) {
                JOptionPane.showMessageDialog(this, addStyle(" برجاء أدخال البيانات كامله  "), "إنتبه", JOptionPane.PLAIN_MESSAGE);
            } else {
                try {
                    if (opj.dataRead("*", "products", "pro_name=N'" + jTextField_pro_name.getText() + "'").next()) {
                        JOptionPane.showMessageDialog(this, addStyle("هذا الصنف موجود بالفعل "), "إنتبه", JOptionPane.PLAIN_MESSAGE);
                    } else {
                        opj.inData("products", "pro_name,weight_of_con,Color", "N'" + jTextField_pro_name.getText() + "',N'" + ToStringEnglish(jTextField_Pros_conWight.getText()) + "',N'" + jTextField_Pros_color.getText() + "'");
                        jTextField_pro_name.setText("");
                        jTextField_Pros_conWight.setText("");
                        jTextField_Pros_color.setText("");
                        fill_pro_table();
                        combox_fill(jComboBox_pro_in_storage, opj.dataRead("pro_name", "products"), true);
                        combox_fill(jComboBox_rep_Pros, opj.dataRead("pro_name", "products"), true);
                        JOptionPane.showMessageDialog(this, addStyle(" تم إدخال الصنف بنجاح  "), "ناجح", JOptionPane.PLAIN_MESSAGE);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "exception", JOptionPane.PLAIN_MESSAGE);
                }
            }
        } else {
            if (jTextField_pro_name.getText().isBlank()) {
                JOptionPane.showMessageDialog(this, addStyle(" برجاء أدخال اسم الصنف  "), "إنتبه", JOptionPane.PLAIN_MESSAGE);
            } else {
                opj.update("products", "pro_name=N'" + jTextField_pro_name.getText() + "' ,weight_of_con=N'" + ToStringEnglish(jTextField_Pros_conWight.getText()) + "' ,Color=N'" + jTextField_Pros_color.getText() + "' ", "pro_id=" + pro_Table_SelectedID + " ");
                pro_Table_SelectedID = 0;
                jTextField_pro_name.setText("");
                jTextField_Pros_conWight.setText("");
                jTextField_Pros_color.setText("");
                fill_pro_table();
                combox_fill(jComboBox_pro_in_storage, opj.dataRead("pro_name", "products"), true);
                combox_fill(jComboBox_rep_Pros, opj.dataRead("pro_name", "products"), true);
                JOptionPane.showMessageDialog(this, addStyle("تم تعديل الصنف بنجاح "), "ناجح", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton_add_proActionPerformed

    private void jTextField_pro_nameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_pro_nameKeyTyped
        textbox_length_limiter(evt, jTextField_pro_name, 35);
        char input = evt.getKeyChar();
        if (Character.isDigit(input)) {
            evt.setKeyChar(ToNumArab(input));
        }
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            jButton_add_pro.doClick();
        }
    }//GEN-LAST:event_jTextField_pro_nameKeyTyped

    private void jTable_proMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_proMouseClicked
        try {
            TableModel model = jTable_pro.getModel();
            jTextField_pro_name.setText(model.getValueAt(jTable_pro.getSelectedRow(), 1).toString());
            jTextField_Pros_conWight.setText(model.getValueAt(jTable_pro.getSelectedRow(), 2).toString());
            pro_Table_SelectedID = Integer.parseInt(model.getValueAt(jTable_pro.getSelectedRow(), 0).toString());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "exception", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_jTable_proMouseClicked

    private void jButton_del_proActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_del_proActionPerformed
        if (pro_Table_SelectedID != 0) {
            try {
                if (!opj.dataRead("*", "storage", "pro_id=" + pro_Table_SelectedID + "").next()) {
                    opj.delData("products", "pro_id=" + pro_Table_SelectedID + " ");
                    JOptionPane.showMessageDialog(this, addStyle("تم حذف الصنف بنجاح "), "ناجح", JOptionPane.PLAIN_MESSAGE);
                    jTextField_pro_name.setText("");
                    pro_Table_SelectedID = 0;
                    fill_pro_table();
                    combox_fill(jComboBox_pro_in_storage, opj.dataRead("pro_name", "products"), true);
                    combox_fill(jComboBox_rep_Pros, opj.dataRead("pro_name", "products"), true);
                } else {
                    JOptionPane.showMessageDialog(this, addStyle("لا يمكن حذف هذا الصنف "), "إنتبه", JOptionPane.PLAIN_MESSAGE);
                }
            } catch (SQLException ex) {
                Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, ex.getMessage(), "exception", JOptionPane.PLAIN_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, addStyle(" برجاء أختيار صنف من الجدول أولا"), "إنتبه", JOptionPane.PLAIN_MESSAGE);
        }

    }//GEN-LAST:event_jButton_del_proActionPerformed

    private void jComboBox_pro_in_storageItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_pro_in_storageItemStateChanged
        jTextField_lot.setText("");

        if (jComboBox_pro_in_storage.hasFocus()) {
            fill_storage_table();
        }
        if (jTable_storage.getRowCount() != 0) {
            jTextField_lot.setText(jTable_storage.getValueAt(0, 2) + "");
        }
        if (jTable_storage.getRowCount() != 0) {
            if (!jTable_storage.getValueAt(0, 5).equals("٢٠")) {
                jTextField_pallet_num.setText(jTable_storage.getValueAt(0, 3) + "");
            } else {

                jTextField_pallet_num.setText(ToDoubleArabic((Integer.parseInt(ToStringEnglish(jTable_storage.getValueAt(0, 3) + "")) + 1) + ""));
            }
        }


    }//GEN-LAST:event_jComboBox_pro_in_storageItemStateChanged

    private void jComboBox_rep_ProsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_rep_ProsItemStateChanged
        if (jComboBox_rep_Pros.hasFocus()) {
            ((DefaultTableModel) jTable_rep_select.getModel()).setRowCount(0);
            ResultSet st = opj.dataRead("count(*),sum(weight_),lot,pallet_numb,used", "storage", "pro_id=(select pro_id from products where pro_name=N'" + jComboBox_rep_Pros.getSelectedItem().toString() + "' )  GROUP BY lot,pallet_numb,used");
            try {
                while (st.next()) {

                    ((DefaultTableModel) jTable_rep_select.getModel()).addRow(new Object[]{ToDoubleArabic(st.getString(1)), ToDoubleArabic(st.getString(2)), ToDoubleArabic(st.getString(3)), ToDoubleArabic(st.getString(4)), ToDoubleArabic(st.getString(5))});
                }
            } catch (SQLException ex) {
                Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, ex.getMessage(), "exception", JOptionPane.PLAIN_MESSAGE);
            }
            ((DefaultTableModel) jTable_rep_preview.getModel()).setRowCount(0);
            serial = 0;
            order_ids.clear();
            jTextField_rep_totweight.setText("");
            jComboBox_rep_palletsNrep.removeAllItems();
        }
    }//GEN-LAST:event_jComboBox_rep_ProsItemStateChanged
    int serial = 0;
    List<String> order_ids = new ArrayList<>();
    boolean second = false;
    String name_of_type, wieght, num_of_shikra;
    JTable first_table;
    List<String> ne = new ArrayList<>();


    private void jButton_rep_printRepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_rep_printRepActionPerformed
        double ss = jTextField_rep_totweight.getText().isEmpty() ? 0.0 : ToDoubleEnglish(jTextField_rep_totweight.getText());
        if (jTable_rep_preview.getRowCount() >= 0 && !jTextField_rep_clientName.getText().isBlank() && (jTable_rep_preview.getRowCount() == (int) ToDoubleEnglish(jTextField_rep_numOfBag.getText()) || !(ToDoubleEnglish(jTextField_rep_numOfBag.getText()) >= ss + repDiff))) {

            if (JOptionPane.showConfirmDialog(this, addStyle("سيتم التصدير للأكسل "), "تنبيه",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                print_shit opject = new print_shit();
                if (jTable_rep_preview.getRowCount() <= 60 && jCheckBox_rep_2n1.isSelected()) {
                    if (!second) {

                        name_of_type = jComboBox_rep_Pros.getSelectedItem().toString();
                        wieght = ToDoubleEnglish(jTextField_rep_totweight.getText()) + "";
                        num_of_shikra = jTable_rep_preview.getRowCount() + "";
                        ne = new ArrayList<>(order_ids);

                        first_table = new JTable();
                        String[] columnNames = {"مسلسل", "وزن", "لوط", "رقم البالتة"};
                        DefaultTableModel d = new DefaultTableModel(columnNames, 0);
                        for (int i = 0; i < jTable_rep_preview.getRowCount(); i++) {
                            Object[] row = {ToDoubleEnglish(jTable_rep_preview.getValueAt(i, 0) + ""), ToDoubleEnglish(jTable_rep_preview.getValueAt(i, 1) + ""), ToStringEnglish(jTable_rep_preview.getValueAt(i, 2) + ""), ToDoubleEnglish(jTable_rep_preview.getValueAt(i, 3) + "")};
                            d.addRow(row);
                        }
                        first_table.setModel(d);
                        ((DefaultTableModel) jTable_rep_preview.getModel()).setRowCount(0);
                        jTextField_rep_numOfBag.setText("");

                        jTextField_rep_totweight.setText("");
                        jComboBox_rep_palletsNrep.removeAllItems();

                        JOptionPane.showMessageDialog(this, addStyle(" ادخل الأذن الثاني  "), "إنتبه", JOptionPane.PLAIN_MESSAGE);

                    } else {
                        if (name_of_type == jComboBox_rep_Pros.getSelectedItem() && first_table.getValueAt(0, 2).toString().equals(ToStringEnglish(jTable_rep_preview.getValueAt(0, 2).toString()))) {
                            JOptionPane.showMessageDialog(this, addStyle(" برجاء تغير الصنف أو اللوط  "), "إنتبه", JOptionPane.PLAIN_MESSAGE);

                            second = !second;
                        } else {
                            opject.excel_60_60(serial, order_ids, ne, jTextField_rep_totweight, jTextField_rep_clientName, jComboBox_rep_Pros, jTable_rep_preview, first_table, num_of_shikra, wieght, name_of_type, opj, jFileChooser1);
                            jCheckBox_rep_2n1.setSelected(false);
                            jTextField_rep_clientName.setText("");
                            jTextField_rep_numOfBag.setText("");
                        }
                    }
                    second = !second;
                } else if (jTable_rep_preview.getRowCount() <= 120) {
                    opject.excel_120(serial, order_ids, jTextField_rep_totweight, jTextField_rep_clientName, jComboBox_rep_Pros, jTable_rep_preview, opj, jFileChooser1);
                    jTextField_rep_clientName.setText("");
                    jTextField_rep_numOfBag.setText("");
                } else if (jTable_rep_preview.getRowCount() <= 160) {
                    opject.excel_160(serial, order_ids, jTextField_rep_totweight, jTextField_rep_clientName, jComboBox_rep_Pros, jTable_rep_preview, opj, jFileChooser1);
                    jTextField_rep_clientName.setText("");
                    jTextField_rep_numOfBag.setText("");
                }
                jComboBox_rep_palletsNrep.removeAllItems();
            }
        } else {
            JOptionPane.showMessageDialog(this, addStyle(" تدخل البيانات كامله أولا"), "إنتبه", JOptionPane.PLAIN_MESSAGE);
        }

    }//GEN-LAST:event_jButton_rep_printRepActionPerformed

    private void jTextField_rep_numOfBagKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_rep_numOfBagKeyTyped
        textbox_number(evt, jTextField_rep_numOfBag, jCheckBox_rep_wzn.isSelected() ? 4 : 3);
    }//GEN-LAST:event_jTextField_rep_numOfBagKeyTyped

    private void jTextField_rep_numOfBagKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_rep_numOfBagKeyReleased
        if (jComboBox_rep_Pros.getSelectedIndex() != -1) {
            ((DefaultTableModel) jTable_rep_select.getModel()).setRowCount(0);
            ResultSet st = opj.dataRead("count(*),sum(weight_),lot,pallet_numb,used", "storage", "pro_id=(select pro_id from products where pro_name=N'" + jComboBox_rep_Pros.getSelectedItem().toString() + "' )  GROUP BY lot,pallet_numb,used");
            try {
                while (st.next()) {
                    ((DefaultTableModel) jTable_rep_select.getModel()).addRow(new Object[]{ToDoubleArabic(st.getString(1)), ToDoubleArabic(st.getString(2)), ToDoubleArabic(st.getString(3)), ToDoubleArabic(st.getString(4)), ToDoubleArabic(st.getString(5))});
                }
            } catch (SQLException ex) {
                Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, ex.getMessage(), "exception", JOptionPane.PLAIN_MESSAGE);
            }

            ((DefaultTableModel) jTable_rep_preview.getModel()).setRowCount(0);
            serial = 0;
            order_ids.clear();
            jTextField_rep_totweight.setText("");
        }

    }//GEN-LAST:event_jTextField_rep_numOfBagKeyReleased

    private void jTextField_rep_numOfBagFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_rep_numOfBagFocusLost
        if (!jTextField_rep_numOfBag.getText().isBlank())
            if (ToDoubleEnglish(jTextField_rep_numOfBag.getText()) > 160 && !jCheckBox_rep_wzn.isSelected()) {
                jTextField_rep_numOfBag.setText("١٦٠");
            }
    }//GEN-LAST:event_jTextField_rep_numOfBagFocusLost

    private void jTextField_rep_totweightKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_rep_totweightKeyTyped
        evt.consume();
    }//GEN-LAST:event_jTextField_rep_totweightKeyTyped

    private void jTextField_rep_totweightKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_rep_totweightKeyPressed
        evt.consume();
    }//GEN-LAST:event_jTextField_rep_totweightKeyPressed

    private void jTable_rep_selectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_rep_selectMouseClicked
        try {
            if (evt.getButton() != MouseEvent.BUTTON1) {
                return;
            }
            double weight_sum = 0.0;
            if (!jTextField_rep_numOfBag.getText().isBlank()) {
                if (jCheckBox_rep_wzn.isSelected()) {
                    double ss = jTextField_rep_totweight.getText().isEmpty() ? 0.0 : ToDoubleEnglish(jTextField_rep_totweight.getText());
                    double wight_of_order = ToDoubleEnglish(jTextField_rep_numOfBag.getText());
                    int pcount = 0;
                    boolean loot = true;
                    if (ToDoubleEnglish(jTextField_rep_numOfBag.getText()) >= ss + repDiff) {
                        if (jTable_rep_preview.getRowCount() > 0) {
                            if (!jTable_rep_preview.getValueAt(0, 2).toString().equals(jTable_rep_select.getValueAt(jTable_rep_select.getSelectedRow(), 2))) {
                                loot = false;
                            }
                        }
                        if (loot) {
                            if (JOptionPane.showConfirmDialog(this, "هل تريد إضافه البالته رقم  " + jTable_rep_select.getValueAt(jTable_rep_select.getSelectedRow(), 3) + "", "تنبيه",
                                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

                                ResultSet st = opj.dataRead("weight_,lot,pallet_numb,storage_id", "storage", " pro_id=(select pro_id from products where pro_name=N'" + jComboBox_rep_Pros.getSelectedItem().toString() + "' ) and "
                                        + "pallet_numb=" + ToDoubleEnglish(jTable_rep_select.getModel().getValueAt(jTable_rep_select.getSelectedRow(), 3).toString()) + " "
                                        + "and lot=N'" + ToStringEnglish(jTable_rep_select.getModel().getValueAt(jTable_rep_select.getSelectedRow(), 2).toString()) + "'  order by pallet_numb ,storage_id DESC ");

                                try {
                                    while (st.next()) {
                                        if (wight_of_order + repDiff > ss + weight_sum + Double.parseDouble(st.getString(1))) {
                                            pcount++;
                                            weight_sum += Double.parseDouble(st.getString(1));
                                            ((DefaultTableModel) jTable_rep_preview.getModel()).addRow(new Object[]{ToDoubleArabic((jTable_rep_preview.getRowCount() + 1) + ""), ToDoubleArabic(st.getString(1)), ToDoubleArabic(st.getString(2)), ToDoubleArabic(st.getString(3))});
                                            order_ids.add(st.getString(4));
                                            if (((DefaultComboBoxModel) jComboBox_rep_palletsNrep.getModel()).getIndexOf(ToDoubleArabic(st.getString(3))) == -1) {
                                                jComboBox_rep_palletsNrep.addItem(ToDoubleArabic(st.getString(3)));
                                            }

                                        }
                                    }
                                } catch (SQLException ex) {
                                    JOptionPane.showMessageDialog(this, ex, "إنتبه", JOptionPane.PLAIN_MESSAGE);
                                }
                                ss += weight_sum;
                                jTextField_rep_totweight.setText(ToDoubleArabic(new DecimalFormat("##.##").format(ss) + ""));
                                if (wight_of_order >= ToDoubleEnglish(jTable_rep_select.getModel().getValueAt(jTable_rep_select.getSelectedRow(), 1).toString()) + ss) {
                                    ((DefaultTableModel) jTable_rep_select.getModel()).removeRow(jTable_rep_select.getSelectedRow());
                                } else {
                                    DecimalFormat df = new DecimalFormat("#.###");
                                    jTable_rep_select.getModel().setValueAt(ToDoubleArabic(df.format(ToDoubleEnglish(jTable_rep_select.getModel().getValueAt(jTable_rep_select.getSelectedRow(), 1).toString()) - weight_sum)), jTable_rep_select.getSelectedRow(), 1);
                                    jTable_rep_select.getModel().setValueAt(ToDoubleArabic((Integer.parseInt(jTable_rep_select.getModel().getValueAt(jTable_rep_select.getSelectedRow(), 0).toString()) - pcount) + ""), jTable_rep_select.getSelectedRow(), 0);
                                    if (ToDoubleEnglish(jTable_rep_select.getValueAt(jTable_rep_select.getSelectedRow(), 0).toString()) <= 0.0 || ToDoubleEnglish(jTable_rep_select.getValueAt(jTable_rep_select.getSelectedRow(), 1).toString()) <= 0.0) {
                                        ((DefaultTableModel) jTable_rep_select.getModel()).removeRow(jTable_rep_select.getSelectedRow());
                                    }

                                }

                                jTable_rep_preview.changeSelection(jTable_rep_preview.getRowCount() - 1, 0, false, false);
                            }

                        } else {
                            JOptionPane.showMessageDialog(this, addStyle("لا يمكن ادخال اكثر من لوط  "), "إنتبه", JOptionPane.PLAIN_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, addStyle(" لقد اكتمل الوزن "), "إنتبه", JOptionPane.PLAIN_MESSAGE);
                    }

                } else {
                    if ((int) ToDoubleEnglish(jTextField_rep_numOfBag.getText()) != jTable_rep_preview.getRowCount()) {
                        boolean loot = true;
                        if (jTable_rep_preview.getRowCount() > 0) {
                            if (!jTable_rep_preview.getValueAt(0, 2).toString().equals(jTable_rep_select.getValueAt(jTable_rep_select.getSelectedRow(), 2))) {
                                loot = false;
                            }
                        }
                        if (loot) {
                            if (JOptionPane.showConfirmDialog(this, "هل تريد إضافه البالته رقم  " + jTable_rep_select.getValueAt(jTable_rep_select.getSelectedRow(), 3) + "", "تنبيه",
                                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                                int num_of_order = (int) ToDoubleEnglish(jTextField_rep_numOfBag.getText());
                                if (num_of_order > 0 && serial < num_of_order) {
                                    ResultSet st = opj.dataRead(" TOP (" + (num_of_order - serial) + ") weight_,lot,pallet_numb,storage_id", "storage", " pro_id=(select pro_id from products where pro_name=N'" + jComboBox_rep_Pros.getSelectedItem().toString() + "' ) and "
                                            + "pallet_numb=" + ToDoubleEnglish(jTable_rep_select.getModel().getValueAt(jTable_rep_select.getSelectedRow(), 3).toString()) + " "
                                            + "and lot=N'" + ToStringEnglish(jTable_rep_select.getModel().getValueAt(jTable_rep_select.getSelectedRow(), 2).toString()) + "'  order by pallet_numb ,storage_id DESC ");
                                    int numm = serial;
                                    try {
                                        while (st.next()) {
                                            weight_sum += Double.parseDouble(st.getString(1));
                                            ((DefaultTableModel) jTable_rep_preview.getModel()).addRow(new Object[]{ToDoubleArabic(++serial + ""), ToDoubleArabic(st.getString(1)), ToDoubleArabic(st.getString(2)), ToDoubleArabic(st.getString(3))});
                                            order_ids.add(st.getString(4));
                                            if (((DefaultComboBoxModel) jComboBox_rep_palletsNrep.getModel()).getIndexOf(ToDoubleArabic(st.getString(3))) == -1) {
                                                jComboBox_rep_palletsNrep.addItem(ToDoubleArabic(st.getString(3)));
                                            }
                                        }
                                    } catch (SQLException ex) {
                                        JOptionPane.showMessageDialog(this, ex, "إنتبه", JOptionPane.PLAIN_MESSAGE);
                                    }
                                    double ss;
                                    ss = weight_sum;
                                    if (!jTextField_rep_totweight.getText().isEmpty()) {
                                        ss += ToDoubleEnglish(jTextField_rep_totweight.getText());
                                    }
                                    jTextField_rep_totweight.setText(ToDoubleArabic(new DecimalFormat("##.##").format(ss) + ""));
                                    if (num_of_order >= ToDoubleEnglish(jTable_rep_select.getModel().getValueAt(jTable_rep_select.getSelectedRow(), 0).toString()) && jTable_rep_preview.getRowCount() < num_of_order) {
                                        ((DefaultTableModel) jTable_rep_select.getModel()).removeRow(jTable_rep_select.getSelectedRow());
                                    } else {
                                        DecimalFormat df = new DecimalFormat("#.###");
                                        st = opj.dataRead("TOP (" + (num_of_order - numm) + ") weight_ ", "storage", " pro_id=(select pro_id from products where pro_name=N'" + jComboBox_rep_Pros.getSelectedItem().toString() + "' ) and "
                                                + "pallet_numb=" + ToDoubleEnglish(jTable_rep_select.getModel().getValueAt(jTable_rep_select.getSelectedRow(), 3).toString()) + " "
                                                + "and lot=N'" + ToStringEnglish(jTable_rep_select.getModel().getValueAt(jTable_rep_select.getSelectedRow(), 2).toString()) + "'   order by pallet_numb ,storage_id DESC ");
                                        try {
                                            while (st.next()) {
                                                jTable_rep_select.getModel().setValueAt(ToDoubleArabic(df.format(ToDoubleEnglish(jTable_rep_select.getModel().getValueAt(jTable_rep_select.getSelectedRow(), 1).toString()) - Double.parseDouble(st.getString(1)))), jTable_rep_select.getSelectedRow(), 1);
                                            }
                                        } catch (SQLException ex) {
                                            JOptionPane.showMessageDialog(this, ex, "إنتبه", JOptionPane.PLAIN_MESSAGE);
                                        }
                                        jTable_rep_select.getModel().setValueAt(ToDoubleArabic((Integer.parseInt(jTable_rep_select.getModel().getValueAt(jTable_rep_select.getSelectedRow(), 0).toString()) - (num_of_order - numm)) + ""), jTable_rep_select.getSelectedRow(), 0);
                                        if (ToDoubleEnglish(jTable_rep_select.getValueAt(jTable_rep_select.getSelectedRow(), 0).toString()) == 0.0 && ToDoubleEnglish(jTable_rep_select.getValueAt(jTable_rep_select.getSelectedRow(), 1).toString()) == 0.0) {
                                            ((DefaultTableModel) jTable_rep_select.getModel()).removeRow(jTable_rep_select.getSelectedRow());
                                        }
                                    }
                                }
                                jTable_rep_preview.changeSelection(jTable_rep_preview.getRowCount() - 1, 0, false, false);
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, addStyle("لا يمكن ادخال اكثر من لوط  "), "إنتبه", JOptionPane.PLAIN_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, addStyle(" لقد اكتمل العدد "), "إنتبه", JOptionPane.PLAIN_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, addStyle("برجاء ادخال عدد الشكاير"), "إنتبه", JOptionPane.PLAIN_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "exception", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_jTable_rep_selectMouseClicked

    private void jTable_storageKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable_storageKeyTyped
        if (evt.getKeyChar() == KeyEvent.VK_DELETE && jTable_storage.hasFocus()) {
            jButton_del_data.doClick();
        }
    }//GEN-LAST:event_jTable_storageKeyTyped

    private void jButton_DoBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_DoBackActionPerformed
        jFileChooser1.showSaveDialog(this);
        if (jFileChooser1.getSelectedFile() != null) {
            if (jFileChooser1.getSelectedFile().getName().endsWith(".bak")
                    || jFileChooser1.getSelectedFile().getName().endsWith(".tm")) {

                opj.backup(jFileChooser1.getSelectedFile().getAbsolutePath() + " " + LocalDate.now() + ".tm");
                JOptionPane.showMessageDialog(this, addStyle("Back up succes "), "3aaash", JOptionPane.INFORMATION_MESSAGE);
            } else {
                opj.backup(jFileChooser1.getSelectedFile().getAbsolutePath() + " " + LocalDate.now() + ".bak");
                JOptionPane.showMessageDialog(this, addStyle("Back up succes "), "3aaash", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }//GEN-LAST:event_jButton_DoBackActionPerformed

    private void jTextField_rep_clientNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_rep_clientNameKeyTyped
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            jButton_rep_printRep.doClick();
        }
    }//GEN-LAST:event_jTextField_rep_clientNameKeyTyped

    private void jTextField_rep_clientNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_rep_clientNameFocusGained
        jTextField_rep_clientName.selectAll();
    }//GEN-LAST:event_jTextField_rep_clientNameFocusGained

    private void jTextField_pallet_numFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_pallet_numFocusGained
        jTextField_pallet_num.selectAll();
    }//GEN-LAST:event_jTextField_pallet_numFocusGained

    private void jButton_Stock_openerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Stock_openerActionPerformed
        open_panel(stock);
        ((DefaultTableModel) jTable_stock.getModel()).setRowCount(0);
        combox_fill(jComboBox_stock_Pros, opj.dataRead("pro_name", "products"), true);
        jComboBox_stock_Pros.setSelectedIndex(-1);
    }//GEN-LAST:event_jButton_Stock_openerActionPerformed

    private void jComboBox_stock_ProsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_stock_ProsItemStateChanged
        if (jComboBox_stock_Pros.getSelectedIndex() != -1 && jComboBox_stock_Pros.hasFocus()) {
            DefaultTableModel model = (DefaultTableModel) jTable_stock.getModel();
            model.setRowCount(0);
            ResultSet st = opj.dataRead("lot ,COUNT( distinct pallet_numb)  ,count(weight_),SUM(weight_)", "storage", "pro_id=(select pro_id from products where pro_name=N'" + jComboBox_stock_Pros.getSelectedItem() + "')  group by lot");
            try {
                while (st.next()) {
                    model.addRow(new Object[]{ToDoubleArabic(st.getString(1)), ToDoubleArabic(st.getString(2)), ToDoubleArabic(st.getString(3)), ToDoubleArabic(st.getString(4))});
                }
            } catch (SQLException ex) {
                Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, ex.getMessage(), "exception", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }//GEN-LAST:event_jComboBox_stock_ProsItemStateChanged

    private void jTextField_num_of_conFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_num_of_conFocusGained
        jTextField_num_of_con.selectAll();
    }//GEN-LAST:event_jTextField_num_of_conFocusGained

    private void jButton_Statics_openerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Statics_openerActionPerformed
        open_panel(statistics);
        ((DefaultTableModel) jTable_statis.getModel()).setRowCount(0);
        jTable_statis.setAutoCreateRowSorter(true);
        jDateChooser_statis_fromDate.setCalendar(null);
        jDateChooser_statis_toDate.setCalendar(null);
    }//GEN-LAST:event_jButton_Statics_openerActionPerformed

    private void jButton_Outs_openerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Outs_openerActionPerformed
        open_panel(yomia);
        jDateChooser_yum_fromDate.setCalendar(null);
        jDateChooser_yum_ToDate.setCalendar(null);
        jTable_youm_clinets.setAutoCreateRowSorter(true);
        jTable_yumia.setAutoCreateRowSorter(true);
    }//GEN-LAST:event_jButton_Outs_openerActionPerformed

    private void jButton_youm_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_youm_searchActionPerformed
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
            String date1 = sdf.format(jDateChooser_yum_fromDate.getCalendar().getTime());
            String date2 = sdf.format(jDateChooser_yum_ToDate.getCalendar().getTime());
            ResultSet st = jCheckBox_youm_old.isSelected() ? opj.dataRead("sum(weight_),pro_name,cli_name,lot,FORMAT (exported_date, 'yyyy-MM-dd'),count(weight_)",
                    "export inner join clients on clients.cli_id=export.cli_id inner join products on products.pro_id=export.pro_id",
                    jTable_youm_clinets.getSelectedRowCount() > 0 ? "exported_date between '" + date1 + "' and '" + date2 + "' and export.cli_id in (" + selectedCIDs + ") "
                    + " group by products.pro_name,clients.cli_name,lot,exported_date order by exported_date ,cli_name "
                    : "exported_date between '" + date1 + "' and '" + date2 + "' "
                    + " group by products.pro_name,clients.cli_name,lot,exported_date order by exported_date ,cli_name ")
                    : opj.dataRead("ord_wight,pro_name,cli_name,lot,FORMAT (exported_date, 'yyyy-MM-dd'),count(weight_)",
                            "export inner join clients on clients.cli_id=export.cli_id inner join products on products.pro_id=export.pro_id inner join orders on orders.ord_id=export.ord_id",
                            jTable_youm_clinets.getSelectedRowCount() > 0 ? "exported_date between '" + date1 + "' and '" + date2 + "' and export.cli_id in (" + selectedCIDs + ") "
                            + " group by orders.ord_wight,products.pro_name,clients.cli_name,lot,exported_date order by exported_date ,cli_name "
                            : "exported_date between '" + date1 + "' and '" + date2 + "' "
                            + " group by orders.ord_wight,products.pro_name,clients.cli_name,lot,exported_date order by exported_date ,cli_name "
                    );

            try {
                while (st.next()) {
                    model.addRow(new Object[]{ToDoubleArabic(st.getString(3)), ToDoubleArabic(st.getString(2)), ToDoubleArabic(st.getString(4)), ToDoubleArabic(st.getString(6)), ToDoubleArabic(st.getString(1)), ToDoubleArabic(st.getString(5))});
                }
            } catch (SQLException ex) {
                Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, ex.getMessage(), "exception", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton_youm_searchActionPerformed

    private void jCheckBox_M_MarkpageKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCheckBox_M_MarkpageKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            if (!jTextField_net_weight.getText().isBlank()) {
                jButton_add_data.doClick();
            }
        }
    }//GEN-LAST:event_jCheckBox_M_MarkpageKeyPressed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            Object[] options1 = {"<html><body><h1  style='font-family: Arial; font-size: 20pt; text-align: center; width: 80px;'>  تسجيل الخروج </h1></body></html>", "<html><body><h1  style='font-family: Arial; font-size: 20pt; text-align: center; width: 80px;'>  قفل البرنامج </h1></body></html>"};
            int result = JOptionPane.showOptionDialog(this, addStyle(" خد بالك ياجدع  "), "انتبه ",
                    JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options1, null);
            if (result == JOptionPane.YES_OPTION) {
                saveTicknum("TicketNumber1.txt", tick1num);
                saveTicknum("TicketNumber2.txt", tick2num);
                login_form opj11 = new login_form();
                opj11.setVisible(true);
                this.dispose();
            }
            if (result == JOptionPane.NO_OPTION) {
                saveTicknum("TicketNumber1.txt", tick1num);
                saveTicknum("TicketNumber2.txt", tick2num);
                System.exit(NORMAL);
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage(), "exception", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_formWindowClosing

    private void jButton_youm_refundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_youm_refundActionPerformed
        if (jButton_Ezn_opener.isEnabled() && jButton_addPro_opener.isEnabled()) {
            if (jTable_yumia.getSelectedRow() != -1) {
                if (JOptionPane.showConfirmDialog(this, "هل تريد استرجاع أزن " + jTable_yumia.getValueAt(jTable_yumia.getSelectedRow(), 0) + " لصنف" + jTable_yumia.getValueAt(jTable_yumia.getSelectedRow(), 1) + " في يوم" + jTable_yumia.getValueAt(jTable_yumia.getSelectedRow(), 5) + " ", "تنبيه",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    try {
                        if (opj.dataRead("*", "export", "lot=N'" + ToStringEnglish("" + jTable_yumia.getValueAt(jTable_yumia.getSelectedRow(), 2)) + "' "
                                + "and pro_id=(select pro_id from products where pro_name=N'" + jTable_yumia.getValueAt(jTable_yumia.getSelectedRow(), 1) + "') "
                                + "and cli_id=(select top(1) cli_id from clients where cli_name=N'" + jTable_yumia.getValueAt(jTable_yumia.getSelectedRow(), 0) + "')"
                                + " and exported_date = '" + ToStringEnglish("" + jTable_yumia.getValueAt(jTable_yumia.getSelectedRow(), 5)) + "'"
                                + "and num_of_con is not null  and pallet_numb is not null         ").next()) {

                            ResultSet s = opj.dataRead("pro_id,weight_,lot,pallet_numb,FORMAT (inserted_date, 'yyyy-MM-dd'),num_of_con,used,tot_wight,ord_id", "export", "lot=N'" + ToStringEnglish("" + jTable_yumia.getValueAt(jTable_yumia.getSelectedRow(), 2)) + "' "
                                    + "and pro_id=(select pro_id from products where pro_name=N'" + jTable_yumia.getValueAt(jTable_yumia.getSelectedRow(), 1) + "') "
                                    + "and cli_id=(select top(1) cli_id from clients where cli_name=N'" + jTable_yumia.getValueAt(jTable_yumia.getSelectedRow(), 0) + "')"
                                    + " and exported_date = '" + ToStringEnglish("" + jTable_yumia.getValueAt(jTable_yumia.getSelectedRow(), 5)) + "'"
                                    + "and num_of_con is not null  and pallet_numb is not null         ");

                            ArrayList<String[]> outerArr = new ArrayList<>();
                            int ordTid = 0;
                            while (s.next()) {
                                String[] myString12 = {s.getString(1), s.getString(2), s.getString(3), s.getString(4), s.getString(5), s.getString(6), s.getString(7), s.getString(8)};
                                outerArr.add(myString12);
                                ordTid = s.getInt(9);
                            }
                            for (int i = outerArr.size() - 1; i > -1; i--) {
                                String[] myString;
                                myString = outerArr.get(i);
                                opj.inData("storage", "pro_id,weight_,lot,pallet_numb,date_,num_of_con,used,tot_wight", "" + myString[0] + "," + myString[1] + ",N'" + myString[2] + "'," + myString[3] + ",'" + myString[4] + "'," + myString[5] + "," + myString[6] + "," + myString[7] + " ");
                            }

                            if (jCheckBox_youm_old.isSelected()) {
                                opj.delData("export", "lot=N'" + ToStringEnglish("" + jTable_yumia.getValueAt(jTable_yumia.getSelectedRow(), 2)) + "' "
                                        + "and pro_id=(select pro_id from products where pro_name=N'" + jTable_yumia.getValueAt(jTable_yumia.getSelectedRow(), 1) + "') "
                                        + "and cli_id=(select top(1) cli_id from clients where cli_name=N'" + jTable_yumia.getValueAt(jTable_yumia.getSelectedRow(), 0) + "')"
                                        + " and exported_date = '" + ToStringEnglish("" + jTable_yumia.getValueAt(jTable_yumia.getSelectedRow(), 5)) + "'"
                                        + "and num_of_con is not null  and pallet_numb is not null         ");
                                if (!opj.dataRead("*", "export", "lot=N'" + ToStringEnglish("" + jTable_yumia.getValueAt(jTable_yumia.getSelectedRow(), 2)) + "' "
                                        + "and pro_id=(select pro_id from products where pro_name=N'" + jTable_yumia.getValueAt(jTable_yumia.getSelectedRow(), 1) + "') "
                                        + "and cli_id=(select top(1) cli_id from clients where cli_name=N'" + jTable_yumia.getValueAt(jTable_yumia.getSelectedRow(), 0) + "')"
                                        + " and exported_date = '" + ToStringEnglish("" + jTable_yumia.getValueAt(jTable_yumia.getSelectedRow(), 5)) + "'"
                                        + "and num_of_con is not null  and pallet_numb is not null         ").next()) {
                                    JOptionPane.showMessageDialog(this, addStyle(" تم استرجاع البيان بنجاح"), "إنتبه", JOptionPane.PLAIN_MESSAGE);
                                } else {
                                    JOptionPane.showMessageDialog(this, addStyle(" حدث خطأ ما"), "إنتبه", JOptionPane.PLAIN_MESSAGE);
                                }

                            } else {
                                opj.delData("export", "ord_id=" + ordTid);
                                opj.delData("orders", "ord_id=" + ordTid);

                                if (!opj.dataRead("*", "export", "ord_id=" + ordTid).next()) {
                                    JOptionPane.showMessageDialog(this, addStyle(" تم استرجاع البيان بنجاح"), "إنتبه", JOptionPane.PLAIN_MESSAGE);
                                } else {
                                    JOptionPane.showMessageDialog(this, addStyle("حدث خطأ ما "), "إنتبه", JOptionPane.PLAIN_MESSAGE);
                                }
                            }

                            jButton_youm_search.doClick();
                        } else {
                            JOptionPane.showMessageDialog(this, addStyle("لا يمكن استرجاع البيان "), "إنتبه", JOptionPane.PLAIN_MESSAGE);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(this, ex.getMessage(), "exception", JOptionPane.PLAIN_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, addStyle(" يجب اختيار بيان من الجدول "), "إنتبه", JOptionPane.PLAIN_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, addStyle("لا يمكن اجراء العمليه "), "إنتبه", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_jButton_youm_refundActionPerformed

    private void SingleEditWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_SingleEditWindowClosing
        this.setEnabled(true);
    }//GEN-LAST:event_SingleEditWindowClosing

    private void jButton_E_EditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_E_EditActionPerformed
        if (!jTextField_E_Weight.getText().isBlank() && !jTextField_E_PaltNum.getText().isBlank() && !jTextField_E_ConNum.getText().isBlank() && !jTextField_E_lot.getText().isBlank()) {
            ResultSet st = opj.dataRead("count(*)", "storage", "lot=N'" + ToStringEnglish(jTextField_E_lot.getText()) + "' and pallet_numb=" + ToStringEnglish(jTextField_E_PaltNum.getText()) + " and pro_id=(select pro_id from products where pro_name=N'" + jComboBox_E_proName.getSelectedItem().toString() + "') ");
            int num_of_shikra1 = 0;
            try {
                while (st.next()) {
                    num_of_shikra1 = st.getInt(1);
                }
            } catch (SQLException ex) {
                Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, ex.getMessage(), "exception", JOptionPane.PLAIN_MESSAGE);
            }
            if (num_of_shikra1 < 20) {

                opj.update("storage",
                        "weight_= " + ToStringEnglish(jTextField_E_Weight.getText()) + ","
                        + "tot_wight= " + ToStringEnglish(jTextField_E_TotWight.getText()) + ","
                        + "num_of_con= " + ToStringEnglish(jTextField_E_ConNum.getText()) + " ,"
                        + " lot= N'" + ToStringEnglish(jTextField_E_lot.getText()) + "',"
                        + "pallet_numb=" + ToStringEnglish(jTextField_E_PaltNum.getText()) + ","
                        + " pro_id=(select pro_id from products where pro_name=N'" + jComboBox_E_proName.getSelectedItem().toString() + "'),"
                        + "used=" + (jCheckBox_E_Mark.isSelected() ? 1 : 0) + " ",
                        "storage_id=" + jTable_storage.getModel().getValueAt(jTable_storage.getSelectedRow(), 4).toString() + "");
                this.setEnabled(true);
                fill_storage_table();
                SingleEdit.dispose();
                JOptionPane.showMessageDialog(this, addStyle(" تم تعديل البيانات بنجاح  "), "إنتبه", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, addStyle("هذه البالته ممتلئه "), "إنتبه", JOptionPane.PLAIN_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, addStyle("برجاء ادخال البيانات صحيحه "), "إنتبه", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_jButton_E_EditActionPerformed

    private void jTextField_E_WeightKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_E_WeightKeyTyped
        textbox_number_weight(evt, jTextField_E_Weight, 999);
    }//GEN-LAST:event_jTextField_E_WeightKeyTyped

    private void jTextField_E_PaltNumKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_E_PaltNumKeyTyped
        textbox_number(evt, jTextField_E_PaltNum, 999);
    }//GEN-LAST:event_jTextField_E_PaltNumKeyTyped

    private void jTextField_E_ConNumKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_E_ConNumKeyTyped
        textbox_number(evt, jTextField_E_ConNum, 999);
    }//GEN-LAST:event_jTextField_E_ConNumKeyTyped

    private void jTextField_E_lotKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_E_lotKeyTyped
        textbox_length_limiter(evt, jTextField_E_lot, 5);
        char input = evt.getKeyChar();
        if (Character.isDigit(input)) {
            evt.setKeyChar(ToNumArab(input));
        }
        if ((!Character.isDigit(input)) && input != 'أ' && input != 'س' && input != 'و' && input != 'د' && input != 'ط' && input != 'ب' && input != 'ا' && input != 'ع' && input != 'ه' && input != 'م' && input != 'ن' && input != 'ي' && input != 'ض') {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField_E_lotKeyTyped

    private void jTextField_Pros_conWightKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_Pros_conWightKeyTyped
        evt.setKeyChar(ToNumArab(evt.getKeyChar()));
        textbox_number(evt, jTextField_Pros_conWight, 4);
    }//GEN-LAST:event_jTextField_Pros_conWightKeyTyped

    private void jButton_Emp_openerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Emp_openerActionPerformed
        open_panel(pause);
        jTextArea_emp.requestFocusInWindow();
        jTextArea_emp.setText("");
    }//GEN-LAST:event_jButton_Emp_openerActionPerformed

    private void jTextArea_empKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea_empKeyTyped
        evt.setKeyChar(ToNumArab(evt.getKeyChar()));
    }//GEN-LAST:event_jTextArea_empKeyTyped

    private void jTable_storageMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_storageMouseReleased
        if (evt.getClickCount() == 3 && jTable_storage.getSelectedRowCount() > 0) {
            if (jTable_storage.getSelectedRowCount() == 1) {
                SingleEdit.setVisible(true);
                SingleEdit.setSize(780, 400);
                this.setEnabled(false);
                try {
                    jCheckBox_E_O_Mark.setSelected(opj.dataRead("*", "storage", " used=1 and  storage_id=" + jTable_storage.getModel().getValueAt(jTable_storage.getSelectedRow(), 4).toString() + " ").next());
                    jCheckBox_E_Mark.setSelected(opj.dataRead("*", "storage", " used=1 and  storage_id=" + jTable_storage.getModel().getValueAt(jTable_storage.getSelectedRow(), 4).toString() + " ").next());
                    ResultSet st = opj.dataRead("tot_wight", "storage", "storage_id=" + jTable_storage.getModel().getValueAt(jTable_storage.getSelectedRow(), 4).toString() + " ");
                    st.next();
                    jTextField_E_O_TotWight.setText(ToDoubleArabic(st.getString(1)));
                    jTextField_E_TotWight.setText(ToDoubleArabic(st.getString(1)));
                } catch (SQLException ex) {
                    Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "exception", JOptionPane.PLAIN_MESSAGE);
                }
                jComboBox_E_O_proName.setSelectedItem(jComboBox_pro_in_storage.getSelectedItem());
                jComboBox_E_proName.setSelectedItem(jComboBox_pro_in_storage.getSelectedItem());
                jTextField_E_O_lot.setText(jTable_storage.getModel().getValueAt(jTable_storage.getSelectedRow(), 2).toString());
                jTextField_E_O_ConNum.setText(jTable_storage.getModel().getValueAt(jTable_storage.getSelectedRow(), 1).toString());
                jTextField_E_lot.setText(jTable_storage.getModel().getValueAt(jTable_storage.getSelectedRow(), 2).toString());
                jTextField_E_O_PaltNum.setText(jTable_storage.getModel().getValueAt(jTable_storage.getSelectedRow(), 3).toString());
                jTextField_E_O_Wight.setText(jTable_storage.getModel().getValueAt(jTable_storage.getSelectedRow(), 0).toString());
                jTextField_E_ConNum.setText(jTable_storage.getModel().getValueAt(jTable_storage.getSelectedRow(), 1).toString());
                jTextField_E_PaltNum.setText(jTable_storage.getModel().getValueAt(jTable_storage.getSelectedRow(), 3).toString());
                jTextField_E_Weight.setText(jTable_storage.getModel().getValueAt(jTable_storage.getSelectedRow(), 0).toString());
                jTextField_E_Color.setText(jTextField_Color.getText());

            } else if (jTable_storage.getSelectedRowCount() > 1) {
                MultiEdit.setVisible(true);
                this.setEnabled(false);
                jComboBox_ME_type.setSelectedItem(jComboBox_pro_in_storage.getSelectedItem());
                jTextField_ME_PaltNum.setText(jTable_storage.getModel().getValueAt(jTable_storage.getSelectedRow(), 3).toString());
                jTextField_ME_lot.setText(jTable_storage.getModel().getValueAt(jTable_storage.getSelectedRow(), 2).toString());
                jCheckBox_ME_MarkBag.setSelected(jTable_storage.getModel().getValueAt(jTable_storage.getSelectedRow(), 6).toString().equals("0"));
            }
        }
    }//GEN-LAST:event_jTable_storageMouseReleased

    private void jButton_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_clearActionPerformed
        jTextField_bag_weight.setText("");
        jTextField_weight.setText("");
        jTextField_net_weight.setText("");
        jTextField_num_of_con.setText("");
        jTextField_num_of_con.requestFocus();
    }//GEN-LAST:event_jButton_clearActionPerformed

    private void jTextField_bag_weightFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_bag_weightFocusGained
        jTextField_bag_weight.selectAll();
    }//GEN-LAST:event_jTextField_bag_weightFocusGained

    private void jTextField_weightFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_weightFocusGained
        jTextField_weight.selectAll();
    }//GEN-LAST:event_jTextField_weightFocusGained

    private void jButton_E_printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_E_printActionPerformed
        try {
            // TODO add your handling code here:
            printex(new ArrayList<>(Arrays.asList(
                    jTextField_E_PaltNum.getText(),
                    jTextField_E_Color.getText(),
                    jComboBox_E_proName.getSelectedItem().toString(),
                    jTextField_E_lot.getText(),
                    jTextField_E_ConNum.getText(),
                    jTextField_E_TotWight.getText(), jTextField_E_Weight.getText())),
                    jCheckBox_E_P.isSelected(), jCheckBox_E_QR.isSelected());
        } catch (WriterException | IOException | PrinterException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "exception", JOptionPane.PLAIN_MESSAGE);
        } catch (InterruptedException ex) {
            Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_E_printActionPerformed

    private void jTextField_ColorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_ColorKeyTyped
        // TODO add your handling code here:
        if (Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField_ColorKeyTyped

    private void jTextField_E_TotWightKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_E_TotWightKeyTyped
        // TODO add your handling code here:
        textbox_number_weight(evt, jTextField_E_TotWight, 999);
    }//GEN-LAST:event_jTextField_E_TotWightKeyTyped

    private void jButton_set_Rest2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_set_Rest2ActionPerformed
        try {
            // TODO add your handling code here:
            tick2num = 0;
            saveTicknum("TicketNumber2.txt", tick2num);
            tick2num = loadTicknum("TicketNumber2.txt");
            jLabel_set_tick2.setText("" + tick2num);
        } catch (IOException ex) {
            Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton_set_Rest2ActionPerformed

    private void jButton_set_Rest1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_set_Rest1ActionPerformed
        try {
            // TODO add your handling code here:
            tick1num = 0;
            saveTicknum("TicketNumber1.txt", tick1num);
            tick1num = loadTicknum("TicketNumber1.txt");
            jLabel_set_tick1.setText("" + tick1num);
        } catch (IOException ex) {
            Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_set_Rest1ActionPerformed

    private void jButton_set_changeareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_set_changeareaActionPerformed
        // TODO add your handling code here:
        Xx = Float.parseFloat(ToStringEnglish(jTextField_set_x.getText()));
        jTextField_set_x.setText("" + Xx);

        Yy = Float.parseFloat(ToStringEnglish(jTextField_set_y.getText()));
        jTextField_set_y.setText("" + Yy);

        width = Float.parseFloat(ToStringEnglish(jTextField_set_width.getText()));
        jTextField_set_width.setText("" + width);

        hight = Float.parseFloat(ToStringEnglish(jTextField_set_hight.getText()));
        jTextField_set_hight.setText("" + hight);
    }//GEN-LAST:event_jButton_set_changeareaActionPerformed

    private void jTextField_set_xKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_set_xKeyTyped
        // TODO add your handling code here:
        textbox_number_weight(evt, jTextField_set_x, 1);
    }//GEN-LAST:event_jTextField_set_xKeyTyped

    private void jTextField_set_yKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_set_yKeyTyped
        // TODO add your handling code here:
        textbox_number_weight(evt, jTextField_set_y, 1);
    }//GEN-LAST:event_jTextField_set_yKeyTyped

    private void jTextField_set_widthKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_set_widthKeyTyped
        // TODO add your handling code here:
        textbox_number_weight(evt, jTextField_set_width, 2);
    }//GEN-LAST:event_jTextField_set_widthKeyTyped

    private void jTextField_set_hightKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_set_hightKeyTyped
        // TODO add your handling code here:
        textbox_number_weight(evt, jTextField_set_hight, 2);
    }//GEN-LAST:event_jTextField_set_hightKeyTyped

    private void jTextField_ME_lotKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_ME_lotKeyTyped
        // TODO add your handling code here:
        textbox_length_limiter(evt, jTextField_ME_lot, 5);
        char input = evt.getKeyChar();
        if (Character.isDigit(input)) {
            evt.setKeyChar(ToNumArab(input));
        }
        if ((!Character.isDigit(input)) && input != 'أ' && input != 'س' && input != 'و' && input != 'د' && input != 'ط' && input != 'ب' && input != 'ا' && input != 'ع' && input != 'ه' && input != 'م' && input != 'ن' && input != 'ي' && input != 'ض') {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField_ME_lotKeyTyped

    private void jTextField_ME_PaltNumKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_ME_PaltNumKeyTyped
        // TODO add your handling code here:
        textbox_number(evt, jTextField_E_PaltNum, 999);
    }//GEN-LAST:event_jTextField_ME_PaltNumKeyTyped

    private void jButton_ME_EditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ME_EditActionPerformed
        // TODO add your handling code here:
        if (!jTextField_ME_PaltNum.getText().isBlank() || !jTextField_ME_lot.getText().isBlank()) {
            for (int i = jTable_storage.getSelectedRows().length - 1; i > -1; i--) {
                ResultSet st = opj.dataRead("count(*)", "storage",
                        "lot=N'"
                        + ToStringEnglish(jTextField_ME_lot.getText())
                        + "' and pallet_numb="
                        + ToStringEnglish(jTextField_ME_PaltNum.getText())
                        + " and pro_id=(select pro_id from products where pro_name=N'" + jComboBox_ME_type.getSelectedItem().toString() + "') ");
                int num_of_shikra1 = 0;
                try {
                    while (st.next()) {
                        num_of_shikra1 = st.getInt(1);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "exception", JOptionPane.PLAIN_MESSAGE);
                }
                if (num_of_shikra1 < 20) {
                    opj.update("storage",
                            "pallet_numb=" + ToStringEnglish(jTextField_ME_PaltNum.getText())
                            + ",lot=N'" + ToStringEnglish(jTextField_ME_lot.getText()) + "'"
                            + ",used=" + (jCheckBox_ME_MarkBag.isSelected() ? 1 : 0) + " "
                            + ",pro_id=(select pro_id from products where pro_name=N'" + jComboBox_ME_type.getSelectedItem().toString() + "')",
                            "storage_id=" + jTable_storage.getModel().getValueAt(jTable_storage.getSelectedRows()[i], 4).toString() + ""
                    );
                } else {
                    JOptionPane.showMessageDialog(this, addStyle("هذه البالته ممتلئه "), "إنتبه", JOptionPane.PLAIN_MESSAGE);
                    break;
                }
            }
            MultiEdit.dispose();
            JOptionPane.showMessageDialog(this, addStyle(" تم تعديل البيانات بنجاح  "), "إنتبه", JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, addStyle("Please enter a valid number"), "إنتبه", JOptionPane.PLAIN_MESSAGE);
        }
        this.setEnabled(true);
        fill_storage_table();
        MultiEdit.dispose();
    }//GEN-LAST:event_jButton_ME_EditActionPerformed

    private void MultiEditWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_MultiEditWindowClosing
        // TODO add your handling code here:
        this.setEnabled(true);
    }//GEN-LAST:event_MultiEditWindowClosing

    private void jTextField_E_ColorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_E_ColorKeyTyped
        // TODO add your handling code here:
        if (Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField_E_ColorKeyTyped

    private void jButton_stock_createExclActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_stock_createExclActionPerformed
        // TODO add your handling code here:
        ResultSet st = opj.dataRead("pro_name, lot, COUNT(weight_), SUM(weight_)", "storage ,products", "storage.pro_id = products.pro_id group by lot, pro_name");
        int RowIndex = 4;
        try {
            Locale arabicLocale = Locale.forLanguageTag("ar");
            DateTimeFormatter arabicDateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(arabicLocale).withDecimalStyle(DecimalStyle.of(arabicLocale));
            LocalDate date_now = LocalDate.now();
            FileInputStream EX = new FileInputStream(new File("Donot_Change\\Stock.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(EX);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Cell cell = sheet.getRow(1).getCell(1);
            cell.setCellValue("التاريـــخ :   " + date_now.format(arabicDateFormatter) + "");
            String prevname = "";
            ArrayList<Integer> reg = new ArrayList<>();
            while (st.next()) {

                if (RowIndex >= 6) {
                    CellCopyPolicy poli = new CellCopyPolicy();
                    poli.setCopyCellStyle(true);
                    poli.setCopyCellValue(true);
                    sheet.copyRows(RowIndex - 1, RowIndex, RowIndex, poli);
                }
                if (prevname.equalsIgnoreCase(st.getString(1))) {
                    reg.add(RowIndex);
                } else {
                    if (reg.size() > 1) {
                        sheet.addMergedRegion(new CellRangeAddress(reg.get(0), reg.get(reg.size() - 1), 1, 1));
                    }
                    reg.clear();
                    reg.add(RowIndex);
                    prevname = st.getString(1);
                    cell = sheet.getRow(RowIndex).getCell(1);
                    cell.setCellValue(st.getString(1));
                }

                cell = sheet.getRow(RowIndex).getCell(2);
                cell.setCellValue(ToDoubleArabic(st.getString(2)));

                cell = sheet.getRow(RowIndex).getCell(3);
                cell.setCellValue(ToDoubleArabic(st.getString(3)));

                cell = sheet.getRow(RowIndex).getCell(4);
                cell.setCellValue(ToDoubleArabic(st.getString(4)));

                RowIndex++;
            }
            if (reg.size() > 1) {
                sheet.addMergedRegion(new CellRangeAddress(reg.get(0), reg.get(reg.size() - 1), 1, 1));
            }
            reg.clear();
            EX.close();
            FileOutputStream fileOut = new FileOutputStream(System.getProperty("user.dir") + "\\Temp\\Stock.xlsx");
            workbook.write(fileOut);
            fileOut.close();
            JOptionPane.showMessageDialog(this, addStyle("please print the Execl"), "Done", JOptionPane.PLAIN_MESSAGE);
        } catch (IOException | SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "exception", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_jButton_stock_createExclActionPerformed

    private void jCheckBox_BoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox_BoxItemStateChanged
        // TODO add your handling code here:
        BagMax = jCheckBox_Box.isSelected() ? 3 : 2;
        jLabel11.setText(!jCheckBox_Box.isSelected() ? "فارغ الشيكاره" : "فارغ الصندوق");
        jTextField_bag_weight.setBackground(jCheckBox_Box.isSelected() ? Color.pink : Color.WHITE);
    }//GEN-LAST:event_jCheckBox_BoxItemStateChanged

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            // TODO add your handling code here:
            Desktop.getDesktop().print(new File(System.getProperty("user.dir") + "\\Temp\\myFile.xlsx"));
            tick1num++;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "exception", JOptionPane.PLAIN_MESSAGE);
            Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton_youm_getClientsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_youm_getClientsActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable_youm_clinets.getModel();
        model.setRowCount(0);
        ResultSet st = opj.dataRead("*", "clients", "cli_name like '%" + jTextField_youm_ClientFilter.getText().strip() + "%' ");
        try {

            while (st.next()) {
                model.addRow(new Object[]{st.getString(1), st.getString(2)});
            }
        } catch (SQLException ex) {
            Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage(), "exception", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_jButton_youm_getClientsActionPerformed

    private void jTextField_youm_ClientFilterKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_youm_ClientFilterKeyTyped
        if (evt.getKeyChar() == '\'') {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField_youm_ClientFilterKeyTyped

    private void jButton_youm_createExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_youm_createExcelActionPerformed
        int RowIndex = 4;
        XSSFWorkbook workbook;
        try ( FileInputStream EX = new FileInputStream(new File("Donot_Change\\report.xlsx"))) {
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

            try ( FileOutputStream fileOut = new FileOutputStream(System.getProperty("user.dir") + "\\Temp\\report.xlsx")) {
                workbook.write(fileOut);
            }
            JOptionPane.showMessageDialog(this, addStyle("please print the Execl"), "Done", JOptionPane.PLAIN_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "exception", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_jButton_youm_createExcelActionPerformed

    private void jTable_yumiaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_yumiaMouseReleased
        // TODO add your handling code here:
        if (evt.getClickCount() == 3) {
            TableModel model = jTable_yumia.getModel();
            String temp = "";
            ResultSet st = opj.dataRead("distinct pallet_numb", "export inner join orders on orders.ord_id=export.ord_id", "pro_id = ( select pro_id from products where pro_name =N'"
                    + model.getValueAt(jTable_yumia.getSelectedRow(), 1).toString() + "') and"
                    + " cli_id IN ( select cli_id from clients where cli_name=N'" + model.getValueAt(jTable_yumia.getSelectedRow(), 0).toString() + "') and"
                    + " lot = N'" + ToStringEnglish(model.getValueAt(jTable_yumia.getSelectedRow(), 2).toString()) + "' and"
                    + " exported_date='" + ToStringEnglish(model.getValueAt(jTable_yumia.getSelectedRow(), 5).toString()) + "'and ord_wight ="
                    + ToDoubleEnglish(model.getValueAt(jTable_yumia.getSelectedRow(), 4).toString()));
            try {
                while (st.next()) {
                    temp += st.getString(1) + " , ";
                }
                JOptionPane.showMessageDialog(this, temp, "Pallets", JOptionPane.PLAIN_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "exception", JOptionPane.PLAIN_MESSAGE);
            }
        }

    }//GEN-LAST:event_jTable_yumiaMouseReleased

    private void jButton_reps_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_reps_clearActionPerformed
        // TODO add your handling code here:
        ((DefaultTableModel) jTable_rep_select.getModel()).setRowCount(0);
        ((DefaultTableModel) jTable_rep_preview.getModel()).setRowCount(0);
        jComboBox_rep_Pros.setSelectedIndex(-1);
        second = false;
        jTextField_rep_totweight.setText("");
        jComboBox_rep_palletsNrep.removeAllItems();
    }//GEN-LAST:event_jButton_reps_clearActionPerformed

    private void jTextField_setting_repsDiffKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_setting_repsDiffKeyTyped
        // TODO add your handling code here:
        textbox_number(evt, jTextField_setting_repsDiff, 2);
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            repDiff = Integer.parseInt(ToStringEnglish(jTextField_setting_repsDiff.getText()));
        }
    }//GEN-LAST:event_jTextField_setting_repsDiffKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        fill_jtable5();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton_statis_createExclActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_statis_createExclActionPerformed
        // TODO add your handling code here:
        if (jDateChooser_statis_fromDate.getCalendar() != null && jDateChooser_statis_toDate.getCalendar() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date1 = sdf.format(jDateChooser_statis_fromDate.getCalendar().getTime());
            String date2 = sdf.format(jDateChooser_statis_toDate.getCalendar().getTime());
            ResultSet st = opj.dataRead(
                    "(select pro_name from products where products.pro_id=s.pro_id) as TypeName, lot as Lot, sum(bags) as Bags, sum(total) as Total",
                    "( select pro_id, lot, count(weight_)as bags, sum(weight_) as total from storage where (date_ between '" + date1 + "'  and '" + date2
                    + "') group by lot, pro_id UNION ALL select pro_id, lot, count(weight_)as bags, sum(weight_) as total from export where (inserted_date between '" + date1 + "'  and '" + date2
                    + "') group by lot, pro_id )s group by s.lot, s.pro_id order by s.pro_id");
            int RowIndex = 4;
            try {
                FileInputStream EX = new FileInputStream(new File("Donot_Change\\Stock.xlsx"));
                XSSFWorkbook workbook = new XSSFWorkbook(EX);
                XSSFSheet sheet = workbook.getSheetAt(0);
                Cell cell = sheet.getRow(1).getCell(1);
                cell.setCellValue("التاريـــخ من :   " + ToDoubleArabic(date1) + "  إلى : " + ToDoubleArabic(date2));
                double prevTot = 0.0;
                String prevName = "";
                ArrayList<Integer> reg = new ArrayList<>();
                while (st.next()) {

                    if (RowIndex >= 6) {
                        CellCopyPolicy poli = new CellCopyPolicy();
                        poli.setCopyCellStyle(true);
                        poli.setCopyCellValue(true);
                        sheet.copyRows(RowIndex - 1, RowIndex, RowIndex, poli);
                    }
                    if (prevName.equalsIgnoreCase(st.getString(1))) {
                        reg.add(RowIndex);
                        prevTot += Double.parseDouble(st.getString(4));
                    } else {
                        if (reg.size() > 1) {
                            sheet.addMergedRegion(new CellRangeAddress(reg.get(0), reg.get(reg.size() - 1), 1, 1));
                            sheet.addMergedRegion(new CellRangeAddress(reg.get(0), reg.get(reg.size() - 1), 5, 5));
                            cell = sheet.getRow(reg.get(0)).getCell(5);
                            cell.setCellValue(ToDoubleArabic(new DecimalFormat("#.###").format(prevTot)));
                        }
                        reg.clear();
                        reg.add(RowIndex);
                        prevName = st.getString(1);

                        cell = sheet.getRow(RowIndex).getCell(1);
                        cell.setCellValue(st.getString(1));

                        prevTot = Double.parseDouble(st.getString(4));
                    }

                    cell = sheet.getRow(RowIndex).getCell(2);
                    cell.setCellValue(ToDoubleArabic(st.getString(2)));

                    cell = sheet.getRow(RowIndex).getCell(3);
                    cell.setCellValue(ToDoubleArabic(st.getString(3)));

                    cell = sheet.getRow(RowIndex).getCell(4);
                    cell.setCellValue(ToDoubleArabic(st.getString(4)));

                    RowIndex++;
                }
                if (reg.size() > 1) {
                    sheet.addMergedRegion(new CellRangeAddress(reg.get(0), reg.get(reg.size() - 1), 1, 1));
                    sheet.addMergedRegion(new CellRangeAddress(reg.get(0), reg.get(reg.size() - 1), 5, 5));
                }
                reg.clear();
                EX.close();
                FileOutputStream fileOut = new FileOutputStream(System.getProperty("user.dir") + "\\Temp\\Statistics.xlsx");
                workbook.write(fileOut);
                fileOut.close();
                JOptionPane.showMessageDialog(this, addStyle("please print the Execl"), "Done", JOptionPane.PLAIN_MESSAGE);
            } catch (IOException | SQLException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "exception", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton_statis_createExclActionPerformed

    private void jCheckBox_rep_wznActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_rep_wznActionPerformed
        // TODO add your handling code here:
        if (jComboBox_rep_Pros.getSelectedIndex() != -1) {
            ((DefaultTableModel) jTable_rep_select.getModel()).setRowCount(0);
            ResultSet st = opj.dataRead("count(*),sum(weight_),lot,pallet_numb,used", "storage", "pro_id=(select pro_id from products where pro_name=N'" + jComboBox_rep_Pros.getSelectedItem().toString() + "' )  GROUP BY lot,pallet_numb,used");
            try {
                while (st.next()) {
                    ((DefaultTableModel) jTable_rep_select.getModel()).addRow(new Object[]{ToDoubleArabic(st.getString(1)), ToDoubleArabic(st.getString(2)), ToDoubleArabic(st.getString(3)), ToDoubleArabic(st.getString(4)), ToDoubleArabic(st.getString(5))});
                }
            } catch (SQLException ex) {
                Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, ex.getMessage(), "exception", JOptionPane.PLAIN_MESSAGE);
            }

            ((DefaultTableModel) jTable_rep_preview.getModel()).setRowCount(0);
            serial = 0;
            order_ids.clear();
            jTextField_rep_totweight.setText("");
            jComboBox_rep_palletsNrep.removeAllItems();
        }
    }//GEN-LAST:event_jCheckBox_rep_wznActionPerformed

    private void jButton_bagmaxActionPerformed() throws IOException {
        open_panel(Settings);
        jTextField_set_x.setText("" + Xx);
        jTextField_set_y.setText("" + Yy);
        jTextField_set_width.setText("" + width);
        jTextField_set_hight.setText("" + hight);
        jTextField_setting_repsDiff.setText(ToDoubleArabic(repDiff + ""));
        saveTicknum("TicketNumber1.txt", tick1num);
        saveTicknum("TicketNumber2.txt", tick2num);
        tick1num = loadTicknum("TicketNumber1.txt");
        tick2num = loadTicknum("TicketNumber2.txt");
        jLabel_set_tick1.setText("" + tick1num);
        jLabel_set_tick2.setText("" + tick2num);
    }

    void open_panel(JPanel panel) {
        left_panel.removeAll();
        left_panel.add(panel);
        left_panel.revalidate();
        left_panel.repaint();
        this.setAlwaysOnTop(false);
    }

    void textbox_number_weight(KeyEvent event, JTextField textboxname, int length) {

        if (event.getKeyChar() == KeyEvent.VK_DELETE) {
            if ((event.getModifiersEx() & KeyEvent.SHIFT_DOWN_MASK) != 0) {
                jButton_clear.doClick();
            }
            textboxname.setText("");
        }
        if (textboxname.getText().length() > length - 1 && event.getKeyChar() != KeyEvent.VK_ENTER && event.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
            event.consume();
        } else {
            if ((!Character.isDigit(event.getKeyChar()) && (event.getKeyChar() == 'ز' || event.getKeyChar() == '.' || event.getKeyChar() == '٫')) && !(textboxname.getText().contains(".") || textboxname.getText().contains("٫"))) {
                event.setKeyChar(ToNumArab(event.getKeyChar()));
            } else {
                if (Character.isDigit(event.getKeyChar())) {
                    if ((textboxname.getText().contains(".") && (textboxname.getText().indexOf(".") == textboxname.getText().length() - 4)) || (textboxname.getText().contains("٫") && (textboxname.getText().indexOf("٫") == textboxname.getText().length() - 4))) {
                        event.consume();
                    } else {
                        event.setKeyChar(ToNumArab(event.getKeyChar()));
                    }
                } else {
                    event.consume();
                }
            }
        }
    }

    boolean printex(ArrayList<String> values, boolean b1, boolean b2) throws WriterException, IOException, PrinterException, InterruptedException {
        Files.createDirectories(Paths.get(System.getProperty("user.dir") + "\\Temp"));
        String n = values.get(2);
        while (n.length() < 20) {
            n += " ";
        }
        if (b2) {
            print_shit.generateQRcode("{\n \"الصنف\": " + n + " ,\n\"اللوط\": " + values.get(3) + " ,\n\"الوزن الصافي\": " + values.get(6) + "\n}", ToDoubleEnglish(values.get(6)) + "", 300, 300);
            BufferedImage image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Temp\\QR" + ToDoubleEnglish(values.get(6)) + ".png"));
            PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
            attributes.add(new PrinterResolution(300, 300, PrinterResolution.DPI));
            attributes.add(new MediaPrintableArea(Xx, Yy, width, hight, MediaPrintableArea.MM));

            PrinterJob job = PrinterJob.getPrinterJob();
            job.setJobName("Print QR");
            for (PrintService printer : PrintServiceLookup.lookupPrintServices(null, null)) {
                if (printer.getName().contains("Xprinter")) {
                    job.setPrintService(printer);
                }
            }

            job.setPrintable((Graphics pg, PageFormat pf, int pageNum) -> {
                if (pageNum != 0) {
                    return Printable.NO_SUCH_PAGE;
                }
                pg.drawImage(image, 0, 0, (int) pf.getImageableWidth(), (int) pf.getImageableHeight(), null);
                Graphics2D g2 = (Graphics2D) pg;
                g2.translate((int) pf.getImageableX(), (int) pf.getImageableY());
                return Printable.PAGE_EXISTS;
            });
            job.print(attributes);
            tick2num++;
            new File(System.getProperty("user.dir") + "\\Temp\\QR" + ToDoubleEnglish(values.get(6)) + ".png").delete();
        }
        if (b1) {
            XSSFWorkbook workbook;
            try ( FileInputStream EX = new FileInputStream(new File("Donot_Change\\Ticket.xlsx"))) {
                workbook = new XSSFWorkbook(EX);
                EX.close();
            }
            XSSFSheet sheet = workbook.getSheetAt(0);

            Cell cell = sheet.getRow(0).getCell(0);
            cell.setCellValue(values.get(0));

            cell = sheet.getRow(1).getCell(0);
            cell.setCellValue(values.get(1));

            cell = sheet.getRow(2).getCell(0);
            cell.setCellValue(values.get(2));

            cell = sheet.getRow(3).getCell(0);
            cell.setCellValue(values.get(3));

            cell = sheet.getRow(4).getCell(0);
            cell.setCellValue(values.get(4));

            cell = sheet.getRow(5).getCell(0);
            cell.setCellValue(values.get(5));

            cell = sheet.getRow(6).getCell(0);
            cell.setCellValue(values.get(6));

            try ( FileOutputStream fileOut = new FileOutputStream(System.getProperty("user.dir") + "\\Temp\\myFile.xlsx")) {
                workbook.write(fileOut);
                fileOut.close();
            }
            Desktop.getDesktop().print(new File(System.getProperty("user.dir") + "\\Temp\\myFile.xlsx"));
            tick1num++;
        }

        return b1 || b2;
    }

    void textbox_length_limiter(KeyEvent event, JTextField textboxname, int length) {
        if (textboxname.getText().length() > length - 1 && event.getKeyChar() != KeyEvent.VK_ENTER && event.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
            event.consume();
        }
        if (event.getKeyChar() == KeyEvent.VK_DELETE) {
            if ((event.getModifiersEx() & KeyEvent.SHIFT_DOWN_MASK) != 0) {
                jButton_clear.doClick();
            }
            textboxname.setText("");
        }
    }

    void textbox_number(KeyEvent event, JTextField textboxname, int length) {

        if (event.getKeyChar() == KeyEvent.VK_DELETE) {
            if ((event.getModifiersEx() & KeyEvent.SHIFT_DOWN_MASK) != 0) {
                jButton_clear.doClick();
            }
            textboxname.setText("");
        }
        if (textboxname.getText().length() > length - 1 && event.getKeyChar() != KeyEvent.VK_ENTER && event.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
            event.consume();
        } else {
            if (!Character.isDigit(event.getKeyChar())) {
                event.consume();
            } else {
                event.setKeyChar(ToNumArab(event.getKeyChar()));
            }
        }
    }

    static char ToNumArab(char eng) {
        return switch (eng) {
            case '0' ->
                '٠';
            case '1' ->
                '١';
            case '2' ->
                '٢';
            case '3' ->
                '٣';
            case '4' ->
                '٤';
            case '5' ->
                '٥';
            case '6' ->
                '٦';
            case '7' ->
                '٧';
            case '8' ->
                '٨';
            case '9' ->
                '٩';
            case '.' ->
                '٫';
            case 'ز' ->
                '٫';
            default ->
                eng;
        };
    }

    static char ToNumEng(char ArabC) {
        return switch (ArabC) {
            case '٠' ->
                '0';
            case '١' ->
                '1';
            case '٢' ->
                '2';
            case '٣' ->
                '3';
            case '٤' ->
                '4';
            case '٥' ->
                '5';
            case '٦' ->
                '6';
            case '٧' ->
                '7';
            case '٨' ->
                '8';
            case '٩' ->
                '9';
            case '٫' ->
                '.';
            default ->
                ArabC;
        };
    }

    static double ToDoubleEnglish(String ArabNum) {
        String eng = "";
        for (char c : ArabNum.toCharArray()) {
            eng += ToNumEng(c);
        }
        return Double.parseDouble(eng);
    }

    static String ToStringEnglish(String ArabNum) {
        String eng = "";
        for (char c : ArabNum.toCharArray()) {
            eng += ToNumEng(c);
        }
        return eng;
    }

    static String ToDoubleArabic(String EnglishNum) {
        String Arab = "";
        for (char c : EnglishNum.toCharArray()) {
            Arab += ToNumArab(c);
        }
        return Arab;
    }

    @SuppressWarnings("unchecked")
    private void combox_fill(JComboBox comboxname, ResultSet st, boolean new_or_add) {
        if (new_or_add) {
            comboxname.removeAllItems();
        }
        try {
            while (st.next()) {
                comboxname.addItem(st.getString(1));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "exception", JOptionPane.PLAIN_MESSAGE);
        }
        comboxname.setSelectedIndex(-1);
    }

    void fill_pro_table() {
        DefaultTableModel model = (DefaultTableModel) jTable_pro.getModel();
        model.setRowCount(0);
        ResultSet st = opj.dataRead("*", "products");
        try {
            while (st.next()) {
                model.addRow(new Object[]{st.getString(1), st.getString(2), ToDoubleArabic(st.getString(3)), st.getString(4)});

            }
        } catch (SQLException ex) {
            Logger.getLogger(mainform.class
                    .getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage(), "exception", JOptionPane.PLAIN_MESSAGE);
        }
    }

    void calc_net_weight() {
        if (!jTextField_num_of_con.getText().isBlank() && !jTextField_weight_of_con.getText().isBlank() && !jTextField_bag_weight.getText().isBlank() && ((jTextField_weight.getText().contains("٫") && jTextField_weight.getText().length() >= 2) || (!jTextField_weight.getText().contains("٫") && !jTextField_weight.getText().isBlank()))) {
            DecimalFormat df2 = new DecimalFormat("#.##");
            df2.setRoundingMode(RoundingMode.UP);
            double num_of_con = ToDoubleEnglish(jTextField_num_of_con.getText()), weight_of_con = ToDoubleEnglish(jTextField_weight_of_con.getText()) / 1000, bag_weight = ToDoubleEnglish(jTextField_bag_weight.getText()) / 100, weight = ToDoubleEnglish(jTextField_weight.getText());
            jTextField_net_weight.setText(ToDoubleArabic(df2.format(weight - (bag_weight + (num_of_con * weight_of_con)))));
        }
    }

    void fill_storage_table() {
        DefaultTableModel model = (DefaultTableModel) jTable_storage.getModel();
        model.setRowCount(0);
        ResultSet st = opj.dataRead("weight_,num_of_con,lot,pallet_numb,storage_id,used, (CASE WHEN ISNUMERIC(lot) = 1 THEN 0 ELSE 1 END) IsNum", "storage ",
                "storage.pro_id=(select pro_id from products where pro_name=N'" + jComboBox_pro_in_storage.getSelectedItem() + "') order by IsNum,lot DESC, pallet_numb DESC, storage_id DESC, used ");
        try {
            while (st.next()) {
                model.addRow(new Object[]{ToDoubleArabic(st.getString(1)), ToDoubleArabic(st.getString(2)), ToDoubleArabic(st.getString(3)), ToDoubleArabic(st.getString(4)), st.getString(5), "", st.getString(6)});
            }
            st = opj.dataRead("weight_of_con", "products", "pro_name=N'" + jComboBox_pro_in_storage.getSelectedItem() + "' ");
            while (st.next()) {
                jTextField_weight_of_con.setText(ToDoubleArabic(st.getString(1)));
            }
            st = opj.dataRead("Color", "products", "pro_name=N'" + jComboBox_pro_in_storage.getSelectedItem() + "' ");
            while (st.next()) {
                jTextField_Color.setText(st.getString(1));

            }
        } catch (SQLException ex) {
            Logger.getLogger(mainform.class
                    .getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage(), "exception", JOptionPane.PLAIN_MESSAGE);
        }
        if (model.getRowCount() != 0) {
            String lott = ToStringEnglish(model.getValueAt(model.getRowCount() - 1, 2).toString()), pallet_num = ToStringEnglish(model.getValueAt(model.getRowCount() - 1, 3).toString());
            int cunt = 0;
            for (int i = model.getRowCount() - 1; i >= 0; i--) {
                if (!lott.equals(ToStringEnglish(model.getValueAt(i, 2).toString())) || !pallet_num.equals(ToStringEnglish(model.getValueAt(i, 3).toString()))) {
                    lott = ToStringEnglish(model.getValueAt(i, 2).toString());
                    pallet_num = ToStringEnglish(model.getValueAt(i, 3).toString());
                    cunt = 0;
                }

                model.setValueAt(ToDoubleArabic(++cunt + ""), i, 5);
            }
        }
    }

    void calc_pallet_weight() {
        if (!jTextField_pallet_num.getText().isBlank() && !jTextField_lot.getText().isBlank() && jComboBox_pro_in_storage.getSelectedIndex() != -1) {
            ResultSet st = opj.dataRead("sum(weight_)", "storage", "lot=N'" + ToStringEnglish(jTextField_lot.getText()) + "' and pallet_numb=" + ToDoubleEnglish(jTextField_pallet_num.getText()) + " and pro_id=(select pro_id from products where pro_name=N'" + jComboBox_pro_in_storage.getSelectedItem() + "')");
            try {
                while (st.next()) {
                    if (st.getString(1) != null) {
                        pallet_weight.setText(ToDoubleArabic(st.getString(1)));

                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(mainform.class
                        .getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, ex.getMessage(), "exception", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    void fill_jtable5() {
        if (jDateChooser_statis_fromDate.getCalendar() != null && jDateChooser_statis_toDate.getCalendar() != null) {
            DefaultTableModel model = (DefaultTableModel) jTable_statis.getModel();
            model.setRowCount(0);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date1 = sdf.format(jDateChooser_statis_fromDate.getCalendar().getTime());
            String date2 = sdf.format(jDateChooser_statis_toDate.getCalendar().getTime());
            ResultSet st = opj.dataRead(
                    "(select pro_name from products where products.pro_id=s.pro_id) as TypeName, lot as Lot, sum(bags) as Bags, sum(total) as Total",
                    "( select pro_id, lot, count(weight_)as bags, sum(weight_) as total from storage where (date_ between '" + date1 + "'  and '" + date2
                    + "') group by lot, pro_id UNION ALL select pro_id, lot, count(weight_)as bags, sum(weight_) as total from export where (inserted_date between '" + date1 + "'  and '" + date2
                    + "') group by lot, pro_id )s group by s.lot, s.pro_id order by s.pro_id");
            try {
                while (st.next()) {
                    model.addRow(new Object[]{st.getString(1), ToDoubleArabic(st.getString(2)), ToDoubleArabic(st.getString(3)), ToDoubleArabic(st.getString(4))});

                }
                double tot = 0.0;
                for (int i = 0; i < model.getRowCount(); i++) {
                    tot += ToDoubleEnglish(model.getValueAt(i, 3).toString());
                }
                jTextField_statis_tot.setText(ToDoubleArabic(new DecimalFormat("#.##").format(tot) + ""));
            } catch (SQLException ex) {
                Logger.getLogger(mainform.class
                        .getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, ex.getMessage(), "exception", JOptionPane.PLAIN_MESSAGE);
            }

        }
    }

    private short loadTicknum(String filename) throws FileNotFoundException, IOException {
        String num = "";
        int i;
        Files.createDirectories(Paths.get(System.getProperty("user.dir") + "\\Temp"));
        try ( FileReader fr = new FileReader(System.getProperty("user.dir") + "\\Temp\\" + filename)) {
            num = "";
            while ((i = fr.read()) != -1) {
                num += Character.digit(i, 10) + "";
            }
        } catch (Exception e) {
            print_shit.NewName(System.getProperty("user.dir") + filename);
        }
        return Short.parseShort((num.isBlank() ? "0" : num));
    }

    int saveTicknum(String filename, short nums) throws FileNotFoundException, IOException {
        try ( FileWriter fw = new FileWriter(System.getProperty("user.dir") + "\\Temp\\" + filename)) {
            fw.write(nums + "");
        }
        return nums;
    }

    String addStyle(String text) {
        return "<html><body><h1 style='font-family: Arial; font-size: 20pt; text-align: right; width: 150px;'>" + text.strip() + "</h1></body></html>";
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame MultiEdit;
    private javax.swing.JPanel Settings;
    private javax.swing.JFrame SingleEdit;
    private javax.swing.JPanel add_product;
    private javax.swing.JPanel in_data;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton_DoBack;
    private javax.swing.JButton jButton_E_Edit;
    private javax.swing.JButton jButton_E_print;
    private javax.swing.JButton jButton_Emp_opener;
    private javax.swing.JButton jButton_Ezn_opener;
    private javax.swing.JButton jButton_ME_Edit;
    private javax.swing.JButton jButton_Mizan_opener;
    private javax.swing.JButton jButton_Outs_opener;
    private javax.swing.JButton jButton_Statics_opener;
    private javax.swing.JButton jButton_Stock_opener;
    private javax.swing.JButton jButton_addPro_opener;
    private javax.swing.JButton jButton_add_data;
    private javax.swing.JButton jButton_add_pro;
    private javax.swing.JButton jButton_clear;
    private javax.swing.JButton jButton_del_data;
    private javax.swing.JButton jButton_del_pro;
    private javax.swing.JButton jButton_rep_printRep;
    private javax.swing.JButton jButton_reps_clear;
    private javax.swing.JButton jButton_set_Rest1;
    private javax.swing.JButton jButton_set_Rest2;
    private javax.swing.JButton jButton_set_changearea;
    private javax.swing.JButton jButton_statis_createExcl;
    private javax.swing.JButton jButton_stock_createExcl;
    private javax.swing.JButton jButton_youm_createExcel;
    private javax.swing.JButton jButton_youm_getClients;
    private javax.swing.JButton jButton_youm_refund;
    private javax.swing.JButton jButton_youm_search;
    private javax.swing.JCheckBox jCheckBox_Box;
    private javax.swing.JCheckBox jCheckBox_E_Mark;
    private javax.swing.JCheckBox jCheckBox_E_O_Mark;
    private javax.swing.JCheckBox jCheckBox_E_P;
    private javax.swing.JCheckBox jCheckBox_E_QR;
    private javax.swing.JCheckBox jCheckBox_ME_MarkBag;
    private javax.swing.JCheckBox jCheckBox_M_Markpage;
    private javax.swing.JCheckBox jCheckBox_QR;
    private javax.swing.JCheckBox jCheckBox_ignore_limits;
    private javax.swing.JCheckBox jCheckBox_print;
    private javax.swing.JCheckBox jCheckBox_rep_2n1;
    private javax.swing.JCheckBox jCheckBox_rep_wzn;
    private javax.swing.JCheckBox jCheckBox_rev_order;
    private javax.swing.JCheckBox jCheckBox_youm_old;
    private javax.swing.JComboBox<String> jComboBox_E_O_proName;
    private javax.swing.JComboBox<String> jComboBox_E_proName;
    private javax.swing.JComboBox<String> jComboBox_ME_type;
    private javax.swing.JComboBox<String> jComboBox_pro_in_storage;
    private javax.swing.JComboBox<String> jComboBox_rep_Pros;
    private javax.swing.JComboBox<String> jComboBox_rep_palletsNrep;
    private javax.swing.JComboBox<String> jComboBox_stock_Pros;
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
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_set_hight;
    private javax.swing.JLabel jLabel_set_tick1;
    private javax.swing.JLabel jLabel_set_tick2;
    private javax.swing.JLabel jLabel_set_width;
    private javax.swing.JLabel jLabel_set_x;
    private javax.swing.JLabel jLabel_set_y;
    private javax.swing.JLabel jLabel_version;
    private javax.swing.JProgressBar jProgressBar_pallet;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSplitPane jSplitPane1;
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
    private javax.swing.JTextField jTextField_E_Weight;
    private javax.swing.JTextField jTextField_E_lot;
    private javax.swing.JTextField jTextField_ME_PaltNum;
    private javax.swing.JTextField jTextField_ME_lot;
    private javax.swing.JTextField jTextField_Pros_color;
    private javax.swing.JTextField jTextField_Pros_conWight;
    private javax.swing.JTextField jTextField_bag_weight;
    private javax.swing.JTextField jTextField_lot;
    private javax.swing.JTextField jTextField_net_weight;
    private javax.swing.JTextField jTextField_num_of_con;
    private javax.swing.JTextField jTextField_pallet_num;
    private javax.swing.JTextField jTextField_pro_name;
    private javax.swing.JTextField jTextField_rep_clientName;
    private javax.swing.JTextField jTextField_rep_numOfBag;
    private javax.swing.JTextField jTextField_rep_totweight;
    private javax.swing.JTextField jTextField_set_hight;
    private javax.swing.JTextField jTextField_set_width;
    private javax.swing.JTextField jTextField_set_x;
    private javax.swing.JTextField jTextField_set_y;
    private javax.swing.JTextField jTextField_setting_repsDiff;
    private javax.swing.JTextField jTextField_statis_tot;
    private javax.swing.JTextField jTextField_weight;
    private javax.swing.JTextField jTextField_weight_of_con;
    private javax.swing.JTextField jTextField_youm_ClientFilter;
    private javax.swing.JPanel left_panel;
    private javax.swing.JTextField pallet_weight;
    private javax.swing.JPanel pause;
    private javax.swing.JPanel reports;
    private javax.swing.JPanel right_panel_menu;
    private javax.swing.JPanel statistics;
    private javax.swing.JPanel stock;
    private javax.swing.JPanel yomia;
    // End of variables declaration//GEN-END:variables
}

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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class mainform extends javax.swing.JFrame {

    sqlcon opj;

    public int tick1num = 0, tick2num = 0;
    private static int BagMax = 2;
    private final JButton jButton_bagmax = new javax.swing.JButton();

    public mainform(sqlcon ops) throws IOException {
        initComponents();

        this.setDefaultCloseOperation(mainform.DO_NOTHING_ON_CLOSE);
        if (!login_form.admin) {
            jButton2.setEnabled(false);
            jButton3.setEnabled(false);
            jButton5.setEnabled(false);
            jButton10.setEnabled(false);
        } else {
            jButton_bagmax.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jButton_bagmax.setText("Seting");
            jButton_bagmax.addActionListener((java.awt.event.ActionEvent evt) -> {
                jButton_bagmaxActionPerformed(evt);
            });
            right_panel.add(jButton_bagmax, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 560, 110, 40));
        }

        this.opj = ops;

        this.jTable_storage.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.jTable_storage.getTableHeader().setFont(new Font("Tahoma", 1, 16));
        this.jTable1.getTableHeader().setFont(new Font("Tahoma", 1, 16));
        this.jTable2.getTableHeader().setFont(new Font("Tahoma", 1, 16));
        this.jTable3.getTableHeader().setFont(new Font("Tahoma", 1, 16));
        this.jTable4.getTableHeader().setFont(new Font("Tahoma", 1, 16));
        this.jTable5.getTableHeader().setFont(new Font("Tahoma", 1, 16));
        combox_fill(this.jComboBox_pro_in_storage, opj.dataRead("pro_name", "products"), true);
        combox_fill(this.jComboBox_pro_in_reports, opj.dataRead("pro_name", "products"), true);
        combox_fill(this.jComboBox_E_O_proName, opj.dataRead("pro_name", "products"), true);
        combox_fill(this.jComboBox_E_proName, opj.dataRead("pro_name", "products"), true);
        tick1num = loadTicknum("TicketNumber1.txt");
        tick2num = loadTicknum("TicketNumber2.txt");
    }

    int pro_Table_SelectedID = 0;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jFrame1 = new javax.swing.JFrame();
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
        jTextField_E_Color = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jCheckBox_E_QR = new javax.swing.JCheckBox();
        jCheckBox_E_P = new javax.swing.JCheckBox();
        jSplitPane1 = new javax.swing.JSplitPane();
        right_panel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
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
        jCheckBox2 = new javax.swing.JCheckBox();
        jButton_clear = new javax.swing.JButton();
        jCheckBox_print = new javax.swing.JCheckBox();
        jTextField_Color = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jCheckBox_QR = new javax.swing.JCheckBox();
        reports = new javax.swing.JPanel();
        jComboBox_pro_in_reports = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jTextField6 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable()
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
        total_weight = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        add_product = new javax.swing.JPanel();
        jTextField_pro_name = new javax.swing.JTextField();
        jButton_add_pro = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_pro = new javax.swing.JTable();
        jButton_del_pro = new javax.swing.JButton();
        jTextField_con_weight_add = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jTextField_pro_color = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        querys = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        stock = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        statistics = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel20 = new javax.swing.JLabel();
        yomia = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jDateChooser4 = new com.toedter.calendar.JDateChooser();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        pause = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel38 = new javax.swing.JLabel();
        print = new javax.swing.JPanel();
        jLabel_QR = new javax.swing.JLabel();
        Maximum = new javax.swing.JPanel();
        jLabel_BagMax = new javax.swing.JLabel();
        jTextField_BagMax = new javax.swing.JTextField();
        jButton_saveMax = new javax.swing.JButton();
        jButton_Max_Rest2 = new javax.swing.JButton();
        jButton_Max_Rest1 = new javax.swing.JButton();
        jLabel_tick1 = new javax.swing.JLabel();
        jLabel_tick2 = new javax.swing.JLabel();

        jFileChooser1.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
        jFileChooser1.setCurrentDirectory(new java.io.File("F:\\"));
            jFileChooser1.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);

            jFrame1.setLocation(new java.awt.Point(300, 100));
            jFrame1.setResizable(false);
            jFrame1.addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowClosing(java.awt.event.WindowEvent evt) {
                    jFrame1WindowClosing(evt);
                }
            });
            jFrame1.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            jLabel25.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jLabel25.setText("رقم البالته");
            jFrame1.getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 30, -1, -1));

            jLabel26.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jLabel26.setText("رقم اللوط");
            jFrame1.getContentPane().add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 30, -1, -1));

            jLabel27.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jLabel27.setText("الصنف");
            jFrame1.getContentPane().add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, -1, -1));

            jLabel28.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jLabel28.setText("عدد الكون");
            jFrame1.getContentPane().add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 30, -1, -1));

            jLabel29.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jLabel29.setText("الوزن");
            jFrame1.getContentPane().add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 30, -1, -1));

            jTextField_E_O_lot.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jTextField_E_O_lot.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            jTextField_E_O_lot.setEnabled(false);
            jFrame1.getContentPane().add(jTextField_E_O_lot, new org.netbeans.lib.awtextra.AbsoluteConstraints(279, 67, 90, -1));

            jTextField_E_O_ConNum.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jTextField_E_O_ConNum.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            jTextField_E_O_ConNum.setEnabled(false);
            jFrame1.getContentPane().add(jTextField_E_O_ConNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(409, 67, 90, -1));

            jTextField_E_O_PaltNum.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jTextField_E_O_PaltNum.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            jTextField_E_O_PaltNum.setEnabled(false);
            jFrame1.getContentPane().add(jTextField_E_O_PaltNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(536, 67, 80, -1));

            jTextField_E_O_Wight.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jTextField_E_O_Wight.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            jTextField_E_O_Wight.setEnabled(false);
            jFrame1.getContentPane().add(jTextField_E_O_Wight, new org.netbeans.lib.awtextra.AbsoluteConstraints(653, 67, 90, -1));

            jComboBox_E_O_proName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jComboBox_E_O_proName.setEnabled(false);
            jFrame1.getContentPane().add(jComboBox_E_O_proName, new org.netbeans.lib.awtextra.AbsoluteConstraints(89, 67, 143, -1));

            jLabel30.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jLabel30.setText("الوزن");
            jFrame1.getContentPane().add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 180, -1, -1));

            jTextField_E_lot.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jTextField_E_lot.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            jTextField_E_lot.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    jTextField_E_lotFocusGained(evt);
                }
            });
            jTextField_E_lot.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTextField_E_lotKeyTyped(evt);
                }
            });
            jFrame1.getContentPane().add(jTextField_E_lot, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 220, 90, -1));

            jTextField_E_ConNum.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jTextField_E_ConNum.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            jTextField_E_ConNum.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    jTextField_E_ConNumFocusGained(evt);
                }
            });
            jTextField_E_ConNum.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTextField_E_ConNumKeyTyped(evt);
                }
            });
            jFrame1.getContentPane().add(jTextField_E_ConNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 220, 90, -1));

            jTextField_E_PaltNum.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jTextField_E_PaltNum.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            jTextField_E_PaltNum.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    jTextField_E_PaltNumFocusGained(evt);
                }
            });
            jTextField_E_PaltNum.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTextField_E_PaltNumKeyTyped(evt);
                }
            });
            jFrame1.getContentPane().add(jTextField_E_PaltNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 220, 90, -1));

            jTextField_E_Weight.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jTextField_E_Weight.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            jTextField_E_Weight.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    jTextField_E_WeightFocusGained(evt);
                }
            });
            jTextField_E_Weight.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTextField_E_WeightKeyTyped(evt);
                }
            });
            jFrame1.getContentPane().add(jTextField_E_Weight, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 220, 90, -1));

            jComboBox_E_proName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jComboBox_E_proName.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jComboBox_E_proNameKeyTyped(evt);
                }
            });
            jFrame1.getContentPane().add(jComboBox_E_proName, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 220, 143, -1));

            jLabel31.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jLabel31.setText("رقم البالته");
            jFrame1.getContentPane().add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 180, -1, -1));

            jLabel32.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jLabel32.setText("رقم اللوط");
            jFrame1.getContentPane().add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 180, -1, -1));

            jLabel33.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jLabel33.setText("الصنف");
            jFrame1.getContentPane().add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 180, -1, 20));

            jLabel34.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jLabel34.setText("عدد الكون");
            jFrame1.getContentPane().add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 180, -1, -1));

            jButton_E_Edit.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jButton_E_Edit.setText("تعديل");
            jButton_E_Edit.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_E_EditActionPerformed(evt);
                }
            });
            jFrame1.getContentPane().add(jButton_E_Edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 280, 141, 39));

            jLabel35.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jLabel35.setText("ـــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــ");
            jFrame1.getContentPane().add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 740, -1));

            jLabel36.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jLabel36.setText("القيم الجديده");
            jFrame1.getContentPane().add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 140, -1, -1));

            jCheckBox_E_O_Mark.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jCheckBox_E_O_Mark.setText("تعليم الشيكاره");
            jCheckBox_E_O_Mark.setEnabled(false);
            jFrame1.getContentPane().add(jCheckBox_E_O_Mark, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, -1, -1));

            jCheckBox_E_Mark.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jCheckBox_E_Mark.setText("تعليم الشيكاره");
            jFrame1.getContentPane().add(jCheckBox_E_Mark, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 270, -1, -1));

            jButton_E_print.setText("Print");
            jButton_E_print.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_E_printActionPerformed(evt);
                }
            });
            jFrame1.getContentPane().add(jButton_E_print, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 273, 100, 40));

            jTextField_E_Color.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jTextField_E_Color.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTextField_E_ColorKeyTyped(evt);
                }
            });
            jFrame1.getContentPane().add(jTextField_E_Color, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 130, 40));

            jLabel43.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jLabel43.setText("اللون");
            jFrame1.getContentPane().add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, 50, 20));

            jCheckBox_E_QR.setText("QR");
            jFrame1.getContentPane().add(jCheckBox_E_QR, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, -1));

            jCheckBox_E_P.setText("print");
            jFrame1.getContentPane().add(jCheckBox_E_P, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, -1, -1));

            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
            setTitle("mizan program");
            setMaximumSize(new java.awt.Dimension(1020, 700));
            setMinimumSize(new java.awt.Dimension(1020, 700));
            setPreferredSize(new java.awt.Dimension(1020, 700));
            setResizable(false);
            addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowClosing(java.awt.event.WindowEvent evt) {
                    formWindowClosing(evt);
                }
            });

            jSplitPane1.setDividerLocation(840);
            jSplitPane1.setMaximumSize(new java.awt.Dimension(1023, 652));
            jSplitPane1.setMinimumSize(new java.awt.Dimension(1023, 652));

            right_panel.setMaximumSize(new java.awt.Dimension(130, 650));
            right_panel.setMinimumSize(new java.awt.Dimension(130, 650));
            right_panel.setPreferredSize(new java.awt.Dimension(130, 650));
            right_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            jButton1.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
            jButton1.setText("ميزان");
            jButton1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton1ActionPerformed(evt);
                }
            });
            right_panel.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 20, 110, 40));

            jButton2.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
            jButton2.setText("إضافه صنف");
            jButton2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton2ActionPerformed(evt);
                }
            });
            right_panel.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 140, 110, 40));

            jButton3.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
            jButton3.setText("إذن غزل");
            jButton3.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton3ActionPerformed(evt);
                }
            });
            right_panel.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 80, 110, 40));

            jButton5.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
            jButton5.setText("أستعلامات");
            jButton5.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton5ActionPerformed(evt);
                }
            });
            right_panel.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 200, 110, 40));

            jButton7.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
            jButton7.setText("باك أب");
            jButton7.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton7ActionPerformed(evt);
                }
            });
            right_panel.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 260, 110, 40));

            jButton8.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jButton8.setText("رصيد");
            jButton8.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton8ActionPerformed(evt);
                }
            });
            right_panel.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 320, 110, 40));

            jButton9.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
            jButton9.setText("إحصائيات");
            jButton9.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton9ActionPerformed(evt);
                }
            });
            right_panel.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 380, 110, 40));

            jButton10.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jButton10.setText("يوميه");
            jButton10.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton10ActionPerformed(evt);
                }
            });
            right_panel.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 440, 110, 40));

            jButton14.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jButton14.setText("أي");
            jButton14.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton14ActionPerformed(evt);
                }
            });
            right_panel.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 500, 110, 40));

            jSplitPane1.setRightComponent(right_panel);

            left_panel.setMaximumSize(new java.awt.Dimension(896, 650));
            left_panel.setLayout(new java.awt.CardLayout());

            in_data.setMaximumSize(new java.awt.Dimension(834, 600));
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
            in_data.add(jButton_add_data, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 590, 90, 50));

            jButton_del_data.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
            jButton_del_data.setText("حذف");
            jButton_del_data.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_del_dataActionPerformed(evt);
                }
            });
            in_data.add(jButton_del_data, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 590, 80, 50));

            jComboBox_pro_in_storage.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
            jComboBox_pro_in_storage.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
            jComboBox_pro_in_storage.addItemListener(new java.awt.event.ItemListener() {
                public void itemStateChanged(java.awt.event.ItemEvent evt) {
                    jComboBox_pro_in_storageItemStateChanged(evt);
                }
            });
            jComboBox_pro_in_storage.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jComboBox_pro_in_storageKeyTyped(evt);
                }
            });
            in_data.add(jComboBox_pro_in_storage, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 40, 200, 30));

            jLabel6.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
            jLabel6.setText("الصنف");
            in_data.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 10, -1, 20));

            jTextField_lot.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
            jTextField_lot.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
            jTextField_lot.setMaximumSize(new java.awt.Dimension(7, 38));
            jTextField_lot.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTextField_lotKeyTyped(evt);
                }
            });
            in_data.add(jTextField_lot, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 90, 120, 36));

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
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    jTextField_weightKeyReleased(evt);
                }
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTextField_weightKeyTyped(evt);
                }
            });
            in_data.add(jTextField_weight, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 300, 120, -1));

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
            in_data.add(jTextField_bag_weight, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 250, 120, -1));

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
            in_data.add(jTextField_num_of_con, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 200, 120, -1));

            jLabel7.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
            jLabel7.setText("الوزن القائم");
            jLabel7.setMaximumSize(new java.awt.Dimension(82, 24));
            jLabel7.setMinimumSize(new java.awt.Dimension(82, 24));
            jLabel7.setPreferredSize(new java.awt.Dimension(82, 24));
            in_data.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 300, -1, 30));

            jLabel8.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
            jLabel8.setText("اللوط");
            jLabel8.setMaximumSize(new java.awt.Dimension(82, 24));
            jLabel8.setMinimumSize(new java.awt.Dimension(82, 24));
            jLabel8.setPreferredSize(new java.awt.Dimension(82, 24));
            in_data.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 90, -1, 30));

            jLabel9.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
            jLabel9.setText("عدد الكون");
            jLabel9.setMaximumSize(new java.awt.Dimension(82, 24));
            jLabel9.setMinimumSize(new java.awt.Dimension(82, 24));
            jLabel9.setPreferredSize(new java.awt.Dimension(82, 24));
            in_data.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 200, -1, 30));

            jLabel10.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
            jLabel10.setText("وزن الكونه");
            jLabel10.setMaximumSize(new java.awt.Dimension(82, 24));
            jLabel10.setMinimumSize(new java.awt.Dimension(82, 24));
            jLabel10.setPreferredSize(new java.awt.Dimension(82, 24));
            in_data.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 420, -1, 30));

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
            in_data.add(jTextField_pallet_num, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 140, 120, -1));

            jTextField_weight_of_con.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
            jTextField_weight_of_con.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
            jTextField_weight_of_con.setMaximumSize(new java.awt.Dimension(7, 38));
            jTextField_weight_of_con.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTextField_weight_of_conKeyTyped(evt);
                }
            });
            in_data.add(jTextField_weight_of_con, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 420, 120, -1));

            jLabel11.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
            jLabel11.setText("فارغ الشيكاره");
            jLabel11.setMaximumSize(new java.awt.Dimension(82, 24));
            jLabel11.setMinimumSize(new java.awt.Dimension(82, 24));
            jLabel11.setPreferredSize(new java.awt.Dimension(82, 24));
            in_data.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 250, -1, 30));

            jLabel12.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
            jLabel12.setText("الوزن الصافي");
            in_data.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 350, -1, 30));

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
            in_data.add(jTextField_net_weight, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 350, 120, -1));

            jLabel2.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
            jLabel2.setText("رقم البالتة");
            jLabel2.setMaximumSize(new java.awt.Dimension(82, 24));
            jLabel2.setMinimumSize(new java.awt.Dimension(82, 24));
            jLabel2.setPreferredSize(new java.awt.Dimension(82, 24));
            in_data.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 140, -1, 30));

            jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jLabel14.setText("وزن البالتة");
            in_data.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 490, -1, 30));

            pallet_weight.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            pallet_weight.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
            in_data.add(pallet_weight, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 490, 140, -1));

            jCheckBox2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            jCheckBox2.setText("تعليم البالته");
            jCheckBox2.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    jCheckBox2KeyPressed(evt);
                }
            });
            in_data.add(jCheckBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 460, -1, -1));

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
            in_data.add(jButton_clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 610, 40, -1));

            jCheckBox_print.setSelected(true);
            jCheckBox_print.setText("طباعة");
            jCheckBox_print.setFocusable(false);
            in_data.add(jCheckBox_print, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, -1, -1));

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

            jCheckBox_QR.setText("QR");
            in_data.add(jCheckBox_QR, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 10, -1, -1));

            left_panel.add(in_data, "card2");

            reports.setMaximumSize(new java.awt.Dimension(834, 600));
            reports.setMinimumSize(new java.awt.Dimension(834, 600));
            reports.setPreferredSize(new java.awt.Dimension(834, 600));
            reports.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            jComboBox_pro_in_reports.setFont(new java.awt.Font("sansserif", 0, 20)); // NOI18N
            jComboBox_pro_in_reports.addItemListener(new java.awt.event.ItemListener() {
                public void itemStateChanged(java.awt.event.ItemEvent evt) {
                    jComboBox_pro_in_reportsItemStateChanged(evt);
                }
            });
            reports.add(jComboBox_pro_in_reports, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 300, -1));

            jLabel1.setFont(new java.awt.Font("sansserif", 0, 20)); // NOI18N
            jLabel1.setText("الصنف");
            reports.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, -1, -1));

            jLabel4.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
            jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel4.setText("_________________________________________________________");
            reports.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 750, 30));

            jTextField5.setFont(new java.awt.Font("sansserif", 0, 20)); // NOI18N
            jTextField5.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
            jTextField5.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusLost(java.awt.event.FocusEvent evt) {
                    jTextField5FocusLost(evt);
                }
            });
            jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    jTextField5KeyReleased(evt);
                }
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTextField5KeyTyped(evt);
                }
            });
            reports.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, 80, -1));

            jButton4.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
            jButton4.setText("طباعة");
            jButton4.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton4ActionPerformed(evt);
                }
            });
            reports.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 560, 120, 50));

            jTable3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jTable3.setModel(new javax.swing.table.DefaultTableModel(
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
            jTable3.setGridColor(new java.awt.Color(0, 0, 0));
            jTable3.setRowHeight(25);
            jTable3.setShowGrid(true);
            jTable3.getTableHeader().setReorderingAllowed(false);
            jScrollPane3.setViewportView(jTable3);

            reports.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 278, 610, 360));

            jTextField6.setFont(new java.awt.Font("sansserif", 0, 20)); // NOI18N
            jTextField6.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
            jTextField6.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    jTextField6FocusGained(evt);
                }
            });
            jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTextField6KeyTyped(evt);
                }
            });
            reports.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, 450, 40));

            jLabel5.setFont(new java.awt.Font("sansserif", 0, 20)); // NOI18N
            jLabel5.setText("أسم العميل");
            reports.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 200, -1, -1));

            jTable4.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
            jTable4.setModel(new javax.swing.table.DefaultTableModel(
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
            jTable4.setGridColor(new java.awt.Color(0, 0, 0));
            jTable4.setRowHeight(25);
            jTable4.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
            jTable4.setShowGrid(true);
            jTable4.getTableHeader().setReorderingAllowed(false);
            jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jTable4MouseClicked(evt);
                }
            });
            jScrollPane4.setViewportView(jTable4);
            if (jTable4.getColumnModel().getColumnCount() > 0) {
                jTable4.getColumnModel().getColumn(3).setMinWidth(100);
                jTable4.getColumnModel().getColumn(3).setPreferredWidth(100);
                jTable4.getColumnModel().getColumn(3).setMaxWidth(100);
                jTable4.getColumnModel().getColumn(4).setMinWidth(0);
                jTable4.getColumnModel().getColumn(4).setPreferredWidth(0);
                jTable4.getColumnModel().getColumn(4).setMaxWidth(0);
            }

            reports.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, 500, 170));

            total_weight.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            total_weight.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            total_weight.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    total_weightKeyPressed(evt);
                }
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    total_weightKeyTyped(evt);
                }
            });
            reports.add(total_weight, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 430, 140, 50));

            jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jLabel3.setText("إجمالي الوزن");
            reports.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 380, -1, -1));

            jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jLabel13.setText("عدد الشكاير");
            reports.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, -1, -1));

            jCheckBox1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jCheckBox1.setText(" صنفين في اذن واحد");
            reports.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, -1, -1));

            jComboBox2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            reports.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 300, 80, -1));

            jLabel16.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
            jLabel16.setText("رقم البالته");
            reports.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 250, -1, -1));

            left_panel.add(reports, "card4");

            add_product.setMaximumSize(new java.awt.Dimension(834, 600));
            add_product.setMinimumSize(new java.awt.Dimension(834, 600));
            add_product.setPreferredSize(new java.awt.Dimension(834, 600));
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
            jTable_pro.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTable_proKeyTyped(evt);
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

            jTextField_con_weight_add.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
            jTextField_con_weight_add.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
            jTextField_con_weight_add.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTextField_con_weight_addKeyTyped(evt);
                }
            });
            add_product.add(jTextField_con_weight_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 90, 140, 30));

            jLabel24.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jLabel24.setText("الأسم");
            add_product.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, -1, -1));

            jLabel37.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jLabel37.setText("وزن الكونه");
            add_product.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, -1, -1));

            jTextField_pro_color.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jTextField_pro_color.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            add_product.add(jTextField_pro_color, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, 140, 30));

            jLabel49.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel49.setText("اللون");
            add_product.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 50, 70, 30));

            left_panel.add(add_product, "card3");

            querys.setMaximumSize(new java.awt.Dimension(834, 600));
            querys.setMinimumSize(new java.awt.Dimension(834, 600));
            querys.setPreferredSize(new java.awt.Dimension(834, 600));

            jTable1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
            jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                    "مسلسل", "الأسم", "التاريخ"
                }
            ) {
                boolean[] canEdit = new boolean [] {
                    false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            });
            jTable1.setGridColor(new java.awt.Color(0, 0, 0));
            jTable1.setRowHeight(25);
            jTable1.setSelectionBackground(new java.awt.Color(0, 0, 0));
            jScrollPane5.setViewportView(jTable1);
            if (jTable1.getColumnModel().getColumnCount() > 0) {
                jTable1.getColumnModel().getColumn(0).setMinWidth(100);
                jTable1.getColumnModel().getColumn(0).setPreferredWidth(100);
                jTable1.getColumnModel().getColumn(0).setMaxWidth(100);
                jTable1.getColumnModel().getColumn(1).setResizable(false);
                jTable1.getColumnModel().getColumn(2).setResizable(false);
            }

            jButton6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jButton6.setText("فتح");
            jButton6.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton6ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout querysLayout = new javax.swing.GroupLayout(querys);
            querys.setLayout(querysLayout);
            querysLayout.setHorizontalGroup(
                querysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(querysLayout.createSequentialGroup()
                    .addGap(28, 28, 28)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 218, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(80, 80, 80))
            );
            querysLayout.setVerticalGroup(
                querysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(querysLayout.createSequentialGroup()
                    .addGroup(querysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(querysLayout.createSequentialGroup()
                            .addGap(85, 85, 85)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(querysLayout.createSequentialGroup()
                            .addGap(210, 210, 210)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(138, Short.MAX_VALUE))
            );

            left_panel.add(querys, "card5");

            stock.setMaximumSize(new java.awt.Dimension(834, 600));
            stock.setMinimumSize(new java.awt.Dimension(834, 600));
            stock.setPreferredSize(new java.awt.Dimension(834, 600));
            stock.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jComboBox1.addItemListener(new java.awt.event.ItemListener() {
                public void itemStateChanged(java.awt.event.ItemEvent evt) {
                    jComboBox1ItemStateChanged(evt);
                }
            });
            stock.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 34, 433, -1));

            jTable2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
            jTable2.setRowHeight(25);
            jTable2.setShowGrid(true);
            jTable2.getTableHeader().setResizingAllowed(false);
            jTable2.getTableHeader().setReorderingAllowed(false);
            jScrollPane6.setViewportView(jTable2);

            stock.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 109, 712, 477));

            jLabel15.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
            jLabel15.setText("الصنف");
            stock.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(616, 26, -1, -1));

            left_panel.add(stock, "card6");

            statistics.setMaximumSize(new java.awt.Dimension(834, 600));
            statistics.setMinimumSize(new java.awt.Dimension(834, 600));
            statistics.setPreferredSize(new java.awt.Dimension(834, 600));
            statistics.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            jTable5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jTable5.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                    "اللوط", "عدد الشكاير", "إجمالي الوزن"
                }
            ) {
                boolean[] canEdit = new boolean [] {
                    false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            });
            jTable5.setRowHeight(25);
            jTable5.setShowGrid(true);
            jScrollPane7.setViewportView(jTable5);
            if (jTable5.getColumnModel().getColumnCount() > 0) {
                jTable5.getColumnModel().getColumn(0).setResizable(false);
                jTable5.getColumnModel().getColumn(1).setResizable(false);
                jTable5.getColumnModel().getColumn(2).setResizable(false);
            }

            statistics.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 760, 420));

            jLabel17.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jLabel17.setText("الصنف");
            statistics.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, -1, -1));

            jLabel18.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jLabel18.setText("إلي");
            statistics.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 20, -1, -1));

            jLabel19.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jLabel19.setText("من");
            statistics.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 10, -1, -1));

            jComboBox3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jComboBox3.addItemListener(new java.awt.event.ItemListener() {
                public void itemStateChanged(java.awt.event.ItemEvent evt) {
                    jComboBox3ItemStateChanged(evt);
                }
            });
            statistics.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 340, -1));
            statistics.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 60, 140, 30));
            statistics.add(jDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 60, 140, 30));

            jLabel20.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jLabel20.setText("التاريخ:");
            statistics.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 10, -1, -1));

            left_panel.add(statistics, "card7");

            yomia.setMaximumSize(new java.awt.Dimension(834, 600));
            yomia.setMinimumSize(new java.awt.Dimension(834, 600));
            yomia.setPreferredSize(new java.awt.Dimension(834, 600));
            yomia.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            jTable6.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
            jTable6.setModel(new javax.swing.table.DefaultTableModel(
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
            jTable6.setRowHeight(25);
            jTable6.getTableHeader().setReorderingAllowed(false);
            jScrollPane8.setViewportView(jTable6);
            if (jTable6.getColumnModel().getColumnCount() > 0) {
                jTable6.getColumnModel().getColumn(0).setPreferredWidth(250);
                jTable6.getColumnModel().getColumn(1).setPreferredWidth(250);
                jTable6.getColumnModel().getColumn(2).setPreferredWidth(50);
                jTable6.getColumnModel().getColumn(3).setPreferredWidth(50);
                jTable6.getColumnModel().getColumn(4).setPreferredWidth(120);
                jTable6.getColumnModel().getColumn(5).setPreferredWidth(120);
            }

            yomia.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 137, 810, 500));
            yomia.add(jDateChooser3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 80, 180, 30));
            yomia.add(jDateChooser4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 160, 30));

            jLabel21.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jLabel21.setText("التاريخ:");
            yomia.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 40, -1, -1));

            jLabel22.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jLabel22.setText("من");
            yomia.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 30, -1, -1));

            jLabel23.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jLabel23.setText("إلي");
            yomia.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, -1, -1));

            jButton11.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jButton11.setText("بحث");
            jButton11.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton11ActionPerformed(evt);
                }
            });
            yomia.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 70, -1, -1));

            jButton12.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
            jButton12.setText("أسترجاع الأزن");
            jButton12.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton12ActionPerformed(evt);
                }
            });
            yomia.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 90, 150, 40));

            left_panel.add(yomia, "card8");

            pause.setEnabled(false);
            pause.setFocusable(false);
            pause.setMaximumSize(new java.awt.Dimension(834, 600));
            pause.setMinimumSize(new java.awt.Dimension(834, 600));
            pause.setPreferredSize(new java.awt.Dimension(834, 600));
            pause.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            jTextArea1.setColumns(20);
            jTextArea1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
            jTextArea1.setRows(5);
            jTextArea1.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTextArea1KeyTyped(evt);
                }
            });
            jScrollPane9.setViewportView(jTextArea1);

            pause.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 820, 540));

            jLabel38.setFont(new java.awt.Font("Tahoma", 3, 48)); // NOI18N
            jLabel38.setText("اي حاجة");
            jLabel38.setEnabled(false);
            jLabel38.setFocusable(false);
            pause.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, 290, 50));

            left_panel.add(pause, "card9");

            print.setBackground(new java.awt.Color(255, 255, 255));
            print.setFocusable(false);
            print.setMaximumSize(new java.awt.Dimension(300, 300));
            print.setMinimumSize(new java.awt.Dimension(200, 200));
            print.setPreferredSize(new java.awt.Dimension(200, 200));
            print.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            jLabel_QR.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel_QR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jLabel_QR.setMaximumSize(new java.awt.Dimension(40, 40));
            jLabel_QR.setMinimumSize(new java.awt.Dimension(40, 40));
            jLabel_QR.setPreferredSize(new java.awt.Dimension(40, 40));
            print.add(jLabel_QR, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 40, 40));

            left_panel.add(print, "card10");

            Maximum.setMaximumSize(new java.awt.Dimension(834, 600));
            Maximum.setMinimumSize(new java.awt.Dimension(834, 600));
            Maximum.setPreferredSize(new java.awt.Dimension(834, 600));
            Maximum.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            jLabel_BagMax.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
            jLabel_BagMax.setText("Bag Max");
            Maximum.add(jLabel_BagMax, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 170, 150, 70));

            jTextField_BagMax.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
            jTextField_BagMax.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    jTextField_BagMaxKeyTyped(evt);
                }
            });
            Maximum.add(jTextField_BagMax, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 170, 140, 70));

            jButton_saveMax.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
            jButton_saveMax.setText("Save");
            jButton_saveMax.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_saveMaxActionPerformed(evt);
                }
            });
            Maximum.add(jButton_saveMax, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 290, 140, 70));

            jButton_Max_Rest2.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
            jButton_Max_Rest2.setText("Reset 2");
            jButton_Max_Rest2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_Max_Rest2ActionPerformed(evt);
                }
            });
            Maximum.add(jButton_Max_Rest2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 420, 110, 60));

            jButton_Max_Rest1.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
            jButton_Max_Rest1.setText("Reset");
            jButton_Max_Rest1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_Max_Rest1ActionPerformed(evt);
                }
            });
            Maximum.add(jButton_Max_Rest1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 420, 110, 60));
            Maximum.add(jLabel_tick1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 490, 70, 50));
            Maximum.add(jLabel_tick2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 490, 70, 50));

            left_panel.add(Maximum, "card11");

            jSplitPane1.setLeftComponent(left_panel);

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 1023, Short.MAX_VALUE)
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        open_panel(in_data);
        if (jComboBox_pro_in_storage.getSelectedIndex() == -1) {
            DefaultTableModel model = (DefaultTableModel) jTable_storage.getModel();
            model.setRowCount(0);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        pro_Table_SelectedID = 0;
        open_panel(add_product);
        fill_pro_table();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        open_panel(reports);
        ((DefaultTableModel) jTable4.getModel()).setRowCount(0);
        ((DefaultTableModel) jTable3.getModel()).setRowCount(0);
        jComboBox_pro_in_reports.setSelectedIndex(-1);
        second = false;
        total_weight.setText("");
        jComboBox2.removeAllItems();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField_lotKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_lotKeyTyped
        textbox_length_limiter(evt, jTextField_lot, 5);
        char input = evt.getKeyChar();
        if (Character.isDigit(input)) {
            evt.setKeyChar(ToNumArab(input));
        }
        if ((!Character.isDigit(input)) && input != 'أ' && input != 'س' && input != 'و' && input != 'د' && input != 'ط' && input != 'ب' && input != 'ا' && input != 'ع' && input != 'ه' && input != 'م' && input != 'ن') {
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
        jCheckBox2.setSelected(false);
        calc_net_weight();
        if (jTextField_pallet_num.getText().isBlank()) {
            pallet_weight.setText("");
        }
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            jTextField_num_of_con.requestFocusInWindow();
        }
    }//GEN-LAST:event_jTextField_pallet_numKeyTyped

    private void jTextField_net_weightKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_net_weightKeyTyped
        if (evt.getKeyChar() == KeyEvent.VK_ENTER && (!jTextField_net_weight.getText().isBlank())) {
            jTextField_num_of_con.requestFocusInWindow();
            jButton_add_data.doClick();
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
                boolean f = true;
                if (opj.dataRead("*", "storage", "pallet_numb=" + ToDoubleEnglish(jTextField_pallet_num.getText()) + " and  lot=N'" + ToStringEnglish(jTextField_lot.getText()) + "' and pro_id=(select pro_id from products where pro_name=N'" + jComboBox_pro_in_storage.getSelectedItem() + "') ").next()) {
                    f = opj.dataRead("*", "storage", "pallet_numb=" + ToDoubleEnglish(jTextField_pallet_num.getText()) + " and  lot=N'" + ToStringEnglish(jTextField_lot.getText()) + "' and pro_id=(select pro_id from products where pro_name=N'" + jComboBox_pro_in_storage.getSelectedItem() + "') and used =" + (jCheckBox2.isSelected() ? 1 : 0) + " ").next();
                }
                if (f) {
                    if (ToDoubleEnglish(jTextField_weight.getText()) <= 80.0 && ToDoubleEnglish(jTextField_net_weight.getText()) > 0.0) {
                        calc_net_weight();
                        int pallet = 0;
                        ResultSet st;
                        do {
                            st = opj.dataRead("count(*) ", "storage",
                                    "pallet_numb=" + ToDoubleEnglish(jTextField_pallet_num.getText()) + " and lot=N'"
                                    + ToStringEnglish(jTextField_lot.getText())
                                    + "' and pro_id=(select pro_id from products where pro_name=N'"
                                    + jComboBox_pro_in_storage.getSelectedItem() + "') ");
                            st.next();
                            if (st.getInt(1) >= 20) {
                                pallet = (int) ToDoubleEnglish(jTextField_pallet_num.getText());
                                jTextField_pallet_num.setText(ToDoubleArabic(++pallet + ""));
                                jCheckBox2.setSelected(false);
                            }
                        } while (st.getInt(1) >= 20);

                        int s = jCheckBox2.isSelected() ? 1 : 0;
                        opj.inData("storage", "pro_id,weight_,lot,pallet_numb,date_,num_of_con,used",
                                " (select pro_id from products where pro_name=N'" + jComboBox_pro_in_storage.getSelectedItem()
                                + "')" + "," + ToDoubleEnglish(jTextField_net_weight.getText()) + ",N'"
                                + ToStringEnglish(jTextField_lot.getText()) + "',"
                                + ToDoubleEnglish(jTextField_pallet_num.getText()) + ",GETDATE(),"
                                + ToDoubleEnglish(jTextField_num_of_con.getText()) + "," + s + " ");
                        calc_pallet_weight();
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
                                JOptionPane.showMessageDialog(null, "<html><body><h1  style='font-family: Arial; font-size: 20pt; text-align: right; width: 150px;'>printing faild</h1></body></html>", "إنتبه", JOptionPane.PLAIN_MESSAGE);
                            }
                        } else {
                            jTextField_net_weight.setText("");
                            jTextField_weight.setText("");
                            jTextField_bag_weight.setText("");
                            fill_storage_table();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "<html><body><h1  style='font-family: Arial; font-size: 20pt; text-align: right; width: 150px;'>خطأ في وزن الشيكاره </h1></body></html>", "إنتبه", JOptionPane.PLAIN_MESSAGE);
                        jTextField_weight.requestFocus();
                        jTextField_weight.selectAll();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "<html><body><h1  style='font-family: Arial; font-size: 20pt; text-align: right; width: 150px;'> خطأ في تعليم الشكاره</h1></body></html>", "إنتبه", JOptionPane.PLAIN_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "<html><body><h1  style='font-family: Arial; font-size: 20pt; text-align: right; width: 150px;'> برجاء إدخال البيانات كامله</h1></body></html>", "إنتبه", JOptionPane.PLAIN_MESSAGE);
            }
        } catch (WriterException | HeadlessException | IOException | SQLException | PrinterException ex) {
            Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage(), "إنتبه", JOptionPane.ERROR_MESSAGE);
        } catch (InterruptedException ex) {
            Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_add_dataActionPerformed

    private void jButton_del_dataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_del_dataActionPerformed
        if (JOptionPane.showConfirmDialog(null, "هل تريد الحذف ؟", "تنبيه", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (jTable_storage.getSelectedRowCount() == 1) {
                opj.delData("storage", "storage_id=" + Integer.parseInt(jTable_storage.getModel().getValueAt(jTable_storage.getSelectedRow(), 4).toString()) + " ");
                JOptionPane.showMessageDialog(null, "<html><body><h1  style='font-family: Arial; font-size: 20pt; text-align: right; width: 150px;'> تم حذف البيان بنجاح </h1></body></html>", "ناجح", JOptionPane.PLAIN_MESSAGE);
                fill_storage_table();
            } else if (jTable_storage.getSelectedRowCount() > 1) {
                for (int row : jTable_storage.getSelectedRows()) {
                    opj.delData("storage", "storage_id=" + Integer.parseInt(jTable_storage.getModel().getValueAt(row, 4).toString()) + " ");
                }
                fill_storage_table();
                JOptionPane.showMessageDialog(null, "<html><body><h1  style='font-family: Arial; font-size: 20pt; text-align: right; width: 150px;'> تم حذف البيــانات بنجاح </h1></body></html>", "ناجح", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "<html><body><h1  style='font-family: Arial; font-size: 20pt; text-align: right; width: 150px;'> برجاء أختيار بيان من الجدول أولا  </h1></body></html>", "إنتبه", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton_del_dataActionPerformed

    private void jButton_add_proActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_add_proActionPerformed
        if (pro_Table_SelectedID == 0) {
            if (jTextField_pro_name.getText().isBlank() || jTextField_con_weight_add.getText().isBlank()) {
                JOptionPane.showMessageDialog(null, "<html><body><h1  style='font-family: Arial; font-size: 20pt; text-align: right; width: 150px;'> برجاء أدخال البيانات كامله  </h1></body></html>", "إنتبه", JOptionPane.PLAIN_MESSAGE);
            } else {
                try {
                    if (opj.dataRead("*", "products", "pro_name=N'" + jTextField_pro_name.getText() + "'").next()) {
                        JOptionPane.showMessageDialog(null, "<html><body><h1  style='font-family: Arial; font-size: 20pt; text-align: right; width: 150px;'>  هذا الصنف موجود بالفعل </h1></body></html>", "إنتبه", JOptionPane.PLAIN_MESSAGE);
                    } else {
                        opj.inData("products", "pro_name,weight_of_con,Color", "N'" + jTextField_pro_name.getText() + "',N'" + ToStringEnglish(jTextField_con_weight_add.getText()) + "',N'" + jTextField_pro_color.getText() + "'");
                        jTextField_pro_name.setText("");
                        jTextField_con_weight_add.setText("");
                        jTextField_pro_color.setText("");
                        fill_pro_table();
                        combox_fill(jComboBox_pro_in_storage, opj.dataRead("pro_name", "products"), true);
                        combox_fill(jComboBox_pro_in_reports, opj.dataRead("pro_name", "products"), true);
                        JOptionPane.showMessageDialog(null, "<html><body><h1  style='font-family: Arial; font-size: 20pt; text-align: right; width: 150px;'> تم إدخال الصنف بنجاح  </h1></body></html>", "ناجح", JOptionPane.PLAIN_MESSAGE);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "إنتبه", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            if (jTextField_pro_name.getText().isBlank()) {
                JOptionPane.showMessageDialog(null, "<html><body><h1  style='font-family: Arial; font-size: 20pt; text-align: right; width: 150px;'> برجاء أدخال اسم الصنف  </h1></body></html>", "إنتبه", JOptionPane.PLAIN_MESSAGE);
            } else {
                opj.update("products", "pro_name=N'" + jTextField_pro_name.getText() + "' ,weight_of_con=N'" + ToStringEnglish(jTextField_con_weight_add.getText()) + "' ,Color=N'" + jTextField_pro_color.getText() + "' ", "pro_id=" + pro_Table_SelectedID + " ");
                pro_Table_SelectedID = 0;
                jTextField_pro_name.setText("");
                jTextField_con_weight_add.setText("");
                jTextField_pro_color.setText("");
                fill_pro_table();
                combox_fill(jComboBox_pro_in_storage, opj.dataRead("pro_name", "products"), true);
                combox_fill(jComboBox_pro_in_reports, opj.dataRead("pro_name", "products"), true);
                JOptionPane.showMessageDialog(null, "<html><body><h1  style='font-family: Arial; font-size: 20pt; text-align: right; width: 150px;'>  تم تعديل الصنف بنجاح </h1></body></html>", "ناجح", JOptionPane.PLAIN_MESSAGE);
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
            jTextField_con_weight_add.setText(model.getValueAt(jTable_pro.getSelectedRow(), 2).toString());
            pro_Table_SelectedID = Integer.parseInt(model.getValueAt(jTable_pro.getSelectedRow(), 0).toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "555");
        }
    }//GEN-LAST:event_jTable_proMouseClicked

    private void jButton_del_proActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_del_proActionPerformed
        if (pro_Table_SelectedID != 0) {
            try {
                if (!opj.dataRead("*", "storage", "pro_id=" + pro_Table_SelectedID + "").next()) {
                    opj.delData("products", "pro_id=" + pro_Table_SelectedID + " ");
                    JOptionPane.showMessageDialog(null, "<html><body><h1  style='font-family: Arial; font-size: 20pt; text-align: right; width: 150px;'>  تم حذف الصنف بنجاح </h1></body></html>", "ناجح", JOptionPane.PLAIN_MESSAGE);
                    jTextField_pro_name.setText("");
                    pro_Table_SelectedID = 0;
                    fill_pro_table();
                    combox_fill(jComboBox_pro_in_storage, opj.dataRead("pro_name", "products"), true);
                    combox_fill(jComboBox_pro_in_reports, opj.dataRead("pro_name", "products"), true);
                } else {
                    JOptionPane.showMessageDialog(null, "<html><body><h1  style='font-family: Arial; font-size: 20pt; text-align: right; width: 150px;'> لا يمكن حذف هذا الصنف  </h1></body></html>", "إنتبه", JOptionPane.PLAIN_MESSAGE);
                }
            } catch (SQLException ex) {
                Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex.getMessage(), "إنتبه", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "<html><body><h1  style='font-family: Arial; font-size: 20pt; text-align: right; width: 150px;'> برجاء أختيار صنف من الجدول أولا  </h1></body></html>", "إنتبه", JOptionPane.PLAIN_MESSAGE);
        }

    }//GEN-LAST:event_jButton_del_proActionPerformed

    private void jComboBox_pro_in_storageItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_pro_in_storageItemStateChanged
        jTextField_lot.setText("");

        if (this.jComboBox_pro_in_storage.hasFocus()) {
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

    private void jComboBox_pro_in_reportsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_pro_in_reportsItemStateChanged
        if (jComboBox_pro_in_reports.hasFocus()) {
            ((DefaultTableModel) jTable4.getModel()).setRowCount(0);
            ResultSet st = opj.dataRead("count(*),sum(weight_),lot,pallet_numb,used", "storage", "pro_id=(select pro_id from products where pro_name=N'" + jComboBox_pro_in_reports.getSelectedItem().toString() + "' )  GROUP BY lot,pallet_numb,used");
            try {
                while (st.next()) {

                    ((DefaultTableModel) jTable4.getModel()).addRow(new Object[]{ToDoubleArabic(st.getString(1)), ToDoubleArabic(st.getString(2)), ToDoubleArabic(st.getString(3)), ToDoubleArabic(st.getString(4)), ToDoubleArabic(st.getString(5))});
                }
            } catch (SQLException ex) {
                Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex.getMessage(), "إنتبه", JOptionPane.ERROR_MESSAGE);
            }
            ((DefaultTableModel) jTable3.getModel()).setRowCount(0);
            serial = 0;
            order_ids.clear();
            total_weight.setText("");
            jComboBox2.removeAllItems();
        }
    }//GEN-LAST:event_jComboBox_pro_in_reportsItemStateChanged
    int serial = 0;
    List<String> order_ids = new ArrayList<>();
    List<String> lot_num = new ArrayList<>();
    boolean second = false;
    String name_of_type, wieght, num_of_shikra;
    JTable first_table;
    List<String> ne = new ArrayList<>();


    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        if (jTable3.getRowCount() >= 0 && !jTextField6.getText().isBlank() && jTable3.getRowCount() == (int) ToDoubleEnglish(jTextField5.getText())) {

            if (JOptionPane.showConfirmDialog(null, "<html><body><h1  style='font-family: Arial; font-size: 20pt; text-align: right; width: 150px;'>  سيتم التصدير للأكسل </h1></body></html>", "تنبيه",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                print_shit opject = new print_shit();
                if (Integer.parseInt(jTextField5.getText()) <= 60 && jCheckBox1.isSelected()) {
                    if (!second) {

                        name_of_type = jComboBox_pro_in_reports.getSelectedItem().toString();
                        wieght = ToDoubleEnglish(total_weight.getText()) + "";
                        num_of_shikra = ToDoubleEnglish(jTextField5.getText()) + "";
                        ne = new ArrayList<>(order_ids);

                        first_table = new JTable();
                        String[] columnNames = {"مسلسل", "وزن", "لوط", "رقم البالتة"};
                        DefaultTableModel d = new DefaultTableModel(columnNames, 0);
                        for (int i = 0; i < jTable3.getRowCount(); i++) {
                            Object[] row = {ToDoubleEnglish(jTable3.getValueAt(i, 0) + ""), ToDoubleEnglish(jTable3.getValueAt(i, 1) + ""), ToStringEnglish(jTable3.getValueAt(i, 2) + ""), ToDoubleEnglish(jTable3.getValueAt(i, 3) + "")};
                            d.addRow(row);
                        }
                        first_table.setModel(d);
                        ((DefaultTableModel) jTable3.getModel()).setRowCount(0);
                        jTextField5.setText("");

                        total_weight.setText("");
                        jComboBox2.removeAllItems();

                        JOptionPane.showMessageDialog(null, "<html><body><h1  style='font-family: Arial; font-size: 20pt; text-align: right; width: 150px;'> ادخل الأذن الثاني  </h1></body></html>", "إنتبه", JOptionPane.PLAIN_MESSAGE);

                    } else {
                        if (name_of_type == jComboBox_pro_in_reports.getSelectedItem() && first_table.getValueAt(0, 2).toString().equals(ToStringEnglish(jTable3.getValueAt(0, 2).toString()))) {
                            JOptionPane.showMessageDialog(null, "<html><body><h1  style='font-family: Arial; font-size: 20pt; text-align: right; width: 150px;'> برجاء تغير الصنف أو اللوط  </h1></body></html>", "إنتبه", JOptionPane.PLAIN_MESSAGE);

                            second = !second;
                        } else {
                            opject.excel_60_60(serial, order_ids, ne, total_weight, jTextField6, jComboBox_pro_in_reports, jTable3, first_table, jTextField5, num_of_shikra, wieght, name_of_type, opj, jFileChooser1);
                            jCheckBox1.setSelected(false);
                            jTextField6.setText("");
                            jTextField5.setText("");
                        }
                    }
                    second = !second;
                } else if (ToDoubleEnglish(jTextField5.getText()) <= 120) {
                    opject.excel_120(serial, order_ids, total_weight, jTextField6, jComboBox_pro_in_reports, jTable3, jTextField5, opj, jFileChooser1);
                    jTextField6.setText("");
                    jTextField5.setText("");
                } else if (ToDoubleEnglish(jTextField5.getText()) <= 160) {
                    opject.excel_160(serial, order_ids, total_weight, jTextField6, jComboBox_pro_in_reports, jTable3, jTextField5, opj, jFileChooser1);
                    jTextField6.setText("");
                    jTextField5.setText("");
                }
                jComboBox2.removeAllItems();

            }
        } else {
            JOptionPane.showMessageDialog(null, "<html><body><h1  style='font-family: Arial; font-size: 20pt; text-align: right; width: 150px;'> تدخل البيانات كامله أولا  </h1></body></html>", "إنتبه", JOptionPane.PLAIN_MESSAGE);
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyTyped
        textbox_number(evt, jTextField5, 3);
    }//GEN-LAST:event_jTextField5KeyTyped

    private void jTextField5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyReleased
        if (jComboBox_pro_in_reports.getSelectedIndex() != -1) {
            ((DefaultTableModel) jTable4.getModel()).setRowCount(0);
            ResultSet st = opj.dataRead("count(*),sum(weight_),lot,pallet_numb,used", "storage", "pro_id=(select pro_id from products where pro_name=N'" + jComboBox_pro_in_reports.getSelectedItem().toString() + "' )  GROUP BY lot,pallet_numb,used");
            try {
                while (st.next()) {
                    ((DefaultTableModel) jTable4.getModel()).addRow(new Object[]{ToDoubleArabic(st.getString(1)), ToDoubleArabic(st.getString(2)), ToDoubleArabic(st.getString(3)), ToDoubleArabic(st.getString(4)), ToDoubleArabic(st.getString(5))});
                }
            } catch (SQLException ex) {
                Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex.getMessage(), "إنتبه", JOptionPane.ERROR_MESSAGE);
            }

            ((DefaultTableModel) jTable3.getModel()).setRowCount(0);
            serial = 0;
            order_ids.clear();
            total_weight.setText("");
        }

    }//GEN-LAST:event_jTextField5KeyReleased

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        open_panel(querys);
        File[] files = new File(System.getProperty("user.dir") + "\\querys").listFiles();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        ArrayList<String> results = new ArrayList<>();
        int num = 0;
        for (File file : files) {
            if (file.isFile()) {
                if (file.getName().length() < 8 || file.getName().contains("~$")) {
                    continue;
                }
                results.add(file.getName());
                model.addRow(new Object[]{++num, file.getName()});
            }
        }
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            jTable1.setValueAt(results.get(i).substring(results.get(i).length() - 15, results.get(i).length() - 5), i, 2);
            jTable1.setValueAt(results.get(i).substring(0, results.get(i).length() - 15), i, 1);
        }
        jTable1.setAutoCreateRowSorter(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
            File file = new File(System.getProperty("user.dir") + "\\querys\\" + jTable1.getValueAt(jTable1.getSelectedRow(), 1) + jTable1.getValueAt(jTable1.getSelectedRow(), 2) + ".xlsx");
            Desktop desktop = Desktop.getDesktop();
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage(), "إنتبه", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTextField5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField5FocusLost
        if (!jTextField5.getText().isBlank())
            if (ToDoubleEnglish(jTextField5.getText()) > 160) {
                jTextField5.setText("١٦٠");
            }
    }//GEN-LAST:event_jTextField5FocusLost

    private void total_weightKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_total_weightKeyTyped
        evt.consume();
    }//GEN-LAST:event_total_weightKeyTyped

    private void total_weightKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_total_weightKeyPressed
        evt.consume();
    }//GEN-LAST:event_total_weightKeyPressed

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
        try {
            Double weight_sum = 0.0;
            if (!jTextField5.getText().isBlank()) {
                if ((int) ToDoubleEnglish(jTextField5.getText()) != jTable3.getRowCount()) {
                    boolean loot = true;
                    if (jTable3.getRowCount() > 0) {
                        if (!jTable3.getValueAt(0, 2).toString().equals(jTable4.getValueAt(jTable4.getSelectedRow(), 2))) {
                            loot = false;
                        }
                    }
                    if (loot) {
                        if (JOptionPane.showConfirmDialog(null, "<html><body><h1  style='font-family: Arial; font-size: 20pt; text-align: right; width: 150px;'>هل تريد إضافه البالته رقم  " + jTable4.getValueAt(jTable4.getSelectedRow(), 3) + "</h1></body></html>", "تنبيه",
                                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                            int num_of_order = (int) ToDoubleEnglish(jTextField5.getText());
                            if (num_of_order > 0 && serial < num_of_order) {
                                ResultSet st = opj.dataRead(" TOP (" + (num_of_order - serial) + ") weight_,lot,pallet_numb,storage_id", "storage", " pro_id=(select pro_id from products where pro_name=N'" + jComboBox_pro_in_reports.getSelectedItem().toString() + "' ) and "
                                        + "pallet_numb=" + ToDoubleEnglish(jTable4.getModel().getValueAt(jTable4.getSelectedRow(), 3).toString()) + " "
                                        + "and lot=N'" + ToStringEnglish(jTable4.getModel().getValueAt(jTable4.getSelectedRow(), 2).toString()) + "'  order by pallet_numb ,storage_id DESC ");
                                int numm = serial;
                                try {
                                    while (st.next()) {
                                        weight_sum += Double.parseDouble(st.getString(1));
                                        ((DefaultTableModel) jTable3.getModel()).addRow(new Object[]{ToDoubleArabic(++serial + ""), ToDoubleArabic(st.getString(1)), ToDoubleArabic(st.getString(2)), ToDoubleArabic(st.getString(3))});
                                        order_ids.add(st.getString(4));
                                        if (((DefaultComboBoxModel) jComboBox2.getModel()).getIndexOf(ToDoubleArabic(st.getString(3))) == -1) {
                                            jComboBox2.addItem(ToDoubleArabic(st.getString(3)));
                                        }
                                    }
                                } catch (SQLException ex) {
                                    JOptionPane.showMessageDialog(null, ex, "إنتبه", JOptionPane.PLAIN_MESSAGE);
                                }
                                double ss;
                                ss = weight_sum;
                                if (!total_weight.getText().isEmpty()) {
                                    ss += ToDoubleEnglish(total_weight.getText());
                                }
                                total_weight.setText(ToDoubleArabic(new DecimalFormat("##.##").format(ss) + ""));
                                if (num_of_order >= ToDoubleEnglish(jTable4.getModel().getValueAt(jTable4.getSelectedRow(), 0).toString()) && jTable3.getRowCount() < num_of_order) {
                                    ((DefaultTableModel) jTable4.getModel()).removeRow(jTable4.getSelectedRow());
                                } else {
                                    DecimalFormat df = new DecimalFormat("#.###");
                                    st = opj.dataRead("TOP (" + (num_of_order - numm) + ") weight_ ", "storage", " pro_id=(select pro_id from products where pro_name=N'" + jComboBox_pro_in_reports.getSelectedItem().toString() + "' ) and "
                                            + "pallet_numb=" + ToDoubleEnglish(jTable4.getModel().getValueAt(jTable4.getSelectedRow(), 3).toString()) + " "
                                            + "and lot=N'" + ToStringEnglish(jTable4.getModel().getValueAt(jTable4.getSelectedRow(), 2).toString()) + "'   order by pallet_numb ,storage_id DESC ");
                                    try {
                                        while (st.next()) {
                                            jTable4.getModel().setValueAt(ToDoubleArabic(df.format(ToDoubleEnglish(jTable4.getModel().getValueAt(jTable4.getSelectedRow(), 1).toString()) - Double.parseDouble(st.getString(1)))), jTable4.getSelectedRow(), 1);
                                        }
                                    } catch (SQLException ex) {
                                        JOptionPane.showMessageDialog(null, ex, "إنتبه", JOptionPane.PLAIN_MESSAGE);
                                    }
                                    jTable4.getModel().setValueAt(ToDoubleArabic((Integer.parseInt(jTable4.getModel().getValueAt(jTable4.getSelectedRow(), 0).toString()) - (num_of_order - numm)) + ""), jTable4.getSelectedRow(), 0);
                                    if (ToDoubleEnglish(jTable4.getValueAt(jTable4.getSelectedRow(), 0).toString()) == 0.0 && ToDoubleEnglish(jTable4.getValueAt(jTable4.getSelectedRow(), 1).toString()) == 0.0) {
                                        ((DefaultTableModel) jTable4.getModel()).removeRow(jTable4.getSelectedRow());
                                    }
                                }
                            }
                            jTable3.changeSelection(jTable3.getRowCount() - 1, 0, false, false);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "<html><body><h1  style='font-family: Arial; font-size: 20pt; text-align: right; width: 150px;'>  لا يمكن ادخال اكثر من لوط  </h1></body></html>", "إنتبه", JOptionPane.PLAIN_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "<html><body><h1  style='font-family: Arial; font-size: 20pt; text-align: right; width: 150px;'> لقد اكتمل العدد  </h1></body></html>", "إنتبه", JOptionPane.PLAIN_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "<html><body><h1  style='font-family: Arial; font-size: 20pt; text-align: right; width: 150px;'>  برجاء ادخال عدد الشكاير </h1></body></html>", "إنتبه", JOptionPane.PLAIN_MESSAGE);
            }
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "555 Ex");
        }
    }//GEN-LAST:event_jTable4MouseClicked

    private void jTable_storageKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable_storageKeyTyped
        if (evt.getKeyChar() == KeyEvent.VK_DELETE) {
            jButton_del_data.doClick();
        }
    }//GEN-LAST:event_jTable_storageKeyTyped

    private void jTable_proKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable_proKeyTyped
        if (evt.getKeyChar() == KeyEvent.VK_DELETE) {
            jButton6.doClick();
        }
    }//GEN-LAST:event_jTable_proKeyTyped

    private void jComboBox_pro_in_storageKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox_pro_in_storageKeyTyped
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            jButton_add_data.doClick();
        }
    }//GEN-LAST:event_jComboBox_pro_in_storageKeyTyped

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        jFileChooser1.showSaveDialog(this);
        if (jFileChooser1.getSelectedFile() != null) {
            if (jFileChooser1.getSelectedFile().getName().endsWith(".bak")
                    || jFileChooser1.getSelectedFile().getName().endsWith(".tm")) {

                opj.backup(jFileChooser1.getSelectedFile().getAbsolutePath() + " " + LocalDate.now() + ".tm");
                JOptionPane.showMessageDialog(null, "<html><body><h1  style='font-family: Arial; font-size: 20pt; text-align: right; width: 150px;'>  Back up succes </h1></body></html>", "3aaash", JOptionPane.INFORMATION_MESSAGE);
            } else {
                opj.backup(jFileChooser1.getSelectedFile().getAbsolutePath() + " " + LocalDate.now() + ".bak");
                JOptionPane.showMessageDialog(null, "<html><body><h1  style='font-family: Arial; font-size: 20pt; text-align: right; width: 150px;'>  Back up succes </h1></body></html>", "3aaash", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTextField6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyTyped
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            jButton4.doClick();
        }
    }//GEN-LAST:event_jTextField6KeyTyped

    private void jTextField6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField6FocusGained
        jTextField6.selectAll();
    }//GEN-LAST:event_jTextField6FocusGained

    private void jTextField_pallet_numFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_pallet_numFocusGained
        jTextField_pallet_num.selectAll();
    }//GEN-LAST:event_jTextField_pallet_numFocusGained

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        open_panel(stock);
        ((DefaultTableModel) jTable2.getModel()).setRowCount(0);
        combox_fill(jComboBox1, opj.dataRead("pro_name", "products"), true);
        jComboBox1.setSelectedIndex(-1);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        if (jComboBox1.getSelectedIndex() != -1 && jComboBox1.hasFocus()) {
            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            model.setRowCount(0);
            ResultSet st = opj.dataRead("lot ,COUNT( distinct pallet_numb)  ,count(weight_),SUM(weight_)", "storage", "pro_id=(select pro_id from products where pro_name=N'" + jComboBox1.getSelectedItem() + "')  group by lot");
            try {
                while (st.next()) {
                    model.addRow(new Object[]{ToDoubleArabic(st.getString(1)), ToDoubleArabic(st.getString(2)), ToDoubleArabic(st.getString(3)), ToDoubleArabic(st.getString(4))});
                }
            } catch (SQLException ex) {
                Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex.getMessage(), "إنتبه", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jTextField_num_of_conFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_num_of_conFocusGained
        jTextField_num_of_con.selectAll();
    }//GEN-LAST:event_jTextField_num_of_conFocusGained

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        open_panel(statistics);
        ((DefaultTableModel) jTable5.getModel()).setRowCount(0);
        jTable5.setAutoCreateRowSorter(true);
        jDateChooser1.setCalendar(null);
        jDateChooser2.setCalendar(null);
        combox_fill(jComboBox3, opj.dataRead("pro_name", "products"), true);
        jComboBox3.setSelectedIndex(-1);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jComboBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox3ItemStateChanged
        if (jComboBox3.getSelectedIndex() != -1 && jComboBox3.hasFocus()) {
            fill_jtable5();
        }
    }//GEN-LAST:event_jComboBox3ItemStateChanged

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        open_panel(yomia);
        jDateChooser3.setCalendar(null);
        jDateChooser4.setCalendar(null);
        ((DefaultTableModel) jTable6.getModel()).setRowCount(0);
        jTable6.setAutoCreateRowSorter(true);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        if (jDateChooser3.getCalendar() != null && jDateChooser4.getCalendar() != null) {
            DefaultTableModel model = (DefaultTableModel) jTable6.getModel();
            model.setRowCount(0);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date1 = sdf.format(jDateChooser3.getCalendar().getTime());
            String date2 = sdf.format(jDateChooser4.getCalendar().getTime());
            ResultSet st = opj.dataRead("sum(weight_),pro_name,cli_name,lot,FORMAT (exported_date, 'yyyy-MM-dd'),count(weight_)",
                    "export inner join clients on clients.cli_id=export.cli_id inner join products on products.pro_id=export.pro_id",
                    "  exported_date between '" + date1 + "' and '" + date2 + "' "
                    + " group by products.pro_name,clients.cli_name,lot,exported_date order by exported_date ,cli_name ");

            try {
                while (st.next()) {
                    model.addRow(new Object[]{ToDoubleArabic(st.getString(3)), ToDoubleArabic(st.getString(2)), ToDoubleArabic(st.getString(4)), ToDoubleArabic(st.getString(6)), ToDoubleArabic(st.getString(1)), ToDoubleArabic(st.getString(5))});
                }
            } catch (SQLException ex) {
                Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex.getMessage(), "إنتبه", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jCheckBox2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCheckBox2KeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            if (!jTextField_net_weight.getText().isBlank()) {
                jButton_add_data.doClick();
            }
        }
    }//GEN-LAST:event_jCheckBox2KeyPressed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            Object[] options1 = {"<html><body><h1  style='font-family: Arial; font-size: 20pt; text-align: center; width: 80px;'>  تسجيل الخروج </h1></body></html>", "<html><body><h1  style='font-family: Arial; font-size: 20pt; text-align: center; width: 80px;'>  قفل البرنامج </h1></body></html>"};
            int result = JOptionPane.showOptionDialog(null, "<html><body><h1  style='font-family: Arial; font-size: 20pt; text-align: right; width: 150px;'> خد بالك ياجدع  </h1></body></html>", "انتبه ",
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
            JOptionPane.showMessageDialog(null, ex.getMessage(), "إنتبه", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_formWindowClosing

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        if (jButton3.isEnabled() && jButton2.isEnabled() && jButton5.isEnabled()) {
            if (jTable6.getSelectedRow() != -1) {
                if (JOptionPane.showConfirmDialog(null, "<html><body><h1  style='font-family: Arial; font-size: 20pt; text-align: right; width: 150px;'>  هل تريد استرجاع أزن " + jTable6.getValueAt(jTable6.getSelectedRow(), 0) + " لصنف" + jTable6.getValueAt(jTable6.getSelectedRow(), 1) + " في يوم" + jTable6.getValueAt(jTable6.getSelectedRow(), 5) + " </h1></body></html>", "تنبيه",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    try {
                        if (opj.dataRead("*", "export", "lot=N'" + ToStringEnglish("" + jTable6.getValueAt(jTable6.getSelectedRow(), 2)) + "' "
                                + "and pro_id=(select pro_id from products where pro_name=N'" + jTable6.getValueAt(jTable6.getSelectedRow(), 1) + "') "
                                + "and cli_id=(select top(1) cli_id from clients where cli_name=N'" + jTable6.getValueAt(jTable6.getSelectedRow(), 0) + "')"
                                + " and exported_date = '" + ToStringEnglish("" + jTable6.getValueAt(jTable6.getSelectedRow(), 5)) + "'"
                                + "  and num_of_con is not null  and pallet_numb is not null         ").next()) {

                            ResultSet s = opj.dataRead("pro_id,weight_,lot,pallet_numb,FORMAT (inserted_date, 'yyyy-MM-dd'),num_of_con,used", "export", "lot=N'" + ToStringEnglish("" + jTable6.getValueAt(jTable6.getSelectedRow(), 2)) + "' "
                                    + "and pro_id=(select pro_id from products where pro_name=N'" + jTable6.getValueAt(jTable6.getSelectedRow(), 1) + "') "
                                    + "and cli_id=(select top(1) cli_id from clients where cli_name=N'" + jTable6.getValueAt(jTable6.getSelectedRow(), 0) + "')"
                                    + " and exported_date = '" + ToStringEnglish("" + jTable6.getValueAt(jTable6.getSelectedRow(), 5)) + "'"
                                    + "  and num_of_con is not null  and pallet_numb is not null         ");

                            ArrayList<String[]> outerArr = new ArrayList<String[]>();

                            while (s.next()) {
                                String[] myString12 = {s.getString(1), s.getString(2), s.getString(3), s.getString(4), s.getString(5), s.getString(6), s.getString(7)};
                                outerArr.add(myString12);
                            }

                            for (int i = outerArr.size() - 1; i > -1; i--) {
                                String[] myString;
                                myString = outerArr.get(i);
                                opj.inData("storage", "pro_id,weight_,lot,pallet_numb,date_,num_of_con,used", "" + myString[0] + "," + myString[1] + ",N'" + myString[2] + "'," + myString[3] + ",'" + myString[4] + "'," + myString[5] + "," + myString[6] + " ");

                            }

                            opj.delData("export", "lot=N'" + ToStringEnglish("" + jTable6.getValueAt(jTable6.getSelectedRow(), 2)) + "' "
                                    + "and pro_id=(select pro_id from products where pro_name=N'" + jTable6.getValueAt(jTable6.getSelectedRow(), 1) + "') "
                                    + "and cli_id=(select top(1) cli_id from clients where cli_name=N'" + jTable6.getValueAt(jTable6.getSelectedRow(), 0) + "')"
                                    + " and exported_date = '" + ToStringEnglish("" + jTable6.getValueAt(jTable6.getSelectedRow(), 5)) + "'"
                                    + "  and num_of_con is not null  and pallet_numb is not null         ");

                            if (!opj.dataRead("*", "export", "lot=N'" + ToStringEnglish("" + jTable6.getValueAt(jTable6.getSelectedRow(), 2)) + "' "
                                    + "and pro_id=(select pro_id from products where pro_name=N'" + jTable6.getValueAt(jTable6.getSelectedRow(), 1) + "') "
                                    + "and cli_id=(select top(1) cli_id from clients where cli_name=N'" + jTable6.getValueAt(jTable6.getSelectedRow(), 0) + "')"
                                    + " and exported_date = '" + ToStringEnglish("" + jTable6.getValueAt(jTable6.getSelectedRow(), 5)) + "'"
                                    + "  and num_of_con is not null  and pallet_numb is not null         ").next()) {

                                JOptionPane.showMessageDialog(null, "<html><body><h1  style='font-family: Arial; font-size: 20pt; text-align: right; width: 150px;'> تم استرجاع البيان بنجاح  </h1></body></html>", "إنتبه", JOptionPane.PLAIN_MESSAGE);
                            } else {

                                JOptionPane.showMessageDialog(null, "<html><body><h1  style='font-family: Arial; font-size: 20pt; text-align: right; width: 150px;'> حدث خطأ ما  </h1></body></html>", "إنتبه", JOptionPane.PLAIN_MESSAGE);
                            }

                            jButton11.doClick();
                        } else {
                            JOptionPane.showMessageDialog(null, "<html><body><h1  style='font-family: Arial; font-size: 20pt; text-align: right; width: 150px;'>  لا يمكن استرجاع البيان </h1></body></html>", "إنتبه", JOptionPane.PLAIN_MESSAGE);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "إنتبه", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "<html><body><h1  style='font-family: Arial; font-size: 20pt; text-align: right; width: 150px;'> يجب اختيار بيان من الجدول </h1></body></html>", "إنتبه", JOptionPane.PLAIN_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "<html><body><h1  style='font-family: Arial; font-size: 20pt; text-align: right; width: 150px;'>  لا يمكن اجراء العمليه </h1></body></html>", "إنتبه", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jFrame1WindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_jFrame1WindowClosing
        this.setEnabled(true);
    }//GEN-LAST:event_jFrame1WindowClosing

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
                JOptionPane.showMessageDialog(null, ex.getMessage(), "إنتبه", JOptionPane.ERROR_MESSAGE);
            }
            if (num_of_shikra1 < 20) {

                opj.update("storage",
                        "weight_= " + ToStringEnglish(jTextField_E_Weight.getText()) + ","
                        + "num_of_con= " + ToStringEnglish(jTextField_E_ConNum.getText()) + " ,"
                        + " lot= N'" + ToStringEnglish(jTextField_E_lot.getText()) + "',"
                        + "pallet_numb=" + ToStringEnglish(jTextField_E_PaltNum.getText()) + ","
                        + " pro_id=(select pro_id from products where pro_name=N'" + jComboBox_E_proName.getSelectedItem().toString() + "'),"
                        + "used=" + (jCheckBox_E_Mark.isSelected() ? 1 : 0) + " ",
                        "storage_id=" + jTable_storage.getModel().getValueAt(jTable_storage.getSelectedRow(), 4).toString() + "");
                JOptionPane.showMessageDialog(null, "<html><body><h1  style='font-family: Arial; font-size: 20pt; text-align: right; width: 150px;'> تم تعديل البيانات بنجاح  </h1></body></html>", "إنتبه", JOptionPane.PLAIN_MESSAGE);
                this.setEnabled(true);
                fill_storage_table();
                jFrame1.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "<html><body><h1  style='font-family: Arial; font-size: 20pt; text-align: right; width: 150px;'>  هذه البالته ممتلئه </h1></body></html>", "إنتبه", JOptionPane.PLAIN_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "<html><body><h1  style='font-family: Arial; font-size: 20pt; text-align: right; width: 150px;'>  برجاء ادخال البيانات صحيحه </h1></body></html>", "إنتبه", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_jButton_E_EditActionPerformed

    private void jTextField_E_WeightKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_E_WeightKeyTyped
        textbox_number_weight(evt, jTextField_E_Weight, 999);
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            jButton_E_Edit.doClick();
        }
    }//GEN-LAST:event_jTextField_E_WeightKeyTyped

    private void jTextField_E_PaltNumKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_E_PaltNumKeyTyped
        textbox_number(evt, jTextField_E_PaltNum, 999);
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            jButton_E_Edit.doClick();
        }
    }//GEN-LAST:event_jTextField_E_PaltNumKeyTyped

    private void jTextField_E_ConNumKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_E_ConNumKeyTyped
        textbox_number(evt, jTextField_E_ConNum, 999);
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            jButton_E_Edit.doClick();
        }
    }//GEN-LAST:event_jTextField_E_ConNumKeyTyped

    private void jTextField_E_lotKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_E_lotKeyTyped
        textbox_length_limiter(evt, jTextField_E_lot, 5);
        char input = evt.getKeyChar();
        if (Character.isDigit(input)) {
            evt.setKeyChar(ToNumArab(input));
        }
        if ((!Character.isDigit(input)) && input != 'أ' && input != 'س' && input != 'و' && input != 'د' && input != 'ط' && input != 'ب' && input != 'ا' && input != 'ع' && input != 'ه' && input != 'م' && input != 'ن') {
            evt.consume();
        }
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            jButton_E_Edit.doClick();
        }
    }//GEN-LAST:event_jTextField_E_lotKeyTyped

    private void jTextField_E_WeightFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_E_WeightFocusGained
        jTextField_E_Weight.selectAll();
    }//GEN-LAST:event_jTextField_E_WeightFocusGained

    private void jTextField_E_PaltNumFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_E_PaltNumFocusGained
        jTextField_E_PaltNum.selectAll();
    }//GEN-LAST:event_jTextField_E_PaltNumFocusGained

    private void jTextField_E_ConNumFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_E_ConNumFocusGained
        jTextField_E_ConNum.selectAll();
    }//GEN-LAST:event_jTextField_E_ConNumFocusGained

    private void jTextField_E_lotFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_E_lotFocusGained
        jTextField_E_lot.selectAll();
    }//GEN-LAST:event_jTextField_E_lotFocusGained

    private void jComboBox_E_proNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox_E_proNameKeyTyped
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            jButton_E_Edit.doClick();
        }
    }//GEN-LAST:event_jComboBox_E_proNameKeyTyped

    private void jTextField_con_weight_addKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_con_weight_addKeyTyped
        evt.setKeyChar(ToNumArab(evt.getKeyChar()));
        textbox_number(evt, jTextField_con_weight_add, 4);
    }//GEN-LAST:event_jTextField_con_weight_addKeyTyped

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        open_panel(pause);
        jTextArea1.requestFocusInWindow();
        jTextArea1.setText("");
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jTextArea1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea1KeyTyped
        evt.setKeyChar(ToNumArab(evt.getKeyChar()));
    }//GEN-LAST:event_jTextArea1KeyTyped

    private void jTable_storageMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_storageMouseReleased
        if (evt.getClickCount() == 3 && jTable_storage.getSelectedRowCount() > 0) {
            if (jTable_storage.getSelectedRowCount() == 1) {
                jFrame1.setVisible(true);
                jFrame1.setSize(790, 370);
                this.setEnabled(false);
                try {
                    jCheckBox_E_O_Mark.setSelected(opj.dataRead("*", "storage", " used=1 and  storage_id=" + jTable_storage.getModel().getValueAt(jTable_storage.getSelectedRow(), 4).toString() + " ").next());
                    jCheckBox_E_Mark.setSelected(opj.dataRead("*", "storage", " used=1 and  storage_id=" + jTable_storage.getModel().getValueAt(jTable_storage.getSelectedRow(), 4).toString() + " ").next());
                } catch (SQLException ex) {
                    Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "إنتبه", JOptionPane.ERROR_MESSAGE);
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
                String newnum = JOptionPane.showInputDialog(null, "أدخل رقم البالتة !", "تغير رقم البالتة للمخار !", JOptionPane.QUESTION_MESSAGE);
                if (newnum.isBlank()) {
                    JOptionPane.showMessageDialog(null, "please enter a valid number", "إنتبه", JOptionPane.PLAIN_MESSAGE);
                    return;
                }
                for (int i = jTable_storage.getSelectedRows().length - 1; i > -1; i--) {
                    ResultSet st = opj.dataRead("count(*)", "storage", "lot=N'" + ToStringEnglish(jTable_storage.getModel().getValueAt(jTable_storage.getSelectedRows()[i], 2).toString()) + "' and pallet_numb=" + newnum + " and pro_id=(select pro_id from products where pro_name=N'" + jComboBox_pro_in_storage.getSelectedItem().toString() + "') ");
                    int num_of_shikra1 = 0;
                    try {
                        while (st.next()) {
                            num_of_shikra1 = st.getInt(1);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "إنتبه", JOptionPane.ERROR_MESSAGE);
                    }
                    if (num_of_shikra1 < 20) {

                        opj.update("storage",
                                "pallet_numb=" + newnum,
                                "storage_id=" + jTable_storage.getModel().getValueAt(jTable_storage.getSelectedRows()[i], 4).toString() + "");
                    } else {
                        JOptionPane.showMessageDialog(null, "<html><body><h1  style='font-family: Arial; font-size: 20pt; text-align: right; width: 150px;'>  هذه البالته ممتلئه </h1></body></html>", "إنتبه", JOptionPane.PLAIN_MESSAGE);
                        break;
                    }
                }
                fill_storage_table();
                JOptionPane.showMessageDialog(null, "<html><body><h1  style='font-family: Arial; font-size: 20pt; text-align: right; width: 150px;'> تم تعديل البيانات بنجاح  </h1></body></html>", "إنتبه", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }//GEN-LAST:event_jTable_storageMouseReleased

    private void jTextField_weightKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_weightKeyReleased
        calc_net_weight();
    }//GEN-LAST:event_jTextField_weightKeyReleased

    private void jButton_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_clearActionPerformed
        jTextField_bag_weight.setText("");
        jTextField_weight.setText("");
        jTextField_net_weight.setText("");
        jTextField_num_of_con.requestFocus();
    }//GEN-LAST:event_jButton_clearActionPerformed

    private void jTextField_bag_weightFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_bag_weightFocusGained
        jTextField_bag_weight.selectAll();
    }//GEN-LAST:event_jTextField_bag_weightFocusGained

    private void jTextField_weightFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_weightFocusGained
        jTextField_weight.selectAll();
    }//GEN-LAST:event_jTextField_weightFocusGained

    private void jButton_saveMaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_saveMaxActionPerformed
        BagMax = Integer.parseInt(jTextField_BagMax.getText());
        jTextField_BagMax.setText("" + BagMax);
        JOptionPane.showMessageDialog(null, "done", "Changed", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton_saveMaxActionPerformed

    private void jTextField_BagMaxKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_BagMaxKeyTyped
        textbox_number(evt, jTextField_BagMax, 2);
    }//GEN-LAST:event_jTextField_BagMaxKeyTyped

    private void jButton_E_printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_E_printActionPerformed
        try {
            // TODO add your handling code here:
            printex(new ArrayList<>(Arrays.asList(
                    jTextField_E_PaltNum.getText(),
                    jTextField_E_Color.getText(),
                    jComboBox_E_proName.getSelectedItem().toString(),
                    jTextField_E_lot.getText(),
                    jTextField_E_ConNum.getText(),
                    "N/A", jTextField_E_Weight.getText())), jCheckBox_E_P.isSelected(), jCheckBox_E_QR.isSelected());
        } catch (WriterException | IOException | PrinterException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "إنتبه", JOptionPane.ERROR_MESSAGE);
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

    private void jTextField_E_ColorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_E_ColorKeyTyped
        // TODO add your handling code here:
        if (Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField_E_ColorKeyTyped

    private void jButton_Max_Rest2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Max_Rest2ActionPerformed
        try {
            // TODO add your handling code here:
            tick2num = 0;
            saveTicknum("TicketNumber2.txt", tick2num);
        } catch (IOException ex) {
            Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton_Max_Rest2ActionPerformed

    private void jButton_Max_Rest1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Max_Rest1ActionPerformed
        try {
            // TODO add your handling code here:
            tick1num = 0;
            saveTicknum("TicketNumber1.txt", tick1num);
        } catch (IOException ex) {
            Logger.getLogger(mainform.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_Max_Rest1ActionPerformed

    private void jButton_bagmaxActionPerformed(java.awt.event.ActionEvent evt) {
        open_panel(Maximum);
        jTextField_BagMax.setText("" + BagMax);
        jLabel_tick1.setText("" + tick1num);
        jLabel_tick2.setText("" + tick2num);
    }

    void open_panel(JPanel panel) {
        left_panel.removeAll();
        left_panel.add(panel);
        left_panel.revalidate();
        left_panel.repaint();
    }

    void textbox_number_weight(KeyEvent event, JTextField textboxname, int length) {
        if (event.getKeyChar() == KeyEvent.VK_DELETE) {
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
        if (b2) {
            print_shit.generateQRcode("{\n \"الصنف\": " + values.get(2) + " ,\n\"اللوط\": " + values.get(3) + " ,\n\"الوزن الصافي\": " + values.get(6) + "\n}", ToDoubleEnglish(values.get(6)) + "", 30, 30);
            jLabel_QR.setIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\Temp\\QR" + ToDoubleEnglish(values.get(6)) + ".png"));

            PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
            attributes.add(new MediaPrintableArea(0, 0, 20, 20, MediaPrintableArea.MM));
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
                Graphics2D g2 = (Graphics2D) pg;
                g2.scale(1.3, 1.3);
                jLabel_QR.paint(g2);
                return Printable.PAGE_EXISTS;
            });
            job.print(attributes);
            tick2num++;
            new File(System.getProperty("user.dir") + "\\Temp\\QR" + ToDoubleEnglish(values.get(6)) + ".png").delete();
            jLabel_QR.setIcon(null);
        }
        if (b1) {
            XSSFWorkbook workbook;
            try (FileInputStream EX = new FileInputStream(new File("Ticket.xlsx"))) {
                workbook = new XSSFWorkbook(EX);
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

            try (FileOutputStream fileOut = new FileOutputStream(System.getProperty("user.dir") + "\\Temp\\myFile.xlsx")) {
                workbook.write(fileOut);
            }
            Desktop.getDesktop().print(new File(System.getProperty("user.dir") + "\\Temp\\myFile.xlsx"));
            tick1num++;
        }

        if (b1 || b2) {
            return true;
        } else {
            return false;
        }
    }

    void textbox_length_limiter(KeyEvent event, JTextField textboxname, int length) {
        if (textboxname.getText().length() > length - 1 && event.getKeyChar() != KeyEvent.VK_ENTER && event.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
            event.consume();
        }
        if (event.getKeyChar() == KeyEvent.VK_DELETE) {
            textboxname.setText("");
        }
    }

    void textbox_number(KeyEvent event, JTextField textboxname, int length) {
        if (event.getKeyChar() == KeyEvent.VK_DELETE) {
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

    private void combox_fill(JComboBox comboxname, ResultSet st, boolean new_or_add) {
        if (new_or_add) {
            comboxname.removeAllItems();
        }
        try {
            while (st.next()) {
                comboxname.addItem(st.getString(1));
            }
        } catch (SQLException ex) {
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
            JOptionPane.showMessageDialog(null, ex.getMessage(), "إنتبه", JOptionPane.ERROR_MESSAGE);
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
        ResultSet st = opj.dataRead("weight_,num_of_con,lot,pallet_numb,storage_id,used", "storage ",
                "storage.pro_id=(select pro_id from products where pro_name=N'" + jComboBox_pro_in_storage.getSelectedItem() + "')  order by lot DESC, pallet_numb DESC ,storage_id DESC ,used ");
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
            JOptionPane.showMessageDialog(null, ex.getMessage(), "إنتبه", JOptionPane.ERROR_MESSAGE);
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
                JOptionPane.showMessageDialog(null, ex.getMessage(), "إنتبه", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    void fill_jtable5() {
        if (jDateChooser1.getCalendar() != null && jDateChooser2.getCalendar() != null) {
            DefaultTableModel model = (DefaultTableModel) jTable5.getModel();
            model.setRowCount(0);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date1 = sdf.format(jDateChooser1.getCalendar().getTime());
            String date2 = sdf.format(jDateChooser2.getCalendar().getTime());
            ResultSet st = opj.dataRead("lot,count(weight_),Sum(weight_)", "storage", "pro_id=(select  pro_id from products where pro_name=N'" + jComboBox3.getSelectedItem() + "') and (date_ between '" + date1 + "'  and '" + date2 + "')  group by lot");
            try {
                while (st.next()) {
                    model.addRow(new Object[]{ToDoubleArabic(st.getString(1)), ToDoubleArabic(st.getString(2)), ToDoubleArabic(st.getString(3))});
                }
            } catch (SQLException ex) {
                Logger.getLogger(mainform.class
                        .getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex.getMessage(), "إنتبه", JOptionPane.ERROR_MESSAGE);
            }
            st = opj.dataRead("lot,count(weight_),Sum(weight_)", "export", "pro_id=(select  pro_id from products where pro_name=N'" + jComboBox3.getSelectedItem() + "') and (inserted_date between '" + date1 + "'  and '" + date2 + "')  group by lot");
            try {
                while (st.next()) {
                    model.addRow(new Object[]{ToDoubleArabic(st.getString(1)), ToDoubleArabic(st.getString(2)), ToDoubleArabic(st.getString(3))});
                }
            } catch (SQLException ex) {
                Logger.getLogger(mainform.class
                        .getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex.getMessage(), "إنتبه", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    int loadTicknum(String filename) throws FileNotFoundException, IOException {
        String num;
        int nums, i;
        FileReader fr = new FileReader(System.getProperty("user.dir") + "\\Temp\\" + filename);
        num = "";
        while ((i = fr.read()) != -1) {
            num += Character.digit(i, 10) + "";
        }
        nums = Integer.parseInt((num.isBlank() ? "0" : num));
        fr.close();
        return nums;
    }

    int saveTicknum(String filename, int nums) throws FileNotFoundException, IOException {
        FileWriter fw = new FileWriter(System.getProperty("user.dir") + "\\Temp\\" + filename);
        fw.write(nums + "");
        fw.close();
        return nums;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Maximum;
    private javax.swing.JPanel add_product;
    private javax.swing.JPanel in_data;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JButton jButton_E_Edit;
    private javax.swing.JButton jButton_E_print;
    private javax.swing.JButton jButton_Max_Rest1;
    private javax.swing.JButton jButton_Max_Rest2;
    private javax.swing.JButton jButton_add_data;
    private javax.swing.JButton jButton_add_pro;
    private javax.swing.JButton jButton_clear;
    private javax.swing.JButton jButton_del_data;
    private javax.swing.JButton jButton_del_pro;
    private javax.swing.JButton jButton_saveMax;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox_E_Mark;
    private javax.swing.JCheckBox jCheckBox_E_O_Mark;
    private javax.swing.JCheckBox jCheckBox_E_P;
    private javax.swing.JCheckBox jCheckBox_E_QR;
    private javax.swing.JCheckBox jCheckBox_QR;
    private javax.swing.JCheckBox jCheckBox_print;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox_E_O_proName;
    private javax.swing.JComboBox<String> jComboBox_E_proName;
    private javax.swing.JComboBox<String> jComboBox_pro_in_reports;
    private javax.swing.JComboBox<String> jComboBox_pro_in_storage;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private com.toedter.calendar.JDateChooser jDateChooser4;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JFrame jFrame1;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_BagMax;
    private javax.swing.JLabel jLabel_QR;
    private javax.swing.JLabel jLabel_tick1;
    private javax.swing.JLabel jLabel_tick2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTable jTable_pro;
    private javax.swing.JTable jTable_storage;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField_BagMax;
    private javax.swing.JTextField jTextField_Color;
    private javax.swing.JTextField jTextField_E_Color;
    private javax.swing.JTextField jTextField_E_ConNum;
    private javax.swing.JTextField jTextField_E_O_ConNum;
    private javax.swing.JTextField jTextField_E_O_PaltNum;
    private javax.swing.JTextField jTextField_E_O_Wight;
    private javax.swing.JTextField jTextField_E_O_lot;
    private javax.swing.JTextField jTextField_E_PaltNum;
    private javax.swing.JTextField jTextField_E_Weight;
    private javax.swing.JTextField jTextField_E_lot;
    private javax.swing.JTextField jTextField_bag_weight;
    private javax.swing.JTextField jTextField_con_weight_add;
    private javax.swing.JTextField jTextField_lot;
    private javax.swing.JTextField jTextField_net_weight;
    private javax.swing.JTextField jTextField_num_of_con;
    private javax.swing.JTextField jTextField_pallet_num;
    private javax.swing.JTextField jTextField_pro_color;
    private javax.swing.JTextField jTextField_pro_name;
    private javax.swing.JTextField jTextField_weight;
    private javax.swing.JTextField jTextField_weight_of_con;
    private javax.swing.JPanel left_panel;
    private javax.swing.JTextField pallet_weight;
    private javax.swing.JPanel pause;
    private javax.swing.JPanel print;
    private javax.swing.JPanel querys;
    private javax.swing.JPanel reports;
    private javax.swing.JPanel right_panel;
    private javax.swing.JPanel statistics;
    private javax.swing.JPanel stock;
    private javax.swing.JTextField total_weight;
    private javax.swing.JPanel yomia;
    // End of variables declaration//GEN-END:variables
}

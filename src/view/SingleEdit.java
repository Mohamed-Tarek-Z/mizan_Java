package view;

import controller.StorageController;
import formController.SettingsFormController;
import formController.SingleEditFormController;
import formController.StorageFormController;
import model.Bag;
import model.Product;
import utils.ErrorListener;

public class SingleEdit extends javax.swing.JFrame {

    protected final SingleEditFormController formController;

    public SingleEdit(Bag bag, Product product, StorageController storageController, SettingsFormController settingsFormController, StorageFormController storageFormController, ErrorListener errorListener) {
        initComponents();
        formController = new SingleEditFormController(this, bag, product, jButton_E_Edit, jButton_E_print, jCheckBox_E_Mark, jCheckBox_E_O_Mark,
                jCheckBox_E_P, jComboBox_E_O_proName, jComboBox_E_proName, jTextField_E_Color, jTextField_E_ConNum, jTextField_E_O_ConNum,
                jTextField_E_O_PaltNum, jTextField_E_O_Wight, jTextField_E_O_Wight, jTextField_E_O_lot, jTextField_E_PaltNum,
                jTextField_E_TotWight, jTextField_E_Wight, jTextField_E_lot, storageController, settingsFormController, storageFormController, errorListener);
        formController.init();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Single Edit");
        setAlwaysOnTop(true);
        setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        setLocation(new java.awt.Point(100, 100));
        setLocationByPlatform(true);
        setMaximumSize(new java.awt.Dimension(780, 400));
        setMinimumSize(new java.awt.Dimension(780, 400));
        setName("Single Edit"); // NOI18N
        setPreferredSize(new java.awt.Dimension(780, 400));
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel25.setText("رقم البالته");
        getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, -1, -1));

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel26.setText("رقم اللوط");
        getContentPane().add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, -1, -1));

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel27.setText("الصنف");
        getContentPane().add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, -1, -1));

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel28.setText("عدد الكون");
        getContentPane().add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, -1, -1));

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel29.setText("الوزن");
        getContentPane().add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 10, -1, -1));

        jTextField_E_O_lot.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField_E_O_lot.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_E_O_lot.setEnabled(false);
        getContentPane().add(jTextField_E_O_lot, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 50, 90, -1));

        jTextField_E_O_ConNum.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField_E_O_ConNum.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_E_O_ConNum.setEnabled(false);
        getContentPane().add(jTextField_E_O_ConNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 50, 90, -1));

        jTextField_E_O_PaltNum.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField_E_O_PaltNum.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_E_O_PaltNum.setEnabled(false);
        getContentPane().add(jTextField_E_O_PaltNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 50, 90, -1));

        jTextField_E_O_Wight.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField_E_O_Wight.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_E_O_Wight.setEnabled(false);
        getContentPane().add(jTextField_E_O_Wight, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 50, 90, -1));

        jComboBox_E_O_proName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jComboBox_E_O_proName.setEnabled(false);
        getContentPane().add(jComboBox_E_O_proName, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, 143, -1));

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel30.setText("الوزن");
        getContentPane().add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 180, -1, -1));

        jTextField_E_lot.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField_E_lot.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(jTextField_E_lot, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 220, 90, -1));

        jTextField_E_ConNum.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField_E_ConNum.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(jTextField_E_ConNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 220, 90, -1));

        jTextField_E_PaltNum.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField_E_PaltNum.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(jTextField_E_PaltNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 220, 90, -1));

        jTextField_E_Wight.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField_E_Wight.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(jTextField_E_Wight, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 220, 90, -1));

        jComboBox_E_proName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(jComboBox_E_proName, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 220, 143, -1));

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel31.setText("رقم البالته");
        getContentPane().add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 180, -1, -1));

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel32.setText("رقم اللوط");
        getContentPane().add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 180, -1, -1));

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel33.setText("الصنف");
        getContentPane().add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 180, -1, 20));

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 51, 51));
        jLabel34.setText("عدد الكون");
        getContentPane().add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 180, -1, -1));

        jButton_E_Edit.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_E_Edit.setText("تعديل");
        getContentPane().add(jButton_E_Edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 270, 141, 39));

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel35.setText("ـــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــ");
        getContentPane().add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 750, 20));

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel36.setText("القيم الجديده");
        getContentPane().add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 130, -1, -1));

        jCheckBox_E_O_Mark.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jCheckBox_E_O_Mark.setText("تعليم الشيكاره");
        jCheckBox_E_O_Mark.setEnabled(false);
        getContentPane().add(jCheckBox_E_O_Mark, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 85, -1, -1));

        jCheckBox_E_Mark.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jCheckBox_E_Mark.setText("تعليم الشيكاره");
        getContentPane().add(jCheckBox_E_Mark, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 280, -1, -1));

        jButton_E_print.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jButton_E_print.setText("Print");
        getContentPane().add(jButton_E_print, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 100, 40));

        jTextField_E_TotWight.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(jTextField_E_TotWight, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, 90, -1));

        jLabel43.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 0, 0));
        jLabel43.setText("الوزن قائم");
        getContentPane().add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, 80, 20));

        jCheckBox_E_P.setText("print");
        getContentPane().add(jCheckBox_E_P, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, -1, -1));

        jTextField_E_Color.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(jTextField_E_Color, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 90, -1));

        jLabel44.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel44.setText("اللون");
        getContentPane().add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 50, 20));

        jTextField_E_O_TotWight.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField_E_O_TotWight.setEnabled(false);
        jTextField_E_O_TotWight.setFocusable(false);
        jTextField_E_O_TotWight.setRequestFocusEnabled(false);
        getContentPane().add(jTextField_E_O_TotWight, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 90, -1));

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel39.setText("قائم");
        getContentPane().add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, -1, -1));

        jLabel13.setForeground(new java.awt.Color(255, 0, 0));
        jLabel13.setText("الأماكن الحمراء لايمكن تعديلها في نفس العملية*");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 120, 280, 20));

        getAccessibleContext().setAccessibleParent(this);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_E_Edit;
    private javax.swing.JButton jButton_E_print;
    private javax.swing.JCheckBox jCheckBox_E_Mark;
    private javax.swing.JCheckBox jCheckBox_E_O_Mark;
    private javax.swing.JCheckBox jCheckBox_E_P;
    private javax.swing.JComboBox<Product> jComboBox_E_O_proName;
    private javax.swing.JComboBox<Product> jComboBox_E_proName;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
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
    // End of variables declaration//GEN-END:variables
}

package view;

import controller.StorageController;
import formController.MultiEditFormController;
import formController.StorageFormController;
import model.Product;
import utils.ErrorListener;

public class MultiEdit extends javax.swing.JFrame {

    protected final MultiEditFormController formController;

    public MultiEdit(Product product, String palletNumber, String lot, boolean marked, StorageController storageController,
            StorageFormController storageFormController, ErrorListener errorListener) {
        initComponents();
        formController = new MultiEditFormController(jComboBox_ME_type, jTextField_ME_PaltNum, jTextField_ME_lot,
                jCheckBox_ME_MarkBag, jButton_ME_Edit, this, product, storageFormController, storageController, errorListener,
                lot, palletNumber, marked);
        formController.init();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField_ME_lot = new javax.swing.JTextField();
        jTextField_ME_PaltNum = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jButton_ME_Edit = new javax.swing.JButton();
        jLabel53 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jComboBox_ME_type = new javax.swing.JComboBox<>();
        jCheckBox_ME_MarkBag = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField_ME_lot.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField_ME_lot.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(jTextField_ME_lot, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 90, -1));

        jTextField_ME_PaltNum.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField_ME_PaltNum.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(jTextField_ME_PaltNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 170, 90, -1));

        jLabel47.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel47.setText("رقم البالته");
        getContentPane().add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 130, -1, -1));

        jLabel48.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel48.setText("رقم اللوط");
        getContentPane().add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        jButton_ME_Edit.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_ME_Edit.setText("تعديل");
        getContentPane().add(jButton_ME_Edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 260, 170, 60));

        jLabel53.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel53.setText("القيم الجديده");
        getContentPane().add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, -1, -1));

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel21.setText("الصنف");
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, -1, -1));

        jComboBox_ME_type.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(jComboBox_ME_type, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 220, -1));

        jCheckBox_ME_MarkBag.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jCheckBox_ME_MarkBag.setText("تعليم الشائر");
        getContentPane().add(jCheckBox_ME_MarkBag, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 140, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_ME_Edit;
    private javax.swing.JCheckBox jCheckBox_ME_MarkBag;
    private javax.swing.JComboBox<Product> jComboBox_ME_type;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JTextField jTextField_ME_PaltNum;
    private javax.swing.JTextField jTextField_ME_lot;
    // End of variables declaration//GEN-END:variables
}

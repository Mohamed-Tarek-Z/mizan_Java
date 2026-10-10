package view;

import controller.ProductController;
import controller.StorageController;
import formController.SettingsFormController;
import formController.StorageFormController;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.table.TableCellRenderer;
import model.Product;
import utils.ErrorListener;
import utils.PrinterManager;

public class StoragePanel extends javax.swing.JPanel {

    protected final StorageFormController formController;

    public StoragePanel(ErrorListener errorListener, JPanel leftPanel, JPanel printPanel, SettingsFormController sfc, JButton formOpenerBtn,
            StorageController storageController, ProductController productController, PrinterManager printerManager) {
        initComponents();
        formController = new StorageFormController(errorListener, leftPanel, printPanel, sfc, this, formOpenerBtn, jTextField_storage_Color,
                jTextField_storage_PalletWeight, jTextField_storage_EmptyConeWeight, jTextField_storage_NetWeight,
                jTextField_storage_TotalWeight, jTextField_storage_EmptyBagWeight, jTextField_storage_coneNumber,
                jTextField_storage_palletNumber, jTextField_storage_lot, jTextField_storage_SearchProducts, jTextField_storage_Error,
                jLabel_storage_EmptyBag, jCheckBox_storage_printLTicket, jCheckBox_storage_ignoreLimits, jCheckBox_storage_Box,
                jCheckBox_storage_MarkBag, jCheckBox_storage_freezeConeNumber, jCheckBox_storage_FreezeEmptyBagWight,
                jCheckBox_storage_FreezeConeWeightChange, jComboBox_storage_products, jButton_storage_addData, jButton_storage_delData,
                jButton_storage_RePrintLastTicket, jButton_storage_Clear, jTable_storage, jProgressBar_storage_pallet, storageController,
                productController, printerManager);
        formController.init();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        jLabel_storage_EmptyBag = new javax.swing.JLabel();
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
        jTextField_storage_Error = new javax.swing.JTextField();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable_storage.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTable_storage.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "الوزن", "عدد الكون", "رقم اللوط", "رقم البالتة", "م", "مسلسل ", "s"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Boolean.class
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

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 590, 620));

        jButton_storage_addData.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jButton_storage_addData.setText("إضافة");
        add(jButton_storage_addData, new org.netbeans.lib.awtextra.AbsoluteConstraints(745, 590, 90, 50));

        jButton_storage_delData.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jButton_storage_delData.setText("حذف");
        add(jButton_storage_delData, new org.netbeans.lib.awtextra.AbsoluteConstraints(605, 590, 80, 50));

        jComboBox_storage_products.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        add(jComboBox_storage_products, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 100, 210, 30));

        jLabel6.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jLabel6.setText("الصنف");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 70, -1, 20));

        jTextField_storage_lot.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jTextField_storage_lot.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField_storage_lot.setMaximumSize(new java.awt.Dimension(7, 38));
        add(jTextField_storage_lot, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 140, 120, 36));

        jTextField_storage_TotalWeight.setBackground(new java.awt.Color(255, 204, 204));
        jTextField_storage_TotalWeight.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jTextField_storage_TotalWeight.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField_storage_TotalWeight.setMaximumSize(new java.awt.Dimension(7, 38));
        add(jTextField_storage_TotalWeight, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 340, 120, -1));

        jTextField_storage_EmptyBagWeight.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jTextField_storage_EmptyBagWeight.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField_storage_EmptyBagWeight.setMaximumSize(new java.awt.Dimension(7, 38));
        add(jTextField_storage_EmptyBagWeight, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 290, 120, -1));

        jTextField_storage_coneNumber.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jTextField_storage_coneNumber.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField_storage_coneNumber.setMaximumSize(new java.awt.Dimension(7, 38));
        add(jTextField_storage_coneNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 240, 120, -1));

        jLabel7.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        jLabel7.setText("الوزن القائم");
        jLabel7.setMaximumSize(new java.awt.Dimension(82, 24));
        jLabel7.setMinimumSize(new java.awt.Dimension(82, 24));
        jLabel7.setPreferredSize(new java.awt.Dimension(82, 24));
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(735, 340, -1, 30));

        jLabel8.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        jLabel8.setText("اللوط");
        jLabel8.setMaximumSize(new java.awt.Dimension(82, 24));
        jLabel8.setMinimumSize(new java.awt.Dimension(82, 24));
        jLabel8.setPreferredSize(new java.awt.Dimension(82, 24));
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 140, -1, 30));

        jLabel9.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        jLabel9.setText("عدد الكون");
        jLabel9.setMaximumSize(new java.awt.Dimension(82, 24));
        jLabel9.setMinimumSize(new java.awt.Dimension(82, 24));
        jLabel9.setPreferredSize(new java.awt.Dimension(82, 24));
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(735, 240, -1, 30));

        jLabel10.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        jLabel10.setText("وزن الكونه");
        jLabel10.setMaximumSize(new java.awt.Dimension(82, 24));
        jLabel10.setMinimumSize(new java.awt.Dimension(82, 24));
        jLabel10.setPreferredSize(new java.awt.Dimension(82, 24));
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 440, -1, 30));

        jTextField_storage_palletNumber.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jTextField_storage_palletNumber.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField_storage_palletNumber.setMaximumSize(new java.awt.Dimension(7, 38));
        add(jTextField_storage_palletNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 180, 120, -1));

        jTextField_storage_EmptyConeWeight.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jTextField_storage_EmptyConeWeight.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField_storage_EmptyConeWeight.setMaximumSize(new java.awt.Dimension(7, 38));
        add(jTextField_storage_EmptyConeWeight, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 440, 120, -1));

        jLabel_storage_EmptyBag.setFont(new java.awt.Font("sansserif", 1, 16)); // NOI18N
        jLabel_storage_EmptyBag.setText("فارغ الشيكاره");
        jLabel_storage_EmptyBag.setMaximumSize(new java.awt.Dimension(82, 24));
        jLabel_storage_EmptyBag.setMinimumSize(new java.awt.Dimension(82, 24));
        jLabel_storage_EmptyBag.setPreferredSize(new java.awt.Dimension(82, 24));
        add(jLabel_storage_EmptyBag, new org.netbeans.lib.awtextra.AbsoluteConstraints(735, 290, -1, 30));

        jLabel12.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        jLabel12.setText("الوزن الصافي");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(735, 390, -1, 30));

        jTextField_storage_NetWeight.setEditable(false);
        jTextField_storage_NetWeight.setBackground(new java.awt.Color(204, 255, 204));
        jTextField_storage_NetWeight.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jTextField_storage_NetWeight.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField_storage_NetWeight.setMaximumSize(new java.awt.Dimension(7, 38));
        add(jTextField_storage_NetWeight, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 390, 120, -1));

        jLabel2.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        jLabel2.setText("رقم البالتة");
        jLabel2.setMaximumSize(new java.awt.Dimension(82, 24));
        jLabel2.setMinimumSize(new java.awt.Dimension(82, 24));
        jLabel2.setPreferredSize(new java.awt.Dimension(82, 24));
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 180, -1, 30));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setText("وزن البالتة");
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 490, -1, 30));

        jTextField_storage_PalletWeight.setEditable(false);
        jTextField_storage_PalletWeight.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTextField_storage_PalletWeight.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        add(jTextField_storage_PalletWeight, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 490, 140, -1));

        jCheckBox_storage_MarkBag.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jCheckBox_storage_MarkBag.setText("تعليم البالته");
        jCheckBox_storage_MarkBag.setFocusable(false);
        add(jCheckBox_storage_MarkBag, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 210, -1, 20));

        jButton_storage_Clear.setBackground(new java.awt.Color(240, 0, 0));
        jButton_storage_Clear.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jButton_storage_Clear.setForeground(new java.awt.Color(255, 255, 255));
        jButton_storage_Clear.setText("X");
        jButton_storage_Clear.setToolTipText("Clear");
        jButton_storage_Clear.setBorderPainted(false);
        jButton_storage_Clear.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        jButton_storage_Clear.setFocusable(false);
        jButton_storage_Clear.setName(""); // NOI18N
        add(jButton_storage_Clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(695, 610, 40, -1));

        jCheckBox_storage_printLTicket.setSelected(true);
        jCheckBox_storage_printLTicket.setText("طباعة");
        jCheckBox_storage_printLTicket.setFocusable(false);
        add(jCheckBox_storage_printLTicket, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, -1, -1));

        jTextField_storage_Color.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField_storage_Color.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        add(jTextField_storage_Color, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 540, 140, 40));

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel41.setText("اللون");
        add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 540, 70, 40));

        jCheckBox_storage_Box.setText("صندوق");
        jCheckBox_storage_Box.setEnabled(false);
        add(jCheckBox_storage_Box, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 70, -1, -1));

        jSeparator4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(602, 232, 230, -1));
        add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 135, 210, -1));

        jSeparator6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(602, 482, 230, -1));

        jButton_storage_RePrintLastTicket.setBackground(new java.awt.Color(153, 153, 255));
        jButton_storage_RePrintLastTicket.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jButton_storage_RePrintLastTicket.setText("Print");
        jButton_storage_RePrintLastTicket.setToolTipText("Re-Print ticket");
        jButton_storage_RePrintLastTicket.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_storage_RePrintLastTicket.setFocusable(false);
        add(jButton_storage_RePrintLastTicket, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 70, 60, 20));

        jProgressBar_storage_pallet.setMaximum(20);
        jProgressBar_storage_pallet.setToolTipText("Pallet");
        jProgressBar_storage_pallet.setFocusable(false);
        jProgressBar_storage_pallet.setMaximumSize(new java.awt.Dimension(10, 14));
        jProgressBar_storage_pallet.setRequestFocusEnabled(false);
        add(jProgressBar_storage_pallet, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 2, 590, -1));

        jCheckBox_storage_freezeConeNumber.setFocusable(false);
        add(jCheckBox_storage_freezeConeNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(815, 250, -1, -1));

        jCheckBox_storage_FreezeEmptyBagWight.setFocusable(false);
        add(jCheckBox_storage_FreezeEmptyBagWight, new org.netbeans.lib.awtextra.AbsoluteConstraints(815, 300, -1, -1));

        jCheckBox_storage_FreezeConeWeightChange.setFocusable(false);
        add(jCheckBox_storage_FreezeConeWeightChange, new org.netbeans.lib.awtextra.AbsoluteConstraints(815, 450, -1, -1));

        jCheckBox_storage_ignoreLimits.setText("سماح الوزن");
        jCheckBox_storage_ignoreLimits.setFocusable(false);
        add(jCheckBox_storage_ignoreLimits, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 10, -1, -1));

        jSeparator7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jSeparator7.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jSeparator7.setMaximumSize(new java.awt.Dimension(37, 3));
        jSeparator7.setMinimumSize(new java.awt.Dimension(37, 3));
        jSeparator7.setPreferredSize(new java.awt.Dimension(37, 55));
        add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(605, 10, 110, 20));
        add(jTextField_storage_SearchProducts, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 40, 150, 20));

        jTextField_storage_Error.setEditable(false);
        jTextField_storage_Error.setBackground(new java.awt.Color(255, 255, 255));
        jTextField_storage_Error.setAutoscrolls(false);
        jTextField_storage_Error.setBorder(null);
        jTextField_storage_Error.setFocusable(false);
        jTextField_storage_Error.setRequestFocusEnabled(false);
        jTextField_storage_Error.setVerifyInputWhenFocusTarget(false);
        add(jTextField_storage_Error, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 40, 40, 30));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_storage_Clear;
    private javax.swing.JButton jButton_storage_RePrintLastTicket;
    private javax.swing.JButton jButton_storage_addData;
    private javax.swing.JButton jButton_storage_delData;
    private javax.swing.JCheckBox jCheckBox_storage_Box;
    private javax.swing.JCheckBox jCheckBox_storage_FreezeConeWeightChange;
    private javax.swing.JCheckBox jCheckBox_storage_FreezeEmptyBagWight;
    private javax.swing.JCheckBox jCheckBox_storage_MarkBag;
    private javax.swing.JCheckBox jCheckBox_storage_freezeConeNumber;
    private javax.swing.JCheckBox jCheckBox_storage_ignoreLimits;
    private javax.swing.JCheckBox jCheckBox_storage_printLTicket;
    private javax.swing.JComboBox<Product> jComboBox_storage_products;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_storage_EmptyBag;
    private javax.swing.JProgressBar jProgressBar_storage_pallet;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JTable jTable_storage;
    private javax.swing.JTextField jTextField_storage_Color;
    private javax.swing.JTextField jTextField_storage_EmptyBagWeight;
    private javax.swing.JTextField jTextField_storage_EmptyConeWeight;
    private javax.swing.JTextField jTextField_storage_Error;
    private javax.swing.JTextField jTextField_storage_NetWeight;
    private javax.swing.JTextField jTextField_storage_PalletWeight;
    private javax.swing.JTextField jTextField_storage_SearchProducts;
    private javax.swing.JTextField jTextField_storage_TotalWeight;
    private javax.swing.JTextField jTextField_storage_coneNumber;
    private javax.swing.JTextField jTextField_storage_lot;
    private javax.swing.JTextField jTextField_storage_palletNumber;
    // End of variables declaration//GEN-END:variables
}

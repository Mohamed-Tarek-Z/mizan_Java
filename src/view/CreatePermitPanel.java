package view;

import controller.ClientController;
import controller.ExportController;
import controller.OrderController;
import controller.ProductController;
import controller.StorageController;
import formController.CreatePermitFormController;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.table.TableCellRenderer;
import model.Product;
import utils.ErrorListener;
import utils.ExcelManager;

public class CreatePermitPanel extends javax.swing.JPanel {

    protected final CreatePermitFormController formController;

    public CreatePermitPanel(ErrorListener errorListener, JPanel leftPanel, JButton panelOpener, StorageController storageController, ProductController productController,
            ClientController clientController, OrderController orderController, ExportController exportController, ExcelManager excelManager) {
        initComponents();
        formController = new CreatePermitFormController(errorListener, leftPanel, this, panelOpener, jTextField_permit_clientName, jTextField_permit_numOfBag,
                jTextField_permit_Search_pros, jTextField_permit_ConeCount, jTextField_permit_totweight, jComboBox_permit_Pros,
                jComboBox_permit_palletsNrep, jLabel_Order_num, jCheckBox_permit_2n1, jCheckBox_permit_highLightMarked, jCheckBox_permit_wzn,
                jButton_permit_printRep, jButton_permit_clear, jTable_rep_preview, jTable_rep_select, storageController, productController,
                clientController, orderController, exportController, excelManager);
        formController.init();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox_permit_Pros = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField_permit_numOfBag = new javax.swing.JTextField();
        jButton_permit_printRep = new javax.swing.JButton();
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
        jTextField_permit_clientName = new javax.swing.JTextField();
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
        jTextField_permit_totweight = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel_Order_num = new javax.swing.JLabel();
        jCheckBox_permit_2n1 = new javax.swing.JCheckBox();
        jComboBox_permit_palletsNrep = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        jButton_permit_clear = new javax.swing.JButton();
        jCheckBox_permit_wzn = new javax.swing.JCheckBox();
        jTextField_permit_Search_pros = new javax.swing.JTextField();
        jCheckBox_permit_highLightMarked = new javax.swing.JCheckBox();
        jTextField_permit_ConeCount = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jComboBox_permit_Pros.setFont(new java.awt.Font("sansserif", 0, 20)); // NOI18N
        add(jComboBox_permit_Pros, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 65, 300, -1));

        jLabel1.setFont(new java.awt.Font("sansserif", 0, 20)); // NOI18N
        jLabel1.setText("الصنف");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, -1, -1));

        jLabel4.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("_________________________________________________________");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 750, 30));

        jTextField_permit_numOfBag.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField_permit_numOfBag.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        add(jTextField_permit_numOfBag, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 80, -1));

        jButton_permit_printRep.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jButton_permit_printRep.setText("طباعة");
        add(jButton_permit_printRep, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 500, 120, 50));

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

        add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 278, 610, 360));

        jTextField_permit_clientName.setFont(new java.awt.Font("sansserif", 0, 20)); // NOI18N
        jTextField_permit_clientName.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        add(jTextField_permit_clientName, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, 450, 40));

        jLabel5.setFont(new java.awt.Font("sansserif", 0, 20)); // NOI18N
        jLabel5.setText("أسم العميل");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 200, -1, -1));

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
        jScrollPane4.setViewportView(jTable_rep_select);
        if (jTable_rep_select.getColumnModel().getColumnCount() > 0) {
            jTable_rep_select.getColumnModel().getColumn(3).setMinWidth(100);
            jTable_rep_select.getColumnModel().getColumn(3).setPreferredWidth(100);
            jTable_rep_select.getColumnModel().getColumn(3).setMaxWidth(100);
            jTable_rep_select.getColumnModel().getColumn(4).setMinWidth(0);
            jTable_rep_select.getColumnModel().getColumn(4).setPreferredWidth(0);
            jTable_rep_select.getColumnModel().getColumn(4).setMaxWidth(0);
        }

        add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, 500, 170));

        jTextField_permit_totweight.setEditable(false);
        jTextField_permit_totweight.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTextField_permit_totweight.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        add(jTextField_permit_totweight, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 430, 140, 50));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("إجمالي الوزن");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 380, -1, -1));

        jLabel_Order_num.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel_Order_num.setText("عدد الشكاير");
        add(jLabel_Order_num, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, -1, -1));

        jCheckBox_permit_2n1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jCheckBox_permit_2n1.setText(" صنفين في اذن واحد");
        add(jCheckBox_permit_2n1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, -1, -1));

        jComboBox_permit_palletsNrep.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        add(jComboBox_permit_palletsNrep, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 210, 80, -1));

        jLabel16.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jLabel16.setText("رقم البالته");
        add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 210, -1, -1));

        jButton_permit_clear.setBackground(new java.awt.Color(255, 0, 0));
        jButton_permit_clear.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButton_permit_clear.setForeground(new java.awt.Color(255, 255, 255));
        jButton_permit_clear.setText("Clear All");
        add(jButton_permit_clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 580, 130, 40));

        jCheckBox_permit_wzn.setText("وزن");
        add(jCheckBox_permit_wzn, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 130, -1, -1));
        add(jTextField_permit_Search_pros, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 35, 200, -1));

        jCheckBox_permit_highLightMarked.setText("تعليم في الاذن؟");
        add(jCheckBox_permit_highLightMarked, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 240, -1, 30));

        jTextField_permit_ConeCount.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField_permit_ConeCount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_permit_ConeCount.setEnabled(false);
        add(jTextField_permit_ConeCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 330, 110, 30));

        jLabel46.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel46.setText("عدد الكون");
        add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 300, 80, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_permit_clear;
    private javax.swing.JButton jButton_permit_printRep;
    private javax.swing.JCheckBox jCheckBox_permit_2n1;
    private javax.swing.JCheckBox jCheckBox_permit_highLightMarked;
    private javax.swing.JCheckBox jCheckBox_permit_wzn;
    private javax.swing.JComboBox<Product> jComboBox_permit_Pros;
    private javax.swing.JComboBox<String> jComboBox_permit_palletsNrep;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel_Order_num;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable_rep_preview;
    private javax.swing.JTable jTable_rep_select;
    private javax.swing.JTextField jTextField_permit_ConeCount;
    private javax.swing.JTextField jTextField_permit_Search_pros;
    private javax.swing.JTextField jTextField_permit_clientName;
    private javax.swing.JTextField jTextField_permit_numOfBag;
    private javax.swing.JTextField jTextField_permit_totweight;
    // End of variables declaration//GEN-END:variables
}

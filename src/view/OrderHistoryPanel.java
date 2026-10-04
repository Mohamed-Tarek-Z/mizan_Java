package view;

import controller.ClientController;
import controller.ExportController;
import controller.ProductController;
import formController.OrderHistoryFormController;
import javax.swing.JButton;
import javax.swing.JPanel;
import model.Product;
import utils.ErrorListener;
import utils.ExcelManager;


public class OrderHistoryPanel extends javax.swing.JPanel {

    public OrderHistoryPanel(ErrorListener errorListener, JPanel leftPanel, JButton panelOpener, ExportController exportController, ProductController productController, ClientController clientController, ExcelManager excelManager) {
        initComponents();

        new OrderHistoryFormController(errorListener, leftPanel, this, panelOpener, jTextField_youm_ClientFilter,
                jTextField_youm_Search_pros, jComboBox_youm_products, jButton_youm_search, jButton_youm_refund,
                jButton_youm_getClients, jButton_youm_createExcel, jTable_yumia, jTable_youm_clinets,
                jDateChooser_youm_fromDate, jDateChooser_youm_ToDate, exportController, productController,
                clientController, excelManager).init();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane8 = new javax.swing.JScrollPane();
        jTable_yumia = new javax.swing.JTable();
        jDateChooser_youm_fromDate = new com.toedter.calendar.JDateChooser();
        jDateChooser_youm_ToDate = new com.toedter.calendar.JDateChooser();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jButton_youm_search = new javax.swing.JButton();
        jButton_youm_refund = new javax.swing.JButton();
        jTextField_youm_ClientFilter = new javax.swing.JTextField();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable_youm_clinets = new javax.swing.JTable();
        jButton_youm_getClients = new javax.swing.JButton();
        jButton_youm_createExcel = new javax.swing.JButton();
        jComboBox_youm_products = new javax.swing.JComboBox<>();
        jTextField_youm_Search_pros = new javax.swing.JTextField();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable_yumia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable_yumia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "الأسم", "الصنف", "اللوط", "عدد الشكاير", "الوزن", "التاريخ", "ordID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class
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
        jTable_yumia.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable_yumia.setColumnSelectionAllowed(true);
        jTable_yumia.setRowHeight(25);
        jTable_yumia.getTableHeader().setReorderingAllowed(false);
        jScrollPane8.setViewportView(jTable_yumia);
        jTable_yumia.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (jTable_yumia.getColumnModel().getColumnCount() > 0) {
            jTable_yumia.getColumnModel().getColumn(0).setPreferredWidth(250);
            jTable_yumia.getColumnModel().getColumn(1).setPreferredWidth(250);
            jTable_yumia.getColumnModel().getColumn(2).setPreferredWidth(50);
            jTable_yumia.getColumnModel().getColumn(3).setPreferredWidth(50);
            jTable_yumia.getColumnModel().getColumn(4).setPreferredWidth(120);
            jTable_yumia.getColumnModel().getColumn(5).setPreferredWidth(120);
            jTable_yumia.getColumnModel().getColumn(6).setMinWidth(0);
            jTable_yumia.getColumnModel().getColumn(6).setPreferredWidth(0);
            jTable_yumia.getColumnModel().getColumn(6).setMaxWidth(0);
        }

        add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 307, 810, 330));
        add(jDateChooser_youm_fromDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 180, 30));
        add(jDateChooser_youm_ToDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 220, 160, 30));

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel22.setText("من");
        add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 180, -1, -1));

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel23.setText("إلي");
        add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, -1, -1));

        jButton_youm_search.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton_youm_search.setText("بحث");
        add(jButton_youm_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 260, -1, -1));

        jButton_youm_refund.setBackground(new java.awt.Color(255, 51, 51));
        jButton_youm_refund.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jButton_youm_refund.setText("أسترجاع الأزن");
        add(jButton_youm_refund, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 230, 150, 40));

        jTextField_youm_ClientFilter.setToolTipText("client Filter");
        add(jTextField_youm_ClientFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 20, 170, 40));

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
        jTable_youm_clinets.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (jTable_youm_clinets.getColumnModel().getColumnCount() > 0) {
            jTable_youm_clinets.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTable_youm_clinets.getColumnModel().getColumn(1).setPreferredWidth(300);
        }

        add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 430, 150));

        jButton_youm_getClients.setBackground(new java.awt.Color(153, 153, 255));
        jButton_youm_getClients.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton_youm_getClients.setText("عملاء");
        add(jButton_youm_getClients, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 20, 120, 40));

        jButton_youm_createExcel.setBackground(new java.awt.Color(255, 255, 0));
        jButton_youm_createExcel.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        jButton_youm_createExcel.setText("طباعة البيان");
        add(jButton_youm_createExcel, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 230, 150, 40));

        jComboBox_youm_products.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        add(jComboBox_youm_products, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 130, 200, 30));
        add(jTextField_youm_Search_pros, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 100, 140, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_youm_createExcel;
    private javax.swing.JButton jButton_youm_getClients;
    private javax.swing.JButton jButton_youm_refund;
    private javax.swing.JButton jButton_youm_search;
    private javax.swing.JComboBox<Product> jComboBox_youm_products;
    private com.toedter.calendar.JDateChooser jDateChooser_youm_ToDate;
    private com.toedter.calendar.JDateChooser jDateChooser_youm_fromDate;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTable jTable_youm_clinets;
    private javax.swing.JTable jTable_yumia;
    private javax.swing.JTextField jTextField_youm_ClientFilter;
    private javax.swing.JTextField jTextField_youm_Search_pros;
    // End of variables declaration//GEN-END:variables
}

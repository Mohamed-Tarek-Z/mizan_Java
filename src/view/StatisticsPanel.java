package view;

import controller.ExportController;
import controller.ProductController;
import formController.StatisticsFormController;
import javax.swing.JButton;
import javax.swing.JPanel;
import model.Product;
import utils.ErrorListener;
import utils.ExcelManager;

public class StatisticsPanel extends javax.swing.JPanel {

    protected final StatisticsFormController formController;

    public StatisticsPanel(ErrorListener errorListener, JPanel leftPanel, JButton panelOpener, ExportController exportController, ProductController productController, ExcelManager excelManager) {
        initComponents();
        formController = new StatisticsFormController(errorListener, leftPanel, this, panelOpener, jTextField_statistics_Search_pros,
                jComboBox_statistics_products, jButton_statistics_search, jButton_statistics_createExcl, jTable_statis, jDateChooser_statis_fromDate, jDateChooser_statis_toDate,
                jTextField_statis_tot, exportController, productController, excelManager);
        
        formController.init();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        jButton_statistics_createExcl = new javax.swing.JButton();
        jComboBox_statistics_products = new javax.swing.JComboBox<>();
        jTextField_statistics_Search_pros = new javax.swing.JTextField();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
            jTable_statis.getColumnModel().getColumn(2).setResizable(false);
            jTable_statis.getColumnModel().getColumn(3).setResizable(false);
        }

        add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 790, 420));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel18.setText("إلي");
        add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 20, -1, -1));

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel19.setText("من");
        add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 20, -1, -1));
        add(jDateChooser_statis_fromDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 70, 140, 30));
        add(jDateChooser_statis_toDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 70, 140, 30));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel20.setText("التاريخ:");
        add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 30, -1, -1));

        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel42.setText("المجموع");
        add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 580, -1, -1));

        jTextField_statis_tot.setEditable(false);
        jTextField_statis_tot.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        add(jTextField_statis_tot, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 572, 200, 50));

        jButton_statistics_search.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_statistics_search.setText("بحث");
        add(jButton_statistics_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 85, 80, 40));

        jButton_statistics_createExcl.setText("Create Excel");
        add(jButton_statistics_createExcl, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 590, -1, -1));

        jComboBox_statistics_products.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        add(jComboBox_statistics_products, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 190, 30));
        add(jTextField_statistics_Search_pros, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 40, 140, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_statistics_createExcl;
    private javax.swing.JButton jButton_statistics_search;
    private javax.swing.JComboBox<Product> jComboBox_statistics_products;
    private com.toedter.calendar.JDateChooser jDateChooser_statis_fromDate;
    private com.toedter.calendar.JDateChooser jDateChooser_statis_toDate;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTable jTable_statis;
    private javax.swing.JTextField jTextField_statis_tot;
    private javax.swing.JTextField jTextField_statistics_Search_pros;
    // End of variables declaration//GEN-END:variables
}

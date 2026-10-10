package view;

import controller.ProductController;
import controller.StorageController;
import formController.StockFormController;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.table.TableCellRenderer;
import model.Product;
import utils.ErrorListener;
import utils.ExcelManager;

public class StockPanel extends javax.swing.JPanel {

    protected final StockFormController formController;

    public StockPanel(ErrorListener errorListener, JPanel leftPanel, JButton panelOpener, StorageController storageController, ProductController productController, ExcelManager excelManager) {
        initComponents();
        formController = new StockFormController(errorListener, leftPanel, this, panelOpener, jTextField_stock_SearchProducts,
                jButton_stock_createExcl, jTable_stock, jComboBox_stock_Pros, storageController, productController, excelManager);
        formController.init();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox_stock_Pros = new javax.swing.JComboBox<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable_stock = new javax.swing.JTable(){
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

        };
        jLabel15 = new javax.swing.JLabel();
        jButton_stock_createExcl = new javax.swing.JButton();
        jTextField_stock_SearchProducts = new javax.swing.JTextField();

        setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        setMaximumSize(new java.awt.Dimension(835, 640));
        setMinimumSize(new java.awt.Dimension(835, 640));
        setPreferredSize(new java.awt.Dimension(835, 640));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jComboBox_stock_Pros.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        add(jComboBox_stock_Pros, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 64, 433, -1));

        jScrollPane6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jTable_stock.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTable_stock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "اللوط", "عدد البالت", "عدد الشكاير", "الوزن الأجمالي", "s"
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
        jTable_stock.setRowHeight(25);
        jTable_stock.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable_stock.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable_stock.setShowGrid(true);
        jTable_stock.getTableHeader().setResizingAllowed(false);
        jTable_stock.getTableHeader().setReorderingAllowed(false);
        jScrollPane6.setViewportView(jTable_stock);
        if (jTable_stock.getColumnModel().getColumnCount() > 0) {
            jTable_stock.getColumnModel().getColumn(4).setMinWidth(0);
            jTable_stock.getColumnModel().getColumn(4).setPreferredWidth(0);
            jTable_stock.getColumnModel().getColumn(4).setMaxWidth(0);
        }
        jTable_stock.getAccessibleContext().setAccessibleParent(this);

        add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 109, 712, 477));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel15.setText("الصنف");
        add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(616, 50, -1, -1));

        jButton_stock_createExcl.setText("Create Excel");
        add(jButton_stock_createExcl, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 600, -1, -1));

        jTextField_stock_SearchProducts.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        add(jTextField_stock_SearchProducts, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 250, 40));

        getAccessibleContext().setAccessibleParent(this);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_stock_createExcl;
    private javax.swing.JComboBox<Product> jComboBox_stock_Pros;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTable_stock;
    private javax.swing.JTextField jTextField_stock_SearchProducts;
    // End of variables declaration//GEN-END:variables
}

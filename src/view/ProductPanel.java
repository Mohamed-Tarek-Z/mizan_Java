package view;

import controller.ProductController;
import formController.ProductFormController;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.table.TableCellRenderer;
import utils.ErrorListener;

public class ProductPanel extends javax.swing.JPanel {

    public ProductPanel(ErrorListener errorListener, JPanel leftPanel, JButton panelOpener, ProductController productController) {
        initComponents();
        new ProductFormController(errorListener, leftPanel, this, panelOpener, jTextField_pro_name,
                jTextField_pro_conWight, jTextField_pro_color, jCheckBox_pro_IsBox, jTable_pro, jButton_pro_add,
                jButton_pro_edit, jButton_pro_del, productController).init();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField_pro_name = new javax.swing.JTextField();
        jButton_pro_add = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_pro = new javax.swing.JTable()
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

        };
        jButton_pro_del = new javax.swing.JButton();
        jTextField_pro_conWight = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jTextField_pro_color = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jCheckBox_pro_IsBox = new javax.swing.JCheckBox();
        jButton_pro_edit = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField_pro_name.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        jTextField_pro_name.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        add(jTextField_pro_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 340, -1));

        jButton_pro_add.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jButton_pro_add.setText("إضافة");
        add(jButton_pro_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 20, 80, 60));

        jTable_pro.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jTable_pro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "مسلسل", "أسم الصنف", "وزن الكونه", "اللون", "صندوق"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
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
        jTable_pro.setColumnSelectionAllowed(true);
        jTable_pro.setGridColor(new java.awt.Color(0, 0, 0));
        jTable_pro.setRowHeight(25);
        jTable_pro.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jTable_pro.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jTable_pro.setShowGrid(true);
        jTable_pro.getTableHeader().setReorderingAllowed(false);
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

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 126, 740, 470));

        jButton_pro_del.setBackground(new java.awt.Color(255, 0, 0));
        jButton_pro_del.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jButton_pro_del.setForeground(new java.awt.Color(255, 255, 255));
        jButton_pro_del.setText("حذف");
        jButton_pro_del.setPreferredSize(new java.awt.Dimension(75, 28));
        add(jButton_pro_del, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 30, 70, 40));

        jTextField_pro_conWight.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        jTextField_pro_conWight.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        add(jTextField_pro_conWight, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 90, 140, 30));

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel24.setText("الأسم");
        add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, -1, -1));

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel37.setText("وزن الكونه");
        add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, -1, -1));

        jTextField_pro_color.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField_pro_color.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        add(jTextField_pro_color, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, 140, 30));

        jLabel49.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel49.setText("اللون");
        add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 50, 70, 30));

        jCheckBox_pro_IsBox.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jCheckBox_pro_IsBox.setText("صندوق");
        add(jCheckBox_pro_IsBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 80, 110, -1));

        jButton_pro_edit.setBackground(new java.awt.Color(51, 204, 255));
        jButton_pro_edit.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        jButton_pro_edit.setText("تعديل");
        add(jButton_pro_edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 30, 70, 40));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_pro_add;
    private javax.swing.JButton jButton_pro_del;
    private javax.swing.JButton jButton_pro_edit;
    private javax.swing.JCheckBox jCheckBox_pro_IsBox;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable_pro;
    private javax.swing.JTextField jTextField_pro_color;
    private javax.swing.JTextField jTextField_pro_conWight;
    private javax.swing.JTextField jTextField_pro_name;
    // End of variables declaration//GEN-END:variables
}

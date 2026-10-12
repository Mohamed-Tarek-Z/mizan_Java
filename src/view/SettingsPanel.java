package view;

import controller.MachineController;
import controller.ProductController;
import model.Product;
import formController.SettingsFormController;
import formController.StorageFormController;
import javax.swing.JButton;
import javax.swing.JPanel;
import utils.ErrorListener;
import utils.PrinterManager;

public class SettingsPanel extends javax.swing.JPanel {

    private final String Version;
    public final SettingsFormController formController;

    public SettingsPanel(ErrorListener errorListener, JPanel leftPanel, PrintPanel printPanel, JButton panelOpener,
            MachineController machController, ProductController prosController, PrinterManager printerManager, String v, StorageFormController sfc) {
        Version = v;
        initComponents();
        formController = new SettingsFormController(errorListener, leftPanel, this, printPanel, panelOpener, jTabbedPane_settings,
                jTextField_mach_MName, jTextField_mach_lot, jComboBox_mach_pros, jButton_mach_addMach,
                jButton_mach_editMach, jButton_mach_Delete, jTable_machines,
                jButton_set_printValueToCenter, jButton_set_changePos, jButton_set_TicketPrinter, jButton_Reset_TicketCount10x10,
                jButton_set_reloadSettingFile, jLabel_Ticket10x10Counter, jLabel_ip,
                machController, prosController, printerManager, sfc);
        formController.init();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane_settings = new javax.swing.JTabbedPane();
        jPanel_Machines = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable_machines = new javax.swing.JTable();
        jTextField_mach_MName = new javax.swing.JTextField();
        jComboBox_mach_pros = new javax.swing.JComboBox<>();
        jTextField_mach_lot = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jButton_mach_addMach = new javax.swing.JButton();
        jButton_mach_Delete = new javax.swing.JButton();
        jButton_mach_editMach = new javax.swing.JButton();
        jTab_set_Printing = new javax.swing.JPanel();
        jButton_set_changePos = new javax.swing.JButton();
        jButton_set_printValueToCenter = new javax.swing.JButton();
        jButton_set_TicketPrinter = new javax.swing.JButton();
        jButton_Reset_TicketCount10x10 = new javax.swing.JButton();
        jLabel_Ticket10x10Counter = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jTab_set_about = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jLabel_ip = new javax.swing.JLabel();
        jButton_set_reloadSettingFile = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane_settings.setBorder(javax.swing.BorderFactory.createTitledBorder("Settings"));
        jTabbedPane_settings.setMaximumSize(new java.awt.Dimension(835, 640));
        jTabbedPane_settings.setMinimumSize(new java.awt.Dimension(835, 640));
        jTabbedPane_settings.setPreferredSize(new java.awt.Dimension(835, 640));

        jPanel_Machines.setMaximumSize(new java.awt.Dimension(830, 635));
        jPanel_Machines.setMinimumSize(new java.awt.Dimension(830, 635));
        jPanel_Machines.setPreferredSize(new java.awt.Dimension(830, 635));
        jPanel_Machines.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable_machines.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable_machines.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Name", "Type", "Lot", "Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        jTable_machines.setColumnSelectionAllowed(true);
        jTable_machines.setRowHeight(30);
        jTable_machines.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable_machines.setShowGrid(true);
        jTable_machines.setShowVerticalLines(false);
        jScrollPane5.setViewportView(jTable_machines);
        jTable_machines.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTable_machines.getColumnModel().getColumnCount() > 0) {
            jTable_machines.getColumnModel().getColumn(0).setMinWidth(0);
            jTable_machines.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTable_machines.getColumnModel().getColumn(0).setMaxWidth(0);
            jTable_machines.getColumnModel().getColumn(3).setResizable(false);
            jTable_machines.getColumnModel().getColumn(4).setResizable(false);
        }

        jPanel_Machines.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 285, 760, 302));

        jTextField_mach_MName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTextField_mach_MName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel_Machines.add(jTextField_mach_MName, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 70, 190, 50));

        jComboBox_mach_pros.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel_Machines.add(jComboBox_mach_pros, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 70, 210, 50));

        jTextField_mach_lot.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTextField_mach_lot.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel_Machines.add(jTextField_mach_lot, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 190, 50));

        jLabel51.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel51.setText("أسم الماكينة");
        jPanel_Machines.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 20, 110, 40));

        jLabel52.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel52.setText("صنف التشغيل");
        jPanel_Machines.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, 140, 40));

        jLabel54.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel54.setText("اللوط");
        jPanel_Machines.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 100, 40));

        jButton_mach_addMach.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jButton_mach_addMach.setText("Add");
        jPanel_Machines.add(jButton_mach_addMach, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 180, 170, 70));

        jButton_mach_Delete.setBackground(new java.awt.Color(255, 0, 0));
        jButton_mach_Delete.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton_mach_Delete.setForeground(new java.awt.Color(255, 255, 255));
        jButton_mach_Delete.setText("Delete");
        jPanel_Machines.add(jButton_mach_Delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 80, 30));

        jButton_mach_editMach.setBackground(new java.awt.Color(102, 204, 255));
        jButton_mach_editMach.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jButton_mach_editMach.setText("Edit");
        jButton_mach_editMach.setMaximumSize(new java.awt.Dimension(73, 39));
        jButton_mach_editMach.setMinimumSize(new java.awt.Dimension(73, 39));
        jButton_mach_editMach.setPreferredSize(new java.awt.Dimension(73, 39));
        jPanel_Machines.add(jButton_mach_editMach, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 190, 140, 50));

        jTabbedPane_settings.addTab("Machine", jPanel_Machines);

        jTab_set_Printing.setMaximumSize(new java.awt.Dimension(835, 640));
        jTab_set_Printing.setMinimumSize(new java.awt.Dimension(835, 640));
        jTab_set_Printing.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton_set_changePos.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton_set_changePos.setText("تغيير أماكن القيم في الطابعة");
        jTab_set_Printing.add(jButton_set_changePos, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, 70));

        jButton_set_printValueToCenter.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton_set_printValueToCenter.setText("تغيير أماكن القيم في الطابعة إلي المنتصف");
        jTab_set_Printing.add(jButton_set_printValueToCenter, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 40, 370, 70));

        jButton_set_TicketPrinter.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton_set_TicketPrinter.setText("Select Ticket Printer");
        jTab_set_Printing.add(jButton_set_TicketPrinter, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 160, 250, 70));

        jButton_Reset_TicketCount10x10.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jButton_Reset_TicketCount10x10.setText("Reset 10 X 10");
        jTab_set_Printing.add(jButton_Reset_TicketCount10x10, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 380, 160, 60));
        jTab_set_Printing.add(jLabel_Ticket10x10Counter, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 380, 170, 60));

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jTab_set_Printing.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 830, 10));

        jTabbedPane_settings.addTab("Printing Options", jTab_set_Printing);

        jTab_set_about.setMaximumSize(new java.awt.Dimension(830, 635));
        jTab_set_about.setMinimumSize(new java.awt.Dimension(830, 635));
        jTab_set_about.setPreferredSize(new java.awt.Dimension(830, 635));
        jTab_set_about.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel40.setBackground(new java.awt.Color(204, 255, 204));
        jLabel40.setFont(new java.awt.Font("Segoe UI", 3, 48)); // NOI18N
        jLabel40.setText(Version);
        jLabel40.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(102, 255, 255)));
        jLabel40.setFocusable(false);
        jLabel40.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel40.setName("setting_version"); // NOI18N
        jLabel40.setRequestFocusEnabled(false);
        jLabel40.setVerifyInputWhenFocusTarget(false);
        jTab_set_about.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 690, 140));

        jLabel_ip.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jTab_set_about.add(jLabel_ip, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 370, 560, 90));

        jButton_set_reloadSettingFile.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jButton_set_reloadSettingFile.setText("Reload Setting File");
        jTab_set_about.add(jButton_set_reloadSettingFile, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 460, 240, 90));

        jTabbedPane_settings.addTab("About", jTab_set_about);

        add(jTabbedPane_settings, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Reset_TicketCount10x10;
    private javax.swing.JButton jButton_mach_Delete;
    private javax.swing.JButton jButton_mach_addMach;
    private javax.swing.JButton jButton_mach_editMach;
    private javax.swing.JButton jButton_set_TicketPrinter;
    private javax.swing.JButton jButton_set_changePos;
    private javax.swing.JButton jButton_set_printValueToCenter;
    private javax.swing.JButton jButton_set_reloadSettingFile;
    private javax.swing.JComboBox<Product> jComboBox_mach_pros;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel_Ticket10x10Counter;
    private javax.swing.JLabel jLabel_ip;
    private javax.swing.JPanel jPanel_Machines;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel jTab_set_Printing;
    private javax.swing.JPanel jTab_set_about;
    private javax.swing.JTabbedPane jTabbedPane_settings;
    private javax.swing.JTable jTable_machines;
    private javax.swing.JTextField jTextField_mach_MName;
    private javax.swing.JTextField jTextField_mach_lot;
    // End of variables declaration//GEN-END:variables
}

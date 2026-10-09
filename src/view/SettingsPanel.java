package view;

import controller.MachineController;
import controller.ProductController;
import model.Product;
import formController.SettingsFormController;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JButton;
import javax.swing.JPanel;
import utils.ErrorListener;
import utils.PrinterManager;

public class SettingsPanel extends javax.swing.JPanel {

    private final String Version;

    public SettingsPanel(ErrorListener errorListener, JPanel leftPanel, JButton panelOpener,
            MachineController machController, ProductController prosController, PrinterManager printerManager, String v) {
        Version = v;
        initComponents();
        new SettingsFormController(errorListener, leftPanel, this, panelOpener, jTabbedPane_settings,
                jTextField_mach_MName, jTextField_mach_lot, jComboBox_mach_pros, jButton_mach_addMach,
                jButton_mach_editMach, jButton_mach_Delete, jTable_machines, jPanel_print,
                jButton_set_printValueToCenter, jButton_set_changePos, jButton_set_TicketPrinter, jButton_Reset_TicketCount10x10,
                jButton_set_reloadSettingFile, jLabel_Ticket10x10Counter, jLabel_ip, jLabel_print_ValPallet, jLabel_print_ValNCone, jLabel_print_ValColor,
                jLabel_print_ValTypeDenir, jLabel_print_ValType, jLabel_print_ValLot, jLabel_print_ValTotalWeight, jLabel_print_ValNetWeight,
                machController, prosController, printerManager).init();

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
        jPanel_print = new javax.swing.JPanel();
        jLabel_print_header = new javax.swing.JLabel();
        jLabel_print_ValPallet = new javax.swing.JLabel()
        {
            @Override
            protected void paintComponent(Graphics g) {
                adjustFontSize(g);
                super.paintComponent(g);
            }

            private void adjustFontSize(Graphics g) {
                if (getText() == null || getText().isEmpty()) {
                    return;
                }

                int labelWidth = getWidth();
                int labelHeight = getHeight();

                if (labelWidth <= 0 || labelHeight <= 0) {
                    return;
                }

                Graphics2D g2d = (Graphics2D) g;
                Font font = getFont();
                FontMetrics fm;
                int fontSize = font.getSize();
                int textWidth;
                int textHeight;

                do {
                    font = font.deriveFont((float) fontSize);
                    fm = g2d.getFontMetrics(font);
                    textWidth = fm.stringWidth(getText());
                    textHeight = fm.getHeight();
                    fontSize--;
                } while (textWidth > labelWidth && fontSize > 5); // Stop at minimum font size of 5

                setFont(font);
            }
        };
        jLabel_print_pallet = new javax.swing.JLabel();
        jSeparator_print_pallet = new javax.swing.JSeparator();
        jLabel_print_ValColor = new javax.swing.JLabel()
        {
            @Override
            protected void paintComponent(Graphics g) {
                adjustFontSize(g);
                super.paintComponent(g);
            }

            private void adjustFontSize(Graphics g) {
                if (getText() == null || getText().isEmpty()) {
                    return;
                }

                int labelWidth = getWidth();
                int labelHeight = getHeight();

                if (labelWidth <= 0 || labelHeight <= 0) {
                    return;
                }

                Graphics2D g2d = (Graphics2D) g;
                Font font = getFont();
                FontMetrics fm;
                int fontSize = font.getSize();
                int textWidth;
                int textHeight;

                do {
                    font = font.deriveFont((float) fontSize);
                    fm = g2d.getFontMetrics(font);
                    textWidth = fm.stringWidth(getText());
                    textHeight = fm.getHeight();
                    fontSize--;
                } while (textWidth > labelWidth && fontSize > 5); // Stop at minimum font size of 5

                setFont(font);
            }
        };
        jSeparator_print_color = new javax.swing.JSeparator();
        jLabel_print_ValNCone = new javax.swing.JLabel()
        {
            @Override
            protected void paintComponent(Graphics g) {
                adjustFontSize(g);
                super.paintComponent(g);
            }

            private void adjustFontSize(Graphics g) {
                if (getText() == null || getText().isEmpty()) {
                    return;
                }

                int labelWidth = getWidth();
                int labelHeight = getHeight();

                if (labelWidth <= 0 || labelHeight <= 0) {
                    return;
                }

                Graphics2D g2d = (Graphics2D) g;
                Font font = getFont();
                FontMetrics fm;
                int fontSize = font.getSize();
                int textWidth;
                int textHeight;

                do {
                    font = font.deriveFont((float) fontSize);
                    fm = g2d.getFontMetrics(font);
                    textWidth = fm.stringWidth(getText());
                    textHeight = fm.getHeight();
                    fontSize--;
                } while (textWidth > labelWidth && fontSize > 5); // Stop at minimum font size of 5

                setFont(font);
            }
        };
        jLabel_print_NCone = new javax.swing.JLabel();
        jSeparator_print_nCone = new javax.swing.JSeparator();
        jLabel_print_ValType = new javax.swing.JLabel()
        {
            @Override
            protected void paintComponent(Graphics g) {
                adjustFontSize(g);
                super.paintComponent(g);
            }

            private void adjustFontSize(Graphics g) {
                if (getText() == null || getText().isEmpty()) {
                    return;
                }

                int labelWidth = getWidth();
                int labelHeight = getHeight();

                if (labelWidth <= 0 || labelHeight <= 0) {
                    return;
                }

                Graphics2D g2d = (Graphics2D) g;
                Font font = getFont();
                FontMetrics fm;
                int fontSize = font.getSize();
                int textWidth;
                int textHeight;

                do {
                    font = font.deriveFont((float) fontSize);
                    fm = g2d.getFontMetrics(font);
                    textWidth = fm.stringWidth(getText());
                    textHeight = fm.getHeight();
                    fontSize--;
                } while (textWidth > labelWidth && fontSize > 5); // Stop at minimum font size of 5

                setFont(font);
            }
        };
        jLabel_print_ValTypeDenir = new javax.swing.JLabel()
        {
            @Override
            protected void paintComponent(Graphics g) {
                adjustFontSize(g);
                super.paintComponent(g);
            }

            private void adjustFontSize(Graphics g) {
                if (getText() == null || getText().isEmpty()) {
                    return;
                }

                int labelWidth = getWidth();
                int labelHeight = getHeight();

                if (labelWidth <= 0 || labelHeight <= 0) {
                    return;
                }

                Graphics2D g2d = (Graphics2D) g;
                Font font = getFont();
                FontMetrics fm;
                int fontSize = font.getSize();
                int textWidth;
                int textHeight;

                do {
                    font = font.deriveFont((float) fontSize);
                    fm = g2d.getFontMetrics(font);
                    textWidth = fm.stringWidth(getText());
                    textHeight = fm.getHeight();
                    fontSize--;
                } while (textWidth > labelWidth && fontSize > 5); // Stop at minimum font size of 5

                setFont(font);
            }
        };
        jLabel_print_type = new javax.swing.JLabel();
        jSeparator_print_type = new javax.swing.JSeparator();
        jLabel_print_ValLot = new javax.swing.JLabel()
        {
            @Override
            protected void paintComponent(Graphics g) {
                adjustFontSize(g);
                super.paintComponent(g);
            }

            private void adjustFontSize(Graphics g) {
                if (getText() == null || getText().isEmpty()) {
                    return;
                }

                int labelWidth = getWidth();
                int labelHeight = getHeight();

                if (labelWidth <= 0 || labelHeight <= 0) {
                    return;
                }

                Graphics2D g2d = (Graphics2D) g;
                Font font = getFont();
                FontMetrics fm;
                int fontSize = font.getSize();
                int textWidth;
                int textHeight;

                do {
                    font = font.deriveFont((float) fontSize);
                    fm = g2d.getFontMetrics(font);
                    textWidth = fm.stringWidth(getText());
                    textHeight = fm.getHeight();
                    fontSize--;
                } while (textWidth > labelWidth && fontSize > 5); // Stop at minimum font size of 5

                setFont(font);
            }
        };
        jLabel_print_lot = new javax.swing.JLabel();
        jSeparator_print_lot = new javax.swing.JSeparator();
        jLabel_print_ValTotalWeight = new javax.swing.JLabel()
        {
            @Override
            protected void paintComponent(Graphics g) {
                adjustFontSize(g);
                super.paintComponent(g);
            }

            private void adjustFontSize(Graphics g) {
                if (getText() == null || getText().isEmpty()) {
                    return;
                }

                int labelWidth = getWidth();
                int labelHeight = getHeight();

                if (labelWidth <= 0 || labelHeight <= 0) {
                    return;
                }

                Graphics2D g2d = (Graphics2D) g;
                Font font = getFont();
                FontMetrics fm;
                int fontSize = font.getSize();
                int textWidth;
                int textHeight;

                do {
                    font = font.deriveFont((float) fontSize);
                    fm = g2d.getFontMetrics(font);
                    textWidth = fm.stringWidth(getText());
                    textHeight = fm.getHeight();
                    fontSize--;
                } while (textWidth > labelWidth && fontSize > 5); // Stop at minimum font size of 5

                setFont(font);
            }
        };
        jLabel_print_TotalWeight = new javax.swing.JLabel();
        jSeparator_print_totWeight = new javax.swing.JSeparator();
        jLabel_print_ValNetWeight = new javax.swing.JLabel()
        {
            @Override
            protected void paintComponent(Graphics g) {
                adjustFontSize(g);
                super.paintComponent(g);
            }

            private void adjustFontSize(Graphics g) {
                if (getText() == null || getText().isEmpty()) {
                    return;
                }

                int labelWidth = getWidth();
                int labelHeight = getHeight();

                if (labelWidth <= 0 || labelHeight <= 0) {
                    return;
                }

                Graphics2D g2d = (Graphics2D) g;
                Font font = getFont();
                FontMetrics fm;
                int fontSize = font.getSize();
                int textWidth;
                int textHeight;

                do {
                    font = font.deriveFont((float) fontSize);
                    fm = g2d.getFontMetrics(font);
                    textWidth = fm.stringWidth(getText());
                    textHeight = fm.getHeight();
                    fontSize--;
                } while (textWidth > labelWidth && fontSize > 5); // Stop at minimum font size of 5

                setFont(font);
            }
        };
        jLabel_print_NetWeight = new javax.swing.JLabel();
        jLabel_print_footer = new javax.swing.JLabel();
        jLabel_print_number = new javax.swing.JLabel();
        jSeparator_print_main = new javax.swing.JSeparator();
        jSeparator_print_valBox = new javax.swing.JSeparator();
        jSeparator_print_Double = new javax.swing.JSeparator();

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

        jPanel_print.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_print.setEnabled(false);
        jPanel_print.setFocusable(false);
        jPanel_print.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jPanel_print.setMaximumSize(new java.awt.Dimension(300, 350));
        jPanel_print.setMinimumSize(new java.awt.Dimension(300, 350));
        jPanel_print.setName("printingPanel"); // NOI18N
        jPanel_print.setPreferredSize(new java.awt.Dimension(300, 350));
        jPanel_print.setRequestFocusEnabled(false);
        jPanel_print.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel_print_header.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel_print_header.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_print_header.setText("الصفا و المروه للغزل و النسيج");
        jLabel_print_header.setFocusable(false);
        jLabel_print_header.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel_print.add(jLabel_print_header, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, -1, -1));

        jLabel_print_ValPallet.setFont(new java.awt.Font("Arial", 0, 30)); // NOI18N
        jLabel_print_ValPallet.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_print_ValPallet.setText("Label");
        jLabel_print_ValPallet.setFocusable(false);
        jLabel_print_ValPallet.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel_print.add(jLabel_print_ValPallet, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 24, 190, 25));

        jLabel_print_pallet.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel_print_pallet.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_print_pallet.setText("رقم البالتة");
        jLabel_print_pallet.setFocusable(false);
        jLabel_print_pallet.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel_print.add(jLabel_print_pallet, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 25, 75, 20));

        jSeparator_print_pallet.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator_print_pallet.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        jSeparator_print_pallet.setMaximumSize(new java.awt.Dimension(50, 100));
        jSeparator_print_pallet.setMinimumSize(new java.awt.Dimension(50, 100));
        jSeparator_print_pallet.setPreferredSize(new java.awt.Dimension(50, 100));
        jPanel_print.add(jSeparator_print_pallet, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 48, 280, 3));

        jLabel_print_ValColor.setFont(new java.awt.Font("Arial", 0, 26)); // NOI18N
        jLabel_print_ValColor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_print_ValColor.setText("Label");
        jLabel_print_ValColor.setFocusable(false);
        jLabel_print_ValColor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel_print.add(jLabel_print_ValColor, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 51, 90, 35));

        jSeparator_print_color.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator_print_color.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator_print_color.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 2, new java.awt.Color(0, 0, 0)));
        jSeparator_print_color.setMaximumSize(new java.awt.Dimension(50, 100));
        jSeparator_print_color.setMinimumSize(new java.awt.Dimension(50, 100));
        jSeparator_print_color.setPreferredSize(new java.awt.Dimension(50, 100));
        jPanel_print.add(jSeparator_print_color, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 50, 5, 40));

        jLabel_print_ValNCone.setFont(new java.awt.Font("Arial", 0, 28)); // NOI18N
        jLabel_print_ValNCone.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_print_ValNCone.setText("Label");
        jLabel_print_ValNCone.setFocusable(false);
        jLabel_print_ValNCone.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel_print.add(jLabel_print_ValNCone, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 51, 90, 35));

        jLabel_print_NCone.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel_print_NCone.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_print_NCone.setText("الكون");
        jLabel_print_NCone.setFocusable(false);
        jLabel_print_NCone.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel_print.add(jLabel_print_NCone, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 51, 75, 35));

        jSeparator_print_nCone.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator_print_nCone.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        jSeparator_print_nCone.setMaximumSize(new java.awt.Dimension(50, 100));
        jSeparator_print_nCone.setMinimumSize(new java.awt.Dimension(50, 100));
        jSeparator_print_nCone.setPreferredSize(new java.awt.Dimension(50, 100));
        jPanel_print.add(jSeparator_print_nCone, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 280, 2));

        jLabel_print_ValType.setFont(new java.awt.Font("Arial", 0, 30)); // NOI18N
        jLabel_print_ValType.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_print_ValType.setText("Label");
        jLabel_print_ValType.setFocusable(false);
        jLabel_print_ValType.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel_print.add(jLabel_print_ValType, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 125, 190, 28));

        jLabel_print_ValTypeDenir.setFont(new java.awt.Font("Arial", 0, 35)); // NOI18N
        jLabel_print_ValTypeDenir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_print_ValTypeDenir.setText("Label");
        jLabel_print_ValTypeDenir.setFocusable(false);
        jLabel_print_ValTypeDenir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel_print.add(jLabel_print_ValTypeDenir, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 95, 190, 28));

        jLabel_print_type.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel_print_type.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_print_type.setText("الصنف");
        jLabel_print_type.setFocusable(false);
        jLabel_print_type.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel_print.add(jLabel_print_type, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 95, 75, 60));

        jSeparator_print_type.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator_print_type.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        jSeparator_print_type.setMaximumSize(new java.awt.Dimension(50, 100));
        jSeparator_print_type.setMinimumSize(new java.awt.Dimension(50, 100));
        jSeparator_print_type.setPreferredSize(new java.awt.Dimension(50, 100));
        jPanel_print.add(jSeparator_print_type, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 155, 280, 3));

        jLabel_print_ValLot.setFont(new java.awt.Font("Arial", 0, 40)); // NOI18N
        jLabel_print_ValLot.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_print_ValLot.setText("Label");
        jLabel_print_ValLot.setFocusable(false);
        jLabel_print_ValLot.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel_print.add(jLabel_print_ValLot, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 162, 190, 30));

        jLabel_print_lot.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel_print_lot.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_print_lot.setText("اللوط");
        jLabel_print_lot.setFocusable(false);
        jLabel_print_lot.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel_print.add(jLabel_print_lot, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 165, 75, 25));

        jSeparator_print_lot.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator_print_lot.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        jSeparator_print_lot.setMaximumSize(new java.awt.Dimension(50, 100));
        jSeparator_print_lot.setMinimumSize(new java.awt.Dimension(50, 100));
        jSeparator_print_lot.setPreferredSize(new java.awt.Dimension(50, 100));
        jPanel_print.add(jSeparator_print_lot, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 194, 280, 3));

        jLabel_print_ValTotalWeight.setFont(new java.awt.Font("Arial", 0, 32)); // NOI18N
        jLabel_print_ValTotalWeight.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_print_ValTotalWeight.setText("Label");
        jLabel_print_ValTotalWeight.setFocusable(false);
        jLabel_print_ValTotalWeight.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel_print.add(jLabel_print_ValTotalWeight, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 200, 190, 35));

        jLabel_print_TotalWeight.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel_print_TotalWeight.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_print_TotalWeight.setText("وزن قائم");
        jLabel_print_TotalWeight.setFocusable(false);
        jLabel_print_TotalWeight.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel_print.add(jLabel_print_TotalWeight, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 195, 75, 40));

        jSeparator_print_totWeight.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator_print_totWeight.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        jSeparator_print_totWeight.setMaximumSize(new java.awt.Dimension(50, 100));
        jSeparator_print_totWeight.setMinimumSize(new java.awt.Dimension(50, 100));
        jSeparator_print_totWeight.setPreferredSize(new java.awt.Dimension(50, 100));
        jPanel_print.add(jSeparator_print_totWeight, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 238, 280, 3));

        jLabel_print_ValNetWeight.setFont(new java.awt.Font("Arial", 0, 48)); // NOI18N
        jLabel_print_ValNetWeight.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_print_ValNetWeight.setText("Label");
        jLabel_print_ValNetWeight.setFocusable(false);
        jLabel_print_ValNetWeight.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel_print.add(jLabel_print_ValNetWeight, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 243, 190, 50));

        jLabel_print_NetWeight.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel_print_NetWeight.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_print_NetWeight.setText("وزن صافي");
        jLabel_print_NetWeight.setFocusable(false);
        jLabel_print_NetWeight.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel_print.add(jLabel_print_NetWeight, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 243, 75, 50));

        jLabel_print_footer.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel_print_footer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_print_footer.setText("أ / وجيه عماره");
        jLabel_print_footer.setFocusable(false);
        jLabel_print_footer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel_print.add(jLabel_print_footer, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 302, 100, 20));

        jLabel_print_number.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel_print_number.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_print_number.setText("ت / ٠١١٤٨٠٥٥٥٥٨");
        jPanel_print.add(jLabel_print_number, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 302, 150, -1));

        jSeparator_print_main.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator_print_main.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jSeparator_print_main.setMaximumSize(new java.awt.Dimension(50, 100));
        jSeparator_print_main.setMinimumSize(new java.awt.Dimension(50, 100));
        jSeparator_print_main.setPreferredSize(new java.awt.Dimension(50, 100));
        jPanel_print.add(jSeparator_print_main, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 280, 280));

        jSeparator_print_valBox.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator_print_valBox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jSeparator_print_valBox.setMaximumSize(new java.awt.Dimension(50, 100));
        jSeparator_print_valBox.setMinimumSize(new java.awt.Dimension(50, 100));
        jSeparator_print_valBox.setPreferredSize(new java.awt.Dimension(50, 100));
        jPanel_print.add(jSeparator_print_valBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 200, 280));

        jSeparator_print_Double.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator_print_Double.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator_print_Double.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 0, new java.awt.Color(0, 0, 0)));
        jPanel_print.add(jSeparator_print_Double, new org.netbeans.lib.awtextra.AbsoluteConstraints(205, 20, 10, 280));

        jTabbedPane_settings.addTab("print", jPanel_print);

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
    private javax.swing.JLabel jLabel_print_NCone;
    private javax.swing.JLabel jLabel_print_NetWeight;
    private javax.swing.JLabel jLabel_print_TotalWeight;
    private javax.swing.JLabel jLabel_print_ValColor;
    private javax.swing.JLabel jLabel_print_ValLot;
    private javax.swing.JLabel jLabel_print_ValNCone;
    private javax.swing.JLabel jLabel_print_ValNetWeight;
    private javax.swing.JLabel jLabel_print_ValPallet;
    private javax.swing.JLabel jLabel_print_ValTotalWeight;
    private javax.swing.JLabel jLabel_print_ValType;
    private javax.swing.JLabel jLabel_print_ValTypeDenir;
    private javax.swing.JLabel jLabel_print_footer;
    private javax.swing.JLabel jLabel_print_header;
    private javax.swing.JLabel jLabel_print_lot;
    private javax.swing.JLabel jLabel_print_number;
    private javax.swing.JLabel jLabel_print_pallet;
    private javax.swing.JLabel jLabel_print_type;
    private javax.swing.JPanel jPanel_Machines;
    private javax.swing.JPanel jPanel_print;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator_print_Double;
    private javax.swing.JSeparator jSeparator_print_color;
    private javax.swing.JSeparator jSeparator_print_lot;
    private javax.swing.JSeparator jSeparator_print_main;
    private javax.swing.JSeparator jSeparator_print_nCone;
    private javax.swing.JSeparator jSeparator_print_pallet;
    private javax.swing.JSeparator jSeparator_print_totWeight;
    private javax.swing.JSeparator jSeparator_print_type;
    private javax.swing.JSeparator jSeparator_print_valBox;
    private javax.swing.JPanel jTab_set_Printing;
    private javax.swing.JPanel jTab_set_about;
    private javax.swing.JTabbedPane jTabbedPane_settings;
    private javax.swing.JTable jTable_machines;
    private javax.swing.JTextField jTextField_mach_MName;
    private javax.swing.JTextField jTextField_mach_lot;
    // End of variables declaration//GEN-END:variables
}

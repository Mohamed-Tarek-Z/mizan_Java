package view;

import formController.PrintFormController;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import utils.PrinterManager;

public class PrintPanel extends javax.swing.JPanel {

    public final PrintFormController formController;

    public PrintPanel(PrinterManager printerManager) {
        initComponents();
        formController = new PrintFormController(jLabel_print_ValPallet, jLabel_print_ValNCone,
                jLabel_print_ValColor, jLabel_print_ValTypeDenir, jLabel_print_ValType, jLabel_print_ValLot,
                jLabel_print_ValTotalWeight, jLabel_print_ValNetWeight, this, printerManager);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel_print_header.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel_print_header.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_print_header.setText("الصفا و المروه للغزل و النسيج");
        jLabel_print_header.setFocusable(false);
        jLabel_print_header.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(jLabel_print_header, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, -1, -1));

        jLabel_print_ValPallet.setFont(new java.awt.Font("Arial", 0, 30)); // NOI18N
        jLabel_print_ValPallet.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_print_ValPallet.setText("Label");
        jLabel_print_ValPallet.setFocusable(false);
        jLabel_print_ValPallet.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(jLabel_print_ValPallet, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 24, 190, 25));

        jLabel_print_pallet.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel_print_pallet.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_print_pallet.setText("رقم البالتة");
        jLabel_print_pallet.setFocusable(false);
        jLabel_print_pallet.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(jLabel_print_pallet, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 25, 75, 20));

        jSeparator_print_pallet.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator_print_pallet.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        jSeparator_print_pallet.setMaximumSize(new java.awt.Dimension(50, 100));
        jSeparator_print_pallet.setMinimumSize(new java.awt.Dimension(50, 100));
        jSeparator_print_pallet.setPreferredSize(new java.awt.Dimension(50, 100));
        add(jSeparator_print_pallet, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 48, 280, 3));

        jLabel_print_ValColor.setFont(new java.awt.Font("Arial", 0, 26)); // NOI18N
        jLabel_print_ValColor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_print_ValColor.setText("Label");
        jLabel_print_ValColor.setFocusable(false);
        jLabel_print_ValColor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(jLabel_print_ValColor, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 51, 90, 35));

        jSeparator_print_color.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator_print_color.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator_print_color.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 2, new java.awt.Color(0, 0, 0)));
        jSeparator_print_color.setMaximumSize(new java.awt.Dimension(50, 100));
        jSeparator_print_color.setMinimumSize(new java.awt.Dimension(50, 100));
        jSeparator_print_color.setPreferredSize(new java.awt.Dimension(50, 100));
        add(jSeparator_print_color, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 50, 5, 40));

        jLabel_print_ValNCone.setFont(new java.awt.Font("Arial", 0, 28)); // NOI18N
        jLabel_print_ValNCone.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_print_ValNCone.setText("Label");
        jLabel_print_ValNCone.setFocusable(false);
        jLabel_print_ValNCone.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(jLabel_print_ValNCone, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 51, 90, 35));

        jLabel_print_NCone.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel_print_NCone.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_print_NCone.setText("الكون");
        jLabel_print_NCone.setFocusable(false);
        jLabel_print_NCone.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(jLabel_print_NCone, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 51, 75, 35));

        jSeparator_print_nCone.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator_print_nCone.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        jSeparator_print_nCone.setMaximumSize(new java.awt.Dimension(50, 100));
        jSeparator_print_nCone.setMinimumSize(new java.awt.Dimension(50, 100));
        jSeparator_print_nCone.setPreferredSize(new java.awt.Dimension(50, 100));
        add(jSeparator_print_nCone, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 280, 2));

        jLabel_print_ValType.setFont(new java.awt.Font("Arial", 0, 30)); // NOI18N
        jLabel_print_ValType.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_print_ValType.setText("Label");
        jLabel_print_ValType.setFocusable(false);
        jLabel_print_ValType.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(jLabel_print_ValType, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 125, 190, 28));

        jLabel_print_ValTypeDenir.setFont(new java.awt.Font("Arial", 0, 35)); // NOI18N
        jLabel_print_ValTypeDenir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_print_ValTypeDenir.setText("Label");
        jLabel_print_ValTypeDenir.setFocusable(false);
        jLabel_print_ValTypeDenir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(jLabel_print_ValTypeDenir, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 95, 190, 28));

        jLabel_print_type.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel_print_type.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_print_type.setText("الصنف");
        jLabel_print_type.setFocusable(false);
        jLabel_print_type.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(jLabel_print_type, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 95, 75, 60));

        jSeparator_print_type.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator_print_type.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        jSeparator_print_type.setMaximumSize(new java.awt.Dimension(50, 100));
        jSeparator_print_type.setMinimumSize(new java.awt.Dimension(50, 100));
        jSeparator_print_type.setPreferredSize(new java.awt.Dimension(50, 100));
        add(jSeparator_print_type, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 155, 280, 3));

        jLabel_print_ValLot.setFont(new java.awt.Font("Arial", 0, 40)); // NOI18N
        jLabel_print_ValLot.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_print_ValLot.setText("Label");
        jLabel_print_ValLot.setFocusable(false);
        jLabel_print_ValLot.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(jLabel_print_ValLot, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 162, 190, 30));

        jLabel_print_lot.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel_print_lot.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_print_lot.setText("اللوط");
        jLabel_print_lot.setFocusable(false);
        jLabel_print_lot.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(jLabel_print_lot, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 165, 75, 25));

        jSeparator_print_lot.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator_print_lot.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        jSeparator_print_lot.setMaximumSize(new java.awt.Dimension(50, 100));
        jSeparator_print_lot.setMinimumSize(new java.awt.Dimension(50, 100));
        jSeparator_print_lot.setPreferredSize(new java.awt.Dimension(50, 100));
        add(jSeparator_print_lot, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 194, 280, 3));

        jLabel_print_ValTotalWeight.setFont(new java.awt.Font("Arial", 0, 32)); // NOI18N
        jLabel_print_ValTotalWeight.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_print_ValTotalWeight.setText("Label");
        jLabel_print_ValTotalWeight.setFocusable(false);
        jLabel_print_ValTotalWeight.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(jLabel_print_ValTotalWeight, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 200, 190, 35));

        jLabel_print_TotalWeight.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel_print_TotalWeight.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_print_TotalWeight.setText("وزن قائم");
        jLabel_print_TotalWeight.setFocusable(false);
        jLabel_print_TotalWeight.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(jLabel_print_TotalWeight, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 195, 75, 40));

        jSeparator_print_totWeight.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator_print_totWeight.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        jSeparator_print_totWeight.setMaximumSize(new java.awt.Dimension(50, 100));
        jSeparator_print_totWeight.setMinimumSize(new java.awt.Dimension(50, 100));
        jSeparator_print_totWeight.setPreferredSize(new java.awt.Dimension(50, 100));
        add(jSeparator_print_totWeight, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 238, 280, 3));

        jLabel_print_ValNetWeight.setFont(new java.awt.Font("Arial", 0, 48)); // NOI18N
        jLabel_print_ValNetWeight.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_print_ValNetWeight.setText("Label");
        jLabel_print_ValNetWeight.setFocusable(false);
        jLabel_print_ValNetWeight.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(jLabel_print_ValNetWeight, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 243, 190, 50));

        jLabel_print_NetWeight.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel_print_NetWeight.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_print_NetWeight.setText("وزن صافي");
        jLabel_print_NetWeight.setFocusable(false);
        jLabel_print_NetWeight.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(jLabel_print_NetWeight, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 243, 75, 50));

        jLabel_print_footer.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel_print_footer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_print_footer.setText("أ / وجيه عماره");
        jLabel_print_footer.setFocusable(false);
        jLabel_print_footer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(jLabel_print_footer, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 302, 100, 20));

        jLabel_print_number.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel_print_number.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_print_number.setText("ت / ٠١١٤٨٠٥٥٥٥٨");
        add(jLabel_print_number, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 302, 150, -1));

        jSeparator_print_main.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator_print_main.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jSeparator_print_main.setMaximumSize(new java.awt.Dimension(50, 100));
        jSeparator_print_main.setMinimumSize(new java.awt.Dimension(50, 100));
        jSeparator_print_main.setPreferredSize(new java.awt.Dimension(50, 100));
        add(jSeparator_print_main, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 280, 280));

        jSeparator_print_valBox.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator_print_valBox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jSeparator_print_valBox.setMaximumSize(new java.awt.Dimension(50, 100));
        jSeparator_print_valBox.setMinimumSize(new java.awt.Dimension(50, 100));
        jSeparator_print_valBox.setPreferredSize(new java.awt.Dimension(50, 100));
        add(jSeparator_print_valBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 200, 280));

        jSeparator_print_Double.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator_print_Double.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator_print_Double.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 0, new java.awt.Color(0, 0, 0)));
        add(jSeparator_print_Double, new org.netbeans.lib.awtextra.AbsoluteConstraints(205, 20, 10, 280));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    private javax.swing.JSeparator jSeparator_print_Double;
    private javax.swing.JSeparator jSeparator_print_color;
    private javax.swing.JSeparator jSeparator_print_lot;
    private javax.swing.JSeparator jSeparator_print_main;
    private javax.swing.JSeparator jSeparator_print_nCone;
    private javax.swing.JSeparator jSeparator_print_pallet;
    private javax.swing.JSeparator jSeparator_print_totWeight;
    private javax.swing.JSeparator jSeparator_print_type;
    private javax.swing.JSeparator jSeparator_print_valBox;
    // End of variables declaration//GEN-END:variables
}

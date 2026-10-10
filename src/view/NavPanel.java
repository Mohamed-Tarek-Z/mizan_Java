package view;

import java.sql.SQLException;
import java.time.LocalDate;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.sqlcon;
import utils.ErrorListener;

public class NavPanel extends javax.swing.JPanel {

    private final String Version;
    private final sqlcon opj;
    private final ErrorListener errorListener;

    public NavPanel(ErrorListener errorListener, String v, sqlcon opj) {
        this.Version = v;
        this.errorListener = errorListener;
        this.opj = opj;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton_Mizan_opener = new javax.swing.JButton();
        jButton_pro_opener = new javax.swing.JButton();
        jButton_Ezn_opener = new javax.swing.JButton();
        jButton_DoBack = new javax.swing.JButton();
        jButton_Stock_opener = new javax.swing.JButton();
        jButton_Statics_opener = new javax.swing.JButton();
        jButton_youm_opener = new javax.swing.JButton();
        jButton_Emp_opener = new javax.swing.JButton();
        jLabel_version = new javax.swing.JLabel();
        jButton_Settings_opener = new javax.swing.JButton();

        setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        setMaximumSize(new java.awt.Dimension(110, 650));
        setMinimumSize(new java.awt.Dimension(110, 650));
        setPreferredSize(new java.awt.Dimension(110, 650));
        setRequestFocusEnabled(false);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton_Mizan_opener.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_Mizan_opener.setText("ميزان");
        jButton_Mizan_opener.setToolTipText("اضغط على F1 لفتح اللوحة");
        add(jButton_Mizan_opener, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 110, 40));

        jButton_pro_opener.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_pro_opener.setText("إضافه صنف");
        jButton_pro_opener.setToolTipText("اضغط على F3 لفتح اللوحة");
        add(jButton_pro_opener, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 110, 40));

        jButton_Ezn_opener.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_Ezn_opener.setText("إذن غزل");
        jButton_Ezn_opener.setToolTipText("اضغط على F2 لفتح اللوحة");
        add(jButton_Ezn_opener, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 110, 40));

        jButton_DoBack.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_DoBack.setText("باك أب");
        jButton_DoBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_DoBackActionPerformed(evt);
            }
        });
        add(jButton_DoBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 110, 40));

        jButton_Stock_opener.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_Stock_opener.setText("رصيد");
        jButton_Stock_opener.setToolTipText("اضغط على F5 لفتح اللوحة");
        add(jButton_Stock_opener, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 110, 40));

        jButton_Statics_opener.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_Statics_opener.setText("إحصائيات");
        add(jButton_Statics_opener, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 110, 40));

        jButton_youm_opener.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_youm_opener.setText("يوميه");
        add(jButton_youm_opener, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 110, 40));

        jButton_Emp_opener.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_Emp_opener.setText("أي");
        jButton_Emp_opener.setToolTipText("اضغط على F4 لفتح اللوحة");
        add(jButton_Emp_opener, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, 110, 40));

        jLabel_version.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel_version.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_version.setText(Version);
        jLabel_version.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 51, 51), 3, true));
        jLabel_version.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        jLabel_version.setFocusable(false);
        jLabel_version.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel_version.setRequestFocusEnabled(false);
        jLabel_version.setVerifyInputWhenFocusTarget(false);
        add(jLabel_version, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 596, 110, 40));

        jButton_Settings_opener.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton_Settings_opener.setText("Setting");
        jButton_Settings_opener.setMaximumSize(new java.awt.Dimension(72, 29));
        jButton_Settings_opener.setMinimumSize(new java.awt.Dimension(72, 29));
        jButton_Settings_opener.setPreferredSize(new java.awt.Dimension(72, 29));
        add(jButton_Settings_opener, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 520, 110, 40));

        getAccessibleContext().setAccessibleParent(this);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_DoBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_DoBackActionPerformed
        evt.getID();
        try {
            JFileChooser fileChooser = new JFileChooser("P:\\");
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fileChooser.setFileFilter(new FileNameExtensionFilter("BAK file", "bak"));
            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                if (fileChooser.getSelectedFile() != null) {
                    opj.backup(fileChooser.getSelectedFile().getAbsolutePath() + " " + LocalDate.now() + ".bak");
                    errorListener.onWarning("Back up succes ", "succes");
                }
            } else {
                errorListener.onWarning("Back up faild ", "faild");
            }
        } catch (SQLException ex) {
            errorListener.onError(ex);
        }
    }//GEN-LAST:event_jButton_DoBackActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton jButton_DoBack;
    protected javax.swing.JButton jButton_Emp_opener;
    protected javax.swing.JButton jButton_Ezn_opener;
    protected javax.swing.JButton jButton_Mizan_opener;
    protected javax.swing.JButton jButton_Settings_opener;
    protected javax.swing.JButton jButton_Statics_opener;
    protected javax.swing.JButton jButton_Stock_opener;
    protected javax.swing.JButton jButton_pro_opener;
    protected javax.swing.JButton jButton_youm_opener;
    protected javax.swing.JLabel jLabel_version;
    // End of variables declaration//GEN-END:variables
}

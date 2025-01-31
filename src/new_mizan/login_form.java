package new_mizan;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class login_form extends javax.swing.JFrame {

    sqlcon opj;

    public login_form() throws SQLException, Exception {
        initComponents();
        opj = new sqlcon();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("تسجيل الدخول");
        setMaximumSize(new java.awt.Dimension(390, 530));
        setMinimumSize(new java.awt.Dimension(390, 530));
        setPreferredSize(new java.awt.Dimension(390, 530));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPasswordField1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jPasswordField1.setText("12345");
        jPasswordField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jPasswordField1KeyTyped(evt);
            }
        });
        getContentPane().add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(78, 261, 244, 40));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("اسم المستخدم");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("كلمه المرور");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 210, -1, -1));

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jButton1.setText("دخول");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 390, -1, -1));

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ميزان", "محمد نشأت" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jComboBox1KeyTyped(evt);
            }
        });
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, 240, 50));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jPasswordField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField1KeyTyped
        textbox_length_limiter(evt, jPasswordField1, 30);
        evt.getID();
        if (evt.getKeyChar() == 10) {
            jButton1.doClick();
        }
    }//GEN-LAST:event_jPasswordField1KeyTyped

    static boolean admin = false;

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            evt.getID();
            String pw = "";
            for (char c : jPasswordField1.getPassword()) {
                pw += c;
            }
            ResultSet st = opj.dataRead("status_", "users",
                    "username=N'" + jComboBox1.getSelectedItem().toString() + "' and password_=N'" + pw + "'");
            if (st.next()) {
                admin = "0".equals(st.getString(1));
                mainform opj1 = new mainform(opj);
                opj1.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "كلمه المرور  غير صحيحه", "انتبه", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(login_form.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage(), "إنتبه", JOptionPane.ERROR_MESSAGE);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(login_form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        if (jComboBox1.getSelectedIndex() == 0) {
            evt.getID();
            jPasswordField1.setText("12345");
        }
        if (jComboBox1.getSelectedIndex() == 1) {
            jPasswordField1.requestFocus();
            jPasswordField1.setText("628");
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jComboBox1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox1KeyTyped
        if (evt.getKeyChar() == 10) {
            evt.getID();
            jButton1.doClick();
        }
    }//GEN-LAST:event_jComboBox1KeyTyped

    void textbox_length_limiter(KeyEvent event, JTextField textboxname, int length) {
        if (textboxname.getText().length() > length - 1) {
            event.consume();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login_form.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new login_form().setVisible(true);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage().substring(0, 67), "إنتبه",
                        JOptionPane.PLAIN_MESSAGE);
                java.util.logging.Logger.getLogger(login_form.class.getName()).log(java.util.logging.Level.SEVERE, null,
                        ex);
                System.exit(NORMAL);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "إنتبه",
                        JOptionPane.PLAIN_MESSAGE);

                Logger.getLogger(login_form.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPasswordField jPasswordField1;
    // End of variables declaration//GEN-END:variables
}

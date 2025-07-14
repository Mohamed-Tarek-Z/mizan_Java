package view;

import controller.UserController;
import dao.UserDAO;
import exceptions.BusinessException;
import exceptions.DatabaseException;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.sqlcon;
import utils.utils;

public class login_form extends javax.swing.JFrame {

    private final UserController userController;
    private final sqlcon dbConnection;

    public login_form() throws BusinessException, DatabaseException {
        try {
            initComponents();

            Properties properties = utils.CheckConfigFileAndFolder();
            if (!properties.getProperty("ip", "localhost").equalsIgnoreCase("localhost")) {
                JOptionPane.showMessageDialog(null, "conneting to remote DataBase Ip:" + properties.getProperty("ip", "localhost"), "انتبه", JOptionPane.INFORMATION_MESSAGE);
            }
            this.dbConnection = new sqlcon(properties.getProperty("ip", "localhost"));
            userController = new UserController(new UserDAO(dbConnection));
        } catch (SQLException ex) {
            throw new DatabaseException("خطأ في أتصال خادم البيانات", ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton_Login = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("تسجيل الدخول");
        setMinimumSize(new java.awt.Dimension(390, 530));
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

        jButton_Login.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jButton_Login.setText("دخول");
        jButton_Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_LoginActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_Login, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 390, -1, -1));

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
        evt.getID();
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            jButton_Login.doClick();
        }
    }//GEN-LAST:event_jPasswordField1KeyTyped


    private void jButton_LoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_LoginActionPerformed

        try {
            if (userController.login(jComboBox1.getSelectedItem().toString(), new String(jPasswordField1.getPassword()))) {
                mainform mainForm = new mainform(userController.isAdmin(jComboBox1.getSelectedItem().toString(), new String(jPasswordField1.getPassword())), this.dbConnection);
                mainForm.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "كلمه المرور غير صحيحه", "انتبه", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (DatabaseException | BusinessException ex) {
            Logger.getLogger(login_form.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(), "إنتبه", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_jButton_LoginActionPerformed

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
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            evt.getID();
            jButton_Login.doClick();
        }
    }//GEN-LAST:event_jComboBox1KeyTyped

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new login_form().setVisible(true);
            } catch (DatabaseException ex) {
                JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "إنتبه", JOptionPane.PLAIN_MESSAGE);
                Logger.getLogger(login_form.class.getName()).log(Level.SEVERE, null, ex);
                System.exit(NORMAL);
            } catch (BusinessException ex) {
                Logger.getLogger(login_form.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "إنتبه", JOptionPane.PLAIN_MESSAGE);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Login;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPasswordField jPasswordField1;
    // End of variables declaration//GEN-END:variables
}

package view;

import dataBase.SqlServerStartup;
import utils.*;
import exceptions.*;
import formController.MainFormController;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutionException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Mainform extends javax.swing.JFrame {

    private final String Version = "V 4.0.0";
    protected final MainFormController formController;

    public Mainform(Statement st) throws DatabaseException, BusinessException, SQLException {
        initComponents();
        formController = new MainFormController(this, Version, jSplitPane1, left_panel, rootPane, st);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        left_panel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("mizan program " + Version);
        setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        setLocation(new java.awt.Point(0, 0));
        setLocationByPlatform(true);
        setMaximumSize(new java.awt.Dimension(1020, 700));
        setMinimumSize(new java.awt.Dimension(1020, 700));
        setName("Main_Frame"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1020, 700));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jSplitPane1.setDividerLocation(840);
        jSplitPane1.setEnabled(false);
        jSplitPane1.setFocusable(false);
        jSplitPane1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jSplitPane1.setMaximumSize(new java.awt.Dimension(1020, 655));
        jSplitPane1.setMinimumSize(new java.awt.Dimension(1020, 655));
        jSplitPane1.setName(""); // NOI18N
        jSplitPane1.setPreferredSize(new java.awt.Dimension(1010, 650));

        left_panel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        left_panel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        left_panel.setMaximumSize(new java.awt.Dimension(895, 650));
        left_panel.setMinimumSize(new java.awt.Dimension(840, 645));
        left_panel.setPreferredSize(new java.awt.Dimension(840, 645));
        left_panel.setLayout(new java.awt.CardLayout());
        jSplitPane1.setLeftComponent(left_panel);
        left_panel.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1020, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 660, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getAccessibleContext().setAccessibleDescription("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            Config.save();
            System.exit(NORMAL);
        } catch (BusinessException ex) {
            formController.onError(ex);
        }
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        SwingUtilities.invokeLater(() -> {
            JFrame loading = new JFrame("Mizan");
            loading.setDefaultCloseOperation(
                    JFrame.EXIT_ON_CLOSE
            );

            JLabel message = new JLabel(
                    "Starting SQL Server and connecting to database..."
            );

            loading.add(message);
            loading.setSize(440, 110);
            loading.setLocationRelativeTo(null);
            loading.setVisible(true);

            SwingWorker<Statement, Void> worker = new SwingWorker<>() {

                @Override
                protected Statement doInBackground()
                        throws Exception {
                    return SqlServerStartup.connectWhenReady();
                }

                @Override
                protected void done() {
                    loading.dispose();

                    try {
                        Statement st = get();

                        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

                        Mainform mainFrame = new Mainform(st);
                        mainFrame.setLocationRelativeTo(null);
                        mainFrame.setVisible(true);

                    } catch (BusinessException | DatabaseException | InterruptedException | SQLException | ExecutionException ex) {

                        JOptionPane.showMessageDialog(
                                null,
                                "Unable to connect to SQL Server.\n\n"
                                + ex.getLocalizedMessage(),
                                "Database Startup Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                        System.getLogger(Mainform.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                    }
                }
            };

            worker.execute();
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JPanel left_panel;
    // End of variables declaration//GEN-END:variables
}

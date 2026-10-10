package view;

import formController.PauseFormController;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PausePanel extends javax.swing.JPanel {

    protected final PauseFormController formController;

    public PausePanel(JButton panelOpener, JPanel leftPanel) {
        initComponents();
        formController = new PauseFormController(panelOpener, this, leftPanel, jTextArea_emp);
        formController.init();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane9 = new javax.swing.JScrollPane();
        jTextArea_emp = new javax.swing.JTextArea();
        jLabel38 = new javax.swing.JLabel();

        setEnabled(false);
        setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        setMaximumSize(new java.awt.Dimension(835, 640));
        setMinimumSize(new java.awt.Dimension(835, 640));
        setName("emp"); // NOI18N
        setPreferredSize(new java.awt.Dimension(835, 640));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextArea_emp.setColumns(20);
        jTextArea_emp.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jTextArea_emp.setRows(5);
        jScrollPane9.setViewportView(jTextArea_emp);

        add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 820, 550));

        jLabel38.setFont(new java.awt.Font("Tahoma", 3, 48)); // NOI18N
        jLabel38.setText("اي حاجة");
        jLabel38.setEnabled(false);
        jLabel38.setFocusable(false);
        add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, 290, 60));

        getAccessibleContext().setAccessibleParent(this);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JLabel jLabel38;
    protected javax.swing.JScrollPane jScrollPane9;
    protected javax.swing.JTextArea jTextArea_emp;
    // End of variables declaration//GEN-END:variables
}

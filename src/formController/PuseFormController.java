package formController;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import utils.Helper;

public class PuseFormController {

    private final JButton formOpenerBtn;
    private final JPanel pausePanel;
    private final JPanel leftPanel;
    private final JTextArea EmptyTextArea;

    public PuseFormController(JButton formOpenerBtn, JPanel pausePanel, JPanel leftPanel, JTextArea EmptyTextArea) {
        this.formOpenerBtn = formOpenerBtn;
        this.pausePanel = pausePanel;
        this.leftPanel = leftPanel;
        this.EmptyTextArea = EmptyTextArea;
    }

    public void init() {

        this.formOpenerBtn.addActionListener((ActionEvent evt) -> {
            Helper.openPanel(leftPanel, pausePanel);
            EmptyTextArea.requestFocusInWindow();
            EmptyTextArea.setText("");
        });

    }

}

package formController;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import utils.utils;

public class pauseFormController {

    private final JButton formOpenerBtn;
    private final JPanel pausePanel;
    private final JPanel leftPanel;
    private final JTextArea EmptyTextArea;

    public pauseFormController(JButton formOpenerBtn, JPanel pausePanel, JPanel leftPanel, JTextArea EmptyTextArea) {
        this.formOpenerBtn = formOpenerBtn;
        this.pausePanel = pausePanel;
        this.leftPanel = leftPanel;
        this.EmptyTextArea = EmptyTextArea;
    }

    public void init() {

        this.formOpenerBtn.addActionListener((ActionEvent evt) -> {
            utils.openPanel(leftPanel, pausePanel);
            EmptyTextArea.requestFocusInWindow();
            EmptyTextArea.setText("");
        });

    }

}

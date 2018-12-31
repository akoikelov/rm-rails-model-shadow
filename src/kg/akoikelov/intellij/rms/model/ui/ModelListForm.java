package kg.akoikelov.intellij.rms.model.ui;

import com.intellij.ui.components.JBList;

import javax.swing.*;

public class ModelListForm {

    private JPanel rootPanel;
    private JBList modelList;

    private void createUIComponents() {
        rootPanel = new JPanel();
        modelList = new JBList();
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}

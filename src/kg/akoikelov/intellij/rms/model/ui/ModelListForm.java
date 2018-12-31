package kg.akoikelov.intellij.rms.model.ui;

import com.intellij.ui.components.JBList;
import kg.akoikelov.intellij.rms.model.interfaces.SchemaChangeListener;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ModelListForm implements SchemaChangeListener {

    private JPanel rootPanel;
    private JBList modelList;
    private HashMap<String, ArrayList<String>> models;

    public ModelListForm(HashMap<String, ArrayList<String>> models) {
        this.models = models;
    }

    private void createUIComponents() {
        rootPanel = new JPanel();
        modelList = new JBList();
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    @Override
    public void schemaChanged(HashMap<String, ArrayList<String>> models) {

    }
}

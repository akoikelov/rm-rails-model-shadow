package kg.akoikelov.intellij.rms.model.ui;

import com.intellij.ui.components.JBScrollPane;
import kg.akoikelov.intellij.rms.model.interfaces.SchemaChangeListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static javax.swing.BoxLayout.Y_AXIS;

public class ModelListForm implements SchemaChangeListener {

    private JPanel rootPanel;
    private HashMap<String, ArrayList<String>> models;

    public ModelListForm(HashMap<String, ArrayList<String>> models) {
        this.models = models;
        createList();
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void createList() {
        JBScrollPane scrollPane = new JBScrollPane();
        JPanel innerPanel = new JPanel();

        innerPanel.setLayout(new BoxLayout(innerPanel, Y_AXIS));
        innerPanel.setAutoscrolls(true);

        for (Map.Entry entry: models.entrySet()) {
            innerPanel.add(new ModelItemForm(entry).build());
        }

        scrollPane.setViewportView(innerPanel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        rootPanel.add(scrollPane, BorderLayout.CENTER);
    }

    @Override
    public void schemaChanged(HashMap<String, ArrayList<String>> models) {
        this.models = models;
        createList();
    }
}

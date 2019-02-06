package kg.akoikelov.intellij.rms.model.ui;

import com.intellij.ui.components.JBScrollPane;
import kg.akoikelov.intellij.rms.model.interfaces.SchemaChangeListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import static javax.swing.BoxLayout.Y_AXIS;

public class ModelListForm implements SchemaChangeListener {

    private JPanel rootPanel;
    private TreeMap<String, ArrayList<String>> models;

    public ModelListForm(TreeMap<String, ArrayList<String>> models) {
        this.models = models;
        createList(false);
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void createList(boolean updateList) {
        if (updateList) {
            rootPanel.removeAll();
        }

        JBScrollPane scrollPane = new JBScrollPane();
        JPanel innerPanel = new JPanel();

        innerPanel.setLayout(new BoxLayout(innerPanel, Y_AXIS));
        innerPanel.setAutoscrolls(true);

        for (Map.Entry entry: models.entrySet()) {
            innerPanel.add(new ModelItemForm(entry).build());
        }

        scrollPane.setViewportView(innerPanel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        rootPanel.add(scrollPane, BorderLayout.CENTER);
        rootPanel.paintComponents(rootPanel.getGraphics());
    }

    @Override
    public void schemaChanged(TreeMap<String, ArrayList<String>> models) {
        this.models = models;
        createList(true);
    }
}

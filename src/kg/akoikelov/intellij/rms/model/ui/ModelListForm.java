package kg.akoikelov.intellij.rms.model.ui;

import com.intellij.ui.components.JBScrollPane;
import kg.akoikelov.intellij.rms.model.interfaces.SchemaChangeListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import static javax.swing.BoxLayout.Y_AXIS;

public class ModelListForm implements SchemaChangeListener, DocumentListener {

    private JPanel rootPanel;
    private JTextField searchField;
    private JPanel container;
    private TreeMap<String, ArrayList<String>> models;
    private String filterWord = "";

    public ModelListForm(TreeMap<String, ArrayList<String>> models) {
        this.models = models;
        createList(false);

        searchField.getDocument().addDocumentListener(this);
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void createList(boolean updateList) {
        if (updateList) {
            container.removeAll();
        }

        JBScrollPane scrollPane = new JBScrollPane();
        JPanel innerPanel = new JPanel();

        innerPanel.setLayout(new BoxLayout(innerPanel, Y_AXIS));
        innerPanel.setAutoscrolls(true);

        for (Map.Entry entry: models.entrySet()) {
            if (this.filterWord.equals("") || ((String) entry.getKey()).contains(this.filterWord)) {
                innerPanel.add(new ModelItemForm(entry, true).build());
            }
        }

        scrollPane.setViewportView(innerPanel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        container.add(scrollPane, BorderLayout.CENTER);
        container.paintComponents(container.getGraphics());
    }

    @Override
    public void schemaChanged(TreeMap<String, ArrayList<String>> models) {
        this.models = models;
        createList(true);
    }

    public void filter(String text) {
        this.filterWord = text;
        createList(true);
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        String word = getSearchText(e);

        if (word != null) {
            filter(word);
        }

    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        insertUpdate(e);
    }

    private String getSearchText(DocumentEvent e) {
        Document doc = e.getDocument();

        try {
            return doc.getText(doc.getStartPosition().getOffset(), doc.getLength());
        } catch (BadLocationException e1) {
            e1.printStackTrace();
        }

        return null;
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        // not implemented
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}

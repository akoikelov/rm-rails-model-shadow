package kg.akoikelov.intellij.rms.model.ui;

import com.intellij.ui.CollectionListModel;
import com.intellij.ui.components.JBList;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

public class ModelItemForm {
    private boolean includeIndex;
    private JLabel modelName;
    private JBList fieldsList;
    private JPanel rootContainer;
    private Map.Entry entry;

    public ModelItemForm(Map.Entry entry, boolean includeIndex) {
        this.entry = entry;
        this.includeIndex = includeIndex;
    }

    public Component build() {
        modelName.setText((String) entry.getKey());

        CollectionListModel<String> items = new CollectionListModel<>();

        for (String i: (ArrayList<String>) entry.getValue()) {
            if (!this.includeIndex && i.startsWith("t.index")) {
                continue;
            }

            items.add(i);
        }

        fieldsList.setModel(items);

        return rootContainer;
    }
}

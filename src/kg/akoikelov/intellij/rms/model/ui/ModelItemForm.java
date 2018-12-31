package kg.akoikelov.intellij.rms.model.ui;

import com.intellij.ui.CollectionListModel;
import com.intellij.ui.components.JBList;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

public class ModelItemForm {
    private JLabel modelName;
    private JBList fieldsList;
    private JPanel rootContainer;
    private Map.Entry entry;

    ModelItemForm(Map.Entry entry) {
        this.entry = entry;
    }

    Component build() {
        modelName.setText((String) entry.getKey());

        CollectionListModel<String> items = new CollectionListModel<>();

        for (String i: (ArrayList<String>) entry.getValue()) {
            items.add(i);
        }

        fieldsList.setModel(items);

        return rootContainer;
    }
}

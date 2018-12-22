package kg.akoikelov.intellij.rms.model.ui;

import com.intellij.openapi.wm.ToolWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowModelFieldsButton extends JButton implements ActionListener {

    private final String modelName;
    private final ToolWindow modelFieldsToolWindow;

    public ShowModelFieldsButton(String buttonText, ToolWindow modelFieldsToolWindow, String modelName) {
        super(buttonText);

        this.modelName = modelName;
        this.modelFieldsToolWindow = modelFieldsToolWindow;

        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (modelFieldsToolWindow != null) {

        }
    }

}

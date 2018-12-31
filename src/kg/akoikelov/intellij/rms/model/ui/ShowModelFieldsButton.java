package kg.akoikelov.intellij.rms.model.ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowModelFieldsButton extends JButton implements ActionListener {

    private final String modelName;
    private final ToolWindow modelFieldsToolWindow;
    private final Project project;

    public ShowModelFieldsButton(String buttonText, ToolWindow modelFieldsToolWindow, String modelName, Project project) {
        super(buttonText);

        this.modelName = modelName;
        this.modelFieldsToolWindow = modelFieldsToolWindow;
        this.project = project;

        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (modelFieldsToolWindow != null) {
            modelFieldsToolWindow.show(null);
        }
    }

}

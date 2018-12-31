package kg.akoikelov.intellij.rms.model.twfactory;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import kg.akoikelov.intellij.rms.model.ui.ModelListForm;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class ModelFieldsToolWindowFactory implements ToolWindowFactory {

    public static final String ID = "rails-model-fields";

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        toolWindow.getComponent().add(this.createTablesList());
    }

    private Component createTablesList() {
        return new ModelListForm().getRootPanel();
    }
}

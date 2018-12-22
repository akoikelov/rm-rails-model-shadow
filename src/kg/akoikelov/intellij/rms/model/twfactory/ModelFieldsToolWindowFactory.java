package kg.akoikelov.intellij.rms.model.twfactory;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import org.jetbrains.annotations.NotNull;

public class ModelFieldsToolWindowFactory implements ToolWindowFactory {

    public static final String ID = "rails-model-fields";

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
    }

}

package kg.akoikelov.intellij.rms.model.twfactory;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import kg.akoikelov.intellij.rms.model.SchemaParser;
import kg.akoikelov.intellij.rms.model.ui.ModelListForm;
import org.jetbrains.annotations.NotNull;

public class ModelFieldsToolWindowFactory implements ToolWindowFactory {

    public static final String ID = "rails-model-fields";

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        SchemaParser schemaParser = SchemaParser.getInstance(project);
        ModelListForm form = new ModelListForm(schemaParser.getModels());

        schemaParser.addChangeListener(form);
        toolWindow.getComponent().add(form.getRootPanel());
    }
}

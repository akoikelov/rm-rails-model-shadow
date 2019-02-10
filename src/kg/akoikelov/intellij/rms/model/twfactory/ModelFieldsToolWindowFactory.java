package kg.akoikelov.intellij.rms.model.twfactory;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import kg.akoikelov.intellij.rms.model.SchemaParser;
import kg.akoikelov.intellij.rms.model.ui.ModelListForm;
import kg.akoikelov.intellij.rms.services.ModelShadowService;
import org.jetbrains.annotations.NotNull;

public class ModelFieldsToolWindowFactory implements ToolWindowFactory {

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        SchemaParser parser = ModelShadowService.parsers.get(project.getName());
        ModelListForm form = new ModelListForm(parser.getModels());

        parser.addChangeListener(form);
        toolWindow.getComponent().add(form.getRootPanel());
    }
}

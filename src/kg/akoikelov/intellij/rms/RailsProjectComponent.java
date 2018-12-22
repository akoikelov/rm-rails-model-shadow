package kg.akoikelov.intellij.rms;

import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;
import kg.akoikelov.intellij.rms.core.interfaces.IService;
import kg.akoikelov.intellij.rms.services.ModelShadowService;

public class RailsProjectComponent implements ProjectComponent {

    private Project currentProject;
    private IService[] services = new IService[] {
        new ModelShadowService()
    };

    public RailsProjectComponent(Project currentProject) {
        this.currentProject = currentProject;
    }

    @Override
    public void projectOpened() {
        for (IService service: services) {
            service.init(currentProject);
        }
    }
}

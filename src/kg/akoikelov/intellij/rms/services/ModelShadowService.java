package kg.akoikelov.intellij.rms.services;

import com.intellij.openapi.project.Project;
import com.intellij.util.messages.MessageBus;
import kg.akoikelov.intellij.rms.core.interfaces.IService;
import kg.akoikelov.intellij.rms.model.ModelFileManager;

import static com.intellij.openapi.vfs.VirtualFileManager.VFS_CHANGES;

public class ModelShadowService implements IService {

    @Override
    public void init(Project currentProject) {
        MessageBus messageBus = currentProject.getMessageBus();
        ModelFileManager modelFileManager = new ModelFileManager(currentProject);

        messageBus.connect().subscribe(VFS_CHANGES, modelFileManager);
    }

}

package kg.akoikelov.intellij.rms.services;

import com.intellij.openapi.project.Project;
import com.intellij.util.messages.MessageBus;
import kg.akoikelov.intellij.rms.core.interfaces.IService;
import kg.akoikelov.intellij.rms.model.ModelFileManager;
import kg.akoikelov.intellij.rms.model.SchemaParser;

import java.util.HashMap;

import static com.intellij.openapi.vfs.VirtualFileManager.VFS_CHANGES;

public class ModelShadowService implements IService {

    public static HashMap<String, SchemaParser> parsers = new HashMap<>();

    @Override
    public void init(Project currentProject) {
        parsers.put(currentProject.getName(), new SchemaParser(currentProject));

        MessageBus messageBus = currentProject.getMessageBus();
        ModelFileManager modelFileManager = new ModelFileManager(currentProject);

        messageBus.connect().subscribe(VFS_CHANGES, modelFileManager);
    }

}

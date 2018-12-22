package kg.akoikelov.intellij.rms.services;

import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.openapi.project.Project;
import com.intellij.util.messages.MessageBus;
import kg.akoikelov.intellij.rms.core.interfaces.IService;
import kg.akoikelov.intellij.rms.model.ModelFileManager;

public class ModelShadowService implements IService {

    @Override
    public void init(Project currentProject) {
        MessageBus messageBus = currentProject.getMessageBus();

        messageBus.connect().subscribe(FileEditorManagerListener.FILE_EDITOR_MANAGER, new ModelFileManager(currentProject));
    }

}

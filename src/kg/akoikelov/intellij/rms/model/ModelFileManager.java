package kg.akoikelov.intellij.rms.model;

import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.newvfs.BulkFileListener;
import com.intellij.openapi.vfs.newvfs.events.VFileEvent;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import kg.akoikelov.intellij.rms.model.twfactory.ModelFieldsToolWindowFactory;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ModelFileManager implements FileEditorManagerListener, BulkFileListener {

    private final ToolWindowManager toolWindowManager;
    private Project currentProject;
    private ToolWindow modelFieldsToolWindow;

    public ModelFileManager(Project currentProject) {
        this.currentProject = currentProject;
        this.toolWindowManager = ToolWindowManager.getInstance(currentProject);
    }

    @Override
    public void fileOpened(@NotNull FileEditorManager source, @NotNull VirtualFile virtualFile) {
        if(this.isModelClassFile(virtualFile)) {
            if (modelFieldsToolWindow == null) {
                modelFieldsToolWindow = toolWindowManager.getToolWindow(ModelFieldsToolWindowFactory.ID);
            }
        }
    }

    private boolean isModelClassFile(VirtualFile file) {
        return file.getPath().equals(this.currentProject.getBasePath() + "/app/models/" + file.getName());
    }

    @Override
    public void after(@NotNull List<? extends VFileEvent> events) {
        if (events.toString().contains("db/schema.rb")) {
            SchemaParser.getInstance(currentProject).triggerChange();
        }
    }
}

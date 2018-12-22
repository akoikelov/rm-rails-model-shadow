package kg.akoikelov.intellij.rms.model;

import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.openapi.fileEditor.impl.FileEditorManagerImpl;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import kg.akoikelov.intellij.rms.model.ui.ShowModelFieldsButton;
import org.jetbrains.annotations.NotNull;

public class ModelFileManager implements FileEditorManagerListener {

    private Project currentProject;
    private FileEditorManager editorManager;
    private ToolWindow modelFieldsToolWindow;

    public ModelFileManager(Project currentProject) {
        this.currentProject = currentProject;
        this.editorManager = FileEditorManagerImpl.getInstance(currentProject);
        this.modelFieldsToolWindow = ToolWindowManager.getInstance(currentProject).getToolWindow("rails-model-fields");
    }

    @Override
    public void fileOpened(@NotNull FileEditorManager source, @NotNull VirtualFile virtualFile) {
        if(this.isModelClassFile(virtualFile)) {
            FileEditor editor = source.getSelectedEditor();

            if (editor != null) {
                editorManager.addBottomComponent(editor,
                        new ShowModelFieldsButton("Show model fields", modelFieldsToolWindow,
                                virtualFile.getNameWithoutExtension()));
            }
        }
    }

    private boolean isModelClassFile(VirtualFile file) {
        return file.getPath().equals(this.currentProject.getBasePath() + "/app/models/" + file.getName());
    }
}

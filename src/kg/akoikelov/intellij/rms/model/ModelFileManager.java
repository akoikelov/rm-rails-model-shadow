package kg.akoikelov.intellij.rms.model;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.newvfs.BulkFileListener;
import com.intellij.openapi.vfs.newvfs.events.VFileEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ModelFileManager implements BulkFileListener {

    private Project currentProject;

    public ModelFileManager(Project currentProject) {
        this.currentProject = currentProject;
    }

    @Override
    public void after(@NotNull List<? extends VFileEvent> events) {
        if (events.toString().contains("db/schema.rb")) {
            SchemaParser.getInstance(currentProject).triggerChange();
        }
    }
}

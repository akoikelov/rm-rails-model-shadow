package kg.akoikelov.intellij.rms.model;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.newvfs.BulkFileListener;
import com.intellij.openapi.vfs.newvfs.events.VFileEvent;
import kg.akoikelov.intellij.rms.services.ModelShadowService;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ModelFileManager implements BulkFileListener {

    private SchemaParser parser;

    public ModelFileManager(Project currentProject) {
        this.parser = ModelShadowService.parsers.get(currentProject.getName());
    }

    @Override
    public void after(@NotNull List<? extends VFileEvent> events) {
        if (events.toString().contains("db/schema.rb")) {
            parser.triggerChange();
        }
    }
}

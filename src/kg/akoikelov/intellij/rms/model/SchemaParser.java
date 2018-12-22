package kg.akoikelov.intellij.rms.model;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileSystem;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import org.jetbrains.annotations.Nullable;

public class SchemaParser {

    private static SchemaParser ourInstance;
    private final Project project;
    private final VirtualFileSystem filesystem;
    private final String SCHEMA_FILE_PATH = "%s/db/schema.rb";
    private final PsiManager psiManager;

    public static SchemaParser getInstance(Project project) {
        if (ourInstance == null) {
            ourInstance = new SchemaParser(project);
        }

        return ourInstance;
    }

    private SchemaParser(Project project) {
        this.project = project;
        this.filesystem = this.project.getProjectFile().getFileSystem();
        this.psiManager = PsiManager.getInstance(this.project);
    }

    public void getModelsInfo() {
        PsiFile schemaPsi = getPsiFile();

        if (schemaPsi != null) {

        }
    }

    @Nullable
    private PsiFile getPsiFile() {
        if (filesystem != null) {
            VirtualFile file = filesystem.findFileByPath(String.format(SCHEMA_FILE_PATH, this.project.getBasePath()));

            if (file != null) {
                return psiManager.findFile(file);
            }
        }

        return null;
    }
}

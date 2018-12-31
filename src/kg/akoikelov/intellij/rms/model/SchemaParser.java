package kg.akoikelov.intellij.rms.model;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileSystem;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SchemaParser {

    private static SchemaParser ourInstance;
    private final Project project;
    private final VirtualFileSystem filesystem;
    private final String SCHEMA_FILE_PATH = "%s/db/schema.rb";
    private final PsiManager psiManager;
    private HashMap<String, ArrayList<String>> models = new HashMap<>();

    private Pattern tableNamePattern = Pattern.compile("\"([a-zA-Z0-9]+)\"");

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

    public HashMap<String, ArrayList<String>> getModels() {
        if (models.size() == 0) {
            syncModels();
        }

        return models;
    }

    public void syncModels() {
        PsiFile schemaPsi = getPsiFile();
        boolean tableDefStart = false;
        ArrayList<String> tableFields = new ArrayList<>();
        String currentTable = "";

        if (schemaPsi != null) {
            String[] tokens = schemaPsi.getText().split("\n");

            for (String token: tokens) {
                token = token.trim();

                if (token.startsWith("create_table")) {
                    tableDefStart = true;
                    currentTable = getTableName(token);
                } else if (token.startsWith("end")) {
                    if (tableFields.size() > 0) {
                        models.put(currentTable, tableFields);
                    }

                    tableFields = new ArrayList<>();
                    tableDefStart = false;
                } else if (tableDefStart) {
                    tableFields.add(token);
                }
            }
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

    private String getTableName(String token) {
        String tableName = "unknown";
        Matcher matcher = tableNamePattern.matcher(token);

        while (matcher.find()) {
            tableName = matcher.group(1);
            break;
        }

        return tableName;
    }
}

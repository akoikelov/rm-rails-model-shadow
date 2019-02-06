package kg.akoikelov.intellij.rms.model;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileSystem;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import kg.akoikelov.intellij.rms.model.interfaces.SchemaChangeListener;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SchemaParser {

    private static SchemaParser ourInstance;
    private final Project project;
    private final VirtualFileSystem filesystem;
    private final String SCHEMA_FILE_PATH = "%s/db/schema.rb";
    private final PsiManager psiManager;
    private TreeMap<String, ArrayList<String>> models = new TreeMap<>();
    private List<SchemaChangeListener> changeListeners = new ArrayList<>();

    private Pattern tableNamePattern = Pattern.compile("\"([a-zA-Z0-9_]+)\"");

    public static SchemaParser getInstance(Project project) {
        if (ourInstance == null) {
            ourInstance = new SchemaParser(project);
        }

        return ourInstance;
    }

    public TreeMap<String, ArrayList<String>> getModels() {
        if (models.size() == 0) {
            syncModels();
        }

        return models;
    }

    public void addChangeListener(SchemaChangeListener listener) {
        this.changeListeners.add(listener);
    }

    void triggerChange() {
        this.syncModels();

        for (SchemaChangeListener l: this.changeListeners) {
            l.schemaChanged(this.models);
        }
    }

    private SchemaParser(Project project) {
        this.project = project;
        this.filesystem = this.project.getProjectFile().getFileSystem();
        this.psiManager = PsiManager.getInstance(this.project);
    }

    private void syncModels() {
        PsiFile schemaPsi = getPsiFile();
        boolean tableDefStart = false;
        ArrayList<String> tableFields = new ArrayList<>();
        String currentTable = "";

        if (schemaPsi != null) {
            String[] tokens = schemaPsi.getText().split("\n");

            for (String token: tokens) {
                token = token.trim().replaceAll("\\s+", " ");

                if (token.startsWith("create_table")) {
                    tableDefStart = true;
                    currentTable = getTableName(token);
                } else if (token.startsWith("end")) {
                    if (tableFields.size() > 0) {
                        tableFields.sort((o1, o2) -> {
                            if (o1.contains("_id") && !o2.contains("_id")) {
                                return 1;
                            } else if (!o1.contains("_id") && o2.contains("_id")) {
                                return -1;
                            }

                            return 0;
                        });

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

        if (matcher.find()) {
            tableName = matcher.group(1);
        }

        return tableName;
    }
}

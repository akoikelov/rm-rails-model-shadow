package kg.akoikelov.intellij.rms.model.interfaces;

import java.util.ArrayList;
import java.util.HashMap;

public interface SchemaChangeListener {
    void schemaChanged(HashMap<String, ArrayList<String>> models);
}

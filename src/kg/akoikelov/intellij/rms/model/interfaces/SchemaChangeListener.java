package kg.akoikelov.intellij.rms.model.interfaces;

import java.util.ArrayList;
import java.util.TreeMap;

public interface SchemaChangeListener {
    void schemaChanged(TreeMap<String, ArrayList<String>> models);
}

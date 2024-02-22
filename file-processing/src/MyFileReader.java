import java.io.File;
import java.util.TreeMap;

public interface MyFileReader {
    File getFileFromPathArgs(String path);
    TreeMap<String,Integer> toSortedNameToAgeContainer(File file);
    void toSortedNameToAgeFile(TreeMap<String,Integer> container);
}

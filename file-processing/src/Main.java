import java.io.File;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        MyFileReaderImpl fileReader = new MyFileReaderImpl();
        fileProcessing(fileReader, "./assets/file.txt");
    }

    public static void fileProcessing(MyFileReader fileReader, String filePath) {
        File file = fileReader.getFileFromPathArgs(filePath);
        TreeMap<String, Integer> container = fileReader.toSortedNameToAgeContainer(file);
        fileReader.toSortedNameToAgeFile(container);
    }
}
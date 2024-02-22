import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class MyFileReaderImpl implements MyFileReader{
    @Override
    public File getFileFromPathArgs(String path) {
        return new File(path);
    }

    @Override
    public TreeMap<String, Integer> toSortedNameToAgeContainer(File file) {
        try {
            TreeMap<String, Integer> container = new TreeMap<>();
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()){
                String currentLine = sc.nextLine();
                String[] nameAndAge = currentLine.split(",");
                container.put(nameAndAge[0],Integer.parseInt(nameAndAge[1]));
            }
            return container;

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        return null;
    }

    @Override
    public void toSortedNameToAgeFile(TreeMap<String, Integer> container) {
        try {
            File newFile = new File("./assets/sortedNames.txt");
            if (newFile.createNewFile()){
                try (FileWriter writer = new FileWriter("./assets/sortedNames.txt")) {
                    for(Map.Entry<String, Integer> entry: container.entrySet()) {
                        String name = entry.getKey();
                        String age = entry.getValue().toString();
                        writer.write(name+","+age);
                        writer.write("\n");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

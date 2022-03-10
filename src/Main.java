import files.ReadSgmFile;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ReadSgmFile f = new ReadSgmFile();
        List<List<String>> files = f.readFiles();
            for(String line : files.get(0)){
                System.out.println(line);
            }
    }
}

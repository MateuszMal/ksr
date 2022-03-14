import files.ExtractFiles;
import files.ReadSgmFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ReadSgmFile f = new ReadSgmFile();
        List<List<String>> files = f.readFiles();

        ExtractFiles ex = new ExtractFiles();

        HashMap<String, List<String>> articles = ex.countriesAndArticles(files.get(0)); // zwraca mape gdzie key - kraj, value - lista artykulow
        for(Map.Entry<String, List<String >> entry : articles.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
            System.out.println("/////////////////");
        }
    }
}

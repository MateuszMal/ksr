import attribute.WordOccurrenceMatrix;
import commons.StringFormatter;
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

        StringFormatter.format_whole_hash_map(articles);

        WordOccurrenceMatrix matrix = new WordOccurrenceMatrix(articles);

        matrix.print_matrix();

        matrix.count_for_whole_database2(articles);

        matrix.print_matrix();

        System.out.println(matrix.get_word_that_occurred_the_most("usa"));


    }
}

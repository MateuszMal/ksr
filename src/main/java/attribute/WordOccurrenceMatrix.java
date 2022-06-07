package attribute;

import commons.Commons;
import commons.StringFormatter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordOccurrenceMatrix {

    int rows = 0;
    int columns = 0;

    private HashMap<String, Integer> words_hash_map;
    private HashMap<String, Integer> country_hash_map;
    private int[][] values_array;


    public WordOccurrenceMatrix(HashMap<String, List<String>> texts_hash) {
        String temp = StringFormatter.remove_separators(Commons.create_whole_text_string(texts_hash));

        String[] words_array = temp.split(" ");

        String[] country_array = Commons.generate_country_array(texts_hash);

        Set<String> test_set = new HashSet<String>();

        test_set.addAll(List.of(words_array));

        words_array = (String[]) test_set.toArray(String[]::new);

        words_hash_map = new HashMap();

        for (int i = 0; i < words_array.length; i++) {
            words_hash_map.put(words_array[i], Integer.valueOf(i));
        }

        country_hash_map = new HashMap();

        for (int j = 0; j < country_array.length; j++) {
            country_hash_map.put(country_array[j], Integer.valueOf(j));
        }

        rows = country_hash_map.size();
        columns = words_hash_map.size();

        values_array = new int[rows][columns];

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                values_array[row][column] = 0;
            }
        }

    }

    public void count_words_occurrence(String category_name, String body) {
        String[] body_array = body.split(" ");
        for (int i = 0; i < body_array.length; i++) {
            if (words_hash_map.containsKey(body_array[i])) {
                values_array[country_hash_map.get(category_name)][words_hash_map.get(body_array[i])]++;
            }
        }
    }

    public void print_matrix() {
        System.out.println();
        for (int row = 0; row < country_hash_map.size(); row++) {
            for (int column = 0; column < words_hash_map.size(); column++) {
                System.out.print(values_array[row][column] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }


    public void count_for_whole_database2(HashMap<String, List<String>> texts_hash) {
        for (String category : texts_hash.keySet()) {
            for (int i = 0; i < texts_hash.get(category).size(); i++) {
                count_words_occurrence(category, texts_hash.get(category).get(i));
            }
        }

    }


    public String get_word_that_occurred_the_most(String category) {
        int max_value_position = 0;
        int row = country_hash_map.get(category);
        int max_value = values_array[row][max_value_position];

        for (int current_position = 0; current_position < columns; current_position++) {
            if (values_array[row][current_position] > max_value) {
                max_value = values_array[row][current_position];
                max_value_position = current_position;
            }
        }

        String word = "";

        for (String key : words_hash_map.keySet()) {
            if (words_hash_map.get(key) == max_value_position) {
                word = key;
            }
        }
        return word;
    }


}

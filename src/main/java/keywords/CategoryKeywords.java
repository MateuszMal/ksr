package keywords;

import commons.StringDoubleContainer;
import commons.StringDoubleContainerComparator;

import java.util.ArrayList;
import java.util.Collections;

public class CategoryKeywords {
    private String category;
    private ArrayList<StringDoubleContainer> key_words_array = new ArrayList<>();

    public CategoryKeywords(String name, ArrayList<StringDoubleContainer> input_array) {
        category = name;
        key_words_array = input_array;
    }


    public String getCategory() {
        return category;
    }

    public ArrayList<StringDoubleContainer> getKey_words_array() {
        return key_words_array;
    }


    public void sort_keywords() {
        Collections.sort(key_words_array, new StringDoubleContainerComparator());
    }

    protected void trim_keywords(int nr_of_keywords_to_remember) {
        if (key_words_array.size() > nr_of_keywords_to_remember) {
            int end_point = key_words_array.size() - nr_of_keywords_to_remember;
            for (int i = 0; i < end_point; i++) {
                key_words_array.remove(0);
            }
        }
    }
}

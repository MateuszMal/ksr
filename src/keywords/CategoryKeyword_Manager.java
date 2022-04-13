package keywords;

import commons.StringFormatter;

import java.util.*;

public class CategoryKeyword_Manager { //zmienic nazwe na creator



    public static HashMap<String, ArrayList<String>> create_category_keywords_hash_map_static(HashMap<String, List<String>> all_articles, int number_of_keywords_to_remember)
    {
        ArrayList <CategoryKeywords> category_keywords_list = new ArrayList<>();

        StringFormatter.format_whole_hash_map(all_articles);

        WordOccurrenceMatrix matrix = new WordOccurrenceMatrix(all_articles);

        matrix.count_for_whole_database(all_articles);

        matrix.TFxIDF_matrix();

        for(String s : all_articles.keySet())
        {
            category_keywords_list.add(new CategoryKeywords(s, matrix.get_key_words(s)));
        }

        sort_all_static(category_keywords_list);
        trim_all_static(category_keywords_list, number_of_keywords_to_remember);

        return convert_from_category_keyword_list_to_string_string_hash(category_keywords_list);

    }

    public static void sort_all_static(ArrayList <CategoryKeywords> category_keywords_list)
    {
        for (int i = 0; i < category_keywords_list.size(); i ++)
        {
            category_keywords_list.get(i).sort_keywords();
        }
    }

    public static void trim_all_static(ArrayList <CategoryKeywords> category_keywords_list_2, int number_of_keywords_to_remember)
    {
        for(int i = 0; i < category_keywords_list_2.size(); i ++)
        {
            category_keywords_list_2.get(i).trim_keywords(number_of_keywords_to_remember);
        }
    }

    public static HashMap<String, ArrayList<String>> convert_from_category_keyword_list_to_string_string_hash(ArrayList <CategoryKeywords> category_keywords_list)
    {
        HashMap <String, ArrayList<String>> category_keywords_hash_map = new HashMap<>();

        for (int i = 0; i < category_keywords_list.size(); i ++)
        {
            category_keywords_hash_map.put(category_keywords_list.get(i).getCategory(), new ArrayList<String>());
        }

        for (int i = 0; i < category_keywords_list.size(); i++)
        {
            for(int j = 0; j < category_keywords_list.get(i).getKey_words_array().size(); j++)
            {
                category_keywords_hash_map.get(category_keywords_list.get(i).getCategory()).add(category_keywords_list.get(i).getKey_words_array().get(j).getWord());
            }
        }
        return category_keywords_hash_map;
    }



    public static ArrayList <String> create_and_return_bag_of_words(HashMap<String, ArrayList<String>> keyword_hashmap_for_all_countries)
    {
        Set<String> temporary_bag_of_words = new HashSet<>();
        for(String s : keyword_hashmap_for_all_countries.keySet())
        {
            temporary_bag_of_words.addAll(keyword_hashmap_for_all_countries.get(s));
        }

        ArrayList<String> bag_of_words = new ArrayList<>();
        bag_of_words.addAll(temporary_bag_of_words);

        return bag_of_words;
    }


}

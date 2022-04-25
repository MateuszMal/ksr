package keywords;

import commons.string_double_container;
import commons.string_double_container_comparator;

import java.util.ArrayList;
import java.util.Collections;

public class CategoryKeywords {
    private String category;
    private ArrayList<string_double_container> key_words_array = new ArrayList<>();

    public CategoryKeywords(String name, ArrayList<string_double_container> input_array)
    {
        category = name;
        key_words_array = input_array;
    }


    public String getCategory(){return category;}
    public ArrayList<string_double_container> getKey_words_array(){return key_words_array;}


    public void sort_keywords()
    {
        Collections.sort(key_words_array, new string_double_container_comparator());
    }

    protected void trim_keywords (int nr_of_keywords_to_remember)
    {
        if(key_words_array.size() > nr_of_keywords_to_remember)
        {
            int end_point = key_words_array.size() -  nr_of_keywords_to_remember;
            for(int i = 0; i < end_point; i ++)
            {
                key_words_array.remove(0);
            }
        }

    }

    public static void trim_keywords_static (ArrayList<string_double_container> new_list_att, int nr_of_keywords_to_remember)
    {
       // if(new_list_att.size() > nr_of_keywords_to_remember)
       // {
            /*int end_point = new_list_att.size() -  nr_of_keywords_to_remember;
            for(int i = 0; i < end_point; i ++)
            {
                new_list_att.remove(0);
            }*/

            while (new_list_att.size() > nr_of_keywords_to_remember)
            {
                new_list_att.remove(0);
            }

        //}

    }


}

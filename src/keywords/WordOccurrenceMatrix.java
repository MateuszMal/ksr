package keywords;

import commons.Commons;
import commons.string_double_container;
import commons.StringFormatter;

import java.util.*;

public class WordOccurrenceMatrix {

    int rows = 0;
    int columns = 0;

    private HashMap<String, Integer> words_hash_map;
    private HashMap <String,Integer> country_hash_map;

    private double [][] values_array;


    public WordOccurrenceMatrix(HashMap<String, List<String>> texts_hash)
    {

        String temp = StringFormatter.remove_separators(Commons.create_whole_text_string(texts_hash));
        temp = StringFormatter.remove_separators(temp);

        String [] words_array = temp.split(" ");

        List <String> test_list = Arrays.asList(temp.split(" ")); //dodane

        for(int i = 0; i < test_list.size(); i ++)
        {
            if(test_list.get(i).compareTo("") != 0)
            {
                test_list.set(i, StringFormatter.remove_stuff_from_string(test_list.get(i)));
            }
        }


        String [] country_array = Commons.generate_country_array(texts_hash);

        Set<String> test_set = new HashSet<String>();

        test_set.addAll(test_list);

        test_set.remove("");

        ///todo TU STOPLISTA !!!!!!

        words_array = (String[]) test_set.toArray(String[]::new);

        words_hash_map = new HashMap();

        for (int i = 0; i < words_array.length; i ++)
        {
            words_hash_map.put(words_array[i], Integer.valueOf(i));
        }

        country_hash_map = new HashMap();

        for(int j = 0; j < country_array.length; j ++)
        {
            country_hash_map.put(country_array[j],Integer.valueOf(j));
        }

        rows = country_hash_map.size();
        columns = words_hash_map.size();

        values_array = new double [rows][columns];

        for (int row = 0; row < rows; row ++)
        {
            for(int column = 0; column < columns; column++)
            {
                values_array[row][column] = 0.0;
            }
        }

    }

    public void count_words_occurrence(String category_name, String body)
    {
        String [] body_array = body.split(" ");
        for(int i = 0; i < body_array.length; i++)
        {
            if(words_hash_map.containsKey(body_array[i]))
            {
                values_array[country_hash_map.get(category_name)][words_hash_map.get(body_array[i])] ++;
            }
        }
    }


    public void count_for_whole_database(HashMap<String, List<String>> texts_hash)
    {
        for(String category : texts_hash.keySet())
        {
            for(int i = 0; i < texts_hash.get(category).size(); i ++)
            {
                count_words_occurrence(category, texts_hash.get(category).get(i) );
            }
        }

    }




    public void TFxIDF_matrix()
    {

        double [] total_number_of_words_array = new double [rows];

        for (int row = 0; row < rows; row ++)
        {
            double total_number_of_words_in_a_row = 0.0;

            for(int column = 0; column < columns; column ++)
            {
                total_number_of_words_in_a_row = total_number_of_words_in_a_row + values_array[row][column];
            }
            total_number_of_words_array[row] = total_number_of_words_in_a_row;
        }


        for(int column = 0; column < columns; column ++)
        {
            double how_many_rows_contain_this_row = 0.0;

            for (int row = 0; row < rows; row ++)
            {
                if(values_array[row][column] > 0.0)
                {
                    how_many_rows_contain_this_row = how_many_rows_contain_this_row + 1.0;
                }
            }

            for(int row = 0; row < rows; row ++)
            {
                values_array[row][column] = (values_array[row][column] / total_number_of_words_array[row]) * Math.log10(rows/how_many_rows_contain_this_row);
            }
        }
    }


    public ArrayList<string_double_container> get_key_words(String category)
    {

        ArrayList<string_double_container> new_list_att = new ArrayList<>();

        int row = country_hash_map.get(category);

        for (int column = 0; column < words_hash_map.size(); column ++)
        {
            if(values_array[row][column] > 0.0)
            {
                    String word = "";

                    for (String key : words_hash_map.keySet())
                    {
                        if (words_hash_map.get(key) == column)
                        {
                            word = key;
                        }
                    }

                    new_list_att.add(new string_double_container(word, values_array[row][column]));
            }

        }

        return new_list_att;

    }


}

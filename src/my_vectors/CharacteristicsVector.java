package my_vectors;

import java.util.ArrayList;

public class CharacteristicsVector {

    private String category;
    String assigned_category;

    private double c1 = 0.0;
    private double c2 = 0.0;
    private double c4 = 0.0;
    private double c6 = 0.0;
    private double c7 = 0.0;
    private double c8 = 0.0;
    private double c9 = 0.0;
    private double c10 = 0.0;

    String c3;
    String c5;

    ArrayList<String> to_remove_keywords; //to remove

    String text; //to remove

    private ArrayList<Double> c_list = new ArrayList<>();

    public CharacteristicsVector (String input_category)
    {
        category = input_category;

    }

    public String getCategory(){return category;}

    public void setAssigned_category(String s){assigned_category = s;} //remove
    public String getAssigned_category(){return assigned_category;} //remove

    public ArrayList<Double> getC_list(){return c_list;}

    public void calculate_all_C (String article, ArrayList<String> bag_of_words)
    {
        to_remove_keywords = bag_of_words; //usunac
        text = article; //do usuniecia

        String fragment_of_the_article = CharacteristicsExtractor.percentage_of_text(article,0,0.3);
        ArrayList<String> keywords_for_this_part_of_the_text = CharacteristicsExtractor.get_X_keywords_that_appear_the_most(fragment_of_the_article, bag_of_words, 5);
        c1 = CharacteristicsExtractor.count_X_keywords_occurrence(fragment_of_the_article, keywords_for_this_part_of_the_text);

        fragment_of_the_article = CharacteristicsExtractor.percentage_of_text(article,0.85,1.0);
        keywords_for_this_part_of_the_text = CharacteristicsExtractor.get_X_keywords_that_appear_the_most(fragment_of_the_article, bag_of_words, 5);
        c2 = CharacteristicsExtractor.count_X_keywords_occurrence(fragment_of_the_article, keywords_for_this_part_of_the_text);

        c4 = CharacteristicsExtractor.text_words_count(article);

        c6 = CharacteristicsExtractor.calculate_average_sentence_length(article);

        c7 = CharacteristicsExtractor.calculate_digits(article);

        keywords_for_this_part_of_the_text = CharacteristicsExtractor.get_X_keywords_that_appear_the_most(article, bag_of_words, 2);
        c8 = CharacteristicsExtractor.count_X_keywords_occurrence(article, keywords_for_this_part_of_the_text);


        c9 = CharacteristicsExtractor.particular_word_occurrence(article,"there");

        c10 = CharacteristicsExtractor.particular_word_occurrence(article,"than");

        c3 = CharacteristicsExtractor.get_word_with_more_than_X_characters_that_occurs_the_most(article, 4);

        c5 = CharacteristicsExtractor.get_most_occurring_word_starting_with_capital_letter_not_at_the_beginning_of_the_sentence(article);

        c_list.add(c1);
        c_list.add(c2);
        c_list.add(c4);
        c_list.add(c6);
        c_list.add(c7);
        c_list.add(c8);
        c_list.add(c9);
        c_list.add(c10);

        //c_list.add(0.0); //c3
        //c_list.add(0.0); //c5
    }

    public String getC3(){return c3;}
    public String getC5(){return c5;}

    public void set_cx_array(double input, int index)
    {
        c_list.set(index, input);
    }

    public void add_to_cx_array(double input)
    {
        c_list.add(input);
    }

}

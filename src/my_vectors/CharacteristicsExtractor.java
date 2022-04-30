package my_vectors;

import commons.StringFormatter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

public class CharacteristicsExtractor {

    public static double calculate_digits(String article) {
        char[] array = article.toCharArray();

        double counter = 0.0;

        for (int i = 0; i < array.length; i++) {
            if ((int) array[i] > 47 && (int) array[i] < 58) {
                counter = counter + 1;
            }
        }
        return counter;
    }

    public static double calculate_average_sentence_length(String article) {
        if (article.compareTo("") == 0) {
            return 0.0;
        }

        double total_words = CharacteristicsExtractor.text_words_count(article);

        if (!article.contains(". ")) {
            return total_words;
        }

        String[] number_of_sentences = article.split("\\.\\s");
        return total_words / number_of_sentences.length;


    }

    public static double text_words_count(String article) {
        String[] array = article.split(" ");
        return array.length;
    }

    public static double particular_word_occurrence(String article, String word) //to chyba do poprawy mozna lepiej napisac
    {
        String str = word;
        String temp = article;
        temp.replace(".", "");

        String upper_word = str.substring(0, 1).toUpperCase() + str.substring(1);
        //add dot removal
        String[] processes_article = article.split(" ");
        double counter = 0.0;
        for (int i = 0; i < processes_article.length; i++) {
            if (processes_article[i].compareTo(word) == 0 || processes_article[i].compareTo(upper_word) == 0) {
                counter = counter + 1.0;
            }
        }
        return counter;
    }

    public static String percentage_of_text(String text, double start, double end) {

        String[] count_words = text.split(" ");
        int start_index = (int) Math.round(start * (count_words.length));
        int end_index = (int) Math.round(end * (count_words.length));

        String output = "";

        for (int i = start_index; i < end_index; i++) {
            output = output + count_words[i] + " ";
        }
        return output;
    }

    public static ArrayList<String> get_X_keywords_that_appear_the_most(String text, ArrayList<String> bag_of_words, int number_of_keywords) //tu wrzucamy cały worek
    {

        int[] keywords_counter = new int[bag_of_words.size()];

        for (int i = 0; i < bag_of_words.size(); i++) {
            String[] smart_count = text.split(bag_of_words.get(i));
            keywords_counter[i] = smart_count.length - 1;
        }

        ArrayList<String> keywords_for_this_text = new ArrayList<>();

        for (int i = 0; i < number_of_keywords; i++) {
            int value = 0;

            int j;
            int position = 0;

            for (j = 0; j < keywords_counter.length; j++) {
                if (value < keywords_counter[j] && keywords_for_this_text.contains(bag_of_words.get(j)) == false) {
                    value = keywords_counter[j];
                    position = j;
                }
            }

            if (value > 0) {
                keywords_for_this_text.add(bag_of_words.get(position));
            }
        }

        return keywords_for_this_text;
    }

    public static double count_X_keywords_occurrence(String text_fragment, ArrayList<String> keywords) {
        String[] total_words_counter = text_fragment.split(" ");

        double counter = 0.0;

        for (int i = 0; i < keywords.size(); i++) {
            String[] temp = text_fragment.split(keywords.get(i));
            counter = counter + (temp.length - 1);

        }

        return counter / total_words_counter.length;
    }

    public static String get_word_with_more_than_X_characters_that_occurs_the_most(String article, int numberOfCharacters) {
        if (article.compareTo("") == 0) {
            return "";
        }

        int max_occurrence = 0;
        String temp = StringFormatter.remove_stuff_from_string(article);
        String[] words = temp.split(" ");

        String output = "";

        for (int i = 0; i < words.length; i++) {
//            if (!StringUtils.isBlank(words[i])) {//todo remove
                String counter_string = words[i];
//                System.out.println(counter_string);//todo remove
                String[] occurrence_counter = temp.split(counter_string);

                if (counter_string.length() > numberOfCharacters && (occurrence_counter.length - 1) > max_occurrence) {
                    output = words[i];
                    max_occurrence = occurrence_counter.length - 1;
                }

            }
//        }//todo remove
        return output;
    }

    public static String get_most_occurring_word_starting_with_capital_letter_not_at_the_beginning_of_the_sentence(String article) {
        if (article.compareTo("") == 0) {
            return "";
        }

        String temp = article;
        String[] words = article.split(" ");

        if (words.length < 2) {
            return "";
        }

        String output = "";

        int max_occurrence = 0;

        for (int i = 1; i < words.length; i++) {
            String counter_string = words[i];

            counter_string = StringFormatter.remove_stuff_from_string(counter_string);

            String[] occurrence_counter = temp.split(counter_string);

            if (CharacteristicsExtractor.contains_capital_letter(counter_string) && !CharacteristicsExtractor.contains_dot_at_the_end(words[i - 1]) &&
                    (occurrence_counter.length - 1) > max_occurrence) {
                output = counter_string;
                max_occurrence = occurrence_counter.length - 1;
            }

        }

        return output;

    }

    public static boolean contains_capital_letter(String s) {
        if (s.compareTo("") == 0) {
            return false;
        }
        char[] temp_array = s.toCharArray();
        if ((int) temp_array[0] > 64 && (int) temp_array[0] < 91) {
            return true;
        }
        return false;

    }

    public static boolean contains_dot_at_the_end(String s) {
        if (StringUtils.isBlank(s)) return false;
        char[] temp_array = s.toCharArray();
        if ((int) temp_array[temp_array.length - 1] == '.') {
            return true;
        }
        return false;
    }


}

package algorithm.DistanceCalculation.ngram;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ngram {

    public static double calculate_difference(String s1, String s2, int n)
    {
        Set<String> s1_n_grams = ngram.create_and_return_n_gram_set(s1, n);
        Set<String> s2_n_grams = ngram.create_and_return_n_gram_set(s2, n);

        if (s1_n_grams.isEmpty() || s2_n_grams.isEmpty()) {
            return 0.0;
        }

        int total = s1_n_grams.size() + s2_n_grams.size();

        double output = 0.0;

        for (String s : s1_n_grams) {
            if (s2_n_grams.contains(s)) {
                output = output + 1.0;
            }
        }

        return (2 * output / total);

    }

    public static Set<String> create_and_return_n_gram_set(String s, int n) {

        Set<String> n_grams = new HashSet<>();

        if (s.length() < n) {
            return n_grams;
        }

        ArrayList<String> temp_array = new ArrayList<>();


        char[] temp_char_array = s.toCharArray();


        for (int i = 0; i < temp_char_array.length - (n - 1); i++) {
            String temp = "";

            for (int j = i; j < i + n; j++) {
                temp = temp + temp_char_array[j];
            }

            temp_array.add(temp);

        }

        n_grams.addAll(temp_array);
        return n_grams;
    }

}
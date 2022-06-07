package myvectors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CharactericticsVector_Manager {

    public static HashMap<String, ArrayList<CharacteristicsVector>> create_and_return_characteristics_vectors_map_static_final(HashMap<String, List<String>> all_articles,
                                                                                                                               ArrayList<String> bag_of_words) {
        HashMap<String, ArrayList<CharacteristicsVector>> characteristics_vectors = new HashMap<>();
        for (String s : all_articles.keySet()) {
            characteristics_vectors.put(s, new ArrayList<CharacteristicsVector>());
        }

        for (String s : all_articles.keySet()) {
            for (int i = 0; i < all_articles.get(s).size(); i++) {
                CharacteristicsVector new_char_vector = new CharacteristicsVector(s);
                new_char_vector.calculate_all_C(all_articles.get(s).get(i), bag_of_words);
                characteristics_vectors.get(s).add(new_char_vector);
            }
        }
        return characteristics_vectors;
    }
}

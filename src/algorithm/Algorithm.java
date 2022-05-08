package algorithm;
import algorithm.DistanceCalculation.VectorDistanceCalculator;
import commons.string_double_container;
import commons.string_double_container_comparator;
import my_vectors.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Algorithm {

    private HashMap<String, ArrayList<CharacteristicsVector>> characteristics_vectors = new HashMap<>();

    private int total_measured = 0;
    private int total_correct = 0;

    private HashMap<String, ArrayList<CharacteristicsVector>> all_article_vectors = new HashMap<>();

    private HashMap<String, ArrayList<CharacteristicsVector>> a_learning_set = new HashMap<>();
    private HashMap<String, ArrayList<CharacteristicsVector>> a_test_set = new HashMap<>();




    public Algorithm(HashMap<String, ArrayList<CharacteristicsVector>> article_vectors)
    {
       all_article_vectors = article_vectors;
    }

    public void split_articles(double percentage)
    {
        for (String s : all_article_vectors.keySet())
        {

            a_learning_set.put(s, new ArrayList<>());
            a_test_set.put(s, new ArrayList<>());

            int divider = (int) (all_article_vectors.get(s).size() * percentage);

            for(int i = 0; i < divider; i ++)
            {
                a_learning_set.get(s).add(all_article_vectors.get(s).get(i));
            }

            for (int i = divider; i < all_article_vectors.get(s).size(); i++)
            {
                a_test_set.get(s).add(all_article_vectors.get(s).get(i));
            }

        }

    }

    public void categorise_single_text(CharacteristicsVector v1, int k, int [] what_to_include, int metric_switch)
    {


        ArrayList<string_double_container> category_scores = create_category_score_sorted_array(v1, k, what_to_include, metric_switch);

        String most_represented_category = determine_the_category(category_scores);

        v1.setAssigned_category(most_represented_category);

        //v1.setAssigned_category(determine_the_category(create_category_score_sorted_array(v1, k, what_to_include, metric_switch)));

        ///////zliczanie mozna wywalic

        if (v1.getCategory().compareTo(most_represented_category) == 0)
        {
            //correct_predictions_by_country.put(v1.getCategory(), correct_predictions_by_country.get(v1.getCategory()) + 1);
            total_correct ++;
        }

        //////zliczanie end

//        System.out.println("Should be: " + v1.getCategory() + " classified: " + most_represented_category);

    }


    public ArrayList<string_double_container> create_category_score_sorted_array(CharacteristicsVector v1, int k, int [] what_to_include, int metric_switch)
    {

        ArrayList<string_double_container> category_scores = new ArrayList<>();

        for (String s : a_learning_set.keySet())
        {
            for(int i = 0; i < a_learning_set.get(s).size(); i ++)
            {
                double score = 0.0;

                switch (metric_switch)
                {
                    case 1:
                    {
                        score = VectorDistanceCalculator.Euclidean_metric(v1, a_learning_set.get(s).get(i), what_to_include);
                        break;
                    }

                    case 2:
                    {
                        score = VectorDistanceCalculator.Manhattan_metric(v1, a_learning_set.get(s).get(i), what_to_include);
                        break;
                    }
                    case 3:
                    {
                        score = VectorDistanceCalculator.Chebyshev_metric(v1, a_learning_set.get(s).get(i), what_to_include);
                        break;
                    }
                }

                category_scores.add(new string_double_container(s,score));
            }
        }

        Collections.sort(category_scores, new string_double_container_comparator());

//        System.out.println();

        while(category_scores.size() > k)
        {
            category_scores.remove(category_scores.size()-1);
        }

        return category_scores;
    }

    public String determine_the_category(ArrayList<string_double_container> category_scores)
    {
        HashMap<String, Integer> occurrences = new HashMap<>();
        for (int i = 0; i < category_scores.size(); i ++)
        {
            String category = category_scores.get(i).getWord();
            if(!occurrences.containsKey(category)){
                occurrences.put(category,1);
            }
            else
            {
                occurrences.put(category, occurrences.get(category) + 1);
            }
        }

        String most_represented_category = "";
        int most_members = 0;

        for (String s: occurrences.keySet())
        {
            if(occurrences.get(s) > most_members)
            {
                most_members = occurrences.get(s);
                most_represented_category = s;
            }
        }


        ////ponizej dodane - determine draw

        HashMap<String, Double> determine_draw = new HashMap<>();

        for(String s : occurrences.keySet())
        {
            if (most_members == occurrences.get(s))
            {
                determine_draw.put(s, 0.0);
            }
        }

        for(int i = 0; i < category_scores.size(); i++)
        {
                if(determine_draw.containsKey(category_scores.get(i).getWord()))
            {
                determine_draw.put(category_scores.get(i).getWord(), determine_draw.get(category_scores.get(i).getWord()) + category_scores.get(i).getOccurrence());
            }
        }

        ArrayList<string_double_container> temp_scores = new ArrayList<>();


        for(String s: determine_draw.keySet())
        {
            temp_scores.add(new string_double_container(s, determine_draw.get(s)));
        }

        Collections.sort(temp_scores, new string_double_container_comparator());

        most_represented_category = temp_scores.get(0).getWord();

        ////dodane end

        return most_represented_category;
    }




    public void categorise_whole_data(int k, int [] what_to_include, int metric_switch)
    {
        total_correct = 0; //do wywalenia
        total_measured = 0; //mozna z size wyliczyc

        for(String s: a_test_set.keySet())
        {
            for(int i = 0; i < a_test_set.get(s).size(); i ++)
            {
                categorise_single_text(a_test_set.get(s).get(i), k,  what_to_include, metric_switch);
                total_measured ++;
            }
        }

        System.out.println("measured: " + total_measured + " correct: " + total_correct);

    }

    public HashMap<String, ArrayList<CharacteristicsVector>> getA_test_set(){return a_test_set;}

}

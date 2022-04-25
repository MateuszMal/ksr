package summary;

import my_vectors.CharacteristicsVector;

import java.util.ArrayList;
import java.util.HashMap;

public class Summary {

    private HashMap<String, Integer> TP = new HashMap<>();
    private HashMap<String, Integer> TN = new HashMap<>();
    private HashMap<String, Integer> FP = new HashMap<>();
    private HashMap<String, Integer> FN = new HashMap<>();
    private HashMap<String, Integer> PP = new HashMap<>();//niepotrzebna TP+FP

    HashMap<String, ArrayList<CharacteristicsVector>> measured_vectors = new HashMap<>();

    public Summary(HashMap<String, ArrayList<CharacteristicsVector>> a_test_set)
    {
        measured_vectors = a_test_set;
    }

    public void print_metrics()
    {

        double N = 0.0;
        double TP_total = 0.0;
        double TN_total = 0.0;

        for(String s : measured_vectors.keySet())
        {
            TP_total = TP_total + TP.get(s);
            TN_total = TN_total + TN.get(s);
            N = N + TP.get(s) + TN.get(s) + FP.get(s) + FN.get(s);
        }

        System.out.println("Accurracy: " + ((TP_total + TN_total) / N) );

        ///////Precision

        for(String s : measured_vectors.keySet())
        {
            double PP = TP.get(s) + FP.get(s);

            if(PP == 0)
            {
                System.out.println("Precision for " + s + " " + 0.0);
            }

            else
            {
                System.out.println("Precision for " + s + " " + (TP.get(s) / PP) );
            }
        }

        /////RECALL

        for(String s : measured_vectors.keySet())
        {
            double recall = (TP.get(s) / (double)measured_vectors.get(s).size());
            System.out.println("Recall for " + s + " " +  recall);
        }

        ////f1

        for(String s : measured_vectors.keySet())
        {
            double f1 = (double) (2 * TP.get(s)) / (2 * TP.get(s) + FN.get(s) + FP.get(s));
            System.out.println("f1 for " + s + " " + f1);
        }

    }

    public void determine_TP_TN_FP_FN_for_whole_test_set()
    {
        for(String s: measured_vectors.keySet())
        {
            TP.put(s,0);
            TN.put(s,0);
            FP.put(s,0);
            FN.put(s,0);
        }

        for(String s : measured_vectors.keySet())
        {
            for(int i = 0; i < measured_vectors.get(s).size(); i ++)
            {
                determine_TP_TN_FP_FN_values_for_a_single_vector(measured_vectors.get(s).get(i));
            }
        }

    }


    public void determine_TP_TN_FP_FN_values_for_a_single_vector(CharacteristicsVector v)
    {

        String proper_category = v.getCategory();
        String assigned_category = v.getAssigned_category();

        if(proper_category.compareTo(assigned_category) == 0)
        {
            TP.put(assigned_category, TP.get(assigned_category) + 1);
        }
        else
        {
            FP.put(assigned_category, FP.get(assigned_category) + 1);
        }

        for(String s: measured_vectors.keySet())
        {
            if(s.compareTo(assigned_category) != 0)
            {
                if(s.compareTo(proper_category) != 0)
                {
                    TN.put(s, TN.get(s) + 1);
                }
                else
                {
                    FN.put(s, FN.get(s) + 1);
                }
            }

        }
    }


}

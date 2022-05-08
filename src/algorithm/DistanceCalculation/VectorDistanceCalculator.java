package algorithm.DistanceCalculation;

import algorithm.DistanceCalculation.ngram.ngram;
import my_vectors.CharacteristicsVector;

public class VectorDistanceCalculator {

    public static double Euclidean_metric (CharacteristicsVector v1, CharacteristicsVector v2, int [] what_to_include)
    {
        double output = 0.0;

        int i;

        for (i = 0; i < v1.getC_list().size(); i ++)
        {
            output = output + what_to_include[i] * Math.pow( ( v1.getC_list().get(i) - v2.getC_list().get(i)), 2) ;
        }

        output = output + what_to_include[i] *  Math.pow(1 - ngram.calculate_difference(v1.getC3(), v2.getC3(), 3),2 );
        output = output + what_to_include[i+1] *  Math.pow(1 - ngram.calculate_difference(v1.getC5(), v2.getC5(), 3),2 );

        return Math.sqrt(output);
    }

    public static double Manhattan_metric (CharacteristicsVector v1, CharacteristicsVector v2, int [] what_to_include)
    {
        double output = 0.0;

        int i;

        for (i = 0; i < v1.getC_list().size(); i ++)
        {
            output = output + what_to_include[i] * Math.abs( ( v1.getC_list().get(i) - v2.getC_list().get(i))) ;
        }

        output = output + what_to_include[i] *  Math.abs(1 - ngram.calculate_difference(v1.getC3(), v2.getC3(), 3));
        output = output + what_to_include[i+1] *  Math.abs(1 - ngram.calculate_difference(v1.getC5(), v2.getC5(), 3));

        return output;
    }

    public static double Chebyshev_metric (CharacteristicsVector v1, CharacteristicsVector v2, int [] what_to_include)
    {
        double output = 0.0;

        int i;

        for (i = 0; i < v1.getC_list().size(); i ++)
        {
            if(output < what_to_include[i] * Math.abs( ( v1.getC_list().get(i) - v2.getC_list().get(i))) )
            {
                output = what_to_include[i] * Math.abs( ( v1.getC_list().get(i) - v2.getC_list().get(i)));
            }
        }


        if(output < what_to_include[i] *  Math.abs(1 - ngram.calculate_difference(v1.getC3(), v2.getC3(), 3)))
        {
            output = what_to_include[i] *  Math.abs(1 - ngram.calculate_difference(v1.getC3(), v2.getC3(), 3));
        }

        if(output < what_to_include[i+1] *  Math.abs(1 - ngram.calculate_difference(v1.getC5(), v2.getC5(), 3)))
        {
            output = what_to_include[i+1] *  Math.abs(1 - ngram.calculate_difference(v1.getC5(), v2.getC5(), 3));
        }


        return output;
    }

}

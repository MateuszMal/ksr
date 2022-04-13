package commons;

import java.util.HashMap;
import java.util.List;

public class StringFormatter {
    public static String remove_separators(String input)
    {
        String output = input;
        output = output.replace("REUTER&#3;", "");
        output.replace("REUTER&#3", "");
        output = output.replace("the ", "");
        output = output.replace("The ", "");
        output = output.replace("0", "");
        output = output.replace("1", "");
        output = output.replace("2", "");
        output = output.replace("3", "");
        output = output.replace("4", "");
        output = output.replace("5", "");
        output = output.replace("6", "");
        output = output.replace("7", "");
        output = output.replace("8", "");
        output = output.replace("9", "");
        output = output.replace(" a ", "");
        output = output.replace(" an ", "");
        output = output.replace("A ", "");
        output = output.replace("An ", "");
        output = output.replace(" and ", "");
        output = output.replace("Reuter&#3", "");
        output = output.replace("\n", " ");
        output = output.replace("\r", " ");
        output = output.replace("\t", " ");
        output = output.replace("\\r\\n", " ");
        output = output.replace("-", " ");
        output = output.replace(",", " ");
        output = output.replace("(", " ");
        output = output.replace(")", " ");
        //output = output.replace("\"", " ");
        output = output.replace(";", " ");
        output = output.replace(">", " ");
        output = output.replace("<", " ");
        output = output.replace("   ", " ");
        output = output.replace("  ", " ");
        output = output.replace("?", ".");
        output = output.replace("+", " ");


        return output;
    }

    public static String remove_stuff_from_string(String input)
    {
        String processed_string = input;
       // System.out.println(":" + processed_string);
        //System.out.println("Tuuuu");
       // processed_string = processed_string.replace(",", "");

            while(processed_string.charAt(processed_string.length()-1) == '.'|| processed_string.charAt(processed_string.length()-1) == ',')
            {
               // System.out.println(processed_string);
                processed_string = processed_string.substring(0, processed_string.length() - 1);
                if (processed_string.compareTo("") == 0) {break;}
            }
        return processed_string;
    }



    public static void format_whole_hash_map(HashMap<String, List<String>> texts_hash)
    {
        for (String category : texts_hash.keySet())
        {
            for(int i = 0; i < texts_hash.get(category).size(); i ++)
            {
                texts_hash.get(category).set(i, StringFormatter.remove_separators(texts_hash.get(category).get(i)));
            }

        }
    }
}

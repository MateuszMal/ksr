package commons;

import java.util.HashMap;
import java.util.List;

public class Commons {

    public static String create_whole_text_string(HashMap<String, List<String>> texts_hash){
        String output = "";
        for (String category : texts_hash.keySet())
        {
            for(int i = 0; i < texts_hash.get(category).size(); i ++)
            {
                output = output + texts_hash.get(category).get(i) + " ";
            }
        }
        return output;
    }

    public static String [] generate_country_array(HashMap<String, List<String>> texts_hash)
    {
        String output = "";

        for ( String key : texts_hash.keySet() ) {
            output = output + key + " ";
        }

        return output.split(" ");
    }

}

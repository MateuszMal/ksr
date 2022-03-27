package commons;

import java.util.HashMap;
import java.util.List;

public class StringFormatter {
    public static String remove_separators(String input)
    {
        String output = input;
        output = output.replace("\n", " ");
        output = output.replace("\r", " ");
        output = output.replace("\t", " ");
        output = output.replace("\\r\\n", " ");
        output = output.replace("   ", " ");
        output = output.replace("  ", " ");
        return output;
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

package commons;

import java.util.Comparator;

public class string_double_container_comparator implements Comparator<string_double_container> {

    @Override
    public int compare(string_double_container k1, string_double_container k2)
    {
        return k1.compareTo(k2);
    }

}

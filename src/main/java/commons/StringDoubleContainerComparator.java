package commons;

import java.util.Comparator;

public class StringDoubleContainerComparator implements Comparator<StringDoubleContainer> {

    @Override
    public int compare(StringDoubleContainer k1, StringDoubleContainer k2) {
        return k1.compareTo(k2);
    }

}

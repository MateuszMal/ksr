package files;

import java.util.Collection;
import java.util.List;

public class Preparatory {

    public static List<String> mergeLists(List<List<String>> lists) {
        return lists.stream()
                .flatMap(Collection::stream).toList();
    }
}

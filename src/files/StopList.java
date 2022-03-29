package files;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class StopList {
    private static final String STOP_LIST_FILE = "src/resources/stop-list/stop-list.txt";
    private final List<String> stopList;

    public StopList() {
        stopList = ReadSgmFile.fileReader(STOP_LIST_FILE);
    }

    /**
     * Removing from articles' map all words from stop list
     * @param articles Map<String, List<String>> countries withs articles
     * @return map with articles WITHOUT comas and dots
     * */
    public Map<String, List<String>> removeWords(Map<String, List<String>> articles) {
        for (Map.Entry<String, List<String>> entry : articles.entrySet()) {
            List<String> articleList = entry.getValue();
            for (int i = 0; i < articleList.size(); i++) {
                String articleWithoutComa = articleList.get(i).replaceAll(",", "");
                String clearArticle = articleWithoutComa.replaceAll("\\.", "");
                List<String> strings = Arrays.stream(clearArticle.split(" ")).collect(Collectors.toList());
                strings.removeIf(str -> stopList.contains(str.toLowerCase()));
                articleList.set(i, String.join(" ", strings));
            }
        }
        return articles;
    }
}

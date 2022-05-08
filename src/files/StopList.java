package files;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class StopList {
    private static final String STOP_LIST_FILE = "src/resources/stop-list/stop-list.txt";
    private final List<String> stopList;

    public StopList() {
        this.stopList = ReadSgmFile.fileReader(STOP_LIST_FILE);
    }

    public StopList(List<String> stopList) {
        this.stopList = stopList;
    }


    public HashMap<String, List<String>> removeWords(HashMap<String, List<String>> articles) {
        for (Map.Entry<String, List<String>> entry : articles.entrySet()) {
            List<String> articleList = entry.getValue();
            for (int i = 0; i < articleList.size(); i++) {
                List<String> words = Arrays.stream(articleList.get(i).split(" ")).collect(Collectors.toList());
                words.removeIf(str -> stopList.contains(str.toLowerCase()));
                for (int j = 0; j < words.size(); j++) {
                    if (words.get(j).endsWith(".")) {
                        String wordWithoutDot = StringUtils.chop(words.get(j));
                        if (stopList.contains(wordWithoutDot.toLowerCase())){
                            words.set(j, ".");
                        }
                    }
                    if (words.get(j).endsWith(",")) {
                        String wordWithoutDot = StringUtils.chop(words.get(j));
                        if (stopList.contains(wordWithoutDot.toLowerCase())){
                            words.remove(words.get(j));
                        }
                    }
                }
                articleList.set(i, String.join(" ", words));
            }
        }
        return articles;
    }
}

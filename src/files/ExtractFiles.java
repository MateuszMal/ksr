package files;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public class ExtractFiles {
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private final Pattern ARTICLE_PATTERN = Pattern.compile("<PLACES>(.*?)</TEXT>");
    private final Pattern PLACES_PATTERN = Pattern.compile("<D>(.*?)</D></PLACES>");
    private final List<String> countries = List.of("west-germany", "usa", "france", "uk", "canada", "japan");
    private final StringBuilder buffer = new StringBuilder();
    private final List<String> filteredByTags = new ArrayList<>();

    public HashMap<String, List<String>> countriesAndArticles(List<String> files) {
        HashMap<String, List<String>> articles = new HashMap<>();
        searchByCountries(getPlace(files), articles);
        filterArticles(articles);
        return articles;
    }

    private List<String> getPlace(List<String> lines) {
        for (String line : lines) {
            buffer.append(line);
            buffer.append(" ");
        }
        Matcher matcher = ARTICLE_PATTERN.matcher(buffer);

        while (matcher.find()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                StringBuilder outBuffer = new StringBuilder();
                if (matcher.group(i) != null) {
                    outBuffer.append(matcher.group(i));
                    filteredByTags.add(outBuffer.toString());
                }
            }
        }
        return filteredByTags;
    }

    private void filterArticles(HashMap<String, List<String>> articlesMap){
        for(Map.Entry<String, List<String>> entry : articlesMap.entrySet()){
            entry.setValue(removeUnnecessaryStrings(entry.getValue()));
        }
    }

    private List<String> removeUnnecessaryStrings(List<String> articles){
        String[] META_CHARS = { "&", "<", ">", "\"", "'" };
        String[] META_CHARS_SERIALIZATIONS = { "&amp;", "&lt;", "&gt;", "&quot;", "&apos;" };

        for (int i = 0; i < articles.size(); i++) {
            for (int j = 0; j < META_CHARS_SERIALIZATIONS.length; j++) {
                articles.set(i, articles.get(i).replaceAll(META_CHARS_SERIALIZATIONS[j], META_CHARS[j]));
            }
            articles.set(i, articles.get(i).substring(0, articles.get(i).length() - 12));
            articles.set(i, articles.get(i).trim().replaceAll(" +", " "));
        }
        return articles;
    }

    private void searchByCountries(List<String> articles, HashMap<String, List<String>> articlesMap) {

        for (String article : articles) {
            Matcher matcher = PLACES_PATTERN.matcher(article);
            if (matcher.find()) {
                for (int i = 0; i < matcher.groupCount(); i++) {
                    String places = matcher.group(i);
                    if (places != null) {
                        if (filterStringsByCountries(places)) {
                            getArticleBodyWithCountry(article, articlesMap);
                        }
                    }
                }
            }
        }
    }

    private boolean filterStringsByCountries(String article) {
        if (filterStringOneCountry(article)) {
            return countries.stream()
                    .anyMatch(article::contains);
        }
        return false;
    }

    private boolean filterStringOneCountry(String article) {
        Pattern countries = Pattern.compile("<D>(.*?)</D>");
        Matcher matcher = countries.matcher(article);
        return matcher.results().count() == 1;
    }

    private void getArticleBodyWithCountry(String article, HashMap<String, List<String>> articlesMap) {
        Pattern country = Pattern.compile("<D>(.*?)</D>");
        Pattern body = Pattern.compile("<BODY>(.*?)</BODY>");
        Matcher countryMatcher = country.matcher(article);
        Matcher bodyMatcher = body.matcher(article);
        if (countryMatcher.find() && bodyMatcher.find()) {
            articlesMap.computeIfAbsent(countryMatcher.group(1), k -> new ArrayList<>()).add(bodyMatcher.group(1));
        }
    }

}

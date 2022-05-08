package mainflow;

import algorithm.Algorithm;
import commons.StringFormatter;
import files.ExtractFiles;
import files.ReadSgmFile;
import files.StopList;
import keywords.CategoryKeyword_Manager;
import lemmatization.Stemming;
import lombok.Getter;
import lombok.Setter;
import my_vectors.CharactericticsVector_Manager;
import my_vectors.CharacteristicsVector;
import summary.Summary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class FlowManager {
    private String dirPath = "data";
    private ReadSgmFile readSgmFile;
    private final ExtractFiles extractFiles = new ExtractFiles();
    private List<String> stopListWords;
    private int metricSwitch = 3;
    private int[] parametersSwitch = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    private double percentage = 0.5;
    private int kParameter = 1;

    public String startAlgorithm() {
        long startTime = System.nanoTime();
        readSgmFile = new ReadSgmFile(dirPath);
        List<String> sgmFiles = readSgmFile.readFiles();

        HashMap<String, List<String>> articles = extractFiles.countriesAndArticles(sgmFiles);

        StopList stopList;
        if (stopListWords != null) {
            stopList = new StopList(stopListWords);
        } else {
            stopList = new StopList();
        }
        HashMap<String, List<String>> articlesAfterStopList = stopList.removeWords(articles);

        Stemming stemming = new Stemming();
        HashMap<String, List<String>> articlesAfterStemming = stemming.lemmtizeArticles(articlesAfterStopList);

        StringFormatter.format_whole_hash_map(articlesAfterStemming);

        HashMap<String, ArrayList<String>> categoryKeywords = CategoryKeyword_Manager.create_category_keywords_hash_map_static(
                articlesAfterStemming,
                10);
        ArrayList<String> bagOfWords = CategoryKeyword_Manager.create_and_return_bag_of_words(categoryKeywords);
        HashMap<String, ArrayList<CharacteristicsVector>> vectorsMap = CharactericticsVector_Manager.create_and_return_characteristics_vectors_map_static_final(
                articlesAfterStemming,
                bagOfWords);


        Algorithm algorithm = new Algorithm(vectorsMap);

        algorithm.split_articles(percentage);
        algorithm.categorise_whole_data(kParameter, parametersSwitch, metricSwitch);

        System.out.println("##############");

        Summary summary = new Summary(algorithm.getA_test_set());
        summary.determine_TP_TN_FP_FN_for_whole_test_set();


        System.out.println("##############");
        long endTime = System.nanoTime();
        System.out.println((endTime - startTime) / 1000000000);
        return summary.print_metrics();

    }
}

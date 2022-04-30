package mainflow;

import algorithm.Algorithm;
import commons.StringFormatter;
import files.ExtractFiles;
import files.ReadSgmFile;
import files.StopList;
import keywords.CategoryKeyword_Manager;
import lemmatization.Stemming;
import my_vectors.CharactericticsVector_Manager;
import my_vectors.CharacteristicsVector;
import summary.Summary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FlowManager {
    private final String dirPath;
    private final ReadSgmFile readSgmFile;
    private final ExtractFiles extractFiles = new ExtractFiles();
    private final StopList stopList = new StopList();

    public FlowManager(String dirPath) {
        this.dirPath = dirPath;
        this.readSgmFile = new ReadSgmFile(dirPath);
    }

    public void startAlgorithm() {
        long startTime = System.nanoTime();
        List<String> sgmFiles = readSgmFile.readFiles();

        HashMap<String, List<String>> articles = extractFiles.countriesAndArticles(sgmFiles);
        HashMap<String, List<String>> articlesAfterStopList = stopList.removeWords(articles);

        Stemming stemming = new Stemming();
        HashMap<String, List<String>> articlesAfterStemming = stemming.lemmtizeArticles(articlesAfterStopList);

        StringFormatter.format_whole_hash_map(articlesAfterStemming); // todo check if it is needed twice

        HashMap<String, ArrayList<String>> categoryKeywords = CategoryKeyword_Manager.create_category_keywords_hash_map_static(
                articlesAfterStemming,
                10);
        ArrayList<String> bagOfWords = CategoryKeyword_Manager.create_and_return_bag_of_words(categoryKeywords);
        HashMap<String, ArrayList<CharacteristicsVector>> vectorsMap = CharactericticsVector_Manager.create_and_return_characteristics_vectors_map_static_final(
                articlesAfterStemming,
                bagOfWords);

        int[] parametersSwitch = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}; //todo this should be taken from user
        int metricSwitch = 3; // todo this should be taken from user
        double percentage = 0.5; //todo this should be taken from user
        int k = 1;

        Algorithm algorithm = new Algorithm(vectorsMap);

        algorithm.split_articles(percentage);
        algorithm.categorise_whole_data(k, parametersSwitch, metricSwitch);

        System.out.println("##############");

        Summary summary = new Summary(algorithm.getA_test_set());
        summary.determine_TP_TN_FP_FN_for_whole_test_set();

        summary.print_metrics();

        System.out.println("##############");
        long endTime = System.nanoTime();
        System.out.println((endTime - startTime) / 1000000000);

    }
}

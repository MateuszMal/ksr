import algorithm.Algorithm;
import commons.StringFormatter;
import files.ExtractFiles;
import files.ReadSgmFile;
import files.StopList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import keywords.CategoryKeyword_Manager;
import lemmatization.Stemming;
import my_vectors.CharactericticsVector_Manager;
import my_vectors.CharacteristicsVector;
import summary.Summary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("resources/fxml/MainWindow.fxml"));
        BorderPane anchorPane = loader.load();

        Scene scene = new Scene(anchorPane);

        stage.setScene(scene);
        stage.setTitle("KSR");
        stage.show();

    }

    public static void main(String[] args) {
//        launch();
        ReadSgmFile f = new ReadSgmFile("data");
        List<List<String>> files = f.readFiles();

        ExtractFiles ex = new ExtractFiles();

//        StopList stopList = new StopList();

        HashMap<String, List<String>> articles = ex.countriesAndArticles(files.get(0));

        Stemming stemming = new Stemming();
        HashMap<String, List<String>> stringListMap1 = stemming.lemmtizeArticles(articles);

//        Map<String, List<String>> stringListMap = stopList.removeWords(articles);
        StringFormatter.format_whole_hash_map(stringListMap1);

        StringFormatter.format_whole_hash_map(stringListMap1);

        ArrayList<String> bag_of_words = CategoryKeyword_Manager.create_and_return_bag_of_words(CategoryKeyword_Manager.create_category_keywords_hash_map_static(stringListMap1, 10)); ///bag of words

        for(String s : bag_of_words)
        {
            System.out.println(s);
        }

        HashMap<String, ArrayList<CharacteristicsVector>> test_vector_Map = CharactericticsVector_Manager.create_and_return_characteristics_vectors_map_static_final(stringListMap1, bag_of_words); ////// tworzenie wektorow

        int [] parameters_switch = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};

        int metric_switch = 3;

        double percentage = 0.5;
        int k = 1;
        Algorithm test_algorithm = new Algorithm(test_vector_Map);

        test_algorithm.split_articles(percentage);

        test_algorithm.categorise_whole_data(k, parameters_switch, metric_switch);

        System.out.println("//////////////////////");
        Summary test_summary = new Summary(test_algorithm.getA_test_set());
        test_summary.determine_TP_TN_FP_FN_for_whole_test_set();
        test_summary.print_metrics();

        System.out.println("//////////////////////");

        System.exit(1);
    }
}

import files.ExtractFiles;
import files.ReadSgmFile;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        launch();
//        ReadSgmFile f = new ReadSgmFile();
//        List<List<String>> files = f.readFiles();
//
//        ExtractFiles ex = new ExtractFiles();
//
//        HashMap<String, List<String>> articles = ex.countriesAndArticles(files.get(0)); // zwraca mape gdzie key - kraj, value - lista artykulow
//        for (Map.Entry<String, List<String>> entry : articles.entrySet()) {
//            System.out.println(entry.getKey() + " : " + entry.getValue());
//            System.out.println("/////////////////");
//        }
    }
}

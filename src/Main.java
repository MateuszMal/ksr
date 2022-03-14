import files.ExtractFiles;
import files.ReadSgmFile;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        Scene scene = new Scene(new StackPane(l), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
        ReadSgmFile f = new ReadSgmFile();
        List<List<String>> files = f.readFiles();

        ExtractFiles ex = new ExtractFiles();

        HashMap<String, List<String>> articles = ex.countriesAndArticles(files.get(0)); // zwraca mape gdzie key - kraj, value - lista artykulow
        for(Map.Entry<String, List<String >> entry : articles.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
            System.out.println("/////////////////");
        }
    }
}

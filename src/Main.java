import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import mainflow.FlowManager;

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
        FlowManager flowManager = new FlowManager("data");
        flowManager.startAlgorithm();

        System.exit(0);
    }
}

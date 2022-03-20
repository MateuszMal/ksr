package gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;


@Slf4j
public class MainWindowController {
    @FXML
    private Button endButton;
    @FXML
    private Button startButton;
    @FXML
    private SplitMenuButton menuButton;
    @FXML
    private MenuItem addFilesButtonItem;
    @FXML
    private MenuItem addFiles;
    @FXML
    private MenuItem endItem;
    @FXML
    private MenuItem stopListItem;
    @FXML
    private MenuItem measuresChoice;
    @FXML
    private MenuItem attributesChoice;
    @FXML
    private MenuItem percentageScope;
    @FXML
    private MenuItem aboutProgram;
    @FXML
    private MenuBar menuBar;

    @FXML
    void initialize() {
    }

    public String chooseDirectoryFromButton(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Wybierz katalog");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        File directory = directoryChooser.showDialog(stage);
        try {
            return directory.getPath();
        } catch (NullPointerException e) {
            throw new RuntimeException();
        }
    }

    public String chooseDirectoryFromMenuItem() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Wybierz katalog");
        Stage stage = (Stage) menuBar.getScene().getWindow();
        File directory = directoryChooser.showDialog(stage); // path to directory
        try {
            return directory.getPath();
        } catch (Exception e) {
            throw new RuntimeException("User does not chose directory");
        }
    }

    public void onPercentageScopeItem(){
        showNewStage("/resources/fxml/RangeBar.fxml");
    }

    public void chooseAttributes() {
        showNewStage("/resources/fxml/ChooseAttributes.fxml");
    }

    public void exit() {
        log.info("Shutting down by user");
        System.exit(0);
    }

    private void showNewStage(String path) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainWindowController.class.getResource(path));

            Scene scene = new Scene(loader.load()/*, 355,510*/);
            Stage stage = new Stage();
            stage.setTitle("KSR");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e){
            log.error("Failed to load new scene");
        }
    }

}

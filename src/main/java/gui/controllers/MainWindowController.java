package gui.controllers;

import gui.Alerts;
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
import mainflow.*;
import org.apache.commons.lang3.StringUtils;

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
    private MenuItem kParameter;
    @FXML
    private MenuBar menuBar;

    private String directoryPath = "data";
    DataHolder dataHolder = DataHolder.getInstance();

    @FXML
    void initialize() {
    }

    public String chooseDirectoryFromButton(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Wybierz katalog");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        File directory = directoryChooser.showDialog(stage);
        try {
            dataHolder.setDirectoryPath(directory.getPath());
            return directory.getPath();
        } catch (NullPointerException e) {
            throw new RuntimeException();
        }
    }

    public String chooseDirectoryFromMenuItem() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Wybierz katalog");
        Stage stage = (Stage) menuBar.getScene().getWindow();
        File directory = directoryChooser.showDialog(stage);
        try {
            dataHolder.setDirectoryPath(directory.getPath());
            return directory.getPath();
        } catch (Exception e) {
            throw new RuntimeException("User does not chose directory");
        }
    }

    public void onPercentageScopeItem() {
        showNewStage("/fxml/RangeBar.fxml");
    }

    public void chooseAttributes() {
        showNewStage("/fxml/ChooseAttributes.fxml");
    }

    public void onStartButton() {
        FlowManager flowManager = setAttributes();
        String results = flowManager.startAlgorithm();
        Alerts.result(results);
    }

    public void onMeasureChoiceItem() {
        showNewStage("/fxml/ChooseMeasures.fxml");
    }

    public void onStopListItem() {
        showNewStage("/fxml/StopListStage.fxml");
    }

    public void onAboutProgram() {
        showNewStage("/fxml/AboutWindow.fxml");
    }

    public void onKParameter() {
        showNewStage("/fxml/KParameter.fxml");
    }


    public void exit() {
        log.info("Shutting down by user");
        System.exit(0);
    }

    private FlowManager setAttributes() {
        FlowManager flowManager = new FlowManager();
        if (StringUtils.isEmpty(dataHolder.getDirectoryPath())) {
            Alerts.alertMsg("Nie podałeś ścieżki do plików z artykułami");
            throw new RuntimeException("No directory with articles");
        } else {
            flowManager.setDirPath(dataHolder.getDirectoryPath());
        }
        if (dataHolder.getMetricSwitch() == 0) {
            Alerts.alertMsg("Nie wybrałeś metryki. Zostanie użyta metryka Euklidesa");
        } else {
            flowManager.setMetricSwitch(dataHolder.getMetricSwitch());
        }
        if (dataHolder.getTestingRange() == 0) {
            Alerts.alertMsg("Nie wybrałeś zakresu danych testowych. Zostanie użyte 50%");
        } else {
            flowManager.setPercentage(dataHolder.getTestingRange());
        }
        if (dataHolder.getAttributes() == null) {
            Alerts.alertMsg("Nie wybrałeś cech. Zostaną użyte wszystkie dostępne");
        } else {
            flowManager.setParametersSwitch(dataHolder.getAttributes().stream().mapToInt(i -> i).toArray());
        }
        if(dataHolder.getKParameter() != 0) {
            flowManager.setKParameter(dataHolder.getKParameter());
        }
        if (dataHolder.getStopList() != null && !dataHolder.getStopList().isEmpty()) {
            flowManager.setStopListWords(dataHolder.getStopList());
        }
        return flowManager;
    }

    private void showNewStage(String path) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainWindowController.class.getResource(path));

            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("KSR");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            log.error("Failed to load new scene");
        }
    }

}

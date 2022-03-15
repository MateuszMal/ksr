package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import lombok.extern.slf4j.Slf4j;

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
    void initialize(){
    }

    public void exit(){
        log.info("Shutting down by user");
        System.exit(0);
    }

}

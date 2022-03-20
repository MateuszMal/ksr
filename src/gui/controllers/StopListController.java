package gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class StopListController {
    @FXML
    private Button choiceButton;
    @FXML
    private MenuItem endItem;
    @FXML
    private MenuBar menuBar;
    @FXML
    private TextArea stopListWords;

    @FXML
    void initialize() {
    }

    public void onChoiceButton(){
        Stage stage = (Stage) choiceButton.getScene().getWindow();
        stage.close();
    }

    public void onEndItem(){
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.close();
    }
}

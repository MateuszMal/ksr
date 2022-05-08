package gui.controllers;

import gui.Alerts;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import mainflow.DataHolder;

import java.util.Arrays;
import java.util.List;

public class StopListController {
    @FXML
    private Button choiceButton;
    @FXML
    private MenuItem endItem;
    @FXML
    private MenuBar menuBar;
    @FXML
    private TextArea stopListWords;
    DataHolder dataHolder = DataHolder.getInstance();

    @FXML
    void initialize() {
    }

    public void onChoiceButton(){
        if(!stopListWords.getText().trim().isEmpty()){
            List<String> words = Arrays.asList(stopListWords.getText().split(" "));
            dataHolder.setStopList(words);
        } else {
            Alerts.alertMsg("Lista jest pusta");
        }
        Stage stage = (Stage) choiceButton.getScene().getWindow();
        stage.close();
    }

    public void onEndItem(){
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.close();
    }
}

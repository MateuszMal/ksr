package gui.controllers;

import gui.Alerts;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import mainflow.DataHolder;

public class RangeBarController {
    @FXML
    private Slider slider;
    @FXML
    private Button choiceButton;
    @FXML
    private MenuBar menuBar;
    @FXML
    private MenuItem endItem;
    DataHolder dataHolder = DataHolder.getInstance();

    @FXML
    void initialize() {
    }

    public void onChoiceButton(){
        Stage stage = (Stage) choiceButton.getScene().getWindow();
        boolean isChosen = false;
        if(slider.getValue() != 0) {
            dataHolder.setTestingRange(slider.getValue() / 100);
            isChosen = true;
        } else {
            Alerts.alertMsg("Podaj wartość większą od 0");
        }
        if (isChosen) {
            stage.close();
        }
    }

    public void onEndItem(){
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.close();
    }

}

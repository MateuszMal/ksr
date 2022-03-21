package gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

public class RangeBarController {
    @FXML
    private Slider slider;
    @FXML
    private Button choiceButton;
    @FXML
    private MenuBar menuBar;
    @FXML
    private MenuItem endItem;

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

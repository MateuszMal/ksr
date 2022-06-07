package gui.controllers;

import gui.Alerts;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import mainflow.DataHolder;

public class ChooseMeasuresController {
    @FXML
    private Button choiceButton;
    @FXML
    private MenuItem endItem;
    @FXML
    private MenuBar menuBar;
    @FXML
    private RadioButton euclides;
    @FXML
    private RadioButton street;
    @FXML
    private RadioButton czebyszew;

    DataHolder dataHolder = DataHolder.getInstance();

    @FXML
    void initialize() {
    }

    public void onChoiceButton() {
        Stage stage = (Stage) choiceButton.getScene().getWindow();
        boolean isChosen = false;
        if (euclides.isSelected() && !street.isSelected() && !czebyszew.isSelected()) {
            dataHolder.setMetricSwitch(1);
            isChosen = true;
        } else if (street.isSelected() && !euclides.isSelected() && !czebyszew.isSelected()) {
            dataHolder.setMetricSwitch(2);
            isChosen = true;
        } else if (czebyszew.isSelected() && !euclides.isSelected() && !street.isSelected()) {
            dataHolder.setMetricSwitch(3);
            isChosen = true;
        } else if (!euclides.isSelected() && !street.isSelected() && !czebyszew.isSelected()) {
            Alerts.alertMsg("Wybierz jedna metryke");
        } else {
            Alerts.alertMsg("Możesz wybrac tylko jedna metryke!");
        }
        if (isChosen) {
            stage.close();
        }
    }

    public void onEndItem() {
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.close();
    }

}

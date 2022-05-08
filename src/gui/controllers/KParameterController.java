package gui.controllers;

import gui.Alerts;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mainflow.DataHolder;
import org.apache.commons.lang3.StringUtils;

public class KParameterController {
    @FXML
    private MenuItem endItem;
    @FXML
    private TextField kParameter;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Button choiceButton;

    DataHolder dataHolder = DataHolder.getInstance();

    @FXML
    void initialize() {
    }

    public void onChoiceButton() {
        Stage stage = (Stage) choiceButton.getScene().getWindow();
        if (!StringUtils.isBlank(kParameter.getText())) {
            try {
                int k = Integer.parseInt(kParameter.getText());
                dataHolder.setKParameter(k);
                stage.close();
            } catch (Exception e) {
                Alerts.alertMsg("Podałeś nieprawidłową wartość. Podaj liczbę");
                kParameter.clear();
            }
        }
    }

    public void onEndItem() {
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.close();
    }
}

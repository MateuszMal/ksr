package gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import mainflow.DataHolder;

import java.util.ArrayList;
import java.util.List;

public class ChooseAttributesController {
    @FXML
    private Button choiceButton;
    @FXML
    private MenuItem endItem;
    @FXML
    private MenuBar menuBar;
    @FXML
    private RadioButton keywordFrequency30;
    @FXML
    private RadioButton keywordFrequency15;
    @FXML
    private RadioButton mostCommonWord;
    @FXML
    private RadioButton textLength;
    @FXML
    private RadioButton mostCommonWordCapitalLetter;
    @FXML
    private RadioButton averageLengthSentence;
    @FXML
    private RadioButton digitFrequency;
    @FXML
    private RadioButton firstTwoKeywordFrequency;
    @FXML
    private RadioButton thereFrequency;
    @FXML
    private RadioButton thanFrequency;

    DataHolder dataHolder = DataHolder.getInstance();
    private ArrayList<Integer> attributes = new ArrayList<>(List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0));

    @FXML
    void initialize() {
    }

    public void onChoiceButton() {
        Stage stage = (Stage) choiceButton.getScene().getWindow();
        if (keywordFrequency30.isSelected()) {
            attributes.set(0, 1);
        }
        if (keywordFrequency15.isSelected()) {
            attributes.set(1, 1);
        }
        if (mostCommonWord.isSelected()) {
            attributes.set(2, 1);
        }
        if (textLength.isSelected()) {
            attributes.set(3, 1);
        }
        if (mostCommonWordCapitalLetter.isSelected()) {
            attributes.set(4, 1);
        }
        if (averageLengthSentence.isSelected()) {
            attributes.set(5, 1);
        }
        if (digitFrequency.isSelected()) {
            attributes.set(6, 1);
        }
        if (firstTwoKeywordFrequency.isSelected()) {
            attributes.set(7, 1);
        }
        if (thereFrequency.isSelected()) {
            attributes.set(8, 1);
        }
        if (thanFrequency.isSelected()) {
            attributes.set(9, 1);
        }
        dataHolder.setAttributes(attributes);
        stage.close();
    }

    public void onEndItem() {
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.close();
    }

}

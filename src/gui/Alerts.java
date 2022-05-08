package gui;

import javafx.scene.control.Alert;

public class Alerts {
    public static void alertMsg(String message) {
        Alert warning = new Alert(Alert.AlertType.WARNING);
        warning.setTitle("Warning");
        warning.setHeaderText("Ostrzeżenie");
        warning.setContentText(message);
        warning.showAndWait();
    }
    public static void result(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Wyniki");
        alert.setHeaderText("Wyniki");
        alert.setContentText(message);
        alert.showAndWait();
    }
}

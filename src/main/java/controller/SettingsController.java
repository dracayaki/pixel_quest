package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.stage.Stage;


public class SettingsController {
    public Slider volumeSlider;
    public ComboBox languageComboBox;

    @FXML
    public void initialize() {
        languageComboBox.getSelectionModel().selectFirst();
    }


    public void handleClose(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public double getVolume() {
        return volumeSlider.getValue();
    }

    public String getSelectedLanguage() {
        return (String) languageComboBox.getValue();
    }
}

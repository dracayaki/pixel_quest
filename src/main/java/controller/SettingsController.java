package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import model.Object.MusicPlayer;

public class SettingsController {

    public Slider volumeSlider;
    public ComboBox<String> languageComboBox;

    @FXML
    public void initialize() {
        // Establece el valor actual del volumen
        volumeSlider.setValue(MusicPlayer.getVolume() * 100);

        // Listener para actualizar el volumen en tiempo real
        volumeSlider.valueProperty().addListener(new VolumeListener());

        // Selecciona por defecto la primera opción de idioma
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
        return languageComboBox.getValue();
    }

    // Clase interna para manejar cambios en el slider
    private static class VolumeListener implements ChangeListener<Number> {
        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            double newVolume = newValue.doubleValue() / 100.0;
            MusicPlayer.setVolume(newVolume);
        }
    }
}

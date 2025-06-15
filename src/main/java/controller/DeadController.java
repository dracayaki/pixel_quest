package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import model.Object.Session;

import java.io.IOException;
import java.util.Optional;

public class DeadController {
    public Button retryBtn;
    public ImageView deathImage;
    public Button menuBtn;
    public Button exitBtn;

    public void handleRetry(ActionEvent actionEvent) {
        try {
            int currentLevel = Session.getCurrentLevel(); // nivel actual guardado
            Main.setScene("/view/fxml/level.fxml", "Level " + currentLevel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleMenu(ActionEvent actionEvent) {
        try {
            Main.setScene("/view/fxml/levelMenu.fxml", "Level Selection");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleExit(ActionEvent actionEvent) { Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Close Application?");
        alert.setContentText("Are you sure you want to exit?");


        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.exit(0);
        }
    }
}

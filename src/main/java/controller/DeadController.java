package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import model.Object.Session;

import java.io.IOException;

public class DeadController {
    public Button retryBtn;
    public ImageView deathImage;
    public Button menuBtn;

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
}

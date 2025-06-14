package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.Object.Session;

import java.io.IOException;

public class LevelMenuController {

    public Button btnLevel1;
    public Button btnLevel2;
    public Button btnLevel3;
    public Button btnLevel4;
    public Button btnLevel5;
    public Button btnLevel6;
    public GridPane levelGrid;



    public void initialize() {
        int lastLevel = Session.getCurrentUser().getLastLevelPlayed();

        System.out.println(lastLevel);

        levelPlayedOrNot(btnLevel1, 1, lastLevel);
        levelPlayedOrNot(btnLevel2, 2, lastLevel);
        levelPlayedOrNot(btnLevel3, 3, lastLevel);
        levelPlayedOrNot(btnLevel4, 4, lastLevel);
        levelPlayedOrNot(btnLevel5, 5, lastLevel);
        levelPlayedOrNot(btnLevel6, 6, lastLevel);
    }

    private void levelPlayedOrNot(Button button, int levelNumber, int lastLevelPlayed) {
        if (levelNumber > lastLevelPlayed) {
            button.setDisable(true);
            ImageView imageView = (ImageView) button.getGraphic();
            ColorAdjust effect = new ColorAdjust();
            effect.setSaturation(-1);
            imageView.setEffect(effect);
        }
    }


    public void handleLevel(ActionEvent actionEvent) {
        Button levelClicked = (Button) actionEvent.getSource();
        String textBtn = (String) levelClicked.getUserData();
        int level = Integer.parseInt(textBtn.replace("Level ", ""));

        Session.setCurrentLevel(level);
        try {
            Main.setScene("/view/fxml/level.fxml", "Level " + level);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

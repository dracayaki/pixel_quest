package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.DAO.LevelDAO;
import model.Object.Level;
import model.Object.Session;

import java.net.URL;
import java.util.ResourceBundle;

public class LevelController implements Initializable {

    public GridPane levelGrid;
    public Label timerLbl;
    private int currentLevel;
    private static final int TILE_SIZE = 50;

    public void setLevel(int level) {
        this.currentLevel = level;
        System.out.println("Current level: " + currentLevel);
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LevelDAO levelDAO = new LevelDAO();
        int currentLevelNumber = Session.getCurrentUser().getLastLevelPlayed();
        Level level = levelDAO.getLevelByNumber(currentLevelNumber);

        if (level != null) {
            String[][] map = level.createMap();
            drawMap(map);
            startTimer();

        }
    }

    private void drawMap(String[][] map) {
        levelGrid.getChildren().clear();

        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                String tile = map[row][col];
                ImageView tileImage = new ImageView();

                switch (tile){
                    case "W":
                        tileImage.setImage(new Image("/img/wall.jpg"));
                        break;
                    case "C":
                        tileImage.setImage(new Image("/img/coin.png"));
                        break;
                    case "P":
                        tileImage.setImage(new Image("/img/snake.jpg"));
                        break;
                    case "E":
                    default:
                        continue;
                }

                tileImage.setFitWidth(TILE_SIZE);
                tileImage.setFitHeight(TILE_SIZE);
                tileImage.setPreserveRatio(true);

                levelGrid.add(tileImage, col, row);

            }
        }
    }

    private void startTimer() {
        Runnable task = () -> {
            int seconds = 0;
            while (true) {
                int finalSeconds = seconds;
                Platform.runLater(() -> timerLbl.setText("Time: " + (finalSeconds / 60) + ":" + String.format("%02d", finalSeconds % 60)));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
                seconds++;
            }
        };
        Thread timerThread = new Thread(task);
        timerThread.setDaemon(true);
        timerThread.start();
    }
}

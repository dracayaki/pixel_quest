package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import model.DAO.LevelDAO;
import model.Object.Level;
import model.Object.Session;


import java.io.IOException;
import java.net.URL;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class LevelController implements Initializable {

    public GridPane levelGrid;
    public Label timerLbl;
    private static final int TILE_SIZE = 32;
    public Button exitBtn;
    public Region spacer;
    public Button settingsBtn;
    private int timeLimit;
    private int snakeRow;
    private int snakeCol;
    private List<ImageView> snakeBody = new ArrayList<>();
    private String[][] map;
    private int currentPoints = 0;
    private long startTime;

    public void setLevel(int level) {

        System.out.println("Current level: " + level);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LevelDAO levelDAO = new LevelDAO();
        int currentLevelNumber = Session.getCurrentUser().getLastLevelPlayed();
        Level level = levelDAO.getLevelByNumber(currentLevelNumber);

        if (level != null) {
            map = level.createMap();
            timeLimit = level.getTimeLimit();
            drawMap(map);
            startTimer();

        }

        levelGrid.setFocusTraversable(true);
        levelGrid.requestFocus();

        Platform.runLater(() -> {
            levelGrid.getScene().setOnKeyPressed(event -> {
                switch (event.getCode()) {
                    case UP -> moveSnake(-1, 0);
                    case DOWN -> moveSnake(1, 0);
                    case LEFT -> moveSnake(0, -1);
                    case RIGHT -> moveSnake(0, 1);
                }
            });
        });
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

        snakeRow = map.length - 3;
        snakeCol = 2;

        ImageView head = createSnakeBody();
        levelGrid.add(head, snakeCol, snakeRow);
        snakeBody.add(head);
    }

    private void startTimer() {

        startTime = System.currentTimeMillis();
        Runnable task = () -> {
            int seconds = timeLimit;

            while (seconds >= 0) {
                int finalSeconds = seconds;
                Platform.runLater(() ->
                        timerLbl.setText("Time: " + (finalSeconds / 60) + ":" + String.format("%02d", finalSeconds % 60))
                );
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
                seconds--;
            }


            Platform.runLater(() -> {
                try {
                    Main.setScene("/view/fxml/you_died.fxml", "You Died");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        };

        Thread timerThread = new Thread(task);
        timerThread.setDaemon(true);
        timerThread.start();

    }

    private void moveSnake(int moveRow, int moveCol){

        int newRow = snakeRow + moveRow;
        int newCol = snakeCol + moveCol;

        if (newRow < 0 || newRow >= levelGrid.getRowCount() || newCol < 0 || newCol >= levelGrid.getColumnCount()) {
            return;
        }

        String targetTile = map[newRow][newCol];

        if (targetTile.equals("W")) {
            currentPoints = Math.max(0, currentPoints - 5);
            if (!snakeBody.isEmpty()) {
                ImageView tail = snakeBody.remove(snakeBody.size() - 1);
                levelGrid.getChildren().remove(tail);
            }

            if (snakeBody.isEmpty()) {
                Platform.runLater(() -> {
                    try {
                        Main.setScene("/view/fxml/you_died.fxml", "You Died");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
            return;
        }

        if (targetTile.equals("C")) {
            currentPoints += 5;
            ImageView newSegment = createSnakeBody();
            levelGrid.add(newSegment, snakeCol, snakeRow);
            snakeBody.add(0, newSegment);
            map[newRow][newCol] = "E";
            removeNodeAt(newRow, newCol);

            if (!hasMoreCoins()) {
                int levelId = Session.getCurrentUser().getLastLevelPlayed();
                int userId = Session.getCurrentUser().getId(); // ajusta según tu clase User
                int usedTime = timeLimit; // o calcula tiempo real usado si puedes

                long endTime = System.currentTimeMillis();
                int usedTimeSeconds = (int) ((endTime - startTime) / 1000);

                LevelDAO.insertRecord(userId, levelId, currentPoints, usedTimeSeconds);
                Platform.runLater(() -> {
                    try {
                        WinController.setLevelData(levelId);
                        Main.setScene("/view/fxml/you_won.fxml", "You Won");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        } else {
            ImageView tail = snakeBody.remove(snakeBody.size() - 1);
            GridPane.setRowIndex(tail, newRow);
            GridPane.setColumnIndex(tail, newCol);
            snakeBody.add(0, tail);
        }

        GridPane.setRowIndex(snakeBody.get(0), newRow);
        GridPane.setColumnIndex(snakeBody.get(0), newCol);
        snakeRow = newRow;
        snakeCol = newCol;
    }

    private void removeNodeAt(int row, int col) {
        levelGrid.getChildren().removeIf(node ->
                GridPane.getRowIndex(node) != null &&
                        GridPane.getColumnIndex(node) != null &&
                        GridPane.getRowIndex(node) == row &&
                        GridPane.getColumnIndex(node) == col
        );
    }


    private ImageView createSnakeBody() {
        ImageView body = new ImageView(new Image("/img/snake.jpg"));
        body.setFitWidth(TILE_SIZE);
        body.setFitHeight(TILE_SIZE);
        body.setPreserveRatio(true);
        return body;
    }

    private boolean hasMoreCoins() {
        for (String[] row : map) {
            for (String cell : row) {
                if (cell.equals("C")) {
                    return true;
                }
            }
        }
        return false;
    }

    public void handleExit(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Close Application?");
        alert.setContentText("Are you sure you want to exit?");


        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.exit(0);
        }
    }

    public void handleSettings(ActionEvent actionEvent) {
        System.out.println("Opening settings");
        try {
            Main.setScene("/view/fxml/settings.fxml", "Settings");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

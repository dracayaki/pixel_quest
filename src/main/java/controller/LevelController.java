package controller;

import javafx.fxml.FXML;
import model.Object.Session;

public class LevelController {

    private int currentLevel;

    public void setLevel(int level) {
        this.currentLevel = level;
        System.out.println("Current level: " + currentLevel);
    }

    @FXML
    public void initialize(){
        this.currentLevel = Session.getCurrentLevel();
        System.out.println("Level loaded: " + currentLevel);

    }

}

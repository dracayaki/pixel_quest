package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import model.DAO.UserDAO;
import model.Object.Session;
import model.Object.User;

public class WinController {
    public ImageView winImage;
    public Button nextBtn;
    public Button retryBtn;
    public Button menuBtn;

    public void handleNextLevel(ActionEvent actionEvent) {
        try {

            User currentUser = Session.getCurrentUser();
            int currentLevel = currentUser.getLastLevelPlayed();
            int nextLevel = currentLevel + 1;

            UserDAO userDAO = new UserDAO();
            userDAO.updateLastLevelPlayed(currentUser.getId(), nextLevel);


            currentUser.setLastLevelPlayed(nextLevel);
            Session.setCurrentUser(currentUser);


            Main.setScene("/view/fxml/level.fxml", "Level " + nextLevel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

package controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.DAO.LevelDAO;
import model.DAO.UserDAO;
import model.Object.RecordEntry;
import model.Object.Session;
import model.Object.User;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class WinController  implements Initializable {
    public ImageView winImage;
    public Button nextBtn;
    public Button retryBtn;
    public Button menuBtn;
    public Button exitBtn;
    public TableView recordTable;
    public TableColumn usernameCol;
    public TableColumn pointsCol;
    public TableColumn timeCol;

    private static int currentLevelId;
    public Button settingsBtn;

    public static void setLevelData(int id) {
        currentLevelId = id;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        pointsCol.setCellValueFactory(new PropertyValueFactory<>("points"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));

        List<RecordEntry> records = LevelDAO.getTopRecords(currentLevelId);
        recordTable.setItems(FXCollections.observableArrayList(records));
    }


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

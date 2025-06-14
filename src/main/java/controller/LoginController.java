package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import model.DAO.UserDAO;
import model.Object.Session;
import model.Object.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class LoginController {
    public Label titleLbl;
    public VBox loginPane;

    @FXML
    private Button loginBtn;

    @FXML
    private PasswordField passwordTxt;

    @FXML
    private Hyperlink registerLink;

    @FXML
    private TextField usernameTxt;

    public void initialize() {
        loginPane.setStyle("-fx-background-color: rgba(255, 255, 255, 0.15); -fx-background-radius: 10; -fx-padding: 20;");

        // Aplicar desenfoque real
        javafx.scene.effect.GaussianBlur blur = new javafx.scene.effect.GaussianBlur(1);
        loginPane.setEffect(blur);

        try (Connection conn = utils.connectorBBDD.getConnection()) {
            System.out.println("Conexión correcta");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("No se pudo conectar a la base de datos.");
        }
    }

    @FXML
    void handleLogin(ActionEvent event) {

        String username = usernameTxt.getText().trim();
        String password = passwordTxt.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Please fill all the fields");
            return;
        }

        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserBBDD(username, password);

        if (user != null) {
            try {
                Session currentSession = new Session();
                Session.setCurrentUser(user);
                Main.setScene("/view/fxml/levelMenu.fxml", "Level");

            } catch (IOException e) {
                e.printStackTrace();
                showAlert("Error", "Something went wrong during the login");
            }
        } else {
            showAlert("Error", "Invalid username or password, please register below");
        }




    }

    @FXML
    void registerAction()  {
        try{
            Main.setScene("/view/fxml/register.fxml", "Register");
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Fail in the register");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

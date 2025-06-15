package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import model.DAO.UserDAO;
import model.Object.User;

import java.io.IOException;
import java.util.Optional;

public class RegisterController {
    public VBox loginPane;
    public Label titleLbl;
    public TextField usernameTxt;
    public PasswordField passwordTxt;
    public Button registerBtn;
    public PasswordField confirmPasswordTxt;
    public Button exitBtn;

    public void handleRegister(ActionEvent actionEvent) {
        String username = usernameTxt.getText().trim();
        String password = passwordTxt.getText().trim();
        String confirmPassword = confirmPasswordTxt.getText().trim();

        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            showAlert("Error", "Please fill all the fields");
            return;
        }

        if (!password.equals(confirmPassword)) {
            showAlert("Error", "Passwords do not match");
            return;
        }

        if (!validatePassword(password)){
            showAlert("Error", "Passwords must contain at least 8 characters long and include one uppercase letter, one number, and one of this special characters  (?=.*[@$!%*?&]).");
            return;
        }


        //create new user

        User newUser = new User(username, password);
        boolean userInserted = UserDAO.insertUser(newUser);

        if (userInserted) {
            showAlert("Success", "User successfully registered! Now we take you back to the login");
            try {
                Main.setScene("/view/fxml/login.fxml", "Login");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showAlert("Error", "There was an error in the registration");
        }
    }

    private boolean validatePassword(String password) {
        String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$"; // at least 1 uppercase letter, 1 digit, 1 special characcter (?=.*[@$!%*?&]), minimum lenght 9

        return password.matches(regex);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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
}



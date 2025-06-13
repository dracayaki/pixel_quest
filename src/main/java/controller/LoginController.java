package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

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
    }

    @FXML
    void handleLogin(ActionEvent event) {

    }

    @FXML
    void registerAction(ActionEvent event) {

    }
}

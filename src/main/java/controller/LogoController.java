package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Optional;

public class LogoController {
    public Pane main_logo_pane;
    public Label lblTitle2;
    public Label lblTitle;
    public Button exitBtn;

    public void initialize() {
        main_logo_pane.setFocusTraversable(true); //indica que puede recibir el foco del teclado
        main_logo_pane.requestFocus(); //sin esto no capta las teclas
    }

    public void handleKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE){
            try {
                Main.setScene("/view/fxml/login.fxml", "Login");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Fail in the login");
            }
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

}

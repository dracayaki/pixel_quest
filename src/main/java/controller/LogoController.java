package controller;

import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class LogoController {
    public Pane main_logo_pane;
    public Label lblTitle2;
    public Label lblTitle;

    public void initialize() {
        main_logo_pane.setFocusTraversable(true); //indica que puede recibir el foco del teclado
        main_logo_pane.requestFocus(); //sin esto no capta las teclas
    }

    public void handleKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE){
            try {
                System.out.println("should open");
                Main.setScene("/view/fxml/login.fxml", "Login");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

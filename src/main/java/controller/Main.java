package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        setScene("/view/fxml/main.fxml", "Pixel Quest");
    }

    public static void setScene(String fxmlName, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxmlName));
        Scene scene = new Scene(loader.load(), 800, 700);
        primaryStage.setScene(scene);
        primaryStage.setTitle(title);
        primaryStage.setResizable(false);
        primaryStage.show();

        scene.getRoot().requestFocus(); // asi la ventana puede detectar las teclas desde el primer momento

    }

    public static void main(String[] args) {
        launch(args);
    }
}

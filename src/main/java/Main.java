

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
        setScene("view/fxml/logo.fxml", "Pixel Quest");
    }

    public static void setScene(String fxmlName, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxmlName));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.setTitle(title);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}

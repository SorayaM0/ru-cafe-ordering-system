package Project4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(
            getClass().getResource("/view/main.fxml")
        );

        Scene scene = new Scene(root, 1400, 850);

        stage.setTitle("RU Cafe");
        stage.setScene(scene);

        stage.setMinWidth(1200);
        stage.setMinHeight(750);

        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
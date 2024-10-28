package org.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.app.utils.AppSystem;

import java.io.IOException;


public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("views/main-window.fxml"));
        Parent root = loader.load();
        stage.setTitle("Life before Deadlines");
//        try (InputStream iconStream = getClass().getResourceAsStream("assets/icon.png")) {
//            Image icon = new Image(iconStream);
//            stage.getIcons().add(icon);
//        } catch (Exception e) {
//            logger.error("Error loading icon: " + e.getMessage());
//        }
        Scene scene = new Scene(root, 600, 450);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }
}
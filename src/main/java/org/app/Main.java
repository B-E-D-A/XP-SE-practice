package org.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.desktop.AppHiddenListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;


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
        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }
}
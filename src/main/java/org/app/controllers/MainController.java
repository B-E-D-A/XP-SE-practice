package org.app.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.app.utils.Deadline;
import org.app.utils.AppSystem;
//import org.app.Main.app;

public class MainController implements Initializable {

    @FXML
    public GridPane CalendarGrid;

    private static final Logger logger = LogManager.getLogger();
    @FXML
    public Label monthName;
    @FXML
    public Button backButton;
    @FXML
    public Button nextButton;

    private LocalDate currentDate;


    private void updateMonthInformation(){
        CalendarGrid.getChildren().clear();
        int year = currentDate.getYear();
        Month month = currentDate.getMonth();
        monthName.setText(month.name() + " "  + year);
        int daysInMonth = month.length(currentDate.isLeapYear());
        int columns = 7;
        int rows = (int) Math.ceil(daysInMonth / (double) columns);

        for (int day = 1; day <= daysInMonth; day++) {
            int col = (day - 1) % columns;
            int row = (day - 1) / columns;

            VBox dayBox = new VBox();
            dayBox.setStyle("-fx-background-color: white; -fx-border-color: #153163; -fx-border-width: 1px;");
            Label dateLabel = new Label(String.valueOf(day));
            dateLabel.setStyle("-fx-text-fill: #153163");
            dayBox.getChildren().add(dateLabel);

            List<Deadline> deadlines = getDeadlinesForDay(day);
//            List<Deadline> deadlineToday = new ArrayList<>();
//            deadlineToday.add(new Deadline("Test Deadline", "28-10-2024", "20:00");
            if (deadlines != null) {
                for (Deadline deadline : deadlines) {
                    Label deadlineLabel = new Label(deadline.getName());
                    dayBox.getChildren().add(deadlineLabel);
                }
            }


            CalendarGrid.add(dayBox, col, row);
        }
    }

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        currentDate = LocalDate.now();
        updateMonthInformation();
    }

    private List<Deadline> getDeadlinesForDay(int day) {
        LocalDate date = LocalDate.of(currentDate.getYear(), currentDate.getMonth(), day);
        return AppSystem.deadlinesByDate.get(date);
    }

    @FXML
    public void turnToPreviousMonth(){
        currentDate = currentDate.minusMonths(1);
        updateMonthInformation();
    }

    @FXML
    public void turnToNextMonth(){
        currentDate = currentDate.plusMonths(1);
        updateMonthInformation();
    }


    @FXML
    public void addDeadline(){
        Stage window = new Stage();
        window.setTitle("New deadline");

        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(300);
        window.setMinHeight(100);

        Label name = new Label("Name");
        TextField nameField = new TextField();
        HBox namehbox = new HBox(name, nameField);

        Label date = new Label("Deadline Date");
        TextField dateField = new TextField();
        HBox datehbox = new HBox(date, dateField);

        Label time = new Label("Time");
        TextField timeField = new TextField();
        HBox timehbox = new HBox(time, timeField);

        Button closeButton = new Button("Create");
        closeButton.setOnAction(e -> {
            window.close();
//                logger.info("INFO", nameField.getText(), dateField.getText(), timeField.getText());
        });
        Deadline deadline = new Deadline(nameField.getText(), dateField.getText(), timeField.getText(), "False");
//        AppSystem.performAddDeadline(deadline);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(namehbox, datehbox, timehbox, closeButton);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}

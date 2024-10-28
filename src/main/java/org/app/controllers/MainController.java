package org.app.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    public GridPane CalendarGrid;
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
//            dayBox.setStyle("dayVB");
            Label dateLabel = new Label(String.valueOf(day));
            dayBox.getChildren().add(dateLabel);

            List<String> deadlines = getDeadlinesForDay(day);
            for (String deadline : deadlines) {
                Label deadlineLabel = new Label(deadline);
                dayBox.getChildren().add(deadlineLabel);
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

    private List<String> getDeadlinesForDay(int day) {
        List<String> deadlines = new ArrayList<>();
        if (day % 2 == 0) {
            deadlines.add("Deadline");
        }
        return deadlines;
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
}

package org.app.utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;


public class AppSystem {

    public static List<Deadline> deadlines;
    public static Map<LocalDate, List<Deadline>> deadlinesByDate = new HashMap<>();

    public static Connection connector = null;
    private static final Logger logger = LogManager.getLogger();

    public AppSystem(){
        deadlines = getDeadlinesFromDB();
        organizeDeadlinesByDate(deadlines);
    }

    public void organizeDeadlinesByDate(List<Deadline> deadlines) {

        for (Deadline deadline : deadlines) {
            LocalDate date = LocalDate.parse(deadline.getDate());
            deadlinesByDate.putIfAbsent(date, new ArrayList<>());
            deadlinesByDate.get(date).add(deadline);
        }

    }

    public List<Deadline> getDeadlinesFromDB(){
        List<Deadline> deadlinesFromDB = new ArrayList<>();
        deadlinesFromDB.addAll(getFinishedDeadlines());
        deadlinesFromDB.addAll(getUnfinishedDeadlines());
        return deadlinesFromDB;
    };

    public List<Deadline> getFinishedDeadlines() {
        List<Deadline> finishedDeadlines = new ArrayList<>();
        String query = "SELECT * FROM deadlines WHERE is_finished = 'True'";

        try (PreparedStatement statement = connector.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Deadline deadline = new Deadline();
                deadline.setId(UUID.fromString(resultSet.getString("id")));
                deadline.setName(resultSet.getString("dl_text"));
                deadline.setDate(resultSet.getString("dl_date"));
                deadline.setTime(resultSet.getString("dl_time"));
                deadline.setIsFinished(resultSet.getString("is_finished"));

                finishedDeadlines.add(deadline);
            }
        } catch (SQLException e) {
            logger.error("Failed to retrieve finished deadlines: " + e.getMessage());
        }

        return finishedDeadlines;
    }

    public List<Deadline> getUnfinishedDeadlines() {
        List<Deadline> unfinishedDeadlines = new ArrayList<>();
        String query = "SELECT * FROM deadlines WHERE is_finished = 'False'";

        try (PreparedStatement statement = connector.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Deadline deadline = new Deadline();
                deadline.setId(UUID.fromString(resultSet.getString("id")));
                deadline.setName(resultSet.getString("dl_text"));
                deadline.setDate(resultSet.getString("dl_date"));
                deadline.setTime(resultSet.getString("dl_time"));
                deadline.setIsFinished(resultSet.getString("is_finished"));

                unfinishedDeadlines.add(deadline);
            }
        } catch (SQLException e) {
            logger.error("Failed to retrieve unfinished deadlines: " + e.getMessage());
        }

        return unfinishedDeadlines;
    }


    public static void connect() {
        try {
            String url = "jdbc:sqlite:" + "/Users/syberlord/XP-SE-practice-main/database/database.db";
            connector = DriverManager.getConnection(url);
            logger.info("Connected to database");

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    public static void performAddDeadline(Deadline deadline) {
        String id = deadline.getId().toString();
        String text = deadline.getName();
        String date = deadline.getDate();
        String time = deadline.getTime();
        String isFinished = deadline.getIsFinished();

        String sql = "INSERT INTO deadlines (id, dl_text, dl_date, dl_time, is_finished, password, password_salt) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connector.prepareStatement(sql)) {
            statement.setString(1, id);
            statement.setString(2, text);
            statement.setString(3, date);
            statement.setString(4, time);
            statement.setString(5, isFinished);
            statement.executeUpdate();
            logger.info("User added into db");
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    public static void performEditDeadline(Deadline deadline) {
        String id =(deadline.getId().toString());
        String text = deadline.getName();
        String date = deadline.getDate();
        String time = deadline.getTime();
        String isFinished = deadline.getIsFinished();

        String updateQuery = "UPDATE deadlines SET dl_text = ?, dl_date = ?, dl_time = ?, is_finished = ? WHERE id = ?";

        try (PreparedStatement statement = connector.prepareStatement(updateQuery)) {
            statement.setString(1, text);
            statement.setString(2, date);
            statement.setString(3, time);
            statement.setString(4, isFinished);
            statement.setString(5, id);

            statement.executeUpdate();
            logger.info("Deadline information updated in the database.");
        } catch (SQLException e) {
            logger.error("Failed to update deadline information: " + e.getMessage());
        }
    }


}
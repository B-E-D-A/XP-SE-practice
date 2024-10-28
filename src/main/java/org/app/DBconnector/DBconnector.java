package org.app.DBconnector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Класс DBconnector отвечает за установление соединения с базой данных SQLite
 * и создание необходимых таблиц.
 */

public class DBconnector {
    private static final Logger logger = LogManager.getLogger();

    public static void connect() {
        Connection connector = null;
        try {
            String url = "jdbc:sqlite:" + "C:/Users/HUAWEI/Documents/hse/course3/se/XP-SE-practice/database/database.db";
            connector = DriverManager.getConnection(url);

            logger.info("Connection to SQLite has been established.");

            String sql = "CREATE TABLE IF NOT EXISTS deadlines ("
                    + "id INTEGER,"
                    + "dl_text TEXT NOT NULL,"
                    + "dl_date TEXT NOT NULL,"
                    + "dl_time TEXT NOT NULL,"
                    + "is_finished TEXT NOT NULL"
                    + ")";
            Statement statement = connector.createStatement();
            statement.executeUpdate(sql);
            logger.info("Tables has been created");
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            try {
                if (connector != null) {
                    connector.close();
                }
            } catch (SQLException ex) {
                logger.error(ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        connect();
    }
}
package ru.eltech.project.server;

import ru.eltech.project.api.data.Shoe;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DatabaseManager {
    private static Connection conn;

    public static Connection getConn(){
        return conn;
    }
    public static void closeConn() throws SQLException {
        conn.close();
    }

    public static void initDB() {
        String url = "jdbc:postgresql://localhost/postgres";
        String login = "postgres";
        String password = "1234";
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            conn = DriverManager.getConnection(url, login, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

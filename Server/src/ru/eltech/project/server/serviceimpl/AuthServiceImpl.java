package ru.eltech.project.server.serviceimpl;

import ru.eltech.project.api.data.Client;
import ru.eltech.project.api.services.AuthService;
import ru.eltech.project.server.DatabaseManager;

import java.sql.*;
import java.util.UUID;

public class AuthServiceImpl implements AuthService {
    private String query;
    private ResultSet resultSet;

    @Override
    public String auth(String login, String password) {
        try {
            Statement statement = DatabaseManager.getConn().createStatement();
            String id = "Not found";
            query = "SELECT id FROM client WHERE login='" + login + "' AND password='" + password + "';";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                id = resultSet.getString("id");
            }
            statement.close();
            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String authAdmin(String login, String password) {
        try {
            Connection conn = DatabaseManager.getConn();
            Statement statement = conn.createStatement();

            String id = "Not found";
            query = "SELECT id FROM admin WHERE login='" + login + "' AND password='" + password + "';";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                id = resultSet.getString("id");
            }
            statement.close();
            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String registration(Client client, String login, String password) {
        try {
            Statement statement = DatabaseManager.getConn().createStatement();
            String id = "Not found";
            query = "INSERT INTO client(id, login, password, name, address, phone, mail) " +
                    "VALUES ('" + UUID.randomUUID() + "','" + login + "','" + password + "','" + client.getName() +
                    "','" + client.getAddress() + "','" + client.getPhone() + "','" + client.getEmail() + "');";
            statement.execute(query);
            query = "SELECT id FROM client WHERE login='" + login + "' AND password='" + password + "';";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                id = resultSet.getString("id");
            }
            statement.close();
            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

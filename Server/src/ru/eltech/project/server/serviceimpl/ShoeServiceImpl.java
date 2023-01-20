package ru.eltech.project.server.serviceimpl;

import ru.eltech.project.api.data.Shoe;
import ru.eltech.project.api.data.Size;
import ru.eltech.project.api.services.ShoeService;
import ru.eltech.project.server.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShoeServiceImpl implements ShoeService {
    private String query;
    private ResultSet resultSet;

    @Override
    public String addShoe(Shoe shoe) {
        try {
            String id = UUID.randomUUID().toString();
            shoe.setId(id);
            Statement statement = DatabaseManager.getConn().createStatement();
            String sql = "INSERT INTO shoes(id, name, articul, price, color, sale, brand, material, picture) " +
                    "VALUES ('" + shoe.getId() + "', '" + shoe.getName() + "', '" + shoe.getArticul() + "'" +
                    ", '" + shoe.getPrice() + "', '" + shoe.getColor() + "', '" + shoe.getSale() + "'" +
                    ", '" + shoe.getBrand() + "', '" + shoe.getAbout() + "', '" + shoe.getPicture() + "');";
            statement.execute(sql);
            statement.close();
            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delShoe(String id) {
        try {
            Statement statement = DatabaseManager.getConn().createStatement();
            String sql = "DELETE FROM shoes WHERE id='" + id + "'";
            statement.execute(sql);
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Shoe getShoe(String id) {
        try {
            Statement statement = DatabaseManager.getConn().createStatement();
            String sql = "SELECT * FROM shoes WHERE id='" + id + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            Shoe shoe = null;
            if (resultSet.next()) {
                shoe = new Shoe();
                shoe.setId(id);
                shoe.setName(resultSet.getString("name"));
                shoe.setAbout(resultSet.getString("material"));
                shoe.setArticul(resultSet.getString("articul"));
                shoe.setPrice(resultSet.getDouble("price"));
                shoe.setBrand(resultSet.getString("brand"));
                shoe.setColor(resultSet.getString("color"));
                shoe.setSale(resultSet.getDouble("sale"));
                shoe.setPicture(resultSet.getString("picture"));
            }
            statement.close();
            return shoe;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Shoe> getShoesList() {
        try {
            Statement statement = DatabaseManager.getConn().createStatement();
            query = "SELECT * FROM shoes";
            ResultSet resultSet = statement.executeQuery(query);
            List<Shoe> shoeList = new ArrayList<>();
            Shoe shoe = null;
            while (resultSet.next()) {
                shoe = new Shoe();
                shoe.setId(resultSet.getString("id"));
                shoe.setName(resultSet.getString("name"));
                shoe.setAbout(resultSet.getString("material"));
                shoe.setArticul(resultSet.getString("articul"));
                shoe.setPrice(resultSet.getDouble("price"));
                shoe.setBrand(resultSet.getString("brand"));
                shoe.setColor(resultSet.getString("color"));
                shoe.setSale(resultSet.getDouble("sale"));
                shoe.setPicture(resultSet.getString("picture"));
                shoeList.add(shoe);
            }
            statement.close();
            return shoeList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addShoeSize(Size size) {
        try {
            Statement statement = DatabaseManager.getConn().createStatement();
            String sql = "INSERT INTO sizes(shoe_id, size, quantity) " +
                    "VALUES ('" + size.getShoeId() + "', " + size.getSize() + ", " + size.getQuantity() + ");";
            statement.execute(sql);
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delShoeSize(Size size) {
        try {
            Statement statement = DatabaseManager.getConn().createStatement();
            String sql = "DELETE FROM sizes WHERE shoe_id='" + size.getShoeId() + "' and size = '" + size.getSize() + "';";
            statement.execute(sql);
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Size> getSizes(String id) {
        try {
            Statement statement = DatabaseManager.getConn().createStatement();
            query = "SELECT * FROM sizes where shoe_id='"+id+"';";
            ResultSet resultSet = statement.executeQuery(query);
            List<Size> shoeList = new ArrayList<>();
            Size size = null;
            while (resultSet.next()) {
                size = new Size();
                size.setShoeId(resultSet.getString("shoe_id"));
                size.setSize(resultSet.getDouble("size"));
                size.setQuantity(resultSet.getInt("quantity"));
                shoeList.add(size);
            }
            statement.close();
            return shoeList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateShoe(Shoe shoe) {
        try {
            Statement statement = DatabaseManager.getConn().createStatement();
            String sql = "UPDATE shoes " +
                    "SET name='" + shoe.getName() + "', articul='" + shoe.getArticul() + "'" +
                    ", price='" + shoe.getPrice() + "', color='" + shoe.getColor() + "', sale='" + shoe.getSale() + "'" +
                    ", brand='" + shoe.getBrand() + "', material='" + shoe.getAbout() + "', picture='" + shoe.getPicture() + "' " +
                    "WHERE id='" + shoe.getId() + "';";
            statement.executeUpdate(sql);
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

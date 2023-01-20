package ru.eltech.project.server.serviceimpl;

import ru.eltech.project.api.data.Order;
import ru.eltech.project.api.data.OrderContent;
import ru.eltech.project.api.data.Shoe;
import ru.eltech.project.api.data.enums.DeliveryType;
import ru.eltech.project.api.data.enums.PaymentType;
import ru.eltech.project.api.data.enums.Status;
import ru.eltech.project.api.services.OrderService;
import ru.eltech.project.server.DatabaseManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderServiceImpl implements OrderService {
    private String query;
    private ResultSet resultSet;

    @Override
    public String createOrder(Order order, List<OrderContent> orderContentList) {
        try {
            String id = UUID.randomUUID().toString();
            order.setId(id);
            Statement statement = DatabaseManager.getConn().createStatement();
            String sql = "INSERT INTO order(id, client_id, delivery_type, payment_type, payment_date, delivery_date, status, summary_price) " +
                    "VALUES ('" + order.getId() + "', '" + order.getClientId() + "', '" + order.getDeliveryType() + "'" +
                    ", '" + order.getPaymentType() + "', '" + order.getPaymentDate() + "', '" + order.getDeliveryDate() + "'" +
                    ", '" + order.getStatus() + "', '" + order.getSummaryPrice() + "');";
            statement.execute(sql);
            for (OrderContent item : orderContentList) {
                sql = "INSERT INTO order_content(shoe_id, order_id, size , quantity, price) " +
                        "VALUES ('" + item.getShoeId() + "', '" + item.getOrderId() + "', '" + item.getShoeSize() + "'" +
                        ", '" + item.getQuantity() + "', '" + item.getPrice() + "');";
                statement.execute(sql);
            }

            statement.close();
            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void cancelOrder(String id) {
        try {
            Statement statement = DatabaseManager.getConn().createStatement();
            String sql = "UPDATE order SET status='CANCELED' WHERE id = '" + id + "';";
            statement.execute(sql);
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Order getOrder(String id) {
        try {
            Statement statement = DatabaseManager.getConn().createStatement();
            String sql = "SELECT * FROM order WHERE id='" + id + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            Order order = null;
            if (resultSet.next()) {
                order = new Order();
                order.setId(id);
                order.setClientId(resultSet.getString("client_id"));
                order.setStatus(Status.valueOf(resultSet.getString("status")));
                order.setDeliveryDate(resultSet.getDate("delivery_date"));
                order.setDeliveryType(DeliveryType.valueOf(resultSet.getString("delivery_type")));
                order.setPaymentDate(resultSet.getDate("payment_date"));
                order.setPaymentType(PaymentType.valueOf(resultSet.getString("payment_type")));
                order.setSummaryPrice(resultSet.getDouble("summary_price"));
            }
            statement.close();
            return order;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<OrderContent> getOrderContent(String id) {
        try {
            Statement statement = DatabaseManager.getConn().createStatement();
            query = "SELECT * FROM shoes";
            ResultSet resultSet = statement.executeQuery(query);
            List<OrderContent> orderContents = new ArrayList<>();
            OrderContent orderContent = null;
            if (resultSet.next()) {
                orderContent = new OrderContent();
                orderContent.setOrderId(id);
                orderContent.setShoeId(resultSet.getString("client_id"));
                orderContent.setPrice(resultSet.getDouble("price"));
                orderContent.setQuantity(resultSet.getInt("quantity"));
                orderContent.setShoeSize(resultSet.getDouble("size"));
                orderContents.add(orderContent);
            }
            statement.close();
            return orderContents;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> getAllOrders() {
        try {
            Statement statement = DatabaseManager.getConn().createStatement();
            query = "SELECT * FROM shoes";
            ResultSet resultSet = statement.executeQuery(query);
            List<Order> orders = new ArrayList<>();
            Order order = null;
            if (resultSet.next()) {
                order = new Order();
                order.setId(resultSet.getString("id"));
                order.setClientId(resultSet.getString("client_id"));
                order.setStatus(Status.valueOf(resultSet.getString("status")));
                order.setDeliveryDate(resultSet.getDate("delivery_date"));
                order.setDeliveryType(DeliveryType.valueOf(resultSet.getString("delivery_type")));
                order.setPaymentDate(resultSet.getDate("payment_date"));
                order.setPaymentType(PaymentType.valueOf(resultSet.getString("payment_type")));
                order.setSummaryPrice(resultSet.getDouble("summary_price"));
                orders.add(order);
            }
            statement.close();
            return orders;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

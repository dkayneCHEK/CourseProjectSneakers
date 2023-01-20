package ru.eltech.project.api.services;

import ru.eltech.project.api.data.Order;
import ru.eltech.project.api.data.OrderContent;

import java.util.List;

public interface OrderService {
    String createOrder(Order order, List<OrderContent> orderContent);

    void cancelOrder(String id);

    Order getOrder(String id);

    List<OrderContent> getOrderContent(String id);

    List<Order> getAllOrders();
}

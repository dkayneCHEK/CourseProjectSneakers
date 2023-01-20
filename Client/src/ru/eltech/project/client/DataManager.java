package ru.eltech.project.client;

import ru.eltech.project.api.data.CartItem;
import ru.eltech.project.api.data.Order;
import ru.eltech.project.api.data.Shoe;
import ru.eltech.project.api.data.Size;

import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static String AUTH_TOKEN;

    private static List<Shoe> CATALOG;

    private static List<Order> ORDERS;

    private static List<CartItem> CART;

    public DataManager() {
        AUTH_TOKEN = "";

        CATALOG = new ArrayList();

        ORDERS = new ArrayList();

        CART = new ArrayList();
    }

    public static String getAuthToken() {
        return AUTH_TOKEN;
    }

    public static void setAuthToken(String authToken) {
        AUTH_TOKEN = authToken;
    }

    public static List<Shoe> getCATALOG() {
        return CATALOG;
    }

    public static void setCATALOG(List<Shoe> CATALOG) {
        DataManager.CATALOG = CATALOG;
    }

    public static List<Order> getORDERS() {
        return ORDERS;
    }

    public static void setORDERS(List<Order> ORDERS) {
        DataManager.ORDERS = ORDERS;
    }

    public static List<CartItem> getCART() {
        return CART;
    }

    public static void setCART(List<CartItem> CART) {
        DataManager.CART = CART;
    }

    public static void addToCATALOG(Shoe shoe) {
        DataManager.CATALOG.add(shoe);
    }

    public static void addToCART(CartItem shoe) {
        DataManager.CART.add(shoe);
    }

    public static void delFromCATALOG(String id) {
        DataManager.CATALOG.removeIf(shoe -> shoe.getId().equals(id));
    }

    public static void delFromCART(String id) {
        DataManager.CART.removeIf(shoe -> shoe.getId().equals(id));
    }
}

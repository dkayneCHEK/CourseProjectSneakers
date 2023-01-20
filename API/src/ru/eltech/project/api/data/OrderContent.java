package ru.eltech.project.api.data;

public class OrderContent {
    private String orderId;
    private String shoeId;
    private double shoeSize;
    private int quantity;
    private double price;

    public OrderContent(String orderId, String shoeId, float shoeSize, int quantity, float price) {
        this.orderId = orderId;
        this.shoeId = shoeId;
        this.shoeSize = shoeSize;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderContent() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getShoeId() {
        return shoeId;
    }

    public void setShoeId(String shoeId) {
        this.shoeId = shoeId;
    }

    public double getShoeSize() {
        return shoeSize;
    }

    public void setShoeSize(double shoeSize) {
        this.shoeSize = shoeSize;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

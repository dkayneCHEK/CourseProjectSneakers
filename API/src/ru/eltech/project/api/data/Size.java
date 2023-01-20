package ru.eltech.project.api.data;

public class Size {
    private String shoeId;
    private double size;
    private int quantity;

    public Size(String shoeId, float size, int quantity) {
        this.shoeId = shoeId;
        this.size = size;
        this.quantity = quantity;
    }

    public Size() {

    }

    public String getShoeId() {
        return shoeId;
    }

    public void setShoeId(String shoeId) {
        this.shoeId = shoeId;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

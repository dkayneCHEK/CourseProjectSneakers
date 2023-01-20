package ru.eltech.project.api.data;

import java.io.Serializable;
import java.util.Objects;

public class CartItem implements Serializable {
    private String id;
    private String name;
    private double price;
    private String about;
    private String articul;
    private String brand;
    private String color;
    private double sale;
    private String picture;
    private double size;
    private int quantity;


    public CartItem(String id, String name, double price, String about, String articul, String brand, String color, double sale, String picture, double size, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.about = about;
        this.articul = articul;
        this.brand = brand;
        this.color = color;
        this.sale = sale;
        this.picture = picture;
        this.size = size;
        this.quantity = quantity;
    }

    public CartItem() {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getArticul() {
        return articul;
    }

    public void setArticul(String articul) {
        this.articul = articul;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getSale() {
        return sale;
    }

    public void setSale(double sale) {
        this.sale = sale;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return Double.compare(cartItem.price, price) == 0 && Double.compare(cartItem.sale, sale) == 0 && id.equals(cartItem.id) && name.equals(cartItem.name) && about.equals(cartItem.about) && articul.equals(cartItem.articul) && brand.equals(cartItem.brand) && color.equals(cartItem.color) && picture.equals(cartItem.picture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, about, articul, brand, color, sale, picture);
    }
}
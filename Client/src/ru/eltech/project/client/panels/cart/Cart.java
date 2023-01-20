package ru.eltech.project.client.panels.cart;

import ru.eltech.project.api.data.CartItem;
import ru.eltech.project.api.data.Shoe;

import javax.swing.*;

public class Cart extends JList<CartItem> {
    public Cart() {
        super(new CartModel<>());
        setCellRenderer(new CartRenderer());
    }

    public CartModel getProductModel() {
        return (CartModel) getModel();
    }
}

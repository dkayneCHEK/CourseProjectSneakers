package ru.eltech.project.client.panels.cart;

import ru.eltech.project.api.data.CartItem;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CartModel<CartItem> extends AbstractListModel<CartItem> {
    private List<CartItem> list = new ArrayList<>();

    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public CartItem getElementAt(int index) {
        return list.get(index);
    }

    public void setProductList(List<CartItem> productList) {
        list = productList;
    }

    /*
    *

    postgresql jdbc

    *
    * */
    public void addProduct(CartItem product) {
        list.add(product);
        fireIntervalAdded(product, list.size() - 1, list.size() - 1);

    }

    public void delProduct(CartItem product) {
        list.remove(product);
        fireIntervalRemoved(product, list.size(), list.size());
    }

    public void clear() {
        list.clear();
    }

}

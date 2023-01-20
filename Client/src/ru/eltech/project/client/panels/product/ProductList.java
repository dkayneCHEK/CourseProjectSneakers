package ru.eltech.project.client.panels.product;

import ru.eltech.project.api.data.Shoe;

import javax.swing.*;

public class ProductList extends JList<Shoe> {
    public ProductList() {
        super(new ProductModel<>());
        setCellRenderer(new ProductRenderer());
    }

    public ProductModel getProductModel() {
        return (ProductModel) getModel();
    }
}

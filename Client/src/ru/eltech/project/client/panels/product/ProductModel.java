package ru.eltech.project.client.panels.product;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ProductModel<Shoe> extends AbstractListModel<Shoe> {
    private List<Shoe> list = new ArrayList<>();

    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public Shoe getElementAt(int index) {
        return list.get(index);
    }

    public void setProductList(List<Shoe> productList) {
        list = productList;
    }
/*
*

postgresql jdbc

*
* */
    public void addProduct(Shoe product) {
        list.add(product);
        fireIntervalAdded(product,list.size()-1, list.size()-1);

    }

    public void delProduct(Shoe product) {
        list.remove(product);
        fireIntervalRemoved(product, list.size(), list.size());
    }

    public void clear(){
        list.clear();
    }

}

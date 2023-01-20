package ru.eltech.project.client.panels.cart;

import ru.eltech.project.api.data.CartItem;
import ru.eltech.project.api.data.Shoe;

import javax.swing.*;
import java.awt.*;

public class CartRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        setText(((CartItem)value).getName());
        return this;
    }
}

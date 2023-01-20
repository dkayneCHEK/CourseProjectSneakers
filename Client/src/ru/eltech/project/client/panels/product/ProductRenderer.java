package ru.eltech.project.client.panels.product;

import ru.eltech.project.api.data.Shoe;

import javax.swing.*;
import java.awt.*;

public class ProductRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        setText(((Shoe)value).getName());
        return this;
    }
}

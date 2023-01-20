package ru.eltech.project.client.panels.product;

import ru.eltech.project.api.data.CartItem;
import ru.eltech.project.api.data.Shoe;
import ru.eltech.project.api.data.Size;
import ru.eltech.project.client.DataManager;
import ru.eltech.project.client.clients.ShoeClient;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductsPanel extends JPanel {

    public ProductsPanel() {
        ProductList list = new ProductList();
        list.getProductModel().clear();
        for(Shoe shoe : DataManager.getCATALOG()){
            list.getProductModel().addProduct(shoe);
        }


        JPanel shoePanel = new JPanel(new GridLayout(0, 2));
        Shoe selectedShoe;
        if (list.getSelectedValue() != null) {
            selectedShoe = list.getSelectedValue();
        } else {
            selectedShoe = new Shoe("", "", 0, "", "", "", "", 0, "");
        }

        System.out.println(selectedShoe.getName());
        JLabel name = new JLabel("Имя: ");
        JLabel nameText = new JLabel(selectedShoe.getName());
        JLabel price = new JLabel("Стоимость: ");
        JLabel priceText = new JLabel(String.valueOf(selectedShoe.getPrice()));
        JLabel about = new JLabel("Описание/материал: ");
        JLabel aboutText = new JLabel(selectedShoe.getAbout());
        JLabel brand = new JLabel("Бренд: ");
        JLabel brandText = new JLabel(selectedShoe.getBrand());
        JLabel sale = new JLabel("Скидка: ");
        JLabel saleText = new JLabel(String.valueOf(selectedShoe.getSale()));
        JLabel color = new JLabel("Цвет: ");
        JLabel colorText = new JLabel(selectedShoe.getColor());
        JLabel articul = new JLabel("Артикул: ");
        JLabel articulText = new JLabel(selectedShoe.getArticul());
        JButton addToCart = new JButton("Добавить в корзину");
        JTextField quantityField = new JTextField();


        JComboBox<Size> sizes = new JComboBox();
        JLabel quantity = new JLabel("");
        JLabel quantityText = new JLabel("В наличии:");
        sizes.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if(value!=null){
                    setText(String.valueOf(((Size) value).getSize()));
                    quantity.setText(String.valueOf(((Size) value).getQuantity()));
                } else {
                    quantity.setText("");
                }
                return this;
            }
        });
        add(sizes, BorderLayout.NORTH);
        shoePanel.add(name);
        shoePanel.add(nameText);
        shoePanel.add(price);
        shoePanel.add(priceText);
        shoePanel.add(about);
        shoePanel.add(aboutText);
        shoePanel.add(brand);
        shoePanel.add(brandText);
        shoePanel.add(sale);
        shoePanel.add(saleText);
        shoePanel.add(color);
        shoePanel.add(colorText);
        shoePanel.add(articul);
        shoePanel.add(articulText);

        shoePanel.add(quantityText);
        shoePanel.add(quantity);
        shoePanel.add(sizes);
        shoePanel.add(addToCart);
        shoePanel.add(quantityField);

        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.add(addToCart);


        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(toolBar, BorderLayout.NORTH);
        leftPanel.add(list, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(shoePanel, BorderLayout.NORTH);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setDividerLocation(200);

        setLayout(new BorderLayout());
        add(splitPane, BorderLayout.CENTER);
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Shoe selectedShoe = list.getSelectedValue();
                System.out.println(selectedShoe.getName());
                nameText.setText(selectedShoe.getName());

                priceText.setText(String.valueOf(selectedShoe.getPrice()));

                aboutText.setText(selectedShoe.getAbout());

                brandText.setText(selectedShoe.getBrand());

                saleText.setText(String.valueOf(selectedShoe.getSale()));

                colorText.setText(selectedShoe.getColor());

                articulText.setText(selectedShoe.getArticul());

                sizes.removeAllItems();
                List<Size> sizeList = ShoeClient.getAllShoeSizes(list.getSelectedValue().getId());
                for (Size size : sizeList) {
                    sizes.addItem(size);
                }
                updateUI();
            }
        });

        addToCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CartItem shoe = new CartItem();
                shoe.setId(list.getSelectedValue().getId());
                shoe.setName(nameText.getText());
                shoe.setColor(colorText.getText());
                shoe.setBrand(brandText.getText());
                shoe.setArticul(articulText.getText());
                shoe.setAbout(aboutText.getText());
                shoe.setPrice(Double.parseDouble(priceText.getText()));
                shoe.setSale(Double.parseDouble(saleText.getText()));
                shoe.setPicture("1");
                shoe.setSize(((Size) sizes.getSelectedItem()).getSize());
                shoe.setQuantity(Integer.parseInt(quantityField.getText()));
                DataManager.addToCART(shoe);
            }
        });
    }
}

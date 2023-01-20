package ru.eltech.project.client.panels;

import ru.eltech.project.api.data.Shoe;
import ru.eltech.project.client.clients.ShoeClient;
import ru.eltech.project.client.components.AddShoe;
import ru.eltech.project.client.panels.product.ProductList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class OrdersPanel extends JPanel {

    public OrdersPanel() {
        ProductList list = new ProductList();
        list.getProductModel().setProductList(ShoeClient.getAllShoes());


        JTextField priceField = new JTextField(8);
        JTextArea aboutArea = new JTextArea();
        JButton addButton = new JButton("Add");
        JButton delButton = new JButton("Del");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddShoe dialog = new AddShoe();
                dialog.pack();
                dialog.setVisible(true);
                dialog.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        super.windowClosed(e);
                        list.getProductModel().setProductList(ShoeClient.getAllShoes());
                    }
                });
            }
        });

        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShoeClient.delShoe(list.getSelectedValue().getId());
                list.getProductModel().setProductList(ShoeClient.getAllShoes());
            }
        });
        JPanel shoePanel = new JPanel(new GridLayout(0,2));
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

        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.add(addButton);
        toolBar.add(delButton);


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

                updateUI();
            }
        });
    }
}

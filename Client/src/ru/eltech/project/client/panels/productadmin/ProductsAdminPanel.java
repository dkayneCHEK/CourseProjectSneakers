package ru.eltech.project.client.panels.productadmin;

import ru.eltech.project.api.data.Shoe;
import ru.eltech.project.client.DataManager;
import ru.eltech.project.client.clients.ShoeClient;
import ru.eltech.project.client.components.AddShoe;
import ru.eltech.project.client.components.ShoeSizeDialog;
import ru.eltech.project.client.panels.product.ProductList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ProductsAdminPanel extends JPanel {

    public ProductsAdminPanel() {
        ProductList list = new ProductList();
        list.getProductModel().setProductList(ShoeClient.getAllShoes());

        JButton addButton = new JButton("Add");
        JButton delButton = new JButton("Del");
        JButton addSizeButton = new JButton("Add size");


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
                        list.getProductModel().setProductList(DataManager.getCATALOG());
                    }
                });
            }
        });
        addSizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShoeSizeDialog dialog = new ShoeSizeDialog(list.getSelectedValue().getId());
                dialog.pack();
                dialog.setVisible(true);
                dialog.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        super.windowClosed(e);
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
        JPanel shoePanel = new JPanel(new GridLayout(0, 2));
        Shoe selectedShoe;
        if (list.getSelectedValue() != null) {
            selectedShoe = list.getSelectedValue();
        } else {
            selectedShoe = new Shoe("", "", 0, "", "", "", "", 0, "");
        }

        JLabel name = new JLabel("Имя: ");
        JTextField nameText = new JTextField(selectedShoe.getName());
        JLabel price = new JLabel("Стоимость: ");
        JTextField priceText = new JTextField(String.valueOf(selectedShoe.getPrice()));
        JLabel about = new JLabel("Описание/материал: ");
        JTextField aboutText = new JTextField(selectedShoe.getAbout());
        JLabel brand = new JLabel("Бренд: ");
        JTextField brandText = new JTextField(selectedShoe.getBrand());
        JLabel sale = new JLabel("Скидка: ");
        JTextField saleText = new JTextField(String.valueOf(selectedShoe.getSale()));
        JLabel color = new JLabel("Цвет: ");
        JTextField colorText = new JTextField(selectedShoe.getColor());
        JLabel articul = new JLabel("Артикул: ");
        JTextField articulText = new JTextField(selectedShoe.getArticul());
        JButton updateBtn = new JButton("Обновить");
        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Shoe shoe = new Shoe();
                shoe.setId(list.getSelectedValue().getId());
                shoe.setName(nameText.getText());
                shoe.setColor(colorText.getText());
                shoe.setBrand(brandText.getText());
                shoe.setArticul(articulText.getText());
                shoe.setAbout(aboutText.getText());
                shoe.setPrice(Double.parseDouble(priceText.getText()));
                shoe.setSale(Double.parseDouble(saleText.getText()));
                shoe.setPicture("1");
                ShoeClient.updateShoe(shoe);

                list.getProductModel().setProductList(ShoeClient.getAllShoes());
                updateUI();
            }
        });
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
        shoePanel.add(updateBtn);

        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.add(addButton);
        toolBar.add(delButton);
        toolBar.add(addSizeButton);


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

package ru.eltech.project.client.panels.cart;

import ru.eltech.project.api.data.CartItem;
import ru.eltech.project.api.data.Shoe;
import ru.eltech.project.client.DataManager;
import ru.eltech.project.client.clients.ShoeClient;
import ru.eltech.project.client.components.AddShoe;
import ru.eltech.project.client.components.CreateOrderDialog;
import ru.eltech.project.client.panels.product.ProductList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CartPanel extends JPanel {

    public CartPanel() {
        Cart list = new Cart();
        list.getProductModel().setProductList(DataManager.getCART());

        JButton createOrderButton = new JButton("Оформить заказ");
        JButton delButton = new JButton("Удалить из корзины");

        createOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateOrderDialog dialog = new CreateOrderDialog();
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
                DataManager.delFromCART(list.getSelectedValue().getId());
                list.getProductModel().setProductList(DataManager.getCART());
            }
        });
        JPanel shoePanel = new JPanel(new GridLayout(0, 2));
        CartItem selectedCartItem = new CartItem("","",0,"","","","",0,"",0,0);
        if (list.getSelectedValue() != null) {
            selectedCartItem = list.getSelectedValue();
        }

        System.out.println(selectedCartItem.getName());
        JLabel name = new JLabel("Имя: ");
        JLabel nameText = new JLabel(selectedCartItem.getName());
        JLabel price = new JLabel("Стоимость: ");
        JLabel priceText = new JLabel(String.valueOf(selectedCartItem.getPrice()));
        JLabel about = new JLabel("Описание/материал: ");
        JLabel aboutText = new JLabel(selectedCartItem.getAbout());
        JLabel brand = new JLabel("Бренд: ");
        JLabel brandText = new JLabel(selectedCartItem.getBrand());
        JLabel sale = new JLabel("Скидка: ");
        JLabel saleText = new JLabel(String.valueOf(selectedCartItem.getSale()));
        JLabel color = new JLabel("Цвет: ");
        JLabel colorText = new JLabel(selectedCartItem.getColor());
        JLabel articul = new JLabel("Артикул: ");
        JLabel articulText = new JLabel(selectedCartItem.getArticul());
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
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                CartItem selectedCartItem = list.getSelectedValue();
                System.out.println(selectedCartItem.getName());
                nameText.setText(selectedCartItem.getName());

                priceText.setText(String.valueOf(selectedCartItem.getPrice()));

                aboutText.setText(selectedCartItem.getAbout());

                brandText.setText(selectedCartItem.getBrand());

                saleText.setText(String.valueOf(selectedCartItem.getSale()));

                colorText.setText(selectedCartItem.getColor());

                articulText.setText(selectedCartItem.getArticul());

                updateUI();
            }
        });
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.add(createOrderButton);
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

    }
}

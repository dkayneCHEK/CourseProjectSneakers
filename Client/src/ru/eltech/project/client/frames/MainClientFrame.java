package ru.eltech.project.client.frames;

import ru.eltech.project.client.DataManager;
import ru.eltech.project.client.panels.cart.CartPanel;
import ru.eltech.project.client.panels.OrdersPanel;
import ru.eltech.project.client.panels.product.ProductsPanel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainClientFrame extends JFrame {
    public MainClientFrame() throws HeadlessException, IOException {
        super("Product base");

        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");
        JMenuItem item1 = new JMenuItem("Log out");
        JMenuItem itemExit = new JMenuItem("Exit");
        menuFile.add(item1);
        menuFile.addSeparator();
        menuFile.add(itemExit);
        menuBar.add(menuFile);

        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataManager.setAuthToken("");
                dispose();
                GreetingFrame frame = null;
                try {
                    frame = new GreetingFrame();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                frame.setSize(1200, 800);
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
        itemExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Products", new ProductsPanel());
        tabbedPane.addTab("Cart", new CartPanel());
        tabbedPane.addTab("Orders", new OrdersPanel());
        setLayout(new BorderLayout());
        add(tabbedPane, BorderLayout.CENTER);
        setJMenuBar(menuBar);

    }
}

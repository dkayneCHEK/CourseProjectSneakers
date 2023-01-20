package ru.eltech.project.client.frames;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import ru.eltech.project.client.Application;
import ru.eltech.project.client.DataManager;
import ru.eltech.project.client.clients.AuthClient;
import ru.eltech.project.client.components.JPanelWithBackground;
import ru.eltech.project.client.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class GreetingFrame extends JFrame {
    private JTextField loginField;
    private JPasswordField passwordField;
    private JButton authButton;
    private JButton regButton;
    private JLabel title;
    private JLabel loginTitle;
    private JLabel passwordTitle;
    private JCheckBox adminCheck;

    public GreetingFrame() throws IOException {
        super("Магазин \"Кроссовочки\"");
        setContentPane(new JPanelWithBackground("images\\hello.jpg"));
        setLayout(new GridLayoutManager(11, 4, new Insets(0, 0, 0, 0), -1, -1));
        final Spacer spacer1 = new Spacer();
        add(spacer1, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        add(spacer2, new GridConstraints(10, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        add(spacer3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        title = new JLabel();
        Font titleFont = Utils.getFont("Roboto Thin", Font.BOLD, 26, title.getFont());
        if (titleFont != null) title.setFont(titleFont);
        title.setText("Магазин \"Кроссовочки\"");
        add(title, new GridConstraints(0, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        loginField = new JTextField();
        add(loginField, new GridConstraints(3, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        passwordTitle = new JLabel();
        Font passwordTitleFont = Utils.getFont("Roboto Light", Font.BOLD, 18, passwordTitle.getFont());
        if (passwordTitleFont != null) passwordTitle.setFont(passwordTitleFont);
        passwordTitle.setText("Пароль");
        add(passwordTitle, new GridConstraints(4, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        passwordField = new JPasswordField();
        passwordField.setText("");
        add(passwordField, new GridConstraints(5, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        loginTitle = new JLabel();
        Font loginTitleFont = Utils.getFont("Roboto Light", Font.BOLD, 18, loginTitle.getFont());
        if (loginTitleFont != null) loginTitle.setFont(loginTitleFont);
        loginTitle.setText("Логин");
        add(loginTitle, new GridConstraints(2, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        authButton = new JButton();
        authButton.setText("Войти");
        add(authButton, new GridConstraints(6, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        regButton = new JButton();
        regButton.setText("Зарегистрироваться");
        add(regButton, new GridConstraints(7, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        add(spacer4, new GridConstraints(1, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        add(spacer5, new GridConstraints(9, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer6 = new Spacer();
        add(spacer6, new GridConstraints(8, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        adminCheck = new JCheckBox();
        adminCheck.setText("Сотрудник магазина");
        add(adminCheck, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

        regButton.addActionListener(actionListener -> {
            setVisible(false);
            RegistrationFrame frame = null;
            try {
                frame = new RegistrationFrame();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            frame.setSize(1200, 800);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
        authButton.addActionListener(actionListener -> {
            String password = new String(passwordField.getPassword());
            if (adminCheck.isSelected()) {
                String authResult = AuthClient.authAdmin(loginField.getText(), password);
                if (!authResult.equals("Not found")) {
                    DataManager.setAuthToken(authResult);
                    dispose();
                    MainAdminFrame frame = null;
                    try {
                        frame = new MainAdminFrame();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    frame.setSize(1200, 800);
                    frame.setLocationRelativeTo(null);
                    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    frame.setVisible(true);
                }
            } else {
                String authResult = AuthClient.authClient(loginField.getText(), password);
                if (!authResult.equals("Not found")) {
                    DataManager.setAuthToken(authResult);
                    dispose();
                    MainClientFrame frame = null;
                    try {
                        frame = new MainClientFrame();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    frame.setSize(1200, 800);
                    frame.setLocationRelativeTo(null);
                    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    frame.setVisible(true);
                }
            }

        });
    }

}

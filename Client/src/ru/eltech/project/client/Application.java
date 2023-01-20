package ru.eltech.project.client;

import ru.eltech.project.client.frames.GreetingFrame;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.io.IOException;

public class Application {

    public static void main(String[] args) throws IOException {
        new DataManager();

        GreetingFrame frame = new GreetingFrame();
        frame.setSize(1200, 800);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

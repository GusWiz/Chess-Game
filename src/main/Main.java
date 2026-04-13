package main;

import javax.swing.*;
import java.awt.*;

class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setLayout(new GridBagLayout());
        frame.setMinimumSize(new Dimension(1000, 1000));
        frame.setLocationRelativeTo(null); // centers the frame to the user's device

        // After writing/creating the board file we have to add it to the main file
        Board board = new Board();
        frame.add(board);

        frame.setVisible(true);
    }
}
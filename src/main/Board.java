package main;

import javax.swing.*;
import java.awt.*;

// extends mean inheriting the properties/values from a normal JPanel

/**
 * Board class is a JPanel that is used to draw the board.
 */
public class Board extends JPanel {

    public int tileSize = 85;

    int rows = 8;
    int cols = 8;

    // Constructor of Board class, as soon as Board() is called it creates this JPanel
    public Board() {
        // Note: when this is called it is changing the properties of the class (JPanel).

        // the reason we input cols first as the parameter is because
        // the function Dimension is width and then height
        this.setPreferredSize(new Dimension(cols * tileSize, rows * tileSize)); // Determines the size of the board
    }

    // This function purpose is to downcast the Graphics object to Graphics2D, allowing us
    // to draw on the screen.
    // Casting is when we change the type of variable. Downcasting is when we make the
    // variable to a subtype of the original type. Upcasting is when we to the opposite, a
    // default type to a subtype.
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g; // (Down)casting the Graphics object to Graphics2D
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // Alternate the color to make a checkerboard pattern.
                g2d.setColor((c + r) % 2 == 0 ? new Color(61, 239, 149) : new Color(237, 85, 148));
                g2d.fillRect(c * tileSize, r * tileSize, tileSize, tileSize);
            }
        }
    }
}

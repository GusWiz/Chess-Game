package pieces;

import main.Board;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Piece {
    public int row, col;
    public int xPos, yPos;
    public boolean isWhite; // piece color (white or not white (black))

    public String name;
    public int value; // tier of piece (pawn, knight, bishop, rook, queen, king)

    BufferedImage sheet;
    {
        try {
            sheet = ImageIO.read(ClassLoader.getSystemResourceAsStream("pieces.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected int sheetScale = sheet.getWidth() / 6;

    Image sprite;

    Board board;

    public Piece(Board board) {
        this.board = board;
    }

    public void paint(Graphics g2d) {
        g2d.drawImage(sprite, xPos, yPos, null);
    }
}

package pieces;

import main.Board;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.FileInputStream;

public abstract class Piece {
    public int row, col;
    public int xPos, yPos;
    public boolean isWhite; // piece color (white or not white (black))

    public String name;
    public int value; // tier of piece (pawn, knight, bishop, rook, queen, king)
    protected int sheetIndex;

    // static makes it run once when the program starts
    static BufferedImage sheet;
    static {
        try {
            sheet = ImageIO.read(new FileInputStream("res/pieces.png"));
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

    public boolean isValidMovement(int col, int row) {
        return true;
    }

    public boolean moveCollidesWithPiece(int col, int row) {
        return true;
    }

    public void paint(Graphics g2d) {
        g2d.drawImage(sprite, xPos, yPos, null);
    }
}

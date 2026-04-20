package main;

import pieces.Piece;
import pieces.Knight;
import pieces.Pawn;
import pieces.Rook;
import pieces.Bishop;
import pieces.King;
import pieces.Queen;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// extends mean inheriting the properties/values from a normal JPanel

/**
 * Board class is a JPanel that is used to draw the board.
 */
public class Board extends JPanel {

    public int tileSize = 80;

    int rows = 8;
    int cols = 8;

    ArrayList<Piece> pieceList = new ArrayList<>();

    public Piece selectedPiece; // Will be used for MouseListerners, to move a Piece.
    // Constructor of Board class, as soon as Board() is called it creates this JPanel

    Input input = new Input(this); // Setups the MouseListener.

    public Board() {
        // Note: when "this" is called it is changing the properties of the class (JPanel).

        // the reason we input cols first as the parameter is because
        // the function Dimension is width and then height of the JPanel (Board)
        this.setPreferredSize(new Dimension(cols * tileSize, rows * tileSize)); // Determines the size of the board

        this.addMouseListener(input);
        this.addMouseMotionListener(input);
        addPieces(); // adds pieces to the board
    }

    public void makeMove(Move move) {
        move.piece.col = move.newCol;
        move.piece.row = move.newRow;

        // Update the position of the piece to new location.
        move.piece.xPos = move.newCol * tileSize;
        move.piece.yPos = move.newRow * tileSize;

        capture(move);
    }

    public void capture(Move move) {
        pieceList.remove(move.capture);
    }

    public boolean isValidMove(Move move) {

        if (sameTeam(move.piece, move.capture)) {
            return false;
        }
        if (!move.piece.isValidMovement(move.newCol, move.newRow)) {
            return false;
        }
        if (move.piece.moveCollidesWithPiece(move.newCol, move.newRow)) {
            return false;
        }
        return true;
    }

    public boolean sameTeam(Piece p1, Piece p2) {
        if (p1 == null || p2 == null) {
            return false;
        }

        return p1.isWhite == p2.isWhite;
    }

    public Piece getPiece(int col, int row) {
        for (Piece piece : pieceList){
            if (piece.col == col && piece.row == row) {
                return piece;
            }
        }
        return null;
    }

    public void addPieces() {
        pieceList.add(new Rook(this, 0, 0, false));
        pieceList.add(new Knight(this, 1, 0, false));
        pieceList.add(new Knight(this, 6, 0, false));
        pieceList.add(new Bishop(this, 2, 0, false));
        pieceList.add(new Bishop(this, 5, 0, false));
        pieceList.add(new Queen(this, 3, 0, false));
        pieceList.add(new King(this, 4, 0, false));
        pieceList.add(new Pawn(this, 0, 1, false));
        pieceList.add(new Pawn(this, 1, 1, false));
        pieceList.add(new Pawn(this, 2, 1, false));
        pieceList.add(new Pawn(this, 3, 1, false));
        pieceList.add(new Pawn(this, 4, 1, false));
        pieceList.add(new Pawn(this, 5, 1, false));
        pieceList.add(new Pawn(this, 6, 1, false));
        pieceList.add(new Pawn(this, 7, 1, false));
        pieceList.add(new Rook(this, 7,0, false));

        pieceList.add(new Rook(this, 0, 7, true));
        pieceList.add(new Knight(this, 1, 7, true));
        pieceList.add(new Knight(this, 6, 7, true));
        pieceList.add(new Bishop(this, 2, 7, true));
        pieceList.add(new Bishop(this, 5, 7, true));
        pieceList.add(new Queen(this, 3, 7, true));
        pieceList.add(new King(this, 4, 7, true));
        pieceList.add(new Pawn(this, 0, 6, true));
        pieceList.add(new Pawn(this, 1, 6, true));
        pieceList.add(new Pawn(this, 2, 6, true));
        pieceList.add(new Pawn(this, 3, 6, true));
        pieceList.add(new Pawn(this, 4, 6, true));
        pieceList.add(new Pawn(this, 5, 6, true));
        pieceList.add(new Pawn(this, 6, 6, true));
        pieceList.add(new Pawn(this, 7, 6, true));
        pieceList.add(new Pawn(this, 7,7, true));
        pieceList.add(new Rook(this, 7,7, true));
    }

    // This function purpose is to downcast the Graphics object to Graphics2D, allowing us
    // to draw on the screen.
    // This is called when the resizing the window or moving a piece on the screen.
    // Casting is when we change the type of variable. Downcasting is when we make the
    // variable to a subtype of the original type. Upcasting is when we to the opposite, a
    // default type to a subtype.
    public void paintComponent(Graphics g) {
        // Note: g2d is like the paintbrush of the Canvas (JPanel)
        // Paint board
        Graphics2D g2d = (Graphics2D) g; // (Down)casting the Graphics object to Graphics2D
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // Alternate the color to make a checkerboard pattern.
                g2d.setColor((c + r) % 2 == 0 ? new Color(61, 239, 249) : new Color(237, 85, 148));
                g2d.fillRect(c * tileSize, r * tileSize, tileSize, tileSize);
            }
        }

        // if statement is necessary as the files will have a blank window.
        // As by default selected Piece is null
        if (selectedPiece != null) // highlight possible moves
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (isValidMove(new Move(this, selectedPiece, c, r))) {
                    g2d.setColor(new Color(240, 90,244, 120));
                    g2d.fillRect(c * tileSize, r * tileSize, tileSize, tileSize);
                }
            }
        }

        // paint pieces using svg file
        for (Piece piece : pieceList) {
            piece.paint(g2d);
        }
    }
}

package pieces;

import main.Board;

import java.awt.image.BufferedImage;

public class Rook extends Piece {
    public Rook(Board board, int col, int row, boolean isWhite) {
        super(board);
        this.col = col;
        this.row = row;
        this.isWhite = isWhite;
        this.xPos = col * board.tileSize;
        this.yPos = row * board.tileSize;

        this.name = "Rook";
        this.sheetIndex = 4;

        /* Note getSubimage(x,y,width of image,height of image), in our case
         we need to scale y to be the last element in our image.
         The getScaledInstance() method is used to scale the image to fit our board.tileSize.
        */
        this.sprite = sheet.getSubimage(sheetIndex * sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(board.tileSize, board.tileSize, BufferedImage.SCALE_SMOOTH);
    }

    public boolean isValidMovement(int col, int row) {
        return this.col == col || this.row == row;
    }

    public boolean moveCollidesWithPiece(int col, int row) {
        // check left collision
        if (this.col > col) {
            for (int c = this.col - 1; c > col; c--) {
                if (board.getPiece(c, this.row) != null) {
                    return true;
                }
            }
        }
        // check right collision
        if (this.col < col) {
            for (int c = this.col + 1; c < col; c++) {
                if (board.getPiece(c,this.row) != null) {
                    return true;
                }
            }
        }

        // check top collision
        if (this.row > row) {
            for (int r = this.row - 1; r > row; r--) {
                if (board.getPiece(this.col, r) != null) {
                    return true;
                }
            }
        }

        // check bottom collision
        if (this.row < row) {
            for (int r = this.row + 1; r < row; r++) {
                if (board.getPiece(this.col, r) != null) {
                    return true;
                }
            }
        }
        return false;
    }
}

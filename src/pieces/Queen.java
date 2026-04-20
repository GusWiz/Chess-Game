package pieces;

import main.Board;

import java.awt.image.BufferedImage;

public class Queen extends Piece{
    public Queen(Board board, int col, int row, boolean isWhite) {
        super(board);
        this.col = col;
        this.row = row;
        this.isWhite = isWhite;
        this.xPos = col * board.tileSize;
        this.yPos = row * board.tileSize;

        this.name = "Queen";
        this.sheetIndex = 0;

        this.sprite = sheet.getSubimage(sheetIndex * sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(board.tileSize, board.tileSize, BufferedImage.SCALE_SMOOTH);
    }

    // Note Queen follows Rook and Bishop logic
    public boolean isValidMovement(int col, int row) {
        return this.col == col || this.row == row || Math.abs(this.col - col) == Math.abs(this.row - row);
    }

    public boolean moveCollidesWithPiece(int col, int row) {
        if (this.col == col || this.row == row) { // follows Rook logic
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
        } else { // Follows Bishop logic
            // check top left diagonal collision
            if (this.col > col && this.row > row) {
                for (int i = 1; i < Math.abs(this.col - col); i++) {
                    if(board.getPiece(this.col - i, this.row - i) != null) {
                        return true;
                    }
                }
            }
            // check top right diagonal collision
            if (this.col < col && this.row > row) {
                for (int i = 1; i < Math.abs(this.col - col); i++) {
                    if (board.getPiece(this.col + i, this.row - i) != null) {
                        return true;
                    }
                }
            }

            // check bottom left diagonal collision
            if (this.col > col && this.row < row) {
                for (int i = 1; i < Math.abs(this.col - col); i++) {
                    if (board.getPiece(this.col - i, this.row + i) != null) {
                        return true;
                    }
                }
            }

            // check bottom right diagonal collision
            if (this.col < col && this.row < row) {
                for (int i = 1; i < Math.abs(this.col - col); i++) {
                    if (board.getPiece(this.col + i, this.row + i) != null) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

package pieces;

import main.Board;

import java.awt.image.BufferedImage;

public class Knight extends Piece{
    public Knight(Board board, int col, int row, boolean isWhite) {
        super(board);
        this.col = col;
        this.row = row;
        this.isWhite = isWhite;
        this.xPos = col * board.tileSize;
        this.yPos = row * board.tileSize;

        this.name = "Knight";
        this.sheetIndex = 3; // the png file has 6 images, so the index of the knight is 3
        this.sprite = sheet.getSubimage(this.sheetIndex * sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(board.tileSize, board.tileSize, BufferedImage.SCALE_SMOOTH);
    }
}

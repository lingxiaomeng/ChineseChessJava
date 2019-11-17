package chess;

import chessboard.ChessboardPoint;
import javafx.geometry.Point2D;


public class ChariotChessComponent extends ChessComponent {
    public ChariotChessComponent(ChessboardPoint chessboardPoint, Point2D location, ChessColor color) {
        super(chessboardPoint, location, color);
        paintChess("俥","車");

    }

    @Override
    public boolean canMoveTo(ChessComponent[][] chessboard, ChessboardPoint destination) {        if (chessboard[destination.getX()][destination.getY()].getChessColor() == getChessColor())
        return false;
        ChessboardPoint source = getChessboardPoint();
        if (source.getX() == destination.getX()) {
            int row = source.getX();
            for (int col = Math.min(source.getY(), destination.getY()) + 1;
                 col < Math.max(source.getY(), destination.getY()); col++) {
                if (!(chessboard[row][col] instanceof EmptySlotComponent)) {
                    return false;
                }
            }
        } else if (source.getY() == destination.getY()) {
            int col = source.getY();
            for (int row = Math.min(source.getX(), destination.getX()) + 1;
                 row < Math.max(source.getX(), destination.getX()); row++) {
                if (!(chessboard[row][col] instanceof EmptySlotComponent)) {
                    return false;
                }
            }
        } else { // Not on the same row or the same column.
            return false;
        }
        return true;
    }


}

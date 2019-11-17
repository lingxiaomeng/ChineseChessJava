package chess;

import chessboard.ChessboardPoint;
import javafx.geometry.Point2D;


public class SoldierChessComponent extends ChessComponent {
    public SoldierChessComponent(ChessboardPoint chessboardPoint, Point2D location, ChessColor chessColor) {
        super(chessboardPoint, location, chessColor);
        paintChess("兵", "卒");

    }

    @Override
    public boolean canMoveTo(ChessComponent[][] chessboard, ChessboardPoint destination) {
        if (chessboard[destination.getX()][destination.getY()].getChessColor() == getChessColor())
            return false;
        int sx = getChessboardPoint().getX();
        int sy = getChessboardPoint().getY();
        int dx = destination.getX();
        int dy = destination.getY();

        if (getChessColor() == ChessColor.RED) {
            if (sx >= 5) {
                return dx == sx - 1 && dy == sy;
            } else {
                return (dx == sx - 1 && dy == sy) || (dx == sx && Math.abs(dy - sy) == 1);
            }
        } else {
            if (sx <= 4) {
                return dx == sx + 1 && dy == sy;
            } else {
                return (dx == sx + 1 && dy == sy) || (dx == sx && Math.abs(dy - sy) == 1);
            }
        }
    }

}

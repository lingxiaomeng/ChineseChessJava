package chess;

import chessboard.ChessboardPoint;
import javafx.geometry.Point2D;

public class HorseChessComponent extends ChessComponent {
    public HorseChessComponent(ChessboardPoint chessboardPoint, Point2D location, ChessColor chessColor) {
        super(chessboardPoint, location, chessColor);
        paintChess("傌","馬");

    }

    @Override
    public boolean canMoveTo(ChessComponent[][] chessboard, ChessboardPoint destination) {        if (chessboard[destination.getX()][destination.getY()].getChessColor() == getChessColor())
        return false;
        int sx = getChessboardPoint().getX();
        int sy = getChessboardPoint().getY();
        int dx = destination.getX();
        int dy = destination.getY();

        if (dx == sx + 1 && dy == sy + 2 && chessboard[sx][sy + 1] instanceof EmptySlotComponent) return true;
        if (dx == sx + 1 && dy == sy - 2 && chessboard[sx][sy - 1] instanceof EmptySlotComponent) return true;
        if (dx == sx - 1 && dy == sy + 2 && chessboard[sx][sy + 1] instanceof EmptySlotComponent) return true;
        if (dx == sx - 1 && dy == sy - 2 && chessboard[sx][sy - 1] instanceof EmptySlotComponent) return true;
        if (dx == sx + 2 && dy == sy + 1 && chessboard[sx + 1][sy] instanceof EmptySlotComponent) return true;
        if (dx == sx + 2 && dy == sy - 1 && chessboard[sx + 1][sy] instanceof EmptySlotComponent) return true;
        if (dx == sx - 2 && dy == sy + 1 && chessboard[sx - 1][sy] instanceof EmptySlotComponent) return true;
        if (dx == sx - 2 && dy == sy - 1 && chessboard[sx - 1][sy] instanceof EmptySlotComponent) return true;
        return false;
    }

}

package chess;

import chessboard.ChessboardPoint;
import javafx.geometry.Point2D;


public class ElephantChessComponent extends ChessComponent {
    public ElephantChessComponent(ChessboardPoint chessboardPoint, Point2D location, ChessColor chessColor) {
        super(chessboardPoint, location, chessColor);
        paintChess("相","象");

    }

    @Override
    public boolean canMoveTo(ChessComponent[][] chessboard, ChessboardPoint destination) {        if (chessboard[destination.getX()][destination.getY()].getChessColor() == getChessColor())
        return false;
        ChessboardPoint source = getChessboardPoint();
        int dx = destination.getX();
        int dy = destination.getY();
        if (((dx >= 0 && dx <= 4) && getChessColor() == ChessColor.BLACK) || ((dx >= 5 && dx <= 9) && getChessColor() == ChessColor.RED)) {
            if (dy == source.getY() + 2 && dx == source.getX() + 2 && chessboard[source.getX() + 1][source.getY() + 1] instanceof EmptySlotComponent)
                return true;
            if (dy == source.getY() - 2 && dx == source.getX() + 2 && chessboard[source.getX() + 1][source.getY() - 1] instanceof EmptySlotComponent)
                return true;
            if (dy == source.getY() + 2 && dx == source.getX() - 2 && chessboard[source.getX() -1][source.getY() +1] instanceof EmptySlotComponent)
                return true;
            if (dy == source.getY() - 2 && dx == source.getX() - 2 &&
                    chessboard[source.getX() - 1][source.getY() - 1] instanceof EmptySlotComponent) return true;
        }
        return false;
    }


}

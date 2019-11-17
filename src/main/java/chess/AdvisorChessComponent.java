package chess;

import chessboard.ChessboardPoint;
import javafx.geometry.Point2D;


public class AdvisorChessComponent extends ChessComponent {

    public AdvisorChessComponent(ChessboardPoint chessboardPoint, Point2D location, ChessColor chessColor) {
        super(chessboardPoint, location, chessColor);
        paintChess("士", "仕");
    }

    @Override
    public boolean canMoveTo(ChessComponent[][] chessboard, ChessboardPoint destination) {
        if (chessboard[destination.getX()][destination.getY()].getChessColor() == getChessColor())
            return false;
        ChessboardPoint source = getChessboardPoint();
        int dy = destination.getX();
        int dx = destination.getY();
        if (((dx >= 3 && dx <= 5 && dy >= 0 && dy <= 2) && getChessColor() == ChessColor.BLACK) || ((dx >= 3 && dx <= 5 && dy >= 7 && dy <= 9) && getChessColor() == ChessColor.RED)) {
            if (dx == source.getY() + 1 && dy == source.getX() + 1) return true;
            if (dx == source.getY() - 1 && dy == source.getX() + 1) return true;
            if (dx == source.getY() + 1 && dy == source.getX() - 1) return true;
            if (dx == source.getY() - 1 && dy == source.getX() - 1) return true;
        }
        return false;
    }


}

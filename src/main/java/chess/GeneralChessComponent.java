package chess;

import chessboard.ChessboardPoint;
import javafx.geometry.Point2D;


public class GeneralChessComponent extends ChessComponent {

    public GeneralChessComponent(ChessboardPoint chessboardPoint, Point2D location, ChessColor chessColor) {
        super(chessboardPoint, location, chessColor);
        paintChess("帥", "将");

    }

    @Override
    public boolean canMoveTo(ChessComponent[][] chessboard, ChessboardPoint destination) {        if (chessboard[destination.getX()][destination.getY()].getChessColor() == getChessColor())
        return false;
        ChessboardPoint source = getChessboardPoint();
        int dx = destination.getX();
        int dy = destination.getY();
        boolean hasNoChees = true;
        boolean hasG = false;
        if (getChessColor() == ChessColor.BLACK)
            for (int x = dx + 1; x <= 9; x++) {
                if (chessboard[x][dy] instanceof GeneralChessComponent && chessboard[x][dy].getChessColor() == getChessColor().getInvColor()) {
                    hasG = true;
                    break;
                } else if (!(chessboard[x][dy] instanceof EmptySlotComponent)) {
                    hasNoChees = false;
                }
            }
        else for (int x = dx - 1; x >= 0; x--) {
            if (chessboard[x][dy] instanceof GeneralChessComponent && chessboard[x][dy].getChessColor() == getChessColor().getInvColor()) {
                hasG = true;
                break;
            } else if (!(chessboard[x][dy] instanceof EmptySlotComponent)) {
                hasNoChees = false;
            }
        }
        if (hasG && hasNoChees) return false;
        if ((dy >= 3 && dy <= 5) && (((dx >= 0 && dx <= 2) && getChessColor() == ChessColor.BLACK) || ((dx >= 7 && dx <= 9) && getChessColor() == ChessColor.RED))) {
            if (dy == source.getY() + 1 && dx == source.getX())
                return true;
            if (dy == source.getY() - 1 && dx == source.getX())
                return true;
            if (dy == source.getY() && dx == source.getX() + 1)
                return true;
            if (dy == source.getY() && dx == source.getX() - 1)
                return true;
        }
        return false;
    }

}

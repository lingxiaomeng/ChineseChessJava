package chess;

import chessboard.ChessboardPoint;
import javafx.geometry.Point2D;


public class CannonChessComponent extends ChessComponent {
    public CannonChessComponent(ChessboardPoint chessboardPoint, Point2D location, ChessColor chessColor) {
        super(chessboardPoint, location, chessColor);
        paintChess("炮","砲");

    }

    @Override
    public boolean canMoveTo(ChessComponent[][] chessboard, ChessboardPoint destination) {        if (chessboard[destination.getX()][destination.getY()].getChessColor() == getChessColor())
        return false;
        int sy = getChessboardPoint().getY();
        int sx = getChessboardPoint().getX();
        int dy = destination.getY();
        int dx = destination.getX();
        int t=0;
        if (sx == dx) {
            for (int y = Math.min(dy, sy) + 1; y < Math.max(sy, dy); y++) {
                if (!(chessboard[dx][y] instanceof EmptySlotComponent)) {
                    t++;
                }
            }
            if (chessboard[dx][dy].getChessColor() == getChessColor().getInvColor()) {
                return t == 1;
            } else return t == 0;
        } else if (sy == dy) {
            for (int x = Math.min(dx, sx) + 1; x < Math.max(dx, sx); x++) {
                if (!(chessboard[x][dy] instanceof EmptySlotComponent)) {
                    t++;
                }
            }
            if (chessboard[dx][dy].getChessColor() == getChessColor().getInvColor()) {
                return t == 1;
            } else return t == 0;
        }
        return false;
    }


}

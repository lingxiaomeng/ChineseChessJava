package chess;

import chessboard.ChessboardPoint;
import javafx.geometry.Point2D;


public class EmptySlotComponent extends ChessComponent {
    public EmptySlotComponent(ChessboardPoint chessboardPoint, Point2D location) {
        super(chessboardPoint, location, ChessColor.NONE);
    }



    @Override
    public boolean canMoveTo(ChessComponent[][] chessboard, ChessboardPoint destination) {
        return false;
    }

}

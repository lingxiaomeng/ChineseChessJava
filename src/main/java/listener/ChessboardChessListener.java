package listener;

import chess.ChessComponent;
import chessboard.ChessboardComponent;

public class ChessboardChessListener extends ChessListener {
    private ChessboardComponent chessboardComponent;
    private ChessComponent first;

    public ChessboardChessListener(ChessboardComponent chessboardComponent) {
        this.chessboardComponent = chessboardComponent;
    }

    @Override
    public void onClick(ChessComponent chessComponent) {
            if (first == null) {
                if (handleFirst(chessComponent)) {
                    chessComponent.setSelected(true);
                    first = chessComponent;
                    chessboardComponent.repaint(chessComponent);
                }
            } else {
                if (first == chessComponent) { // Double-click to unselect.
                    chessComponent.setSelected(false);
                    first = null;
                    chessboardComponent.repaint(null);
                } else if (handleSecond(chessComponent)) {
                    chessboardComponent.swapChessComponents(first, chessComponent);
                    chessboardComponent.swapColor();
                    first.setSelected(false);
                    first = null;
                    chessboardComponent.repaint(null);
                }
            }
    }

    private boolean handleFirst(ChessComponent chessComponent) {
        return chessComponent.getChessColor() == chessboardComponent.getCurrentColor();
    }

    private boolean handleSecond(ChessComponent chessComponent) {
        return chessComponent.getChessColor() != chessboardComponent.getCurrentColor() &&
                first.canMoveTo(chessboardComponent.getChessboard(), chessComponent.getChessboardPoint());
    }
}

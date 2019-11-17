package chess;

import chessboard.ChessboardPoint;
import javafx.event.EventHandler;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import listener.ChessListener;


import java.util.ArrayList;
import java.util.List;


public abstract class ChessComponent extends Canvas {
    public final static Dimension2D CHESS_SIZE = new Dimension2D(40, 40);
    public final static Color CHESS_COLOR = Color.rgb(254, 218, 164);
    private GraphicsContext g = getGraphicsContext2D();
    private static List<ChessListener> listenerList = new ArrayList<>();

    private ChessboardPoint chessboardPoint;
    private ChessColor chessColor;
    private boolean selected;
    private String name;
    private boolean canMoveTo;

    protected ChessComponent(ChessboardPoint chessboardPoint, Point2D location, ChessColor chessColor) {
        this.setWidth(CHESS_SIZE.getWidth());
        this.setHeight(CHESS_SIZE.getHeight());
        this.setLayoutX(location.getX());
        this.setLayoutY(location.getY());

        this.chessboardPoint = chessboardPoint;
        this.chessColor = chessColor;
        this.selected = false;

        this.addEventHandler(MouseEvent.MOUSE_CLICKED, t -> {
            for (ChessListener listener : listenerList) {
                listener.onClick(this);
            }
        });
    }


    void paintChess(String red_name, String black_name) {
        g.setFill(CHESS_COLOR);
        g.fillOval(0, 0, getWidth() - 1, getHeight() - 1);
        g.setFill(getChessColor().getColor());
        g.strokeOval(2, 2, getWidth() - 5, getHeight() - 5);
        g.setFill(getChessColor().getColor());
        if (getChessColor() == ChessColor.BLACK) {
            g.fillText(black_name, 15, 25);
            this.name = black_name;
        } else {
            g.fillText(red_name, 15, 25);
            this.name = red_name;
        }
        if (isSelected()) { // Highlights the chess if selected.
            g.setFill(Color.RED);
            g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
        }
    }

    public void repaint() {
        g.clearRect(0, 0, getWidth(), getHeight());
        if (getChessColor() != ChessColor.NONE) {
            g.setFill(CHESS_COLOR);
            g.fillOval(0, 0, getWidth() - 1, getHeight() - 1);
            g.setStroke(getChessColor().getColor());
            g.strokeOval(2, 2, getWidth() - 5, getHeight() - 5);
            g.setFill(getChessColor().getColor());
            g.fillText(name, 14, 24);
            if (isSelected()) { // Highlights the chess if selected.
                g.setStroke(Color.RED);
                g.strokeRect(0, 0, getWidth() - 2, getHeight() - 2);
            }
        }
        if (canMoveTo) {
            g.setFill(Color.RED);
            g.fillRect(getWidth() / 2 - 5, getWidth() / 2 - 5, 10, 10);
        }
    }

    public ChessboardPoint getChessboardPoint() {
        return chessboardPoint;
    }

    public void setChessboardPoint(ChessboardPoint chessboardPoint) {
        this.chessboardPoint = chessboardPoint;
    }

    public ChessColor getChessColor() {
        return chessColor;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void swapLocation(ChessComponent another) {
        ChessboardPoint chessboardPoint1 = getChessboardPoint(), chessboardPoint2 = another.getChessboardPoint();
        Point2D point1 = new Point2D(this.getLayoutX(), this.getLayoutY()), point2 = new Point2D(another.getLayoutX(), another.getLayoutY());
        setChessboardPoint(chessboardPoint2);
        this.setLayoutX(point2.getX());
        this.setLayoutY(point2.getY());
        another.setChessboardPoint(chessboardPoint1);
        another.setLayoutX(point1.getX());
        another.setLayoutY(point1.getY());
    }


    public abstract boolean canMoveTo(ChessComponent[][] chessboard, ChessboardPoint destination);

    public static boolean registerListener(ChessListener listener) {
        return listenerList.add(listener);
    }

    public static boolean unregisterListener(ChessListener listener) {
        return listenerList.remove(listener);
    }

    public Point2D getLocation() {
        return new Point2D((int) getLayoutX(), (int) getLayoutY());
    }


    public void setCanMoveTo(boolean canMoveTo) {
        this.canMoveTo = canMoveTo;
    }
}

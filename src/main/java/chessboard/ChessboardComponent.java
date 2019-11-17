package chessboard;

import chess.*;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import listener.ChessListener;
import listener.ChessboardChessListener;
//import listener.ChessboardChessListener;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ChessboardComponent extends Pane {
    private ChessListener chessListener = new ChessboardChessListener(this);
    private ChessComponent[][] chessboard = new ChessComponent[10][9];
    private ChessColor currentColor = ChessColor.RED;
    private Canvas chessboard_graph = new Canvas();

    public ChessboardComponent(int width, int height) {
//        setLayout(null); // Use absolute layout.
//        setSize(width, height);
        this.setWidth(width);
        this.setHeight(height);
        chessboard_graph.setWidth(width);
        chessboard_graph.setHeight(height);
        GraphicsContext graphicsContext = chessboard_graph.getGraphicsContext2D();
        paintComponent(graphicsContext);
        this.getChildren().add(chessboard_graph);


        ChessComponent.registerListener(chessListener);
        for (int i = 0; i < chessboard.length; i++) {
            for (int j = 0; j < chessboard[i].length; j++) {
                putChessOnBoard(new EmptySlotComponent(new ChessboardPoint(i, j), calculatePoint(i, j)));
            }
        }
        try {
            initBoard();
        } catch (IOException e) {
            System.out.println("init failed");
        }

//        this.setOnMouseClicked(mouseEvent -> System.out.println("click chessboard"));
    }

    public ChessComponent[][] getChessboard() {
        return chessboard;
    }

    public ChessColor getCurrentColor() {
        return currentColor;
    }

    public void putChessOnBoard(ChessComponent chessComponent) {
        int row = chessComponent.getChessboardPoint().getX(), col = chessComponent.getChessboardPoint().getY();
        if (chessboard[row][col] != null) {
            this.getChildren().remove(chessboard[row][col]);
        }
        chessComponent.setVisible(true);
        chessComponent.repaint();
        this.getChildren().add(chessboard[row][col] = chessComponent);
    }

    public void swapChessComponents(ChessComponent chess1, ChessComponent chess2) {
        // Note that chess1 has higher priority, 'destroys' chess2 if exists.
        if (!(chess2 instanceof EmptySlotComponent)) {
            this.getChildren().remove(chess2);
            this.getChildren().add(chess2 = new EmptySlotComponent(chess2.getChessboardPoint(), chess2.getLocation()));
        }
        chess1.swapLocation(chess2);
        int row1 = chess1.getChessboardPoint().getX(), col1 = chess1.getChessboardPoint().getY();
        chessboard[row1][col1] = chess1;
        int row2 = chess2.getChessboardPoint().getX(), col2 = chess2.getChessboardPoint().getY();
        chessboard[row2][col2] = chess2;
    }

    public void swapColor() {
        currentColor = currentColor == ChessColor.BLACK ? ChessColor.RED : ChessColor.BLACK;
    }

    //
    private void initBoard() throws IOException {
        File file = new File("Y:\\IdeaProject\\CS102A-ChineseChess\\CS102A-ChineseChess\\src\\xyz\\chengzi\\cs102a\\chinesechess\\chessboard\\initcheesbord.txt");
        Boolean boo = file.exists() && file.isFile();
        int x = 0;
        int y = 0;
        ChessColor chessColor;
        if (boo) {
            BufferedReader bufferedReader = null;
            bufferedReader = new BufferedReader(new FileReader(file));
            String linetxt;
            ChessComponent chessComponent;
            label_A:
            while ((linetxt = bufferedReader.readLine()) != null) {
                for (char chess : linetxt.toCharArray()) {
                    if (Character.isLowerCase(chess)) chessColor = ChessColor.RED;
                    else chessColor = ChessColor.BLACK;
                    chess = Character.toLowerCase(chess);
                    switch (chess) {
                        case '-':
                            continue label_A;
                        case 'c':
                            chessComponent = new ChariotChessComponent(new ChessboardPoint(x, y), calculatePoint(x, y), chessColor);
                            break;
                        case 'h':
                            chessComponent = new HorseChessComponent(new ChessboardPoint(x, y), calculatePoint(x, y), chessColor);
                            break;
                        case 'e':
                            chessComponent = new ElephantChessComponent(new ChessboardPoint(x, y), calculatePoint(x, y), chessColor);
                            break;
                        case 's':
                            chessComponent = new SoldierChessComponent(new ChessboardPoint(x, y), calculatePoint(x, y), chessColor);
                            break;
                        case 'g':
                            chessComponent = new GeneralChessComponent(new ChessboardPoint(x, y), calculatePoint(x, y), chessColor);
                            break;
                        case 'n':
                            chessComponent = new CannonChessComponent(new ChessboardPoint(x, y), calculatePoint(x, y), chessColor);
                            break;
                        case 'a':
                            chessComponent = new AdvisorChessComponent(new ChessboardPoint(x, y), calculatePoint(x, y), chessColor);
                            break;
                        default:
                            chessComponent = null;
                    }
                    y++;
                    if (chessComponent != null) {
                        chessComponent.setVisible(true);
                        putChessOnBoard(chessComponent);
                    }
                }
                y = 0;
                x++;
            }
            bufferedReader.close();
        }

    }


    protected void paintComponent(GraphicsContext g) {
//        super.paintComponent(g);

//        g.(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        paintBoardLine(g, 0, 0, 9, 0);
        paintBoardLine(g, 0, 8, 9, 8);
        paintHalfBoard(g, 0);
        paintHalfBoard(g, 5);
        paintKingSquare(g, 1, 4);
        paintKingSquare(g, 8, 4);
    }

    private void paintHalfBoard(GraphicsContext g, int startRow) {
        for (int row = startRow; row < startRow + 5; row++) {
            paintBoardLine(g, row, 0, row, 8);
        }
        for (int col = 0; col < 9; col++) {
            paintBoardLine(g, startRow, col, startRow + 4, col);
        }
    }

    private void paintKingSquare(GraphicsContext g, int centerRow, int centerCol) {
        paintBoardLine(g, centerRow - 1, centerCol - 1, centerRow + 1, centerCol + 1);
        paintBoardLine(g, centerRow - 1, centerCol + 1, centerRow + 1, centerCol - 1);
    }

    private void paintBoardLine(GraphicsContext g, int rowFrom, int colFrom, int rowTo, int colTo) {
        double offsetX = ChessComponent.CHESS_SIZE.getWidth() / 2, offsetY = ChessComponent.CHESS_SIZE.getHeight() / 2;
        javafx.geometry.Point2D p1 = calculatePoint(rowFrom, colFrom), p2 = calculatePoint(rowTo, colTo);
        g.strokeLine(p1.getX() + offsetX, p1.getY() + offsetY, p2.getX() + offsetX, p2.getY() + offsetY);
    }

    private Point2D calculatePoint(int row, int col) {
        return new Point2D((col * this.getWidth() / 9), (row * this.getHeight() / 10));
    }

    public void repaint(ChessComponent currentChess) {
        for (int i = 0; i < chessboard.length; i++) {
            for (int j = 0; j < chessboard[i].length; j++) {
                if (null != currentChess && currentChess.canMoveTo(chessboard, chessboard[i][j].getChessboardPoint()))
                    chessboard[i][j].setCanMoveTo(true);
                else chessboard[i][j].setCanMoveTo(false);
                putChessOnBoard(chessboard[i][j]);
            }
        }
    }
}

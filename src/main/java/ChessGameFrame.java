import chessboard.ChessboardComponent;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.swing.*;

public class ChessGameFrame extends Application {
    public ChessGameFrame() {

    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("2019 CS102A Project Demo");
        stage.setWidth(411);
        stage.setHeight(450);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        Scene scene = new Scene(grid, 1200, 750);
        stage.setScene(scene);
        ChessboardComponent chessboard = new ChessboardComponent(400, 400);
        grid.add(chessboard,0,0);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

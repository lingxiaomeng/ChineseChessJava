package gui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
    private Button button = new Button("开始游戏");
    private Label label1 = new Label("选择模式:");
    private Label message = new Label();

    public static void main(String args[]) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Dots and Boxes");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        HBox hBox1 = new HBox();
        HBox hBox2 = new HBox();
        hBox1.setSpacing(10);
        hBox2.setSpacing(10);
        RadioButton pvp = new RadioButton("双人对战");
        RadioButton pvc = new RadioButton("联机对战");

        hBox2.getChildren().add(label1);
        hBox2.getChildren().add(pvp);
        hBox2.getChildren().add(pvc);

        ToggleGroup group1 = new ToggleGroup();


        final ToggleGroup group = new ToggleGroup();
        pvp.setToggleGroup(group);
        pvc.setToggleGroup(group);
        group.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (pvc.isSelected()) {
            } else {
            }
        });
        grid.add(hBox1, 0, 0, 2, 1);
        grid.add(hBox2, 0, 1, 2, 1);
        grid.add(button, 0, 3, 2, 1);
        grid.add(message, 0, 4, 2, 1);
        message.setVisible(false);
        pvp.setSelected(true);

        Scene scene = new Scene(grid, 700, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
        button.setOnAction(event -> {
            try {
                if (pvp.isSelected()) {
                } else if (pvc.isSelected()) {

                }
            } catch (NumberFormatException exception) {

                Timeline error = new Timeline(new KeyFrame(Duration.seconds(0), e -> {
                    message.setTextFill(Color.RED);
                    message.setText("请输入正确的数字");
                    message.setVisible(true);
                }), new KeyFrame(Duration.seconds(1), e -> {
                    message.setVisible(false);
//                message.setText("请选择文件");
                }));
                error.setCycleCount(1);
                error.play();

            }
        });
    }
}


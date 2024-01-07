package com.example.javafx_space_invaders.nepouzite;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SpaceInvadersGame {

    private final int width;
    private final int height;

    public SpaceInvadersGame(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void init(Stage primaryStage) {
        Canvas canvas = new Canvas(width, height);
        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Space Invaders");
        primaryStage.show();

        SpaceInvadersController controller = new SpaceInvadersController(canvas);
        controller.init();
    }
}

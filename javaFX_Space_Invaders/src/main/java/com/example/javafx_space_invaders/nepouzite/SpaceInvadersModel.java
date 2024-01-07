package com.example.javafx_space_invaders.nepouzite;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class SpaceInvadersModel {

    private final GraphicsContext gc;
    private final SpaceInvadersGameLogic gameLogic;

    public SpaceInvadersModel(double width, double height) {
        Canvas canvas = new Canvas(width, height);
        this.gc = canvas.getGraphicsContext2D();
        this.gameLogic = new SpaceInvadersGameLogic(gc, width, height);

        // Load images
        ImageLoader.loadImages();
    }

    public void setMouseX(double mouseX) {
        gameLogic.setMouseX(mouseX);
    }

    public void handleMouseClick(double mouseX, double mouseY) {
        gameLogic.handleMouseClick(mouseX, mouseY);
    }
}

package com.example.javafx_space_invaders.nepouzite;

import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

public class SpaceInvadersController {

    private final Canvas canvas;
    private final SpaceInvadersModel model;

    public SpaceInvadersController(Canvas canvas) {
        this.canvas = canvas;
        this.model = new SpaceInvadersModel(canvas.getWidth(), canvas.getHeight());
    }

    public void init() {
        canvas.setCursor(Cursor.MOVE);
        canvas.setOnMouseMoved(this::handleMouseMoved);
        canvas.setOnMouseClicked(this::handleMouseClicked);
    }

    private void handleMouseMoved(MouseEvent event) {
        model.setMouseX(event.getX());
    }

    private void handleMouseClicked(MouseEvent event) {
        model.handleMouseClick(event.getX(), event.getY());
    }
}

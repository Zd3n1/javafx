package com.example.javafx_space_invaders.nepouzite;

import javafx.scene.canvas.GraphicsContext;

public class SpaceInvadersGameLogic {

    private final GraphicsContext gc;
    private final double width;
    private final double height;

    public SpaceInvadersGameLogic(GraphicsContext gc, double width, double height) {
        this.gc = gc;
        this.width = width;
        this.height = height;
    }

    public void setMouseX(double mouseX) {
        // Update logic based on mouse movement
    }

    public void handleMouseClick(double mouseX, double mouseY) {
        // Handle mouse click events
    }
}

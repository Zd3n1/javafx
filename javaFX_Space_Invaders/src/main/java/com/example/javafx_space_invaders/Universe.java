package com.example.javafx_space_invaders;

import javafx.scene.paint.Color;
import com.example.javafx_space_invaders.Universe;

import java.util.Random;

/**
 * The Universe class represents celestial bodies in the Space Invaders game.
 * It includes methods to initialize and draw these celestial bodies.
 */
public class Universe {

    private static final Random RAND = new Random();
    private final SpaceInvaders spaceInvaders;
    int posX, posY;
    private int h, w, r, g, b;
    private double opacity;

    /**
     * Constructs a Universe object with random initial values.
     *
     * @param spaceInvaders The SpaceInvaders object to associate with.
     */
    public Universe(SpaceInvaders spaceInvaders) {
        this.spaceInvaders = spaceInvaders;
        posX = SpaceInvaders.RAND.nextInt(SpaceInvaders.WIDTH);
        posY = 0;
        w = SpaceInvaders.RAND.nextInt(5) + 1;
        h = SpaceInvaders.RAND.nextInt(5) + 1;
        r = SpaceInvaders.RAND.nextInt(100) + 150;
        g = SpaceInvaders.RAND.nextInt(100) + 150;
        b = SpaceInvaders.RAND.nextInt(100) + 150;
        opacity = SpaceInvaders.RAND.nextFloat();
        if (opacity < 0) opacity *= -1;
        if (opacity > 0.5) opacity = 0.5;
    }

    /**
     * Draws the celestial body on the game canvas.
     * Adjusts opacity and moves the body downward in each draw cycle.
     * */
    public void draw() {
        if (opacity > 0.8) opacity -= 0.01;
        if (opacity < 0.1) opacity += 0.01;
        spaceInvaders.gc.setFill(Color.rgb(r, g, b, opacity));
        spaceInvaders.gc.fillOval(posX, posY, w, h);
        posY += 20;
    }
}

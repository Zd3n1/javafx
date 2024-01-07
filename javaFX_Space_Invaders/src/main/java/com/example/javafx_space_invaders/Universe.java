package com.example.javafx_space_invaders;

import javafx.scene.paint.Color;
import com.example.javafx_space_invaders.Universe;

import java.util.Random;

public class Universe {

    private static final Random RAND = new Random();
    private final SpaceInvaders spaceInvaders;
    int posX, posY;
    private int h, w, r, g, b;
    private double opacity;

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

    public void draw() {
        if (opacity > 0.8) opacity -= 0.01;
        if (opacity < 0.1) opacity += 0.01;
        spaceInvaders.gc.setFill(Color.rgb(r, g, b, opacity));
        spaceInvaders.gc.fillOval(posX, posY, w, h);
        posY += 20;
    }
}

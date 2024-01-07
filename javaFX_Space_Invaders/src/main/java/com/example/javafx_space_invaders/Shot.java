package com.example.javafx_space_invaders;

import javafx.scene.paint.Color;

public class Shot {

    private final SpaceInvaders spaceInvaders;
    public boolean toRemove;

    int posX, posY, speed = 10;
    static final int size = 6;

    public Shot(SpaceInvaders spaceInvaders, int posX, int posY) {
        this.spaceInvaders = spaceInvaders;
        this.posX = posX;
        this.posY = posY;
    }

    public void update() {
        posY -= speed;
    }

    int lvl_1 = 10;     //50
    int lvl_2 = 20;     //75
    int lvl_3 = 30;     //100
    int lvl_4 = 40;

    public void draw() {
        spaceInvaders.gc.setFill(Color.RED);
        if (spaceInvaders.score >= lvl_2 && spaceInvaders.score < lvl_3) {
            spaceInvaders.gc.setFill(Color.BLUE);
            speed = 50;
            spaceInvaders.maxShots = 15;
            spaceInvaders.gc.fillOval(posX - 5, posY - 10, size + 3, size + 7);
        } else if (spaceInvaders.score >= lvl_3 && spaceInvaders.score < lvl_4) {
            spaceInvaders.gc.setFill(Color.YELLOWGREEN);
            speed = 70;
            spaceInvaders.maxShots = 20;
            spaceInvaders.gc.fillOval(posX - 5, posY - 10, size + 3, size + 7);
        } else if (spaceInvaders.score >= lvl_4) {
            spaceInvaders.gc.setFill(Color.PAPAYAWHIP);
            speed = 100;
            spaceInvaders.maxShots = 25;
            spaceInvaders.gc.fillOval(posX - 5, posY - 10, size + 3, size + 7);
        } else {
            spaceInvaders.gc.fillOval(posX, posY, size, size);
        }
    }

    public boolean colide(SpaceInvaders.Rocket Rocket) {
        int distance = spaceInvaders.distance(this.posX + size / 2, this.posY + size / 2,
                Rocket.posX + Rocket.size / 2, Rocket.posY + Rocket.size / 2);
        return distance < Rocket.size / 2 + size / 2;
    }
}

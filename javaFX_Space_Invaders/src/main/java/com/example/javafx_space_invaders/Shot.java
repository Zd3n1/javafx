package com.example.javafx_space_invaders;

import javafx.scene.paint.Color;

/**
 * The Shot class represents a projectile in the Space Invaders game that can be shot by the player.
 * It includes methods for updating its position, drawing on the game canvas, and checking for collisions.
 */
public class Shot {

    private final SpaceInvaders spaceInvaders;
    public boolean toRemove;

    int posX, posY, speed = 10;
    static final int size = 6;

    /**
     * Creates a new Shot instance associated with the given SpaceInvaders game at the specified position.
     *
     * @param spaceInvaders The SpaceInvaders instance.
     * @param posX          The initial X-coordinate of the shot.
     * @param posY          The initial Y-coordinate of the shot.
     */
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


    /**
     * Draws the shot on the game canvas, adjusting color and speed based on the player's score.
     * The shot is represented by a filled oval shape with different colors and sizes depending on the score level.
     * The method modifies the fill color, speed, and maximum allowed shots in the game accordingly.
     * If the player's score is between {@code lvl_2} (inclusive) and {@code lvl_3} (exclusive),
     * the shot will be filled with blue color, have a speed of 50, and allow a maximum of 15 shots.
     * If the score is between {@code lvl_3} (inclusive) and {@code lvl_4} (exclusive),
     * the shot will be filled with yellow-green color, have a speed of 70, and allow a maximum of 20 shots.
     * If the score is greater than or equal to {@code lvl_4},
     * the shot will be filled with papayawhip color, have a speed of 100, and allow a maximum of 25 shots.
     * Otherwise, the default red-colored shot with the original speed and maximum shots will be drawn.
     * The position and size of the oval are determined by the shot's {@code posX}, {@code posY}, {@code size},
     * along with additional adjustments for better visual representation.
     */

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

    /**
     * Checks for collision between the shot and a Rocket (player or bomb).
     *
     * @param Rocket The Rocket to check for collision.
     * @return True if a collision is detected, otherwise false.
     */
    public boolean colide(SpaceInvaders.Rocket Rocket) {
        int distance = spaceInvaders.distance(this.posX + size / 2, this.posY + size / 2,
                Rocket.posX + Rocket.size / 2, Rocket.posY + Rocket.size / 2);
        return distance < Rocket.size / 2 + size / 2;
    }
}

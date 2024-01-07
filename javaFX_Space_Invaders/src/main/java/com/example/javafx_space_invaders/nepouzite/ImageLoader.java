package com.example.javafx_space_invaders.nepouzite;

import javafx.scene.image.Image;

public class ImageLoader {

    public static Image EXPLOSION_IMG;
    public static Image EXPLOSION_8_IMG;
    public static Image PLAYER_IMG;
    public static Image[] BOMBS_IMG;

    // Remove the static declaration here
    // static String version;

    static void loadImages() {
        // Use the local version variable without the static keyword
        String version = "comic/c";

        EXPLOSION_IMG = load("file:./images/explosion.png");
        EXPLOSION_8_IMG = load("file:./images/explosion8bit.png");
        PLAYER_IMG = load("file:./images/" + version + ".png");

        BOMBS_IMG = new Image[]{
                load("file:./images/" + version + "1.png"),
                load("file:./images/" + version + "2.png"),
                load("file:./images/" + version + "3.png"),
                load("file:./images/" + version + "4.png"),
                load("file:./images/" + version + "5.png"),
                load("file:./images/" + version + "6.png"),
                load("file:./images/" + version + "7.png"),
                load("file:./images/" + version + "8.png"),
                load("file:./images/" + version + "9.png"),
                load("file:./images/" + version + "10.png"),
        };
    }

    private static Image load(String path) {
        return new Image(path);
    }
}

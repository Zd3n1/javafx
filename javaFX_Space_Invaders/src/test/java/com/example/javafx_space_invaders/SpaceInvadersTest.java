package com.example.javafx_space_invaders;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxToolkit;

import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.*;
import java.util.concurrent.TimeoutException;

@ExtendWith(ApplicationExtension.class)
class SpaceInvadersTest {

    @Test
    void loadImages() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        SpaceInvaders spaceInvaders = new SpaceInvaders();
        spaceInvaders.loadImages();

        // Add assertions to check if images are loaded successfully
        assertNotNull(spaceInvaders.ENEMY_IMG);
        assertNotNull(spaceInvaders.PLAYER_IMG);
    }

    @Test
    void checkFilePath() {
        // Set the static variable version for the test
        SpaceInvaders.version = "comic/c";

        // Call the loadImages method to initialize ENEMY_IMG array
        SpaceInvaders.loadImages();

        // Assuming version "comic/c" will generate the file path "file:./images/comic/c1.png"
        String expectedFilePath = "file:./images/comic/c1.png";

        // Check if the first element in the ENEMY_IMG array has the expected file path
        assertEquals(expectedFilePath, SpaceInvaders.ENEMY_IMG[0].getUrl());
    }

    @Test
    void music() {
        SpaceInvaders spaceInvaders = new SpaceInvaders();

        // Test for version "8bit/b"
        spaceInvaders.setVersion("8bit/b");
        assertEquals("b.mp3", spaceInvaders.music());

        // Test for version "sw/SW"
        spaceInvaders.setVersion("sw/SW");
        assertEquals("SW.mp3", spaceInvaders.music());

        // Test for version "st/ST"
        spaceInvaders.setVersion("st/ST");
        assertEquals("ST.mp3", spaceInvaders.music());

        // Test for unknown version
        spaceInvaders.setVersion("unknownVersion");
        assertEquals("c.mp3", spaceInvaders.music());
    }
    @Test
    void distance() {
        SpaceInvaders spaceInvaders = new SpaceInvaders();

        // Test distance between two points
        int result = spaceInvaders.distance(0, 0, 3, 4);
        assertEquals(5, result);
    }
    @Test
    void distance0() {
        SpaceInvaders spaceInvaders = new SpaceInvaders();

        // Test distance between two points
        int result = spaceInvaders.distance(0, 0, 0, 0);
        assertEquals(0, result);
    }

}
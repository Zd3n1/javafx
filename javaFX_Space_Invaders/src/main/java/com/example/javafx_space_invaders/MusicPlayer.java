//package com.example.javafx_space_invaders;
//
//import javafx.application.Platform;
//import javafx.embed.swing.JFXPanel;
//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;
//
//import java.io.File;
//
//public class MusicPlayer {
//
//    public static void main(String[] args) {
//        // Initialize JavaFX
//        JFXPanel jfxPanel = new JFXPanel();
//
//        String musicFile = "file:./test.mp3";
//
//        try {
//            // Run on JavaFX Application thread
//            Platform.runLater(() -> {
//                Media sound = new Media(musicFile);
//                MediaPlayer mediaPlayer = new MediaPlayer(sound);
//
//                // Play the music immediately
//                mediaPlayer.play();
//            });
//
//            // Keep the program running until the music finishes (you may need to adjust this)
//            Thread.sleep(10000);
//        } catch (Exception e) {
//            e.printStackTrace();
//            // Handle the exception (e.g., show an error message)
//        } finally {
//            // Exit the program
//            Platform.exit();
//        }
//    }
//}

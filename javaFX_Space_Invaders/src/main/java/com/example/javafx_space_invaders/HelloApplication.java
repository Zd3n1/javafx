package com.example.javafx_space_invaders;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

import java.awt.Taskbar;
import java.awt.Toolkit;
import java.awt.Taskbar.Feature;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
//        // Image icon = new Image("file:./icon.icns");
//        Image icon = new Image("file:/Users/zdeneksilhan/javafx/javaFX_Space_Invaders/images/icon.icns");
//        stage.getIcons().add(icon);
//
        //not working

//        //Set icon on the taskbar/dock
//        if (Taskbar.isTaskbarSupported()) {
//            var taskbar = Taskbar.getTaskbar();
//
//            if (taskbar.isSupported(Feature.ICON_IMAGE)) {
//                final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
//                var dockIcon = defaultToolkit.getImage(getClass().getResource("explosion8bit.png"));
//                taskbar.setIconImage(dockIcon);
//            }
//
//        }
        //crashing whole app

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);
        stage.setTitle("Space Invaders 2.0");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}




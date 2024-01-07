package com.example.javafx_space_invaders;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class SceneLoader {

    public static void loadScene(String fxmlFile) {
        try {
            URL resourceUrl = SceneLoader.class.getResource(fxmlFile);

            if (resourceUrl == null) {
                throw new IOException("FXML file not found: " + fxmlFile);
            }

            FXMLLoader loader = new FXMLLoader(resourceUrl);
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

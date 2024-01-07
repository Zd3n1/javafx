package com.example.javafx_space_invaders;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class CreditController {

    @FXML
    private void onBackButtonClick(ActionEvent event) {
        // Load the main scene (Hello.fxml)
        SceneLoader.loadScene("Hello.fxml");
    }
}

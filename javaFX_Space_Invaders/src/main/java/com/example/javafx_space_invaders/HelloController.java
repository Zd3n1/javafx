package com.example.javafx_space_invaders;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.controlsfx.control.spreadsheet.SpreadsheetCell;

public class HelloController {

    @FXML
    protected void onHelloButtonClick() {
        SpaceInvaders spaceInvaders = new SpaceInvaders();

        try {
            spaceInvaders.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onCreditsButtonClick(ActionEvent event) {
        SceneLoader.loadScene("Credit.fxml");
    }

    public void onRetroButtonClick(ActionEvent actionEvent) {
        SpaceInvaders.setVersion("8bit/b");
    }

    public void onComicsButtonClick(ActionEvent actionEvent) {
        SpaceInvaders.setVersion("comic/c");
    }

    public void onSWButtonClick(ActionEvent actionEvent) {
        SpaceInvaders.setVersion("sw/SW");
    }

    public void onSTButtonClick(ActionEvent actionEvent) {
        SpaceInvaders.setVersion("st/ST");
    }
}

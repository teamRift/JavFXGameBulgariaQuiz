package application.controllers;

import application.classes.Values;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class BootController {

    @FXML
    Label textLabel;

    @FXML
    public void initialize() throws IOException {
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished( event -> startGame() );
        delay.play();
    }
    void startGame(){
        Stage stage = (Stage) textLabel.getScene().getWindow();
        Parent root = stage.getScene().getRoot();
        try {
            root = FXMLLoader.load(getClass().getResource("../resources/fxml/start.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.setScene(new Scene(root, Values.SCREEN_WIDTH, Values.SCREEN_HEIGHT));
        stage.show();
    }
}
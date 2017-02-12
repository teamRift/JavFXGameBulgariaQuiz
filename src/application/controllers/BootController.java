package application.controllers;

import application.classes.GameManager;
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

    public static GameManager gameManager;

    @FXML
    Label textLabel;

    @FXML
    public void initialize() throws IOException {

        gameManager = new GameManager();

        PauseTransition delay = new PauseTransition(Duration.millis(4000));

        delay.setOnFinished( event -> startGame() );

        delay.play();

    }
    private void startGame() {

        Stage stage = (Stage) textLabel.getScene().getWindow();


        Parent root = stage.getScene().getRoot();

        try {

            root = FXMLLoader.load(getClass().getResource("../resources/fxml/menu.fxml"));

        } catch (IOException e) {

            e.printStackTrace();

        }

        stage.setScene(new Scene(root, Values.SCREEN_WIDTH, Values.SCREEN_HEIGHT));

        stage.show();

    }

}

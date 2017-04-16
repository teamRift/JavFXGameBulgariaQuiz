package application.controllers;

import application.constants.ConstantsDimentions;
import application.constants.ConstantsPath;
import application.ranking.Ranking;
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

    @FXML
    private Label textLabel;

    @FXML
    public void initialize() throws IOException {
        GameManager.init();
        Ranking.init();
        PauseTransition delay = new PauseTransition(Duration.millis(4000));
        delay.setOnFinished( event -> startGame() );
        delay.play();
    }

    private void startGame() {
        Stage stage = (Stage) this.textLabel.getScene().getWindow();
        Parent root = stage.getScene().getRoot();

        try {
            root = FXMLLoader.load(getClass().getResource(ConstantsPath.PATH_TO_MENU));
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.setScene(new Scene(root, ConstantsDimentions.SCREEN_WIDTH, ConstantsDimentions.SCREEN_HEIGHT));
        stage.show();
    }
}

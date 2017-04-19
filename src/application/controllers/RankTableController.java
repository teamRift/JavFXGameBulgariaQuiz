package application.controllers;

import application.classes.GameManager;
import application.classes.Score;
import application.constants.ConstantsDimentions;
import application.constants.ConstantsPath;
import application.dependencies.DependencyInjectionContainer;
import application.enums.RankTableEnum;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.Pane;

import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.List;


public class RankTableController {
    private static final int GOLDEN_SCORE = 40;
    private static final int SILVER_SCORE = 30;
    private static final int BRONZE_SCORE = 20;
    private static final int MAX_SIZE_FOR_SHOW_PLAYERS = 13;
    private static final String PLAYER_CITY = "[Player: %s] -- [City - %s]\n";
    @FXML
    public Pane GoldenPane;
    @FXML
    public Pane SilverPane;
    @FXML
    public Pane BronzePane;
    @FXML
    public Label GoldenLabel;
    @FXML
    public Label SilverLabel;
    @FXML
    public Label BronzeLabel;
    public Button backBtn;

    private GameManager gameManager = DependencyInjectionContainer.getGameManagerInstance();


    public void initialize() throws Exception {
        loadScores();
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> System.out.println("Started."));
        delay.play();
    }

    private void loadScores() {
        gameManager.getScores();

        List<Score> mScores = gameManager.getScores();

        GoldenLabel.setText(getNamesByScore(RankTableEnum.GOLDEN, mScores));
        SilverLabel.setText(getNamesByScore(RankTableEnum.SILVER, mScores));
        BronzeLabel.setText(getNamesByScore(RankTableEnum.BRONZE, mScores));

    }

    private String getNamesByScore(RankTableEnum rankTableEnum, List<Score> mScores) {
        StringBuilder names = new StringBuilder();
        switch (rankTableEnum) {
            case GOLDEN:
                mScores.stream().filter(s -> s.getValue() == GOLDEN_SCORE).limit(MAX_SIZE_FOR_SHOW_PLAYERS)
                        .forEach(n -> names.append(String.format(PLAYER_CITY, n.getUserName(), n.getCityName())));

                break;
            case SILVER:
                mScores.stream().filter(s -> s.getValue() == SILVER_SCORE).limit(MAX_SIZE_FOR_SHOW_PLAYERS)
                        .forEach(n -> names.append(String.format(PLAYER_CITY, n.getUserName(), n.getCityName())));

                break;
            case BRONZE:
                mScores.stream().filter(s -> s.getValue() == BRONZE_SCORE).limit(MAX_SIZE_FOR_SHOW_PLAYERS)
                        .forEach(n -> names.append(String.format(PLAYER_CITY, n.getUserName(), n.getCityName())));

                break;
        }
        return names.toString();
    }


    public void back(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(ConstantsPath.PATH_TO_MENU));
        Stage stage = (Stage) this.backBtn.getScene().getWindow();
        stage.setScene(new Scene(root, ConstantsDimentions.SCREEN_WIDTH, ConstantsDimentions.SCREEN_HEIGHT));
        stage.show();
    }
}



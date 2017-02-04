package application.engine.controllers;

import application.engine.quiz.Player;
import application.engine.quiz.Question;
import application.engine.quiz.Score;
import application.engine.quiz.Rankings;
import application.engine.utils.Strings;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.*;

public class GameController {
    @FXML
    private Label questionLabel;
    @FXML
    public Button firstButton;
    @FXML
    public Button secondButton;
    @FXML
    public Button thirdButton;
    @FXML
    public Button fourthButton;
    @FXML
    public Label questionNumLabel;
    @FXML
    private Label scoreLabel;

    private static ArrayList<Score> rankings;
    private static ArrayList<Question> questions;
    private static int percent;
    private static int pauseValue = 1000;
    @FXML
    public void initialize() {

        questions = Question.loadQuestions(Strings.PATH_MORDOR_CITY);

        Question.setButtons(firstButton, secondButton, thirdButton, fourthButton);

        questions.get(Question.getQuestionIndex()).displayQuestion(questionLabel, questionNumLabel);

        firstButton.setOnAction(this::handleButtonAction);
        secondButton.setOnAction(this::handleButtonAction);
        thirdButton.setOnAction(this::handleButtonAction);
        fourthButton.setOnAction(this::handleButtonAction);
        loadRankings();
    }

    private void handleButtonAction(ActionEvent event) {

        firstButton.setDisable(true);
        secondButton.setDisable(true);
        thirdButton.setDisable(true);
        fourthButton.setDisable(true);

        questions.get(Question.getQuestionIndex()).checkCorrect((Button) event.getTarget(), questions, scoreLabel);

        Timer time = new Timer();
        time.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    questions.get(Question.getQuestionIndex()).displayQuestion(questionLabel, questionNumLabel);
                    firstButton.setDisable(false);
                    secondButton.setDisable(false);
                    thirdButton.setDisable(false);
                    fourthButton.setDisable(false);
                });
            }
        }, pauseValue);
    }

    public static void finished(int score, int questionsCorrect) {

        if (questionsCorrect == 0) {
            percent = 0;
        } else {
            percent = (int) ((double) questionsCorrect / (double) questions.size() * 100);
        }

        Alert finish = new Alert(Alert.AlertType.INFORMATION);
        finish.setTitle("You Win!");
        finish.setHeaderText("Score: " + score);
        finish.setContentText("Questions Correct: " + questionsCorrect + " out of " + questions.size() + " (" + percent + "%)");

        Score userResult = new Score("username",score);
        Rankings.save(userResult);
        finish.showAndWait();

        for (Score ranking : rankings) {
            System.out.println(ranking.prepareSave());
        }

        Platform.exit();
        System.exit(0);
    }

    private static void loadRankings() {
        try {
            rankings = Rankings.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

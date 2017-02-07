package application.controllers;

import application.Main;
import application.classes.Question;
import application.classes.Rankings;
import application.classes.Score;
import application.classes.Values;
import application.classes.cities.CityManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static application.controllers.CitiesController.cityManager;


public class QuestionsController {



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

    static ArrayList<Question> questions;
    static int percent;
    static int pauseValue = Values.PAUSE_VALUE;

    @FXML
    public void initialize() {
//        questions = Question.loadQuestions(cityName + "Questions.txt");

        CityManager manager = cityManager;
        System.out.println(manager.getCity().getClass().getSimpleName());

        questions = Question.loadQuestions(manager.getFileName());
        Question.setButtons(firstButton, secondButton, thirdButton, fourthButton);
        questions.get(Question.getQuestionIndex()).displayQuestion(questionLabel, questionNumLabel);

        firstButton.setOnAction(this::handleButtonAction);
        secondButton.setOnAction(this::handleButtonAction);
        thirdButton.setOnAction(this::handleButtonAction);
        fourthButton.setOnAction(this::handleButtonAction);
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
        Score newScore = new Score("username",score);
        Rankings.save(newScore);

        Alert finish = new Alert(Alert.AlertType.INFORMATION);
        finish.setTitle("You Win!");
        finish.setHeaderText("Score: " + score);
        finish.setContentText("Questions Correct: " + questionsCorrect + " out of " + questions.size() + " (" + percent + "%)");

        finish.showAndWait();


        Platform.exit();
        System.exit(0);
    }
}
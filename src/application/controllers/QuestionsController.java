package application.controllers;

import application.classes.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static application.controllers.BootController.gameManager;

public class QuestionsController {
    @FXML
    Button backButton;
    @FXML
    Label questionNumLabel;
    @FXML
    Label questionLabel;
    @FXML
    Label scoreLabel;
    @FXML
    Label cityName;
    @FXML
    Label userName;
    @FXML
    Label maxScore;
    @FXML
    Label maxScoreUser;
    @FXML
    Label hintLabel;
    @FXML
    ImageView background;
    @FXML
    BorderPane mainPane;
    @FXML
    GridPane topPane;
    @FXML
    GridPane leftPane;
    @FXML
    GridPane bottomPane;
    @FXML
    GridPane rightPane;
    @FXML
    GridPane questionButtons;
    @FXML
    GridPane centerPane;
    @FXML
    Button hintOne;
    @FXML
    public Button firstButton;
    @FXML
    public Button secondButton;
    @FXML
    public Button thirdButton;
    @FXML
    public Button fourthButton;

    private static ArrayList<Question> questions;

    @FXML
    public void initialize() {
        
        setBackground();
        
        setPanes();
        
        setLabels();

        Utils.setSize(hintOne,Values.TWO_COLS,Values.ONE_ROW);

        Utils.styleButton(firstButton,secondButton,thirdButton,fourthButton,backButton,hintOne);

        Utils.setSize(backButton,Values.ONE_COL, Values.ONE_ROW);

        backButton.setText(Values.LABEL_BACK_BTN);


        questionButtons.setMinWidth(Values.SIX_COLS);

        questionButtons.setMinHeight(Values.FOUR_ROWS);

        Question.reset();

        questions = Question.loadQuestions(gameManager.getFileName());
        
        Question.setButtons(firstButton, secondButton, thirdButton, fourthButton);
        
        questions.get(Question.getQuestionIndex()).displayQuestion(questionLabel, questionNumLabel);

        
        firstButton.setOnAction(this::handleButtonAction);
        
        secondButton.setOnAction(this::handleButtonAction);
        
        thirdButton.setOnAction(this::handleButtonAction);
        
        fourthButton.setOnAction(this::handleButtonAction);

    }

    public static void finished(int score, int questionsCorrect) {

        int percent = 0;

        if (questionsCorrect > 0) {

            percent = (int) ((double) questionsCorrect / (double) questions.size() * 100);

        }

        Score newScore = new Score(gameManager.getCityName(), gameManager.getCurrentUser(), score);
        System.out.println("Save score.");
        Scores.save(newScore);

        gameManager.setCurrentUserPoints(score);

    }

    private void setBackground(){

        background.setFitHeight(Values.SCREEN_HEIGHT);

        background.setFitWidth(Values.SCREEN_WIDTH);

        background.setImage(new Image(Values.IMG_BACKGROUND, Values.SCREEN_WIDTH, Values.SCREEN_HEIGHT,false,false));

    }

    private void setLabels() {

        cityName.setText(gameManager.getCityName());

        userName.setText(gameManager.getCurrentUser());

        maxScore.setText(String.valueOf("Game Max: " + gameManager.getMaxScore()));

        maxScoreUser.setText(String.valueOf("User Max: " + gameManager.getUserMaxPoints()));

        gameManager.setFactsLabel(hintLabel);

        Utils.styleLabel(25,cityName,userName,maxScore,maxScoreUser,questionNumLabel,questionLabel,scoreLabel,hintLabel);
    }

    private void setPanes() {

        Utils.setSize(mainPane, Values.SCREEN_WIDTH, Values.SCREEN_HEIGHT);

        Utils.setSize(topPane, Values.SCREEN_WIDTH, Values.TWO_ROWS);

        Utils.setSize(bottomPane, Values.SCREEN_WIDTH, Values.TWO_ROWS);

        Utils.setSize(leftPane, Values.THREE_COLS, Values.SCREEN_HEIGHT - Values.FOUR_ROWS);

        Utils.setSize(rightPane, Values.THREE_COLS, Values.SCREEN_HEIGHT - Values.FOUR_ROWS);

        Utils.setSize(centerPane, Values.SIX_COLS, Values.SCREEN_HEIGHT - Values.FOUR_ROWS);

        Utils.setSize(mainPane, Values.SCREEN_WIDTH, Values.SCREEN_HEIGHT);

        Utils.setSize(questionButtons,Values.SIX_COLS,Values.TWO_ROWS);

        Utils.setBackground(mainPane,Values.SCREEN_WIDTH, Values.SCREEN_HEIGHT);

    }

    private void handleButtonAction(ActionEvent event) {

        firstButton.setDisable(true);

        secondButton.setDisable(true);

        thirdButton.setDisable(true);

        fourthButton.setDisable(true);

        questions.get(Question.getQuestionIndex())
                .checkCorrect((Button) event.getTarget(), questions, scoreLabel);

        Timer time = new Timer();

        time.schedule(new TimerTask() {

            @Override

            public void run() {

                Platform.runLater(() -> {

                    if (questions.size()>Question.getQuestionIndex()){

                        questions.get(Question.getQuestionIndex()).displayQuestion(questionLabel, questionNumLabel);

                    }

                    firstButton.setDisable(false);

                    secondButton.setDisable(false);

                    thirdButton.setDisable(false);

                    fourthButton.setDisable(false);

                });

            }
        }, Values.PAUSE_VALUE);
    }

    public void hintButton(ActionEvent actionEvent) {

        Random rn = new Random();

        ArrayList<Button> buttons = new ArrayList<>();

        buttons.add(firstButton);

        buttons.add(secondButton);

        buttons.add(thirdButton);

        buttons.add(firstButton);

        Question question = questions.get(Question.getQuestionIndex());

        buttons = question.jokerBtn(buttons);

        for (int i = 0; i < 2; i++) {

            int index = rn.nextInt(buttons.size());

            buttons.get(index).setDisable(true);

            buttons.remove(index);

        }

        hintOne.setDisable(true);

    }

    public void OnBack(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource(Values.PATH_CITIES));

        Stage stage = (Stage)backButton.getScene().getWindow();

        stage.setScene(new Scene(root, Values.SCREEN_WIDTH,Values.SCREEN_HEIGHT));

        stage.show();
    }
}

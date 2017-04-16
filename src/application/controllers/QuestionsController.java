package application.controllers;

import application.classes.*;
import application.constants.ConstantsDifficulty;
import application.constants.ConstantsDimentions;
import application.constants.ConstantsLabel;
import application.constants.ConstantsPath;
import application.dependencies.DependencyInjectionContainer;
import application.ranking.Scores;
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

public class QuestionsController {
    @FXML
    private Button backButton;
    @FXML
    private Label questionNumLabel;
    @FXML
    private Label questionLabel;
    @FXML
    private Label scoreLabel;
    @FXML
    private Label cityName;
    @FXML
    private Label userName;
    @FXML
    private Label maxScore;
    @FXML
    private Label maxScoreUser;
    @FXML
    private Label hintLabel;
    @FXML
    private ImageView background;
    @FXML
    private BorderPane mainPane;
    @FXML
    private GridPane topPane;
    @FXML
    private GridPane leftPane;
    @FXML
    private GridPane bottomPane;
    @FXML
    private GridPane rightPane;
    @FXML
    private GridPane questionButtons;
    @FXML
    private GridPane centerPane;
    @FXML
    private Button hintOne;
    @FXML
    private Button firstButton;
    @FXML
    private Button secondButton;
    @FXML
    private Button thirdButton;
    @FXML
    private Button fourthButton;

    public static final short PAUSE_VALUE = 1000;

    private GameManager gameManager = DependencyInjectionContainer.getGameManagerInstance();
    private Question question = DependencyInjectionContainer.getQuestionInstance();

    private ArrayList<Question> questions;

    @FXML
    public void initialize() {
        setBackground();
        setPanes();
        setLabels();

        GUIHelper.styleButton(this.firstButton, this.secondButton, this.thirdButton, this.fourthButton, this.backButton, this.hintOne);

        GUIHelper.setViewDimensions(this.hintOne, ConstantsDimentions.ONE_COL, ConstantsDimentions.ONE_ROW);
        GUIHelper.setViewDimensions(this.backButton, ConstantsDimentions.ONE_COL, ConstantsDimentions.ONE_ROW / 2);
        GUIHelper.setViewDimensions(this.firstButton, ConstantsDimentions.THREE_COLS, ConstantsDimentions.ONE_ROW);
        GUIHelper.setViewDimensions(this.secondButton, ConstantsDimentions.THREE_COLS, ConstantsDimentions.ONE_ROW);
        GUIHelper.setViewDimensions(this.thirdButton, ConstantsDimentions.THREE_COLS, ConstantsDimentions.ONE_ROW);
        GUIHelper.setViewDimensions(this.fourthButton, ConstantsDimentions.THREE_COLS, ConstantsDimentions.ONE_ROW);

        this.backButton.setText(ConstantsLabel.LABEL_BACK_BTN);
        this.questionButtons.setMinWidth(ConstantsDimentions.SIX_COLS);
        this.questionButtons.setMinHeight(ConstantsDimentions.THREE_ROWS);

        this.question.reset();
        this.questions = this.question.loadQuestions(this.gameManager.getFileName(), this.gameManager.getQuestionsDifficulty());
        for (Question currentQuestion : this.questions) {
            currentQuestion.setButtons(this.firstButton, this.secondButton, this.thirdButton, this.fourthButton);
        }
        this.questions.get(this.question.getQuestionIndex()).displayQuestion(this.questionLabel, this.questionNumLabel);

        this.firstButton.setOnAction(this::handleButtonAction);
        this.secondButton.setOnAction(this::handleButtonAction);
        this.thirdButton.setOnAction(this::handleButtonAction);
        this.fourthButton.setOnAction(this::handleButtonAction);
    }


    public void finished(int score, int questionsCorrect, ArrayList<Question> questions) {
        int percent = 0;
        if (questionsCorrect > 0) {
            percent = (int) ((double) questionsCorrect / (double) questions.size() * 100);
        }

        Score newScore = new Score(this.gameManager.getCityName(), this.gameManager.getCurrentUser(), score);
        System.out.println("Save score.");
        Scores scoresInstance = DependencyInjectionContainer.getScoresInstance();
        scoresInstance.save(newScore, ConstantsDifficulty.DIFFICULT_GLOBAL);
        scoresInstance.save(newScore, this.gameManager.getQuestionsDifficulty().toLowerCase().substring(2));

        this.gameManager.setCurrentUserPoints(score);
    }

    private void setBackground() {
        this.background.setFitHeight(ConstantsDimentions.SCREEN_HEIGHT);
        this.background.setFitWidth(ConstantsDimentions.SCREEN_WIDTH);
        this.background.setImage(new Image(ConstantsPath.IMG_BACKGROUND, ConstantsDimentions.SCREEN_WIDTH, ConstantsDimentions.SCREEN_HEIGHT, false, false));
    }

    private void setLabels() {
        this.cityName.setText(this.gameManager.getCityName());
        this.userName.setText(this.gameManager.getCurrentUser());
        this.maxScore.setText(String.valueOf("Game Max: " + this.gameManager.getMaxScore()));
        this.maxScoreUser.setText(String.valueOf("User Max: " + this.gameManager.getUserMaxPoints()));
        this.gameManager.setFactsLabel(this.hintLabel);
        GUIHelper.styleLabel(ConstantsDimentions.H3, this.cityName, this.userName, this.maxScore, this.maxScoreUser, this.questionNumLabel, this.questionLabel, this.scoreLabel, this.hintLabel);
    }

    private void setPanes() {
        GUIHelper.setViewDimensions(this.mainPane, ConstantsDimentions.SCREEN_WIDTH, ConstantsDimentions.SCREEN_HEIGHT);
        GUIHelper.setViewDimensions(this.topPane, ConstantsDimentions.SCREEN_WIDTH, ConstantsDimentions.TWO_ROWS);
        GUIHelper.setViewDimensions(this.bottomPane, ConstantsDimentions.SCREEN_WIDTH, ConstantsDimentions.TWO_ROWS);
        GUIHelper.setViewDimensions(this.leftPane, ConstantsDimentions.THREE_COLS, ConstantsDimentions.SCREEN_HEIGHT - ConstantsDimentions.FOUR_ROWS);
        GUIHelper.setViewDimensions(this.rightPane, ConstantsDimentions.THREE_COLS, ConstantsDimentions.SCREEN_HEIGHT - ConstantsDimentions.FOUR_ROWS);
        GUIHelper.setViewDimensions(this.centerPane, ConstantsDimentions.SIX_COLS, ConstantsDimentions.SCREEN_HEIGHT - ConstantsDimentions.FOUR_ROWS);
        GUIHelper.setViewDimensions(this.mainPane, ConstantsDimentions.SCREEN_WIDTH, ConstantsDimentions.SCREEN_HEIGHT);
        GUIHelper.setViewDimensions(this.questionButtons, ConstantsDimentions.SIX_COLS, ConstantsDimentions.TWO_ROWS);
        GUIHelper.setBackground(this.mainPane, ConstantsDimentions.SCREEN_WIDTH, ConstantsDimentions.SCREEN_HEIGHT);
    }

    private void handleButtonAction(ActionEvent event) {

        this.firstButton.setDisable(true);
        this.secondButton.setDisable(true);
        this.thirdButton.setDisable(true);
        this.fourthButton.setDisable(true);

        this.questions.get(this.question.getQuestionIndex())
                .checkCorrect((Button) event.getTarget(), this.questions, this.scoreLabel);

        Timer time = new Timer();
        time.schedule(new TimerTask() {

            @Override

            public void run() {
                Platform.runLater(() -> {
                    if (questions.size() > DependencyInjectionContainer.getQuestionInstance().getQuestionIndex()) {
                        questions.get(DependencyInjectionContainer.getQuestionInstance().getQuestionIndex()).displayQuestion(questionLabel, questionNumLabel);
                    }
                    firstButton.setDisable(false);
                    secondButton.setDisable(false);
                    thirdButton.setDisable(false);
                    fourthButton.setDisable(false);
                });
            }
        }, PAUSE_VALUE);
    }

    public void hintButton(ActionEvent actionEvent) {
        Random rn = new Random();
        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add(this.firstButton);
        buttons.add(this.secondButton);
        buttons.add(this.thirdButton);
        buttons.add(this.fourthButton);

        Question question = this.questions.get(this.question.getQuestionIndex());
        buttons = question.jokerBtn(buttons);

        for (int i = 0; i < 2; i++) {
            int index = rn.nextInt(buttons.size());
            buttons.get(index).setDisable(true);
            buttons.remove(index);
        }
        this.hintOne.setDisable(true);
    }

    public void OnBack(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(ConstantsPath.PATH_TO_CITIES));
        Stage stage = (Stage) this.backButton.getScene().getWindow();
        stage.setScene(new Scene(root, ConstantsDimentions.SCREEN_WIDTH, ConstantsDimentions.SCREEN_HEIGHT));
        stage.show();
    }
}

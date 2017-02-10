package application.controllers;

import application.classes.Question;
import application.classes.Scores;
import application.classes.Score;
import application.classes.Values;
import application.classes.GameManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;


public class QuestionsController {
    @FXML
    Button backButton2;
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


    static ArrayList<Question> questions;
    static int percent;
    static int pauseValue = Values.PAUSE_VALUE;

    @FXML
    public void initialize() {
        setBackground();
        setPanes();
        questionButtons.setMinWidth(Values.SIX_COLS);
        questionButtons.setMinHeight(Values.TWO_ROWS);
        questions = Question.loadQuestions(CitiesController.gameManager.getFileName());
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
        Score newScore = new Score(CitiesController.gameManager.getCityName(), CitiesController.gameManager.getCurrentUser(), score);
        Scores.save(newScore);

        Alert finish = new Alert(Alert.AlertType.INFORMATION);
        finish.setTitle("You Win!");
        finish.setHeaderText("Score: " + score);
        finish.setContentText("Questions Correct: " + questionsCorrect + " out of " + questions.size() + " (" + percent + "%)");

        finish.showAndWait();

        Platform.exit();
        System.exit(0);
    }

    private void setBackground() {
        background.setFitHeight(Values.SCREEN_HEIGHT);
        background.setFitWidth(Values.SCREEN_WIDTH);
        background.setImage(new Image(Values.IMG_BACKGROUND, Values.SCREEN_WIDTH, Values.SCREEN_HEIGHT, false, false));
    }

    private void setPanes() {

        mainPane.setMinWidth(Values.SCREEN_WIDTH);
        mainPane.setMinHeight(Values.SCREEN_HEIGHT);
        mainPane.setPrefWidth(Values.SCREEN_WIDTH);
        mainPane.setPrefHeight(Values.SCREEN_HEIGHT);
        mainPane.setMaxWidth(Values.SCREEN_WIDTH);
        mainPane.setMaxHeight(Values.SCREEN_HEIGHT);

        topPane.setMinWidth(Values.SCREEN_WIDTH);
        topPane.setMinHeight(Values.TWO_ROWS);
        topPane.setPrefWidth(Values.SCREEN_WIDTH);
        topPane.setPrefHeight(Values.TWO_ROWS);
        topPane.setMaxWidth(Values.SCREEN_WIDTH);
        topPane.setMaxHeight(Values.TWO_ROWS);

        bottomPane.setMinWidth(Values.SCREEN_WIDTH);
        bottomPane.setMinHeight(Values.ONE_ROW);
        bottomPane.setPrefWidth(Values.SCREEN_WIDTH);
        bottomPane.setPrefHeight(Values.ONE_ROW);
        bottomPane.setMaxWidth(Values.SCREEN_WIDTH);
        bottomPane.setMaxHeight(Values.ONE_ROW);

        leftPane.setMinWidth(Values.THREE_COLS);
        leftPane.setMinHeight(Values.SIX_ROWS);
        leftPane.setPrefWidth(Values.THREE_COLS);
        leftPane.setPrefHeight(Values.SIX_ROWS);
        leftPane.setMaxWidth(Values.THREE_COLS);
        leftPane.setMaxHeight(Values.SIX_ROWS);

        rightPane.setMinWidth(Values.THREE_COLS);
        rightPane.setMinHeight(Values.SIX_ROWS);
        rightPane.setPrefWidth(Values.THREE_COLS);
        rightPane.setPrefHeight(Values.SIX_ROWS);
        rightPane.setMaxWidth(Values.THREE_COLS);
        rightPane.setMaxHeight(Values.SIX_ROWS);

        centerPane.setMinWidth(Values.SIX_COLS);
        centerPane.setMinHeight(Values.SIX_ROWS + Values.THREE_ROWS);
        centerPane.setPrefWidth(Values.SIX_COLS);
        centerPane.setPrefHeight(Values.SCREEN_HEIGHT - Values.THREE_ROWS);
        centerPane.setMaxWidth(Values.SIX_COLS);
        centerPane.setMaxHeight(Values.SCREEN_HEIGHT - Values.THREE_ROWS);

        questionButtons.setMinWidth(Values.SIX_COLS);
        questionButtons.setMinHeight(Values.TWO_ROWS);
        questionButtons.setPrefWidth(Values.SIX_COLS);
        questionButtons.setPrefHeight(Values.TWO_ROWS);
        questionButtons.setMaxWidth(Values.SIX_COLS);
        questionButtons.setMaxHeight(Values.TWO_ROWS);
    }

    public void initHintButton(ActionEvent actionEvent) {
        Random rn = new Random();

        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add(firstButton);
        buttons.add(secondButton);
        buttons.add(thirdButton);
        buttons.add(firstButton);

        Question a = questions.get(Question.getQuestionIndex());
        buttons = a.jokerBtn(buttons);


        for (int i = 0; i < 2; i++) {
            int index = rn.nextInt(buttons.size());
            buttons.get(index).setDisable(true);
            buttons.remove(index);
        }


        hintOne.setDisable(true);

    }

    public void OnBack(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to go back ?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/cities.fxml"));
            Stage stage = (Stage) backButton2.getScene().getWindow();
            stage.setScene(new Scene(root, Values.SCREEN_WIDTH, Values.SCREEN_HEIGHT));
            stage.show();
        }

    }
}


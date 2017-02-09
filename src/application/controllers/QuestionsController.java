package application.controllers;

import application.classes.*;
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

import static application.controllers.BootController.gameManager;

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


    private static ArrayList<Question> questions;


    @FXML
    public void initialize() {
        
        setBackground();
        
        setPanes();
        
        setLabels();
        
        
        questionButtons.setMinWidth(Values.SIX_COLS);

        questionButtons.setMinHeight(Values.TWO_ROWS);

        
        questions = Question.loadQuestions(gameManager.getFileName());
        
        Question.setButtons(firstButton, secondButton, thirdButton, fourthButton);
        
        questions.get(Question.getQuestionIndex()).displayQuestion(questionLabel, questionNumLabel);

        
        firstButton.setOnAction(this::handleButtonAction);
        
        secondButton.setOnAction(this::handleButtonAction);
        
        thirdButton.setOnAction(this::handleButtonAction);
        
        fourthButton.setOnAction(this::handleButtonAction);
        
        
        gameManager.setFactsLabel(hintLabel);
    }

    public static void finished(int score, int questionsCorrect) {
        int percent = 0;

        if (questionsCorrect > 0) {

            percent = (int) ((double) questionsCorrect / (double) questions.size() * 100);

        }


        Score newScore = new Score(gameManager.getCityName(), gameManager.getCurrentUser(),score);

        Scores.save(newScore);


        Alert finish = new Alert(Alert.AlertType.INFORMATION);

        finish.setTitle("You Win!");

        finish.setHeaderText("Score: " + score);

        finish.setContentText("Questions Correct: " + questionsCorrect + " out of " + questions.size() + " (" + percent + "%)");

        finish.showAndWait();


        Platform.exit();

        System.exit(0);
    }

    private void setBackground(){
        background.setFitHeight(Values.SCREEN_HEIGHT);
        background.setFitWidth(Values.SCREEN_WIDTH);
        background.setImage(new Image(Values.IMG_BACKGROUND, Values.SCREEN_WIDTH, Values.SCREEN_HEIGHT,false,false));
    }

    private void setLabels() {
        cityName.setText(gameManager.getCityName());
        userName.setText(gameManager.getCurrentUser());
        maxScore.setText(String.valueOf(gameManager.getMaxScore()));
        maxScoreUser.setText(String.valueOf(gameManager.getUserMaxPoints()));
    }

    private void setPanes(){
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
        }, Values.PAUSE_VALUE);
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

            Parent root = FXMLLoader.load(getClass().getResource(Values.PATH_CITIES));

            Stage stage = (Stage)backButton2.getScene().getWindow();

            stage.setScene(new Scene(root, Values.SCREEN_WIDTH,Values.SCREEN_HEIGHT));

            stage.show();
    }
}

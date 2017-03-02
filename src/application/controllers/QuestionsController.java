package application.controllers;

import application.classes.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalTime;
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

        setSize(hintOne,Values.TWO_COLS,Values.ONE_ROW);

        styleButton(firstButton,secondButton,thirdButton,fourthButton,backButton,hintOne);

        setSize(backButton,Values.ONE_COL, Values.ONE_ROW/2);
        setSize(firstButton, Values.THREE_COLS, Values.ONE_ROW);
        setSize(secondButton, Values.THREE_COLS, Values.ONE_ROW);
        setSize(thirdButton, Values.THREE_COLS, Values.ONE_ROW);
        setSize(fourthButton, Values.THREE_COLS, Values.ONE_ROW);

        backButton.setText(Values.LABEL_BACK_BTN);

        questionButtons.setMinWidth(Values.SIX_COLS);

        questionButtons.setMinHeight(Values.THREE_ROWS);

        Question.reset();

        questions = Question.loadQuestions(gameManager.getFileName(),Values.DIFFICULTY_NORMAL);
        
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

        styleLabel(Values.H3,cityName,userName,maxScore,maxScoreUser,questionNumLabel,questionLabel,scoreLabel,hintLabel);
    }

    private void setPanes() {

        setSize(mainPane, Values.SCREEN_WIDTH, Values.SCREEN_HEIGHT);

        setSize(topPane, Values.SCREEN_WIDTH, Values.TWO_ROWS);

        setSize(bottomPane, Values.SCREEN_WIDTH, Values.TWO_ROWS);

        setSize(leftPane, Values.THREE_COLS, Values.SCREEN_HEIGHT - Values.FOUR_ROWS);

        setSize(rightPane, Values.THREE_COLS, Values.SCREEN_HEIGHT - Values.FOUR_ROWS);

        setSize(centerPane, Values.SIX_COLS, Values.SCREEN_HEIGHT - Values.FOUR_ROWS);

        setSize(mainPane, Values.SCREEN_WIDTH, Values.SCREEN_HEIGHT);

        setSize(questionButtons,Values.SIX_COLS,Values.TWO_ROWS);

        setBackground(mainPane,Values.SCREEN_WIDTH, Values.SCREEN_HEIGHT);

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

    public static String capitalize(String input){
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }
    public static void setBackground(Pane pane, double WIDTH, double HEIGHT) {

        BackgroundImage myBI = new BackgroundImage(new Image(Values.IMG_BACKGROUND,WIDTH,HEIGHT,false,true),
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        pane.setBackground(new Background(myBI));

    }
    public static void styleLabel(int size, Label... labels) {
        for (Label label : labels) {
            label.setFont(Font.font(Values.DEFAULT_FONT, FontWeight.BOLD,size));

            label.setTextFill(Color.WHITE);

            setShadow(label);

        }
    }
    public static void setSize(Object object, double WIDTH, double HEIGHT) {

        if (object instanceof Pane) {

            ((Pane) object).setMinWidth(WIDTH);

            ((Pane) object).setMaxWidth(WIDTH);

            ((Pane) object).setPrefWidth(WIDTH);

            ((Pane) object).setMinHeight(HEIGHT);

            ((Pane) object).setMaxHeight(HEIGHT);

            ((Pane) object).setPrefHeight(HEIGHT);

        } else if (object instanceof Button) {

            ((Button) object).setMinWidth(WIDTH);

            ((Button) object).setMaxWidth(WIDTH);

            ((Button) object).setPrefWidth(WIDTH);

            ((Button) object).setMinHeight(HEIGHT);

            ((Button) object).setMaxHeight(HEIGHT);

            ((Button) object).setPrefHeight(HEIGHT);

            ((Button) object).setFont(Font.font(Values.DEFAULT_FONT, FontWeight.BOLD, Values.H3));

            styleButton((Button) object);

        } else if (object instanceof Label) {

            ((Label) object).setMinWidth(WIDTH);

            ((Label) object).setPrefWidth(WIDTH);

            ((Label) object).setMaxWidth(WIDTH);

            ((Label) object).setMinHeight(HEIGHT);

            ((Label) object).setPrefHeight(HEIGHT);

            ((Label) object).setMaxHeight(HEIGHT);


        } else if (object instanceof ImageView) {

            ((ImageView) object).setFitWidth(WIDTH);

            ((ImageView) object).setFitHeight(HEIGHT);

        } else if (object instanceof TextField) {

            ((TextField) object).setMinWidth(WIDTH);

            ((TextField) object).setMaxWidth(WIDTH);

            ((TextField) object).setPrefWidth(WIDTH);

            ((TextField) object).setMinHeight(HEIGHT);

            ((TextField) object).setMaxHeight(HEIGHT);

            ((TextField) object).setPrefHeight(HEIGHT);

            ((TextField) object).setFont(Font.font(Values.DEFAULT_FONT, FontWeight.BOLD, Values.H3));

        } else {

            System.out.printf("setSize(Object object) : %s is not a valid object. Pane, ImageView, Label or Button required.", capitalize(object.getClass().getSimpleName()));

        }
    }
    public static void setShadow(Object object) {

        DropShadow shadow = new DropShadow();

        shadow.setColor(Color.BLACK);

        if (object instanceof Label) {

            shadow.setRadius(5);

            shadow.setOffsetX(5);

            shadow.setOffsetY(5);

            shadow.setBlurType(BlurType.ONE_PASS_BOX);

            ((Label) object).setEffect(shadow);

        } else if (object instanceof Button) {

            shadow.setRadius(5);

            shadow.setOffsetX(5);

            shadow.setOffsetY(5);

            shadow.setBlurType(BlurType.GAUSSIAN);

            ((Button) object).setEffect(shadow);

        } else if (object instanceof HBox) {

            shadow.setRadius(-5);

            shadow.setOffsetX(5);

            shadow.setOffsetY(5);

            shadow.setBlurType(BlurType.GAUSSIAN);

            ((HBox) object).setEffect(shadow);

        }

    }
    public static void styleButton(Button... buttons) {

       for (Button button : buttons) {

         button.setBackground(new Background(new BackgroundFill(Paint.valueOf("#FFFFFF"),new CornerRadii(7), new Insets(5,5,5,5))));

          button.setFont(Font.font(Values.DEFAULT_FONT,FontWeight.BOLD, Values.H3));

           button.setTextFill(Color.BLACK);

           setShadow(button);

       }

    }
}

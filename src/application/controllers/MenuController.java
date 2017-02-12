package application.controllers;

import application.classes.GameManager;
import application.classes.Utils;
import application.classes.Values;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;

import static application.controllers.BootController.gameManager;


public class MenuController {
    @FXML
    Label gameTitle;
    @FXML
    Button exitBtn;
    @FXML
    Button startBtn;
    @FXML
    Button scoresBtn;
    @FXML
    BorderPane mainPane;
    @FXML
    GridPane topPane;
    @FXML
    GridPane leftPane;
    @FXML
    GridPane rightPane;
    @FXML
    GridPane bottomPane;
    @FXML
    GridPane centerPane;
    @FXML
    TextField inputUserName;
    @FXML
    VBox buttonsGroup;
    @FXML
    ImageView mapImage;
    @FXML
    Label menuLeftLabel;
    @FXML
    Label menuRightLabel;

    private void initButtons(){

        scoresBtn.setText(Values.LABEL_SCORES_BTN);

        Utils.setSize(scoresBtn, Values.FOUR_COLS, Values.ONE_ROW);

        scoresBtn.setOnAction((ActionEvent actionEvent) -> {

            try {

                showScores(actionEvent);

            } catch (IOException e) {

                e.printStackTrace();

            }

        });

        startBtn.setText(Values.LABEL_START_BTN);

        initStartButton();

        startBtn.setOnAction((ActionEvent actionEvent) -> {

            try {

                startGame(actionEvent);

            } catch (IOException e) {

                e.printStackTrace();

            }

            gameManager.setCurrentUser(inputUserName.getText());

        });

        exitBtn.setText(Values.LABEL_EXIT_BTN);

        Utils.setSize(exitBtn, Values.FOUR_COLS, Values.ONE_ROW);

        exitBtn.setOnAction(this::exitGame);
    }

    // set dimmensions of start button and input field for username
    private void initStartButton(){


        inputUserName.setFont(Font.font(Values.DEFAULT_FONT, FontWeight.BOLD, FontPosture.ITALIC, 25));

        inputUserName.setMaxHeight(Values.ONE_ROW);

        inputUserName.setMaxWidth(Values.TWO_COLS);

        inputUserName.setMinWidth(Values.TWO_COLS);
        inputUserName.setText("USERNAME");
        inputUserName.setBackground(Background.EMPTY);

        Utils.setSize(startBtn, Values.TWO_COLS, Values.ONE_ROW);

        startBtn.setPrefWidth(Values.TWO_COLS);

        startBtn.setMaxHeight(Values.ONE_ROW);

        startBtn.setMaxWidth(Values.TWO_COLS);

    }


    private void setPanes(){

        Utils.setSize(mainPane, Values.SCREEN_WIDTH, Values.SCREEN_HEIGHT);

        Utils.setSize(topPane, Values.SCREEN_WIDTH, Values.TWO_ROWS);

        Utils.setSize(bottomPane, Values.SCREEN_WIDTH, Values.TWO_ROWS);

        Utils.setSize(leftPane, Values.TWO_COLS, Values.SCREEN_HEIGHT - Values.FOUR_ROWS);

        Utils.setSize(rightPane, Values.TWO_COLS, Values.SCREEN_HEIGHT - Values.FOUR_ROWS);

        Utils.setSize(centerPane, Values.SCREEN_WIDTH , Values.SCREEN_HEIGHT);

        Utils.setSize(mapImage, Values.SCREEN_WIDTH- Values.THREE_COLS, Values.SCREEN_HEIGHT - Values.THREE_ROWS);

        Utils.setBackground(mainPane, Values.SCREEN_WIDTH,Values.SCREEN_HEIGHT);

    }


    public void initialize() {

        initButtons();

        setPanes();

        initLabels();
        Utils.styleButton(exitBtn,startBtn,scoresBtn);
    }


    private void initLabels() {

        initGameLabel();

        initRiftLabel();

        initSoftUniLabel();

    }


    private void exitGame(ActionEvent event) {

        Stage stage = (Stage)exitBtn.getScene().getWindow();

        stage.close();

    }


    private void showScores(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/rankings.fxml"));

        Stage stage = (Stage)scoresBtn.getScene().getWindow();

        stage.setScene(new Scene(root, Values.SCREEN_WIDTH,Values.SCREEN_HEIGHT));

        stage.show();

    }

    private void startGame(ActionEvent actionEvent) throws IOException {

        gameManager = new GameManager();

        Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/cities.fxml"));

        Stage stage = (Stage)startBtn.getScene().getWindow();

        stage.setScene(new Scene(root, Values.SCREEN_WIDTH,Values.SCREEN_HEIGHT));

        stage.show();

    }

    private void initGameLabel() {

        gameTitle.setText(Values.LABEL_GAME_TITLE);

        gameTitle.setTextFill(Color.DARKOLIVEGREEN);

        gameTitle.setFont(Font.font(Values.DEFAULT_FONT, FontWeight.BOLD,70));

        DropShadow shadow = new DropShadow();

        shadow.setRadius(10);

        shadow.setOffsetX(0);

        shadow.setOffsetY(10);

        shadow.setBlurType(BlurType.GAUSSIAN);

        shadow.setColor(Color.BLACK);

        gameTitle.setEffect(shadow);

    }

    private void initRiftLabel() {

        menuLeftLabel.setText(Values.LABEL_TEAM_RIFT);

        menuLeftLabel.setTextFill(Color.WHITESMOKE);

        menuLeftLabel.setFont(Font.font(Values.DEFAULT_FONT, FontWeight.BOLD,32));

        Utils.setShadow(menuLeftLabel);

    }

    private void initSoftUniLabel() {

        menuRightLabel.setText(Values.LABEL_SOFTUNI);

        menuRightLabel.setTextFill(Color.WHITE);

        menuRightLabel.setFont(Font.font(Values.DEFAULT_FONT, FontWeight.BOLD,32));

        Utils.setShadow(menuRightLabel);
    }
}

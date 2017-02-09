package application.controllers;

import application.classes.Utils;
import application.classes.Values;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

import static application.controllers.CitiesController.gameManager;

public class MenuController {

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
        Font font = new Font(23);
        inputUserName.setFont(font);
        inputUserName.setMaxHeight(Values.ONE_ROW/1.35);
        inputUserName.setMaxWidth(Values.TWO_COLS);
        inputUserName.setMinWidth(Values.TWO_COLS);
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
        Utils.setSize(mapImage, Values.FOUR_COLS*2, Values.FOUR_ROWS-2);
        Utils.setSize(centerPane, Values.SCREEN_WIDTH  - Values.FOUR_COLS, Values.SCREEN_HEIGHT- Values.FOUR_COLS);
        Utils.setBackground(mainPane, Values.SCREEN_WIDTH,Values.SCREEN_HEIGHT);
    }
    public void initialize(){
        initButtons();
    }
    @FXML
    public void exitGame(ActionEvent event) {
        Stage stage = (Stage)exitBtn.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void showScores(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/rankings.fxml"));
        Stage stage = (Stage)scoresBtn.getScene().getWindow();
        stage.setScene(new Scene(root, Values.SCREEN_WIDTH,Values.SCREEN_HEIGHT));
        stage.show();
    }
    @FXML
    public void startGame(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/cities.fxml"));
        Stage stage = (Stage)startBtn.getScene().getWindow();
        stage.setScene(new Scene(root, Values.SCREEN_WIDTH,Values.SCREEN_HEIGHT));
        stage.show();
    }

}

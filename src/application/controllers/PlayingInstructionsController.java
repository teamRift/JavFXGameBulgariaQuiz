package application.controllers;

import application.classes.GUIHelper;
import application.classes.Values;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class PlayingInstructionsController {

    @FXML
    Button backBtn;
    @FXML
    public Label gameTitle, leftLabel, rightLabel;
    @FXML
    private BorderPane mainPane;
    @FXML
    private GridPane howToPlayBox , topPane, bottomPane, leftPane, rightPane;
    @FXML
    private Label rule1, rule2, rule3;

    public void initialize() {
        setLabels();
        setPanes();
        setButtons();
    }

    private void setButtons() {
        GUIHelper.styleButton(backBtn);
        GUIHelper.setViewDimensions(backBtn, Values.ONE_COL, Values.ONE_ROW);
        backBtn.setText(Values.LABEL_BACK_BTN);
        backBtn.setOnAction((actionEvent) -> {
            try {
                onBack();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void setPanes(){
        GUIHelper.setBackground(mainPane, Values.SCREEN_WIDTH, Values.SCREEN_HEIGHT);
        GUIHelper.setViewDimensions(mainPane, Values.SCREEN_WIDTH, Values.SCREEN_HEIGHT);
        //GUIHelper.setViewDimensions(instructionsBox, Values.SCREEN_WIDTH - Values.ONE_COL, Values.SCREEN_HEIGHT - Values.ONE_COL);
        GUIHelper.setViewDimensions(howToPlayBox, Values.SCREEN_WIDTH - Values.ONE_COL, Values.SCREEN_HEIGHT - Values.FOUR_ROWS);
        GUIHelper.setViewDimensions(topPane, Values.SCREEN_WIDTH, Values.TWO_ROWS);
        GUIHelper.setViewDimensions(bottomPane, Values.SCREEN_WIDTH, Values.TWO_ROWS);
        GUIHelper.setViewDimensions(leftPane, Values.TWO_COLS, Values.SCREEN_HEIGHT - Values.FOUR_ROWS);
        GUIHelper.setViewDimensions(rightPane, Values.TWO_COLS, Values.SCREEN_HEIGHT - Values.FOUR_ROWS);
    }

    private void setLabels() {
        GUIHelper.styleLabel(50,gameTitle);
        gameTitle.setText(Values.LABEL_GAME_TITLE);
        GUIHelper.styleLabel(32, leftLabel, rightLabel);
        leftLabel.setText(Values.LABEL_TEAM_RIFT);
        rightLabel.setText(Values.LABEL_SOFTUNI);
        rule1.setText("1. Select dificulty");
        rule2.setText("2. Select city");
        rule3.setText("3. Enjoy the game! :)");
        GUIHelper.styleLabel(37,rule1, rule2, rule3);



    }

    private void onBack() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/menu.fxml"));
        Stage stage = ( Stage ) backBtn.getScene().getWindow();
        stage.setScene( new Scene( root, Values.SCREEN_WIDTH, Values.SCREEN_HEIGHT));
        stage.show();
    }
}


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
    private GridPane instructionsBox, howToPlayBox , topPane, bottomPane, leftPane, rightPane;
    @FXML
    // private Label creditsL1, creditsL2, creditsL3, creditsL4, creditsR1, creditsR2, creditsR3, creditsR4;

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
        GUIHelper.setViewDimensions(instructionsBox, Values.SCREEN_WIDTH - Values.ONE_COL, Values.SCREEN_HEIGHT - Values.ONE_COL);
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

    }

    private void onBack() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/menu.fxml"));
        Stage stage = ( Stage ) backBtn.getScene().getWindow();
        stage.setScene( new Scene( root, Values.SCREEN_WIDTH, Values.SCREEN_HEIGHT));
        stage.show();
    }
}


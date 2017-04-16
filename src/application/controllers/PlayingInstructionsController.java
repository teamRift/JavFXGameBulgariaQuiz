package application.controllers;

import application.classes.GUIHelper;
import application.constants.ConstantsDimentions;
import application.constants.ConstantsLabel;
import application.constants.ConstantsPath;
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
    private Button backBtn;
    @FXML
    private Label gameTitle, leftLabel, rightLabel;
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
        GUIHelper.styleButton(this.backBtn);
        GUIHelper.setViewDimensions(this.backBtn, ConstantsDimentions.ONE_COL, ConstantsDimentions.ONE_ROW);
        this.backBtn.setText(ConstantsLabel.LABEL_BACK_BTN);
        this.backBtn.setOnAction((actionEvent) -> {
            try {
                onBack();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void setPanes(){
        GUIHelper.setBackground(this.mainPane, ConstantsDimentions.SCREEN_WIDTH, ConstantsDimentions.SCREEN_HEIGHT);
        GUIHelper.setViewDimensions(this.mainPane, ConstantsDimentions.SCREEN_WIDTH, ConstantsDimentions.SCREEN_HEIGHT);
        //GUIHelper.setViewDimensions(instructionsBox, Values.SCREEN_WIDTH - Values.ONE_COL, Values.SCREEN_HEIGHT - Values.ONE_COL);
        GUIHelper.setViewDimensions(this.howToPlayBox, ConstantsDimentions.SCREEN_WIDTH - ConstantsDimentions.ONE_COL, ConstantsDimentions.SCREEN_HEIGHT - ConstantsDimentions.FOUR_ROWS);
        GUIHelper.setViewDimensions(this.topPane, ConstantsDimentions.SCREEN_WIDTH, ConstantsDimentions.TWO_ROWS);
        GUIHelper.setViewDimensions(this.bottomPane, ConstantsDimentions.SCREEN_WIDTH, ConstantsDimentions.TWO_ROWS);
        GUIHelper.setViewDimensions(this.leftPane, ConstantsDimentions.TWO_COLS, ConstantsDimentions.SCREEN_HEIGHT - ConstantsDimentions.FOUR_ROWS);
        GUIHelper.setViewDimensions(this.rightPane, ConstantsDimentions.TWO_COLS, ConstantsDimentions.SCREEN_HEIGHT - ConstantsDimentions.FOUR_ROWS);
    }

    private void setLabels() {
        GUIHelper.styleLabel(50, this.gameTitle);
        this.gameTitle.setText(ConstantsLabel.LABEL_GAME_TITLE);
        GUIHelper.styleLabel(32, this.leftLabel, this.rightLabel);
        this.leftLabel.setText(ConstantsLabel.LABEL_TEAM_RIFT);
        this.rightLabel.setText(ConstantsLabel.LABEL_SOFTUNI);
        this.rule1.setText("1. Select dificulty");
        this.rule2.setText("2. Select city");
        this.rule3.setText("3. Enjoy the game! :)");
        GUIHelper.styleLabel(37, this.rule1, this.rule2, this.rule3);



    }

    private void onBack() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(ConstantsPath.PATH_TO_MENU));
        Stage stage = ( Stage ) this.backBtn.getScene().getWindow();
        stage.setScene( new Scene( root, ConstantsDimentions.SCREEN_WIDTH, ConstantsDimentions.SCREEN_HEIGHT));
        stage.show();
    }
}


package application.controllers;

import application.classes.GUIHelper;
import application.constants.ConstantsDimensions;
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
import javafx.scene.paint.Color;
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
        GUIHelper.setViewDimensions(this.backBtn, ConstantsDimensions.ONE_COL, ConstantsDimensions.ONE_ROW/2);
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
        GUIHelper.setBackground(this.mainPane, ConstantsDimensions.SCREEN_WIDTH, ConstantsDimensions.SCREEN_HEIGHT);
        GUIHelper.setViewDimensions(this.mainPane, ConstantsDimensions.SCREEN_WIDTH, ConstantsDimensions.SCREEN_HEIGHT);
        GUIHelper.setViewDimensions(this.howToPlayBox, ConstantsDimensions.SCREEN_WIDTH - ConstantsDimensions.ONE_COL, ConstantsDimensions.SCREEN_HEIGHT - ConstantsDimensions.FOUR_ROWS);
        GUIHelper.setViewDimensions(this.topPane, ConstantsDimensions.SCREEN_WIDTH, ConstantsDimensions.TWO_ROWS);
        GUIHelper.setViewDimensions(this.bottomPane, ConstantsDimensions.SCREEN_WIDTH, ConstantsDimensions.TWO_ROWS);
        GUIHelper.setViewDimensions(this.leftPane, ConstantsDimensions.TWO_COLS, ConstantsDimensions.SCREEN_HEIGHT - ConstantsDimensions.FOUR_ROWS);
        GUIHelper.setViewDimensions(this.rightPane, ConstantsDimensions.TWO_COLS, ConstantsDimensions.SCREEN_HEIGHT - ConstantsDimensions.FOUR_ROWS);
    }

    private void setLabels() {
        GUIHelper.styleLabel(ConstantsDimensions.H3*3, this.gameTitle);
        this.gameTitle.setText(ConstantsLabel.LABEL_HOW_TO_PLAY);
        this.gameTitle.setTextFill(Color.SEAGREEN);
        GUIHelper.setShadow(this.gameTitle);
        GUIHelper.styleLabel(ConstantsDimensions.H2, this.leftLabel);
        this.leftLabel.setText(ConstantsLabel.LABEL_TEAM_RIFT);
        GUIHelper.styleLabel(ConstantsDimensions.H3*2, this.rightLabel);
        this.rightLabel.setText(ConstantsLabel.LABEL_SOFTUNI);

        this.rule1.setText("1. Select difficulty");
        this.rule2.setText("2. Select city");
        this.rule3.setText("3. Enjoy the game! :)");
        GUIHelper.styleLabel(ConstantsDimensions.H3*3, this.rule1, this.rule2, this.rule3);



    }

    private void onBack() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(ConstantsPath.PATH_TO_MENU));
        Stage stage = ( Stage ) this.backBtn.getScene().getWindow();
        stage.setScene( new Scene( root, ConstantsDimensions.SCREEN_WIDTH, ConstantsDimensions.SCREEN_HEIGHT));
        stage.show();
    }
}


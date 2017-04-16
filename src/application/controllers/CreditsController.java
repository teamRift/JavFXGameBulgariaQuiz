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
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class CreditsController {

    @FXML
    private Button backBtn;
    @FXML
    private Label gameTitle, leftLabel, rightLabel;
    @FXML
    private BorderPane mainPane;
    @FXML
    private GridPane teamRiftBox, topPane, bottomPane, leftPane, rightPane;
    @FXML
    private Label creditsL1, creditsL2, creditsL3, creditsL4, creditsR1, creditsR2, creditsR3, creditsR4;

    public void initialize(){
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
        GUIHelper.setViewDimensions(this.teamRiftBox, ConstantsDimentions.SCREEN_WIDTH - ConstantsDimentions.FOUR_COLS, ConstantsDimentions.SCREEN_HEIGHT - ConstantsDimentions.FOUR_ROWS);
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
        this.creditsL1.setText("@Alish");
        this.creditsR1.setText("@asyadi");
        this.creditsL2.setText("@AngelD");
        this.creditsR2.setText("@Cvetan1");
        this.creditsL3.setText("@daniel.a.mihaylоv");
        this.creditsR3.setText("@Daniela.Raycheva");
        this.creditsL4.setText("@koushalieva");
        this.creditsR4.setText("@mariyanhadzhiev");
        GUIHelper.styleLabel(25, this.creditsL1, this.creditsL2, this.creditsL3, this.creditsL4, this.creditsR1, this.creditsR2, this.creditsR3, this.creditsR4);
    }

    private void onBack() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(ConstantsPath.PATH_TO_MENU));
        Stage stage = ( Stage ) this.backBtn.getScene().getWindow();
        stage.setScene( new Scene( root, ConstantsDimentions.SCREEN_WIDTH, ConstantsDimentions.SCREEN_HEIGHT));
        stage.show();
    }
}


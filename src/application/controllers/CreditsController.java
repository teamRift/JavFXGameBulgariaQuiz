package application.controllers;

import application.classes.GUIHelper;
import application.classes.Values;
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
    Button backBtn;
    @FXML
    public Label gameTitle, leftLabel, rightLabel;
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
        GUIHelper.setViewDimensions(teamRiftBox, Values.SCREEN_WIDTH - Values.FOUR_COLS, Values.SCREEN_HEIGHT - Values.FOUR_ROWS);
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
        creditsL1.setText("@Alish");
        creditsR1.setText("@asyadi");
        creditsL2.setText("@AngelD");
        creditsR2.setText("@Cvetan1");
        creditsL3.setText("@daniel.a.mihaylоv");
        creditsR3.setText("@Daniela.Raycheva");
        creditsL4.setText("@koushalieva");
        creditsR4.setText("@mariyanhadzhiev");
        GUIHelper.styleLabel(25,creditsL1,creditsL2,creditsL3,creditsL4,creditsR1,creditsR2,creditsR3,creditsR4);
    }

    private void onBack() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/menu.fxml"));
        Stage stage = ( Stage ) backBtn.getScene().getWindow();
        stage.setScene( new Scene( root, Values.SCREEN_WIDTH, Values.SCREEN_HEIGHT));
        stage.show();
    }
}


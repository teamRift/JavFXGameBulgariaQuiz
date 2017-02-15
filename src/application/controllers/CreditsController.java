package application.controllers;

import application.classes.Utils;
import application.classes.Values;
import javafx.event.ActionEvent;
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

public class CreditsController {

    @FXML
    Button backBtn;
    @FXML
    public Label gameTitle;
    @FXML
    public Label leftLabel;
    @FXML
    public Label rightLabel;
    @FXML
    private BorderPane mainPane;
    @FXML
    private GridPane teamRiftBox;
    @FXML
    private GridPane topPane;
    @FXML
    private GridPane leftPane;
    @FXML
    private GridPane bottomPane;
    @FXML
    private GridPane rightPane;
    @FXML
    private Label creditsL1;
    @FXML
    private Label creditsL2;
    @FXML
    private Label creditsL3;
    @FXML
    private Label creditsL4;
    @FXML
    private Label creditsR1;
    @FXML
    private Label creditsR2;
    @FXML
    private Label creditsR3;
    @FXML
    private Label creditsR4;


    public void initialize(){

        setLabels();

        setPanes();

        setButtons();

    }

    private void setButtons() {

        Utils.styleButton(backBtn);

        Utils.setSize(backBtn, Values.ONE_COL, Values.ONE_ROW);
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

        Utils.setBackground(mainPane, Values.SCREEN_WIDTH, Values.SCREEN_HEIGHT);

        Utils.setSize(mainPane, Values.SCREEN_WIDTH, Values.SCREEN_HEIGHT);

        Utils.setSize(teamRiftBox, Values.SCREEN_WIDTH - Values.FOUR_COLS, Values.SCREEN_HEIGHT - Values.FOUR_ROWS);

        Utils.setSize(topPane, Values.SCREEN_WIDTH, Values.TWO_ROWS);

        Utils.setSize(bottomPane, Values.SCREEN_WIDTH, Values.TWO_ROWS);

        Utils.setSize(leftPane, Values.TWO_COLS, Values.SCREEN_HEIGHT - Values.FOUR_ROWS);

        Utils.setSize(rightPane, Values.TWO_COLS, Values.SCREEN_HEIGHT - Values.FOUR_ROWS);

    }

    private void setLabels() {

        Utils.styleLabel(50,gameTitle);

        gameTitle.setText(Values.LABEL_GAME_TITLE);


        Utils.styleLabel(32, leftLabel, rightLabel);

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

        Utils.styleLabel(25,creditsL1,creditsL2,creditsL3,creditsL4,creditsR1,creditsR2,creditsR3,creditsR4);

    }

    public void onBack() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/menu.fxml"));

        Stage stage = ( Stage ) backBtn.getScene().getWindow();

        stage.setScene( new Scene( root, Values.SCREEN_WIDTH, Values.SCREEN_HEIGHT));

        stage.show();
    }
}

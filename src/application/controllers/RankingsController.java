package application.controllers;

import application.classes.GUIHelper;
import application.classes.GameManager;
import application.classes.Score;
import application.classes.Values;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class RankingsController {
    @FXML
    public Label rankingsTitle;
    @FXML
    public Label rankingsCurrent;
    @FXML
    public Label rankingsFirst;
    @FXML
    public Label rankingsSecond;
    @FXML
    public Label rankingsThird;
    @FXML
    public Label rankingsFourth;
    @FXML
    public Label rankingsFifth;
    @FXML
    public Label hintLabel;
    @FXML
    public StackPane mainPane;
    @FXML
    public ImageView background;
    @FXML
    Button backBtn;

    public void initialize() throws Exception {

        initCurrent();

        loadScores();

        GUIHelper.setBackground(mainPane, Values.SCREEN_WIDTH, Values.SCREEN_HEIGHT);

        GUIHelper.setViewDimensions(background, Values.SCREEN_WIDTH- Values.THREE_COLS, Values.SCREEN_HEIGHT- Values.THREE_ROWS);

        GUIHelper.styleLabel(35,rankingsCurrent,rankingsFirst,rankingsSecond,rankingsThird,rankingsFourth,rankingsFifth);

        GUIHelper.styleLabel(50,rankingsTitle);

        GUIHelper.styleLabel(25,hintLabel);

        initBackButton();
    }

    public void OnBack(ActionEvent actionEvent) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../resources/fxml/menu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage)backBtn.getScene().getWindow();
        stage.setScene(new Scene(root, Values.SCREEN_WIDTH,Values.SCREEN_HEIGHT));
        stage.show();
    }

    private void loadScores() {
        List<Score> mScores = GameManager.getScores();
        rankingsTitle.setText(Values.LABEL_RANKINGS_TOP_5);

        for (int i = 5 - mScores.size(); i > 0; i--) {
            mScores.set(mScores.size(), new Score("---","---",0));
        }

        rankingsFirst.setText(getRanking(mScores.get(0).prepareSave()));
        rankingsSecond.setText(getRanking(mScores.get(1).prepareSave()));
        rankingsThird.setText(getRanking(mScores.get(2).prepareSave()));
        rankingsFourth.setText(getRanking(mScores.get(3).prepareSave()));
        rankingsFifth.setText(getRanking(mScores.get(4).prepareSave()));

        GameManager.setFactsLabel(hintLabel);
    }

    private void initBackButton(){
        backBtn.setOnAction(this::OnBack);
        backBtn.setText(Values.LABEL_BACK_BTN);
        GUIHelper.setViewDimensions(backBtn, Values.ONE_COL, Values.ONE_ROW);
        GUIHelper.styleButton(backBtn);
    }
    private void initCurrent(){
        if (GameManager.playerHasPlayed()){
            rankingsCurrent.setText(String.format("%s %s %s",
                    GameManager.getCityName(),
                    GameManager.getCurrentUser(),
                    GameManager.getCurrentUserPoints()));
            GameManager.setHasPlayed(false);
        } else {
            rankingsCurrent.setVisible(false);
        }
    }
    public static String getRanking(String input) {
        return String.format("%s %s %s",
                input.split(";")[0],
                input.split(";")[1],
                input.split(";")[2]
        );
    }

}

package application.controllers;

import application.classes.GUIHelper;
import application.classes.GameManager;
import application.classes.Score;
import application.classes.Values;
import application.constants.ConstantsDimentions;
import application.constants.ConstantsLabel;
import application.constants.ConstantsPath;
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
    private Label rankingsTitle;
    @FXML
    private Label rankingsCurrent;
    @FXML
    private Label rankingsFirst;
    @FXML
    private Label rankingsSecond;
    @FXML
    private Label rankingsThird;
    @FXML
    private Label rankingsFourth;
    @FXML
    private Label rankingsFifth;
    @FXML
    private Label hintLabel;
    @FXML
    private StackPane mainPane;
    @FXML
    private ImageView background;
    @FXML
    private Button backBtn;

    public void initialize() throws Exception {
        initCurrent();
        loadScores();
        GUIHelper.setBackground(this.mainPane, ConstantsDimentions.SCREEN_WIDTH, ConstantsDimentions.SCREEN_HEIGHT);
        GUIHelper.setViewDimensions(this.background, ConstantsDimentions.SCREEN_WIDTH- ConstantsDimentions.THREE_COLS, ConstantsDimentions.SCREEN_HEIGHT- ConstantsDimentions.THREE_ROWS);
        GUIHelper.styleLabel(35,this.rankingsCurrent, this.rankingsFirst, this.rankingsSecond, this.rankingsThird, this.rankingsFourth, this.rankingsFifth);
        GUIHelper.styleLabel(50, this.rankingsTitle);
        GUIHelper.styleLabel(25, this.hintLabel);
        initBackButton();
    }

    private void OnBack(ActionEvent actionEvent) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(ConstantsPath.PATH_TO_MENU));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage)this.backBtn.getScene().getWindow();
        stage.setScene(new Scene(root, ConstantsDimentions.SCREEN_WIDTH, ConstantsDimentions.SCREEN_HEIGHT));
        stage.show();
    }

    private void loadScores() {
        List<Score> mScores = GameManager.getScores();
        this.rankingsTitle.setText(ConstantsLabel.LABEL_RANKINGS_TOP_5);

        for (int i = 5 - mScores.size(); i > 0; i--) {
            mScores.set(mScores.size(), new Score("---","---",0));
        }

        this.rankingsFirst.setText(getRanking(mScores.get(0).prepareSave()));
        this.rankingsSecond.setText(getRanking(mScores.get(1).prepareSave()));
        this.rankingsThird.setText(getRanking(mScores.get(2).prepareSave()));
        this.rankingsFourth.setText(getRanking(mScores.get(3).prepareSave()));
        this.rankingsFifth.setText(getRanking(mScores.get(4).prepareSave()));
        GameManager.setFactsLabel(this.hintLabel);
    }

    private void initBackButton(){
        backBtn.setOnAction(this::OnBack);
        backBtn.setText(ConstantsLabel.LABEL_BACK_BTN);
        GUIHelper.setViewDimensions(this.backBtn, ConstantsDimentions.ONE_COL, ConstantsDimentions.ONE_ROW);
        GUIHelper.styleButton(this.backBtn);
    }

    private void initCurrent(){
        if (GameManager.playerHasPlayed()){
            this.rankingsCurrent.setText(String.format("%s %s %s",
                    GameManager.getCityName(),
                    GameManager.getCurrentUser(),
                    GameManager.getCurrentUserPoints()));
            GameManager.setHasPlayed(false);
        } else {
            this.rankingsCurrent.setVisible(false);
        }
    }

    private static String getRanking(String input) {
        return String.format("%s %s %s",
                input.split(";")[0],
                input.split(";")[1],
                input.split(";")[2]
        );
    }
}

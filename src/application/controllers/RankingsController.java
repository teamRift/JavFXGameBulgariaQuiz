package application.controllers;

import application.classes.GUIHelper;
import application.classes.GameManager;
import application.classes.Score;
import application.constants.ConstantsDimensions;
import application.constants.ConstantsLabel;
import application.constants.ConstantsPath;
import application.dependencies.DependencyInjectionContainer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class RankingsController {
    private static final int FIRST_SCORE_VALUE = 0;
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

    private GameManager gameManager = DependencyInjectionContainer.getGameManagerInstance();

    public void initialize() throws Exception {
        initCurrent();
        loadScores();
        setPane();
        setLabels();
        initBackButton();
    }

    private void OnBack(ActionEvent actionEvent) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(ConstantsPath.PATH_TO_MENU));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) this.backBtn.getScene().getWindow();
        stage.setScene(new Scene(root, ConstantsDimensions.SCREEN_WIDTH, ConstantsDimensions.SCREEN_HEIGHT));
        stage.show();
    }

    private void loadScores() {
        List<Score> mScores = this.gameManager.getScores();
        this.rankingsTitle.setText(ConstantsLabel.LABEL_RANKINGS_TOP_5);

        for (int i = 5 - mScores.size(); i > 0; i--) {
            mScores.set(mScores.size(), new Score("---", "---", FIRST_SCORE_VALUE));
        }

        this.rankingsFirst.setText(getRanking(mScores.get(0).prepareSave()));
        this.rankingsSecond.setText(getRanking(mScores.get(1).prepareSave()));
        this.rankingsThird.setText(getRanking(mScores.get(2).prepareSave()));
        this.rankingsFourth.setText(getRanking(mScores.get(3).prepareSave()));
        this.rankingsFifth.setText(getRanking(mScores.get(4).prepareSave()));
        this.gameManager.setFactsLabel(this.hintLabel);
    }

    private void initBackButton() {
        backBtn.setOnAction(this::OnBack);
        backBtn.setText(ConstantsLabel.LABEL_BACK_BTN);
        GUIHelper.setViewDimensions(this.backBtn, ConstantsDimensions.ONE_COL, ConstantsDimensions.ONE_ROW/2);
        GUIHelper.styleButton(this.backBtn);
    }

    private void initCurrent() {
        if (this.gameManager.playerHasPlayed()) {
            this.rankingsCurrent.setText(String.format("%s %s %s",
                    this.gameManager.getCityName(),
                    this.gameManager.getCurrentUser(),
                    this.gameManager.getCurrentUserPoints()));
            this.gameManager.setHasPlayed(false);
        } else {
            this.rankingsCurrent.setVisible(false);
        }
    }

    private void setPane(){
        GUIHelper.setBackground(this.mainPane, ConstantsDimensions.SCREEN_WIDTH, ConstantsDimensions.SCREEN_HEIGHT);
        GUIHelper.setViewDimensions(this.background, ConstantsDimensions.SCREEN_WIDTH - ConstantsDimensions.THREE_COLS, ConstantsDimensions.SCREEN_HEIGHT - ConstantsDimensions.THREE_ROWS);
    }

    private void setLabels(){
        GUIHelper.styleLabel(ConstantsDimensions.H2, this.rankingsFirst, this.rankingsSecond, this.rankingsThird, this.rankingsFourth, this.rankingsFifth);
        GUIHelper.styleLabel(ConstantsDimensions.H3*3, this.rankingsTitle);
        this.rankingsTitle.setTextFill(Color.SEAGREEN);
        GUIHelper.setShadow(this.rankingsTitle);
        GUIHelper.styleLabel(ConstantsDimensions.H2/2, this.hintLabel);
        GUIHelper.styleLabel(ConstantsDimensions.H3*3, this.rankingsCurrent);
    }

    private String getRanking(String input) {
        return String.format("%s %s %s",
                input.split(";")[0],
                input.split(";")[1],
                input.split(";")[2]
        );
    }
}

package application.controllers;

import application.constants.ConstantsDimensions;
import application.constants.ConstantsLabel;
import application.constants.ConstantsPath;
import application.constants.ConstantsStyle;
import application.dependencies.DependencyInjectionContainer;
import application.ranking.Ranking;
import application.classes.GUIHelper;
import application.classes.Score;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class DifficultRankingController {

    private static final int FIRST_SCORE_VALUE = 0;
    @FXML
    private StackPane mainPane;
    @FXML
    private ImageView background;
    @FXML
    private Button backBtn;
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
    private Label rankingRightLabel;
    @FXML
    private GridPane rightPane;
    @FXML
    private Label rankingLeftLabel;
    @FXML
    private GridPane leftPane;
    @FXML
    private Label rankingTitle;

    private Ranking ranking = DependencyInjectionContainer.getRankingInstance();

    public void initialize() throws Exception {
        loadCurrentRanking();
        initBackButton();
        initPanes();
        initLabels();
    }

    private void initBackButton(){
        this.backBtn.setOnAction(this::OnBack);
        GUIHelper.setViewDimensions(this.backBtn, ConstantsDimensions.ONE_COL, ConstantsDimensions.ONE_ROW/2);
        GUIHelper.styleAsButton(this.backBtn);
    }

    private void initLabels(){
        setTextLabels();
        setTitleLabel();
        setRiftLabel();
        setSoftUniLabel();
    }

    private void OnBack(ActionEvent actionEvent) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(ConstantsPath.PATH_TO_SELECT_RANKING));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = (Stage)this.backBtn.getScene().getWindow();
        stage.setScene(new Scene(root, ConstantsDimensions.SCREEN_WIDTH, ConstantsDimensions.SCREEN_HEIGHT));
        stage.show();
    }

    private void loadCurrentRanking(){
        List<Score> currentScores = this.ranking.load(this.ranking.getDifficult());
        for (int i = 5 - currentScores.size(); i > 0; i--) {
            currentScores.set(currentScores.size(), new Score("---","---", FIRST_SCORE_VALUE));
        }

        this.rankingsFirst.setText(getCurrentRanking(currentScores.get(0).prepareSave()));
        this.rankingsSecond.setText(getCurrentRanking(currentScores.get(1).prepareSave()));
        this.rankingsThird.setText(getCurrentRanking(currentScores.get(2).prepareSave()));
        this.rankingsFourth.setText(getCurrentRanking(currentScores.get(3).prepareSave()));
        this.rankingsFifth.setText(getCurrentRanking(currentScores.get(4).prepareSave()));
    }

    private static String getCurrentRanking(String input) {
        return String.format("%s %s %s",
                input.split(";")[0],
                input.split(";")[1],
                input.split(";")[2]
        );
    }

    private void initPanes(){
        GUIHelper.setViewDimensions(this.background, ConstantsDimensions.SCREEN_WIDTH- ConstantsDimensions.THREE_COLS, ConstantsDimensions.SCREEN_HEIGHT - ConstantsDimensions.THREE_ROWS);
        GUIHelper.setViewDimensions(this.leftPane, ConstantsDimensions.TWO_COLS, ConstantsDimensions.SCREEN_HEIGHT - ConstantsDimensions.FOUR_ROWS);
        GUIHelper.setViewDimensions(this.rightPane, ConstantsDimensions.TWO_COLS, ConstantsDimensions.SCREEN_HEIGHT - ConstantsDimensions.FOUR_ROWS);
        GUIHelper.setBackground(this.mainPane, ConstantsDimensions.SCREEN_WIDTH, ConstantsDimensions.SCREEN_HEIGHT);
    }

    private void setTextLabels(){
        this.rankingsFirst.setTextFill(Color.WHITESMOKE);
        this.rankingsFirst.setFont(Font.font(ConstantsStyle.DEFAULT_FONT, FontWeight.BOLD, ConstantsDimensions.H2));
        GUIHelper.setShadow(this.rankingsFirst);
        this.rankingsSecond.setTextFill(Color.WHITESMOKE);
        this.rankingsSecond.setFont(Font.font(ConstantsStyle.DEFAULT_FONT, FontWeight.BOLD, ConstantsDimensions.H2));
        GUIHelper.setShadow(this.rankingsSecond);
        this.rankingsThird.setTextFill(Color.WHITESMOKE);
        this.rankingsThird.setFont(Font.font(ConstantsStyle.DEFAULT_FONT, FontWeight.BOLD, ConstantsDimensions.H2));
        GUIHelper.setShadow(this.rankingsThird);
        this.rankingsFourth.setTextFill(Color.WHITESMOKE);
        this.rankingsFourth.setFont(Font.font(ConstantsStyle.DEFAULT_FONT, FontWeight.BOLD, ConstantsDimensions.H2));
        GUIHelper.setShadow(this.rankingsFourth);
        this.rankingsFifth.setTextFill(Color.WHITESMOKE);
        this.rankingsFifth.setFont(Font.font(ConstantsStyle.DEFAULT_FONT, FontWeight.BOLD, ConstantsDimensions.H2));
        GUIHelper.setShadow(this.rankingsFifth);

    }

    private void setTitleLabel() {
        this.rankingTitle.setText(ConstantsLabel.LABEL_RANKING_TITEL_PART_1 + this.ranking.getDifficult() + ConstantsLabel.LABEL_RANKING_TITLE_PART_2);
        this.rankingTitle.setTextFill(Color.SEAGREEN);
        this.rankingTitle.setFont(Font.font(ConstantsStyle.DEFAULT_FONT, FontWeight.BOLD, ConstantsDimensions.H3*3));
        GUIHelper.setShadow(this.rankingTitle);
    }

    private void setRiftLabel() {
        this.rankingLeftLabel.setText(ConstantsLabel.LABEL_TEAM_RIFT);
        this.rankingLeftLabel.setTextFill(Color.WHITESMOKE);
        this.rankingLeftLabel.setFont(Font.font(ConstantsStyle.DEFAULT_FONT, FontWeight.BOLD, ConstantsDimensions.H2));
        GUIHelper.setShadow(this.rankingLeftLabel);
    }

    private void setSoftUniLabel() {
        this.rankingRightLabel.setText(ConstantsLabel.LABEL_SOFTUNI);
        this.rankingRightLabel.setTextFill(Color.WHITE);
        this.rankingRightLabel.setFont(Font.font(ConstantsStyle.DEFAULT_FONT, FontWeight.BOLD, ConstantsDimensions.H3*2));
        GUIHelper.setShadow(this.rankingRightLabel);
    }
}

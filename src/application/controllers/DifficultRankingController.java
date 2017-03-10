package application.controllers;

import application.ranking.Ranking;
import application.classes.GUIHelper;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class DifficultRankingController {

    @FXML
    StackPane mainPane;
    @FXML
    ImageView background;
    @FXML
    Button backBtn;
    @FXML
    Label rankingsFirst;
    @FXML
    Label rankingsSecond;
    @FXML
    Label rankingsThird;
    @FXML
    Label rankingsFourth;
    @FXML
    Label rankingsFifth;
    @FXML
    Label rankingsTitle;
    @FXML
    Label rankingRightLabel;
    @FXML
    GridPane rightPane;
    @FXML
    GridPane bottomPane;
    @FXML
    Label rankingLeftLabel;
    @FXML
    GridPane leftPane;
    @FXML
    Label rankingTitle;
    @FXML
    GridPane topPane;
    @FXML
    GridPane centerPane;

    public void initialize() throws Exception {
        loadCurrentRanking();
        initBackButton();
        initPanes();
        initLabels();
    }

    private void initBackButton(){
        backBtn.setOnAction(this::OnBack);
        GUIHelper.setViewDimensions(backBtn, Values.ONE_COL, Values.ONE_ROW/2);
        GUIHelper.styleAsButton(backBtn);
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
            root = FXMLLoader.load(getClass().getResource("../resources/fxml/selectRanking.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = (Stage)backBtn.getScene().getWindow();
        stage.setScene(new Scene(root, Values.SCREEN_WIDTH, Values.SCREEN_HEIGHT));
        stage.show();
    }

    private void loadCurrentRanking(){
        List<Score> currentScores = Ranking.loadAndSortRanking(Ranking.getDifficult());
        for (int i = 5 - currentScores.size(); i > 0; i--) {
            currentScores.set(currentScores.size(), new Score("---","---",0));
        }

        rankingsFirst.setText(getCurrentRanking(currentScores.get(0).prepareSave()));
        rankingsSecond.setText(getCurrentRanking(currentScores.get(1).prepareSave()));
        rankingsThird.setText(getCurrentRanking(currentScores.get(2).prepareSave()));
        rankingsFourth.setText(getCurrentRanking(currentScores.get(3).prepareSave()));
        rankingsFifth.setText(getCurrentRanking(currentScores.get(4).prepareSave()));
    }

    private static String getCurrentRanking(String input) {
        return String.format("%s %s %s",
                input.split(";")[0],
                input.split(";")[1],
                input.split(";")[2]
        );
    }

    private void initPanes(){
        GUIHelper.setViewDimensions(background, Values.SCREEN_WIDTH- Values.THREE_COLS, Values.SCREEN_HEIGHT - Values.THREE_ROWS);
        GUIHelper.setViewDimensions(leftPane, Values.TWO_COLS, Values.SCREEN_HEIGHT - Values.FOUR_ROWS);
        GUIHelper.setViewDimensions(rightPane, Values.TWO_COLS, Values.SCREEN_HEIGHT - Values.FOUR_ROWS);
        GUIHelper.setBackground(mainPane, Values.SCREEN_WIDTH, Values.SCREEN_HEIGHT);
    }

    private void setTextLabels(){
        rankingsFirst.setTextFill(Color.WHITESMOKE);
        rankingsFirst.setFont(Font.font(Values.DEFAULT_FONT, FontWeight.BOLD, Values.H2));
        GUIHelper.setShadow(rankingsFirst);
        rankingsSecond.setTextFill(Color.WHITESMOKE);
        rankingsSecond.setFont(Font.font(Values.DEFAULT_FONT, FontWeight.BOLD, Values.H2));
        GUIHelper.setShadow(rankingsSecond);
        rankingsThird.setTextFill(Color.WHITESMOKE);
        rankingsThird.setFont(Font.font(Values.DEFAULT_FONT, FontWeight.BOLD, Values.H2));
        GUIHelper.setShadow(rankingsThird);
        rankingsFourth.setTextFill(Color.WHITESMOKE);
        rankingsFourth.setFont(Font.font(Values.DEFAULT_FONT, FontWeight.BOLD, Values.H2));
        GUIHelper.setShadow(rankingsFourth);
        rankingsFifth.setTextFill(Color.WHITESMOKE);
        rankingsFifth.setFont(Font.font(Values.DEFAULT_FONT, FontWeight.BOLD, Values.H2));
        GUIHelper.setShadow(rankingsFifth);

    }

    private void setTitleLabel() {
        rankingTitle.setText(Values.LABEL_RANKING_TITEL_PART_1 + Ranking.getDifficult() + Values.LABEL_RANKING_TITLE_PART_2);
        rankingTitle.setTextFill(Color.SEAGREEN);
        rankingTitle.setFont(Font.font(Values.DEFAULT_FONT, FontWeight.BOLD, Values.H3*3));
        GUIHelper.setShadow(rankingTitle);
    }

    private void setRiftLabel() {
        rankingLeftLabel.setText(Values.LABEL_TEAM_RIFT);
        rankingLeftLabel.setTextFill(Color.WHITESMOKE);
        rankingLeftLabel.setFont(Font.font(Values.DEFAULT_FONT, FontWeight.BOLD, Values.H2));
        GUIHelper.setShadow(rankingLeftLabel);
    }

    private void setSoftUniLabel() {
        rankingRightLabel.setText(Values.LABEL_SOFTUNI);
        rankingRightLabel.setTextFill(Color.WHITE);
        rankingRightLabel.setFont(Font.font(Values.DEFAULT_FONT, FontWeight.BOLD, Values.H3*2));
        GUIHelper.setShadow(rankingRightLabel);
    }
}

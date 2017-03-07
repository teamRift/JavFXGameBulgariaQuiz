package application.controllers;

import application.Ranking.Ranking;
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
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class DifficultRankingController {

    @FXML
    public StackPane mainPane;
    @FXML
    public ImageView background;
    @FXML
    public Button backBtn;
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
    public Label rankingsTitle;

    public void initialize() throws Exception {
        loadCurrentRanking();
        initBackButton();
        initPanes();
    }

    private void initBackButton(){
        backBtn.setOnAction(this::OnBack);
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
        rankingsTitle.setText(Values.LABEL_RANKINGS_TOP_5);

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
        GUIHelper.setViewDimensions(mainPane, Values.SCREEN_WIDTH, Values.SCREEN_HEIGHT);
        GUIHelper.setViewDimensions(background, Values.SCREEN_WIDTH- Values.THREE_COLS, Values.SCREEN_HEIGHT - Values.THREE_ROWS);
        GUIHelper.setBackground(mainPane, Values.SCREEN_WIDTH, Values.SCREEN_HEIGHT);
    }
}

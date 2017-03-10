package application.controllers;

import application.ranking.Ranking;
import application.classes.GUIHelper;
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

public class SelectRankingController {

    @FXML
    Button backBtn;
    @FXML
    Button global;
    @FXML
    Button easy;
    @FXML
    Button normal;
    @FXML
    Button hard;
    @FXML
    StackPane mainPane;
    @FXML
    ImageView background;
    @FXML
    Label rankingTitle;
    @FXML
    Label rankingLeftLabel;
    @FXML
    Label rankingRightLabel;
    @FXML
    GridPane centerPane;
    @FXML
    GridPane topPane;
    @FXML
    GridPane leftPane;
    @FXML
    GridPane bottomPane;
    @FXML
    GridPane rightPane;


    public void initialize() throws Exception {
        initPanes();
        initButtons();
        setTitleLabel();
        setRiftLabel();
        setSoftUniLabel();
    }

    private void initButtons(){
        initBackButton();
        initEasyButton();
        initGlobalButton();
        initNormalButton();
        initHardButton();
    }

    private void initGlobalButton(){
        setButtons();
        global.setOnAction(this::OnClick);
    }

    private void initHardButton() {
        setButtons();
        hard.setOnAction(this::OnClick);
    }

    private void initEasyButton(){
        setButtons();
        easy.setOnAction(this::OnClick);
    }

    private void initNormalButton(){
        setButtons();
        normal.setOnAction(this::OnClick);
    }

    private void initBackButton(){
        setButtons();
        backBtn.setOnAction(this::OnClick);
    }

    private void OnClick(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        String id = button.getId();
        Ranking.setDifficult(id);
        Parent root = null;
        String path = "../resources/fxml/difficultRanking.fxml";
        if (id.equals("backBtn")) {
            path = "../resources/fxml/menu.fxml";
        }

        try {
            root = FXMLLoader.load(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(new Scene(root, Values.SCREEN_WIDTH, Values.SCREEN_HEIGHT));
        stage.show();
    }

    private void initPanes(){
        GUIHelper.setViewDimensions(background, Values.SCREEN_WIDTH- Values.THREE_COLS, Values.SCREEN_HEIGHT - Values.THREE_ROWS);
        GUIHelper.setViewDimensions(leftPane, Values.TWO_COLS, Values.SCREEN_HEIGHT - Values.FOUR_ROWS);
        GUIHelper.setViewDimensions(rightPane, Values.TWO_COLS, Values.SCREEN_HEIGHT - Values.FOUR_ROWS);
        GUIHelper.setBackground(mainPane, Values.SCREEN_WIDTH, Values.SCREEN_HEIGHT);
    }

    private void setButtons() {
        GUIHelper.setViewDimensions(backBtn, Values.ONE_COL, Values.ONE_ROW/2);
        GUIHelper.setViewDimensions(global, Values.TWO_COLS, Values.ONE_ROW/2);
        GUIHelper.setViewDimensions(easy, Values.TWO_COLS, Values.ONE_ROW/2);
        GUIHelper.setViewDimensions(normal, Values.TWO_COLS, Values.ONE_ROW/2);
        GUIHelper.setViewDimensions(hard, Values.TWO_COLS, Values.ONE_ROW/2);
        GUIHelper.styleAsButton(backBtn, easy, normal, hard, global);
    }

    private void setTitleLabel() {
        rankingTitle.setText(Values.LABEL_SELECT_RANKING);
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

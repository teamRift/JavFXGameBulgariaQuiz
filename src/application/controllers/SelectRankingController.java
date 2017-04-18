package application.controllers;

import application.constants.ConstantsDimentions;
import application.constants.ConstantsLabel;
import application.constants.ConstantsPath;
import application.constants.ConstantsStyle;
import application.dependencies.DependencyInjectionContainer;
import application.ranking.Ranking;
import application.classes.GUIHelper;
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
    private Button backBtn;
    @FXML
    private Button global;
    @FXML
    private Button easy;
    @FXML
    private Button normal;
    @FXML
    private Button hard;
    @FXML
    private StackPane mainPane;
    @FXML
    private ImageView background;
    @FXML
    private Label rankingTitle;
    @FXML
    private Label rankingLeftLabel;
    @FXML
    private Label rankingRightLabel;
    @FXML
    private GridPane leftPane;
    @FXML
    private GridPane rightPane;
    @FXML
    private Button rankTblBtn;

    private Ranking ranking = DependencyInjectionContainer.getRankingInstance();

    public void initialize() throws Exception {
        initPanes();
        initButtons();
        setTitleLabel();
        setRiftLabel();
        setSoftUniLabel();
        initRankTblButton();
    }

    private void initButtons(){
        initBackButton();
        initEasyButton();
        initGlobalButton();
        initNormalButton();
        initHardButton();
    }

    private void initRankTblButton(){
        rankTblBtn.setOnAction(this::GoToRankTabel);
        rankTblBtn.setText(ConstantsLabel.LABEL_RANKTABLE_BTN);
        GUIHelper.styleButton(rankTblBtn);

    }

    private void initGlobalButton(){
        setButtons();
        this.global.setOnAction(this::OnClick);
    }

    private void initHardButton() {
        setButtons();
        this.hard.setOnAction(this::OnClick);
    }

    private void initEasyButton(){
        setButtons();
        this.easy.setOnAction(this::OnClick);
    }

    private void initNormalButton(){
        setButtons();
        this.normal.setOnAction(this::OnClick);
    }

    private void initBackButton(){
        setButtons();
        this.backBtn.setOnAction(this::OnClick);
    }

    private void OnClick(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        String id = button.getId();
        this.ranking.setDifficult(id);
        Parent root = null;
        String path = ConstantsPath.PATH_TO_DIFFICULT_RANKING;
        if (id.equals("backBtn")) {
            path = ConstantsPath.PATH_TO_MENU;
        }

        try {
            root = FXMLLoader.load(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(new Scene(root, ConstantsDimentions.SCREEN_WIDTH, ConstantsDimentions.SCREEN_HEIGHT));
        stage.show();
    }

    private void GoToRankTabel(ActionEvent actionEvent){
        Parent root=null;
        try {
            root = FXMLLoader.load(getClass().getResource("../resources/fxml/rankTable.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage=(Stage)rankTblBtn.getScene().getWindow();
        stage.setScene(new Scene(root, ConstantsDimentions.SCREEN_WIDTH,ConstantsDimentions.SCREEN_HEIGHT));
        stage.show();
    }

    private void initPanes(){
        GUIHelper.setViewDimensions(this.background, ConstantsDimentions.SCREEN_WIDTH- ConstantsDimentions.THREE_COLS, ConstantsDimentions.SCREEN_HEIGHT - ConstantsDimentions.THREE_ROWS);
        GUIHelper.setViewDimensions(this.leftPane, ConstantsDimentions.TWO_COLS, ConstantsDimentions.SCREEN_HEIGHT - ConstantsDimentions.FOUR_ROWS);
        GUIHelper.setViewDimensions(this.rightPane, ConstantsDimentions.TWO_COLS, ConstantsDimentions.SCREEN_HEIGHT - ConstantsDimentions.FOUR_ROWS);
        GUIHelper.setBackground(this.mainPane, ConstantsDimentions.SCREEN_WIDTH, ConstantsDimentions.SCREEN_HEIGHT);
    }

    private void setButtons() {
        GUIHelper.setViewDimensions(this.backBtn, ConstantsDimentions.ONE_COL, ConstantsDimentions.ONE_ROW/2);
        GUIHelper.setViewDimensions(this.global, ConstantsDimentions.TWO_COLS, ConstantsDimentions.ONE_ROW/2);
        GUIHelper.setViewDimensions(this.easy, ConstantsDimentions.TWO_COLS, ConstantsDimentions.ONE_ROW/2);
        GUIHelper.setViewDimensions(this.normal, ConstantsDimentions.TWO_COLS, ConstantsDimentions.ONE_ROW/2);
        GUIHelper.setViewDimensions(this.hard, ConstantsDimentions.TWO_COLS, ConstantsDimentions.ONE_ROW/2);
        GUIHelper.setViewDimensions(this.rankTblBtn, ConstantsDimentions.TWO_COLS, ConstantsDimentions.ONE_ROW/2);
        GUIHelper.styleAsButton(this.backBtn, this.easy, this.normal, this.hard, this.global, this.rankTblBtn);
    }

    private void setTitleLabel() {
        this.rankingTitle.setText(ConstantsLabel.LABEL_SELECT_RANKING);
        this.rankingTitle.setTextFill(Color.SEAGREEN);
        this.rankingTitle.setFont(Font.font(ConstantsStyle.DEFAULT_FONT, FontWeight.BOLD, ConstantsDimentions.H3*3));
        GUIHelper.setShadow(this.rankingTitle);
    }

    private void setRiftLabel() {
        this.rankingLeftLabel.setText(ConstantsLabel.LABEL_TEAM_RIFT);
        this.rankingLeftLabel.setTextFill(Color.WHITESMOKE);
        this.rankingLeftLabel.setFont(Font.font(ConstantsStyle.DEFAULT_FONT, FontWeight.BOLD, ConstantsDimentions.H2));
        GUIHelper.setShadow(this.rankingLeftLabel);
    }

    private void setSoftUniLabel() {
        this.rankingRightLabel.setText(ConstantsLabel.LABEL_SOFTUNI);
        this.rankingRightLabel.setTextFill(Color.WHITE);
        this.rankingRightLabel.setFont(Font.font(ConstantsStyle.DEFAULT_FONT, FontWeight.BOLD, ConstantsDimentions.H3*2));
        GUIHelper.setShadow(this.rankingRightLabel);
    }
}

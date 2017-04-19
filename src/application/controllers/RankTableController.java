package application.controllers;

import application.classes.GUIHelper;
import application.classes.GameManager;
import application.classes.Score;
import application.constants.ConstantsDimentions;
import application.constants.ConstantsLabel;
import application.constants.ConstantsPath;
import application.constants.ConstantsStyle;
import application.dependencies.DependencyInjectionContainer;
import application.enums.RankTableEnum;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.List;


public class RankTableController {
    private static final int GOLDEN_SCORE = 40;
    private static final int SILVER_SCORE = 30;
    private static final int BRONZE_SCORE = 20;
    private static final int MAX_SIZE_FOR_SHOW_PLAYERS = 13;
    private static final String PLAYER_CITY = "[Player: %s] -- [City - %s]\n";

    @FXML
    private Label tableTitle;
    @FXML
    private Label GoldenLabel;
    @FXML
    private Label SilverLabel;
    @FXML
    private Label BronzeLabel;
    @FXML
    private Button backBtn;
    @FXML
    private AnchorPane mainPane;

    private GameManager gameManager = DependencyInjectionContainer.getGameManagerInstance();


    public void initialize() throws Exception {
        loadScores();
        setPanes();
        setTitle();
        initBackButton();
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> System.out.println("Started."));
        delay.play();
    }

    private void loadScores() {
        gameManager.getScores();

        List<Score> mScores = gameManager.getScores();

        GoldenLabel.setText(getNamesByScore(RankTableEnum.GOLDEN, mScores));
        SilverLabel.setText(getNamesByScore(RankTableEnum.SILVER, mScores));
        BronzeLabel.setText(getNamesByScore(RankTableEnum.BRONZE, mScores));

    }

    private String getNamesByScore(RankTableEnum rankTableEnum, List<Score> mScores) {
        StringBuilder names = new StringBuilder();
        switch (rankTableEnum) {
            case GOLDEN:
                mScores.stream().filter(s -> s.getValue() == GOLDEN_SCORE).limit(MAX_SIZE_FOR_SHOW_PLAYERS)
                        .forEach(n -> names.append(String.format(PLAYER_CITY, n.getUserName(), n.getCityName())));

                break;
            case SILVER:
                mScores.stream().filter(s -> s.getValue() == SILVER_SCORE).limit(MAX_SIZE_FOR_SHOW_PLAYERS)
                        .forEach(n -> names.append(String.format(PLAYER_CITY, n.getUserName(), n.getCityName())));

                break;
            case BRONZE:
                mScores.stream().filter(s -> s.getValue() == BRONZE_SCORE).limit(MAX_SIZE_FOR_SHOW_PLAYERS)
                        .forEach(n -> names.append(String.format(PLAYER_CITY, n.getUserName(), n.getCityName())));

                break;
        }
        return names.toString();
    }

    private void setPanes() {
        GUIHelper.setBackground(this.mainPane, ConstantsDimentions.SCREEN_WIDTH, ConstantsDimentions.SCREEN_HEIGHT);

    }

    public void back(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(ConstantsPath.PATH_TO_MENU));
        Stage stage = (Stage) this.backBtn.getScene().getWindow();
        stage.setScene(new Scene(root, ConstantsDimentions.SCREEN_WIDTH, ConstantsDimentions.SCREEN_HEIGHT));
        stage.show();
    }

    private void setTitle(){
        this.tableTitle.setTextFill(Color.SEAGREEN);
        this.tableTitle.setFont(Font.font(ConstantsStyle.DEFAULT_FONT, FontWeight.BOLD, ConstantsDimentions.H3*3));
        this.tableTitle.setLayoutX(ConstantsDimentions.FOUR_COLS);
        this.tableTitle.setLayoutY(ConstantsDimentions.ONE_ROW);
        GUIHelper.setShadow(this.tableTitle);
    }

    private void initBackButton(){
        GUIHelper.setViewDimensions(this.backBtn, ConstantsDimentions.ONE_COL, ConstantsDimentions.ONE_ROW/2);
        GUIHelper.styleAsButton(this.backBtn);
    }
}

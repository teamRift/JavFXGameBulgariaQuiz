package application.controllers;

import application.classes.GUIHelper;
import application.classes.GameManager;
import application.constants.*;
import application.dependencies.DependencyInjectionContainer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;


public class CitiesController {
    @FXML
    private Label labelDifficulty;
    @FXML
    private Button buttonEasy;
    @FXML
    private Button buttonNormal;
    @FXML
    private Button buttonHard;
    @FXML
    private TextField inputUserName;
    @FXML
    private Label hintLabel, labelChooseCity;
    @FXML
    private Button varna, sofia, ruse, burgas, blagoevgrad, velikoturnovo, pleven, plovdiv, backButton, hardButton, normalButton,easyButton;
    @FXML
    private AnchorPane mapPane;
    @FXML
    private BorderPane mainPane;
    @FXML
    private GridPane topPane, bottomPane, leftPane, rightPane ;
    @FXML
    private ImageView mapImg;
    @FXML
    private VBox difficultyBox;

    private GameManager gameManager = DependencyInjectionContainer.getGameManagerInstance();

    public void initialize() throws IOException {
        this.inputUserName.setFont(Font.font(ConstantsStyle.DEFAULT_FONT, FontWeight.NORMAL, FontPosture.REGULAR, ConstantsDimensions.H3));
        this.gameManager.setUserName(this.inputUserName);
        setPanes();
        setButtons();
        setLabels();
    }

    public void onCityQuestion(ActionEvent actionEvent) throws IOException {
        Button button  = (Button)  actionEvent.getSource();
        String id = button.getId();
        this.gameManager.setCurrentUser(this.inputUserName.getText());
        setCity(id.toLowerCase());
        Parent root = FXMLLoader.load(getClass().getResource(ConstantsPath.PATH_TO_QUIZ_WINDOW));
        Stage stage = (Stage)button.getScene().getWindow();
        stage.setScene( new Scene( root, ConstantsDimensions.SCREEN_WIDTH, ConstantsDimensions.SCREEN_HEIGHT));
        stage.show();
    }

    public void OnBack(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(ConstantsPath.PATH_TO_MENU));
        Stage stage = ( Stage ) this.backButton.getScene().getWindow();
        stage.setScene( new Scene( root, ConstantsDimensions.SCREEN_WIDTH, ConstantsDimensions.SCREEN_HEIGHT));
        stage.show();
    }

    private void setButtons() {
        GUIHelper.styleButton(this.buttonEasy, this.buttonNormal, this.buttonHard);
        GUIHelper.setViewDimensions(this.buttonEasy, ConstantsDimensions.ONE_COL, ConstantsDimensions.ONE_ROW/2);
        this.buttonEasy.setOnAction((event -> {
            resetDifficultyButtons();
            this.gameManager.setQuestionsDifficulty(ConstantsDifficulty.DIFFICULTY_EASY);
            ((Button)event.getSource()).setTextFill(Color.WHITE);
            ((Button)event.getSource()).setBackground(new Background(new BackgroundFill(Paint.valueOf("#006600"),new CornerRadii(7), new Insets(5,5,5,5))));
        }));
        GUIHelper.setViewDimensions(this.buttonNormal, ConstantsDimensions.ONE_COL, ConstantsDimensions.ONE_ROW/2);
        this.buttonNormal.setOnAction((event -> {
            resetDifficultyButtons();
            this.gameManager.setQuestionsDifficulty(ConstantsDifficulty.DIFFICULTY_NORMAL);
            ((Button)event.getSource()).setTextFill(Color.WHITE);
            ((Button)event.getSource()).setBackground(new Background(new BackgroundFill(Paint.valueOf("#006600"),new CornerRadii(7), new Insets(5,5,5,5))));
        }));
        GUIHelper.setViewDimensions(this.buttonHard, ConstantsDimensions.ONE_COL, ConstantsDimensions.ONE_ROW/2);
        this.buttonHard.setOnAction((event -> {
            resetDifficultyButtons();
            this.gameManager.setQuestionsDifficulty(ConstantsDifficulty.DIFFICULTY_HARD);
            ((Button)event.getSource()).setTextFill(Color.WHITE);
            ((Button)event.getSource()).setBackground(new Background(new BackgroundFill(Paint.valueOf("#006600"),new CornerRadii(7), new Insets(5,5,5,5))));
        }));


        GUIHelper.styleCityButton(this.varna, this.ruse, this.sofia, this.burgas, this.blagoevgrad, this.plovdiv, this.pleven, this.velikoturnovo);
        this.burgas.setLayoutX(ConstantsDimensions.SIX_COLS - ConstantsDimensions.ONE_ROW);
        this.burgas.setLayoutY(ConstantsDimensions.FOUR_ROWS * 1.2);

        this.varna.setLayoutX(ConstantsDimensions.SIX_COLS * 0.93);
        this.varna.setLayoutY(ConstantsDimensions.TWO_ROWS * 1.27);

        this.ruse.setLayoutX(ConstantsDimensions.THREE_COLS * 1.07);
        this.ruse.setLayoutY(ConstantsDimensions.ONE_ROW* 1.2);

        this.sofia.setLayoutX(ConstantsDimensions.ONE_COL);
        this.sofia.setLayoutY(ConstantsDimensions.TWO_COLS * 1.12);

        this.blagoevgrad.setLayoutX(ConstantsDimensions.ONE_COL * 0.75);
        this.blagoevgrad.setLayoutY(ConstantsDimensions.SIX_ROWS * 1.2);

        this.plovdiv.setLayoutX(ConstantsDimensions.THREE_COLS);
        this.plovdiv.setLayoutY(ConstantsDimensions.FOUR_ROWS * 1.3);

        this.pleven.setLayoutX(ConstantsDimensions.TWO_COLS * 0.97);
        this.pleven.setLayoutY(ConstantsDimensions.TWO_ROWS * 1.45);

        this.velikoturnovo.setLayoutX(ConstantsDimensions.THREE_COLS * 1.15);
        this.velikoturnovo.setLayoutY(ConstantsDimensions.TWO_ROWS * 1.5);

        GUIHelper.styleButton(this.backButton);
        GUIHelper.setViewDimensions(this.backButton, ConstantsDimensions.ONE_COL*0.8, ConstantsDimensions.ONE_ROW/2);
        this.backButton.setText(ConstantsLabel.LABEL_BACK_BTN);
    }

    private void resetDifficultyButtons() {
        this.buttonEasy.setTextFill(Color.BLACK);
        this.buttonEasy.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii(7), new Insets(5,5,5,5))));
        this.buttonNormal.setTextFill(Color.BLACK);
        this.buttonNormal.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii(7), new Insets(5,5,5,5))));
        this.buttonHard.setTextFill(Color.BLACK);
        this.buttonHard.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii(7), new Insets(5,5,5,5))));
    }

    private void setLabels() {
        this.gameManager.setFactsLabel(this.hintLabel);
        GUIHelper.styleLabel(ConstantsDimensions.H3, this.hintLabel);
        this.labelDifficulty.setText(ConstantsLabel.LABEL_DIFFICULTY);
        this.labelChooseCity.setText(ConstantsLabel.LABEL_CHOOSE_CITY_BTN);
        GUIHelper.styleLabel(ConstantsDimensions.H3*2, this.labelChooseCity);
    }

    private void setCity(String cityName) {
        this.gameManager.setCityName(cityName);
    }

    private void setPanes(){
        GUIHelper.setViewDimensions(this.mainPane, ConstantsDimensions.SCREEN_WIDTH, ConstantsDimensions.SCREEN_HEIGHT);
        GUIHelper.setViewDimensions(this.topPane, ConstantsDimensions.SCREEN_WIDTH, ConstantsDimensions.THREE_ROWS);
        GUIHelper.setViewDimensions(this.bottomPane, ConstantsDimensions.SCREEN_WIDTH, ConstantsDimensions.TWO_ROWS);
        GUIHelper.setViewDimensions(this.leftPane, ConstantsDimensions.THREE_COLS, ConstantsDimensions.SCREEN_HEIGHT - ConstantsDimensions.FOUR_ROWS);
        GUIHelper.setViewDimensions(this.rightPane, ConstantsDimensions.THREE_COLS, ConstantsDimensions.SCREEN_HEIGHT - ConstantsDimensions.FOUR_ROWS);
        GUIHelper.setViewDimensions(this.mapPane, ConstantsDimensions.SIX_COLS, ConstantsDimensions.SIX_ROWS + ConstantsDimensions.THREE_ROWS);
        this.difficultyBox.setPadding(new Insets(0,0,0, ConstantsDimensions.ONE_ROW/5));
        GUIHelper.setBackground(this.mainPane, ConstantsDimensions.SCREEN_WIDTH, ConstantsDimensions.SCREEN_HEIGHT);
        this.mapImg.setFitWidth(ConstantsDimensions.SCREEN_WIDTH);
        this.mapImg.setFitWidth(ConstantsDimensions.SCREEN_HEIGHT);
    }
}

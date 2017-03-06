package application.controllers;

import application.classes.City;
import application.classes.GUIHelper;
import application.classes.GameManager;
import application.classes.Values;
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
    public Label labelDifficulty;
    @FXML
    public Button buttonEasy;
    @FXML
    public Button buttonNormal;
    @FXML
    public Button buttonHard;
    public Label labelInputUserName;
    public TextField inputUserName;
    @FXML
    Label hintLabel, labelChooseCity;
    @FXML
    Button varna, sofia, ruse, burgas, blagoevgrad, velikoturnovo, pleven, plovdiv, backButton, hardButton, normalButton,easyButton;
    @FXML
    AnchorPane mapPane;
    @FXML
    BorderPane mainPane;
    @FXML
    GridPane topPane, bottomPane, leftPane, rightPane ;
    @FXML
    ImageView mapImg;
    @FXML
    VBox difficultyBox;
    public void initialize() throws IOException {
        inputUserName.setFont(Font.font(Values.DEFAULT_FONT, FontWeight.NORMAL, FontPosture.REGULAR, Values.H3));
        GameManager.setUserName(inputUserName);
        setPanes();
        setButtons();
        setLabels();
    }

    public void onCityQuestion(ActionEvent actionEvent) throws IOException {
        Button button  = (Button)  actionEvent.getSource();
        String id = button.getId();
        GameManager.setCurrentUser(inputUserName.getText());
        setCity(id.toLowerCase());
        Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/quizWindow.fxml"));
        Stage stage = (Stage)button.getScene().getWindow();
        stage.setScene( new Scene( root, Values.SCREEN_WIDTH, Values.SCREEN_HEIGHT));
        stage.show();
    }

    public void OnBack(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/menu.fxml"));
        Stage stage = ( Stage ) backButton.getScene().getWindow();
        stage.setScene( new Scene( root, Values.SCREEN_WIDTH, Values.SCREEN_HEIGHT));
        stage.show();
    }

    private void setButtons() {
        GUIHelper.styleButton(buttonEasy,buttonNormal,buttonHard);
        GUIHelper.setViewDimensions(buttonEasy,Values.ONE_COL, Values.ONE_ROW/2);
        buttonEasy.setOnAction((event -> {
            resetDifficultyButtons();
            GameManager.setQuestionsDifficulty(Values.DIFFICULTY_EASY);
            ((Button)event.getSource()).setTextFill(Color.WHITE);
            ((Button)event.getSource()).setBackground(new Background(new BackgroundFill(Paint.valueOf("#006600"),new CornerRadii(7), new Insets(5,5,5,5))));
        }));
        GUIHelper.setViewDimensions(buttonNormal,Values.ONE_COL, Values.ONE_ROW/2);
        buttonNormal.setOnAction((event -> {
            resetDifficultyButtons();
            GameManager.setQuestionsDifficulty(Values.DIFFICULTY_NORMAL);
            ((Button)event.getSource()).setTextFill(Color.WHITE);
            ((Button)event.getSource()).setBackground(new Background(new BackgroundFill(Paint.valueOf("#006600"),new CornerRadii(7), new Insets(5,5,5,5))));
        }));
        GUIHelper.setViewDimensions(buttonHard,Values.ONE_COL, Values.ONE_ROW/2);
        buttonHard.setOnAction((event -> {
            resetDifficultyButtons();
            GameManager.setQuestionsDifficulty(Values.DIFFICULTY_HARD);
            ((Button)event.getSource()).setTextFill(Color.WHITE);
            ((Button)event.getSource()).setBackground(new Background(new BackgroundFill(Paint.valueOf("#006600"),new CornerRadii(7), new Insets(5,5,5,5))));
        }));


        GUIHelper.styleCityButton(varna,ruse,sofia,burgas,blagoevgrad,plovdiv,pleven,velikoturnovo);
        burgas.setLayoutX(Values.SIX_COLS - Values.ONE_ROW);
        burgas.setLayoutY(Values.FOUR_ROWS * 1.2);

        varna.setLayoutX(Values.SIX_COLS * 0.93);
        varna.setLayoutY(Values.TWO_ROWS * 1.27);

        ruse.setLayoutX(Values.THREE_COLS * 1.07);
        ruse.setLayoutY(Values.ONE_ROW* 1.2);

        sofia.setLayoutX(Values.ONE_COL);
        sofia.setLayoutY(Values.TWO_COLS * 1.12);

        blagoevgrad.setLayoutX(Values.ONE_COL * 0.75);
        blagoevgrad.setLayoutY(Values.SIX_ROWS * 1.2);

        plovdiv.setLayoutX(Values.THREE_COLS);
        plovdiv.setLayoutY(Values.FOUR_ROWS * 1.3);

        pleven.setLayoutX(Values.TWO_COLS * 0.97);
        pleven.setLayoutY(Values.TWO_ROWS * 1.45);

        velikoturnovo.setLayoutX(Values.THREE_COLS * 1.15);
        velikoturnovo.setLayoutY(Values.TWO_ROWS * 1.5);

        GUIHelper.styleButton(backButton);
        GUIHelper.setViewDimensions(backButton,Values.ONE_COL*0.8, Values.ONE_ROW/2);

        backButton.setText(Values.LABEL_BACK_BTN);
    }
    private void resetDifficultyButtons() {
        buttonEasy.setTextFill(Color.BLACK);
        buttonEasy.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii(7), new Insets(5,5,5,5))));
        buttonNormal.setTextFill(Color.BLACK);
        buttonNormal.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii(7), new Insets(5,5,5,5))));
        buttonHard.setTextFill(Color.BLACK);
        buttonHard.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii(7), new Insets(5,5,5,5))));
    }
    private void setLabels() {

        GameManager.setFactsLabel(hintLabel);

        GUIHelper.styleLabel(Values.H3,hintLabel);
        labelDifficulty.setText(Values.LABEL_DIFFICULTY);

        labelChooseCity.setText(Values.LABEL_CHOOSE_CITY_BTN);

        GUIHelper.styleLabel(Values.H2,labelChooseCity);

    }
    private void setCity(String cityName) {
        GameManager.setCityName(cityName);
    }
    private void setPanes(){

        GUIHelper.setViewDimensions(mainPane, Values.SCREEN_WIDTH, Values.SCREEN_HEIGHT);

        GUIHelper.setViewDimensions(topPane, Values.SCREEN_WIDTH, Values.THREE_ROWS);

        GUIHelper.setViewDimensions(bottomPane, Values.SCREEN_WIDTH, Values.TWO_ROWS);

        GUIHelper.setViewDimensions(leftPane, Values.THREE_COLS, Values.SCREEN_HEIGHT - Values.FOUR_ROWS);

        GUIHelper.setViewDimensions(rightPane, Values.THREE_COLS, Values.SCREEN_HEIGHT - Values.FOUR_ROWS);

        GUIHelper.setViewDimensions(mapPane, Values.SIX_COLS, Values.SIX_ROWS + Values.THREE_ROWS);
        difficultyBox.setPadding(new Insets(0,0,0,Values.ONE_ROW/5));
        GUIHelper.setBackground(mainPane, Values.SCREEN_WIDTH,Values.SCREEN_HEIGHT);

        mapImg.setFitWidth(Values.SCREEN_WIDTH);
        mapImg.setFitWidth(Values.SCREEN_HEIGHT);
    }
}

package application.controllers;

import application.classes.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;

import static application.controllers.BootController.gameManager;


public class MenuController {
    public Button songBtn;
    @FXML
    BorderPane mainPane;
    @FXML
    GridPane topPane, bottomPane, leftPane, rightPane, centerPane;
    @FXML
    Label gameTitle,exitBtn,creditsBtn, startBtn, scoresBtn, rankBtn, getMenuLeftLabel,getMenuRightLabel;
    @FXML
    HBox startGameBox,scoresBox,creditsBox,exitBox;
    @FXML
    TextField inputUserName;
    @FXML
    VBox buttonsGroup;
    @FXML
    ImageView mapImage;
    @FXML
    Label menuLeftLabel;
    @FXML
    Label menuRightLabel;

    public void initialize() {
        initButtons();
        initPanes();
        initLabels();
    }

    private void initButtons(){
        initCreditsButton();
        initExitButton();
        initScoresButton();
        initStartButton();
        initRankButton();
        initSongButton();
    }
    private void initStartButton(){
        startBtn.setText(Values.LABEL_START_BTN);
        setStartButton();
        startBtn.setOnMouseClicked(actionEvent -> {
            try {
                startGame();
            } catch (IOException e) {
                e.printStackTrace();
            }
            gameManager.setCurrentUser(inputUserName.getText());
        });

    }
    private void initExitButton(){
        exitBtn.setText(Values.LABEL_EXIT_BTN);
        setSize(exitBtn, Values.FOUR_COLS, Values.ONE_ROW);
        exitBtn.setOnMouseClicked(actionEvent -> {
            exitGame();
            gameManager.setCurrentUser(inputUserName.getText());
        });
    }
    private void initCreditsButton(){
        creditsBtn.setText(Values.LABEL_CREDITS_BTN);
        creditsBtn.setOnMouseClicked(actionEvent -> {
            try {
                showCredits();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    private void initScoresButton(){
        scoresBtn.setText(Values.LABEL_SCORES_BTN);
        scoresBtn.setOnMouseClicked(actionEvent -> {
            try {
                showScores();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
    private void initSongButton(){
        if (Song.check){
            songBtn.setText("Song On");
        }
    }
    private void initRankButton(){
        //        rankBtn.setText(Values.LABEL_RANKTABLE_BTN);
//        rankBtn.setMaxWidth(Values.FOUR_COLS);
//        rankBtn.setMinWidth(Values.FOUR_COLS);
//        rankBtn.setPrefWidth(Values.FOUR_COLS);
//        rankBtn.setMaxHeight(Values.ONE_ROW/2);
//        rankBtn.setOnAction((ActionEvent actionEvent) ->{
//            try{
//                showRankTable(actionEvent);
//            }catch(IOException e){
//                e.printStackTrace();
//            }
//        });

    }    
    private void initLabels() {

        setGameLabel();

        setRiftLabel();

        setSoftUniLabel();

    }
    private void setGameLabel() {

        gameTitle.setText(Values.LABEL_GAME_TITLE);

        gameTitle.setTextFill(Color.SEAGREEN);

        gameTitle.setFont(Font.font(Values.DEFAULT_FONT, FontWeight.BOLD,Values.H1 - Values.H2));

        setShadow(gameTitle);
    }
    private void setRiftLabel() {

        menuLeftLabel.setText(Values.LABEL_TEAM_RIFT);

        menuLeftLabel.setTextFill(Color.WHITESMOKE);

        menuLeftLabel.setFont(Font.font(Values.DEFAULT_FONT, FontWeight.BOLD, Values.H2));

        setShadow(menuLeftLabel);

    }
    private void setSoftUniLabel() {

        menuRightLabel.setText(Values.LABEL_SOFTUNI);

        menuRightLabel.setTextFill(Color.WHITE);

        menuRightLabel.setFont(Font.font(Values.DEFAULT_FONT, FontWeight.BOLD,Values.H3*2));

        setShadow(menuRightLabel);
    }
    private void setStartButton(){

        inputUserName.setFont(Font.font(Values.DEFAULT_FONT, FontWeight.NORMAL, FontPosture.REGULAR, Values.H3));
        gameManager.setUserName(inputUserName);
        inputUserName.setBackground(Background.EMPTY);
        setSize(inputUserName,Values.ONE_COL, Values.ONE_ROW);
        setSize(startBtn, Values.ONE_COL, Values.ONE_ROW);
        setSize(exitBtn, Values.ONE_COL*1.5, Values.ONE_ROW);
        setSize(creditsBtn, Values.ONE_COL*1.5, Values.ONE_ROW);
        setSize(scoresBtn, Values.ONE_COL*1.5, Values.ONE_ROW);

        setSize(startGameBox,Values.THREE_COLS,Values.ONE_ROW);
        setSize(scoresBox,Values.THREE_COLS,Values.ONE_ROW);
        setSize(creditsBox,Values.THREE_COLS,Values.ONE_ROW);
        setSize(exitBox,Values.THREE_COLS,Values.ONE_ROW);

        styleAsButton(startBtn,exitBtn,creditsBtn,scoresBtn);

        styleAsButton(startGameBox,scoresBox,creditsBox,exitBox);
    }
    private void initPanes(){

        setSize(mainPane, Values.SCREEN_WIDTH, Values.SCREEN_HEIGHT);

        setSize(topPane, Values.SCREEN_WIDTH, Values.TWO_ROWS);

        setSize(bottomPane, Values.SCREEN_WIDTH, Values.TWO_ROWS);

        setSize(leftPane, Values.TWO_COLS, Values.SCREEN_HEIGHT - Values.FOUR_ROWS);

        setSize(rightPane, Values.TWO_COLS, Values.SCREEN_HEIGHT - Values.FOUR_ROWS);

        setSize(centerPane, Values.SCREEN_WIDTH , Values.SCREEN_HEIGHT);

        setSize(mapImage, Values.SCREEN_WIDTH- Values.THREE_COLS, Values.SCREEN_HEIGHT - Values.THREE_ROWS);

        setBackground(mainPane, Values.SCREEN_WIDTH,Values.SCREEN_HEIGHT);

    }

    private void exitGame() {

        Stage stage = (Stage)exitBtn.getScene().getWindow();

        stage.close();

    }
    private void showCredits() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/credits.fxml"));

        Stage stage = (Stage)creditsBtn.getScene().getWindow();

        stage.setScene(new Scene(root, Values.SCREEN_WIDTH,Values.SCREEN_HEIGHT));

        stage.show();

    }
    private void showScores() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/rankings.fxml"));

        Stage stage = (Stage)scoresBtn.getScene().getWindow();

        stage.setScene(new Scene(root, Values.SCREEN_WIDTH,Values.SCREEN_HEIGHT));

        stage.show();

    }
    private void startGame() throws IOException {

        gameManager = new GameManager();

        Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/cities.fxml"));

        Stage stage = (Stage)startBtn.getScene().getWindow();

        stage.setScene(new Scene(root, Values.SCREEN_WIDTH,Values.SCREEN_HEIGHT));

        stage.show();

    }
    //    @FXML
//    public void showRankTable(ActionEvent actionEvent) throws IOException{
//        Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/RankTable.fxml"));
//        Stage stage = (Stage)rankBtn.getScene().getWindow();
//        stage.setScene(new Scene(root, Values.SCREEN_WIDTH,Values.SCREEN_HEIGHT));
//        stage.show();
//    }

    private static String capitalize(String input){
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }
    private static void setBackground(Pane pane, double WIDTH, double HEIGHT) {

        BackgroundImage myBI = new BackgroundImage(new Image(Values.IMG_BACKGROUND,WIDTH,HEIGHT,false,true),
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        pane.setBackground(new Background(myBI));

    }
    private static void setSize(Object object, double WIDTH, double HEIGHT) {

        if (object instanceof Pane) {

            ((Pane) object).setMinWidth(WIDTH);

            ((Pane) object).setMaxWidth(WIDTH);

            ((Pane) object).setPrefWidth(WIDTH);

            ((Pane) object).setMinHeight(HEIGHT);

            ((Pane) object).setMaxHeight(HEIGHT);

            ((Pane) object).setPrefHeight(HEIGHT);

        } else if (object instanceof Button) {

            ((Button) object).setMinWidth(WIDTH);

            ((Button) object).setMaxWidth(WIDTH);

            ((Button) object).setPrefWidth(WIDTH);

            ((Button) object).setMinHeight(HEIGHT);

            ((Button) object).setMaxHeight(HEIGHT);

            ((Button) object).setPrefHeight(HEIGHT);

            ((Button) object).setFont(Font.font(Values.DEFAULT_FONT, FontWeight.BOLD, Values.H3));

            styleButton((Button) object);

        } else if (object instanceof Label) {

            ((Label) object).setMinWidth(WIDTH);

            ((Label) object).setPrefWidth(WIDTH);

            ((Label) object).setMaxWidth(WIDTH);

            ((Label) object).setMinHeight(HEIGHT);

            ((Label) object).setPrefHeight(HEIGHT);

            ((Label) object).setMaxHeight(HEIGHT);


        } else if (object instanceof ImageView) {

            ((ImageView) object).setFitWidth(WIDTH);

            ((ImageView) object).setFitHeight(HEIGHT);

        } else if (object instanceof TextField) {

            ((TextField) object).setMinWidth(WIDTH);

            ((TextField) object).setMaxWidth(WIDTH);

            ((TextField) object).setPrefWidth(WIDTH);

            ((TextField) object).setMinHeight(HEIGHT);

            ((TextField) object).setMaxHeight(HEIGHT);

            ((TextField) object).setPrefHeight(HEIGHT);

            ((TextField) object).setFont(Font.font(Values.DEFAULT_FONT, FontWeight.BOLD, Values.H3));

        } else {

            System.out.printf("setSize(Object object) : %s is not a valid object. Pane, ImageView, Label or Button required.", capitalize(object.getClass().getSimpleName()));

        }
    }
    private static void setShadow(Object object) {

        DropShadow shadow = new DropShadow();

        shadow.setColor(Color.BLACK);

        if (object instanceof Label) {

            shadow.setRadius(5);

            shadow.setOffsetX(5);

            shadow.setOffsetY(5);

            shadow.setBlurType(BlurType.ONE_PASS_BOX);

            ((Label) object).setEffect(shadow);

        } else if (object instanceof Button) {

            shadow.setRadius(5);

            shadow.setOffsetX(5);

            shadow.setOffsetY(5);

            shadow.setBlurType(BlurType.GAUSSIAN);

            ((Button) object).setEffect(shadow);

        } else if (object instanceof HBox) {

            shadow.setRadius(-5);

            shadow.setOffsetX(5);

            shadow.setOffsetY(5);

            shadow.setBlurType(BlurType.GAUSSIAN);

            ((HBox) object).setEffect(shadow);

        }

    }
    private static void styleButton(Button... buttons) {

        for (Button button : buttons) {

            button.setBackground(new Background(new BackgroundFill(Paint.valueOf("#FFFFFF"),new CornerRadii(7), new Insets(5,5,5,5))));

            button.setFont(Font.font(Values.DEFAULT_FONT,FontWeight.BOLD, Values.H3));

            button.setTextFill(Color.BLACK);

            setShadow(button);

        }

    }
    private static void styleAsButton(Object... objects) {
        for (Object object : objects) {
            if (object instanceof HBox){

                ((HBox) object).setBackground(
                        new Background(
                                new BackgroundFill(Paint.valueOf("#FFFFFF"),
                                        new CornerRadii(7),
                                        new Insets(5))));

                setShadow(((HBox) object));

            } else if (object instanceof Label) {

                ((Label) object).setFont(Font.font(Values.DEFAULT_FONT,FontWeight.BOLD,Values.H3));

                ((Label) object).setTextFill(Color.BLACK);

            }

        }

    }

    public void songControllers(ActionEvent actionEvent) {
        if (Song.check){
            Song.start();
            songBtn.setText("Song Off");
        } else {
            Song.pause();
            songBtn.setText("Song On");
        }
    }
}

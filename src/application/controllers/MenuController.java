package application.controllers;

import application.classes.GUIHelper;
import application.classes.Song;
import application.classes.Values;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {
    public Button songBtn,buttonExit,buttonCredits, buttonStart, buttonScores;
    @FXML
    BorderPane mainPane;
    @FXML
    GridPane topPane, bottomPane, leftPane, rightPane, centerPane;
    @FXML
    Label gameTitle;
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
        buttonStart.setText(Values.LABEL_START_BTN);
        setButtons();
        buttonStart.setOnMouseClicked(actionEvent -> {
            try {
                startGame();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void initExitButton(){
        buttonExit.setText(Values.LABEL_EXIT_BTN);
        GUIHelper.setViewDimensions(buttonExit, Values.FOUR_COLS, Values.ONE_ROW);
        buttonExit.setOnMouseClicked(actionEvent -> {
            exitGame();
        });
    }

    private void initCreditsButton(){
        buttonCredits.setText(Values.LABEL_CREDITS_BTN);
        buttonCredits.setOnMouseClicked(actionEvent -> {
            try {
                showCredits();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void initScoresButton(){
        buttonScores.setText(Values.LABEL_SCORES_BTN);
        buttonScores.setOnMouseClicked(actionEvent -> {
            try {
                showScores();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void initSongButton(){
        GUIHelper.styleButton(songBtn);
        if (Song.check){
            songBtn.setText("Sound On");
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
        gameTitle.setFont(Font.font(Values.DEFAULT_FONT, FontWeight.BOLD, Values.H1 - Values.H2));
        GUIHelper.setShadow(gameTitle);
    }

    private void setRiftLabel() {
        menuLeftLabel.setText(Values.LABEL_TEAM_RIFT);
        menuLeftLabel.setTextFill(Color.WHITESMOKE);
        menuLeftLabel.setFont(Font.font(Values.DEFAULT_FONT, FontWeight.BOLD, Values.H2));
        GUIHelper.setShadow(menuLeftLabel);
    }

    private void setSoftUniLabel() {
        menuRightLabel.setText(Values.LABEL_SOFTUNI);
        menuRightLabel.setTextFill(Color.WHITE);
        menuRightLabel.setFont(Font.font(Values.DEFAULT_FONT, FontWeight.BOLD, Values.H3*2));
        GUIHelper.setShadow(menuRightLabel);
    }

    private void setButtons() {
        GUIHelper.setViewDimensions(buttonStart, Values.TWO_COLS, Values.ONE_ROW/2);
        GUIHelper.setViewDimensions(buttonExit, Values.TWO_COLS, Values.ONE_ROW/2);
        GUIHelper.setViewDimensions(buttonCredits, Values.TWO_COLS, Values.ONE_ROW/2);
        GUIHelper.setViewDimensions(buttonScores, Values.TWO_COLS, Values.ONE_ROW/2);
        GUIHelper.styleAsButton(buttonStart,buttonExit,buttonCredits,buttonScores);
    }

    private void initPanes(){
        GUIHelper.setViewDimensions(mainPane, Values.SCREEN_WIDTH, Values.SCREEN_HEIGHT);
        GUIHelper.setViewDimensions(topPane, Values.SCREEN_WIDTH, Values.TWO_ROWS);
        GUIHelper.setViewDimensions(bottomPane, Values.SCREEN_WIDTH, Values.TWO_ROWS);
        GUIHelper.setViewDimensions(leftPane, Values.TWO_COLS, Values.SCREEN_HEIGHT - Values.FOUR_ROWS);
        GUIHelper.setViewDimensions(rightPane, Values.TWO_COLS, Values.SCREEN_HEIGHT - Values.FOUR_ROWS);
        GUIHelper.setViewDimensions(centerPane, Values.SCREEN_WIDTH , Values.SCREEN_HEIGHT);
        GUIHelper.setViewDimensions(mapImage, Values.SCREEN_WIDTH- Values.THREE_COLS, Values.SCREEN_HEIGHT - Values.THREE_ROWS);
        GUIHelper.setBackground(mainPane, Values.SCREEN_WIDTH, Values.SCREEN_HEIGHT);
    }

    private void exitGame() {
        Stage stage = (Stage)buttonExit.getScene().getWindow();
        stage.close();
    }

    private void showCredits() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/credits.fxml"));
        Stage stage = (Stage)buttonCredits.getScene().getWindow();
        stage.setScene(new Scene(root, Values.SCREEN_WIDTH, Values.SCREEN_HEIGHT));
        stage.show();
    }

    private void showScores() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/selectRanking.fxml"));
        Stage stage = (Stage)buttonScores.getScene().getWindow();
        stage.setScene(new Scene(root, Values.SCREEN_WIDTH, Values.SCREEN_HEIGHT));
        stage.show();
    }

    private void startGame() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/cities.fxml"));
        Stage stage = (Stage)buttonStart.getScene().getWindow();
        stage.setScene(new Scene(root, Values.SCREEN_WIDTH, Values.SCREEN_HEIGHT));
        stage.show();
    }

    //    @FXML
//    public void showRankTable(ActionEvent actionEvent) throws IOException{
//        Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/RankTable.fxml"));
//        Stage stage = (Stage)rankBtn.getScene().getWindow();
//        stage.setScene(new Scene(root, Values.SCREEN_WIDTH,Values.SCREEN_HEIGHT));
//        stage.show();
//    }

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

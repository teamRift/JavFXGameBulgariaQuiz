package application.controllers;

import application.classes.GUIHelper;
import application.classes.Song;
import application.constants.ConstantsDimensions;
import application.constants.ConstantsLabel;
import application.constants.ConstantsPath;
import application.constants.ConstantsStyle;
import application.dependencies.DependencyInjectionContainer;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {
    @FXML
    private Button songBtn, buttonExit, buttonCredits, buttonPlayingInstructions, buttonStart, buttonScores;
    @FXML
    private BorderPane mainPane;
    @FXML
    private GridPane topPane, bottomPane, leftPane, rightPane, centerPane;
    @FXML
    private Label gameTitle;
    @FXML
    private ImageView mapImage;
    @FXML
    private Label menuLeftLabel;
    @FXML
    private Label menuRightLabel;

    private Song song = DependencyInjectionContainer.getSongInstance();

    public void initialize() {
        initButtons();
        initPanes();
        initLabels();
    }

    private void initButtons(){
        initPlayingInstructionsButton();
        initCreditsButton();
        initExitButton();
        initScoresButton();
        initStartButton();
        initRankButton();
        initSongButton();
    }

    private void initStartButton(){
        this.buttonStart.setText(ConstantsLabel.LABEL_START_BTN);
        setButtons();
        this.buttonStart.setOnMouseClicked(actionEvent -> {
            try {
                startGame();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void initExitButton(){
        this.buttonExit.setText(ConstantsLabel.LABEL_EXIT_BTN);
        GUIHelper.setViewDimensions(this.buttonExit, ConstantsDimensions.FOUR_COLS, ConstantsDimensions.ONE_ROW);
        this.buttonExit.setOnMouseClicked(actionEvent -> {
            exitGame();
        });
    }

    private void initPlayingInstructionsButton(){
        this.buttonPlayingInstructions.setText(ConstantsLabel.LABEL_PLAYINGINSTRUCTIONS_BTN);
        this.buttonPlayingInstructions.setOnMouseClicked(actionEvent -> {
            try {
                showPlayingInstructions();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    private void initCreditsButton(){
        this.buttonCredits.setText(ConstantsLabel.LABEL_CREDITS_BTN);
        this.buttonCredits.setOnMouseClicked(actionEvent -> {
            try {
                showCredits();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void initScoresButton(){
        this.buttonScores.setText(ConstantsLabel.LABEL_SCORES_BTN);
        this.buttonScores.setOnMouseClicked(actionEvent -> {
            try {
                showScores();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void initSongButton(){
        GUIHelper.styleButton(this.songBtn);
        if (this.song.getCheck()){
            this.songBtn.setText("Sound On");
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
        this.gameTitle.setText(ConstantsLabel.LABEL_GAME_TITLE);
        this.gameTitle.setTextFill(Color.SEAGREEN);
        this.gameTitle.setFont(Font.font(ConstantsStyle.DEFAULT_FONT, FontWeight.BOLD, ConstantsDimensions.H1 - ConstantsDimensions.H2));
        GUIHelper.setShadow(this.gameTitle);
    }

    private void setRiftLabel() {
        this.menuLeftLabel.setText(ConstantsLabel.LABEL_TEAM_RIFT);
        this.menuLeftLabel.setTextFill(Color.WHITESMOKE);
        this.menuLeftLabel.setFont(Font.font(ConstantsStyle.DEFAULT_FONT, FontWeight.BOLD, ConstantsDimensions.H2));
        GUIHelper.setShadow(this.menuLeftLabel);
    }

    private void setSoftUniLabel() {
        this.menuRightLabel.setText(ConstantsLabel.LABEL_SOFTUNI);
        this.menuRightLabel.setTextFill(Color.WHITE);
        this.menuRightLabel.setFont(Font.font(ConstantsStyle.DEFAULT_FONT, FontWeight.BOLD, ConstantsDimensions.H3*2));
        GUIHelper.setShadow(this.menuRightLabel);
    }

    private void setButtons() {
        GUIHelper.setViewDimensions(this.buttonStart, ConstantsDimensions.TWO_COLS, ConstantsDimensions.ONE_ROW/2);
        GUIHelper.setViewDimensions(this.buttonExit, ConstantsDimensions.TWO_COLS, ConstantsDimensions.ONE_ROW/2);
        GUIHelper.setViewDimensions(this.buttonPlayingInstructions, ConstantsDimensions.TWO_COLS, ConstantsDimensions.ONE_ROW/2);
        GUIHelper.setViewDimensions(this.buttonCredits, ConstantsDimensions.TWO_COLS, ConstantsDimensions.ONE_ROW/2);
        GUIHelper.setViewDimensions(this.buttonScores, ConstantsDimensions.TWO_COLS, ConstantsDimensions.ONE_ROW/2);
        GUIHelper.styleAsButton(this.buttonStart, this.buttonExit, this.buttonCredits, this.buttonScores);
    }

    private void initPanes(){
        GUIHelper.setViewDimensions(this.mainPane, ConstantsDimensions.SCREEN_WIDTH, ConstantsDimensions.SCREEN_HEIGHT);
        GUIHelper.setViewDimensions(this.topPane, ConstantsDimensions.SCREEN_WIDTH, ConstantsDimensions.TWO_ROWS);
        GUIHelper.setViewDimensions(this.bottomPane, ConstantsDimensions.SCREEN_WIDTH, ConstantsDimensions.TWO_ROWS);
        GUIHelper.setViewDimensions(this.leftPane, ConstantsDimensions.TWO_COLS, ConstantsDimensions.SCREEN_HEIGHT - ConstantsDimensions.FOUR_ROWS);
        GUIHelper.setViewDimensions(this.rightPane, ConstantsDimensions.TWO_COLS, ConstantsDimensions.SCREEN_HEIGHT - ConstantsDimensions.FOUR_ROWS);
        GUIHelper.setViewDimensions(this.centerPane, ConstantsDimensions.SCREEN_WIDTH , ConstantsDimensions.SCREEN_HEIGHT);
        GUIHelper.setViewDimensions(this.mapImage, ConstantsDimensions.SCREEN_WIDTH- ConstantsDimensions.THREE_COLS, ConstantsDimensions.SCREEN_HEIGHT - ConstantsDimensions.THREE_ROWS);
        GUIHelper.setBackground(this.mainPane, ConstantsDimensions.SCREEN_WIDTH, ConstantsDimensions.SCREEN_HEIGHT);
    }

    private void exitGame() {
        Stage stage = (Stage)this.buttonExit.getScene().getWindow();
        stage.close();
    }

    private void showPlayingInstructions() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(ConstantsPath.PATH_TO_PLAYING_INSTRUCTION));
        Stage stage = (Stage)this.buttonPlayingInstructions.getScene().getWindow();
        stage.setScene(new Scene(root, ConstantsDimensions.SCREEN_WIDTH, ConstantsDimensions.SCREEN_HEIGHT));
        stage.show();
    }

    private void showCredits() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(ConstantsPath.PATH_TO_CREDITS));
        Stage stage = (Stage)this.buttonCredits.getScene().getWindow();
        stage.setScene(new Scene(root, ConstantsDimensions.SCREEN_WIDTH, ConstantsDimensions.SCREEN_HEIGHT));
        stage.show();
    }

    private void showScores() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(ConstantsPath.PATH_TO_SELECT_RANKING));
        Stage stage = (Stage)this.buttonScores.getScene().getWindow();
        stage.setScene(new Scene(root, ConstantsDimensions.SCREEN_WIDTH, ConstantsDimensions.SCREEN_HEIGHT));
        stage.show();
    }

    private void startGame() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(ConstantsPath.PATH_TO_CITIES));
        Stage stage = (Stage)this.buttonStart.getScene().getWindow();
        stage.setScene(new Scene(root, ConstantsDimensions.SCREEN_WIDTH, ConstantsDimensions.SCREEN_HEIGHT));
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
        if (this.song.getCheck()){
            this.song.start();
            this.songBtn.setText("Song Off");
        } else {
            this.song.pause();
            this.songBtn.setText("Song On");
        }
    }
}

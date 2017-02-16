package application.controllers;

import application.classes.*;
import com.sun.javafx.font.freetype.HBGlyphLayout;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    @FXML
    Label gameTitle;
    @FXML
    Label exitBtn;
    @FXML
    Label creditsBtn;
    @FXML
    Label startBtn;
    @FXML
    HBox startGameBox,scoresBox,creditsBox,exitBox;
    @FXML
    Label scoresBtn;
    @FXML
    Button rankBtn;
    @FXML
    BorderPane mainPane;
    @FXML
    GridPane topPane;
    @FXML
    GridPane leftPane;
    @FXML
    GridPane rightPane;
    @FXML
    GridPane bottomPane;
    @FXML
    GridPane centerPane;
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

        setButtons();

        setPanes();

        setLabels();

//        Score mock = new Score("VARNA", "AS", 310);
//
//        Scores.save(mock);
    }

    private void setButtons(){


        scoresBtn.setText(Values.LABEL_SCORES_BTN);

        scoresBtn.setOnMouseClicked(actionEvent -> {

            try {

                showScores();

            } catch (IOException e) {

                e.printStackTrace();

            }

        });
        creditsBtn.setText(Values.LABEL_CREDITS_BTN);

        creditsBtn.setOnMouseClicked(actionEvent -> {

            try {

                showCredits();

            } catch (IOException e) {

                e.printStackTrace();

            }

        });

        rankBtn.setText(Values.LABEL_RANKTABLE_BTN);
        rankBtn.setMaxWidth(Values.FOUR_COLS);
        rankBtn.setMinWidth(Values.FOUR_COLS);
        rankBtn.setPrefWidth(Values.FOUR_COLS);
        rankBtn.setMaxHeight(Values.ONE_ROW/2);
        rankBtn.setOnAction((ActionEvent actionEvent) ->{
            try{
                showRankTable(actionEvent);
            }catch(IOException e){
                e.printStackTrace();
            }
        });

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

        exitBtn.setText(Values.LABEL_EXIT_BTN);

        Utils.setSize(exitBtn, Values.FOUR_COLS, Values.ONE_ROW);

        exitBtn.setOnMouseClicked(actionEvent -> {

                exitGame();

            gameManager.setCurrentUser(inputUserName.getText());

        });
    }
    
    private void setLabels() {

        setGameLabel();

        setRiftLabel();

        setSoftUniLabel();

    }

    private void setGameLabel() {

        gameTitle.setText(Values.LABEL_GAME_TITLE);

        gameTitle.setTextFill(Color.SEAGREEN);

        gameTitle.setFont(Font.font(Values.DEFAULT_FONT, FontWeight.BOLD,70));

        Utils.setShadow(gameTitle);
    }

    private void setRiftLabel() {

        menuLeftLabel.setText(Values.LABEL_TEAM_RIFT);

        menuLeftLabel.setTextFill(Color.WHITESMOKE);

        menuLeftLabel.setFont(Font.font(Values.DEFAULT_FONT, FontWeight.BOLD,32));

        Utils.setShadow(menuLeftLabel);

    }

    private void setSoftUniLabel() {

        menuRightLabel.setText(Values.LABEL_SOFTUNI);

        menuRightLabel.setTextFill(Color.WHITE);

        menuRightLabel.setFont(Font.font(Values.DEFAULT_FONT, FontWeight.BOLD,32));

        Utils.setShadow(menuRightLabel);
    }

    private void setStartButton(){

        inputUserName.setFont(Font.font(Values.DEFAULT_FONT, FontWeight.BOLD, FontPosture.ITALIC, 25));
        gameManager.setUserName(inputUserName);
        inputUserName.setBackground(Background.EMPTY);

        Utils.setSize(startBtn, Values.ONE_COL*1.5, Values.ONE_ROW);
        Utils.setSize(exitBtn, Values.ONE_COL*1.5, Values.ONE_ROW);
        Utils.setSize(creditsBtn, Values.ONE_COL*1.5, Values.ONE_ROW);
        Utils.setSize(scoresBtn, Values.ONE_COL*1.5, Values.ONE_ROW);

        Utils.setSize(startGameBox,Values.THREE_COLS,Values.ONE_ROW);
        Utils.setSize(scoresBox,Values.THREE_COLS,Values.ONE_ROW);
        Utils.setSize(creditsBox,Values.THREE_COLS,Values.ONE_ROW);
        Utils.setSize(exitBox,Values.THREE_COLS,Values.ONE_ROW);

        Utils.makeItLookLikeButton(startBtn,exitBtn,creditsBtn,scoresBtn);

        Utils.makeItLookLikeButton(startGameBox,scoresBox,creditsBox,exitBox);
    }

    private void setPanes(){

        Utils.setSize(mainPane, Values.SCREEN_WIDTH, Values.SCREEN_HEIGHT);

        Utils.setSize(topPane, Values.SCREEN_WIDTH, Values.TWO_ROWS);

        Utils.setSize(bottomPane, Values.SCREEN_WIDTH, Values.TWO_ROWS);

        Utils.setSize(leftPane, Values.TWO_COLS, Values.SCREEN_HEIGHT - Values.FOUR_ROWS);

        Utils.setSize(rightPane, Values.TWO_COLS, Values.SCREEN_HEIGHT - Values.FOUR_ROWS);

        Utils.setSize(centerPane, Values.SCREEN_WIDTH , Values.SCREEN_HEIGHT);

        Utils.setSize(mapImage, Values.SCREEN_WIDTH- Values.THREE_COLS, Values.SCREEN_HEIGHT - Values.THREE_ROWS);

        Utils.setBackground(mainPane, Values.SCREEN_WIDTH,Values.SCREEN_HEIGHT);

    }
    @FXML
    public void showRankTable(ActionEvent actionEvent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/RankTable.fxml"));
        Stage stage = (Stage)rankBtn.getScene().getWindow();
        stage.setScene(new Scene(root, Values.SCREEN_WIDTH,Values.SCREEN_HEIGHT));
        stage.show();
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
}

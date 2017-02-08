package application.controllers;

import application.classes.Utils;
import application.classes.Values;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

import static application.controllers.CitiesController.gameManager;

public class MenuController {

    @FXML
    public Button exitBtn;
    @FXML
    public Button startBtn;
    @FXML
    public Button scoresBtn;
    @FXML
    public AnchorPane mainPane;

    @FXML
    public TextField inputUserName;
    @FXML
    VBox buttonsGroup;
    @FXML
    public GridPane topPane;
    @FXML
    ImageView background;
    @FXML
    private void initBackground(){
        background.setImage(new Image("application/resources/images/bulgaria1.png",
                Values.SCREEN_WIDTH- Values.ONE_COL,
                Values.SCREEN_HEIGHT - Values.ONE_ROW,
                true,
                true));
    }

    private void initButtons(){

        scoresBtn.setText(Values.LABEL_SCORES_BTN);
        scoresBtn.setMaxWidth(Values.FOUR_COLS);
        scoresBtn.setMinWidth(Values.FOUR_COLS);
        scoresBtn.setPrefWidth(Values.FOUR_COLS);
        scoresBtn.setMaxHeight(Values.ONE_ROW/2);
        scoresBtn.setOnAction((ActionEvent actionEvent) -> {
            try {
                showScores(actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        startBtn.setText(Values.LABEL_START_BTN);
        initStartButton();
        startBtn.setOnAction((ActionEvent actionEvent) -> {
            try {
                startGame(actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
            gameManager.setCurrentUser(inputUserName.getText());
        });

        exitBtn.setText(Values.LABEL_EXIT_BTN);
        exitBtn.setMaxWidth(Values.FOUR_COLS);
        exitBtn.setMinWidth(Values.FOUR_COLS);
        exitBtn.setPrefWidth(Values.FOUR_COLS);
        exitBtn.setMaxHeight(Values.ONE_ROW/2);
        exitBtn.setOnAction(this::exitGame);
    }

    // set dimmensions of start button and input field
    private void initStartButton(){
        inputUserName.setMaxHeight(Values.ONE_ROW/1.3);
        inputUserName.setMaxWidth(Values.THREE_COLS);
        inputUserName.setMinWidth(Values.THREE_COLS);
        exitBtn.setPrefWidth(Values.TWO_COLS);
        startBtn.setMaxHeight(Values.ONE_ROW/2);
        startBtn.setMaxWidth(Values.TWO_COLS);
        exitBtn.setMinWidth(Values.TWO_COLS);
        exitBtn.setPrefWidth(Values.TWO_COLS);

    }

    public void initialize(){
        initBackground();
        initButtons();
    }
    @FXML
    public void exitGame(ActionEvent event) {
        Stage stage = (Stage)exitBtn.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void showScores(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/rankings.fxml"));
        Stage stage = (Stage)scoresBtn.getScene().getWindow();
        stage.setScene(new Scene(root, Values.SCREEN_WIDTH,Values.SCREEN_HEIGHT));
        stage.show();
    }
    @FXML
    public void startGame(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/cities.fxml"));
        Stage stage = (Stage)startBtn.getScene().getWindow();
        stage.setScene(new Scene(root, Values.SCREEN_WIDTH,Values.SCREEN_HEIGHT));
        stage.show();
    }

}

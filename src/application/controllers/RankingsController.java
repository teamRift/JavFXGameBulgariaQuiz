package application.controllers;

import application.classes.GameManager;
import application.classes.Score;
import application.classes.Utils;
import application.classes.Values;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.List;


import static application.controllers.BootController.gameManager;


public class RankingsController {
    @FXML
    public Label rankingsTitle;
    @FXML
    public Label rankingsCurrent;
    @FXML
    public Label rankingsFirst;
    @FXML
    public Label rankingsSecond;
    @FXML
    public Label rankingsThird;
    @FXML
    public Label rankingsFourth;
    @FXML
    public Label rankingsFifth;
    @FXML
    public Label hintLabel;
    @FXML
    public StackPane mainPane;
    @FXML
    public ImageView background;
    @FXML
    Button backBtn;

    public void initialize() throws Exception {

        initCurrent();

        loadScores();

        Utils.setBackground(mainPane, Values.SCREEN_WIDTH, Values.SCREEN_HEIGHT);

        Utils.setSize(background, Values.SCREEN_WIDTH- Values.THREE_COLS, Values.SCREEN_HEIGHT- Values.THREE_ROWS);

        Utils.styleLabel(35,rankingsCurrent,rankingsFirst,rankingsSecond,rankingsThird,rankingsFourth,rankingsFifth);

        Utils.styleLabel(50,rankingsTitle);

        Utils.styleLabel(25,hintLabel);

        initBackButton();
    }

    public void OnBack(ActionEvent actionEvent) {

        Parent root = null;

        try {

            root = FXMLLoader.load(getClass().getResource("../resources/fxml/menu.fxml"));

        } catch (IOException e) {

            e.printStackTrace();

        }


        Stage stage = (Stage)backBtn.getScene().getWindow();

        stage.setScene(new Scene(root, Values.SCREEN_WIDTH,Values.SCREEN_HEIGHT));

        stage.show();

    }

    private void loadScores() {
        List<Score> mScores = gameManager.getScores();
        rankingsTitle.setText(Values.LABEL_RANKINGS_TOP_5);

        //if less than 5 entries in ranks will throw array lenght error
        //to do fix
        for (int i = 5 - mScores.size(); i > 0; i--) {
            mScores.set(mScores.size(), new Score("---","---",0));
        }

        rankingsFirst.setText(Utils.getRanking(mScores.get(0).prepareSave()));
        rankingsSecond.setText(Utils.getRanking(mScores.get(1).prepareSave()));
        rankingsThird.setText(Utils.getRanking(mScores.get(2).prepareSave()));
        rankingsFourth.setText(Utils.getRanking(mScores.get(3).prepareSave()));
        rankingsFifth.setText(Utils.getRanking(mScores.get(4).prepareSave()));

        //Поле с интересна информация
        //В последствие може да се направи файл ако някой иска да се
        // занимае от който да се четат факти и да се зареждат тук
        // идеята е да го има на всеки екран освен boot.fxml
        // Фронтенд да се пипне на това цялото view
        gameManager.setFactsLabel(hintLabel);
    }

    private void initBackButton(){
        backBtn.setOnAction(this::OnBack);
        backBtn.setText(Values.LABEL_BACK_BTN);
        Utils.setSize(backBtn, Values.ONE_COL, Values.ONE_ROW);
        Utils.styleButton(backBtn);
    }
    private void initCurrent(){
        if (GameManager.hasPlayed){
            rankingsCurrent.setText(String.format("%s %s %s",
                    gameManager.getCityName(),
                    gameManager.getCurrentUser(),
                    gameManager.getCurrentUserPoints()));
            GameManager.hasPlayed = false;
        } else {
            rankingsCurrent.setVisible(false);
        }
    }
}

package application.controllers;

import application.classes.Score;
import application.classes.Scores;
import application.classes.Values;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

import java.util.List;

public class RankingsController {
    @FXML
    public Label rankingsTitle;
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
    Label textLabel;
    @FXML
    public void initialize() throws Exception {
        loadScores();
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished( event -> System.out.println("Started."));
        delay.play();
    }

    private void loadScores() {
        List<Score> scores = null;
        try {
            scores = Scores.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int length = scores.size();
        if (length>5){
            length = 5;
        }
        scores = scores.subList(0,length);
        for (Score score : scores) {
            System.out.println(score.prepareSave());
        }
        rankingsTitle.setText(Values.LABEL_RANKINGS_TOP_5);

        //if less than 5 entries in ranks will throw array lenght error
        //to do fix

        rankingsFirst.setText(scores.get(0).prepareSave());
        rankingsSecond.setText(scores.get(1).prepareSave());
        rankingsThird.setText(scores.get(2).prepareSave());
        rankingsFourth.setText(scores.get(3).prepareSave());
        rankingsFifth.setText(scores.get(4).prepareSave());

        //Поле с интересна информация
        //В последствие може да се направи файл ако някой иска да се
        // занимае от който да се четат факти и да се зареждат тук
        // идеята е да го има на всеки екран освен boot.fxml
        // винаги може да се махне
        // Фронтенд да се пипне на това цялото view

        hintLabel.setText("Знаете ли че на дъното на Черно море лежат останки от град, изчезнал преди 10 хиляди години?");
        hintLabel.setTextFill(Paint.valueOf("#2dad2e"));
        hintLabel.setStyle("-fx-background-color: #000000;");    }
}

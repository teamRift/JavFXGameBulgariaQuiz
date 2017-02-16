package application.controllers;

import application.classes.Score;
import application.classes.Scores;
import application.classes.Utils;
import application.classes.Values;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

import javax.jws.soap.SOAPBinding;
import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static application.controllers.BootController.gameManager;

public class RankTableController {
    @FXML
    public Pane GoldenPane;
    @FXML
    public Pane SilverPane;
    @FXML
    public Pane BronzePane;
    @FXML
    public Label GoldenLabel;
    @FXML
    public Label SilverLabel;
    @FXML
    public Label BronzeLabel;



    public void initialize() throws Exception {
        loadScores();
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> System.out.println("Started."));
        delay.play();
    }

    private void loadScores() {

        List<Score> mScores = gameManager.getScores();

        GoldenLabel.setText(Utils.getRanking(mScores.get(0).prepareSave()));
        SilverLabel.setText(Utils.getRanking(mScores.get(1).prepareSave()));
        BronzeLabel.setText(Utils.getRanking(mScores.get(2).prepareSave()));

        }


    }



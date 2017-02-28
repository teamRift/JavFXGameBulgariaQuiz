package application.controllers;

import application.classes.GameManager;
import application.classes.Score;
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
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalTime;
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

        setBackground(mainPane, Values.SCREEN_WIDTH, Values.SCREEN_HEIGHT);

        setSize(background, Values.SCREEN_WIDTH- Values.THREE_COLS, Values.SCREEN_HEIGHT- Values.THREE_ROWS);

        styleLabel(35,rankingsCurrent,rankingsFirst,rankingsSecond,rankingsThird,rankingsFourth,rankingsFifth);

        styleLabel(50,rankingsTitle);

        styleLabel(25,hintLabel);

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

        rankingsFirst.setText(getRanking(mScores.get(0).prepareSave()));
        rankingsSecond.setText(getRanking(mScores.get(1).prepareSave()));
        rankingsThird.setText(getRanking(mScores.get(2).prepareSave()));
        rankingsFourth.setText(getRanking(mScores.get(3).prepareSave()));
        rankingsFifth.setText(getRanking(mScores.get(4).prepareSave()));

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
        setSize(backBtn, Values.ONE_COL, Values.ONE_ROW);
        styleButton(backBtn);
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
    public static String capitalize(String input){
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }
    public static void log(Object object, String input) {

        System.out.printf("%s : %s.class %s.\n",
                object.getClass().getSimpleName(),
                LocalTime.now(),
                input);

    }
    public static void setBackground(Pane pane, double WIDTH, double HEIGHT) {

        BackgroundImage myBI = new BackgroundImage(new Image(Values.IMG_BACKGROUND,WIDTH,HEIGHT,false,true),
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        pane.setBackground(new Background(myBI));

    }
    public static void styleLabel(int size, Label... labels) {
        for (Label label : labels) {
            label.setFont(Font.font(Values.DEFAULT_FONT, FontWeight.BOLD,size));

            label.setTextFill(Color.WHITE);

            setShadow(label);

        }
    }
    public static void setSize(Object object, double WIDTH, double HEIGHT) {

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
    public static void setShadow(Object object) {

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
    public static void styleButton(Button... buttons) {

        for (Button button : buttons) {

            button.setBackground(new Background(new BackgroundFill(Paint.valueOf("#FFFFFF"),new CornerRadii(7), new Insets(5,5,5,5))));

            button.setFont(Font.font(Values.DEFAULT_FONT,FontWeight.BOLD, Values.H3));

            button.setTextFill(Color.BLACK);

            setShadow(button);

        }

    }
    public static String getRanking(String input) {
        return String.format("%s %s %s",
                input.split(";")[0],
                input.split(";")[1],
                input.split(";")[2]
        );
    }

}

package application.controllers;

import application.classes.City;
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

import java.io.IOException;
import java.time.LocalTime;

import static application.controllers.BootController.gameManager;

public class CitiesController {
    @FXML
    public Label labelMaxRecord;
    @FXML
    public Label labelUserRecord;
    @FXML
    public  Label labelUserName;
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

    public void initialize() throws IOException {

        setPanes();

        setButtons();

        setLabels();

    }
    public void onCityQuestion( ActionEvent actionEvent) throws IOException {

        Button button  = (Button)  actionEvent.getSource();

        String id = button.getId();

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
    private void setButtons(){

        styleCityButton(varna,ruse,sofia,burgas,blagoevgrad,plovdiv,pleven,velikoturnovo);

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

        styleButton(backButton);
        setSize(backButton,Values.ONE_COL, Values.ONE_ROW);

        backButton.setText(Values.LABEL_BACK_BTN);
    }
    private void setLabels() {

        gameManager.setFactsLabel(hintLabel);

        styleLabel(Values.H3,hintLabel);

        labelChooseCity.setText(Values.LABEL_CHOOSE_CITY_BTN);

        styleLabel(Values.H2,labelChooseCity);

    }
    private void setCity(String city) {
        City choosenCity = new City();
        choosenCity.setName(city);
        choosenCity.setFileName();
        gameManager.setCity(choosenCity);
    }
    private void setPanes(){

        setSize(mainPane, Values.SCREEN_WIDTH, Values.SCREEN_HEIGHT);

        setSize(topPane, Values.SCREEN_WIDTH, Values.THREE_ROWS);

        setSize(bottomPane, Values.SCREEN_WIDTH, Values.TWO_ROWS);

        setSize(leftPane, Values.THREE_COLS, Values.SCREEN_HEIGHT - Values.FOUR_ROWS);

        setSize(rightPane, Values.THREE_COLS, Values.SCREEN_HEIGHT - Values.FOUR_ROWS);

        setSize(mapPane, Values.SIX_COLS, Values.SIX_ROWS + Values.THREE_ROWS);

        setBackground(mainPane, Values.SCREEN_WIDTH,Values.SCREEN_HEIGHT);

        mapImg.setFitWidth(Values.SCREEN_WIDTH);
        mapImg.setFitWidth(Values.SCREEN_HEIGHT);
    }
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
    private static void styleLabel(int size, Label... labels) {
        for (Label label : labels) {
            label.setFont(Font.font(Values.DEFAULT_FONT,FontWeight.BOLD,size));

            label.setTextFill(Color.WHITE);

            setShadow(label);

        }
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
    private static void styleCityButton(Button... buttons) {

        for (Button button : buttons) {

            button.setBackground(Background.EMPTY);

            button.setFont(Font.font(Values.DEFAULT_FONT,FontWeight.BOLD,Values.H3));

            button.setTextFill(Color.WHITESMOKE);

            setShadow(button);

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

    public void OnHard(ActionEvent actionEvent) throws IOException {

    }

    public void OnNormal(ActionEvent actionEvent) {
    }

    public void OnEasy(ActionEvent actionEvent) {
    }
}

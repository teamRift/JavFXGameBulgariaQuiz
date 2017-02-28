package application.controllers;

import application.classes.Values;
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

public class CreditsController {

    @FXML
    Button backBtn;
    @FXML
    public Label gameTitle;
    @FXML
    public Label leftLabel;
    @FXML
    public Label rightLabel;
    @FXML
    private BorderPane mainPane;
    @FXML
    private GridPane teamRiftBox;
    @FXML
    private GridPane topPane;
    @FXML
    private GridPane leftPane;
    @FXML
    private GridPane bottomPane;
    @FXML
    private GridPane rightPane;
    @FXML
    private Label creditsL1;
    @FXML
    private Label creditsL2;
    @FXML
    private Label creditsL3;
    @FXML
    private Label creditsL4;
    @FXML
    private Label creditsR1;
    @FXML
    private Label creditsR2;
    @FXML
    private Label creditsR3;
    @FXML
    private Label creditsR4;


    public void initialize(){

        setLabels();

        setPanes();

        setButtons();

    }

    private void setButtons() {

        styleButton(backBtn);

        setSize(backBtn, Values.ONE_COL, Values.ONE_ROW);
        backBtn.setText(Values.LABEL_BACK_BTN);
        backBtn.setOnAction((actionEvent) -> {

            try {

                onBack();

            } catch (IOException e) {

                e.printStackTrace();

            }

        });

    }

    private void setPanes(){

        setBackground(mainPane, Values.SCREEN_WIDTH, Values.SCREEN_HEIGHT);

        setSize(mainPane, Values.SCREEN_WIDTH, Values.SCREEN_HEIGHT);

        setSize(teamRiftBox, Values.SCREEN_WIDTH - Values.FOUR_COLS, Values.SCREEN_HEIGHT - Values.FOUR_ROWS);

        setSize(topPane, Values.SCREEN_WIDTH, Values.TWO_ROWS);

        setSize(bottomPane, Values.SCREEN_WIDTH, Values.TWO_ROWS);

        setSize(leftPane, Values.TWO_COLS, Values.SCREEN_HEIGHT - Values.FOUR_ROWS);

        setSize(rightPane, Values.TWO_COLS, Values.SCREEN_HEIGHT - Values.FOUR_ROWS);

    }

    private void setLabels() {

        styleLabel(50,gameTitle);

        gameTitle.setText(Values.LABEL_GAME_TITLE);

            styleLabel(32, leftLabel, rightLabel);

        leftLabel.setText(Values.LABEL_TEAM_RIFT);

        rightLabel.setText(Values.LABEL_SOFTUNI);



        creditsL1.setText("@Alish");

        creditsR1.setText("@asyadi");

        creditsL2.setText("@AngelD");

        creditsR2.setText("@Cvetan1");

        creditsL3.setText("@daniel.a.mihaylоv");

        creditsR3.setText("@Daniela.Raycheva");

        creditsL4.setText("@koushalieva");

        creditsR4.setText("@mariyanhadzhiev");

        styleLabel(25,creditsL1,creditsL2,creditsL3,creditsL4,creditsR1,creditsR2,creditsR3,creditsR4);

    }

    public void onBack() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/menu.fxml"));

        Stage stage = ( Stage ) backBtn.getScene().getWindow();

        stage.setScene( new Scene( root, Values.SCREEN_WIDTH, Values.SCREEN_HEIGHT));

        stage.show();
    }
    public static String capitalize(String input){
        return input.substring(0, 1).toUpperCase() + input.substring(1);
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
}


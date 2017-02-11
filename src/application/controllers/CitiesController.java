package application.controllers;

import application.classes.GameManager;
import application.classes.Utils;
import application.classes.cities.*;
import application.classes.Values;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;

import static application.controllers.BootController.gameManager;

public class CitiesController {
    @FXML
    Button backButton;
    @FXML
    BorderPane mainPane;
    @FXML
    GridPane topPane;
    @FXML
    GridPane rightPane;
    @FXML
    GridPane bottomPane;
    @FXML
    GridPane leftPane;
    @FXML
    GridPane centerPane;
    @FXML
    Pane mapPane;
    @FXML
    ImageView mapImg;
    @FXML
    Button varna;
    @FXML
    Button ruse;
    @FXML
    Button sofia;
    @FXML
    Button burgas;
    @FXML
    Button blagoevgrad;
    @FXML
    Button plovdiv;
    @FXML
    Button pleven;
    @FXML
    Button turnovo;
    @FXML
    Label hintLabel;
    @FXML
    Label gameTitle;

    public void initialize() throws IOException {
        setPanes();
        initHint();
        Utils.styleCityButton(varna,ruse,sofia,burgas,blagoevgrad,plovdiv,pleven,turnovo);
        Utils.styleButton(backButton);
        backButton.setText(Values.LABEL_BACK_BTN);
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

    private void initHint() {

        gameManager.setFactsLabel(hintLabel);

        hintLabel.setTextFill(Color.WHITE);

        hintLabel.setFont(Font.font(Values.DEFAULT_FONT, FontWeight.BOLD,25));

        Utils.setShadow(hintLabel);

    }

    private void setCity(String city){
        if (city.equalsIgnoreCase("sofia")){
            gameManager.setCity(new Sofia());
        } else if (city.equalsIgnoreCase("varna")) {
            gameManager.setCity(new Varna());
        }  else if (city.equalsIgnoreCase("plovdiv")) {
            gameManager.setCity(new Plovdiv());
        }  else if (city.equalsIgnoreCase("burgas")) {
            gameManager.setCity(new Burgas());
        }  else if (city.equalsIgnoreCase("turnovo")) {
            gameManager.setCity(new VelikoTurnovo());
        }  else if (city.equalsIgnoreCase("pleven")) {
            gameManager.setCity(new Pleven());
        }  else if (city.equalsIgnoreCase("ruse")) {
            gameManager.setCity(new Ruse());
        }  else if (city.equalsIgnoreCase("blagoevgrad")) {
            gameManager.setCity(new Blagoevgrad());
        } else {
            gameManager.setCity(new Mordor());
        }
    }

    private void setPanes(){
        Utils.setSize(mainPane, Values.SCREEN_WIDTH, Values.SCREEN_HEIGHT);
        Utils.setSize(topPane, Values.SCREEN_WIDTH, Values.TWO_ROWS);
        Utils.setSize(bottomPane, Values.SCREEN_WIDTH, Values.TWO_ROWS);
        Utils.setSize(leftPane, Values.TWO_COLS, Values.SCREEN_HEIGHT - Values.FOUR_ROWS);
        Utils.setSize(rightPane, Values.TWO_COLS, Values.SCREEN_HEIGHT - Values.FOUR_ROWS);
        Utils.setSize(centerPane, Values.SCREEN_WIDTH  - Values.FOUR_COLS, Values.SCREEN_HEIGHT- Values.FOUR_COLS);
        Utils.setBackground(mainPane, Values.SCREEN_WIDTH,Values.SCREEN_HEIGHT);
    }

}

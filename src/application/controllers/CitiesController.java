package application.controllers;

import application.classes.GameManager;
import application.classes.cities.*;
import application.classes.Values;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class CitiesController {
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
    Pane centerPane;
    @FXML
    Hyperlink varna;
    @FXML
    Hyperlink ruse;
    @FXML
    Hyperlink sofia;
    @FXML
    Hyperlink burgas;
    @FXML
    Hyperlink blagoevgrad;
    @FXML
    Hyperlink plovdiv;
    @FXML
    Hyperlink pleven;
    @FXML
    Hyperlink turnovo;

    public static GameManager gameManager;

    @FXML
    public void initialize() throws IOException {
        setPanes();
        initCityManager();
    }

    private static void initCityManager() {
        gameManager = new GameManager();
    }

    public void onCityQuestion(ActionEvent actionEvent) throws IOException {
        Hyperlink button  = (Hyperlink)  actionEvent.getSource();
        String id = button.getId();
        setCity(id.toLowerCase());
        Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/quizWindow.fxml"));

        Stage stage = (Stage)button.getScene().getWindow();
        stage.setScene(new Scene(root, Values.SCREEN_WIDTH,Values.SCREEN_HEIGHT));
        stage.show();
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
        topPane.setMinHeight(Values.SCREEN_HEIGHT/4);
        topPane.setPrefHeight(Values.SCREEN_HEIGHT/4);
        bottomPane.setMinHeight(Values.SCREEN_HEIGHT/4);
        bottomPane.setPrefHeight(Values.SCREEN_HEIGHT/4);
        leftPane.setPrefWidth(Values.SCREEN_WIDTH/7);
        rightPane.setPrefWidth(Values.SCREEN_WIDTH/8);
        mainPane.setPrefWidth(Values.SCREEN_WIDTH);
        mainPane.setPrefHeight(Values.SCREEN_HEIGHT);
        centerPane.setPrefWidth(Values.SCREEN_WIDTH - (3  * (Values.SCREEN_WIDTH / 6)));
        centerPane.setPrefHeight(Values.SCREEN_HEIGHT - (3  * (Values.SCREEN_HEIGHT / 6)));
    }
}

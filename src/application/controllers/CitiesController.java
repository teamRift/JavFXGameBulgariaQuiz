package application.controllers;

import application.classes.GameManager;
import application.classes.Utils;
import application.classes.cities.*;
import application.classes.Values;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

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
    @FXML
    Label hintLabel;

    public static GameManager gameManager;

    @FXML
    public void initialize() throws IOException {
        setPanes();
        initCityManager();
        initHint();
        gameManager.setFactsLabel(hintLabel);
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

    public void OnBack(ActionEvent actionEvent){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to go back ?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("../resources/fxml/menu.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = (Stage)backButton.getScene().getWindow();
            stage.setScene(new Scene(root, Values.SCREEN_WIDTH,Values.SCREEN_HEIGHT));
            stage.show();
        }
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

    private void initHint() {
        hintLabel.setTextFill(Paint.valueOf("#2dad2e"));
        hintLabel.setStyle("-fx-background-color: #000000;");
    }

    private void initCityManager() {
        gameManager = new GameManager();
    }

}

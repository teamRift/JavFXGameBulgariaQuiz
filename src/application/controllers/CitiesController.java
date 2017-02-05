package application.controllers;

import application.classes.City;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class CitiesController {
    @FXML
    Hyperlink varna;
    @FXML
    Hyperlink vidin;
    @FXML
    Hyperlink russe;
    @FXML
    Hyperlink sofia;
    @FXML
    Hyperlink bourgas;
    @FXML
    Hyperlink blagoevgrad;
    @FXML
    Hyperlink plovdiv;
    @FXML
    Hyperlink kyrdzhali;
    @FXML
    Hyperlink pleven;
    @FXML
    Hyperlink silistra;
    @FXML
    Hyperlink tyrnovo;
    @FXML
    Hyperlink zagora;
    @FXML
    Hyperlink montana;

    static ArrayList<City> cities;


    public void onCityQuestion(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("../quizWindow.fxml"));

//        String sourceID = actionEvent.getSource().toString();
//        sourceID = sourceID.substring(sourceID.indexOf('=') + 1, sourceID.indexOf(','));
//        String cityName = sourceID;

        Stage stage = (Stage)sofia.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}

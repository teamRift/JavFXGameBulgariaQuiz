package application.controllers;

import application.classes.Values;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    public Button exit;
    @FXML
    public AnchorPane mainPane;
    @FXML
    public GridPane buttonGroupPane;
    @FXML
    public GridPane topPane;
    @FXML
    ImageView background;
    @FXML
    Button StartBtn;
    @FXML
    public void initialize(){
        background.setImage(new Image("application/resources/images/bulgaria1.png", Values.SCREEN_WIDTH, Values.SCREEN_HEIGHT, false,false));
        buttonGroupPane.setMinWidth(Values.SCREEN_WIDTH/4);
        buttonGroupPane.setMinHeight(Values.SCREEN_HEIGHT/4);
    }
    public void ExitBtn(ActionEvent actionEvent) {
        Stage stage = (Stage)exit.getScene().getWindow();
        stage.close();
    }

    public void getStart(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/cities.fxml"));
        Stage stage = (Stage)StartBtn.getScene().getWindow();
        stage.setScene(new Scene(root, Values.SCREEN_WIDTH,Values.SCREEN_HEIGHT));
        stage.show();
    }

}

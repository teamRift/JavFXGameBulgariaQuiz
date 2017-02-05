package application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    @FXML
    Button StartBtn;

    public void ExitBtn(ActionEvent actionEvent) {

    }

    public void getStart(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../cities.fxml"));
        Stage stage = (Stage)StartBtn.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}

package application.controllers;

import application.classes.Score;
import application.classes.Scores;
import application.classes.Values;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static application.controllers.BootController.gameManager;


public class RankingsController {
    @FXML
    public Label rankingsTitle;
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
    @FXML
    public void initialize() throws Exception {
        loadScores();
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished( event -> System.out.println("Started."));
        delay.play();
        backBtn.setOnAction(this::OnBack);
    }
    public void OnBack(ActionEvent actionEvent) {
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
            Stage stage = (Stage)backBtn.getScene().getWindow();
            stage.setScene(new Scene(root, Values.SCREEN_WIDTH,Values.SCREEN_HEIGHT));
            stage.show();
        }
    }

    private void loadScores() {
        List<Score> mScores = gameManager.getScores();
        rankingsTitle.setText(Values.LABEL_RANKINGS_TOP_5);

        //if less than 5 entries in ranks will throw array lenght error
        //to do fix
        for (int i = 5 - mScores.size(); i > 0; i--) {
            mScores.set(mScores.size(), new Score("---","---",0));
        }


        rankingsFirst.setText(mScores.get(0).prepareSave());
        rankingsSecond.setText(mScores.get(1).prepareSave());
        rankingsThird.setText(mScores.get(2).prepareSave());
        rankingsFourth.setText(mScores.get(3).prepareSave());
        rankingsFifth.setText(mScores.get(4).prepareSave());

        //Поле с интересна информация
        //В последствие може да се направи файл ако някой иска да се
        // занимае от който да се четат факти и да се зареждат тук
        // идеята е да го има на всеки екран освен boot.fxml
        // Фронтенд да се пипне на това цялото view

        gameManager.setFactsLabel(hintLabel);
    }
}

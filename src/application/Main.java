
package application;

import application.classes.GameManager;
import application.classes.Scores;
import application.classes.Song;
import application.classes.Values;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //replace with boot.fxml for bootscreen
        // replace with menu.fxml for startscreen
        Parent root = FXMLLoader.load(getClass().getResource("resources/fxml/boot.fxml"));
       // primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        root.getStylesheets().add("application/lightTheme.css");

        primaryStage.setResizable(false);
        primaryStage.setTitle("Bulgaria Quiz");
        primaryStage.setOnCloseRequest((event -> {
            Platform.exit();
            System.exit(0);
        }));
        primaryStage.setScene(new Scene(root, Values.SCREEN_WIDTH,Values.SCREEN_HEIGHT));
        primaryStage.show();
        Song song = new Song();
        song.playTrack();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

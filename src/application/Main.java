// Made by:
// Gunvir Ranu
// g1ranu@gmail.com

package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;



public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {


        Parent root = FXMLLoader.load(getClass().getResource("quizWindow.fxml"));

       // primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));


        primaryStage.setResizable(false);
        primaryStage.setTitle("Bulgaria Quiz");
        primaryStage.setOnCloseRequest((event -> {
            Platform.exit();
            System.exit(0);
        }));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

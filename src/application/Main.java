
package application;

import application.classes.Rankings;
import application.classes.Values;
import application.classes.cities.CityManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Rankings.create();
        //replace with boot.fxml for bootscreen
        // replace with start.fxml for startscreen
        Parent root = FXMLLoader.load(getClass().getResource("resources/fxml/cities.fxml")); //replace with start.fxml to skip boot
       // primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));

        primaryStage.setResizable(false);
        primaryStage.setTitle("Bulgaria Quiz");
        primaryStage.setOnCloseRequest((event -> {
            Platform.exit();
            System.exit(0);
        }));
        primaryStage.setScene(new Scene(root, Values.SCREEN_WIDTH,Values.SCREEN_HEIGHT));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}

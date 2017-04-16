
package application;

import application.classes.Song;
import application.constants.ConstantsDimentions;
import application.dependencies.DependencyInjectionContainer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private Song song = DependencyInjectionContainer.getSongInstance();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("resources/fxml/boot.fxml"));
        root.getStylesheets().add("application/lightTheme.css");

        primaryStage.setResizable(false);
        primaryStage.setTitle("Bulgaria Quiz");
        primaryStage.setOnCloseRequest((event -> {
            Platform.exit();
            System.exit(0);
        }));
        primaryStage.setScene(new Scene(root, ConstantsDimentions.SCREEN_WIDTH,ConstantsDimentions.SCREEN_HEIGHT));
        primaryStage.show();
        this.song.playTrack();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

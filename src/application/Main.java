
package application;

import application.classes.Song;
import application.constants.ConstantsDimensions;
import application.constants.ConstantsPath;
import application.dependencies.DependencyInjectionContainer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static final String TITLE_NAME_OF_GAME = "Bulgaria Quiz";
    private Song song = DependencyInjectionContainer.getSongInstance();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(ConstantsPath.PATH_TO_BOOT_WINDOW));
        root.getStylesheets().add("application/lightTheme.css");

        primaryStage.setResizable(false);
        primaryStage.setTitle(TITLE_NAME_OF_GAME);
        primaryStage.setOnCloseRequest((event -> {
            Platform.exit();
            System.exit(0);
        }));
        primaryStage.setScene(new Scene(root, ConstantsDimensions.SCREEN_WIDTH, ConstantsDimensions.SCREEN_HEIGHT));
        primaryStage.show();
        this.song.playTrack();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

package application.classes;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Song {
    private static MediaPlayer player;
    public static Boolean check = false;

    public void playTrack() {
        String ssound = "src/application/resources/songs/song.wav";
        Media sound = new Media(new File(ssound).toURI().toString());
        this.player = new MediaPlayer(sound);
        player.setCycleCount(MediaPlayer.INDEFINITE);
        player.play();
    }

    public static void pause(){
        check = true;
        player.pause();
    }

    public static void start(){
        check = false;
      player.play();
    }

    public static void winSound(){
        String soundPath = "src/application/resources/songs/win.wav";
        Media sound = new Media(new File(soundPath).toURI().toString());
        MediaPlayer win = new MediaPlayer(sound);
        win.play();
    }

    public static void wrongSound(){
        String soundPath = "src/application/resources/songs/wrong.wav";
        Media sound = new Media(new File(soundPath).toURI().toString());
        MediaPlayer wrong = new MediaPlayer(sound);
        wrong.play();
    }
}

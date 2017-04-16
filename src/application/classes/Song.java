package application.classes;

import application.constants.ConstantsPath;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Song {
    private MediaPlayer player;
    private Boolean check = false;

    public void playTrack() {
        Media sound = new Media(new File(ConstantsPath.SOUND_PATH_SONG).toURI().toString());
        this.player = new MediaPlayer(sound);
        this.player.setCycleCount(MediaPlayer.INDEFINITE);
        this.player.play();
    }

    public void pause(){
        this.check = true;
        this.player.pause();
    }

    public void start(){
        this.check = false;
        this.player.play();
    }

    public void winSound(){
    Media sound = new Media(new File(ConstantsPath.SOUND_PATH_CORRECT).toURI().toString());
        MediaPlayer win = new MediaPlayer(sound);
        win.play();
    }

    public void wrongSound(){
        Media sound = new Media(new File(ConstantsPath.SOUND_PATH_WRONG).toURI().toString());
        MediaPlayer wrong = new MediaPlayer(sound);
        wrong.play();
    }

    public Boolean getCheck() {
        return this.check;
    }
}

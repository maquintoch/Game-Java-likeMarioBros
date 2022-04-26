package inf112.skeleton.app.services;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;

public class AudioPlayer {

    private MediaPlayer mediaPlayer;

    public AudioPlayer(String path) {
        try {
            Media uri = new Media(Paths.get(path).toUri().toString());
            mediaPlayer = new MediaPlayer(uri);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        try {
            mediaPlayer.play();
            mediaPlayer.seek(mediaPlayer.getStartTime());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

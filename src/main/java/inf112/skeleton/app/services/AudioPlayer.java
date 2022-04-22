package inf112.skeleton.app.services;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;

public class AudioPlayer {

    private final MediaPlayer mediaPlayer;

    public AudioPlayer(String path) {
        Media uri = new Media(Paths.get(path).toUri().toString());
        mediaPlayer = new MediaPlayer(uri);
    }

    public void play() {
        mediaPlayer.play();
        mediaPlayer.seek(mediaPlayer.getStartTime());
    }
}

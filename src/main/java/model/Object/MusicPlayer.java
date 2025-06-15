package model.Object;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MusicPlayer {
    private static MediaPlayer mediaPlayer;
    private static final List<String> tracks = new ArrayList<>();

    static {
        tracks.add("src/main/resources/music/track1.mp3");
        tracks.add("src/main/resources/music/track2.mp3");
        tracks.add("src/main/resources/music/track3.mp3");
    }

    public static void play(int trackIndex) {
        if (trackIndex < 0 || trackIndex >= tracks.size()) return;

        stop();

        Media media = new Media(new File(tracks.get(trackIndex)).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Bucle infinito
        mediaPlayer.play();
    }

    public static void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }
}


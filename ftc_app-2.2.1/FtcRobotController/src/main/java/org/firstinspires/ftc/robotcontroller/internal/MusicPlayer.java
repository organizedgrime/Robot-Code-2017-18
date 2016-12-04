package org.firstinspires.ftc.robotcontroller.internal;

import android.content.Context;
import android.media.MediaPlayer;

public class MusicPlayer {
    Context context;
    int[] songs;
    int songIndex = 0;
    MediaPlayer mediaPlayer;

    public MusicPlayer(int song, Context context) {
        this.context = context;
        songs = new int[]{song};

    }

    public MusicPlayer(int[] songs, Context context) {
        this.context = context;
        this.songs = songs;
    }

    public void play() {
        mediaPlayer = MediaPlayer.create(context, songs[songIndex]);
        mediaPlayer.start();
    }

    public void next() {
        if(songIndex < songs.length) {
            songIndex++;
            play();
        }
    }

    public void pause() {
        mediaPlayer.pause();
    }

    public void stop() {
        mediaPlayer.stop();
    }
}

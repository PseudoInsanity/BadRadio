package com.example.edmirsuljic.badradio.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;

public class MusicService extends Service {

    //TODO Fix so that the media player staticUrl changes accordingly to Firebase-RadioStaion getUrl()
    public static MediaPlayer mediaPlayer;
    public static String staticUrl;
    public static boolean isPlaying;


    public MusicService() {
    }

    //This is the method that is called when getActivity().startService(i); is called
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        try {

            mediaPlayer = new MediaPlayer();


            //sets sound src file
            try {
                String url = staticUrl;
                mediaPlayer.setDataSource(url);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //prepares the player for playback
            mediaPlayer.prepare();

            //starts the playback
            mediaPlayer.start();
           // isPlaying = true;

        } catch (IOException e) {
            e.printStackTrace();
            Log.i("show", "Error: " + e.toString());
        }

        return START_NOT_STICKY;
    }

    //This is the method that is called when getActivity().startStop(i); is called
    @Override
    public void onDestroy() {

        //stops the playback
        mediaPlayer.stop();
        //releases any resource attached with the MediaPlayer
        mediaPlayer.release();
       // isPlaying = false;

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static void setUrl(String url) {
        MusicService.staticUrl = url;
    }

    public static boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }
}

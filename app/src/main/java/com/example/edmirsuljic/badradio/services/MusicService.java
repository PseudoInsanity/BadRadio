package com.example.edmirsuljic.badradio.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;


import java.io.IOException;

import static com.example.edmirsuljic.badradio.Fragments.PlayerFragment.playing;

public class MusicService extends Service {

    private MediaPlayer mediaPlayer;
    private static String url = "No station used";
    private static String currStation = "Choose a station";


    public MusicService() {
    }

    //This is the method that is called when getActivity().startService(i); is called
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        try {

            mediaPlayer = new MediaPlayer();


            //sets sound src file

            url = intent.getStringExtra("stationURL");
            currStation = intent.getStringExtra("stationName");

            mediaPlayer.setDataSource(url);

            //prepares the player for playback
            mediaPlayer.prepare();

            //starts the playback
            mediaPlayer.start();
            // isPlaying = true;

            playing = true;

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

        playing = false;

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static String getUrl() {
        return url;
    }

    public static String getCurrStation() {
        return currStation;
    }
}

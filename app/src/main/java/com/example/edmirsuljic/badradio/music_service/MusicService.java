package com.example.edmirsuljic.badradio.music_service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;

public class MusicService extends Service {


    private MediaPlayer mediaPlayer;

    public MusicService() {}

    //This is the method that is called when getActivity().startService(i); is called
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        try {

            mediaPlayer = new MediaPlayer();

            //sets sound src file
            try {
                mediaPlayer.setDataSource("http://fm01-ice.stream.khz.se/fm01_mp3");
            } catch (Exception e) {
                e.printStackTrace();
            }

            //prepares the player for playback
            mediaPlayer.prepare();

            //starts the playback
            mediaPlayer.start();

        } catch (IOException e) {
            e.printStackTrace();
            Log.i("show","Error: "+ e.toString());
        }

        return START_STICKY;
    }

    //This is the method that is called when getActivity().startStop(i); is called
    @Override
    public void onDestroy(){

        //stops the playback
        mediaPlayer.stop();
        //releases any resource attached with the MediaPlayer
        mediaPlayer.release();

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}

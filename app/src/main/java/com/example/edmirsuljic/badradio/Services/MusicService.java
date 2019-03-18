package com.example.edmirsuljic.badradio.Services;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.MediaSessionManager;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.example.edmirsuljic.badradio.Fragments.PlayerFragment;
import com.example.edmirsuljic.badradio.R;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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

package com.example.edmirsuljic.badradio.activities;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.VideoView;

import com.example.edmirsuljic.badradio.fragments.StartFragment;
import com.example.edmirsuljic.badradio.R;
import com.example.edmirsuljic.badradio.radio_related.RadioHandler;

import static com.example.edmirsuljic.badradio.activities.MainActivity.notificationManager;

public class StartActivity extends AppCompatActivity {

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        RadioHandler radioHandler = new RadioHandler();
        radioHandler.getRadioStation();

        videoView = findViewById(R.id.startVid);
        playBackground();

        Fragment fragment = new StartFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.start_fragment_holder, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onDestroy () {
        notificationManager.cancelAll();
        super.onDestroy();
    }

    @Override
    public void onStart() {
        super.onStart();
        playBackground();

    }

    private void playBackground () {

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

                videoView.start();
            }
        });

        Uri uri = Uri.parse("android.resource://com.example.edmirsuljic.badradio/" + R.raw.badradio_vid);
        videoView.setVideoURI(uri);
        videoView.start();
    }

}

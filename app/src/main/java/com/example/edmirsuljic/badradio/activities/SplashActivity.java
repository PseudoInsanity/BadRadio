package com.example.edmirsuljic.badradio.activities;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.VideoView;

import com.example.edmirsuljic.badradio.R;
import com.example.edmirsuljic.badradio.Radio_Related.RadioHandler;

/* Activity transitions in this project is inspired by Coding in Flow
    https://www.youtube.com/watch?v=0s6x3Sn4eYo
 */

public class SplashActivity extends AppCompatActivity {

    private VideoView videoView;
    private final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final RadioHandler radioHandler = new RadioHandler();

        videoView = findViewById(R.id.splashVid);

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

                if (radioHandler.getRadioStation()) {

                    Uri uri = Uri.parse("android.resource://com.example.edmirsuljic.badradio/" + R.raw.name_stroke);
                    videoView.setVideoURI(uri);
                    videoView.start();

                    videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            Intent intent = new Intent(context, StartActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            finish();
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        }
                    });
                }
            }
        });

        Uri uri = Uri.parse("android.resource://com.example.edmirsuljic.badradio/" + R.raw.fade_logo_short);
        videoView.setVideoURI(uri);
        videoView.start();
    }
}

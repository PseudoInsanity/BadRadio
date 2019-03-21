package com.example.edmirsuljic.badradio.activities;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.VideoView;

import com.example.edmirsuljic.badradio.Fragments.StartFragment;
import com.example.edmirsuljic.badradio.R;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.example.edmirsuljic.badradio.activities.MainActivity.notificationManager;

public class StartActivity extends AppCompatActivity {

    private VideoView videoView;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            Intent intent = new Intent(context, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }

            videoView = findViewById(R.id.startVid);
            playBackground();

            Fragment fragment = new StartFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.start_fragment_holder, fragment);
            transaction.commit();


    }

    @Override
    public void onDestroy () {
        notificationManager.cancelAll();
        super.onDestroy();
    }

    @Override
    public void onBackPressed () {
        int backStackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
        if (backStackEntryCount == 0) {
            moveTaskToBack(true);   // write your code to switch between fragments.
        } else {
            super.onBackPressed();
        }
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

package com.example.edmirsuljic.badradio.Fragments;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.VideoView;

import com.example.edmirsuljic.badradio.BuildConfig;
import com.example.edmirsuljic.badradio.Services.MusicService;
import com.example.edmirsuljic.badradio.R;
import com.example.edmirsuljic.badradio.Services.NotifyBroadcast;

import static com.example.edmirsuljic.badradio.Services.NotifyChannel.MUSIC_CHANNEL;


public class PlayerFragment extends Fragment{


    public PlayerFragment() {}

    private ImageView imageButton;
    private VideoView videoView;
    private static View view;
    public static boolean playing = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        view = inflater.inflate(R. layout.fragment_player, container, false);

        videoView = view.findViewById(R.id.videoView);
        imageButton = view.findViewById(R.id.imageButton);

        if (savedInstanceState != null) {
            playBackground();
        }

        //Init state of playbutton
        if (playing) {

            imageButton.setImageResource(R.drawable.avd_anim_two);


        } else if (!playing) {

            imageButton.setImageResource(R.drawable.avd_anim);

        }

        playButtonAnimation();
        playBackground();

        return view;
    }

    //Handling the play/pause button
    private void playButtonAnimation () {


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (playing) {

                    imageButton.setImageResource(R.drawable.avd_anim_two);
                    playing = false;

                    //Called for music service to stop
                    Intent i = new Intent(view.getContext(), MusicService.class);
                    getActivity().stopService(i);




                } else if (!playing) {

                    imageButton.setImageResource(R.drawable.avd_anim);
                    playing = true;

                    //Called for music service to start
                    Intent i = new Intent(view.getContext(), MusicService.class);
                    getActivity().startService(i);


                }

                Drawable drawable = imageButton.getDrawable();
                if (drawable instanceof AnimatedVectorDrawableCompat) {

                    AnimatedVectorDrawableCompat compat = (AnimatedVectorDrawableCompat) drawable;
                    compat.start();


                } else if (drawable instanceof AnimatedVectorDrawable) {

                    AnimatedVectorDrawable vectorDrawable = (AnimatedVectorDrawable) drawable;
                    vectorDrawable.start();

                }
            }
        });
    }

    //Plays the moving background in a videoview
    private void playBackground () {

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

                videoView.start();
            }
        });

        Uri uri = Uri.parse("android.resource://com.example.edmirsuljic.badradio/" + R.raw.vid);
        videoView.setVideoURI(uri);
        videoView.start();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);


    }
}

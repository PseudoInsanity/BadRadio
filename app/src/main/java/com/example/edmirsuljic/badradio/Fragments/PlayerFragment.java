package com.example.edmirsuljic.badradio.Fragments;

import android.content.Intent;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.VideoView;

import com.example.edmirsuljic.badradio.MusicService.MusicService;
import com.example.edmirsuljic.badradio.R;

import java.util.Objects;


public class PlayerFragment extends DialogFragment {


    public PlayerFragment() {}

    private ImageView imageButton, shareBtn;
    public static boolean playing = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final View view = inflater.inflate(R. layout.fragment_player, container, false);

        imageButton = view.findViewById(R.id.imageButton);
        shareBtn = view.findViewById(R.id.shareBtn);

        //Init state of playbutton
        if (playing) {

            imageButton.setImageResource(R.drawable.ic_play24dp);


        } else if (!playing) {

            imageButton.setImageResource(R.drawable.ic_play24dp);

        }

        playButtonHandle(view);

        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                ShareFragment dialogFragment = new ShareFragment();
                dialogFragment.show(fm, "Sample Fragment");
            }
        });

        return view;
    }

    //Handling the play/pause button
    private void playButtonHandle (View view) {


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (playing) {

                    imageButton.setImageResource(R.drawable.ic_play24dp);
                    playing = false;

                    //Called for music service to stop
                    Intent i = new Intent(view.getContext(), MusicService.class);
                    getActivity().stopService(i);




                } else if (!playing) {

                    imageButton.setImageResource(R.drawable.ic_pause24dp);
                    playing = true;

                    //Called for music service to start
                    Intent i = new Intent(view.getContext(), MusicService.class);
                    getActivity().startService(i);


                }
            }
        });
    }
}

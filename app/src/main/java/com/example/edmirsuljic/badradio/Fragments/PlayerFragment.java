package com.example.edmirsuljic.badradio.Fragments;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;


import com.example.edmirsuljic.badradio.services.MusicService;
import com.example.edmirsuljic.badradio.R;


public class PlayerFragment extends Fragment {


    public PlayerFragment() {}

    private ImageView imageButton, shareBtn;
    private VideoView videoView;
    private TextView textView;
    public static boolean playing = false;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final View view = inflater.inflate(R. layout.fragment_player, container, false);

        imageButton = view.findViewById(R.id.imageButton);
        shareBtn = view.findViewById(R.id.shareBtn);
        videoView = view.findViewById(R.id.videoView);
        textView = view.findViewById(R.id.currentStationTxt);

        textView.setText(MusicService.getCurrStation());

        //Init state of playbutton
        if (playing) {

            imageButton.setImageResource(R.drawable.ic_pause24dp);


        } else if (!playing) {

            imageButton.setImageResource(R.drawable.ic_play24dp);

        }

        playBackground();

        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                ShareFragment dialogFragment = new ShareFragment();
                dialogFragment.show(fm, "Sample Fragment");
            }
        });

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

                    MusicService service = new MusicService();

                    //Called for music service to start
                    Intent i = new Intent(view.getContext(), MusicService.class);
                    i.putExtra("stationURL", service.getUrl());
                    i.putExtra("stationName", service.getCurrStation());
                    getActivity().startService(i);
                }

            }
        });

        return view;
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


    @Override
    public void onStart() {
        super.onStart();

        playBackground();
    }
}

package com.example.edmirsuljic.badradio.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.edmirsuljic.badradio.fragments.ShareFragment;
import com.example.edmirsuljic.badradio.services.MusicService;
import com.example.edmirsuljic.badradio.R;


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

            imageButton.setImageResource(R.drawable.ic_pause24dp);


        } else if (!playing) {

            imageButton.setImageResource(R.drawable.ic_play24dp);

        }

        playButtonHandle();

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
    private void playButtonHandle () {


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (playing) {

                    imageButton.setImageResource(R.drawable.ic_pause24dp);
                    playing = false;

                    //Called for music service to stop
                    Intent i = new Intent(view.getContext(), MusicService.class);
                    getActivity().stopService(i);


                } else if (!playing) {

                    imageButton.setImageResource(R.drawable.ic_play24dp);
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
    }
}

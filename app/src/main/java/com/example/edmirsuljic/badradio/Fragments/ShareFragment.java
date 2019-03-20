package com.example.edmirsuljic.badradio.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.edmirsuljic.badradio.R;
import com.example.edmirsuljic.badradio.Adapters.RadioAdapter;
import com.example.edmirsuljic.badradio.Radio_Related.RadioStation;
import com.example.edmirsuljic.badradio.services.MusicService;

import java.util.ArrayList;


public class ShareFragment extends DialogFragment implements View.OnClickListener{
    private ImageButton twitter, facebook, instagram;

    private Button share;
    private ArrayList<RadioStation> mList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View inflate = inflater.inflate(R.layout.fragment_share, container, false);
        share = inflate.findViewById(R.id.imageViewShare);

        share.setOnClickListener(this);


        return inflate;
    }

    public void openAppToShare(View view) {
        String radioStation = MusicService.getCurrStation();
        String url = MusicService.getUrl();
        String message = "I'm listening to " + radioStation + "! So can you: " + url;


        Intent shareIntent = new Intent();
        shareIntent.setAction(shareIntent.ACTION_SEND);
        shareIntent.putExtra(shareIntent.EXTRA_TEXT,message);
        shareIntent.setType("text/plain");

        startActivity(shareIntent.createChooser(shareIntent,"Share your favorite station to : " ));
    }


    @Override
    public void onClick(View v) {

        int position = 0;
        openAppToShare(v);
    }
}

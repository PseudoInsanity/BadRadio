package com.example.edmirsuljic.badradio.Fragments;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.VideoView;

import com.example.edmirsuljic.badradio.R;

import java.util.Objects;

public class StartFragment extends Fragment {

    private VideoView videoView;
    private Button signBtn, regBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_start, container, false);
        videoView = view.findViewById(R.id.signInVideoView);
        signBtn = view.findViewById(R.id.signinBtn);
        regBtn = view.findViewById(R.id.regBtn);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new RegisterFragment();
                FragmentTransaction transaction = Objects.requireNonNull(getFragmentManager()).beginTransaction();
                transaction.replace(R.id.start_fragment_holder, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        signBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new LoginFragment();
                FragmentTransaction transaction = Objects.requireNonNull(getFragmentManager()).beginTransaction();
                transaction.replace(R.id.start_fragment_holder, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        playBackground();

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
    public void onStart () {
        super.onStart();

        playBackground();
    }

}

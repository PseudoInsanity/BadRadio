package com.example.edmirsuljic.badradio.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.edmirsuljic.badradio.R;

public class ShareFragment extends Fragment implements View.OnClickListener{
    private Button share;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View inflate = inflater.inflate(R.layout.fragment_share, container, false);
        share = inflate.findViewById(R.id.imageViewShare);

        share.setOnClickListener(this);

        return inflate;
    }


    /*public void openTwitter(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);

        intent.setType("text/hello");
        intent.putExtra(Intent.EXTRA_TEXT, "https://www.google.com");
        startActivity(Intent.createChooser(intent, "Share"));

        Intent twitterIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/login"));
        startActivity(twitterIntent);

    }
    public void openInstagram(View view) {
        Intent instagramIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/accounts/login/"));
        startActivity(instagramIntent);
    }*/

    public void openAppToShare(View view) {
        String message = "I'm listening to (station)! You can too: (url)";


        Intent shareIntent = new Intent();
        shareIntent.setAction(shareIntent.ACTION_SEND);
        shareIntent.putExtra(shareIntent.EXTRA_TEXT,message);
        shareIntent.setType("text/plain");

        startActivity(shareIntent.createChooser(shareIntent,"Share your favorite station to : " ));
    }


    @Override
    public void onClick(View v) {
        openAppToShare(v);
    }
}

package com.example.edmirsuljic.badradio.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.edmirsuljic.badradio.R;

public class ShareFragment extends DialogFragment implements View.OnClickListener{
    private ImageButton twitter, facebook, instagram;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View inflate = inflater.inflate(R.layout.fragment_share, container, false);
        twitter = inflate.findViewById(R.id.imageViewTwitter);
        facebook = inflate.findViewById(R.id.imageViewFacebook);
        instagram = inflate.findViewById(R.id.imageViewInstagram);

        twitter.setOnClickListener(this);
        facebook.setOnClickListener(this);
        instagram.setOnClickListener(this);

        return inflate;
    }


    public void openTwitter(View view) {
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
    }

    public void openFacebook(View view) {
        Intent facebookIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com"));
        startActivity(facebookIntent);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageViewTwitter:
                openTwitter(v);
                break;
            case R.id.imageViewFacebook:
                openFacebook(v);
                break;
            case R.id.imageViewInstagram:
                openInstagram(v);
                break;
        }
    }
}

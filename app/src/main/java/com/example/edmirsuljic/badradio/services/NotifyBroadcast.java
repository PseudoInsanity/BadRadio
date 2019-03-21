package com.example.edmirsuljic.badradio.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.edmirsuljic.badradio.Activities.MainActivity;
import com.example.edmirsuljic.badradio.R;

import static com.example.edmirsuljic.badradio.Adapters.RadioAdapter.currPos;
import static com.example.edmirsuljic.badradio.Fragments.HomeFragment.lastPos;
import static com.example.edmirsuljic.badradio.Fragments.PlayerFragment.playing;

public class NotifyBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        MusicService service = new MusicService();

                if (playing) {

                    lastPos = -1;
                    MainActivity.notification.contentView.setImageViewResource(R.id.notiBtn, R.drawable.ic_play24dp);
                    MainActivity.notification.contentView.setTextViewText(R.id.notiStation, service.getCurrStation());
                    MainActivity.notificationManager.notify(1, MainActivity.notification);

                    playing = false;

                    //Called for music service to start
                    Intent i = new Intent(context, MusicService.class);
                    context.stopService(i);

                } else if (!playing)  {

                    lastPos = currPos;

                    MainActivity.notification.contentView.setImageViewResource(R.id.notiBtn, R.drawable.ic_pause24dp);
                    MainActivity.notification.contentView.setTextViewText(R.id.notiStation, service.getCurrStation());
                    MainActivity.notificationManager.notify(1, MainActivity.notification);

                    playing = true;

                    //Called for music service to start
                    Intent i = new Intent(context, MusicService.class);
                    i.putExtra("stationURL", service.getUrl());
                    i.putExtra("stationName", service.getCurrStation());
                    context.startService(i);
                }


    }
}

package com.example.edmirsuljic.badradio.Service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;

import com.example.edmirsuljic.badradio.Activities.MainActivity;
import com.example.edmirsuljic.badradio.Fragments.PlayerFragment;
import com.example.edmirsuljic.badradio.R;

import static com.example.edmirsuljic.badradio.Fragments.PlayerFragment.playing;

public class NotifyBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        MusicService service = new MusicService();

                if (playing) {

                    MainActivity.notification.contentView.setImageViewResource(R.id.notiBtn, R.drawable.ic_play24dp);
                    MainActivity.notification.contentView.setTextViewText(R.id.notiStation, service.getCurrStation());
                    MainActivity.notificationManager.notify(1, MainActivity.notification);

                    //Called for music service to start
                    Intent i = new Intent(context, MusicService.class);
                    context.stopService(i);

                } else if (!playing) {

                    MainActivity.notification.contentView.setImageViewResource(R.id.notiBtn, R.drawable.ic_pause24dp);
                    MainActivity.notification.contentView.setTextViewText(R.id.notiStation, service.getCurrStation());
                    MainActivity.notificationManager.notify(1, MainActivity.notification);

                    //Called for music service to start
                    Intent i = new Intent(context, MusicService.class);
                    i.putExtra("stationURL", service.getUrl());
                    i.putExtra("stationName", service.getCurrStation());
                    context.startService(i);
                }


    }
}

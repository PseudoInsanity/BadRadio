package com.example.edmirsuljic.badradio.MusicService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.edmirsuljic.badradio.Activities.MainActivity;
import com.example.edmirsuljic.badradio.Fragments.PlayerFragment;
import com.example.edmirsuljic.badradio.R;

public class NotifyBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {


                if (PlayerFragment.playing) {

                    PlayerFragment.playing = false;

                    MainActivity.notification.contentView.setImageViewResource(R.id.imageView3, R.drawable.avd_anim);
                    MainActivity.notificationManager.notify(1, MainActivity.notification);

                    //Called for music service to start
                    Intent i = new Intent(context, MusicService.class);
                    context.stopService(i);

                } else if (!PlayerFragment.playing) {

                    PlayerFragment.playing = true;
                    MainActivity.notification.contentView.setImageViewResource(R.id.imageView3, R.drawable.avd_anim_two);
                    MainActivity.notificationManager.notify(1, MainActivity.notification);

                    //Called for music service to start
                    Intent i = new Intent(context, MusicService.class);
                    context.startService(i);
                }


    }
}

package com.example.edmirsuljic.badradio.Service;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class NotifyChannel extends Application {

    public static final String MUSIC_CHANNEL = "Music Notify";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotifyChannel();
    }

    private void createNotifyChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel notificationChannel = new NotificationChannel(
                    MUSIC_CHANNEL,
                    "Music Stream",
                    NotificationManager.IMPORTANCE_LOW
            );

            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
}

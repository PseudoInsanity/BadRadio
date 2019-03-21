package com.example.edmirsuljic.badradio.activities;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RemoteViews;

import com.example.edmirsuljic.badradio.Fragments.AccountFragment;
import com.example.edmirsuljic.badradio.Fragments.HomeFragment;
import com.example.edmirsuljic.badradio.Fragments.PlayerFragment;
import com.example.edmirsuljic.badradio.R;
import com.example.edmirsuljic.badradio.services.MusicService;
import com.example.edmirsuljic.badradio.services.NotifyBroadcast;
import com.google.firebase.auth.FirebaseAuth;

import static com.example.edmirsuljic.badradio.Fragments.PlayerFragment.playing;
import static com.example.edmirsuljic.badradio.services.NotifyChannel.MUSIC_CHANNEL;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private Context context = this;
    public static NotificationManagerCompat notificationManager;
    public static Notification notification;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        notificationManager = NotificationManagerCompat.from(context);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        FloatingActionButton floatingActionButton = findViewById(R.id.playFab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new PlayerFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_holder, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Fragment fragment = new HomeFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_holder, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            notificationManager.cancelAll();
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(context, StartActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        selectDrawerItem(item);
        return true;
    }

    @Override
    public void onPause() {
        showNotification();

        super.onPause();
    }

    @Override
    public void onDestroy() {
        FirebaseAuth.getInstance().signOut();
        notificationManager.cancelAll();
        super.onDestroy();
    }

    @Override
    public void onStart() {
        notificationManager.cancelAll();
        super.onStart();
    }

    private void selectDrawerItem(MenuItem item) {
        int id = item.getItemId();
        Fragment fragment = null;
        Class fragmentClass = null;

        switch (id) {
            case R.id.nav_home:
                fragmentClass = HomeFragment.class;
                break;
            case R.id.nav_account:
                fragmentClass = AccountFragment.class;
                break;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_holder, fragment);
        transaction.addToBackStack(null);
        transaction.commit();


        drawer.closeDrawers();
    }

    public void showNotification() {

        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notification_layout);

        Intent clickedIntent = new Intent(context, NotifyBroadcast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0,
                clickedIntent, 0);

        remoteViews.setOnClickPendingIntent(R.id.notiBtn, pendingIntent);

        if (playing) {

            remoteViews.setImageViewResource(R.id.notiBtn, R.drawable.ic_pause24dp);

        } else if (!playing) {

            remoteViews.setImageViewResource(R.id.notiBtn, R.drawable.ic_play24dp);
        }

        remoteViews.setTextViewText(R.id.notiStation, MusicService.getCurrStation());

        notification = new NotificationCompat.Builder(context, MUSIC_CHANNEL)
                .setSmallIcon(R.drawable.logo)
                .setCustomContentView(remoteViews)
                .build();

        notificationManager.notify(1, notification);
    }
}

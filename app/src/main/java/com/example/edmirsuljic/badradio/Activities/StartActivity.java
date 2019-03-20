package com.example.edmirsuljic.badradio.Activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.edmirsuljic.badradio.Fragments.StartFragment;
import com.example.edmirsuljic.badradio.R;
import com.example.edmirsuljic.badradio.Radio_Related.RadioHandler;

import static com.example.edmirsuljic.badradio.Activities.MainActivity.notificationManager;

public class StartActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        RadioHandler radioHandler = new RadioHandler();
        radioHandler.getRadioStation();

        Fragment fragment = new StartFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.start_fragment_holder, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onDestroy () {
        notificationManager.cancelAll();
        super.onDestroy();
    }

    @Override
    public void onStart() {
        super.onStart();


    }

}

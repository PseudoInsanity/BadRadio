package com.example.edmirsuljic.badradio.RadioRelated;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RadioHandler {

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("radioStations");
    private List<RadioStation> radioStationList = new ArrayList<>();



    public List<RadioStation> getRadioStation() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot radioSnapshot : dataSnapshot.getChildren()) {
                    RadioStation radioStation = radioSnapshot.getValue(RadioStation.class);

                    radioStationList.add(radioStation);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("ERROR", databaseError.getMessage());
            }
        });

        return radioStationList;
    }
}

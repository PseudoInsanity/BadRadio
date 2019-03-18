package com.example.edmirsuljic.badradio.radio_related;

import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class RadioHandler {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static ArrayList<RadioStation> radioStationList;

    public void getRadioStation() {

        radioStationList = new ArrayList<>();
        db.collection("radioStations")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot q: queryDocumentSnapshots) {
                            radioStationList.add(new RadioStation(q.getString("name"), q.getString("url")));
                            Log.d("Edmir", "Success");
                        }
                    }
                });

    }

    public ArrayList<RadioStation> getRadioStationList() {
        return radioStationList;
    }
}

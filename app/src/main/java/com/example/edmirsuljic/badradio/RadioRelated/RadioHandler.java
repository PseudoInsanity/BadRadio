package com.example.edmirsuljic.badradio.RadioRelated;

import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class RadioHandler {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<RadioStation> radioStationList = new ArrayList<>();


    public List<RadioStation> getRadioStation() {

        db.collection("radioStations")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot q: queryDocumentSnapshots) {
                            radioStationList.add(new RadioStation(q.getString("name"), q.getString("url")));
                            System.out.println("Name: " + q.get("name"));
                            Log.d("Edmir", "Success");
                        }
                    }
                });
        return radioStationList;
    }
}

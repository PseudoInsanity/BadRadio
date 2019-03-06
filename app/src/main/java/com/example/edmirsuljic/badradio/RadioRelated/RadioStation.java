package com.example.edmirsuljic.badradio.RadioRelated;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RadioStation {

    public String url;
    public String name;

    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    public RadioStation(){

    }

    public RadioStation(String url, String name) {
        this.url = url;
        this.name = name;
    }
}

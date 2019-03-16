package com.example.edmirsuljic.badradio.radio_related;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.example.edmirsuljic.badradio.radio_related;


public class RadioStation {

    private String url;
    private String name;


    public RadioStation(String name, String url) {
        this.url = url;
        this.name = name;
    }

    public String getUrl() {
        return url;
    }


    public String getName() {
        return name;
    }


}

package com.example.cosmicsaki.content.geoLocation.model;

import com.google.android.gms.maps.model.LatLng;

public class LocationDemo {

    private LatLng latLng;
    private String username;

    public LocationDemo() {
    }

    public LocationDemo(String username, LatLng latLng) {
        this.latLng = latLng;
        this.username = username;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public String getUsername() {
        return username;
    }
}

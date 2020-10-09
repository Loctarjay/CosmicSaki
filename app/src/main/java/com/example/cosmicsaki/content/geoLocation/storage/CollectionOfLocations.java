package com.example.cosmicsaki.content.geoLocation.storage;

import com.example.cosmicsaki.content.geoLocation.model.LocationDemo;

import java.util.ArrayList;

public class CollectionOfLocations {

    public static ArrayList<LocationDemo> list;

    static {
        list = new ArrayList<>();
    }

    public static ArrayList<LocationDemo> getList() {
        return list;
    }
}

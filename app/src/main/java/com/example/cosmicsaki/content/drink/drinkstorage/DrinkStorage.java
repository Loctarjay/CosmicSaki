package com.example.cosmicsaki.content.drink.drinkstorage;

import android.util.Log;

import com.example.cosmicsaki.content.drink.drinksmodel.Drink;

import java.util.ArrayList;

public class DrinkStorage {

    public static ArrayList<Drink> list;

    static {
        list = new ArrayList<>();
    }

    public static ArrayList<Drink> getList() {
        return list;
    }

    public static int getSize() {
        return list.size();
    }

    public static Drink getDrink(int index) {
        Log.i("headline", list.get(index).getTitle());
        return list.get(index);
    }

}

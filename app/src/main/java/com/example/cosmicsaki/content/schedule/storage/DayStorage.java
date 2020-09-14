package com.example.cosmicsaki.content.schedule.storage;

import com.example.cosmicsaki.content.schedule.model.Day;

import java.util.ArrayList;

public class DayStorage {

    public static ArrayList<Day> list;

    static {
        list = new ArrayList<>();
    }

    public static ArrayList<Day> getList() {
        return list;
    }

    public static int getSize(){
        return list.size();
    }

    public static Day getDay(int index){
        return list.get(index);
    }

    public static Day getDay(String day){
        Day temp = null;
        for (Day tempDay : list) {
            if (tempDay.getDay().equalsIgnoreCase(day)){
                temp = tempDay;
            }
        }
        return temp;
    }
}

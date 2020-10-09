package com.example.cosmicsaki.content.schedule.model;

public class Day {

    private String day, from, to;

    public Day() {
    }

    public Day(String day, String from, String to) {
        this.day = day;
        this.from = from;
        this.to = to;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}

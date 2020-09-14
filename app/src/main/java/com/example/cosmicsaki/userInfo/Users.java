package com.example.cosmicsaki.userInfo;

public class Users {

    private String username;
    private String email;
    private double latitude;
    private double longitude;
    private String userAccess;

    public Users(String username, String email){
        this.username = username;
        this.email = email;
        this.latitude = 0;
        this.longitude = 0;
        this.userAccess = "standard";
    }

    public Users(String username, double latitude, double longitude) {
        this.username = username;
        this.latitude = latitude;
        this.longitude = longitude;
        this.userAccess = "standard";
    }

    public Users(String username, String email, String userAccess) {
        this.username = username;
        this.email = email;
        this.userAccess = userAccess;
    }

    public Users(String username, String email, double latitude, double longitude) {
        this.username = username;
        this.email = email;
        this.latitude = latitude;
        this.longitude = longitude;
        this.userAccess = "standard";
    }

    public Users(String username, String email, double latitude, double longitude, String userAccess) {
        this.username = username;
        this.email = email;
        this.latitude = latitude;
        this.longitude = longitude;
        this.userAccess = userAccess;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserAccess() {
        return userAccess;
    }

    public void setUserAccess(String userAccess) {
        this.userAccess = userAccess;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}

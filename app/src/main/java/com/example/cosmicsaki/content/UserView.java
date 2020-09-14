package com.example.cosmicsaki.content;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cosmicsaki.MainActivity;
import com.example.cosmicsaki.R;
import com.example.cosmicsaki.auth.FirebaseManager;
import com.example.cosmicsaki.content.changeusers.ChangeUser;
import com.example.cosmicsaki.content.drink.DrinkRecipe;
import com.example.cosmicsaki.content.gallery.Gallery;
import com.example.cosmicsaki.content.geoLocation.Locations;
import com.example.cosmicsaki.content.playerlist.PlayerGames;
import com.example.cosmicsaki.content.schedule.Schedule;

public class UserView extends AppCompatActivity implements View.OnClickListener {

    private ImageButton schedules, gallery, drinks, geoLoc, playerGames;
    private Button logout, useraccess;
    private TextView username, textview7;
    private Bundle ext;
    public static String currentUserAccess = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_content);

        FirebaseManager.getGeolocation();

        ext = getIntent().getExtras();

        username = findViewById(R.id.signedInUser);
        if (ext == null){
            Log.i("username", "nothing extra");
        } else {
            username.setText(ext.getString(MainActivity.usernameAccess));
        }

        Log.i("userAccess", ext.getString(MainActivity.userAccess));

        schedules = findViewById(R.id.schedules);
        schedules.setOnClickListener(this);
        gallery = findViewById(R.id.gallery);
        gallery.setOnClickListener(this);
        drinks = findViewById(R.id.drinks);
        drinks.setOnClickListener(this);
        geoLoc = findViewById(R.id.geolocations);
        geoLoc.setOnClickListener(this);
        playerGames = findViewById(R.id.playerGames);
        playerGames.setOnClickListener(this);

        logout = findViewById(R.id.logout1);
        logout.setOnClickListener(this);

        // Admin area
        useraccess = findViewById(R.id.userAccess);
        useraccess.setOnClickListener(this);
        textview7 = findViewById(R.id.textView7);

        currentUserAccess = ext.getString(MainActivity.userAccess);

        if (currentUserAccess.equalsIgnoreCase("admin") ||
                currentUserAccess.equalsIgnoreCase("mod")){

            useraccess.setVisibility(View.VISIBLE);
            textview7.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.schedules:
                Intent schedule = new Intent(this, Schedule.class);
                schedule.putExtra(MainActivity.usernameAccess,
                        ext.getString(MainActivity.usernameAccess));
                schedule.putExtra(MainActivity.userAccess, ext.getString(MainActivity.userAccess));
                startActivity(schedule);
                break;
            case R.id.gallery:
                Intent gallery = new Intent(this, Gallery.class);
                startActivity(gallery);
                break;
            case R.id.drinks:
                Intent drinks = new Intent(this, DrinkRecipe.class);
                startActivity(drinks);
                break;
            case R.id.geolocations:
                Intent geoLoc = new Intent(this, Locations.class);
                startActivity(geoLoc);
                break;
            case R.id.userAccess:
                Intent change = new Intent(this, ChangeUser.class);
                startActivity(change);
                break;
            case R.id.playerGames:
                Intent playGame = new Intent(this, PlayerGames.class);
                startActivity(playGame);
                break;
            case R.id.logout1:
                MainActivity.firebaseManager.logout();
                finish();
                useraccess.setVisibility(View.INVISIBLE);
                textview7.setVisibility(View.INVISIBLE);
                currentUserAccess = "";
                break;
        }
    }
}

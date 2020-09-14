package com.example.cosmicsaki.content.schedule;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cosmicsaki.MainActivity;
import com.example.cosmicsaki.R;
import com.example.cosmicsaki.content.schedule.dialogBox.TimeDialog;
import com.example.cosmicsaki.content.schedule.storage.DayStorage;

public class Schedule extends AppCompatActivity implements View.OnClickListener, TimeDialog.timeDialogListener{

    private Button back;
    private TextView monday, tuesday, wednesday, thursday, friday, saturday;
    private Bundle ext;
    private int mondayID, tuesdayID, wednesdayID, thursdayID, fridayID, saturdayID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedules);
        mondayID = R.id.mondaytime;
        monday = findViewById(mondayID);
        monday.setText(setDayTime(DayStorage.getDay("monday").getFrom(), DayStorage.getDay("monday").getTo()));
        tuesdayID = R.id.tuesdayTime;
        tuesday = findViewById(tuesdayID);
        tuesday.setText(setDayTime(DayStorage.getDay("tuesday").getFrom(), DayStorage.getDay("tuesday").getTo()));
        wednesdayID = R.id.wednesdayTime;
        wednesday = findViewById(wednesdayID);
        wednesday.setText(setDayTime(DayStorage.getDay("wednesday").getFrom(), DayStorage.getDay("wednesday").getTo()));
        thursdayID = R.id.thursdayTime;
        thursday = findViewById(thursdayID);
        thursday.setText(setDayTime(DayStorage.getDay("thursday").getFrom(), DayStorage.getDay("thursday").getTo()));
        fridayID = R.id.fridayTime;
        friday = findViewById(fridayID);
        friday.setText(setDayTime(DayStorage.getDay("friday").getFrom(), DayStorage.getDay("friday").getTo()));
        saturdayID = R.id.saturdayTime;
        saturday = findViewById(saturdayID);
        saturday.setText(setDayTime(DayStorage.getDay("saturday").getFrom(), DayStorage.getDay("saturday").getTo()));

        ext = getIntent().getExtras();

        back = findViewById(R.id.back2);
        back.setOnClickListener(this);

        if (ext.getString(MainActivity.userAccess).equalsIgnoreCase("admin")){
            monday.setOnClickListener(this);
            tuesday.setOnClickListener(this);
            wednesday.setOnClickListener(this);
            thursday.setOnClickListener(this);
            friday.setOnClickListener(this);
            saturday.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mondaytime:
                openDialog(v.getId());
                break;
            case R.id.tuesdayTime:
                openDialog(v.getId());
                break;
            case R.id.wednesdayTime:
                openDialog(v.getId());
                break;
            case R.id.thursdayTime:
                openDialog(v.getId());
                break;
            case R.id.fridayTime:
                openDialog(v.getId());
                break;
            case R.id.saturdayTime:
                openDialog(v.getId());
                break;
            case R.id.back2:
                //MainActivity.firebaseManager.saveSchedule();
                finish();
                break;
        }
    }

    public void openDialog(int viewID){
        TimeDialog timeDialog = new TimeDialog(viewID);
        timeDialog.show(getSupportFragmentManager(), "change time dialog");
    }

    @Override
    public void applyText(String startTime, String stopTime, int viewID) {
        TextView day = (TextView) findViewById(viewID);
        day.setText(setDayTime(startTime, stopTime));
        if (viewID == mondayID){
            DayStorage.getDay("monday").setFrom(startTime);
            DayStorage.getDay("monday").setTo(stopTime);
            Log.i("test2", DayStorage.getDay("monday").getFrom() + " - " + DayStorage.getDay("monday").getTo());
        } else if (viewID == tuesdayID) {
            DayStorage.getDay("tuesday").setFrom(startTime);
            DayStorage.getDay("tuesday").setTo(stopTime);
        } else if (viewID == wednesdayID) {
            DayStorage.getDay("wednesday").setFrom(startTime);
            DayStorage.getDay("wednesday").setTo(stopTime);
        } else if (viewID == thursdayID){
            DayStorage.getDay("thursday").setFrom(startTime);
            DayStorage.getDay("thursday").setTo(stopTime);
        } else if (viewID == fridayID){
            DayStorage.getDay("friday").setFrom(startTime);
            DayStorage.getDay("friday").setTo(stopTime);
        } else if (viewID == saturdayID){
            DayStorage.getDay("saturday").setFrom(startTime);
            DayStorage.getDay("saturday").setTo(stopTime);
        }
        MainActivity.firebaseManager.saveSchedule();
    }

    private String setDayTime(String from, String to){
        return from + " - " + to;
    }
}

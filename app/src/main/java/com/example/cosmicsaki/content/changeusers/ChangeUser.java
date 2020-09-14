package com.example.cosmicsaki.content.changeusers;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cosmicsaki.R;

public class ChangeUser extends AppCompatActivity implements View.OnClickListener{

    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_users);

        back = findViewById(R.id.back5);
        back.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back5:
                finish();
                break;
        }
    }
}

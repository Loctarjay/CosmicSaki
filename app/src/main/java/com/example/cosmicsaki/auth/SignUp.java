package com.example.cosmicsaki.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cosmicsaki.MainActivity;
import com.example.cosmicsaki.R;
import com.example.cosmicsaki.followerLocation.FollowerLocation;

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    private Button location, skip;
    private EditText email, password, username;
    private String emailText;
    private String passwordText;
    public static final String usernameAccess = "Username";
    public static final String userEmailAccess = "email";
    public static final String userPasswordAccess = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        location = findViewById(R.id.makeLocation);
        location.setOnClickListener(this);
        skip = findViewById(R.id.skipLocation);
        skip.setOnClickListener(this);
        email = findViewById(R.id.email2);
        password = findViewById(R.id.password2);
        username = findViewById(R.id.username);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.makeLocation:
                Intent location = new Intent(this, FollowerLocation.class);
                location.putExtra(usernameAccess, username.getText().toString());
                MainActivity.firebaseManager.setTempUser(username.getText().toString(),
                        email.getText().toString(), password.getText().toString());
                startActivity(location);
                finish();
                break;
            case R.id.skipLocation:
                emailText = email.getText().toString();
                passwordText = password.getText().toString();
                if (emailText.length() > 0 && passwordText.length() > 0){
                    MainActivity.firebaseManager.signUpWithoutLocation(emailText,
                            passwordText,
                            username.getText().toString());
                    finish();
                } else {
                    Toast.makeText(this, "Please fill both fields", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }
}

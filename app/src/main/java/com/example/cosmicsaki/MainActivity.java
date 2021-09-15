package com.example.cosmicsaki;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cosmicsaki.auth.FirebaseManager;
import com.example.cosmicsaki.auth.SignUp;
import com.example.cosmicsaki.userInfo.userStorage.UserStorage;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button signIn, signUp;
    private EditText email, password;
    private String emailText, passwordText;

    public static FirebaseManager firebaseManager;
    public static String userCollection = "userCollections",  usernameAccess = "username",
            userAccess = "userAccess", dayTime = "schedule", geoLoc = "geolocation";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UserStorage.getList().clear();
        firebaseManager = new FirebaseManager();

        signIn = findViewById(R.id.signIn);
        signIn.setOnClickListener(this);
        signUp = findViewById(R.id.signUp);
        signUp.setOnClickListener(this);

        email = findViewById(R.id.email1);
        password = findViewById(R.id.password1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signIn:
                emailText = email.getText().toString();
                passwordText = password.getText().toString();
                if (emailText.length() > 0 && passwordText.length() > 0) {
                    firebaseManager.signIn(emailText, passwordText, this);
                    email.setText(null);
                    password.setText(null);
                    signIn.clearFocus();
                    break;
                } else {
                    Toast.makeText(this,
                            "Please fill both fields.",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.signUp:
                Intent SU = new Intent(this, SignUp.class);
                startActivity(SU);
                break;

            case R.id.close:
                finish();
                break;

        }
    }
}

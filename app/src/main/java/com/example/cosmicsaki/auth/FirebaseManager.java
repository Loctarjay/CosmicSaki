package com.example.cosmicsaki.auth;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cosmicsaki.MainActivity;
import com.example.cosmicsaki.content.UserView;
import com.example.cosmicsaki.content.geoLocation.model.LocationDemo;
import com.example.cosmicsaki.content.geoLocation.storage.CollectionOfLocations;
import com.example.cosmicsaki.content.schedule.model.Day;
import com.example.cosmicsaki.content.schedule.storage.DayStorage;
import com.example.cosmicsaki.userInfo.Users;
import com.example.cosmicsaki.userInfo.userStorage.UserStorage;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class FirebaseManager {

    private static FirebaseAuth auth;
    private static FirebaseFirestore db;
    private static FirebaseFirestoreSettings settings;
    private int loginCounter = 0;
    private static Users tempUser;
    private static String tempPassword;

    public FirebaseManager() {
        auth = FirebaseAuth.getInstance();
        setupAuthStateListener();
        db = FirebaseFirestore.getInstance();
        settings = new FirebaseFirestoreSettings.Builder().setPersistenceEnabled(false).build();
        db.setFirestoreSettings(settings);
    }

    private void setupAuthStateListener(){
        auth.addIdTokenListener(new FirebaseAuth.IdTokenListener() {
            @Override
            public void onIdTokenChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null){
                    System.out.println("Signed out from firebase");
                }else{
                    System.out.println("Signed in to firebase");
                }
            }
        });
    }

    public void signIn(final String email, String pwd, final MainActivity activity){
        try {
            auth.signOut();
        } catch (Exception e) {
            e.printStackTrace();
        }
        auth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Log.i("test1", "user connected");
                    final Intent intentLogin = new Intent(activity, UserView.class);
                    db.collection(MainActivity.userCollection).addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(@Nullable QuerySnapshot values, @Nullable FirebaseFirestoreException e) {
                            UserStorage.list.clear();
                            for (DocumentSnapshot snap : values.getDocuments()){
                                Log.i("test3", snap.get("username").toString() + " is " + snap.get("userAccess").toString());
                                Users user = new Users(
                                        snap.get("username").toString(),
                                        snap.get("email").toString(),
                                        Double.parseDouble(snap.get("latitude").toString()),
                                        Double.parseDouble(snap.get("longitude").toString()),
                                        snap.get("userAccess").toString());
                                UserStorage.list.add(user);
                                if (snap.get("email").toString().equalsIgnoreCase(email)) {
                                    Log.i("test1", "right account fetched");
                                    intentLogin.putExtra(MainActivity.usernameAccess, user.getUsername());
                                    intentLogin.putExtra(MainActivity.userAccess, user.getUserAccess());

                                    // Getting the streaming schedule time
                                    getSchedule();
                                    activity.startActivity(intentLogin);
                                    //loginCounter++;
                                }
                            }
                        }
                    });
                }else{
                    Log.i("FirebaseManager", "Login failed: " + task.getException().getMessage());
                    Toast.makeText(activity, "User doesn't exist", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //loginCounter = 0;
    }

    public void signUpWithoutLocation(final String email, String pwd, final String username){
        auth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    // add user to collection
                    Users user = new Users(username, email);
                    db.collection(MainActivity.userCollection).add(user);
                    Log.i("test1", "User Created");
                }else{
                    Log.e("FirebaseManager", "Sign up failed: " + task.getException().getMessage());
                }
            }
        });
    }

    public static void signUpWithLocation(final String username, final String email, String pwd, final double latitude, final double longitude){
        auth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    System.out.println("Sign up Success " + task.getResult().getUser().getEmail());
                    // add user to collection
                    Users user = new Users(username, email, latitude, longitude);
                    db.collection(MainActivity.userCollection).add(user);
                    Log.i("test1", "User Created");
                }else{
                    Log.e("FirebaseManager", "Sign up failed: " + task.getException().getMessage());
                }
            }
        });
    }

    public void logout(){
        auth.signOut();
        System.out.println("logged out");
    }

    public void getSchedule(){
        db.collection(MainActivity.dayTime).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        DayStorage.getList().add(new Day(
                                                    document.get("day").toString(),
                                                    document.get("from").toString(),
                                                    document.get("to").toString()));
                    }
                    Log.i("test1", "Have downloaded the schedule");
                }
            }
        });
    }

    public void saveSchedule(){
        for (int x = 0; x < DayStorage.getSize(); x++) {
            Log.i("test2", "Day - " + DayStorage.getDay(x).getDay() + " time - " + DayStorage.getDay(x).getFrom() + " - " + DayStorage.getDay(x).getTo());
            db.collection(MainActivity.dayTime).document(DayStorage.getDay(x).getDay()).update(
                    "from", DayStorage.getDay(x).getFrom(),
                    "to", DayStorage.getDay(x).getTo()
            );
        }
    }

    public static void getGeolocation(){
        for (Users user : UserStorage.getList()) {
            String username = user.getUsername();
            LatLng latLng = new LatLng(user.getLatitude(), user.getLongitude());
            CollectionOfLocations.getList().add(new LocationDemo(username, latLng));
        }
    }

    public static void setTempUser(String username, String email, String password){
        Log.i("test1" , "username: " + username);
        Log.i("test1" , "email: " + email);
        Log.i("test1" , "password: " + password);
        tempUser = new Users(username, email);
        tempPassword = password;
    }

    public static Users getTempUser(){
        return tempUser;
    }
    public static String getTempPassword(){
        return tempPassword;
    }
}
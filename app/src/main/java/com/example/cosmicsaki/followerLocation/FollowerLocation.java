package com.example.cosmicsaki.followerLocation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.example.cosmicsaki.R;
import com.example.cosmicsaki.auth.FirebaseManager;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class FollowerLocation extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private int placedMarker = 0;
    private Marker marker = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follower_location);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                markerPlaced(latLng);
            }
        });
    }

    private void markerPlaced(final LatLng latLng) {
        if (marker == null) {
            MarkerOptions markFirst = new MarkerOptions().position(latLng);
            setMarker(latLng, markFirst);
        } else {
            marker.remove();
            MarkerOptions markSecond = new MarkerOptions().position(latLng);
            setMarker(latLng, markSecond);
        }
    }

    private void setMarker(final LatLng latLng, final MarkerOptions mark) {
        final AlertDialog.Builder alertBox = new AlertDialog.Builder(FollowerLocation.this);
        alertBox.setTitle("Is this your location: " +
                FirebaseManager.getTempUser().getUsername());

        alertBox.setPositiveButton("Save Location", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                marker = mMap.addMarker(mark);
                marker.setTitle(FirebaseManager.getTempUser().getUsername());
                FirebaseManager.signUpWithLocation(
                        FirebaseManager.getTempUser().getUsername(),
                        FirebaseManager.getTempUser().getEmail(),
                        FirebaseManager.getTempPassword(),
                        latLng.latitude,
                        latLng.longitude);
                marker.showInfoWindow();
            }
        })
                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton("Cancel", null)
                .show();
    }
}

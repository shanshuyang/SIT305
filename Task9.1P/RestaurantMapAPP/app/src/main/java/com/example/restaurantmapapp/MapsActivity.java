package com.example.restaurantmapapp;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    double latitude, longitude;
    double[] latitudeArray, longitudeArray;
    int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();
        num = intent.getIntExtra("num",2);
        if(num == 1) {
            latitude = intent.getDoubleExtra("latitude", 0);
            longitude = intent.getDoubleExtra("longitude", 0);
        }
        else if (num == 0) {
            latitudeArray = intent.getDoubleArrayExtra("latitudeArray");
            longitudeArray = intent.getDoubleArrayExtra("longitudeArray");
        }
        else{
            Toast.makeText(MapsActivity.this, "Error", Toast.LENGTH_LONG).show();
        }
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
        switch (num){
            case 0:
                for (int i = 0; i < latitudeArray.length; i++){
                    if (latitudeArray[i] != 0 && longitudeArray[i] != 0) {
                        LatLng restaurant = new LatLng(latitudeArray[i], longitudeArray[i]);
                        mMap.addMarker(new MarkerOptions().position(restaurant).title("Marker is here"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(restaurant));
                    }
                }
                break;
            case 1:
                LatLng restaurant = new LatLng(latitude, longitude);
                mMap.addMarker(new MarkerOptions().position(restaurant).title("Marker is here"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(restaurant));
                break;
            case 2:
                Toast.makeText(MapsActivity.this, "Error", Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }

        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
    }
}
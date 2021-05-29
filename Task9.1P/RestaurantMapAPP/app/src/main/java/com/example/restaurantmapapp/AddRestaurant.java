package com.example.restaurantmapapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddRestaurant extends AppCompatActivity {
    TextView placeNameTextView, locationTextView;
    String placeName, location;
    double latitude, longitude;
    double[] latitudeArray;
    double[] longitudeArray;
    int counts;
    int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant);
        placeNameTextView = findViewById(R.id.placeNameTextView);
        locationTextView = findViewById(R.id.locationTextView);

        Intent intent = getIntent();
        num = intent.getIntExtra("num", 1);
        if (num == 0){
            counts = intent.getIntExtra("counts",0);
            latitudeArray = intent.getDoubleArrayExtra("latitudeArray");
            longitudeArray = intent.getDoubleArrayExtra("longitudeArray");
        }
        else if (num == 2) {
            placeName = intent.getStringExtra("name");
            location = intent.getStringExtra("location");
            latitude = intent.getDoubleExtra("latitude", 0);
            longitude = intent.getDoubleExtra("longitude", 0);
            counts = intent.getIntExtra("counts",0);
            latitudeArray = intent.getDoubleArrayExtra("latitudeArray");
            longitudeArray = intent.getDoubleArrayExtra("longitudeArray");
            placeNameTextView.setText(intent.getStringExtra("name"));
            locationTextView.setText(intent.getStringExtra("location"));
        }
        else{
            Toast.makeText(AddRestaurant.this, "Error", Toast.LENGTH_LONG).show();
        }
    }

    public void clickGetLocation(View view) {
        Intent intent = new Intent(AddRestaurant.this, PickRestaurant.class);
        intent.putExtra("counts", counts);
        intent.putExtra("latitudeArray", latitudeArray);
        intent.putExtra("longitudeArray", longitudeArray);
        startActivity(intent);
    }

    public void clickShowLocation(View view) {
        Intent intent = new Intent(AddRestaurant.this, MapsActivity.class);
        intent.putExtra("latitude", latitude);
        intent.putExtra("longitude", longitude);
        intent.putExtra("num", 1);
        startActivity(intent);
    }

    public void clickSave(View view) {
        Intent intent = new Intent(AddRestaurant.this, MainActivity.class);
        intent.putExtra("latitude", latitude);
        intent.putExtra("longitude", longitude);
        intent.putExtra("counts", counts);
        intent.putExtra("latitudeArray", latitudeArray);
        intent.putExtra("longitudeArray", longitudeArray);
        intent.putExtra("num", 1);
        startActivity(intent);
    }

}
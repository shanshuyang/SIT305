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

import java.util.Arrays;

public class PickRestaurant extends AppCompatActivity {
    TextView pickPlaceTextView, pickLocationTextView, pickLatitudeTextView, pickLongitudeTextView;
    String placeName, location;
    double latitude, longitude;
    double[] latitudeArray;
    double[] longitudeArray;
    int counts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_restaurant);
        pickPlaceTextView = findViewById(R.id.pickPlaceTextView);
        pickLocationTextView = findViewById(R.id.pickLocationTextView);
        pickLatitudeTextView = findViewById(R.id.pickLatitudeTextView);
        pickLongitudeTextView = findViewById(R.id.pickLongitudeTextView);
        Intent intent = getIntent();
        counts = intent.getIntExtra("counts",0);
        latitudeArray = intent.getDoubleArrayExtra("latitudeArray");
        longitudeArray = intent.getDoubleArrayExtra("longitudeArray");

        Places.initialize(getApplicationContext(), getString(R.string.Places_API));
        PlacesClient placesClient = Places.createClient(this);

        // Initialize the AutocompleteSupportFragment.
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        // Specify the types of place data to return.
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG, Place.Field.ADDRESS));

        // Set up a PlaceSelectionListener to handle the response.
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                // TODO: Get info about the selected place.
                Toast.makeText(PickRestaurant.this, "Place: " + place.getName() + ", " + place.getLatLng().toString() + ", " + place.getAddress(), Toast.LENGTH_LONG).show();
                placeName = place.getName();
                location = place.getAddress();
                latitude = place.getLatLng().latitude;
                longitude = place.getLatLng().longitude;
                pickPlaceTextView.setText(placeName);
                pickLocationTextView.setText(location);
                pickLatitudeTextView.setText(Double.toString(latitude));
                pickLongitudeTextView.setText(Double.toString(longitude));
            }
            @Override
            public void onError(@NonNull Status status) {
                // TODO: Handle the error.
                Toast.makeText(PickRestaurant.this, "An error occurred: " + status, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void clickConfirm(View view) {
        Intent intent = new Intent(PickRestaurant.this, AddRestaurant.class);
        intent.putExtra("name", placeName);
        intent.putExtra("location", location);
        intent.putExtra("latitude", latitude);
        intent.putExtra("longitude", longitude);
        intent.putExtra("counts", counts);
        intent.putExtra("latitudeArray", latitudeArray);
        intent.putExtra("longitudeArray", longitudeArray);
        intent.putExtra("num", 2);
        startActivity(intent);
    }
}
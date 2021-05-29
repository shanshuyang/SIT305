package com.example.restaurantmapapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    double[] latitudeArray = new double[10];
    double[] longitudeArray = new double[10];
    int num = 0;
    int counts = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intentFromAdd = getIntent();
        num = intentFromAdd.getIntExtra("num",0);
        if (num == 1) {
            counts = intentFromAdd.getIntExtra("counts", 0);
            latitudeArray = intentFromAdd.getDoubleArrayExtra("latitudeArray");
            longitudeArray = intentFromAdd.getDoubleArrayExtra("longitudeArray");
            latitudeArray[counts] = intentFromAdd.getDoubleExtra("latitude", 0);
            longitudeArray[counts] = intentFromAdd.getDoubleExtra("longitude", 0);
        }
        else{
            Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
        }

        if (latitudeArray[counts] != 0 && longitudeArray[counts] != 0) {
            counts++;
            Toast.makeText(MainActivity.this, "Saved successfully!", Toast.LENGTH_LONG).show();
        }
        else{
            if (counts != 0) {
                Toast.makeText(MainActivity.this, "Failed to save. Please select a restaurant again!", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void clickAdd(View view) {
        Intent intent = new Intent(MainActivity.this, AddRestaurant.class);
        intent.putExtra("counts", counts);
        intent.putExtra("latitudeArray", latitudeArray);
        intent.putExtra("longitudeArray", longitudeArray);
        intent.putExtra("num", 0);
        startActivity(intent);
    }

    public void clickShowAll(View view) {
        Intent intent = new Intent(MainActivity.this, MapsActivity.class);
        intent.putExtra("latitudeArray", latitudeArray);
        intent.putExtra("longitudeArray", longitudeArray);
        intent.putExtra("num", 0);
        startActivity(intent);
    }
}
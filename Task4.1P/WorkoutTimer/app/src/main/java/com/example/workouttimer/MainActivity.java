package com.example.workouttimer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String LAST_TEXT;
    String TIMER;
    String CLICK_COUNT;
    long pauseTime = 0;
    Integer clickCount = 0;
    TextView lastHourTextView;
    EditText workoutTypeEditText;
    Chronometer chronometer;
    SharedPreferences sharedPrefString;
    SharedPreferences sharedPrefLong;
    SharedPreferences sharedPrefInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPrefString = getSharedPreferences("saveString", Context.MODE_PRIVATE);
        sharedPrefLong = getSharedPreferences("saveLong", Context.MODE_PRIVATE);
        sharedPrefInt = getSharedPreferences("saveInt", Context.MODE_PRIVATE);
        lastHourTextView =findViewById(R.id.lastHoursTextView);
        chronometer = findViewById(R.id.chronometer);
        chronometer.setFormat("00:%s");
        if(savedInstanceState != null){
            lastHourTextView.setText(sharedPrefString.getString(LAST_TEXT,""));
            clickCount = sharedPrefInt.getInt(CLICK_COUNT, 0);
            chronometer.setBase(sharedPrefLong.getLong(TIMER, SystemClock.elapsedRealtime()));
            if (clickCount == 1) {
                chronometer.start();
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        SharedPreferences.Editor editorInt = sharedPrefInt.edit();
        editorInt.putInt(CLICK_COUNT, clickCount);
        editorInt.apply();
        SharedPreferences.Editor editorLong = sharedPrefLong.edit();
        if(clickCount == 1){
            editorLong.putLong(TIMER, chronometer.getBase());
        }
        else{
            editorLong.putLong(TIMER, SystemClock.elapsedRealtime());
        }
        editorLong.apply();
        SharedPreferences.Editor editorString = sharedPrefString.edit();
        editorString.putString(LAST_TEXT, lastHourTextView.getText().toString());
        editorString.apply();
    }

    public void startClick(View view) {
        if (clickCount == 0) {
            if (pauseTime != 0) {
                chronometer.setBase(chronometer.getBase() + SystemClock.elapsedRealtime() - pauseTime);
            }
            else {
                chronometer.setBase(SystemClock.elapsedRealtime());
            }
            chronometer.start();
            clickCount++;
        }
    }

    public void pauseClick(View view) {
        if (clickCount == 1) {
            pauseTime = SystemClock.elapsedRealtime();
            chronometer.stop();
            clickCount--;
        }
    }

    public void stopClick(View view) {
        chronometer.stop();
        workoutTypeEditText = findViewById(R.id.workoutTypeEditText);
        lastHourTextView.setText("You spend " + chronometer.getText().toString() +" on "+ workoutTypeEditText.getText().toString() +" last time.");
        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseTime = 0;
        clickCount = 0;
    }
}
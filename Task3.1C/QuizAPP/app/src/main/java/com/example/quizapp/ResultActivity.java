package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class ResultActivity extends AppCompatActivity {
    Integer correctNumber;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        DealActivity.getInstance().addActivity(this);
        Intent intentFromSecond = getIntent();
        correctNumber = intentFromSecond.getIntExtra("correctNumber",0);
        name = intentFromSecond.getStringExtra("name");
        TextView congratulationsTextView = findViewById(R.id.congratulationsTextView);
        congratulationsTextView.setText("Congratulations "+ name +"!");
        TextView scoreTextView = findViewById(R.id.scoreTextView);
        scoreTextView.setText(correctNumber.toString()+"/5");
    }

    public void takeNewQuiz(View view) {
        Intent intentToMain = new Intent(this, MainActivity.class);
        intentToMain.putExtra("name", name);
        startActivity(intentToMain);
    }

    public void closeAPP(View view) {
        DealActivity.getInstance().exit();
    }
}
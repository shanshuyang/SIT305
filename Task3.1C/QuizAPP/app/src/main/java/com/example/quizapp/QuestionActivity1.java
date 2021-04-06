package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionActivity1 extends AppCompatActivity {
    Integer answerNumber = 0;
    Integer submitNumber = 0;
    Integer correctNumber = 0;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question1);
        DealActivity.getInstance().addActivity(this);
        Intent intentFromMain = getIntent();
        name = intentFromMain.getStringExtra("name");
        TextView welcomeNameTextView = findViewById(R.id.welcomeNameTextView);
        welcomeNameTextView.setText("Welcome " + name);
    }

    public void selectAnswer(View view) {
        switch (view.getId()) {
            case R.id.answer1:
                answerNumber = 1;
                break;
            case R.id.answer2:
                answerNumber = 2;
                break;
            case R.id.answer3:
                answerNumber = 3;
                break;
            default:
                answerNumber = 0;
                break;
        }
    }

    public void submitAndNext(View view) {
        Button answer1 = findViewById(R.id.answer1);
        Button answer2 = findViewById(R.id.answer2);
        Button answer3 = findViewById(R.id.answer3);
        Button submitAndNext = findViewById(R.id.nextButton);
        if(submitNumber == 0) {
            if (answerNumber == 0) {
                Toast.makeText(this, "Please choose a answer", Toast.LENGTH_LONG).show();
            }
            else if (answerNumber == 1) {
                answer1.setBackgroundResource(R.drawable.button_false_background);
                answer3.setBackgroundResource(R.drawable.button_correct_background);
                submitAndNext.setText("Next");
                submitNumber++;
            }
            else if (answerNumber == 2) {
                answer2.setBackgroundResource(R.drawable.button_false_background);
                answer3.setBackgroundResource(R.drawable.button_correct_background);
                submitAndNext.setText("Next");
                submitNumber++;
            }
            else if (answerNumber == 3) {
                answer3.setBackgroundResource(R.drawable.button_correct_background);
                submitAndNext.setText("Next");
                submitNumber++;
                correctNumber++;
            }
        }
        else if(submitNumber == 1){
            Intent intentToNext = new Intent(getApplicationContext(), QuestionActivity2.class);
            intentToNext.putExtra("correctNumber", correctNumber);
            intentToNext.putExtra("name",name);
            startActivity(intentToNext);
        }
    }
}
package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    String name;
    EditText nameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DealActivity.getInstance().addActivity(this);
        Intent intentFromResult = getIntent();
        name = intentFromResult.getStringExtra("name");
        nameEditText = findViewById(R.id.nameEditText);
        nameEditText.setText(name);
    }

    public void startClick(View view) {
        name = nameEditText.getText().toString();
        Intent intentToQuiz = new Intent(getApplicationContext(), QuestionActivity1.class);
        intentToQuiz.putExtra("name",name);
        startActivity(intentToQuiz);
    }
}
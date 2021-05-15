package com.example.foodrescueapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodrescueapp.data.DatabaseHelper;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText usernameEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usernameEditText = findViewById(R.id.mainUsername);
        passwordEditText = findViewById(R.id.mainPassword);
        db = new DatabaseHelper(this);
    }

    public void clickLogin(View view) {
        boolean result = db.fetchUser(usernameEditText.getText().toString(), passwordEditText.getText().toString());
        if (result){
            Toast.makeText(MainActivity.this, "Successfully logged in!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(MainActivity.this, "The user does not exist.", Toast.LENGTH_SHORT).show();
        }
    }

    public void clickSignUp(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }


}
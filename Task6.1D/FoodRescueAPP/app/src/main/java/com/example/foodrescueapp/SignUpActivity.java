package com.example.foodrescueapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodrescueapp.data.DatabaseHelper;
import com.example.foodrescueapp.model.User;

public class SignUpActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText nameEditText, passwordEditText, confirmPasswordEditText, emailEditText, phoneEditText, addressEditText;
    String username, password, confirmPassword, email, phone, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        db = new DatabaseHelper(this);
        nameEditText = findViewById(R.id.signUpName);
        passwordEditText = findViewById(R.id.signUpPassword);
        confirmPasswordEditText = findViewById(R.id.signUpConfirm);
        emailEditText = findViewById(R.id.signUpEmail);
        phoneEditText = findViewById(R.id.signUpPhone);
        addressEditText = findViewById(R.id.signUpAddress);
    }

    public void clickSave(View view) {
        username = nameEditText.getText().toString();
        password = passwordEditText.getText().toString();
        confirmPassword = confirmPasswordEditText.getText().toString();
        email = emailEditText.getText().toString();
        phone = phoneEditText.getText().toString();
        address = addressEditText.getText().toString();
        if (password.equals(confirmPassword)){
            long result = db.insertUser(new User(username, password, email, phone, address));
            if (result > 0){
                Toast.makeText(SignUpActivity.this, "Registered successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(SignUpActivity.this, "Registration error!", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(SignUpActivity.this, "Two passwords do not match!", Toast.LENGTH_SHORT).show();
        }
    }
}
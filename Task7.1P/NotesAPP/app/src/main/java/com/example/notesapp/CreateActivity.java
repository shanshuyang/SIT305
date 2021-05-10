package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notesapp.data.DatabaseHelper;
import com.example.notesapp.model.Note;

public class CreateActivity extends AppCompatActivity {
    EditText noteEditText;
    String noteContent;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat);
    }

    public void clickSave(View view) {
        long result;
        db = new DatabaseHelper(this);
        noteEditText = findViewById(R.id.noteEditText);
        noteContent = noteEditText.getText().toString();
        Note note = new Note(noteContent);
        result = db.insertNote(note);
        if (result > 0){
            Toast.makeText(this, "Saved successfully!", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Save failed!", Toast.LENGTH_LONG).show();
        }
        Intent intent = new Intent(CreateActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
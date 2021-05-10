package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notesapp.data.DatabaseHelper;
import com.example.notesapp.model.Note;

import java.util.List;

public class NotesActivity extends AppCompatActivity {
    Intent intentFormShow;
    TextView titleTextView;
    EditText noteTextView;
    String noteContent;
    int position;
    Note note;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        titleTextView = findViewById(R.id.titleTextView);
        noteTextView = findViewById(R.id.noteTextView);
        note = new Note();
        db = new DatabaseHelper(this);
        intentFormShow = getIntent();
        position = intentFormShow.getIntExtra("position", 0);
        titleTextView.setText("Note" + (position + 1));
        List<Note> noteList = db.fetchAllNotes();
        noteContent = noteList.get(position).getNote();
        noteTextView.setText(noteContent);
        note = noteList.get(position);
    }

    public void clickUpdate(View view) {
        String noteContent= noteTextView.getText().toString();
        note.setNote(noteContent);
        int result = db.updateNote(note);
        if (result != 0){
            Toast.makeText(NotesActivity.this, "Update successfully!", Toast.LENGTH_LONG).show();
        }
        Intent intent = new Intent(NotesActivity.this, ShowActivity.class);
        startActivity(intent);
    }

    public void clickDelete(View view) {
        db.deleteNote(note);
        Intent intent = new Intent(NotesActivity.this, ShowActivity.class);
        startActivity(intent);
    }
}
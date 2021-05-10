package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.notesapp.data.DatabaseHelper;
import com.example.notesapp.model.Note;

import java.util.ArrayList;
import java.util.List;

public class ShowActivity extends AppCompatActivity {
    ListView noteListView;
    ArrayList<String> noteIdArrayList;
    ArrayAdapter<String> adapter;
    int noteNumber;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        noteListView = findViewById(R.id.listView);
        noteIdArrayList = new ArrayList<>();
        db = new DatabaseHelper(ShowActivity.this);
        noteNumber = 1;
        List<Note> noteList = db.fetchAllNotes();
        for (Note note : noteList) {
            noteIdArrayList.add("Note" + noteNumber);
            noteNumber++;
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, noteIdArrayList);
        noteListView.setAdapter(adapter);

        noteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ShowActivity.this, NotesActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
    }

}
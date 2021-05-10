package com.example.notesapp.model;

public class Note {
    private String note;
    private int note_id;

    public Note(String note) {
        this.note = note;
    }

    public Note(){}

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getNote_id() {
        return note_id;
    }

    public void setNote_id(int note_id) {
        this.note_id = note_id;
    }
}

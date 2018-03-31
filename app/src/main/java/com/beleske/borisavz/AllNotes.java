package com.beleske.borisavz;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AllNotes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_notes);

        TextView allNotesTextView = findViewById(R.id.allNotes);

        StringBuilder allNotesText = new StringBuilder();

        List<Note> allNotes = NoteDatabase.getNoteDatabase(getApplicationContext()).noteDao().getAllNotes();
        Collections.reverse(allNotes);

        if(allNotes.size() == 0) {
            Toast.makeText(this, "Нема белешки!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        for(Note note : allNotes) {
            allNotesText.append(note.getNoteContent() + "\n-----\n");
        }
        allNotesTextView.setText(allNotesText.toString());
    }
}

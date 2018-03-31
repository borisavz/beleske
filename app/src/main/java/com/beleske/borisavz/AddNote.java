package com.beleske.borisavz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;

public class AddNote extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        Button saveButton = findViewById(R.id.saveButton);

        final EditText noteContentEditText = findViewById(R.id.noteContentEditText);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date = Calendar.getInstance().getTime();
                Note note = new Note();
                note.setDateCreated(date.toString());
                System.out.println(date.toString());
                note.setNoteContent(noteContentEditText.getText().toString());
                NoteDatabase.getNoteDatabase(getApplicationContext()).noteDao().addNote(note);
                finish();
            }
        });
    }
}

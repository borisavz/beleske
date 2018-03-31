package com.beleske.borisavz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button newNoteButton = findViewById(R.id.newNote);
        Button allNotesButton = findViewById(R.id.allNotes);
        Button singleNoteButton = findViewById(R.id.singleNote);
        Button aboutButton = findViewById(R.id.about);

        final Intent addNoteIntent = new Intent(this, AddNote.class);
        final Intent allNotesIntent = new Intent(this, AllNotes.class);
        final Intent singleNoteIntent = new Intent(this, SingleNote.class);
        final Intent aboutIntent = new Intent(this, About.class);

        newNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(addNoteIntent);
            }
        });

        allNotesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(allNotesIntent);
            }
        });

        singleNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(singleNoteIntent);
            }
        });

        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(aboutIntent);
            }
        });
    }
}

package com.beleske.borisavz;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

public class SingleNote extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_note);

        final int[] noteId = new int[1];
        final int[] notesSize = new int[1];

        final Button nextNoteButton = findViewById(R.id.nextNote);
        final Button previousNoteButton = findViewById(R.id.previousNote);
        final Button deleteNoteButton = findViewById(R.id.deleteNote);

        final TextView showNoteTextView = findViewById(R.id.showNote);
        final TextView notePositionTextView = findViewById(R.id.notePosition);
        final TextView noteDateTextView = findViewById(R.id.noteDate);

        final List<Note> allNotes = NoteDatabase.getNoteDatabase(getApplicationContext()).noteDao().getAllNotes();
        notesSize[0] = allNotes.size();
        Collections.reverse(allNotes);

        System.out.println(notesSize);

        if(notesSize[0] == 0) {
            Toast.makeText(this, "Нема белешки!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        showNoteTextView.setText(allNotes.get(0).getNoteContent());
        noteDateTextView.setText(allNotes.get(0).getDateCreated());
        notePositionTextView.setText("1/" + notesSize[0]);

        final AlertDialog.Builder altBx = new AlertDialog.Builder(this);
        altBx.setTitle("Обриши белешку");
        altBx.setMessage("Да ли сте сигурни да желите да обришете белешку?");

        altBx.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                NoteDatabase.getNoteDatabase(getApplicationContext()).noteDao().removeNote(allNotes.get(noteId[0]));
                allNotes.remove(noteId[0]);
                notesSize[0]--;

                if(notesSize[0] == 0) {
                    finish();
                    return;
                }

                if(noteId[0] == 0) {
                    if(notesSize[0] != 1) {
                        noteId[0]++;
                    }
                } else {
                    noteId[0]--;
                }

                showNoteTextView.setText(allNotes.get(noteId[0]).getNoteContent());
                noteDateTextView.setText(allNotes.get(noteId[0]).getDateCreated());
                notePositionTextView.setText((noteId[0] + 1) + "/" + notesSize[0]);
            }
        });
        altBx.setNegativeButton("Не", null);

        nextNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(noteId[0] != 0) {
                    noteId[0]--;
                } else {
                    noteId[0] = notesSize[0] - 1;
                }
                showNoteTextView.setText(allNotes.get(noteId[0]).getNoteContent());
                noteDateTextView.setText(allNotes.get(noteId[0]).getDateCreated());
                notePositionTextView.setText((noteId[0] + 1) + "/" + notesSize[0]);
            }
        });

        previousNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(noteId[0] != (notesSize[0] - 1)) {
                    noteId[0]++;
                } else {
                    noteId[0] = 0;
                }
                showNoteTextView.setText(allNotes.get(noteId[0]).getNoteContent());
                noteDateTextView.setText(allNotes.get(noteId[0]).getDateCreated());
                notePositionTextView.setText((noteId[0] + 1) + "/" + notesSize[0]);
            }
        });

        deleteNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                altBx.show();
            }
        });
    }
}

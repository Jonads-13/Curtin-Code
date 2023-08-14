package com.example.notes;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> notes = new ArrayList<>();
    private Button[] buttons = new Button[5];
    private boolean[] buttonVisibility = {true, false, false, false, false};
    public static final String NOTE_CONTENT = "noteContent";
    public static final String INDEX = "index";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttons[0] = findViewById(R.id.AddNoteButton);
        buttons[1] = findViewById(R.id.Note1Button);
        buttons[2] = findViewById(R.id.Note2Button);
        buttons[3] = findViewById(R.id.Note3Button);
        buttons[4] = findViewById(R.id.Note4Button);

        ActivityResultLauncher<Intent> addNoteActivityLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                Intent data = result.getData();
                notes.add(data.getStringExtra(NOTE_CONTENT));

                if(notes.size() == 4) {
                    buttons[0].setVisibility(View.INVISIBLE);
                    buttonVisibility[0] = false;
                }

                buttons[notes.size()].setVisibility(View.VISIBLE);
                buttonVisibility[notes.size()] = true;
            }
        });

        ActivityResultLauncher<Intent> editNoteActivityLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                Intent data = result.getData();
                int i = data.getIntExtra(INDEX, -1);
                notes.set(i, data.getStringExtra(NOTE_CONTENT));
            }
        });

        if(savedInstanceState!= null) {
            buttonVisibility = savedInstanceState.getBooleanArray("visibilityArray");
            notes = savedInstanceState.getStringArrayList("notesList");
            int i = 0;
            for(Button button: buttons) {
                if(!buttonVisibility[i]) {
                    button.setVisibility(View.INVISIBLE);
                }
                i++;
            }
        }

        else {
            for(Button button: buttons) {
                button.setVisibility(View.INVISIBLE);
            }
            buttons[0].setVisibility(View.VISIBLE);
        }

        // AddNoteButton
        buttons[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                addNoteActivityLauncher.launch(intent);
            }
        });

        // Note1Button
        buttons[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                int index = 0;
                intent.putExtra(NOTE_CONTENT, notes.get(index));
                intent.putExtra(INDEX, index);
                editNoteActivityLauncher.launch(intent);

            }
        });

        // Note2Button
        buttons[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                int index = 1;
                intent.putExtra(NOTE_CONTENT, notes.get(index));
                intent.putExtra(INDEX, index);
                editNoteActivityLauncher.launch(intent);

            }
        });

        // Note3Button
        buttons[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                int index = 2;
                intent.putExtra(NOTE_CONTENT, notes.get(index));
                intent.putExtra(INDEX, index);
                editNoteActivityLauncher.launch(intent);

            }
        });

        // Note4Button
        buttons[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                int index = 3;
                intent.putExtra(NOTE_CONTENT, notes.get(index));
                intent.putExtra(INDEX, index);
                editNoteActivityLauncher.launch(intent);

            }
        });
    }

    @Override
    public void onSaveInstanceState(@NotNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBooleanArray("visibilityArray", buttonVisibility);
        outState.putStringArrayList("notesList", notes);
    }
}
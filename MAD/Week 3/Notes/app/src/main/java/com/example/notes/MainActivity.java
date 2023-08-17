package com.example.notes;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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
    private static final String NOTE_CONTENT = "noteContent";
    private static final String INDEX = "index";
    private static final String VISIBILITY_ARRAY = "visibilityArray";
    private static final String NOTES_LIST = "notesList";


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
            buttonVisibility = savedInstanceState.getBooleanArray(VISIBILITY_ARRAY);
            notes = savedInstanceState.getStringArrayList(NOTES_LIST);
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
                int index = 0;
                Intent intent = getNoteActivityIntent(MainActivity.this, notes.get(index), index);
                editNoteActivityLauncher.launch(intent);

            }
        });

        // Note2Button
        buttons[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = 1;
                Intent intent = getNoteActivityIntent(MainActivity.this, notes.get(index), index);
                editNoteActivityLauncher.launch(intent);

            }
        });

        // Note3Button
        buttons[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = 2;
                Intent intent = getNoteActivityIntent(MainActivity.this, notes.get(index), index);
                editNoteActivityLauncher.launch(intent);

            }
        });

        // Note4Button
        buttons[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = 3;
                Intent intent = getNoteActivityIntent(MainActivity.this, notes.get(index), index);
                editNoteActivityLauncher.launch(intent);

            }
        });
    }

    public static Intent getNoteActivityIntent(Context c, String note, int index)
    {
        Intent intent = new Intent(c, NoteActivity.class);
        intent.putExtra(NOTE_CONTENT, note);
        intent.putExtra(INDEX, index);
        return intent;
    }

    public static Intent getMainActivityIntent(String note, int index)
    {
        Intent intent = new Intent();
        intent.putExtra(NOTE_CONTENT, note);
        intent.putExtra(INDEX, index);
        return intent;

    }

    public static String getStringFromIntent(Intent intent)
    {
        return intent.getStringExtra(NOTE_CONTENT);
    }

    public static int getIntFromIntent(Intent intent)
    {
        return intent.getIntExtra(INDEX, -1);
    }

    @Override
    public void onSaveInstanceState(@NotNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBooleanArray(VISIBILITY_ARRAY, buttonVisibility);
        outState.putStringArrayList("notesList", notes);
    }
}
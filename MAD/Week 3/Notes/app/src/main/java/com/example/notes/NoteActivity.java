package com.example.notes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class NoteActivity extends AppCompatActivity {
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_note_activity);

        EditText noteBox = findViewById(R.id.noteContent);
        Button backButton = findViewById(R.id.saveButton);

        Intent data = getIntent();

        String note = data.getStringExtra(MainActivity.NOTE_CONTENT);
        int index = data.getIntExtra(MainActivity.INDEX, -1);
        if(note != null) {
            noteBox.setText(note);
        }


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(MainActivity.NOTE_CONTENT, noteBox.getText().toString());
                intent.putExtra(MainActivity.INDEX, index);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}

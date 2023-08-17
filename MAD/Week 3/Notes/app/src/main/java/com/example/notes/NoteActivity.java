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

        String note = MainActivity.getStringFromIntent(data);
        int index = MainActivity.getIntFromIntent(data);
        if(note != null) {
            noteBox.setText(note);
        }


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = MainActivity.getMainActivityIntent(noteBox.getText().toString(), index);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}

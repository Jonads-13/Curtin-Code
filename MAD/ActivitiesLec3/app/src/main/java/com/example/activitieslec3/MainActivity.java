package com.example.activitieslec3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button button;
    Button singletonButton;
    EditText title;
    EditText counter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.menuButton);
        singletonButton = findViewById(R.id.singTonButton);
        title = findViewById(R.id.title);
        counter = findViewById(R.id.startingValue);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startSavingStateActivity();
            }
        });
        singletonButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                startActivityUsingSingleTon();
            }
        });

    }
    private void startSavingStateActivity(){
        String inputTitle = title.getText().toString();
        int inputCounter = Integer.parseInt(counter.getText().toString());
        Intent intent = SavingStateActivity.getSavingStateActivityIntent(this,inputTitle, inputCounter);
        startActivity(intent);
    }

    private void startActivityUsingSingleTon(){
        MyDataManager myDataManager = MyDataManager.getInstance();
        myDataManager.setTitle(title.getText().toString());
        myDataManager.setCounter(Integer.parseInt(counter.getText().toString()));
        Intent intent = new Intent(this, SavingStateActivity.class);
        intent.putExtra("button_pressed", "singleton");
        startActivity(intent);
    }
}



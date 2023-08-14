package com.example.adaptiveui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b1 = findViewById(R.id.mButton1);
        Button b2 = findViewById(R.id.mButton2);
        Button b3 = findViewById(R.id.mButton3);
        Button b4 = findViewById(R.id.mButton4);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(),NestedLayouts.class);
                startActivity(i);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), GuideLineActivity.class);
                startActivity(i);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(),DrawableActivity.class);
                startActivity(i);
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(),ConstraintLayoutActivity.class);
                startActivity(i);
            }
        });
    }
}
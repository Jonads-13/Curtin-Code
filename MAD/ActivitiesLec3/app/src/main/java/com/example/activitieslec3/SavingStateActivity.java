package com.example.activitieslec3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class SavingStateActivity extends AppCompatActivity {
    Button button;
    TextView counterView;
    TextView titleView;
    int i;
    private static final String COUNTER ="com.example.counter";
    private static final String TITLE = "com.example.title";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saving_state);
        button = findViewById(R.id.button);
        counterView = findViewById(R.id.textView);
        titleView = findViewById(R.id.title);
        updateView();
        if(savedInstanceState!=null) {
            String value = savedInstanceState.getString(COUNTER);
            counterView.setText(value);
            value = savedInstanceState.getString(TITLE);
            titleView.setText(value);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = i+1;
                counterView.setText(String.valueOf(i));
            }
        });
    }
    private void updateView(){
        Intent intent = getIntent();
        String menu = intent.getStringExtra("button_pressed");
        if(menu!=null)
        {
            if(menu.equals("singleton")){
                updateViewWithSingleTon();
            }
        }
        else {
            String title = intent.getStringExtra(TITLE);
            if (title != null) {
                titleView.setText(title);
            }
            i = intent.getIntExtra(COUNTER, 0);
            counterView.setText(String.valueOf(i));
        }
    }

    private void updateViewWithSingleTon(){
        MyDataManager myDataManager = MyDataManager.getInstance();
        titleView.setText(myDataManager.getTitle());
        i = myDataManager.getCounter();
        counterView.setText(String.valueOf(i));
    }
    public static Intent getSavingStateActivityIntent(Context c, String title, int counter){
        Intent intent = new Intent(c, SavingStateActivity.class);
        intent.putExtra(TITLE,title);
        intent.putExtra(COUNTER,counter);
        return intent;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(COUNTER,counterView.getText().toString());
        outState.putString(TITLE,titleView.getText().toString());
    }
}



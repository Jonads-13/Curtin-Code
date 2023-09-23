package com.example.islandbuilder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private MapFragment mapFrag = new MapFragment();
    private SelectorFragment selectFrag = new SelectorFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadNewFrag(R.id.map, mapFrag);
        loadNewFrag(R.id.selector, selectFrag);
    }

    private void loadNewFrag( int id, Fragment newFrag )
    {
        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentById(id);
        if( frag == null )
        {
            fm.beginTransaction().add( id, newFrag ).commit();
        }
        else
        {
            fm.beginTransaction().replace( id, newFrag ).commit();
        }
    }
}
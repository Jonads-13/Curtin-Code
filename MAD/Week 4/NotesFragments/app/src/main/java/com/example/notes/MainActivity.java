package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.res.Configuration;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    //HeaderFragment headerFrag = new HeaderFragment();
    MenuFragment menuFrag = new MenuFragment();
    NoteFragment noteFrag = new NoteFragment();

    private String note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int orientation = getResources().getConfiguration().orientation;

        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            loadFragment(R.id.main_f, menuFrag);
            MainActivityData mainActivityDataViewModel = new ViewModelProvider(this)
                    .get(com.example.notes.MainActivityData.class);
            mainActivityDataViewModel.clickedValue.observe(this, new Observer<Integer>() {
                @Override
                public void onChanged(Integer integer) {
                    if(mainActivityDataViewModel.getClickedValue() == R.integer.MENU){
                        loadFragment(R.id.main_f, menuFrag);
                    } else if (mainActivityDataViewModel.getClickedValue() == R.integer.NOTE) {
                        loadFragment(R.id.main_f, noteFrag);
                    }
                }
            });
        }
        else if(orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            loadFragment(R.id.menu_f, menuFrag);
            loadFragment(R.id.note_f, noteFrag);
        }
    }

     private void loadFragment(int id, Fragment f) {
         FragmentManager fm = getSupportFragmentManager();
         Fragment frag = fm.findFragmentById(id);
         if (frag == null) {
             fm.beginTransaction().add(id, f).commit();
         } else {
             fm.beginTransaction().replace(id, f).commit();
         }
     }
}
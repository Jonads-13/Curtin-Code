package com.example.notes;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityData extends ViewModel {
    public MutableLiveData<Integer> clickedValue;
    public MutableLiveData<String> note;
    private String noteTitle;

    public MainActivityData(){
        clickedValue = new MediatorLiveData<>();
        clickedValue.setValue(R.integer.MENU);
        note = new MediatorLiveData<>();
        noteTitle = null;
    }

    public int getClickedValue(){
        return clickedValue.getValue();
    }
    public void setClickedValue(int value){
        clickedValue.setValue(value);
    }

    public String getNote() {return note.getValue();}

    public void setNote(String n){note.setValue(n);}

    public String getNoteTitle() {return noteTitle;}

    public void setNoteTitle(String n){noteTitle = n;}



}

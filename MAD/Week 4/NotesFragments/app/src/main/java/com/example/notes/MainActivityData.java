package com.example.notes;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityData extends ViewModel {
    public MutableLiveData<Integer> clickedValue;
    private String note;

    public MainActivityData(){
        clickedValue = new MediatorLiveData<>();
        clickedValue.setValue(0);
        note = null;
    }

    public int getClickedValue(){
        return clickedValue.getValue();
    }
    public void setClickedValue(int value){
        clickedValue.setValue(value);
    }

    public String getNote() {return note;}
    public void setNote(String n){note = n;}



}

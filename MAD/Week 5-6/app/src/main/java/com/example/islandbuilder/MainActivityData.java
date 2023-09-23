package com.example.islandbuilder;

import androidx.lifecycle.ViewModel;

public class MainActivityData extends ViewModel {

    private Structure selectedStruct;

    public MainActivityData()
    {
        selectedStruct = null;
    }

    public Structure getSelectedStruct() {
        return selectedStruct;
    }

    public void setSelectedStruct(Structure selectedStruct) {
        this.selectedStruct = selectedStruct;
    }
}

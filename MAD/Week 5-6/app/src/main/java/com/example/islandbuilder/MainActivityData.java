package com.example.islandbuilder;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityData extends ViewModel {

    private Structure selectedStruct;
    private StructureVH selectedStructVH;
    private MapElement selectedMapElement;
    private MapElementVH selectedMapHolder;
    public MutableLiveData<Boolean> regenerated;

    public MainActivityData()
    {
        selectedStruct = null;
        selectedStructVH = null;
        selectedMapElement = null;
        selectedMapHolder = null;
        regenerated = new MutableLiveData<>();
    }

    public Structure getSelectedStruct() {
        return selectedStruct;
    }

    public void setSelectedStruct(Structure selectedStruct) {
        this.selectedStruct = selectedStruct;
    }

    public void setRegenerated(boolean value)
    {
        regenerated.setValue(value);
    }

    public boolean getRegenerated()
    {
        return regenerated.getValue();
    }

    public MapElement getSelectedMapElement() {
        return selectedMapElement;
    }

    public void setSelectedMapElement(MapElement selectedMapElement) {
        this.selectedMapElement = selectedMapElement;
    }

    public MapElementVH getSelectedMapHolder() {
        return selectedMapHolder;
    }

    public void setSelectedMapHolder(MapElementVH selectedMapHolder) {
        this.selectedMapHolder = selectedMapHolder;
    }

    public StructureVH getSelectedStructVH() {
        return selectedStructVH;
    }

    public void setSelectedStructVH(StructureVH selectedStructVH) {
        this.selectedStructVH = selectedStructVH;
    }
}

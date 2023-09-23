package com.example.islandbuilder;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MapElementAdapter extends RecyclerView.Adapter<MapElementVH> {

    MapData map;
    MainActivityData viewModel;

    public MapElementAdapter(MapData map, MainActivityData vm)
    {
        Log.d("DEBUG", "MapElementAdapter created");
        this.map = map;
        this.viewModel = vm;
    }
    @NonNull
    @Override
    public MapElementVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        Log.d("DEBUG", "Is this ever called?");
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.grid_cell, parent, false);
        return new MapElementVH(view, parent);
    }

    @Override
    public void onBindViewHolder(@NonNull MapElementVH holder, int position)
    {
        int row = position % MapData.HEIGHT;
        int col = position / MapData.HEIGHT;
        Log.d("DEBUG", "row: " + row + " col: " + col);
        MapElement element = map.get(row, col);
        holder.topLeft.setImageResource(element.getNorthWest());
        holder.topRight.setImageResource(element.getNorthEast());
        holder.bottomLeft.setImageResource(element.getSouthWest());
        holder.bottomRight.setImageResource(element.getSouthEast());

        holder.onTop.setOnClickListener(v -> {
            element.setStructure(viewModel.getSelectedStruct());
            holder.onTop.setImageResource(element.getStructure().getDrawableId());
            Log.d("DEBUG", "drawable id: " + element.getStructure().getDrawableId());
        });
    }

    @Override
    public int getItemCount() {
        return MapData.HEIGHT * MapData.WIDTH;
    }
}

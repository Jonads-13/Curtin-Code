package com.example.islandbuilder;

import android.util.Log;
import android.view.DragEvent;
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

        if(element.getStructure() != null)
        {
            holder.onTop.setImageResource(element.getStructure().getDrawableId());
            Log.d("DEBUG", " drawable id: " + element.getStructure().getDrawableId());
        }

        holder.onTop.setOnClickListener(v -> {
            if((element.getStructure() == null) && (element.isBuildable()))
            {
                element.setStructure(viewModel.getSelectedStruct());
                holder.onTop.setImageResource(element.getStructure().getDrawableId());
                Log.d("DEBUG ONCLICK", "drawable id: " + element.getStructure().getDrawableId());
                notifyItemChanged(holder.getAbsoluteAdapterPosition());
            }
            else if(element.getStructure() != null)
            {
                viewModel.setSelectedMapElement(element);
                viewModel.setSelectedMapHolder(holder);
            }
            Log.d("DEBUG ONCLICK", "row: " + row + " col: " + col);
        });

        holder.onTop.setOnDragListener((v, event) -> {
            if(event.getAction() == DragEvent.ACTION_DROP)
            {
                if(element.isBuildable())
                {
                    element.setStructure(viewModel.getSelectedStruct());
                    holder.onTop.setImageResource(element.getStructure().getDrawableId());
                    notifyItemChanged(holder.getAbsoluteAdapterPosition());
                }
            }
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return MapData.HEIGHT * MapData.WIDTH;
    }
}

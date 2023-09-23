package com.example.islandbuilder;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

public class StructureAdapter extends RecyclerView.Adapter<StructureVH> {

    StructureData structureList;
    MainActivityData viewModel;

    public StructureAdapter(StructureData structureList, MainActivityData vm)
    {
        this.structureList = structureList;
        this.viewModel = vm;
    }

    @NonNull
    @Override
    public StructureVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_selection, parent, false);
        return new StructureVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StructureVH holder, int position)
    {
        Structure structure = this.structureList.get(position);
        holder.structureIcon.setImageResource(structure.getDrawableId());
        holder.structureName.setText(structure.getLabel());
        Log.d("DEBUG", "row col: ");
        holder.structureIcon.setOnClickListener(v -> {
            viewModel.setSelectedStruct(structure);
        });

    }

    @Override
    public int getItemCount() {
        return structureList.size();
    }
}

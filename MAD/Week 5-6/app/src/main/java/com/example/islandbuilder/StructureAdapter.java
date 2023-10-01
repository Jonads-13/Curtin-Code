package com.example.islandbuilder;

import android.content.ClipData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        holder.structureIcon.setOnClickListener(v -> {
            if(viewModel.getSelectedStructVH() != null)
            {
                // reset background color
                viewModel.getSelectedStructVH().structureIcon.setBackgroundColor(0);
            }
            viewModel.setSelectedStructVH(holder);
            viewModel.setSelectedStruct(structure);
            holder.structureIcon.setBackgroundColor(R.color.black);
        });

        holder.structureIcon.setOnLongClickListener(v -> {
            if(viewModel.getSelectedStructVH() != null)
            {
                // reset background color
                viewModel.getSelectedStructVH().structureIcon.setBackgroundColor(0);
            }
            viewModel.setSelectedStructVH(holder);
            viewModel.setSelectedStruct(structure);
            holder.structureIcon.setBackgroundColor(R.color.black);

            View.DragShadowBuilder shadow = new View.DragShadowBuilder(holder.structureIcon);
            v.startDragAndDrop(null, shadow, null, 0);
            return true;
        });

    }

    @Override
    public int getItemCount() {
        return structureList.size();
    }
}

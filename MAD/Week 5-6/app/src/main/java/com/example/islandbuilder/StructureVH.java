package com.example.islandbuilder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StructureVH extends RecyclerView.ViewHolder {

    ImageView structureIcon;
    TextView structureName;
    public StructureVH(@NonNull View itemView) {
        super(itemView);

        structureIcon = itemView.findViewById(R.id.structure_icon);
        structureName = itemView.findViewById(R.id.structure_name);
    }
}

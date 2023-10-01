package com.example.islandbuilder;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MapElementVH extends RecyclerView.ViewHolder {

    ImageView onTop;
    ImageView topLeft;
    ImageView topRight;
    ImageView bottomLeft;
    ImageView bottomRight;

    public MapElementVH(@NonNull View itemView, ViewGroup parent)
    {
        super(itemView);

        int size = parent.getMeasuredHeight() / MapData.HEIGHT + 1;
        Log.d("DEBUG", "size: " + size);
        ViewGroup.LayoutParams lp = itemView.getLayoutParams();
        lp.width = size;
        lp.height = size;

        onTop = itemView.findViewById(R.id.on_top);
        topLeft = itemView.findViewById(R.id.top_left);
        topRight = itemView.findViewById(R.id.top_right);
        bottomLeft = itemView.findViewById(R.id.bottom_left);
        bottomRight = itemView.findViewById(R.id.bottom_right);
    }
}

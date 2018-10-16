package com.appinionbd.isoladministrativesolutions.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.appinionbd.isoladministrativesolutions.R;

public class RecyclerAdapterProductFloor extends RecyclerView.Adapter<RecyclerAdapterProductFloor.ProductFloorViewHolder>{


    @NonNull
    @Override
    public ProductFloorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductFloorViewHolder productFloorViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ProductFloorViewHolder extends RecyclerView.ViewHolder{

        TextView textViewFloorName;
        TextView textViewFloorAvailableProduct;
        TextView textViewQuantity;
        ImageView imageViewUp;
        ImageView imageViewDown;

        public ProductFloorViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewFloorName = itemView.findViewById(R.id.textView_floor_name);
            textViewFloorAvailableProduct = itemView.findViewById(R.id.textView_floor_available_product);
            textViewQuantity = itemView.findViewById(R.id.textView_quantity);
            imageViewUp = itemView.findViewById(R.id.imageView_up);
            imageViewDown = itemView.findViewById(R.id.imageView_down);
        }
    }
}

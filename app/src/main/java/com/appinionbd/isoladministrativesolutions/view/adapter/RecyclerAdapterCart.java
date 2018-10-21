package com.appinionbd.isoladministrativesolutions.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class RecyclerAdapterCart extends RecyclerView.Adapter<RecyclerAdapterCart.CartViewHolder>{

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder cartViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class CartViewHolder extends RecyclerView.ViewHolder{

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

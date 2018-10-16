package com.appinionbd.isoladministrativesolutions.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.appinionbd.isoladministrativesolutions.R;
import com.appinionbd.isoladministrativesolutions.extraLibrary.imageLibraryGlide.GlideApp;
import com.appinionbd.isoladministrativesolutions.interfaces.RecyclerAdapterProductLibraryInterface.IRecyclerAdapterProductLibrary;
import com.appinionbd.isoladministrativesolutions.model.dataModel.Product;
import com.appinionbd.isoladministrativesolutions.view.home.HomeActivity;

import java.util.List;

public class RecyclerAdapterProductLibrary extends RecyclerView.Adapter<RecyclerAdapterProductLibrary.ProductViewHolder>{

    List<Product> productList;
    Context context;
    private IRecyclerAdapterProductLibrary iRecyclerAdapterProductLibrary;

    public RecyclerAdapterProductLibrary(List<Product> productList, Context context, IRecyclerAdapterProductLibrary iRecyclerAdapterProductLibrary) {
        this.productList = productList;
        this.context = context;
        this.iRecyclerAdapterProductLibrary = iRecyclerAdapterProductLibrary;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_product , parent ,false);
        return new ProductViewHolder(view);
//        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        GlideApp.with(context)
                .load(R.drawable.no_product)
                .circleCrop()
                .into(holder.imageViewProduct);
        holder.textViewId.setText(productList.get(position).getItemName());
        holder.textViewName.setText(productList.get(position).getItemCode());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void filterList(List<Product> filterProductList){
        this.productList = filterProductList;
        notifyDataSetChanged();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder{

        ImageView imageViewProduct;
        CardView cardViewProduct;
        TextView textViewName;
        TextView textViewId;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewProduct = itemView.findViewById(R.id.imageView_product);
            cardViewProduct = itemView.findViewById(R.id.cardView_product);
            textViewName = itemView.findViewById(R.id.textView_name);
            textViewId = itemView.findViewById(R.id.textView_id);

            cardViewProduct.setOnClickListener(v -> {
                iRecyclerAdapterProductLibrary.productClicked(productList.get(getLayoutPosition()).getItemId() );
            });
        }
    }
}














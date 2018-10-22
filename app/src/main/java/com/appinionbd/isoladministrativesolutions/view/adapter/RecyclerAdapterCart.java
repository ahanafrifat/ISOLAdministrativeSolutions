package com.appinionbd.isoladministrativesolutions.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.appinionbd.isoladministrativesolutions.R;
import com.appinionbd.isoladministrativesolutions.model.dataHolder.CartProduct;

import java.util.List;

public class RecyclerAdapterCart extends RecyclerView.Adapter<RecyclerAdapterCart.CartViewHolder>{

    private List<CartProduct> cartProducts;

    public RecyclerAdapterCart(List<CartProduct> cartProducts) {
        this.cartProducts = cartProducts;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_cart_product , parent , false);
        return new CartViewHolder(view);
//        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder cartViewHolder, int position) {

        cartViewHolder.textViewCartProductName.setSelected(true);
        cartViewHolder.textViewCartProductName.setText(cartProducts.get(position).getItemName());
        cartViewHolder.textViewCartProductId.setText(cartProducts.get(position).getItemCode());
        cartViewHolder.textViewCartProductRequested.setText(cartProducts.get(position).getQuantity());

    }

    @Override
    public int getItemCount() {
        return cartProducts.size();
    }

    class CartViewHolder extends RecyclerView.ViewHolder{

        TextView textViewCartProductName;
        TextView textViewCartProductId;
        TextView textViewCartProductRequested;
        ImageView imageViewCartProductCancel;



        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewCartProductName = itemView.findViewById(R.id.textView_cart_product_name);
            textViewCartProductId = itemView.findViewById(R.id.textView_cart_product_id);
            textViewCartProductRequested = itemView.findViewById(R.id.textView_cart_product_requested);
            imageViewCartProductCancel = itemView.findViewById(R.id.imageView_cart_product_cancel);
        }
    }
}

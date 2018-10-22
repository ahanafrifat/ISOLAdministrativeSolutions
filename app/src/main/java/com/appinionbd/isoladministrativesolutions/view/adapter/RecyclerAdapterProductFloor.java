package com.appinionbd.isoladministrativesolutions.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.appinionbd.isoladministrativesolutions.R;
import com.appinionbd.isoladministrativesolutions.model.dataHolder.FloorSaved;
import com.appinionbd.isoladministrativesolutions.model.dataHolder.SavedProduct;
import com.appinionbd.isoladministrativesolutions.model.dataModel.Product;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class RecyclerAdapterProductFloor extends RecyclerView.Adapter<RecyclerAdapterProductFloor.ProductFloorViewHolder>{

    private List<FloorSaved> floorSaveds;

    public RecyclerAdapterProductFloor(List<FloorSaved> floorSaveds) {
        this.floorSaveds = floorSaveds;
    }

    @NonNull
    @Override
    public ProductFloorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_floor , parent , false);
        return new ProductFloorViewHolder(view);
//        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductFloorViewHolder holder, int position) {

        holder.textViewFloorName.setText(floorSaveds.get(position).getFloorNo());
        holder.textViewFloorAvailableProduct.setText(floorSaveds.get(position).getQuantity());

//        holder.textViewQuantity.setText(floorSaveds.get(position).getQuantity());

        holder.imageViewUp.setOnClickListener(v -> {
            addQuantity(holder.textViewFloorAvailableProduct ,
                    holder.textViewQuantity ,
                    floorSaveds.get(position).getFloorSavedId(),
                    floorSaveds.get(position).getFloorId() ,
                    floorSaveds.get(position).getItemCode()
                    );
        });

        holder.imageViewDown.setOnClickListener(v -> {
            subtractQuantity(holder.textViewFloorAvailableProduct ,
                    holder.textViewQuantity ,
                    floorSaveds.get(position).getFloorSavedId(),
                    floorSaveds.get(position).getFloorId() ,
                    floorSaveds.get(position).getItemCode());
        });
    }

    @Override
    public int getItemCount() {
        return floorSaveds.size();
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

    private void addQuantity(TextView textViewFloorAvailableProduct, TextView textViewQuantity, String floorSavedId, String floorId, String itemCode) {

        SavedProduct savedProduct = new SavedProduct();

        try (Realm realm = Realm.getDefaultInstance()) {

            SavedProduct savedProductTemp = realm.where(SavedProduct.class).equalTo("savedProductId" , floorSavedId).findFirst();

            if(savedProductTemp == null){

                Product product = realm.where(Product.class).equalTo("itemCode" , itemCode).findFirst();
                savedProduct.setSavedProductId(floorSavedId);
                savedProduct.setItemId(product.getItemId());
                savedProduct.setFloorId(floorId);
                savedProduct.setItemCode(itemCode);
                savedProduct.setItemName(product.getItemName());
                savedProduct.setQuantity("1");
                textViewQuantity.setText("1");
            }
            else{

                int tempQuantity = Integer.parseInt(savedProductTemp.getQuantity()) ;

                FloorSaved floorSaved = realm.where(FloorSaved.class).equalTo("floorSavedId" , floorSavedId).findFirst();

                int higestQunatity = Integer.parseInt(floorSaved.getQuantity());

                if(tempQuantity < higestQunatity  ) {

                    Product product = realm.where(Product.class).equalTo("itemCode" , itemCode).findFirst();

                    savedProduct.setSavedProductId(floorSavedId);
                    savedProduct.setItemId(product.getItemId());
                    savedProduct.setFloorId(floorId);
                    savedProduct.setItemCode(itemCode);
                    savedProduct.setItemName(product.getItemName());

                    int increaseTempQuantity = Integer.parseInt(savedProductTemp.getQuantity()) + 1;

                    savedProduct.setQuantity( String.valueOf(increaseTempQuantity) );

                    savedProduct.setQuantity( String.valueOf(increaseTempQuantity) );

                    textViewQuantity.setText( String.valueOf(increaseTempQuantity) );
                }
                else{

                }
            }

            realm.executeTransaction(realm1 -> {
                realm1.insertOrUpdate(savedProduct);
            });

        }
    }

    private void subtractQuantity(TextView textViewFloorAvailableProduct, TextView textViewQuantity, String floorSavedId, String floorId, String itemCode) {

        SavedProduct savedProduct = new SavedProduct();

        try (Realm realm = Realm.getDefaultInstance()) {

            SavedProduct savedProductTemp = realm.where(SavedProduct.class).equalTo("savedProductId" , floorSavedId).findFirst();

            if(savedProductTemp == null){
//                savedProduct.setSavedProductId(floorSavedId);
//                savedProduct.setItemId(itemId);
//                savedProduct.setFloorId(floorId);
//                savedProduct.setQuantity("1");
//                textViewQuantity.setText("1");
            }
            else{

                int tempQuantity = Integer.parseInt(savedProductTemp.getQuantity()) ;

//                FloorSaved floorSaved = realm.where(FloorSaved.class).equalTo("floorSavedId" , floorSavedId).findFirst();
//
//                int higestQunatity = Integer.parseInt(floorSaved.getQuantity());

                if(tempQuantity > 0  ) {

                    Product product = realm.where(Product.class).equalTo("itemCode" , itemCode).findFirst();

                    savedProduct.setSavedProductId(floorSavedId);
                    savedProduct.setItemId(product.getItemId());
                    savedProduct.setFloorId(floorId);
                    savedProduct.setItemCode(itemCode);
                    savedProduct.setItemName(product.getItemName());

                    int increaseTempQuantity = Integer.parseInt(savedProductTemp.getQuantity()) - 1;

                    savedProduct.setQuantity( String.valueOf(increaseTempQuantity) );

                    savedProduct.setQuantity( String.valueOf(increaseTempQuantity) );

                    textViewQuantity.setText( String.valueOf(increaseTempQuantity) );
                }
                else{

                }
            }

            realm.executeTransaction(realm1 -> {
                realm1.insertOrUpdate(savedProduct);
            });

        }

    }

}

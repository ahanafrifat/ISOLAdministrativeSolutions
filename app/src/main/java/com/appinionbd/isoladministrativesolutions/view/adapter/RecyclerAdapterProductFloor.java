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
import com.appinionbd.isoladministrativesolutions.model.dataHolder.ItemHolder;
import com.appinionbd.isoladministrativesolutions.model.dataModel.Floor;
import com.appinionbd.isoladministrativesolutions.model.dataModel.FloorList;
import com.appinionbd.isoladministrativesolutions.model.dataModel.ItemList;

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
            addQuantity(floorSaveds.get(position).getId() , holder.textViewFloorAvailableProduct ,floorSaveds.get(position).getFloorId() , holder.textViewQuantity);
        });

        holder.imageViewDown.setOnClickListener(v -> {

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

    private void addQuantity(String id, TextView textViewFloorAvailableProduct, String floorId, TextView textViewQuantity) {

        int quantity = 0 ;
        int quantityOld = 0 ;
        FloorList floorSavedTemp = new FloorList();
        ItemHolder itemHolderTemp = new ItemHolder();

        try(Realm realm = Realm.getDefaultInstance()){

            FloorSaved floorSaved = realm.where(FloorSaved.class).equalTo("id" , id).findFirst();
            ItemHolder itemHolder = realm.where(ItemHolder.class).equalTo("itemHolderId" , id).findFirst();

            itemHolderTemp.setItemHolderId( floorSaved.getId() );
            itemHolderTemp.setItemId( floorSaved.getItemId() );
            itemHolderTemp.setFloorId( floorSaved.getFloorId() );

            if(itemHolderTemp == null){
                itemHolderTemp.setQuantity("0");
            }
            else
            {
                quantity = Integer.parseInt(itemHolderTemp.getQuantity());
                quantity = quantity + 1 ;
                itemHolder.setQuantity(String.valueOf(quantity));

//                textViewFloorAvailableProduct.setText();
                textViewQuantity.setText(quantity);
            }

        }
    }

    private void subtractQuantity(String id, TextView textViewFloorAvailableProduct) {

        int quantity = 0 ;

        try(Realm realm = Realm.getDefaultInstance()){

            FloorSaved floorSaved = realm.where(FloorSaved.class).equalTo("id" , id).findFirst();
            quantity = Integer.parseInt(floorSaved.getQuantity());

            if(quantity > 0) {
                quantity = quantity + 1;
                floorSaved.setQuantity(String.valueOf(quantity));
                textViewFloorAvailableProduct.setText(String.valueOf(quantity));
                realm.executeTransaction(realm1 -> {
                    realm1.insertOrUpdate(floorSaved);
                });
                notifyDataSetChanged();
            }
            else{

            }
        }
    }



}

package com.appinionbd.isoladministrativesolutions.view.productHome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.appinionbd.isoladministrativesolutions.R;
import com.appinionbd.isoladministrativesolutions.appUtil.AppUtil;
import com.appinionbd.isoladministrativesolutions.interfaces.presenterInterface.IProductHome;
import com.appinionbd.isoladministrativesolutions.model.dataHolder.FloorSaved;
import com.appinionbd.isoladministrativesolutions.model.dataModel.Product;
import com.appinionbd.isoladministrativesolutions.presenter.ProductFloorPresenter;
import com.appinionbd.isoladministrativesolutions.view.adapter.RecyclerAdapterProductFloor;

import java.util.List;

import es.dmoral.toasty.Toasty;
import io.realm.Realm;

public class ProductHomeActivity extends AppCompatActivity implements IProductHome.View {

    private String itemId;
    private String itemName;

    private TextView textViewProductActivityName;
    private TextView textViewProductActivityId;

    private RecyclerView recyclerViewProductFloor;

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerAdapterProductFloor recyclerAdapterProductFloor;

    private IProductHome.Presenter iProductHomePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_home);
        Realm.init(this);

        Intent intent = getIntent();
        itemId = intent.getStringExtra("itemId");

        iProductHomePresenter = new ProductFloorPresenter(this);

        textViewProductActivityName = findViewById(R.id.textView_product_activity_name);
        textViewProductActivityId = findViewById(R.id.textView_product_activity_id);
        recyclerViewProductFloor = findViewById(R.id.recyclerView_product_floor);

        iProductHomePresenter.getProductFloorWithoutWaiting(itemId);
    }

    @Override
    protected void onStart() {
        super.onStart();

        try(Realm realm = Realm.getDefaultInstance()){
            Product product = realm.where(Product.class).equalTo("itemId" , itemId).findFirst();
            itemName = product.getItemName();
        }

        textViewProductActivityName.setText(itemName);
        textViewProductActivityId.setText(itemId);
    }

    @Override
    public void showProductFloor(List<FloorSaved> floorSaveds) {

        try {
            if (floorSaveds.size() > 0) {
                layoutManager = new LinearLayoutManager(this);
                recyclerViewProductFloor.setLayoutManager(layoutManager);
                recyclerViewProductFloor.setHasFixedSize(true);

                recyclerAdapterProductFloor = new RecyclerAdapterProductFloor(floorSaveds);
                recyclerAdapterProductFloor.notifyDataSetChanged();
                recyclerViewProductFloor.setAdapter(recyclerAdapterProductFloor);
            }
        }
        catch (Exception e){
            AppUtil.log("ProductHomeActivity", e.getMessage());
        }
    }

    @Override
    public void emptyProductFloor(String message) {
        Toasty.info(this , message , Toast.LENGTH_LONG , true).show();
    }

    @Override
    public void networkErrorInProductFloor(String message) {

    }

    @Override
    public void otherErrorInProductFloor(String message) {

    }
}

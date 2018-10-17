package com.appinionbd.isoladministrativesolutions.view.productHome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.appinionbd.isoladministrativesolutions.R;
import com.appinionbd.isoladministrativesolutions.interfaces.presenterInterface.IProductHome;
import com.appinionbd.isoladministrativesolutions.model.dataHolder.FloorSaved;
import com.appinionbd.isoladministrativesolutions.presenter.ProductFloorPresenter;
import com.appinionbd.isoladministrativesolutions.view.adapter.RecyclerAdapterProductFloor;

import java.util.List;

public class ProductHomeActivity extends AppCompatActivity implements IProductHome.View {

    private String itemId;
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

    }

    @Override
    public void showProductFloor(List<FloorSaved> floorSaveds) {

        if(floorSaveds.size() > 0){
            layoutManager = new LinearLayoutManager(this);
            recyclerViewProductFloor.setLayoutManager(layoutManager);
            recyclerViewProductFloor.setHasFixedSize(true);

            recyclerAdapterProductFloor = new RecyclerAdapterProductFloor(floorSaveds);
            recyclerAdapterProductFloor.notifyDataSetChanged();
            recyclerViewProductFloor.setAdapter(recyclerAdapterProductFloor);
        }
    }

    @Override
    public void emptyProductFloor(String message) {

    }

    @Override
    public void networkErrorInProductFloor(String message) {

    }

    @Override
    public void otherErrorInProductFloor(String message) {

    }
}

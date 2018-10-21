package com.appinionbd.isoladministrativesolutions.view.productHome;

import android.content.Intent;
import android.support.design.button.MaterialButton;
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
import com.appinionbd.isoladministrativesolutions.view.home.HomeActivity;
import com.appinionbd.isoladministrativesolutions.view.proceedIssuance.ProceedIssuanceActivity;

import java.util.List;

import es.dmoral.toasty.Toasty;
import io.realm.Realm;

public class ProductHomeActivity extends AppCompatActivity implements IProductHome.View {

    private String itemCode;
    private String itemName;

    private TextView textViewProductActivityName;
    private TextView textViewProductActivityId;

    private RecyclerView recyclerViewProductFloor;

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerAdapterProductFloor recyclerAdapterProductFloor;

    private MaterialButton buttonAddToCart;
    private MaterialButton buttonIssueNow;

    private IProductHome.Presenter iProductHomePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_home);
        Realm.init(this);

        Intent intent = getIntent();
        itemCode = intent.getStringExtra("itemCode");

        iProductHomePresenter = new ProductFloorPresenter(this);

        textViewProductActivityName = findViewById(R.id.textView_product_activity_name);
        textViewProductActivityId = findViewById(R.id.textView_product_activity_id);
        recyclerViewProductFloor = findViewById(R.id.recyclerView_product_floor);

        buttonAddToCart = findViewById(R.id.button_add_to_cart);
        buttonIssueNow = findViewById(R.id.button_issue_now);

        iProductHomePresenter.getProductFloorWithoutWaiting(itemCode);
    }

    @Override
    protected void onStart() {
        super.onStart();

        try {
            iProductHomePresenter.getProductFloorWithoutWaiting(itemCode);

            try (Realm realm = Realm.getDefaultInstance()) {
                Product product = realm.where(Product.class).equalTo("itemCode", itemCode).findFirst();
                itemName = product.getItemName();
            }

            textViewProductActivityName.setText(itemName);
            textViewProductActivityId.setText(itemCode);

            buttonAddToCart.setOnClickListener(v -> {
                gotoHomeActivity();
            });

            buttonIssueNow.setOnClickListener(v -> {
                gotoIssueNowActivity();
            });
        }
        catch (Exception e){
            AppUtil.log("ProductHomeActivity" , e.getMessage());
        }
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

    private void gotoHomeActivity() {
        Intent intent = new Intent(this , HomeActivity.class);
        startActivity(intent);
    }

    private void gotoIssueNowActivity() {
        Intent intent = new Intent(this , ProceedIssuanceActivity.class);
        startActivity(intent);
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

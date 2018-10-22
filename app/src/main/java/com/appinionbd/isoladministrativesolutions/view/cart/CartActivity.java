package com.appinionbd.isoladministrativesolutions.view.cart;

import android.content.Intent;
import android.support.design.button.MaterialButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.appinionbd.isoladministrativesolutions.R;
import com.appinionbd.isoladministrativesolutions.interfaces.presenterInterface.ICart;
import com.appinionbd.isoladministrativesolutions.model.dataHolder.CartProduct;
import com.appinionbd.isoladministrativesolutions.presenter.CartPresenter;
import com.appinionbd.isoladministrativesolutions.view.adapter.RecyclerAdapterCart;
import com.appinionbd.isoladministrativesolutions.view.proceedIssuance.ProceedIssuanceActivity;

import java.util.List;

import io.realm.Realm;

public class CartActivity extends AppCompatActivity implements ICart.View {

    private RecyclerView recyclerViewCart;

    private MaterialButton materialButtonContinue;

    private ICart.Presenter cartPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Realm.init(this);

        cartPresenter = new CartPresenter(this);

    }

    @Override
    protected void onStart() {
        super.onStart();

        recyclerViewCart = findViewById(R.id.recyclerView_cart);

        materialButtonContinue = findViewById(R.id.materialButton_continue);

        cartPresenter.getCartWithoutWaiting();
    }

    @Override
    public void showCart(List<CartProduct> cartProducts) {

        if(cartProducts != null) {
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerViewCart.setLayoutManager(layoutManager);
            recyclerViewCart.setHasFixedSize(true);

            RecyclerAdapterCart recyclerAdapterCart = new RecyclerAdapterCart(cartProducts);
            recyclerAdapterCart.notifyDataSetChanged();
            recyclerViewCart.setAdapter(recyclerAdapterCart);

            materialButtonContinue.setOnClickListener(v -> gotoProceedIssuance());
        }

    }

    private void gotoProceedIssuance() {
        Intent intent = new Intent(this , ProceedIssuanceActivity.class);
        startActivity(intent);
    }
}

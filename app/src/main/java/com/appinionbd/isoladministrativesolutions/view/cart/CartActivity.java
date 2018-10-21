package com.appinionbd.isoladministrativesolutions.view.cart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.appinionbd.isoladministrativesolutions.R;
import com.appinionbd.isoladministrativesolutions.interfaces.presenterInterface.ICart;
import com.appinionbd.isoladministrativesolutions.presenter.CartPresenter;
import com.appinionbd.isoladministrativesolutions.view.adapter.RecyclerAdapterCart;

import io.realm.Realm;

public class CartActivity extends AppCompatActivity implements ICart.View {

    private RecyclerView recyclerViewProduct;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerAdapterCart recyclerAdapterCart;
    private RecyclerView recyclerViewCart;


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
    }

    @Override
    public void showCart() {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewCart.setLayoutManager(layoutManager);
        recyclerViewCart.setHasFixedSize(true);
        recyclerAdapterCart = new RecyclerAdapterCart();
        recyclerAdapterCart.notifyDataSetChanged();
        recyclerViewCart.setAdapter(recyclerAdapterCart);
    }
}

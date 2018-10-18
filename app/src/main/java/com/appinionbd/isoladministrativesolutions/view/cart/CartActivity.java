package com.appinionbd.isoladministrativesolutions.view.cart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.appinionbd.isoladministrativesolutions.R;
import com.appinionbd.isoladministrativesolutions.interfaces.presenterInterface.ICart;
import com.appinionbd.isoladministrativesolutions.presenter.CartPresenter;

import io.realm.Realm;

public class CartActivity extends AppCompatActivity implements ICart.View {

    private ICart.Presenter cartPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Realm.init(this);

        cartPresenter = new CartPresenter();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}

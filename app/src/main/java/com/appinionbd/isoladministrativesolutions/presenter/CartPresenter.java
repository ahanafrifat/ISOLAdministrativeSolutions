package com.appinionbd.isoladministrativesolutions.presenter;

import com.appinionbd.isoladministrativesolutions.interfaces.presenterInterface.ICart;

public class CartPresenter implements ICart.Presenter {

    private ICart.View view;

    public CartPresenter() {
    }

    public CartPresenter(ICart.View view) {
        this.view = view;
    }

    @Override
    public void getCartWithoutWaiting() {

    }
}

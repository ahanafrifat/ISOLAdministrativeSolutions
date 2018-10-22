package com.appinionbd.isoladministrativesolutions.interfaces.presenterInterface;

import com.appinionbd.isoladministrativesolutions.model.dataHolder.CartProduct;

import java.util.List;

public interface ICart {
    interface View{

        void showCart(List<CartProduct> cartProducts);

    }
    interface Presenter{

        void getCartWithoutWaiting();
    }
}

package com.appinionbd.isoladministrativesolutions.interfaces.presenterInterface;

public interface ICart {
    interface View{

        void showCart();

    }
    interface Presenter{

        void getCartWithoutWaiting();
    }
}

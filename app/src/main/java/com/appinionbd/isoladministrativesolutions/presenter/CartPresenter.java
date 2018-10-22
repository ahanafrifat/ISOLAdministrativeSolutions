package com.appinionbd.isoladministrativesolutions.presenter;

import com.appinionbd.isoladministrativesolutions.appUtil.AppUtil;
import com.appinionbd.isoladministrativesolutions.interfaces.presenterInterface.ICart;
import com.appinionbd.isoladministrativesolutions.model.dataHolder.CartProduct;
import com.appinionbd.isoladministrativesolutions.model.dataHolder.SavedProduct;
import com.appinionbd.isoladministrativesolutions.model.dataModel.Product;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class CartPresenter implements ICart.Presenter {

    private ICart.View view;

    public CartPresenter() {
    }

    public CartPresenter(ICart.View view) {
        this.view = view;
    }

    @Override
    public void getCartWithoutWaiting() {

        List<Product> productList = new ArrayList<>();

        List<SavedProduct> savedProductList = new ArrayList<>();

        List<CartProduct> cartProducts = new ArrayList<>();



        try(Realm realm = Realm.getDefaultInstance()){

//            RealmResults<SavedProduct> savedProductRealmResults = realm.where(SavedProduct.class).findAll();
            RealmResults<Product> productRealmResults = realm.where(Product.class).findAll();


            for(Product product : productRealmResults){

                RealmResults<SavedProduct> savedProductRealmResults = realm.where(SavedProduct.class)
                        .equalTo("itemCode" , product.getItemCode())
                        .findAll();

                if( savedProductRealmResults != null ) {

                    int cartProductQuantity = 0;

                    for (SavedProduct savedProduct : savedProductRealmResults) {
                        cartProductQuantity = cartProductQuantity + Integer.parseInt(savedProduct.getQuantity());
                    }

                    if(cartProductQuantity > 0) {


                        CartProduct cartProduct = new CartProduct();
                        cartProduct.setItemCode(product.getItemCode());
                        cartProduct.setItemName(product.getItemName());
                        cartProduct.setQuantity(String.valueOf(cartProductQuantity));

                        cartProducts.add(cartProduct);

                        realm.executeTransaction(realm1 -> {
                            realm1.insertOrUpdate(cartProduct);
                        });
                    }
                }

            }

        }

        view.showCart(cartProducts);
    }
}















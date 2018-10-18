package com.appinionbd.isoladministrativesolutions.presenter;

import android.os.Handler;

import com.appinionbd.isoladministrativesolutions.appUtil.AppUtil;
import com.appinionbd.isoladministrativesolutions.interfaces.networkInterface.ISyncAppData;
import com.appinionbd.isoladministrativesolutions.interfaces.presenterInterface.IHome;
import com.appinionbd.isoladministrativesolutions.model.dataHolder.UserToken;
import com.appinionbd.isoladministrativesolutions.model.dataModel.Product;
import com.appinionbd.isoladministrativesolutions.model.dataModel.ProductLibrary;
import com.appinionbd.isoladministrativesolutions.model.dataModel.UserInfo;
import com.appinionbd.isoladministrativesolutions.networking.syncAppData.SyncAppData;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;


public class HomePresenter implements IHome.Presenter {

    private IHome.View view;

    public HomePresenter() {
    }

    public HomePresenter(IHome.View view) {
        this.view = view;
    }

    @Override
    public void getRefreshData() {
        try {
            Handler handler = new Handler();
            handler.postDelayed(() -> getData(), 100);
        }
        catch (Exception e){
            AppUtil.log("HomePresenter" , "HomePresenter : " + e.getMessage());
        }
    }

    @Override
    public void getDataWithoutWaiting() {
        getData();
    }

    public void getData() {
        String token= "";
        try(Realm realmInstance = Realm.getDefaultInstance()) {
            UserToken userToken = realmInstance.where(UserToken.class).findFirst();
            token = userToken.getToken();
        }
        SyncAppData.getSyncAppData().setSyncAppData(token, new ISyncAppData() {
            @Override
            public void successfulProductLibrary(ProductLibrary productLibrary) {
                try {
                    if (productLibrary.getProducts().size() > 0) {

                        List<Product> productList = new ArrayList<>();

                        for (Product product : productLibrary.getProducts()) {

                            Product tempProduct = new Product();

                            tempProduct.setItemName(product.getItemName());
                            tempProduct.setItemCode(product.getItemCode());
                            tempProduct.setItemId(product.getItemId());

                            productList.add(tempProduct);

                            AppUtil.log("HomePresenter", product.getItemName() + " product size: " + productList.size());

                            try (Realm realmInstance = Realm.getDefaultInstance()) {
                                realmInstance.executeTransaction(realm -> realm.insertOrUpdate(tempProduct));
                            }
                        }

                        view.showData(productList);

                    } else {
                        view.emptyData("There is no product");
                    }
                }
                catch (Exception e){
                    AppUtil.log("HomePresenter" , e.getMessage());
                }
            }

            @Override
            public void error(String error) {
                view.otherError(error);
            }

            @Override
            public void networkError(String error) {
                view.networkError(error);
            }
        });
    }

    @Override
    public void proceedToLogout() {
        try (Realm realm = Realm.getDefaultInstance()) {

            realm.executeTransaction(realm1 -> {
                RealmResults<UserInfo> userInfos = realm1.where(UserInfo.class).findAll();
                RealmResults<UserToken> userTokens = realm1.where(UserToken.class).findAll();
                userInfos.deleteAllFromRealm();
                userTokens.deleteAllFromRealm();
            });
        }

        view.logoutSuccessful();
    }
}

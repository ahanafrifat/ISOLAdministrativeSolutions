package com.appinionbd.isoladministrativesolutions.presenter;

import android.os.Handler;

import com.appinionbd.isoladministrativesolutions.appUtil.AppUtil;
import com.appinionbd.isoladministrativesolutions.interfaces.networkInterface.ISyncProductFloor;
import com.appinionbd.isoladministrativesolutions.interfaces.presenterInterface.IProductHome;
import com.appinionbd.isoladministrativesolutions.model.dataHolder.FloorSaved;
import com.appinionbd.isoladministrativesolutions.model.dataHolder.UserToken;
import com.appinionbd.isoladministrativesolutions.model.dataModel.Floor;
import com.appinionbd.isoladministrativesolutions.model.dataModel.SearchItem;
import com.appinionbd.isoladministrativesolutions.model.dataModel.SingleProductFloorList;
import com.appinionbd.isoladministrativesolutions.networking.syncProductFloor.SyncProductFloor;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class ProductFloorPresenter implements IProductHome.Presenter {

    private IProductHome.View view;

    public ProductFloorPresenter() {
    }

    public ProductFloorPresenter(IProductHome.View view) {
        this.view = view;
    }

    @Override
    public void getRefreshDataOfProductFloor(String itemCode) {
        try {
            Handler handler = new Handler();
            handler.postDelayed(() -> getProductFloor(itemCode), 100);
        }
        catch (Exception e){
            AppUtil.log("HomePresenter" , "HomePresenter : " + e.getMessage());
        }
    }

    @Override
    public void getProductFloorWithoutWaiting(String itemCode) {
        getProductFloor(itemCode);
    }

    private void getProductFloor(String itemCode) {

        String token= "";

        try(Realm realmInstance = Realm.getDefaultInstance()) {
            UserToken userToken = realmInstance.where(UserToken.class).findFirst();
            token = userToken.getToken();
        }

        SearchItem searchItem = new SearchItem();
        searchItem.setItemCode(itemCode);

        SyncProductFloor.getSyncProductFloor().setSyncProductFloor(token, searchItem, new ISyncProductFloor() {
            @Override
            public void successfulSyncProductFloor(SingleProductFloorList singleProductFloorList) {

                List<FloorSaved> floorSaveds = new ArrayList<>();

                if(singleProductFloorList.getStatus() == 200) {


                    for (Floor floor : singleProductFloorList.getFloor()) {

                        FloorSaved floorSaved = new FloorSaved();

                        floorSaved.setFloorSavedId(itemCode + "_" + floor.getFloorId());
                        floorSaved.setFloorId(floor.getFloorId());
                        floorSaved.setItemCode(itemCode);
                        floorSaved.setQuantity(floor.getQuantity());
                        floorSaved.setFloorNo(floor.getFloorNo());

                        try (Realm realm = Realm.getDefaultInstance()) {
                            realm.executeTransaction(realm1 -> {
                                realm1.insertOrUpdate(floorSaved);
                            });
                        }

                        floorSaveds.add(floorSaved);
                    }

                    view.showProductFloor(floorSaveds);
                }
                else{
                    view.emptyProductFloor("No floor found");
                }
            }

            @Override
            public void errorProductFloor(String message) {

            }

            @Override
            public void connectionErrorProductFloor(String message) {

            }
        });
    }

}

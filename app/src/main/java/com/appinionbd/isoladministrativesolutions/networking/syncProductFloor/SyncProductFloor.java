package com.appinionbd.isoladministrativesolutions.networking.syncProductFloor;

import com.appinionbd.isoladministrativesolutions.interfaces.networkInterface.ISyncProductFloor;
import com.appinionbd.isoladministrativesolutions.model.dataModel.SearchItem;
import com.appinionbd.isoladministrativesolutions.model.dataModel.SingleProductFloorList;
import com.appinionbd.isoladministrativesolutions.networking.retrofit.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SyncProductFloor {
    private static SyncProductFloor syncProductFloor;

    public SyncProductFloor() {
    }

    public static SyncProductFloor getSyncProductFloor(){
        if(syncProductFloor == null)
            syncProductFloor = new SyncProductFloor();
        return syncProductFloor;
    }

    public void setSyncProductFloor(String token , SearchItem searchItem , ISyncProductFloor iSyncProductFloor){
        apiSyncProductFloor(token , searchItem , iSyncProductFloor);
    }

    private void apiSyncProductFloor(String token, SearchItem searchItem, ISyncProductFloor iSyncProductFloor) {

        Call<SingleProductFloorList> call = ApiClient.getApiInterface().singleProductFloorListCall(token , searchItem);
        call.enqueue(new Callback<SingleProductFloorList>() {
            @Override
            public void onResponse(Call<SingleProductFloorList> call, Response<SingleProductFloorList> response) {

                if(response.isSuccessful() && response.code() == 200 ){
                    SingleProductFloorList singleProductFloorList = response.body();
                    iSyncProductFloor.successfulSyncProductFloor(singleProductFloorList);
                }
                else{
                    iSyncProductFloor.errorProductFloor("Error in Sync: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<SingleProductFloorList> call, Throwable t) {
                iSyncProductFloor.connectionErrorProductFloor("Error in connection: " + t.getMessage());
            }
        });
    }
}

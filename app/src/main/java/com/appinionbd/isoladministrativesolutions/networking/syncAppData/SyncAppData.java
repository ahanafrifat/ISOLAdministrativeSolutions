package com.appinionbd.isoladministrativesolutions.networking.syncAppData;

import com.appinionbd.isoladministrativesolutions.interfaces.networkInterface.ISyncAppData;
import com.appinionbd.isoladministrativesolutions.model.dataModel.ProductLibrary;
import com.appinionbd.isoladministrativesolutions.networking.retrofit.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SyncAppData {
    private static SyncAppData syncAppData;

    private SyncAppData() {
    }

    public static SyncAppData getSyncAppData() {
        if (syncAppData == null)
            syncAppData = new SyncAppData();
        return syncAppData;
    }

    public void setSyncAppData(String token , ISyncAppData iSyncAppData) {
        apiSyncAppData(token , iSyncAppData);
    }

    private void apiSyncAppData(String token, ISyncAppData iSyncAppData) {
        Call<ProductLibrary> call = ApiClient.getApiInterface().productLibraryCall(token);
        call.enqueue(new Callback<ProductLibrary>() {
            @Override
            public void onResponse(Call<ProductLibrary> call, Response<ProductLibrary> response) {
                if(response.isSuccessful() && response.code() == 200){
                    ProductLibrary productLibrary = response.body();
                    iSyncAppData.successfulProductLibrary(productLibrary);
                }
                else{
                    iSyncAppData.error("Error in response : " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ProductLibrary> call, Throwable t) {
                iSyncAppData.networkError("Network error : " + t.getMessage());
            }
        });
    }
}

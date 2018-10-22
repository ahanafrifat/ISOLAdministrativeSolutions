package com.appinionbd.isoladministrativesolutions.networking.uploadInvoice;

import android.util.Log;

import com.appinionbd.isoladministrativesolutions.model.dataModel.Issuance;
import com.appinionbd.isoladministrativesolutions.model.dataModel.ResponseModel;
import com.appinionbd.isoladministrativesolutions.networking.retrofit.ApiClient;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UploadInvoice {

    private static UploadInvoice uploadInvoice;
    private  UploadInvoice(){};

    public synchronized static UploadInvoice getUploadInvoice() {
        if(uploadInvoice==null)
            uploadInvoice = new UploadInvoice();
        return uploadInvoice;
    }

    public void uploadImage(String token, MultipartBody.Part image, RequestBody uploadName, Issuance issuance) {
        Call<ResponseModel> call = ApiClient.getApiInterface().uploadImage(token, image,uploadName);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if(response.isSuccessful() && response.body()!=null)
                    Log.d("Issuance", response.body().getMessage());
                else{
                    Log.d("Issuance", response.body().getMessage() + " Error : ");
                }

            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Log.d("Issuance", "Error "+t.getMessage());
            }
        });
    }
}

package com.appinionbd.isoladministrativesolutions.networking.retrofit;

import com.appinionbd.isoladministrativesolutions.model.dataModel.APIAuth;
import com.appinionbd.isoladministrativesolutions.model.dataModel.LoginAuth;
import com.appinionbd.isoladministrativesolutions.model.dataModel.LoginCredential;
import com.appinionbd.isoladministrativesolutions.model.dataModel.ProductLibrary;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @Headers({"Client-Service: appinion-client"
            , "Auth-Key: 523f260e015519f3a6da69f9ae1a94de"
            , "Content-Type: application/x-www-form-urlencoded"})
    @POST("/isol_inventory/auth/login")
    Call<APIAuth> apiAuthCall(@Query("username") String username , @Query("password") String password);

    @POST("/isol_inventory/api/app_login")
    Call<LoginAuth> loginAuth(@Header("authorization") String authorization,
                              @Query("app_user_id") String username,
                              @Query("app_password") String password);

    @GET("/isol_inventory/api/user/me")
    Call<LoginCredential> loginCredentialCall (@Header("token") String token);

    @GET("/isol_inventory/api/product/product_library")
    Call<ProductLibrary> productLibraryCall(@Header("token") String token );
}

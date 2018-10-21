package com.appinionbd.isoladministrativesolutions.networking.retrofit;

import com.appinionbd.isoladministrativesolutions.model.dataModel.APIAuth;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class ApiClient {
    private static final String BASE_URL = "http://10.10.20.29";

    private static Retrofit retrofit = null;

    private static Retrofit getApiClient(){

        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClientBuilder.addInterceptor(logging);

        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClientBuilder.build())
                    .build();
        }

        return retrofit;
    }

    public static ApiInterface getApiInterface(){
        return ApiClient.getApiClient().create(ApiInterface.class);
    }

}




















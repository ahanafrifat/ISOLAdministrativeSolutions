package com.appinionbd.isoladministrativesolutions.networking.authentication;

import android.support.annotation.NonNull;

import com.appinionbd.isoladministrativesolutions.interfaces.networkInterface.IAuthentication;
import com.appinionbd.isoladministrativesolutions.model.dataHolder.UserToken;
import com.appinionbd.isoladministrativesolutions.model.dataModel.APIAuth;
import com.appinionbd.isoladministrativesolutions.model.dataModel.LoginAuth;
import com.appinionbd.isoladministrativesolutions.model.dataModel.LoginCredential;
import com.appinionbd.isoladministrativesolutions.model.dataModel.UserInfo;
import com.appinionbd.isoladministrativesolutions.networking.retrofit.ApiClient;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginAuthentication {

    private static LoginAuthentication authentication;

    private LoginAuthentication() {
    }

    public static LoginAuthentication getAuthentication() {
        if (authentication == null) {
            authentication = new LoginAuthentication();
        }
        return authentication;
    }

    public void loginAuthentication(String userName, String password, IAuthentication iAuthentication) {
        apiAuthentication(userName, password, iAuthentication);
    }

    private void apiAuthentication(String userName, String password, IAuthentication iAuthentication) {

        Call<APIAuth> call = ApiClient.getApiInterface().apiAuthCall("appinion_test", "123456");
        call.enqueue(new Callback<APIAuth>() {
            @Override
            public void onResponse(@NonNull Call<APIAuth> call, @NonNull Response<APIAuth> response) {
                if (response.isSuccessful()) {
                    APIAuth apiAuth = response.body();
                    if (apiAuth != null && apiAuth.getStatus() == 200 && !apiAuth.getAuthorization().isEmpty()) {
                        gotoLoginAuth(apiAuth.getAuthorization(), userName, password, iAuthentication);
                    }
                }
                else {
                    iAuthentication.authFailed();
                }
            }

            @Override
            public void onFailure(@NonNull Call<APIAuth> call, @NonNull Throwable t) {
                iAuthentication.connectionError("Connection failed in auth!");
            }
        });
    }

    private void gotoLoginAuth(String authorization, String userName, String password, IAuthentication iAuthentication) {

        Call<LoginAuth> call = ApiClient.getApiInterface().loginAuth(authorization, userName, password);
        call.enqueue(new Callback<LoginAuth>() {
            @Override
            public void onResponse(@NonNull Call<LoginAuth> call, @NonNull Response<LoginAuth> response) {
                if (response.isSuccessful()) {
                    LoginAuth loginAuth = response.body();
                    if (loginAuth != null) {
                        if (loginAuth.getStatus() == 200) {
                            gotoUserAuth(loginAuth.getToken(), iAuthentication);
                        }
                        else
                            iAuthentication.wrongUserNameOrPassword();
                    }
                    else
                        iAuthentication.wrongUserNameOrPassword();
                }
                else
                    iAuthentication.wrongUserNameOrPassword();
            }

            @Override
            public void onFailure(@NonNull Call<LoginAuth> call, @NonNull Throwable t) {
                iAuthentication.connectionError("Connection failed in login!");
            }
        });
    }

    private void gotoUserAuth(String token, IAuthentication iAuthentication) {
        Call<LoginCredential> call = ApiClient.getApiInterface().loginCredentialCall(token);
        call.enqueue(new Callback<LoginCredential>() {
            @Override
            public void onResponse(@NonNull Call<LoginCredential> call, @NonNull Response<LoginCredential> response) {
                if (response.isSuccessful()) {
                    LoginCredential loginCredential = response.body();
                    if (loginCredential != null) {
                        UserInfo userInfo= new UserInfo();

                        userInfo.setUserName(loginCredential.getUserInfo().getUserName());
                        userInfo.setPhoneNumber(loginCredential.getUserInfo().getPhoneNumber());
                        userInfo.setUserId(loginCredential.getUserInfo().getUserId());
                        userInfo.setEmail(loginCredential.getUserInfo().getEmail());
                        userInfo.setDesignation(loginCredential.getUserInfo().getDesignation());
                        userInfo.setAddress(loginCredential.getUserInfo().getAddress());

                        UserToken userToken = new UserToken();

                        userToken.setUserId(loginCredential.getUserInfo().getUserId());
                        userToken.setToken(token);

                        try(Realm realmInstance = Realm.getDefaultInstance()){
                            realmInstance.executeTransaction(realm-> {
                                realm.insertOrUpdate(userInfo);
                                realm.insertOrUpdate(userToken);
                            });
                        }

                        iAuthentication.successful(userInfo);

                    } else {
                        iAuthentication.failed();
                    }
                }
                else{
                    iAuthentication.failed();
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginCredential> call, @NonNull Throwable t) {
                iAuthentication.connectionError("Connection failed in login to get user Info!");
            }
        });

    }
}

package com.appinionbd.isoladministrativesolutions.presenter;

import com.appinionbd.isoladministrativesolutions.interfaces.networkInterface.IAuthentication;
import com.appinionbd.isoladministrativesolutions.interfaces.presenterInterface.ILogin;
import com.appinionbd.isoladministrativesolutions.model.dataModel.UserInfo;
import com.appinionbd.isoladministrativesolutions.networking.authentication.LoginAuthentication;

public class LoginPresenter implements ILogin.Presenter {
    private ILogin.View view;

    public LoginPresenter() {
    }

    public LoginPresenter(ILogin.View view) {
        this.view = view;
    }

    @Override
    public void loginVerification(String username, String password) {

        if (username.isEmpty()) {
            view.wrongUsername();
        } else if (password.isEmpty()) {
            view.wrongPassword();
        }

        LoginAuthentication.getAuthentication().loginAuthentication(username, password, new IAuthentication() {
            @Override
            public void successful(UserInfo userInfo) {
                view.loginSuccessful(userInfo);
            }

            @Override
            public void authFailed() {
                view.authFailedShowInView();
            }

            @Override
            public void wrongUserNameOrPassword() {
                view.wrongUserNameOrPasswordShowInView();
            }

            @Override
            public void failed() {
                view.loginFailed();
            }

            @Override
            public void connectionError(String message) {
                view.connectionError();
            }
        });
    }


}

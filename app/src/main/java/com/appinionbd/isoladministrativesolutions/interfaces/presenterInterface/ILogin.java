package com.appinionbd.isoladministrativesolutions.interfaces.presenterInterface;

import com.appinionbd.isoladministrativesolutions.model.dataModel.UserInfo;

public interface ILogin {
    interface View{
        void loginSuccessful(UserInfo userInfo);
        void loginFailed();
        void wrongUsername();
        void wrongPassword();
        void connectionError();
        void authFailedShowInView();
        void wrongUserNameOrPasswordShowInView();
    }
    interface Presenter{
        void loginVerification(String username, String password);
    }
}

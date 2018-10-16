package com.appinionbd.isoladministrativesolutions.interfaces.networkInterface;

import com.appinionbd.isoladministrativesolutions.model.dataModel.UserInfo;

public interface IAuthentication {
    void successful(UserInfo userInfo);
    void authFailed();
    void wrongUserNameOrPassword();
    void failed();
    void connectionError(String message);
}

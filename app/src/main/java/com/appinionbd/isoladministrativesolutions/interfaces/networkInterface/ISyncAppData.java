package com.appinionbd.isoladministrativesolutions.interfaces.networkInterface;

import com.appinionbd.isoladministrativesolutions.model.dataModel.ProductLibrary;

public interface ISyncAppData {
    void successfulProductLibrary(ProductLibrary productLibrary);
    void error(String error);
    void networkError(String error);
}

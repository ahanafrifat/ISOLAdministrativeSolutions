package com.appinionbd.isoladministrativesolutions.interfaces.presenterInterface;

import com.appinionbd.isoladministrativesolutions.model.dataModel.Product;

import java.util.List;

public interface IHome {
    interface View{

        void showData(List<Product> productList);

        void emptyData(String message);

        void networkError(String message);

        void otherError(String message);
    }
    interface Presenter{
        void getRefreshData();
        void getDataWithoutWaiting();
    }
}

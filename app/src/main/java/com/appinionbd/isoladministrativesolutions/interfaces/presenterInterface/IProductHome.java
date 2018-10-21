package com.appinionbd.isoladministrativesolutions.interfaces.presenterInterface;

import com.appinionbd.isoladministrativesolutions.model.dataHolder.FloorSaved;

import java.util.List;

public interface IProductHome {
    interface View{

        void showProductFloor(List<FloorSaved> floorSaveds);

        void emptyProductFloor(String message);

        void networkErrorInProductFloor(String message);

        void otherErrorInProductFloor(String message);
    }
    interface Presenter{

        void getRefreshDataOfProductFloor(String itemCode);

        void getProductFloorWithoutWaiting(String itemCode);

    }
}

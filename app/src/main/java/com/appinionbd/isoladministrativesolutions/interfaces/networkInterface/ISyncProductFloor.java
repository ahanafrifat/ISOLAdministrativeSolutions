package com.appinionbd.isoladministrativesolutions.interfaces.networkInterface;

import com.appinionbd.isoladministrativesolutions.model.dataModel.Floor;
import com.appinionbd.isoladministrativesolutions.model.dataModel.SingleProductFloorList;

public interface ISyncProductFloor {

    void successfulSyncProductFloor(SingleProductFloorList singleProductFloorList);

    void errorProductFloor(String message);

    void connectionErrorProductFloor(String message);
}

package com.appinionbd.isoladministrativesolutions.model.dataHolder;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class FloorSaved extends RealmObject {

    @PrimaryKey
    private String floorSavedId;
    private String floorId;
    private String itemCode;
    private String quantity;
    private String floorNo;

    public FloorSaved() {
    }

    public FloorSaved(String floorSavedId, String floorId, String itemCode, String quantity, String floorNo) {
        this.floorSavedId = floorSavedId;
        this.floorId = floorId;
        this.itemCode = itemCode;
        this.quantity = quantity;
        this.floorNo = floorNo;
    }

    public String getFloorSavedId() {
        return floorSavedId;
    }

    public void setFloorSavedId(String floorSavedId) {
        this.floorSavedId = floorSavedId;
    }

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(String floorNo) {
        this.floorNo = floorNo;
    }
}

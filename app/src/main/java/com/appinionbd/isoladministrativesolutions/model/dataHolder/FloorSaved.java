package com.appinionbd.isoladministrativesolutions.model.dataHolder;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class FloorSaved extends RealmObject {

    @PrimaryKey
    private String id;
    private String floorId;
    private String itemId;
    private String quantity;
    private String floorNo;

    public FloorSaved() {
    }

    public FloorSaved(String id, String floorId, String itemId, String quantity, String floorNo) {
        this.id = id;
        this.floorId = floorId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.floorNo = floorNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
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

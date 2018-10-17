package com.appinionbd.isoladministrativesolutions.model.dataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Floor {

    @SerializedName("floor_id")
    @Expose
    private String floorId;
    @SerializedName("floor_no")
    @Expose
    private String floorNo;
    @SerializedName("quantity")
    @Expose
    private String quantity;

    public Floor() {
    }

    public Floor(String floorId, String floorNo, String quantity) {
        this.floorId = floorId;
        this.floorNo = floorNo;
        this.quantity = quantity;
    }

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    public String getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(String floorNo) {
        this.floorNo = floorNo;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}

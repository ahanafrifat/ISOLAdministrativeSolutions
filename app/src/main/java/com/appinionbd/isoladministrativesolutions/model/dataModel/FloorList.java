package com.appinionbd.isoladministrativesolutions.model.dataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FloorList {
    @SerializedName("floor_id")
    @Expose
    private String floorId;
    @SerializedName("quantity")
    @Expose
    private String quantity;

    public FloorList() {
    }

    public FloorList(String floorId, String quantity) {
        this.floorId = floorId;
        this.quantity = quantity;
    }

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}

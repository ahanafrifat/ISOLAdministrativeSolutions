package com.appinionbd.isoladministrativesolutions.model.dataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemList {
    @SerializedName("item_id")
    @Expose
    private String itemId;
    @SerializedName("floor_list")
    @Expose
    private List<FloorList> floorList = null;

    public ItemList() {
    }

    public ItemList(String itemId, List<FloorList> floorList) {
        this.itemId = itemId;
        this.floorList = floorList;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public List<FloorList> getFloorList() {
        return floorList;
    }

    public void setFloorList(List<FloorList> floorList) {
        this.floorList = floorList;
    }
}

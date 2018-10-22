package com.appinionbd.isoladministrativesolutions.model.dataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemList{

    @SerializedName("item_code")
    @Expose
    private String itemCode;
    @SerializedName("floor_list")
    @Expose
    private List<FloorList> floorList = null;

    public ItemList() {
    }

    public ItemList(String itemCode, List<FloorList> floorList) {
        this.itemCode = itemCode;
        this.floorList = floorList;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public List<FloorList> getFloorList() {
        return floorList;
    }

    public void setFloorList(List<FloorList> floorList) {
        this.floorList = floorList;
    }
}

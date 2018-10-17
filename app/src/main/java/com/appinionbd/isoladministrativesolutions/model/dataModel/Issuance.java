package com.appinionbd.isoladministrativesolutions.model.dataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Issuance {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("vendor_name")
    @Expose
    private String vendorName;
    @SerializedName("remark")
    @Expose
    private String remark;
    @SerializedName("item_list")
    @Expose
    private List<ItemList> itemList = null;

    public Issuance() {
    }

    public Issuance(String userId, String vendorName, String remark, List<ItemList> itemList) {
        this.userId = userId;
        this.vendorName = vendorName;
        this.remark = remark;
        this.itemList = itemList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<ItemList> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemList> itemList) {
        this.itemList = itemList;
    }
}

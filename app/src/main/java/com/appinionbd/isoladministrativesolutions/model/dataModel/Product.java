package com.appinionbd.isoladministrativesolutions.model.dataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Product extends RealmObject {

    @SerializedName("item_name")
    @Expose
    private String itemName;
    @PrimaryKey
    @SerializedName("item_code")
    @Expose
    private String itemCode;
    @SerializedName("item_id")
    @Expose
    private String itemId;

    public Product() {
    }

    public Product(String itemName, String itemCode, String itemId) {
        this.itemName = itemName;
        this.itemCode = itemCode;
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

}

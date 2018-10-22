package com.appinionbd.isoladministrativesolutions.model.dataHolder;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class SavedProduct extends RealmObject {
    @PrimaryKey
    private String savedProductId;
    private String itemId;
    private String itemCode;
    private String itemName;
    private String floorId;
    private String quantity;

    public SavedProduct() {
    }

    public SavedProduct(String savedProductId, String itemId, String itemCode, String itemName, String floorId, String quantity) {
        this.savedProductId = savedProductId;
        this.itemId = itemId;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.floorId = floorId;
        this.quantity = quantity;
    }

    public String getSavedProductId() {
        return savedProductId;
    }

    public void setSavedProductId(String savedProductId) {
        this.savedProductId = savedProductId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
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

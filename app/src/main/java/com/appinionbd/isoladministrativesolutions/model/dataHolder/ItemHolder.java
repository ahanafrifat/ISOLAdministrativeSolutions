package com.appinionbd.isoladministrativesolutions.model.dataHolder;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ItemHolder extends RealmObject {

    @PrimaryKey
    private String itemHolderId;
    private String itemId;
    private String floorId;
    private String quantity;

    public ItemHolder() {
    }

    public ItemHolder(String itemHolderId, String itemId, String floorId, String quantity) {
        this.itemHolderId = itemHolderId;
        this.itemId = itemId;
        this.floorId = floorId;
        this.quantity = quantity;
    }

    public String getItemHolderId() {
        return itemHolderId;
    }

    public void setItemHolderId(String itemHolderId) {
        this.itemHolderId = itemHolderId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
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

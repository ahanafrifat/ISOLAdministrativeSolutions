package com.appinionbd.isoladministrativesolutions.model.dataHolder;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class CartProduct extends RealmObject {

    @PrimaryKey
    private String itemCode;
    private String itemName;
    private String quantity;

    public CartProduct() {
    }

    public CartProduct(String itemCode, String itemName, String quantity) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.quantity = quantity;
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}

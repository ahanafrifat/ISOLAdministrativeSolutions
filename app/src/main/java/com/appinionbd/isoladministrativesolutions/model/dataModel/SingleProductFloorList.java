package com.appinionbd.isoladministrativesolutions.model.dataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SingleProductFloorList {

    @SerializedName("floor")
    @Expose
    private List<Floor> floor = null;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;

    public SingleProductFloorList() {
    }

    public SingleProductFloorList(List<Floor> floor, Integer status, String message) {
        this.floor = floor;
        this.status = status;
        this.message = message;
    }

    public List<Floor> getFloor() {
        return floor;
    }

    public void setFloor(List<Floor> floor) {
        this.floor = floor;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

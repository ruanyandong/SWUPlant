package com.example.ai.swuplant.entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PointInfo implements Serializable{


    private int pointNumber;//植物点位
    private double latitude;// 纬度，纬线
    private double longitude;//经度，经线
    private List<String> plantNameList;//植物名称列表

    public PointInfo(int pointNumber, double latitude, double longitude, List<String> plantNameList) {
        this.pointNumber = pointNumber;
        this.latitude = latitude;
        this.longitude = longitude;
        this.plantNameList = plantNameList;
    }

    public int getPointNumber() {
        return pointNumber;
    }

    public void setPointNumber(int pointNumber) {
        this.pointNumber = pointNumber;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public List<String> getPlantNameList() {
        return plantNameList;
    }

    public void setPlantNameList(List<String> plantNameList) {
        this.plantNameList = plantNameList;
    }

}

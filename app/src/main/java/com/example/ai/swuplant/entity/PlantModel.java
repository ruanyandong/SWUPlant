package com.example.ai.swuplant.entity;

public class PlantModel {

    private int mPlantImageId;
    private String mPlantCNName;
    private String mPlantEnName;
    private String mPlantProperty;
    private String mPlantDescription;
    private String mPlantDistribution;

    public PlantModel(int mPlantImageId, String mPlantCNName, String mPlantEnName, String mPlantProperty, String mPlantDescription, String mPlantDistribution) {
        this.mPlantImageId = mPlantImageId;
        this.mPlantCNName = mPlantCNName;
        this.mPlantEnName = mPlantEnName;
        this.mPlantProperty = mPlantProperty;
        this.mPlantDescription = mPlantDescription;
        this.mPlantDistribution = mPlantDistribution;
    }

    public int getPlantImageId() {
        return mPlantImageId;
    }

    public void setPlantImageId(int mPlantImageId) {
        this.mPlantImageId = mPlantImageId;
    }

    public String getPlantCNName() {
        return mPlantCNName;
    }

    public void setPlantCNName(String mPlantCNName) {
        this.mPlantCNName = mPlantCNName;
    }

    public String getPlantEnName() {
        return mPlantEnName;
    }

    public void setPlantEnName(String mPlantEnName) {
        this.mPlantEnName = mPlantEnName;
    }

    public String getPlantProperty() {
        return mPlantProperty;
    }

    public void setPlantProperty(String mPlantProperty) {
        this.mPlantProperty = mPlantProperty;
    }

    public String getPlantDescription() {
        return mPlantDescription;
    }

    public void setPlantDescription(String mPlantDescription) {
        this.mPlantDescription = mPlantDescription;
    }

    public String getPlantDistribution() {
        return mPlantDistribution;
    }

    public void setPlantDistribution(String mPlantDistribution) {
        this.mPlantDistribution = mPlantDistribution;
    }

}

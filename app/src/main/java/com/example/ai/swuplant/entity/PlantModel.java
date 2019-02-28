package com.example.ai.swuplant.entity;

public class PlantModel {

    private String plantChineseName;
    private String plantEnglishName;
    private String plantProperty;
    private String plantDescription;
    private String plantDistribution;
    private String plantImageURL;

    public PlantModel(String plantChineseName, String plantEnglishName, String plantProperty, String plantDescription, String plantDistribution, String plantImageURL) {
        this.plantChineseName = plantChineseName;
        this.plantEnglishName = plantEnglishName;
        this.plantProperty = plantProperty;
        this.plantDescription = plantDescription;
        this.plantDistribution = plantDistribution;
        this.plantImageURL = plantImageURL;
    }

    public String getPlantChineseName() {
        return plantChineseName;
    }

    public void setPlantChineseName(String plantChineseName) {
        this.plantChineseName = plantChineseName;
    }

    public String getPlantEnglishName() {
        return plantEnglishName;
    }

    public void setPlantEnglishName(String plantEnglishName) {
        this.plantEnglishName = plantEnglishName;
    }

    public String getPlantProperty() {
        return plantProperty;
    }

    public void setPlantProperty(String plantProperty) {
        this.plantProperty = plantProperty;
    }

    public String getPlantDescription() {
        return plantDescription;
    }

    public void setPlantDescription(String plantDescription) {
        this.plantDescription = plantDescription;
    }

    public String getPlantDistribution() {
        return plantDistribution;
    }

    public void setPlantDistribution(String plantDistribution) {
        this.plantDistribution = plantDistribution;
    }

    public String getPlantImageURL() {
        return plantImageURL;
    }

    public void setPlantImageURL(String plantImageURL) {
        this.plantImageURL = plantImageURL;
    }

}

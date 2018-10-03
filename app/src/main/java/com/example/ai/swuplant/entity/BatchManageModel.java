package com.example.ai.swuplant.entity;

public class BatchManageModel {

    private boolean isCheck;
    private PlantModel plantModel;

    public BatchManageModel(PlantModel plantModel){
      this.plantModel = plantModel;
    }

    public boolean getCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public PlantModel getPlantModel() {
        return plantModel;
    }

    public void setPlantModel(PlantModel plantModel) {
        this.plantModel = plantModel;
    }

}

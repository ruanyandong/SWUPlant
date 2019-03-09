package com.example.ai.swuplant.net.bean;

import java.util.List;

public class CollectionBackResult {
    private int code;
    private String msg;
    private String username;
    private List<String> plantNames;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getPlantNames() {
        return plantNames;
    }

    public void setPlantNames(List<String> plantNames) {
        this.plantNames = plantNames;
    }


    @Override
    public String toString() {
        return getCode()+"\t"+getMsg()+"\t"+getUsername()+"\t"+getPlantNames();
    }
}

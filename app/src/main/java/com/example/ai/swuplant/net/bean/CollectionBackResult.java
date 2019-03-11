package com.example.ai.swuplant.net.bean;

import java.util.List;

public class CollectionBackResult {
    private int code;
    private String msg;
    private Data data;
    public class Data{
        private String username;
        private List<String> plantNames;

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
    }

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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}

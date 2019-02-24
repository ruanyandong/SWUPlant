package com.example.ai.swuplant.net.bean;

import android.support.annotation.NonNull;

public class LoginBackResult {
    private int code;
    private String msg;
    private Data data;
    private class Data{
        private int id;
        private String username;
        private String password;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
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

    @NonNull
    @Override
    public String toString() {
        return "{"+getCode()+","+getMsg()+","+getData().getId()+","+getData().getUsername()+","+getData().getPassword()+"}";
    }
}

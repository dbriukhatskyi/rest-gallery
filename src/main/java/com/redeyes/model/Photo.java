package com.redeyes.model;

import java.io.Serializable;

public class Photo implements Serializable {
    private static int COUNT = 0;
    private int id;
    private String data = "Photo Name";

    public Photo() {
        id = ++COUNT;
    }

    public Photo(int id, String data) {
        this.id = id;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

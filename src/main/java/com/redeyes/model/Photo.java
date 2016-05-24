package com.redeyes.model;

public class Photo {
    private int id;
    private byte[] photo;

    public Photo(byte[] photo) {
        this.photo = photo;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

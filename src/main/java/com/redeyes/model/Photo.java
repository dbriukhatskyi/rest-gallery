package com.redeyes.model;

public class Photo {
    private static int COUNT = 0;
    private int id;
    private byte[] photo;

    public Photo(byte[] photo) {
        id = COUNT++;
        this.photo = photo;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public int getId() {
        return id;
    }
}

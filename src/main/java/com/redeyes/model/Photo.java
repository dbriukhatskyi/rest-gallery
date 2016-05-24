package com.redeyes.model;

public class Photo {
    private int id;
    private byte[] photo;

    public Photo(final byte[] photo) {
        this.photo = photo;
    }

    public final byte[] getPhoto() {
        return photo;
    }

    public final int getId() {
        return id;
    }

    public final void setId(final int id) {
        this.id = id;
    }
}

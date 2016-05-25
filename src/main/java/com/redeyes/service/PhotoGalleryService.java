package com.redeyes.service;

import java.util.List;

public interface PhotoGalleryService {
    void savePhotosFromDir(String dir);

    byte[] getPhoto(int id);

    int getSize();

    List<Integer> getIds();
}

package com.redeyes.service;

import java.io.IOException;
import java.util.List;

public interface PhotoGalleryService {
    void savePhoto(String dir) throws IOException;

    byte[] getPhoto(int id);

    int getSize();

    List<Integer> getIds();

}

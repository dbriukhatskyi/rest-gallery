package com.redeyes.service;

import com.redeyes.model.Photo;

import java.util.Collection;

public interface PhotoGalleryService {
    public Collection<Photo> getPhotosFromDir(String dirPath);
}

package com.redeyes.service;

import com.redeyes.model.Photo;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;

@Service
public class PhotoGalleryServiceImpl implements PhotoGalleryService {


    @Override
    public Collection<Photo> getPhotosFromDir(String dirPath) {
        //scan dir for photos and return photos.
        return Arrays.asList(new Photo(), new Photo());
    }
}

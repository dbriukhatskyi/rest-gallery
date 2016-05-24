package com.redeyes.repository;

import com.redeyes.model.Photo;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public class PhotoRepositoryImpl implements PhotoRepository {
    private List<Photo> photos;
    @Override

    public void add(Photo photo) {
        photos.add(photo);
    }

    @Override
    public byte[] get(int id) {
        return photos.stream()
                .filter(photo -> photo.getId() == id).findFirst().get().getPhoto();
    }

    @Override
    public int size() {
        return photos.size();
    }

    @Override
    public List<Photo> getAll() {
        return photos;
    }

    @Override
    public void init() {
        photos = new LinkedList<>();
    }
}

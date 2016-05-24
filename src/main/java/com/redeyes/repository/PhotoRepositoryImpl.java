package com.redeyes.repository;

import com.redeyes.model.Photo;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public class PhotoRepositoryImpl implements PhotoRepository {
    private int photoId = 0;
    private List<Photo> photos;

    @Override
    public final void add(final Photo photo) {
        photo.setId(photoId++);
        photos.add(photo);
    }

    @Override
    public final byte[] get(final int id) {
        return photos.stream()
                .filter(photo -> photo.getId() == id).findFirst().get().getPhoto();
    }

    @Override
    public final int size() {
        return photos.size();
    }

    @Override
    public final void init() {
        photoId = 0;
        photos = new LinkedList<>();
    }

    @Override
    public final List<Integer> getIds() {
        List<Integer> counts = new LinkedList<>();
        for (int i = 0; i < photos.size(); i++) {
            counts.add(i);
        }
        return counts;
    }
}

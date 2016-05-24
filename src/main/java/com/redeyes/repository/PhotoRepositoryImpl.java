package com.redeyes.repository;

import com.redeyes.model.Photo;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public class PhotoRepositoryImpl implements PhotoRepository {
    private static int ID = 0;
    private List<Photo> photos;

    @Override

    public void add(Photo photo) {
        photo.setId(ID++);
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
        ID = 0;
        photos = new LinkedList<>();
    }

    @Override
    public List<Integer> getIds() {
        List<Integer> counts = new LinkedList<>();
        for (int i = 0; i < photos.size(); i++) {
            counts.add(i);
        }
        return counts;
    }
}

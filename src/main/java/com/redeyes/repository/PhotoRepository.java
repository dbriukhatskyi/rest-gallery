package com.redeyes.repository;

import com.redeyes.model.Photo;

import java.util.List;

public interface PhotoRepository {
    void add(Photo photo);

    byte[] get(int id);

    int size();

    List<Photo> getAll();

    void init();
}

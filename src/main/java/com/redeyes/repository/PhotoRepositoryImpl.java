package com.redeyes.repository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class PhotoRepositoryImpl implements PhotoRepository {

    private List<byte[]> photos;

    @Override
    public final void add(final byte[] photo) {
        photos.add(photo);
    }

    @Override
    public final byte[] get(final int id) {
        if (id < photos.size() && id >= 0) {
            return photos.get(id);
        }
        return new byte[]{ };
    }

    @Override
    public final int size() {
        return photos.size();
    }

    @Override
    public final void init() {
        photos = new ArrayList<>();
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

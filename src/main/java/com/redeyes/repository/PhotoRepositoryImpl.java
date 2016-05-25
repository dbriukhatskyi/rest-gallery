package com.redeyes.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Repository
public class PhotoRepositoryImpl implements PhotoRepository {
    private static final Logger LOG = LoggerFactory.getLogger(PhotoRepositoryImpl.class);

    private List<byte[]> photos = new ArrayList<>();

    @Override
    public final void add(final byte[] photo) {
        photos.add(photo);
    }

    @Override
    public final byte[] get(final int id) {
        LOG.info("Returned photo with id: {}", id);
        return photos.get(id);
    }

    @Override
    public final int size() {
        LOG.info("Returned cache size.");
        return photos.size();
    }

    @Override
    public final void init() {
        LOG.info("Initialize storage.");
        photos = new ArrayList<>();
    }

    @Override
    public final List<Integer> getIds() {
        LOG.info("Returned photo id's.");
        List<Integer> counts = new LinkedList<>();
        for (int i = 0; i < photos.size(); i++) {
            counts.add(i);
        }
        return counts;
    }
}

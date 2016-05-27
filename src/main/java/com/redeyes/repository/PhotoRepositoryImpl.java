package com.redeyes.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Photo cache storage implements storage interface.
 *
 * @author Oleksandr Dres.
 * @author Dmytro Briukhatskyi.
 */
@Repository
public class PhotoRepositoryImpl implements PhotoRepository {
    /**
     * Logger cache storage.
     */
    private static final Logger LOG = LoggerFactory.getLogger(PhotoRepositoryImpl.class);

    /**
     * Field contains photos in byte array.
     */
    private List<byte[]> photos = new ArrayList<>();

    @Override
    public final void add(final byte[] photo) {
        LOG.info("Add photo to cache.");
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
        int cacheSize = size();
        List<Integer> counts = new ArrayList<>(cacheSize);
        for (int i = 0; i < cacheSize; i++) {
            counts.add(i);
        }
        return counts;
    }
}

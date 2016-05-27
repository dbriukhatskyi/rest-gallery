package com.redeyes.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * A photo storage implementation using in-memory cache.
 *
 * @author Oleksandr Dres
 * @author Dmytro Briukhatskyi
 */
@Repository
public class PhotoRepositoryImpl implements PhotoRepository {
    /**
     * Logger used by a storage.
     */
    private static final Logger LOG = LoggerFactory.getLogger(PhotoRepositoryImpl.class);

    /**
     * All stored photos in the form of byte arrays.
     */
    private List<byte[]> photos = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public final void add(final byte[] photo) {
        LOG.debug("Adding new photo to cache.");
        photos.add(photo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final byte[] get(final int id) {
        LOG.debug("Returning photo with id: {}", id);
        return photos.get(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int size() {
        LOG.debug("Returning cache size.");
        return photos.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void init() {
        LOG.debug("Initializing storage.");
        photos.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<Integer> getIds() {
        LOG.debug("Returning a list of photo IDs.");
        int cacheSize = size();
        List<Integer> counts = new ArrayList<>(cacheSize);

        for (int i = 0; i < cacheSize; i++) {
            counts.add(i);
        }

        return counts;
    }
}

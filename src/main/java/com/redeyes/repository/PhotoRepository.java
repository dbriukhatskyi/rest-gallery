package com.redeyes.repository;

import java.util.List;

/**
 * Photo cache interface.
 *
 * @author Oleksandr Dres.
 * @author Dmytro Briukhatskyi.
 */
public interface PhotoRepository {
    /**
     * Add photo to cache.
     *
     * @param photo Photo file bytes.
     */
    void add(byte[] photo);

    /**
     * @param id Photo id.
     * @return Photo in byte array.
     */
    byte[] get(int id);

    /**
     * Returned photo count in cache.
     *
     * @return Photo count in cache.
     */
    int size();

    /**
     * Initialize cache storage.
     */
    void init();

    /**
     * Returned list of photo id's.
     *
     * @return List of photo id's.
     */
    List<Integer> getIds();
}

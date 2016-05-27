package com.redeyes.repository;

import java.util.List;

/**
 * A photo storage interface.
 *
 * @author Oleksandr Dres.
 * @author Dmytro Briukhatskyi.
 */
public interface PhotoRepository {
    /**
     * Adds a photo to the storage.
     *
     * @param photo
     *        a byte array representing image file contents
     */
    void add(byte[] photo);

    /**
     * Returns a photo with specified ID from storage.
     *
     * @param id
     *        photo ID in the cache
     *
     * @return a byte array representing image file contents
     */
    byte[] get(int id);

    /**
     * Returns current count of photos in the storage.
     *
     * @return count of photos in the storage
     */
    int size();

    /**
     * Initializes or cleans up the storage.
     */
    void init();

    /**
     * Returns a list of all available in the storage photo IDs.
     *
     * @return a list of photo IDs
     */
    List<Integer> getIds();
}

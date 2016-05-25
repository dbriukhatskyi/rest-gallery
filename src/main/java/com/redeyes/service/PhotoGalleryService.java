package com.redeyes.service;

import java.util.List;

/**
 * Service for photo gallery.
 *
 * @author Oleksandr Dres.
 * @author Dmytro Briukhatskyi.
 */
public interface PhotoGalleryService {
    /**
     * Scanning directory on the photo and adding to the cache.
     *
     * @param dir Directory on local disk to scan for photos.
     */
    void savePhotosFromDir(String dir);

    /**
     * Returned photo from cache.
     *
     * @param id Photo id in cache.
     * @return Photo file in byte array.
     */
    byte[] getPhoto(int id);

    /**
     * Returned photo count in cache.
     *
     * @return Photo count in cache.
     */
    int getSize();

    /**
     * Returned photo id's.
     *
     * @return Photo id,s.
     */
    List<Integer> getIds();
}

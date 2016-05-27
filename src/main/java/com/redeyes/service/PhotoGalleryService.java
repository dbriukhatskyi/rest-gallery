package com.redeyes.service;

import java.util.List;

/**
 * A photo gallery service.
 *
 * @author Oleksandr Dres
 * @author Dmytro Briukhatskyi
 */
public interface PhotoGalleryService {
    /**
     * Scans directory for the photos and adds found photos to the storage.
     *
     * @param dir
     *        a local directory to scan for photos
     */
    void savePhotosFromDir(String dir);

    /**
     * Returns a photo with the specified ID from the storage.
     *
     * @param id
     *        ID of the photo in the storage
     *
     * @return a byte array representing image file contents
     */
    byte[] getPhoto(int id);

    /**
     * Returns current count of photos in the storage.
     *
     * @return count of photos in the storage
     */
    int getSize();

    /**
     * Returns a list of all available in the storage photo IDs.
     *
     * @return a list of photo IDs
     */
    List<Integer> getIds();
}

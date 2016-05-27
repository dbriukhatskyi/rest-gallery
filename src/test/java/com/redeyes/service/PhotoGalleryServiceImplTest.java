package com.redeyes.service;

import com.redeyes.ResTfulPhotoGalleryApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test for photo service.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(ResTfulPhotoGalleryApplication.class)
@WebAppConfiguration
public class PhotoGalleryServiceImplTest {


    /**
     * Path to photo.
     */
    private static final String IMAGE_URL = System.getProperty("user.dir")
            + "\\src\\test\\resources\\images";
    /**
     * Photo service.
     */
    @Autowired
    private PhotoGalleryService service;

    @Before
    public void before() {
        service.savePhotosFromDir(IMAGE_URL + "\\cat");
    }

    /**
     * Add test image to cache.
     */
    @Test
    public void testSavePhotosFromDir() {
        service.savePhotosFromDir(IMAGE_URL + "\\fox");
    }

    /**
     * Empty dir
     */
    @Test
    public void testSavePhotosFromEmptyDir() {
        service.savePhotosFromDir(IMAGE_URL + "\\empty");
    }

    /**
     * Error reading from dir
     */
    @Test
    public void testSavePhotosFromDirError() {
        service.savePhotosFromDir(IMAGE_URL + "\\errorDir");
    }

    /**
     * Test fail image.
     */
    @Test
    public void testSavePhotosFromDirFailImage() {
        service.savePhotosFromDir(IMAGE_URL + "\\fail");
    }

    /**
     * Test path to file.
     */
    @Test
    public void testSavePhotosFromFile() {
        service.savePhotosFromDir(IMAGE_URL + "\\fox\\Fox.png");
    }

    /**
     * Test get number of photos.
     */
    @Test
    public void testGetSize() {
        assertEquals(service.getSize(), 1);
    }

    /**
     * Get photo id's.
     */
    @Test
    public void testGetIds() {
        assertTrue(service.getIds().equals(Arrays.asList(0)));
    }

    /**
     * Test get photo.
     */
    @Test
    public void testGetPhoto() {
        service.getPhoto(0);
    }
}
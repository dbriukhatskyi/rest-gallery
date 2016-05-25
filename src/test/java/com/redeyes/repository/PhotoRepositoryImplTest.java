package com.redeyes.repository;

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
 * Tests for photo cache.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(ResTfulPhotoGalleryApplication.class)
@WebAppConfiguration
public class PhotoRepositoryImplTest {
    /**
     * Photo storage
     */
    @Autowired
    private PhotoRepository photos;

    /**
     * Fill storage mock photos.
     */
    @Before
    public void before() {
        photos.add(new byte[]{0, 0, 0, 0, 1});
    }

    /**
     * Add photo to cache test.
     */
    @Test
    public void testAdd() {
        photos.add(new byte[]{10, 10, 10, 10});
        assertEquals(photos.size(), 4);
    }

    /**
     * Get photo from
     */
    @Test
    public void testGet() {
        byte[] bytes = new byte[]{0, 0, 0, 0, 1};
        photos.add(bytes);
        assertTrue(Arrays.equals(bytes, photos.get(1)));
    }

    /**
     * Test cache size.
     */
    @Test
    public void testSize() {
        assertEquals(photos.size(), 1);
    }

    /**
     * Init cache test.
     */
    @Test
    public void testInit() {
        photos.init();
    }

    /**
     * Get id's test.
     */
    @Test
    public void testGetIds() {
        assertTrue(photos.getIds().equals(Arrays.asList(0, 1)));
    }

    /**
     * Get wrong photo test.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void getWrongId() {
        assertTrue(Arrays.equals(photos.get(8), new byte[]{0, 1, 1, 1, 1}));
    }

}
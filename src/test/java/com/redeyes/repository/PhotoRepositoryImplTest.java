package com.redeyes.repository;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for photo cache.
 */
public class PhotoRepositoryImplTest {
    /**
     * Photo storage
     */
    private PhotoRepository photos = new PhotoRepositoryImpl();

    /**
     * Fill storage mock photos.
     */
    @Before
    public void before() {
        photos.add(new byte[]{0, 0, 0, 0, 1});
        photos.add(new byte[]{0, 0, 0, 1, 1});
        photos.add(new byte[]{0, 0, 1, 1, 1});
        photos.add(new byte[]{0, 1, 1, 1, 1});
        photos.add(new byte[]{1, 1, 1, 1, 1});
    }

    /**
     * Add photo to cache test.
     */
    @Test
    public void testAdd() {
        photos.add(new byte[]{10, 10, 10, 10});
        assertEquals(photos.size(), 6);
    }

    /**
     * Get photo from
     */
    @Test
    public void testGet() {
        assertTrue(Arrays.equals(photos.get(3), new byte[]{0, 1, 1, 1, 1}));
    }

    /**
     * Test cache size.
     */
    @Test
    public void testSize() {
        assertEquals(photos.size(), 5);
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
        assertTrue(photos.getIds().equals(Arrays.asList(0, 1, 2, 3, 4)));
    }

    /**
     * Get wrong photo test.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void getWrongId() {
        assertTrue(Arrays.equals(photos.get(8), new byte[]{0, 1, 1, 1, 1}));
    }

}
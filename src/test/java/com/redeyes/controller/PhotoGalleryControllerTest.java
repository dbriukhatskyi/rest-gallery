package com.redeyes.controller;

import com.redeyes.ResTfulPhotoGalleryApplication;
import com.redeyes.service.PhotoGalleryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test for controller.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(ResTfulPhotoGalleryApplication.class)
@WebAppConfiguration
public class PhotoGalleryControllerTest {

    private static String pathToCatImage = System.getProperty("user.dir")
            + "\\src\\test\\resources\\images\\cat";

    private static String URL = "/photo";

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private PhotoGalleryService service;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    /**
     * Test main page with form.
     *
     * @throws Exception Exception.
     */
    @Test
    public void testHome() throws Exception {
        mockMvc.perform(get(URL))
                .andExpect(status().isOk())
                .andExpect(view().name("photo"))
                .andExpect(model().attribute("main", true));
    }

    @Test
    public void testPost() throws Exception {
        mockMvc.perform(post(URL).param("path", pathToCatImage))
                .andExpect(status().isOk())
                .andExpect(view().name("photo"))
                .andExpect(model().attribute("size", 1));
        assertEquals(service.getSize(), 1);
    }

    @Test
    public void testBlack() throws Exception {
        mockMvc.perform(get(URL + "/blackbackground"))
                .andExpect(status().isOk())
                .andExpect(view().name("photo"))
                .andExpect(model().attribute("black", true));
    }

    @Test
    public void testOriginal() throws Exception {
        mockMvc.perform(get(URL + "/original"))
                .andExpect(status().isOk())
                .andExpect(view().name("photo"))
                .andExpect(model().attribute("original", true));
    }

    @Test
    public void testRows() throws Exception {
        mockMvc.perform(get(URL + "/row/5"))
                .andExpect(status().isOk())
                .andExpect(view().name("photo"))
                .andExpect(model().attribute("row", 5));
    }

    @Test
    public void testWh() throws Exception {
        mockMvc.perform(get(URL + "/wh/250x250"))
                .andExpect(status().isOk())
                .andExpect(view().name("photo"))
                .andExpect(model().attribute("height", "250"))
                .andExpect(model().attribute("width", "250"));
    }

    @Test
    public void testImage() throws Exception {
        service.savePhotosFromDir(pathToCatImage);
        mockMvc.perform(get(URL + "/image/0"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.IMAGE_PNG));
    }

}
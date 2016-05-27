package com.redeyes.controller;

import com.redeyes.service.PhotoGalleryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.ByteArrayInputStream;

/**
 * Photo gallery controller.
 *
 * @author Oleksandr Dres
 * @author Dmytro Briukhatskyi
 */
@Controller
@RequestMapping("/photo")
public class PhotoGalleryController {
    /**
     * Logger for photo controller.
     */
    private static final Logger LOG = LoggerFactory.getLogger(PhotoGalleryController.class);
    /**
     * Default rows count for photo view.
     */
    private static final int DEFAULT_ROWS = 4;
    /**
     * Default photo size in pixels.
     */
    private static final int DEFAULT_SIZE = 200;

    /**
     * Photo gallery service.
     */
    @Autowired
    private PhotoGalleryService service;

    /**
     * Returns main screen with a prompt to pick a directory.
     *
     * @return View with form
     */
    @RequestMapping(method = RequestMethod.GET)
    public final ModelAndView home() {
        LOG.info("Returned main view.");
        return new ModelAndView("photo", "main", true);
    }

    /**
     * Scans directory for photos, adds photos to cache and returns a view with a photo gallery.
     *
     * @param path
     *        path to a local directory with photos
     *
     * @return view with gallery using default photo parameters
     */
    @RequestMapping(method = RequestMethod.POST)
    public final ModelAndView post(@RequestParam final String path) {
        service.savePhotosFromDir(path);
        ModelAndView model = getModelAndView();
        addDefaultPhotoSize(model);
        LOG.info("Returned photo view.");
        return model;
    }

    /**
     * Returns photo gallery with black background.
     *
     * @return view with a gallery using black background
     */
    @RequestMapping(value = "/blackbackground", method = RequestMethod.GET)
    public final ModelAndView black() {
        ModelAndView model = getModelAndView();
        model.addObject("black", true);
        addDefaultPhotoSize(model);
        LOG.info("Returned photo view with black background.");
        return model;
    }

    /**
     * Returns photo gallery with original photo size.
     *
     * @return view with a gallery using original photo size
     */
    @RequestMapping(value = "/original", method = RequestMethod.GET)
    public final ModelAndView original() {
        ModelAndView model = getModelAndView();
        model.addObject("original", true);
        LOG.info("Returned photo view with original photo size.");
        return model;
    }

    /**
     * Returns photo gallery with custom photos per row count.
     *
     * @param row
     *        photos per row count
     *
     * @return view with a gallery using custom photos per row count
     */
    @RequestMapping(value = "/row/{row}", method = RequestMethod.GET)
    public final ModelAndView rows(@PathVariable final int row) {
        ModelAndView model = getModelAndView();
        model.addObject("row", row);
        addDefaultPhotoSize(model);
        LOG.info("Returned photo view {} in a row.", row);
        return model;
    }

    /**
     * Returns photo gallery using custom photo size.
     *
     * @param wh
     *        (width and height) custom size in form of YYYxZZZ,
     *        where YYY is width and ZZZ is height to use
     *        for photos display in the gallery
     *
     * @return view with a gallery using custom photo size
     */
    @RequestMapping(value = "/wh/{wh:\\d{3}x\\d{3}}", method = RequestMethod.GET)
    public final ModelAndView wh(@PathVariable final String wh) {
        ModelAndView model = getModelAndView();
        String[] size = wh.split("x");
        model.addObject("width", size[0]);
        model.addObject("height", size[1]);
        LOG.info("Returned photo view with photo size: {}.", wh);
        return model;
    }

    /**
     * Returns specified photo from server cache.
     *
     * @param id
     *        ID of the photo to retrieve from cache
     *
     * @return {@code InputStreamResource} representing a PNG image
     */
    @RequestMapping(value = "/image/{id:\\d+}", method = RequestMethod.GET)
    public final ResponseEntity<InputStreamResource> image(@PathVariable final int id) {
        byte[] img = service.getPhoto(id);

        return ResponseEntity.ok()
                .contentLength(img.length)
                .contentType(MediaType.IMAGE_PNG)
                .body(new InputStreamResource(new ByteArrayInputStream(img)));
    }

    /**
     * Returns photo gallery view with default parameters.
     *
     * @return view with a gallery using default parameters
     */
    private ModelAndView getModelAndView() {
        ModelAndView modelAndView = new ModelAndView("photo");
        modelAndView.addObject("gallery", true);
        modelAndView.addObject("Ids", service.getIds());
        modelAndView.addObject("size", service.getSize());
        modelAndView.addObject("row", DEFAULT_ROWS);
        return modelAndView;
    }

    /**
     * Sets default photo size to view.
     *
     * @param model
     *        a view to set parameters to
     */
    private void addDefaultPhotoSize(final ModelAndView model) {
        model.addObject("width", DEFAULT_SIZE);
        model.addObject("height", DEFAULT_SIZE);
    }
}

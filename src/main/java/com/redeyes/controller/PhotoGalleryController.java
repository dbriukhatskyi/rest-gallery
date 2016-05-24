package com.redeyes.controller;

import com.redeyes.service.PhotoGalleryService;
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
import java.io.IOException;

@Controller
@RequestMapping("/photo")
public class PhotoGalleryController {
    private static final int DEFAULT_ROWS = 4;
    private static final int DEFAULT_SIZE = 200;

    @Autowired
    private PhotoGalleryService service;

    @RequestMapping(method = RequestMethod.GET)
    public final ModelAndView home() {
        return new ModelAndView("photo", "main", true);
    }

    @RequestMapping(method = RequestMethod.POST)
    public final ModelAndView post(@RequestParam final String path) throws IOException {
        service.savePhoto(path);
        ModelAndView model = getModelAndView();
        addDefaultPhotoSize(model);
        return model;
    }

    @RequestMapping(value = "/blackbackground", method = RequestMethod.GET)
    public final ModelAndView black() {
        ModelAndView model = getModelAndView();
        model.addObject("black", true);
        addDefaultPhotoSize(model);
        return model;
    }

    @RequestMapping(value = "/original", method = RequestMethod.GET)
    public final ModelAndView original() {
        ModelAndView model = getModelAndView();
        model.addObject("original", true);
        return model;
    }

    @RequestMapping(value = "/row/{row}", method = RequestMethod.GET)
    public final ModelAndView rows(@PathVariable final int row) {
        ModelAndView model = getModelAndView();
        model.addObject("row", row);
        addDefaultPhotoSize(model);
        return model;
    }

    @RequestMapping(value = "/wh/{wh:\\d{3}x\\d{3}}", method = RequestMethod.GET)
    public final ModelAndView wh(@PathVariable final String wh) {
        ModelAndView model = getModelAndView();
        String[] size = wh.split("x");
        model.addObject("width", size[0]);
        model.addObject("height", size[1]);
        return model;
    }

    @RequestMapping(value = "/image/{num:\\d+}", method = RequestMethod.GET)
    public final ResponseEntity<InputStreamResource> image(@PathVariable final int num) throws IOException {
        byte[] img = service.getPhoto(num);

        return ResponseEntity.ok()
                .contentLength(img.length)
                .contentType(MediaType.IMAGE_PNG)
                .body(new InputStreamResource(new ByteArrayInputStream(img)));
    }

    private ModelAndView getModelAndView() {
        ModelAndView modelAndView = new ModelAndView("photo");
        modelAndView.addObject("post", true);
        modelAndView.addObject("Ids", service.getIds());
        modelAndView.addObject("size", service.getSize());
        modelAndView.addObject("row", DEFAULT_ROWS);
        return modelAndView;
    }

    private void addDefaultPhotoSize(final ModelAndView model) {
        model.addObject("width", DEFAULT_SIZE);
        model.addObject("height", DEFAULT_SIZE);
    }
}

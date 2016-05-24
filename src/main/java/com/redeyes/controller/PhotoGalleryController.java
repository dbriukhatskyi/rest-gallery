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

    @Autowired
    private PhotoGalleryService service;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView home() {
        return new ModelAndView("photo", "main", true);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView post(@RequestParam String path) throws IOException {
        service.savePhoto(path);
        ModelAndView model = getModelAndView();
        addDefaultPhotoSize(model);
        return model;
    }

    @RequestMapping(value = "/blackbackground", method = RequestMethod.GET)
    public ModelAndView black() {
        ModelAndView model = getModelAndView();
        model.addObject("black", true);
        addDefaultPhotoSize(model);
        return model;
    }

    @RequestMapping(value = "/original", method = RequestMethod.GET)
    public ModelAndView original() {
        ModelAndView model = getModelAndView();
        model.addObject("original", true);
        return model;
    }

    @RequestMapping(value = "/row/{rows}", method = RequestMethod.GET)
    public ModelAndView rows(@PathVariable int rows) {
        ModelAndView model = getModelAndView();
        model.addObject("rows", rows);
        model.addObject("row", true);
        addDefaultPhotoSize(model);
        return model;
    }

    @RequestMapping(value = "/wh/{wh:\\d{3}x\\d{3}}", method = RequestMethod.GET)
    public ModelAndView wh(@PathVariable String wh) {
        ModelAndView model = getModelAndView();
        String[] size = wh.split("x");
        model.addObject("wh", true);
        model.addObject("width", size[0]);
        model.addObject("height", size[1]);
        return model;
    }

    @RequestMapping(value = "/image/{num:\\d}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> image(@PathVariable int num) throws IOException {
        byte[] img = service.getPhoto(num);

        return ResponseEntity.ok()
                .contentLength(img.length)
                .contentType(MediaType.IMAGE_PNG)
                .body(new InputStreamResource(new ByteArrayInputStream(img)));
    }

    private ModelAndView getModelAndView() {
        ModelAndView modelAndView = new ModelAndView("photo");
        modelAndView.addObject("post", true);
        modelAndView.addObject("counts", service.getSize());
        return modelAndView;
    }

    private void addDefaultPhotoSize(ModelAndView model) {
        model.addObject("width", 200);
        model.addObject("height", 200);
    }
}

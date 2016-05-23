package com.redeyes.controller;

import com.redeyes.service.PhotoGalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/photo")
public class PhotoGalleryController {

    private static String pathToPhotoDir;

    @Autowired
    private PhotoGalleryService service;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView home() {
        return new ModelAndView("photo", "main", true);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView post(@RequestParam String path) {
        pathToPhotoDir = path;
        ModelAndView modelAndView = new ModelAndView("photo", "photos",
                service.getPhotosFromDir(pathToPhotoDir));
        modelAndView.addObject("post", true);
        return modelAndView;
    }

    @RequestMapping(value = "/blackbackground", method = RequestMethod.GET)
    public ModelAndView black() {
        ModelAndView modelAndView = new ModelAndView("photo", "photos",
                service.getPhotosFromDir(pathToPhotoDir));
        modelAndView.addObject("post", true);
        modelAndView.addObject("black", true);
        return modelAndView;
    }

    @RequestMapping(value = "/original", method = RequestMethod.GET)
    public ModelAndView original() {
        ModelAndView modelAndView = new ModelAndView("photo", "photos",
                service.getPhotosFromDir(pathToPhotoDir));
        modelAndView.addObject("post", true);
        modelAndView.addObject("original", true);
        return modelAndView;
    }

    @RequestMapping(value = "/row/{rows}", method = RequestMethod.GET)
    public ModelAndView rows(@PathVariable int rows) {
        ModelAndView modelAndView = new ModelAndView("photo", "photos",
                service.getPhotosFromDir(pathToPhotoDir));
        modelAndView.addObject("post", true);
        modelAndView.addObject("rows", rows);
        modelAndView.addObject("row", true);
        return modelAndView;
    }

    @RequestMapping(value = "/wh/{wh:\\d{3}x\\d{3}}", method = RequestMethod.GET)
    public ModelAndView wh(@PathVariable String wh ) {
        ModelAndView modelAndView = new ModelAndView("photo", "photos",
                service.getPhotosFromDir(pathToPhotoDir));
        modelAndView.addObject("post", true);
        String[] size = wh.split("x");
        modelAndView.addObject("wh", true);
        modelAndView.addObject("width", size[0]);
        modelAndView.addObject("height", size[1]);
        return modelAndView;
    }
}

package com.redeyes.service;

import com.redeyes.model.Photo;
import com.redeyes.repository.PhotoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

@Service
public class PhotoGalleryServiceImpl implements PhotoGalleryService {
    private static final Logger LOG = LoggerFactory.getLogger(PhotoGalleryServiceImpl.class.getName());

    @Autowired
    private PhotoRepository repository;

    @Override
    public void savePhoto(String dirPath) throws IOException {
        List<Path> files = DirectoryScanner.getFiles(dirPath);
        repository.init();
        for (Path file : files) {
            BufferedInputStream photo = new BufferedInputStream(
                    new FileInputStream(file.toFile().toString())
            );
            byte[] bytes = new byte[photo.available()];
            photo.read(bytes);
            photo.close();
            repository.add(new Photo(bytes));
        }
    }

    @Override
    public byte[] getPhoto(int id) {
        return repository.get(id);
    }

    @Override
    public List<Integer> getSize() {
        List<Integer> counts = new LinkedList<>();
        for (int i = 0; i < repository.size(); i++) {
            counts.add(i);
        }
        return counts;
    }
}

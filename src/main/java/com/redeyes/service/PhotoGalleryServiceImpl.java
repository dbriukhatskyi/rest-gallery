package com.redeyes.service;

import com.redeyes.model.Photo;
import com.redeyes.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@Service
public class PhotoGalleryServiceImpl implements PhotoGalleryService {

    @Autowired
    private PhotoRepository repository;

    @Override
    public final void savePhoto(final String dirPath) throws IOException {
        List<Path> files = DirectoryScanner.getFiles(dirPath);
        repository.init();
        for (Path file : files) {
            BufferedInputStream photo = new BufferedInputStream(new FileInputStream(file.toFile().toString())
            );
            byte[] bytes = new byte[photo.available()];
            photo.read(bytes);
            photo.close();
            repository.add(new Photo(bytes));
        }
    }

    @Override
    public final byte[] getPhoto(final int id) {
        return repository.get(id);
    }

    @Override
    public final int getSize() {
        return repository.size();
    }

    @Override
    public final List<Integer> getIds() {
        return repository.getIds();
    }
}

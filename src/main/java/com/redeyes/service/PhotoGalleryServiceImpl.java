package com.redeyes.service;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redeyes.repository.PhotoRepository;

@Service
public class PhotoGalleryServiceImpl implements PhotoGalleryService {

    private static final int BUF_SIZE = 4096;

    @Autowired
    private PhotoRepository repository;

    @Override
    public final void savePhotosFromDir(final String dirPath) throws IOException {
        List<Path> files = DirectoryScanner.getFiles(dirPath);
        repository.init();
        byte[] buffer = new byte[BUF_SIZE];

        for (Path file : files) {
            try (InputStream photo = new BufferedInputStream(Files.newInputStream(file), BUF_SIZE)) {
                ByteArrayOutputStream byteStream = new ByteArrayOutputStream();

                while(photo.available() > 0) {
                    int size = photo.read(buffer);
                    byteStream.write(buffer, 0, size);
                }

                repository.add(byteStream.toByteArray());
            }
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

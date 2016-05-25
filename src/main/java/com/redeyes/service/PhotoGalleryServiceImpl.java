package com.redeyes.service;

import com.redeyes.repository.PhotoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * A photo gallery service.
 *
 * @author Oleksandr Dres.
 * @author Dmytro Briukhatskyi.
 */
@Service
public class PhotoGalleryServiceImpl implements PhotoGalleryService {
    /**
     * Logger for photo service.
     */
    private static final Logger LOG = LoggerFactory.getLogger(PhotoGalleryServiceImpl.class);
    /**
     * Field contains buffer size.
     */
    private static final int BUF_SIZE = 4096;

    /**
     * Photo gallery cache storage.
     */
    @Autowired
    private PhotoRepository repository;

    @Override
    public final void savePhotosFromDir(final String dirPath) {
        LOG.info("Uploading photos...");
        List<Path> files = DirectoryScanner.getFiles(dirPath);
        if (files.isEmpty()) {
            LOG.info("Photos not found.");
            return;
        }
        repository.init();
        byte[] buffer = new byte[BUF_SIZE];
        LOG.info("Adds photos to cache...");
        for (Path file : files) {
            try (InputStream photo = new BufferedInputStream(Files.newInputStream(file), BUF_SIZE)) {
                ByteArrayOutputStream byteStream = new ByteArrayOutputStream();

                while (photo.available() > 0) {
                    int size = photo.read(buffer);
                    byteStream.write(buffer, 0, size);
                }

                repository.add(byteStream.toByteArray());
            } catch (IOException e) {
                LOG.error("Error reading photo file: {}", e.getMessage());
            }
        }
        LOG.info("Photos have been uploaded.");
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

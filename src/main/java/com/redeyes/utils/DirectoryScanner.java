/**
 *
 */
package com.redeyes.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * A helper class to provide functionality of searching for files
 * in the specified local directory.
 *
 * @author Dmytro Briukhatskyi
 */
public final class DirectoryScanner {
    /**
     * Logger for scanner.
     */
    private static final Logger LOG = LoggerFactory.getLogger(DirectoryScanner.class);
    /**
     * A photo extension.
     */
    private static final String IMG_FILE_EXT = ".png";

    /**
     * Ensure non-instantiability.
     */
    private DirectoryScanner() {
    }

    /**
     * Searches for files having the specified extension in the specified
     * directory and all of its subdirectories and returns a {@code Collection}
     * of the found files paths.
     *
     * @param directory path to directory to scan
     * @return a list of full paths to the files matching the input criteria
     */
    public static List<Path> getFiles(final String directory) {
        LOG.info("Finding *.png images...");
        Path baseDir = Paths.get(directory);

        if (!Files.exists(baseDir) || !Files.isDirectory(baseDir)) {
            return Collections.emptyList();
        }

        List<Path> found = new LinkedList<>();

        try {
            Files.walkFileTree(baseDir, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs)
                        throws IOException {

                    if (file.toString().endsWith(IMG_FILE_EXT)) {
                        found.add(file);
                    }

                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            LOG.error("Error reading directory: {}.\n Scanning photo stops", e.getMessage());
        }

        return found;
    }
}

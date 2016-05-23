/**
 *
 */
package com.redeyes.service;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * A helper class to provide functionality of searching for files
 * in the specified local directory.
 *
 * @author Dmytro Briukhatskyi
 *
 */
final class DirectoryScanner {

    /** Ensure non-instantiability */
    private DirectoryScanner() { }

    /**
     * Searches for files having the specified extension in the specified
     * directory and all of its subdirectories and returns a {@code Collection}
     * of the found files paths.
     *
     * @param extension
     *        a string with file extension to accept without a starting dot
     *
     * @return a list of full paths to the files matching the input criteria
     *
     * @throws IOException
     *         if I/O exception has happened during the directory scan
     */
    public static Collection<Path> getFiles(String directory, String extension) throws IOException {
        Path baseDir = Paths.get(directory);

        if (!Files.exists(baseDir) || !Files.isDirectory(baseDir)) {
            return Collections.emptyList();
        }

        String dotExtension = "." + extension;
        List<Path> found = new LinkedList<>();

        Files.walkFileTree(baseDir, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs)
                    throws IOException {

                if (file.toString().endsWith(dotExtension)) {
                    found.add(file);
                }

                return FileVisitResult.CONTINUE;
            }
        });

        return found;
    }

}

/**
 *
 */
package com.redeyes.service;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
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
     * @return a list of full paths to the files matching the input criteria
     *
     * @throws IOException
     *         if I/O exception has happened during the directory scan
     */
    public static List<Path> getFiles(String directory) throws IOException {
        Path baseDir = Paths.get(directory);

        if (!Files.exists(baseDir) || !Files.isDirectory(baseDir)) {
            return Collections.emptyList();
        }

        List<Path> found = new LinkedList<>();

        Files.walkFileTree(baseDir, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs)
                    throws IOException {

                if (file.toString().endsWith(".png")) {
                    found.add(file);
                }

                return FileVisitResult.CONTINUE;
            }
        });

        return found;
    }
//
//    public static void main(String[] args) throws IOException {
//        for (Path path : getFiles("C:\\image")) {
//            System.out.println(path.toFile().toString());
//        }
//    }
}

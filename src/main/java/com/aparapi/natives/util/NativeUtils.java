/**
 * Copyright (c) 2016 - 2018 Syncleus, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.aparapi.natives.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;

/**
 * A simple library class which helps with loading dynamic libraries stored in the
 * JAR archive. These libraries usually contain implementation of some methods in
 * native code (using JNI - Java Native Interface).
 *
 */
public class NativeUtils {

    /**
     * Private constructor - this class will never be instanced
     */
    private NativeUtils() {
    }

    /**
     * Loads library from current JAR archive
     *
     * The file from JAR is copied into system temporary directory and then loaded. The temporary file and folder are deleted after exiting.
     * Method uses String as filename because the pathname is "abstract", not system-dependent.
     *
     * @param path The path of file inside JAR as absolute path (beginning with '/'), e.g. /package/File.ext
     * @throws IOException If temporary file creation or read/write operation fails
     * @throws IllegalArgumentException If source file (param path) does not exist
     * @throws IllegalArgumentException If the path is not absolute or if the filename is shorter than three characters (restriction of @see File#createTempFile(java.lang.String, java.lang.String)).
     */
    public static void loadLibraryFromJar(String path, String libraryTargetFileName) throws IOException {

        if (!path.startsWith("/")) {
            throw new IllegalArgumentException("The path has to be absolute (start with '/').");
        }

        // Obtain filename from path
        String[] parts = path.split("/");
        String filename = (parts.length > 1) ? parts[parts.length - 1] : null;

        // Split filename to prefix and suffix (extension)
        String prefix = "";
        if (filename != null) {
            parts = filename.split("\\.", 2);
            prefix = parts[0];
        }

        // Check if the filename is okay
        if (filename == null || prefix.length() < 3) {
            throw new IllegalArgumentException("The filename has to be at least 3 characters long.");
        }

        // Prepare temporary file in a temporary folder, to keep names consistent, or Windows will unexpectedly fail to load the DLLs 
        Path tempDir = Files.createTempDirectory("Aparapi", new FileAttribute<?>[] {});
        tempDir.toFile().deleteOnExit();
        if (!tempDir.toFile().exists()) {
            throw new FileNotFoundException("Directory " + tempDir.toAbsolutePath() + " does not exist.");
        }
        
        Path libraryFilePath = tempDir.resolve(libraryTargetFileName);
        Path temp = Files.createFile(libraryFilePath, new FileAttribute<?>[] {});
        temp.toFile().deleteOnExit();
        if (!temp.toFile().exists()) {
            throw new FileNotFoundException("File " + temp.toAbsolutePath() + " does not exist.");
        }

        // Prepare buffer for data copying
        byte[] buffer = new byte[1024];
        int readBytes;

        // Open and check input stream
        InputStream is = NativeUtils.class.getResourceAsStream(path);
        if (is == null) {
            throw new FileNotFoundException("File " + path + " was not found inside JAR.");
        }

        // Open output stream and copy data between source file in JAR and the temporary file
        OutputStream os = new FileOutputStream(temp.toFile());
        try {
            while ((readBytes = is.read(buffer)) != -1) {
                os.write(buffer, 0, readBytes);
            }
        } finally {
            // If read/write fails, close streams safely before throwing an exception
            os.close();
            is.close();
        }
        
        // Finally, load the library
        System.load(temp.toAbsolutePath().toString());
    }
}

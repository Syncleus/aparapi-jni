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
package com.aparapi.natives;

import com.aparapi.natives.util.NativeUtils;

import java.io.IOException;

public class NativeLoader {
    private static final String ARCH = System.getProperty("os.arch").toLowerCase();
    private static final String OS = System.getProperty("os.name").toLowerCase();

    public static void load() throws IOException {
//        String arch = System.getenv("PROCESSOR_ARCHITECTURE");
//        String wow64Arch = System.getenv("PROCESSOR_ARCHITEW6432");
//
//        String realArch = arch.endsWith("64")
//                || wow64Arch != null && wow64Arch.endsWith("64")
//                ? "64" : "32";
        if( isUnix() ) {
            if( is64Bit() )
                NativeUtils.loadLibraryFromJar("/linux/libaparapi_x86_64.so");
            else
                NativeUtils.loadLibraryFromJar("/linux/libaparapi_x86.so");
        }
        else if( isMac() && is64Bit() )
            NativeUtils.loadLibraryFromJar("/osx/libaparapi_x86_64.dylib");
        else if( isWindows() && is64Bit() )
            NativeUtils.loadLibraryFromJar("/win/libaparapi_x86_64.dll");
        else if( isWindows() && is32Bit() )
            NativeUtils.loadLibraryFromJar("/win/libaparapi_x86.dll");
        else
            throw new IOException("System is not compatable with any of the known native libraries.");
    }

    private static boolean isWindows() {
        return OS.contains("win");
    }

    private static boolean isMac() {
        return OS.contains("mac");
    }

    private static boolean isUnix() {
        return (OS.contains("nix") || OS.contains("nux") || OS.contains("aix"));
    }

    private static boolean isSolaris() {
        return OS.contains("sunos");
    }

    private static boolean is64Bit() {
        return ARCH.contains("64");
    }

    private static boolean is32Bit() {
        return !is64Bit();
    }
}

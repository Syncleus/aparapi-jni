package com.aparapi.natives;

import com.aparapi.natives.util.NativeUtils;

import java.io.IOException;

public class NativeLoader {
    public static void load() throws IOException {
//        String arch = System.getenv("PROCESSOR_ARCHITECTURE");
//        String wow64Arch = System.getenv("PROCESSOR_ARCHITEW6432");
//
//        String realArch = arch.endsWith("64")
//                || wow64Arch != null && wow64Arch.endsWith("64")
//                ? "64" : "32";
        final String arch = System.getProperty("os.arch");
        if( arch.contains("64"))
            NativeUtils.loadLibraryFromJar("/linux/libaparapi_x86_64.so");
        else
            NativeUtils.loadLibraryFromJar("/linux/libaparapi_x86.so");
    }
}

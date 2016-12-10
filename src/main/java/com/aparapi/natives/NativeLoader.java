/**
 * Copyright (c) 2016 - 2017 Syncleus, Inc.
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

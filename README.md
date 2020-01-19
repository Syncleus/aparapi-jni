![](http://aparapi.com/images/logo-text-adjacent.png)

[![License](http://img.shields.io/:license-apache-blue.svg?style=flat-square)](http://www.apache.org/licenses/LICENSE-2.0.html)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.aparapi/aparapi-jni/badge.png?style=flat)](https://maven-badges.herokuapp.com/maven-central/com.aparapi/aparapi-jni/)
[![Javadocs](http://www.javadoc.io/badge/com.aparapi/aparapi-jni.svg)](http://www.javadoc.io/doc/com.aparapi/aparapi-jni)
[![Gitter](https://badges.gitter.im/Syncleus/aparapi.svg)](https://gitter.im/Syncleus/aparapi?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

**Licensed under the Apache Software License v2**

This project simply loads the [Aparapi Native](https://git.qoto.org/aparapi/aparapi-native) library at runtime. This prevents the need for the user to install it manually.

There is usually no need for a user to call this library directly as it is called internally by [Aparapi](https://git.qoto.org/aparapi/aparapi). However it may be useful if creating your own custom agent.

**Currently packaged native libraries support Windows 64bit, Windows 32bit, Mac OSX 64bit, Linux 64bit, and Linux 32bit.**

## Support and Documentation

The official source repository for this project can be found on [QOTO GitLab](https://git.qoto.org/aparapi/aparapi-jni) an up-to-date mirror of the repository is also hosted on [Github](https://github.com/Syncleus/aparapi).

Aparapi JNI Javadocs: [latest](http://www.javadoc.io/doc/com.aparapi/aparapi-jni) - [1.4.1](http://www.javadoc.io/doc/com.aparapi/aparapi-jni/1.4.1) - [1.4.0](http://www.javadoc.io/doc/com.aparapi/aparapi-jni/1.4.0) - [1.3.1](http://www.javadoc.io/doc/com.aparapi/aparapi-jni/1.3.1) - [1.2.0](http://www.javadoc.io/doc/com.aparapi/aparapi-jni/1.2.0) - [1.1.2](http://www.javadoc.io/doc/com.aparapi/aparapi-jni/1.1.2) - [1.1.1](http://www.javadoc.io/doc/com.aparapi/aparapi-jni/1.1.1) - [1.1.0](http://www.javadoc.io/doc/com.aparapi/aparapi-jni/1.1.0) - [1.0.1](http://www.javadoc.io/doc/com.aparapi/aparapi-jni/1.0.1) - [1.0.0](http://www.javadoc.io/doc/com.aparapi/aparapi-jni/1.0.0)

For detailed documentation see [Aparapi.com](http://Aparapi.com) or check out the [latest Javadocs](http://www.javadoc.io/doc/com.aparapi/aparapi-jni).

For support please use [Gitter](https://gitter.im/Syncleus/aparapi) or the [official Aparapi mailing list and Discourse forum](https://discourse.qoto.org/c/PROJ/APA).

Please file bugs and feature requests on [QOTO GitLab](https://git.qoto.org/aparapi/aparapi-jni/issues) older issues are archived at [Github](https://github.com/Syncleus/aparapi/issues).

## Related Projects

This particular repository only represents the core Java library. There are several other related repositories worth taking a look at.

* [Aparapi](https://git.qoto.org/aparapi/aparapi) - Core Aparapi java library.
* [Aparapi Examples](https://git.qoto.org/aparapi/aparapi-examples) - A collection of Java examples to showcase the Aparapi library and help developers get started.
* [Aparapi Native](https://git.qoto.org/aparapi/aparapi-native) - The native library component. Without this the Java library can't talk to the graphics card. This is not a java project but rather a C/C++ project.
* [Aparapi Vagrant](https://git.qoto.org/aparapi/aparapi-vagrant) - A vagrant environment for compiling aparapi native libraries for linux, both x86 an x64.
* [Aparapi Website](https://git.qoto.org/aparapi/aparapi.com) - Source for the Aparapi website as hosted at [http://aparapi.com](http://aparapi.com). The site also contains our detailed documentation.

## Java Dependency

To include Aparapi in your project of choice include the following Maven dependency into your build.

```xml

<dependency>
    <groupId>com.aparapi</groupId>
    <artifactId>aparapi-jni</artifactId>
    <version>1.4.1</version>
</dependency>
```

## Obtaining the Source

The official source repository for Aparapi is located on the QOTO GitLab repository and can be cloned using the
following command.

```bash

git clone http://git.qoto.org/aparapi/aparapi-jni.git
```

## Getting Started

Simply add the dependency above then call the load method, thats all there is to it.

```java

try {
   NativeLoader.load();
   System.out.println("Aparapi JNI loaded successfully.");
}
catch (final IOException e) {
   System.out.println("Check your environment. Failed to load aparapi native library "
         + " or possibly failed to locate opencl native library (opencl.dll/opencl.so)."
         + " Ensure that OpenCL is in your PATH (windows) or in LD_LIBRARY_PATH (linux).");
}
```

# Aparapi JNI Changelog

## 1.4.3

* Enforces Kernel and Device max work group size limitations and provides query functions for clGetKernelWorkGroupInfo(...)
* Support for armhf and aarch64 architectures for Linux OS
* Support for loading MingW compiled DLLs

## 1.4.2

* Fixed Potential JVM crash when using multi-dimensional arrays (> 1D)

## 1.4.1

* Fixed exception handling when calling Java method OpenCLDevice.configure() to not hide exceptions

## 1.4.0

* Fixed local arrays handling 1D and ND, to cope with arrays resizing across kernel executions
* Significant speed-up on discrete GPUs with dedicated memory - OpenCLDevice.setSharedMemory(false)
* Now supports efficient execution on discrete GPU and other devices with dedicated memory
* Support for OpenCLDevice configurator/configure API

## 1.3.1

* Fixed JVM crash with multi-dimensional arrays in Local memory (2D and 3D local arrays are now supported)

## 1.3.0

* This version was skipped due to a mistake in the versioning of the previous release. The version now matches the version of the native library.

## 1.2.0

* createProgram in Java had the wrong signature producing a unsatisfied link exception that is now fixed.
* The same scripts can now compile for Linux and OSX and potentially even windows.
* Now supports OpenCL 2.1.
* Fixed the context to a single device instead of all devices of a specific device type. creating a context per type breaks compilation on dopencl.
* Added jni method to write device name

## 1.1.2

* Fixed `UnsatisfiedLinkError` which occured only on Windows.

## 1.1.1

* Fixed "`CXXABI_1.3.8' not found " error encountered on some older systems.

## 1.1.0

* Added Windows 64bit support.
* The load method now throws an exception if a library can't be loaded rather than returning quietly.

## 1.0.1

* Added OSX support.

## 1.0.0

* Initial release, loads Aparapi JNI libraries dynamically so they don't need to be installed.

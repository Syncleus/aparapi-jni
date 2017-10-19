# Aparapi JNI Changelog

## 1.1.3

* createProgram in Java had the wrong signature producing a unsatisfied link exception that is now fixed.

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

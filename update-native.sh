#!/bin/sh

cd native
UID=${UID} GID=${GID} docker-compose run --rm aparapi-native-builder
cd ..

cp -f ./native/.libs32/win/libgcc_s_sjlj-1.dll ./src/main/resources/win/libgcc_s_sjlj_x86.dll
cp -f ./native/.libs32/win/libstdc++-6.dll ./src/main/resources/win/libstdc++-6_x86.dll
cp -f ./native/.libs32/win/libaparapi-1.dll ./src/main/resources/win/libaparapi_x86.dll

cp -f ./native/.libs64/win/libgcc_s_seh-1.dll ./src/main/resources/win/libgcc_s_seh_x86_64.dll
cp -f ./native/.libs64/win/libstdc++-6.dll ./src/main/resources/win/libstdc++-6_x86_64.dll
cp -f ./native/.libs64/win/libaparapi-1.dll ./src/main/resources/win/libaparapi_x86_64.dll

cp -f ./native/.libs32/linux/libaparapi.so.*.*.* ./src/main/resources/linux/libaparapi_x86.so
cp -f ./native/.libs64/linux/libaparapi.so.*.*.* ./src/main/resources/linux/libaparapi_x86_64.so

cp -f ./native/.libs32/linuxARM/libaparapi.so.*.*.* ./src/main/resources/linux/libaparapi_armhf.so
cp -f ./native/.libs64/linuxARM/libaparapi.so.*.*.* ./src/main/resources/linux/libaparapi_aarch64.so

cd native
UID=${UID} GID=${GID} docker-compose run --rm -e CROSS_TRIPLE=x86_64-apple-darwin aparapi-native-builder ./buildDarwin.sh
cd ..

cp -f ./native/.libs64/darwin/libaparapi.1.dylib ./src/main/resources/osx/libaparapi_x86_64.dylib

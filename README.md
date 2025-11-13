
# Flam R&D Intern — Real-Time Edge Detection Scaffold

**What this contains**
- Minimal Android project scaffold (core files) showing Camera2 -> JNI -> OpenCV C++ -> OpenGL ES texture rendering.
- Native C++ example with OpenCV edge detection (Canny).
- CMakeLists for NDK build.
- Simple OpenGL renderer skeleton.
- A small TypeScript web viewer that displays a sample processed image and FPS text.
- Detailed step-by-step setup and commands to make the full project buildable.

> This scaffold is a starting point. You must install Android Studio, Android NDK, and OpenCV for Android, then integrate the OpenCV SDK as described below.

---

## Folder layout (in this zip)
```
/app
  /src/main/AndroidManifest.xml
  /src/main/java/com/flam/rnd/MainActivity.kt
  /src/main/java/com/flam/rnd/GLRenderer.kt
  /src/main/java/com/flam/rnd/GLSurface.kt
  /jni/CMakeLists.txt
  /jni/native-lib.cpp
  /jni/native_headers.h
/web
  index.html
  src/main.ts
  package.json
  tsconfig.json
README.md
```

---

## Quick setup (high level)
1. Install Android Studio (Arctic Fox or later recommended).
2. Install Android NDK (inside SDK Tools) — note the version you install.
3. Download OpenCV Android SDK (e.g., opencv-4.x-android).
4. Import this project into Android Studio or create a new project and copy the files under `app/src/main` and `jni` accordingly.
5. Configure `build.gradle` to enable CMake/ndk build and set `externalNativeBuild` pointing to `jni/CMakeLists.txt`.
6. In CMakeLists, set path to OpenCV `sdk/native/jni` include/libs or use a local prebuilt OpenCV `.so`.
7. Build and run on a device (recommended) — camera access and NDK libs require device testing for camera and OpenGL.

---

## How the pieces fit together (architecture)
- `MainActivity.kt` — sets up TextureView, Camera2, and a GLSurface that receives processed frames.
- `native-lib.cpp` — JNI functions to accept YUV/RGBA frame data, convert to OpenCV `cv::Mat`, run Canny, and output RGBA bytes.
- `GLRenderer.kt` — creates an OpenGL texture from a byte buffer and renders it to the screen.
- `web/` — small TypeScript app that loads a `sample_processed.png` (you export from Android) and displays FPS/metadata.

---

## Step-by-step detailed instructions

### A. Android Studio / Gradle / NDK changes
1. Create a new Android project (Empty Activity). Minimum SDK: 24 or higher.
2. In `app/build.gradle`, enable externalNativeBuild:
```gradle
android {
  ...
  externalNativeBuild {
    cmake {
      path "jni/CMakeLists.txt"
      version "3.22.1"
    }
  }
  ndkVersion "25.2.9519653" // example, adjust to your NDK
}
```
3. Add required permissions in `AndroidManifest.xml`:
```xml
<uses-permission android:name="android.permission.CAMERA"/>
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
<uses-feature android:name="android.hardware.camera" android:required="true"/>
```
4. Configure `CMakeLists.txt` to find OpenCV — either by unpacking OpenCV Android SDK into the project and pointing `OpenCV_DIR` to `sdk/native/jni`.

### B. OpenCV + JNI
1. Download OpenCV Android SDK from https://opencv.org/releases/.
2. Unzip into `third_party/opencv-android` in your project root (or anywhere and update paths).
3. In `jni/CMakeLists.txt`, update `OpenCV_DIR` to `../../third_party/opencv-android/sdk/native/jni` or the path in your machine.
4. `native-lib.cpp` contains a JNI function `Java_com_flam_rnd_MainActivity_processFrame` that accepts a byte array frame and returns processed RGBA bytes. It uses OpenCV C++ API.

### C. Camera -> Frame flow
1. Capture frames in YUV_420_888 format using Camera2 and convert to RGBA bytes on Java side (or pass YUV to native and convert there).
2. Call `processFrame` JNI and get back processed RGBA (or an OpenGL texture handle if you implement that).
3. Pass RGBA to `GLRenderer` which uploads it to a GL texture using `glTexImage2D` and draws a textured quad.

### D. Web Viewer (TypeScript)
1. Enter `web` directory.
2. `npm install` (this scaffold uses no external deps).
3. `npm run build` to compile TypeScript to JS (or use `tsc`).
4. Open `index.html` in a browser — replace the `sample_processed.png` with a screenshot exported from Android.

---

## Files included (key snippets)
- `MainActivity.kt` — Camera2 setup, TextureView listener, JNI calls.
- `native-lib.cpp` — example Canny edge detector using OpenCV.
- `CMakeLists.txt` — builds native lib and links OpenCV.
- `GLRenderer.kt` — uploads and renders RGBA pixel buffer as GL texture.
- `web/index.html` + `web/src/main.ts` — TypeScript front-end.

---

## Recommended development plan (3 days)
**Day 1**
- Setup Android project + NDK + OpenCV.
- Implement Camera2 capture and log frames.

**Day 2**
- Implement JNI & native C++ OpenCV processing.
- Test by invoking native function with a static image first.

**Day 3**
- Implement OpenGL renderer and wire processed frames to GL texture.
- Add toggle between raw and processed.
- Capture screenshot for web viewer and finalize README, GIFs, and commit history.

---

## How to export a processed image for the web viewer
- Inside your app, write processed RGBA bytes to a bitmap and save to external storage as PNG. Transfer to your PC and place in `web/` as `sample_processed.png`.

---

## Git commit suggestions
- Make small atomic commits:
  - `init: project scaffold`
  - `feat(camera): Camera2 capture implementation`
  - `feat(native): JNI bridge + C++ processFrame stub`
  - `feat(opencv): Canny edge detection implementation`
  - `feat(gl): texture upload and rendering`
  - `feat(web): typescript viewer and sample image`
  - `docs: README and setup instructions`

---

## Notes & gotchas
- OpenCV Android gives prebuilt `.so` files per ABI — make sure you include them or build OpenCV from source.
- Camera2 YUV -> RGBA conversion can be done in Java or native; doing it native reduces Java overhead.
- Real-time performance: avoid copying memory excessively; use direct ByteBuffers and `glTexSubImage2D` for updates.

---

If you want, I can also:
- Generate a full zip with these scaffold files (Kotlin, C++, CMakeLists, TypeScript) — ready to import and expand in Android Studio.
- Or produce a super-detailed `MainActivity.kt` + `native-lib.cpp` implementation you can paste in to speed up development.

Which would you prefer? (I'll create the zip scaffold now if you say "create zip")

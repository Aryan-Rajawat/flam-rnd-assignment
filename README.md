# Flam R&D Assignment - Real Time Edge Detection

Hi, I’m Aryan Rajawat
This is my submission for the Flam Software Engineering Intern (R&D) assignment.

The task was to make a small Android project that can do real-time edge detection using OpenCV and NDK, and also create a small web viewer using TypeScript.

---

## About the Project

In this project, the Android app uses the camera to capture live frames, sends them to native C++ code using JNI, and applies edge detection using OpenCV.  
Then it renders the processed frames using OpenGL ES 2.0.

The web part is a simple viewer built with TypeScript that displays a processed image and some basic frame details.

---

## Project Structure

app → Android app (Java/Kotlin)
jni → Native C++ code (OpenCV + JNI)
web → TypeScript web viewer

---

## Android Features

- Uses Camera2 API to capture frames
- Sends frames to native C++ through JNI
- Applies Canny Edge Detection using OpenCV
- Renders frames with OpenGL ES
- Has a button to save a sample processed image (PNG)

---

## Web Features

- Simple HTML + TypeScript web viewer
- Shows a sample processed image (`sample_processed.png`)
- Displays resolution and FPS (static data for now)

---

## How to Set Up

### Android Part

1. Install Android Studio and make sure NDK + CMake are installed.
2. Download OpenCV Android SDK from: [https://opencv.org/releases/](https://opencv.org/releases/)
3. Extract it in your project under this path:
   third_party/opencv-android/
4. Open `app/jni/CMakeLists.txt` and make sure the path is correct:

```cmake
set(OpenCV_DIR ${CMAKE_SOURCE_DIR}/../third_party/opencv-android/sdk/native/jni)
Open the project in Android Studio.

Connect your Android phone → click Run → allow camera permission.

You’ll see the live edge detection working.

Web Part

Open a terminal inside the web folder.

Run:

npm install
npm run build


Open index.html in any browser to see the sample image.

Simple Architecture
Camera2 (Android)
   ↓
JNI (Java ↔ C++)
   ↓
OpenCV C++ (Edge Detection)
   ↓
OpenGL Renderer (Display)


Android handles the camera, C++ does the edge detection, and OpenGL shows the output.
The web viewer is a simple static page that shows the result.

Tools & Tech Used

Kotlin / Java (Android)

C++ / JNI / OpenCV

OpenGL ES 2.0

TypeScript / HTML

Android Studio / VS Code

Screenshots (to be added later)

I’ll add screenshots here once I record the Android output and web viewer.



About Me :

Name: Aryan Rajawat
Role: Student / Developer
GitHub: @Aryan-Rajawat

Email: (2k22.cscys.2212230@gmail.com)

Thanks for reading!
```

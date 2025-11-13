
cmake_minimum_required(VERSION 3.4.1)
project("flam_rnd")

# Set path to OpenCV dir if present.
# Example: set(OpenCV_DIR ${CMAKE_SOURCE_DIR}/../third_party/opencv-android/sdk/native/jni)
# find_package(OpenCV REQUIRED)

add_library( native-lib
             SHARED
             native-lib.cpp )

# include_directories(${OpenCV_INCLUDE_DIRS})
# target_link_libraries( native-lib ${OpenCV_LIBS} )

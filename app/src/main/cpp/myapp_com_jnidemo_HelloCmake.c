#include "myapp_com_jnidemo_HelloCmake.h"


JNIEXPORT jstring JNICALL Java_myapp_com_jnidemo_HelloCmake_getString(JNIEnv *env, jobject thiz){
    return (*env)-> NewStringUTF(env,"Java_myapp_com_jnidemo_HelloCmake_getString");
};


JNIEXPORT jstring JNICALL Java_myapp_com_jnidemo_HelloCmake_getStringWithParam(JNIEnv *env, jobject thiz, jstring str){
    return (*env)-> NewStringUTF(env,"Java_myapp_com_jnidemo_HelloCmake_getString");
};


JNIEXPORT jstring JNICALL Java_myapp_com_jnidemo_HelloCmake_getStringFromNDK(JNIEnv *env, jclass clazz){
    return (*env)-> NewStringUTF(env,"Java_myapp_com_jnidemo_HelloCmake_getStringFromNDK");
};

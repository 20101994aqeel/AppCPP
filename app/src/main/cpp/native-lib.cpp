#include <jni.h>
#include <string>

jstring spname(JNIEnv *env) {
    return env->NewStringUTF("Myshared");
}

jstring objectname(JNIEnv *env) {
    return env->NewStringUTF("login");
}

jobject mainClass;

extern "C" JNIEXPORT jstring JNICALL
Java_com_qboxus_appcpp_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}


extern "C"
JNIEXPORT void JNICALL
Java_com_qboxus_appcpp_MainActivity_setStoreBoolValues(JNIEnv *env,
                                                       jobject instance,
                                                       jobject activity,
                                                       jboolean flag) {
    jclass spcls = env->FindClass("android/content/SharedPreferences");
    jclass speditorcls = env->FindClass("android/content/SharedPreferences$Editor");
    jclass contextcls = env->FindClass("android/content/Context");

    mainClass = env->NewGlobalRef(activity);

    jmethodID mid = env->GetMethodID(contextcls, "getSharedPreferences",
                                     "(Ljava/lang/String;I)Landroid/content/SharedPreferences;");

    jmethodID midedit = env->GetMethodID(spcls, "edit",
                                         "()Landroid/content/SharedPreferences$Editor;");

    jmethodID midputbool = env->GetMethodID(speditorcls, "putBoolean",
                                            "(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;");

    jmethodID midapply = env->GetMethodID(speditorcls, "apply",
                                          "()V");
    jobject jobjectshared = env->CallObjectMethod(mainClass, mid,spname(env), 0);
    jobject jobjectsharededit = env->CallObjectMethod(jobjectshared, midedit);
    env->CallVoidMethod(env->CallObjectMethod(jobjectsharededit, midputbool, objectname(env), flag),
                        midapply);
    env->DeleteLocalRef(spcls);
    env->DeleteLocalRef(contextcls);
    env->DeleteLocalRef(speditorcls);
}
#include <jni.h>
#include <string>
#include <android/log.h>

extern "C" {

#include <libavcodec/avcodec.h>
#include <libavformat/avformat.h>
#include <libavfilter/avfilter.h>
#include "ffmpeg.h"

#define LOG_TAG "FFmpegBox"
#define LOGI(...)  __android_log_print(ANDROID_LOG_INFO,LOG_TAG,__VA_ARGS__)
#define LOGW(...)  __android_log_print(ANDROID_LOG_WARN,LOG_TAG,__VA_ARGS__)
#define LOGE(...)  __android_log_print(ANDROID_LOG_ERROR,LOG_TAG,__VA_ARGS__)
#define LOGD(...)  __android_log_print(ANDROID_LOG_DEBUG,LOG_TAG,__VA_ARGS__)
#define LOGF(...)  __android_log_print(ANDROID_LOG_FATAL,LOG_TAG,__VA_ARGS__)
#define LOGV(...)  __android_log_print(ANDROID_LOG_VERBOSE,LOG_TAG,__VA_ARGS__)


JNIEXPORT jstring JNICALL
Java_cn_gavinliu_android_ffmpeg_box_FFmpegBox_avFormatInfo(
        JNIEnv *env,
        jobject /* this */) {
    char info[40000] = {0};
    av_register_all();
    AVInputFormat *if_temp = av_iformat_next(NULL);
    AVOutputFormat *of_temp = av_oformat_next(NULL);
    while (if_temp != NULL) {
        sprintf(info, "%sInput: %s\n", info, if_temp->name);
        if_temp = if_temp->next;
    }
    while (of_temp != NULL) {
        sprintf(info, "%sOutput: %s\n", info, of_temp->name);
        of_temp = of_temp->next;
    }
    return env->NewStringUTF(info);
}

JNIEXPORT jstring JNICALL
Java_cn_gavinliu_android_ffmpeg_box_FFmpegBox_urlProtocolInfo(
        JNIEnv *env,
        jobject /* this */) {
    char info[40000] = {0};
    av_register_all();
    struct URLProtocol *pup = NULL;
    struct URLProtocol **p_temp = &pup;
    avio_enum_protocols((void **) p_temp, 0);
    while ((*p_temp) != NULL) {
        sprintf(info, "%sInput: %s\n", info, avio_enum_protocols((void **) p_temp, 0));
    }
    pup = NULL;
    avio_enum_protocols((void **) p_temp, 1);
    while ((*p_temp) != NULL) {
        sprintf(info, "%sInput: %s\n", info, avio_enum_protocols((void **) p_temp, 1));
    }
    return env->NewStringUTF(info);
}

JNIEXPORT jstring JNICALL
Java_cn_gavinliu_android_ffmpeg_box_FFmpegBox_avCodecInfo(
        JNIEnv *env,
        jobject /* this */) {
    char info[40000] = {0};
    av_register_all();
    AVCodec *c_temp = av_codec_next(NULL);
    while (c_temp != NULL) {
        if (c_temp->decode != NULL) {
            sprintf(info, "%sdecode:", info);
        } else {
            sprintf(info, "%sencode:", info);
        }
        switch (c_temp->type) {
            case AVMEDIA_TYPE_VIDEO:
                sprintf(info, "%s(video):", info);
                break;
            case AVMEDIA_TYPE_AUDIO:
                sprintf(info, "%s(audio):", info);
                break;
            default:
                sprintf(info, "%s(other):", info);
                break;
        }
        sprintf(info, "%s[%10s]\n", info, c_temp->name);
        c_temp = c_temp->next;
    }
    return env->NewStringUTF(info);
}

JNIEXPORT jstring JNICALL
Java_cn_gavinliu_android_ffmpeg_box_FFmpegBox_avFilterInfo(JNIEnv *env, jobject /* this */) {
    char info[40000] = {0};
    avfilter_register_all();
    AVFilter *f_temp = (AVFilter *) avfilter_next(NULL);
    while (f_temp != NULL) {
        sprintf(info, "%s%s\n", info, f_temp->name);
        f_temp = f_temp->next;
    }
    return env->NewStringUTF(info);
}

void my_logcat(void *ptr, int level, const char *fmt, va_list vl) {
    va_list vl2;
    char line[1024];
    static int print_prefix = 1;

    va_copy(vl2, vl);
    av_log_format_line(ptr, level, fmt, vl2, line, sizeof(line), &print_prefix);
    va_end(vl2);


    switch (level) {
        case AV_LOG_VERBOSE:
            LOGV("%s", line);
            break;

        case AV_LOG_INFO:
            LOGI("%s", line);
            break;

        case AV_LOG_DEBUG:
            LOGD("%s", line);
            break;

        case AV_LOG_FATAL:
            LOGF("%s", line);
            break;

        case AV_LOG_WARNING:
            LOGW("%s", line);
            break;

        case AV_LOG_TRACE:
        case AV_LOG_ERROR:
        default:
            LOGE("%s", line);
    }
}

JNIEXPORT jint JNICALL
Java_cn_gavinliu_android_ffmpeg_box_FFmpegBox_execute(
        JNIEnv *env,
        jobject /* this */, jobjectArray commands) {

    av_log_set_callback(my_logcat);

    int argc = env->GetArrayLength(commands);
    char *argv[argc];
    int i;
    for (i = 0; i < argc; i++) {
        jstring js = (jstring) env->GetObjectArrayElement(commands, i);
        argv[i] = (char *) env->GetStringUTFChars(js, 0);
    }
    return execute(argc, argv);
}

}

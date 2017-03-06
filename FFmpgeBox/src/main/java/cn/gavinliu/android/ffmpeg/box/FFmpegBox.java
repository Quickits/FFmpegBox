package cn.gavinliu.android.ffmpeg.box;

/**
 * Created by Gavin on 17-3-6.
 */

public class FFmpegBox {

    static {
        System.loadLibrary("ffmpeg_box");
        System.loadLibrary("ffmpeg");
    }

    public native String stringFromJNI();

    public native String avformatinfo();

    public native String urlprotocolinfo();

    public native String avcodecinfo();

    public native String avfilterinfo();

    public native int run(String[] commonds);

}

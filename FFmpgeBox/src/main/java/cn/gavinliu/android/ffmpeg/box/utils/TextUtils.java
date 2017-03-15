package cn.gavinliu.android.ffmpeg.box.utils;

import java.util.Locale;

/**
 * Created by gavin on 2017/3/15.
 */

public class TextUtils {

    public static String cmdFormat(String cmd, Object... args) {
        return String.format(Locale.getDefault(), cmd, args);
    }
}

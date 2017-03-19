package cn.gavinliu.android.ffmpeg.box;

import android.text.TextUtils;

import cn.gavinliu.android.ffmpeg.box.commands.Command;

/**
 * FFmpeg Tool box
 * <p>
 * Created by Gavin on 17-3-6.
 */

public class FFmpegBox {

    private static FFmpegBox sInstance;

    public static FFmpegBox getInstance() {
        if (sInstance == null) sInstance = new FFmpegBox();
        return sInstance;
    }

    private FFmpegBox() {
    }

    public void execute(Command command) {
        if (command == null || TextUtils.isEmpty(command.getCommand())) return;

        String[] commands = command.getCommand().split("\\s");
        execute(commands);
    }

    public native String urlProtocolInfo();

    public native String avCodecInfo();

    public native String avFilterInfo();

    public native String avFormatInfo();

    private native int execute(String[] commonds);

    static {
        System.loadLibrary("ffmpeg_box");
        System.loadLibrary("ffmpeg");
    }

}

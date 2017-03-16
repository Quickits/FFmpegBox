package cn.gavinliu.android.ffmpeg.box.utils;

/**
 * Created by Gavin on 17-3-16.
 */

public class Constant {

    public enum Format {

        _3GP("3gp"),
        MP4("mp4"),
        AVI("avi"),
        MKV("matroska");

        String name;

        Format(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public enum Separator {
        Video, Audio
    }

}

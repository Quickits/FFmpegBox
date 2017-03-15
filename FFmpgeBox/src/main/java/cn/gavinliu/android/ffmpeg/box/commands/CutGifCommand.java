package cn.gavinliu.android.ffmpeg.box.commands;

import static cn.gavinliu.android.ffmpeg.box.utils.TextUtils.cmdFormat;


/**
 * Created by gavin on 2017/3/15.
 */

public class CutGifCommand extends BaseCommand {

    private static final String CMD = "ffmpeg -ss %d -t %d -i %s -s %d*%d -f gif %s";

    private CutGifCommand(String command) {
        super(command);
    }

    public static class Builder {

        String videoFile;

        String gifFile;

        int width;

        int height;

        int startTime;

        int duration;

        public Builder setVideoFile(String videoFile) {
            this.videoFile = videoFile;
            return this;
        }

        public Builder setGifFile(String gifFile) {
            this.gifFile = gifFile;
            return this;
        }

        public Builder setWidth(int width) {
            this.width = width;
            return this;
        }

        public Builder setHeight(int height) {
            this.height = height;
            return this;
        }

        public Builder setStartTime(int startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder setDuration(int duration) {
            this.duration = duration;
            return this;
        }

        public Command build() {
            String cmd = cmdFormat(CMD, startTime, duration, videoFile, width, height, gifFile);
            return new CutGifCommand(cmd);
        }

    }

}

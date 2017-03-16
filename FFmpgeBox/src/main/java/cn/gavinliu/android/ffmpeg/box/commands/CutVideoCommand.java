package cn.gavinliu.android.ffmpeg.box.commands;

import cn.gavinliu.android.ffmpeg.box.utils.Constant;

import static cn.gavinliu.android.ffmpeg.box.utils.TextUtils.cmdFormat;

/**
 * Created by gavin on 2017/3/16.
 */

public class CutVideoCommand extends BaseCommand {

    private static final String CMD = "ffmpeg -ss %d -t %d -i %s -c copy -f %s %s";

    private CutVideoCommand(String command) {
        super(command);
    }

    public static class Builder implements IBuilder {

        int startTime;

        int duration;

        String inputFile;

        String outputFile;

        Constant.Format format;

        public Builder setStartTime(int startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder setDuration(int duration) {
            this.duration = duration;
            return this;
        }

        public Builder setInputFile(String inputFile) {
            this.inputFile = inputFile;
            return this;
        }

        public Builder setOutputFile(String outputFile) {
            this.outputFile = outputFile;
            return this;
        }

        public Builder setFormat(Constant.Format format) {
            this.format = format;
            return this;
        }

        @Override
        public Command build() {
            String cmd = cmdFormat(CMD, startTime, duration, inputFile, format.getName(), outputFile);
            return new CutVideoCommand(cmd);
        }
    }
}

package cn.gavinliu.android.ffmpeg.box.commands;

import cn.gavinliu.android.ffmpeg.box.utils.Constant;

import static cn.gavinliu.android.ffmpeg.box.utils.TextUtils.cmdFormat;

/**
 * Created by gavin on 2017/3/16.
 */

public class SeparatorCommand extends BaseCommand {

    private static final String CMD = "ffmpeg -i %s %s";

    private SeparatorCommand(String command) {
        super(command);
    }

    public static class Builder implements IBuilder {

        String videoFile;
        String outputFile;

        Constant.Separator separator;

        public Builder setVideoFile(String videoFile) {
            this.videoFile = videoFile;
            return this;
        }

        public Builder setOutputFile(String outputFile) {
            this.outputFile = outputFile;
            return this;
        }

        public Builder setSeparator(Constant.Separator separator) {
            this.separator = separator;
            return this;
        }

        @Override
        public Command build() {
            String cmd = "";

            switch (separator) {
                case Video:
                    cmd = cmdFormat(CMD, videoFile, outputFile);
                    break;

                case Audio:
                    cmd = cmdFormat(CMD, videoFile, outputFile);
                    break;
            }

            return new SeparatorCommand(cmd);
        }
    }

}

package cn.gavinliu.android.ffmpeg.box.commands;

import cn.gavinliu.android.ffmpeg.box.utils.Constant;

import static cn.gavinliu.android.ffmpeg.box.utils.TextUtils.cmdFormat;

/**
 * Created by Gavin on 17-3-16.
 */

public class FormatConvertCommand extends BaseCommand {

    private static final String CMD = "ffmpeg -i %s -c copy -f %s %s";

    private FormatConvertCommand(String command) {
        super(command);
    }

    public static class Builder implements IBuilder {
        String inputFile;

        String outputFile;

        Constant.Format format;

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
            String cmd = cmdFormat(CMD, inputFile, format.getName(), outputFile);
            return new FormatConvertCommand(cmd);
        }
    }

}

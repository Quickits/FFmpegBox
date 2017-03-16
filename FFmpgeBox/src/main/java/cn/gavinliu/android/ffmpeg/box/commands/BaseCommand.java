package cn.gavinliu.android.ffmpeg.box.commands;

/**
 * Created by gavin on 2017/3/15.
 */

public abstract class BaseCommand implements Command {

    private String command;

    public BaseCommand(String command) {
        this.command = command;
    }

    @Override
    public String getCommand() {
        return command;
    }

    public interface IBuilder {

        Command build();

    }
}

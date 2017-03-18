package cn.gavinliu.android.ffmpeg.box.app.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import cn.gavinliu.android.ffmpeg.box.app.ui.list.VideoListFragment;
import cn.gavinliu.android.ffmpeg.box.app.util.PermissionsUtils;
import cn.gavinliu.android.ffmpeg.box.app.widget.BaseContainerActivity;
import cn.gavinliu.android.ffmpeg.box.commands.Command;

public class MainActivity extends BaseContainerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PermissionsUtils.getReadStoragePermissions(this);
        PermissionsUtils.getWriteStoragePermissions(this);
    }

    @Override
    public Fragment getFragment() {
        return VideoListFragment.newInstance();
    }


    public void cmd(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                long time = System.currentTimeMillis();

                Command command;

//                command = new CutGifCommand.Builder()
//                        .setVideoFile("/sdcard/明明就.mp4")
//                        .setGifFile("/sdcard/明明就.gif")
//                        .setStartTime(90)
//                        .setDuration(20)
//                        .setWidth(480)
//                        .setHeight(270)
//                        .build();

//                command = new ScreenshotCommand.Builder()
//                        .setVideoFile("/sdcard/明明就.mp4")
//                        .setImageFile("/sdcard/明明就.jpg")
//                        .setTime(30)
//                        .build();

//                command = new FormatConvertCommand.Builder()
//                        .setInputFile("/sdcard/明明就.mp4")
//                        .setOutputFile("/sdcard/明明就.mkv")
//                        .setFormat(Constant.Format.MKV)
//                        .build();

//                command = new SeparatorCommand.Builder()
//                        .setVideoFile("/sdcard/明明就.mp4")
//                        .setOutputFile("/sdcard/明明就.aac")
//                        .setSeparator(Constant.Separator.Audio)
//                        .build();

//                command = new CutVideoCommand.Builder()
//                        .setInputFile("/sdcard/明明就.mp4")
//                        .setOutputFile("/sdcard/明明就2.mp4")
//                        .setFormat(Constant.Format.MP4)
//                        .setStartTime(46)
//                        .setDuration(62)
//                        .build();

            }
        }).start();
    }

}

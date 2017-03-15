package cn.gavinliu.android.ffmpeg.box.app;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import cn.gavinliu.android.ffmpeg.box.FFmpegBox;
import cn.gavinliu.android.ffmpeg.box.app.utils.PermissionsUtils;
import cn.gavinliu.android.ffmpeg.box.commands.CutGifCommand;
import cn.gavinliu.android.ffmpeg.box.commands.Command;

public class MainActivity extends AppCompatActivity {

    private TextView tv;

    private Handler handler;

    private FFmpegBox ffmpegBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ffmpegBox = new FFmpegBox();
        handler = new Handler();

        tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(ffmpegBox.urlProtocolInfo());

        PermissionsUtils.getReadStoragePermissions(this);
        PermissionsUtils.getWriteStoragePermissions(this);
    }

    public void protocol(View view) {
        tv.setText(ffmpegBox.urlProtocolInfo());
    }

    public void filter(View view) {
        tv.setText(ffmpegBox.avFilterInfo());
    }

    public void format(View view) {
        tv.setText(ffmpegBox.avFormatInfo());
    }

    public void codec(View view) {
        tv.setText(ffmpegBox.avCodecInfo());
    }

    public void cmd(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                long time = System.currentTimeMillis();

                Command command = new CutGifCommand.Builder()
                        .setVideoFile("/sdcard/明明就.mp4")
                        .setGifFile("/sdcard/明明就.gif")
                        .setStartTime(90)
                        .setDuration(20)
                        .setWidth(480)
                        .setHeight(270)
                        .build();

                ffmpegBox.execute(command);

                final String msg = "耗时：" + (System.currentTimeMillis() - time);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();
    }

}

package cn.gavinliu.android.ffmpeg.box.app;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import cn.gavinliu.android.ffmpeg.box.FFmpegBox;

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
        tv.setText(ffmpegBox.stringFromJNI());
    }

    public void protocol(View view) {
        tv.setText(ffmpegBox.urlprotocolinfo());
    }

    public void filter(View view) {
        tv.setText(ffmpegBox.avformatinfo());
    }

    public void format(View view) {
        tv.setText(ffmpegBox.avfilterinfo());
    }

    public void codec(View view) {
        tv.setText(ffmpegBox.avcodecinfo());
    }

    public void cmd(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                long time = System.currentTimeMillis();

                String cmd = "ffmpeg -ss 40 -t 10 -i /sdcard/不该.mp4 -s 480*270 -f gif /sdcard/不该.gif";
                String[] commands = cmd.split(" ");

                ffmpegBox.run(commands);

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

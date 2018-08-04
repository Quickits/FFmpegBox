package cn.gavinliu.ffmpegbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cn.quickits.ffmpeg.box.FFmpegBox
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        load.setOnClickListener {
            FFmpegBox.get().loadBinary()
        }

        execute.setOnClickListener {
            FFmpegBox.get().execute()
        }
    }
}

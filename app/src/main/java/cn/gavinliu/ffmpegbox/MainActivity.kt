package cn.gavinliu.ffmpegbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import cn.quickits.ffmpeg.box.FFmpegBox
import cn.quickits.ffmpeg.box.data.Success
import cn.quickits.ffmpeg.box.util.FileUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        execute.setOnClickListener {
            FFmpegBox.get().version()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { version ->
                        msg.text = version
                    }

//            FFmpegBox.get().exec(arrayOf(FileUtils.getFFmpegFilePath(),
//                    "-i", "/sdcard/screenshots.mp4",
//                    "-vcodec", "copy",
//                    "/sdcard/screenshots.avi"
//            )).subscribe { status ->
//                Log.d("MainActivity", "${status.msg} ${status is Success}")
//            }

        }
    }
}

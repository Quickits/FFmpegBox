package cn.gavinliu.ffmpegbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cn.quickits.ffmpeg.box.FFmpegBox
import cn.quickits.ffmpeg.box.util.FileUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        version.setOnClickListener {
            FFmpegBox.get().version()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { version ->
                        msg.text = version
                    }
        }

        converter.setOnClickListener {
            FFmpegBox.get().exec(arrayOf(
                    "-i", "/sdcard/screenshots.mp4",
                    "-vcodec", "copy",
                    "/sdcard/screenshots.avi"
            )).observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ status ->
                        msg.append(status.msg)
                        msg.append("\n")
                    }, { error -> error.printStackTrace() })
        }
    }
}

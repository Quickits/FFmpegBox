package cn.gavinliu.ffmpegbox

import android.app.Application
import cn.quickits.ffmpeg.box.FFmpegBox

class FFmpegBoxApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        FFmpegBox.init(this)
    }

}
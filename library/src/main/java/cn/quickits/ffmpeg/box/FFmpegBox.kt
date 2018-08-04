package cn.quickits.ffmpeg.box

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import cn.quickits.ffmpeg.box.data.FFmpegBoxSpec
import cn.quickits.ffmpeg.box.util.FileUtils
import cn.quickits.ffmpeg.box.util.ProcessUtils
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

class FFmpegBox {

    fun loadBinary() {
        FFmpegBinaryLoader().execute()
    }

    @SuppressLint("CheckResult")
    fun execute() {
//        val str = arrayOf(
//                FileUtils.getFFmpegFilePath(),
//                "-i",
//                "/sdcard/screenshots.mp4",
//                "-vcodec",
//                "libx264",
//                "/sdcard/screenshots-out.mp4"
//        )

        val str = arrayOf(
                FileUtils.getFFmpegFilePath(),
                "-version"
        )

        Flowable.just(str)
                .map { Runtime.getRuntime().exec(it) }
                .map {
                    ProcessUtils.checkAndUpdate(it)
                    val text = ProcessUtils.outputText(it)
                    Log.d("FFmpegBox", text)
                    true
                }
                .subscribeOn(Schedulers.io())
                .subscribe({
                    Log.d("FFmpegBox", "$it")
                }, {
                    it.printStackTrace()
                })
    }

    companion object {

        fun init(context: Context) {
            FFmpegBoxSpec.createInstance(context)
        }

        @Volatile
        private var box: FFmpegBox? = null

        fun get(): FFmpegBox {
            if (box == null) {
                synchronized(FFmpegBox::class) {
                    box = FFmpegBox()
                }
            }

            return box as FFmpegBox
        }

    }
}
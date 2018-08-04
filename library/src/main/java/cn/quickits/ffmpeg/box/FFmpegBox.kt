package cn.quickits.ffmpeg.box

import android.content.Context
import cn.quickits.ffmpeg.box.data.FFmpegBoxSpec
import cn.quickits.ffmpeg.box.data.Status
import cn.quickits.ffmpeg.box.data.Success
import cn.quickits.ffmpeg.box.util.FileUtils
import io.reactivex.Flowable


class FFmpegBox {

    fun loadBinary() {
        FFmpegBinaryLoader().execute()
    }

    fun exec(cmd: Array<String>): Flowable<Status> = FFmpegBoxSpec.instance().commanderBox.exec(cmd)

    fun version(): Flowable<String> {
        val cmd = arrayOf(FileUtils.getFFmpegFilePath(), "-version")
        return exec(cmd).filter { it is Success }.map { it.msg }
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
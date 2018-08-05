package cn.quickits.ffmpeg.box

import android.content.Context
import cn.quickits.ffmpeg.box.data.FFmpegBoxSpec
import cn.quickits.ffmpeg.box.data.Status
import cn.quickits.ffmpeg.box.data.Success
import cn.quickits.ffmpeg.box.util.FileUtils
import io.reactivex.Flowable


class FFmpegBox {

    fun exec(cmd: Array<String>): Flowable<Status> {
        var newCmd = arrayOf(FileUtils.getFFmpegFilePath())
        newCmd = newCmd.plus(cmd)
        return FFmpegBoxSpec.instance().commanderBox.exec(newCmd)
    }

    fun version(): Flowable<String> {
        return exec(arrayOf("-version")).filter { it is Success }.map { it.msg }
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
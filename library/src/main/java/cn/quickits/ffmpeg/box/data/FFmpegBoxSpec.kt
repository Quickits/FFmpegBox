package cn.quickits.ffmpeg.box.data

import android.annotation.SuppressLint
import android.content.Context

@SuppressLint("StaticFieldLeak")
class FFmpegBoxSpec(var context: Context) {

    var commanderBox: CommanderBox = CommanderBox()

    companion object {

        @Volatile
        private var INSTANCE: FFmpegBoxSpec? = null

        fun instance(): FFmpegBoxSpec = INSTANCE as FFmpegBoxSpec

        fun createInstance(context: Context): FFmpegBoxSpec {
            if (INSTANCE == null) {
                synchronized(FFmpegBoxSpec::class) {
                    INSTANCE = FFmpegBoxSpec(context.applicationContext)
                }
            }
            return INSTANCE as FFmpegBoxSpec
        }

    }
}
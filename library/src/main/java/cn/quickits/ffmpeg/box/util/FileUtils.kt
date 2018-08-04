package cn.quickits.ffmpeg.box.util

import cn.quickits.ffmpeg.box.data.FFmpegBoxSpec
import java.io.File
import java.io.FileOutputStream


object FileUtils {

    private const val ffmpegFileName = "ffmpeg"

    fun getFFmpegFile() = File(FFmpegBoxSpec.instance().context.filesDir, ffmpegFileName)

    fun getFFmpegFilePath() = getFFmpegFile().absolutePath

    fun copyBinaryFromAssetsToData(): Boolean {
        try {
            val inputStream = FFmpegBoxSpec.instance().context.assets.open("armeabi-v7a/ffmpeg")
            val outputStream = FileOutputStream(getFFmpegFile())

            val buffer = ByteArray(DEFAULT_BUFFER_SIZE)

            var n: Int = inputStream.read(buffer)
            while (n != -1) {
                outputStream.write(buffer, 0, n)
                n = inputStream.read(buffer)
            }

            outputStream.close()
            inputStream.close()

            return true

        } catch (e: Exception) {
            e.printStackTrace()
        }

        return false
    }

}
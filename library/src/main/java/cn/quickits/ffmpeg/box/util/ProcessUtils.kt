package cn.quickits.ffmpeg.box.util

import android.util.Log
import java.io.*


object ProcessUtils {

    fun checkAndUpdate(process: Process?) {
        process ?: return

        while (!isCompleted(process)) {

            if (isCompleted(process)) {
                return
            }

            try {
                val reader = BufferedReader(InputStreamReader(process.errorStream) as Reader?)
                var line = reader.readLine()
                while (line != null) {
                    Log.e("FFmpegBox", line)
                    line = reader.readLine()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    fun outputText(process: Process?): String? {
        process ?: return null

        return if (isSuccess(process)) {
            convertInputStreamToString(process.inputStream)
        } else {
            convertInputStreamToString(process.errorStream)
        }
    }

    private fun convertInputStreamToString(inputStream: InputStream): String? {
        try {
            val r = BufferedReader(InputStreamReader(inputStream))
            val sb = StringBuilder()
            var str: String? = r.readLine()
            while (str != null) {
                sb.append(str)
                sb.append("\n")
                str = r.readLine()
            }
            return sb.toString()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return null
    }

    private fun isCompleted(process: Process?): Boolean {
        process ?: return false
        try {
            process.exitValue()
            return true
        } catch (e: Exception) { }
        return false
    }

    private fun isSuccess(process: Process?): Boolean {
        process ?: return false
        val exitValue = process.exitValue()
        return exitValue == 0
    }

}
package cn.quickits.ffmpeg.box

import android.os.AsyncTask
import android.util.Log
import cn.quickits.ffmpeg.box.util.FileUtils

class FFmpegBinaryLoader : AsyncTask<Void, Void, Boolean>() {

    override fun doInBackground(vararg params: Void?): Boolean {
        val ffmpegFile = FileUtils.getFFmpegFile()

        if (!ffmpegFile.exists()) {
            if (FileUtils.copyBinaryFromAssetsToData()) {
                if (ffmpegFile.canExecute() || ffmpegFile.setExecutable(true)) {
                    return true
                }
            }
        }

        return ffmpegFile.exists() && ffmpegFile.canExecute()
    }

    override fun onPostExecute(result: Boolean?) {
        super.onPostExecute(result)
        Log.d("FFmpegBinaryLoader", "$result")
    }

}
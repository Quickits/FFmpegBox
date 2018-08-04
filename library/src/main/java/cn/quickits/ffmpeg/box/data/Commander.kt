package cn.quickits.ffmpeg.box.data

import cn.quickits.ffmpeg.box.util.ProcessUtils
import io.reactivex.Flowable
import io.reactivex.disposables.Disposable
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.schedulers.Schedulers
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.Reader

class Commander(val cmd: Array<String>) {

    private val processor = BehaviorProcessor.create<Status>().toSerialized()

    private var disposable: Disposable? = null

    fun getFlowable(): Flowable<Status> = processor

    fun exec() {
        if (disposable == null) {
            disposable = Flowable.just(cmd)
                    .map { Runtime.getRuntime().exec(it) }
                    .map { process ->
                        checkAndUpdate(process)
                        val outputText = ProcessUtils.outputText(process) ?: ""
                        Success(outputText)
                    }
                    .subscribeOn(Schedulers.newThread())
                    .doOnError {

                    }
                    .doOnCancel {

                    }
                    .doOnComplete {

                    }
                    .doFinally {
                        
                    }
                    .subscribe({ emitStatus(it) }, {
                        emitStatus(Failed(it.message ?: ""))
                    })
        }
    }

    private fun emitStatus(status: Status) {
        processor.onNext(status)
    }

    private fun checkAndUpdate(process: Process?) {
        process ?: return

        while (!ProcessUtils.isCompleted(process)) {

            if (ProcessUtils.isCompleted(process)) {
                return
            }

            try {
                val reader = BufferedReader(InputStreamReader(process.errorStream) as Reader?)
                var line = reader.readLine()
                while (line != null) {
                    emitStatus(Executing(line))
                    line = reader.readLine()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}

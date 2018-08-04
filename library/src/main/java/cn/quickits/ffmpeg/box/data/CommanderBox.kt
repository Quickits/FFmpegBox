package cn.quickits.ffmpeg.box.data

import io.reactivex.Flowable


class CommanderBox {

    private val box = mutableListOf<Commander>()

    fun exec(cmd: Array<String>): Flowable<Status> {
        val commander = Commander(cmd)

        box.add(commander)

        commander.exec()

        return commander.getFlowable()
    }

}
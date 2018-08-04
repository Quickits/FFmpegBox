package cn.quickits.ffmpeg.box.data

open class Status(val msg: String) {

}

class Executing(msg: String) : Status(msg)

class Success(msg: String) : Status(msg)

class Failed(msg: String) : Status(msg)

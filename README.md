# FFmpegBox

安卓平台的 ``FFmpeg`` 命令行工具箱，基于 ``FFmpeg 3.2.4`` 。

## App


----------


## 开发库

### 引用

由于 FFmpeg 库比较大，放在中心仓库里不太合适，所以还是下载源码引用吧。

1. 拷贝 FFmpegBox 到你的项目根目录，
2. 在 ``setting.gradle`` 添加 ``':FFmpegBox'``，
3. 在 ``build.gradle`` 添加 ``compile project(':FFmpegBox')``

### Command.Builder API

使用 ``Command.Builder`` 链式API生成一个 ``Command``

```java
Command command = new CutGifCommand.Builder()
    .setVideoFile("/sdcard/明明就.mp4")
    .setGifFile("/sdcard/明明就.gif")
    .setStartTime(45)
    .setDuration(10)
    .setWidth(480)
    .setHeight(270)
    .build();

new FFmpegBox().execute(command);
```

### 自定义 Command

```java
public interface Command {

    String getCommand();

}
```

实现 ``Command`` 接口，``getCommand()`` 方法返回 ``ffmpeg -i inputFile ...`` 类似的命令行字符串即可。

为了编码规范，你应该使用 ``Builder`` 模式，采用链式API的方式创建一个 ``Command``，可参考：[CutGifCommand](https://github.com/gavinliu/FFmpegBox/blob/master/FFmpgeBox/src/main/java/cn/gavinliu/android/ffmpeg/box/commands/CutGifCommand.java)。

### 支持的命令

* CutGifCommand
* CutVideoCommand
* ScreenshotCommand
* FormatConvertCommand
* SeparatorCommand
* ...
* coming soon

### License

MIT

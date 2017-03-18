package cn.gavinliu.android.ffmpeg.box.app;

import android.app.Application;

import cn.gavinliu.android.ffmpeg.box.app.data.source.Repository;

/**
 * Created by gavin on 2017/3/17.
 */

public class BoxApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Repository.createInstance(this);
    }

}

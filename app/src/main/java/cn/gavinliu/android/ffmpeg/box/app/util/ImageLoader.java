package cn.gavinliu.android.ffmpeg.box.app.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;

/**
 * Created by gavin on 2017/3/18.
 */

public class ImageLoader {

    public static void load(Context context, String path, ImageView view) {
        Glide.with(context).load(new File(path))
                .centerCrop()
                .into(view);
    }
}

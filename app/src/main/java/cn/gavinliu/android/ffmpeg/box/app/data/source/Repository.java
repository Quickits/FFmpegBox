package cn.gavinliu.android.ffmpeg.box.app.data.source;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cn.gavinliu.android.ffmpeg.box.app.data.Video;

/**
 * Created by gavin on 2017/3/17.
 */

public class Repository {

    private static final String TAG = Repository.class.getSimpleName();

    private static Repository mInstance;

    public static void createInstance(Context ctx) {
        if (mInstance == null) {
            mInstance = new Repository(ctx);
        }
    }

    public static Repository getInstance() {
        return mInstance;
    }

    // class content start

    private ContentResolver mContentResolver;

    private Repository(Context ctx) {
        mContentResolver = ctx.getContentResolver();
    }

    public List<Video> getVideo() {
        Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;

        String projection[] = {
                MediaStore.Video.Media._ID,
                MediaStore.Video.Media.TITLE,
                MediaStore.Video.Media.DURATION,
                MediaStore.Video.Media.DATA
        };

        Cursor cursor = mContentResolver.query(uri, projection, null, null, null);

        List<Video> list = new ArrayList<>();

        if (cursor != null) {
            while (cursor.moveToNext()) {
                long id = cursor.getLong(0);
                String title = cursor.getString(1);
                long duration = cursor.getLong(2);
                String path = cursor.getString(3);

                Log.d(TAG, "Video: " + id + ", " + title + ", " + duration + ", " + path);

                Video video = new Video(title, path, duration);
                list.add(video);
            }
            cursor.close();
        }

        return list;
    }

}

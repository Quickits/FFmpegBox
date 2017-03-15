package cn.gavinliu.android.ffmpeg.box.app.utils;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by gavin on 2017/3/15.
 */

public class PermissionsUtils {

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void getReadStoragePermissions(Activity activity) {
        int permissionCheck = ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_EXTERNAL_STORAGE)) {

            } else {
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
        }
    }

    public static void getWriteStoragePermissions(Activity activity) {
        int permissionCheck = ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            } else {
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
    }

}

package cn.gavinliu.android.ffmpeg.box.app.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import cn.gavinliu.android.ffmpeg.box.app.R;

/**
 * Created by gavin on 2017/3/18.
 */

public abstract class BaseContainerActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_container_activity);

        FragmentManager fragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null) {
            fragmentManager.beginTransaction()
                    .add(R.id.container, getFragment())
                    .commitAllowingStateLoss();
        }
    }


    public abstract Fragment getFragment();
}

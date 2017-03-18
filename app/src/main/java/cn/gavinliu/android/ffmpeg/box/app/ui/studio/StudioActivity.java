package cn.gavinliu.android.ffmpeg.box.app.ui.studio;

import android.support.v4.app.Fragment;

import cn.gavinliu.android.ffmpeg.box.app.widget.BaseContainerActivity;

/**
 * Created by gavin on 2017/3/17.
 */

public class StudioActivity extends BaseContainerActivity {

    @Override
    public Fragment getFragment() {
        return StudioFragment.newInstance(getIntent().getStringExtra("PATH"));
    }
}

package cn.gavinliu.android.ffmpeg.box.app.ui.studio;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import cn.gavinliu.android.ffmpeg.box.FFmpegBox;
import cn.gavinliu.android.ffmpeg.box.app.R;
import cn.gavinliu.android.ffmpeg.box.app.widget.BaseFragment;
import cn.gavinliu.android.ffmpeg.box.commands.Command;
import cn.gavinliu.android.ffmpeg.box.commands.CutGifCommand;

/**
 * Created by gavin on 2017/3/18.
 */

public class StudioFragment extends BaseFragment {

    private static final String TAG = StudioFragment.class.getSimpleName();

    public static StudioFragment newInstance(String path) {
        Bundle args = new Bundle();
        args.putString("PATH", path);

        StudioFragment fragment = new StudioFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected String getFragmentName() {
        return "工作室页";
    }


    private VideoView mVideoView;
    private Button mButton;

    private String mPath;

    private Handler mHandler;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_studio, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mVideoView = (VideoView) view.findViewById(R.id.video);
        mButton = (Button) view.findViewById(R.id.cut_gif);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cmdGif();
            }
        });
    }

    private void cmdGif() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                final long time = System.currentTimeMillis();

                String path = mPath;

                Command command = new CutGifCommand.Builder()
                        .setVideoFile(path)
                        .setGifFile(path + "_" + System.currentTimeMillis() + ".gif")
                        .setStartTime(mVideoView.getCurrentPosition() / 1000)
                        .setDuration(5)
                        .setWidth(480)
                        .setHeight(270)
                        .build();

                FFmpegBox.getInstance().execute(command);

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "耗时：" + (System.currentTimeMillis() - time), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mPath = getArguments().getString("PATH");

        mVideoView.setVideoPath(mPath);
        mVideoView.start();

    }
}

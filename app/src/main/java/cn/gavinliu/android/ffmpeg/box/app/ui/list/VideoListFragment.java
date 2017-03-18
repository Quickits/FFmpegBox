package cn.gavinliu.android.ffmpeg.box.app.ui.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.gavinliu.android.ffmpeg.box.app.R;
import cn.gavinliu.android.ffmpeg.box.app.data.Video;
import cn.gavinliu.android.ffmpeg.box.app.data.source.Repository;
import cn.gavinliu.android.ffmpeg.box.app.ui.studio.StudioActivity;
import cn.gavinliu.android.ffmpeg.box.app.util.ImageLoader;
import cn.gavinliu.android.ffmpeg.box.app.widget.BaseListFragment;

/**
 * Created by gavin on 2017/3/18.
 */

public class VideoListFragment extends BaseListFragment {

    public static VideoListFragment newInstance() {
        Bundle args = new Bundle();

        VideoListFragment fragment = new VideoListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private Adapter mAdapter;

    @Override
    protected String getFragmentName() {
        return "视频列表页";
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mAdapter = new Adapter();
        mAdapter.setVideos(Repository.getInstance().getVideo());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);

        showRecyclerView();
    }

    private static class Adapter extends RecyclerView.Adapter<Holder> {

        private List<Video> mVideos;

        public void setVideos(List<Video> videos) {
            mVideos = videos;
        }

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video_list, parent, false);
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            final Video video = mVideos.get(position);

            holder.mTitleView.setText(video.getTitle());

            ImageLoader.load(holder.mContext, video.getPath(), holder.mCoverView);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), StudioActivity.class);
                    intent.putExtra("PATH", video.getPath());
                    v.getContext().startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mVideos == null ? 0 : mVideos.size();
        }
    }

    private static class Holder extends RecyclerView.ViewHolder {

        private Context mContext;
        private TextView mTitleView;
        private ImageView mCoverView;

        public Holder(View itemView) {
            super(itemView);
            mContext = itemView.getContext();

            mTitleView = (TextView) itemView.findViewById(R.id.title);
            mCoverView = (ImageView) itemView.findViewById(R.id.cover);


        }
    }

}

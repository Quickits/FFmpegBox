package cn.gavinliu.android.ffmpeg.box.app.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.gavinliu.android.ffmpeg.box.app.R;

/**
 * Created by gavin on 2017/3/18.
 */

public abstract class BaseListFragment extends BaseFragment {

    protected View mLoadingView;
    protected TextView mTipsView;
    protected RecyclerView mRecyclerView;

    protected int fragmentLayoutID() {
        return R.layout.base_fragment_list;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(fragmentLayoutID(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.list);
        mTipsView = (TextView) view.findViewById(R.id.tips_view);
        mLoadingView = view.findViewById(R.id.loading_view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showLoadingView();
    }

    protected void showRecyclerView() {
        mLoadingView.setVisibility(View.GONE);
        mTipsView.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    protected void showTipsView(int tipsRes) {
        mLoadingView.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.GONE);

        mTipsView.setVisibility(View.VISIBLE);
        mTipsView.setText(getResources().getText(tipsRes, ""));
    }

    protected void showLoadingView() {
        mTipsView.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.GONE);
        mLoadingView.setVisibility(View.VISIBLE);
    }

}

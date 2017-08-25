package com.kevin.live.fragment.news;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kevin.live.R;
import com.kevin.live.adapter.news.HotNewsAdapter;
import com.kevin.live.base.BaseFragment;
import com.kevin.live.constant.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> on 2017/8/24 for Project ILife.
 * <h3>Description:</h3>
 * <div>
 * <div/>
 */


public class HotNewsFragment extends BaseFragment {
    @BindView(R.id.rl_recycler_view)
    RecyclerView rlRecyclerView;
    private List<String> data = new ArrayList<>();

    public static HotNewsFragment newInstance(String s) {
        HotNewsFragment hotNewsFragment = new HotNewsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.ARGS, s);
        hotNewsFragment.setArguments(bundle);
        return hotNewsFragment;
    }

    @Override
    public int setLayoutResId() {
        return R.layout.fragment_hot_news;
    }

    @Override
    public void initView() {
        for (int i = 0; i < 100; i++) {
            data.add("Life:\t" + i);
        }
        HotNewsAdapter hotNewsAdapter = new HotNewsAdapter(data, mActivity);
        rlRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        rlRecyclerView.setAdapter(hotNewsAdapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

}

package com.kevin.live.fragment.news;

import android.os.Bundle;

import com.kevin.live.R;
import com.kevin.live.base.BaseFragment;
import com.kevin.live.constant.Constant;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> on 2017/8/24 for Project ILife.
 * <h3>Description:</h3>
 * <div>
 * <div/>
 */


public class WorldNewsFragment extends BaseFragment {
    public static WorldNewsFragment newInstance(String s) {
        WorldNewsFragment fragment = new WorldNewsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.ARGS, s);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int setLayoutResId() {
        return R.layout.fragment_hot_news;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }
}

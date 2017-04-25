package com.kevin.live.fragment;

import android.os.Bundle;

import com.kevin.live.R;
import com.kevin.live.base.BaseFragment;
import com.kevin.live.constant.Constant;

/**
 * Created by Kevin on 2017/3/17.
 * Blog:http://blog.csdn.net/student9128.
 * Description:
 */

public class MeFragment extends BaseFragment {
    public static MeFragment newInstance(String s) {
        MeFragment fragment = new MeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.ARGS, s);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int setLayoutResId() {
        return R.layout.fragment_me;
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

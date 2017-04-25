package com.kevin.live.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.kevin.live.R;
import com.kevin.live.activity.MobileLookUpActivity;
import com.kevin.live.adapter.HomeGridViewAdapter;
import com.kevin.live.base.BaseFragment;
import com.kevin.live.constant.Constant;
import com.kevin.live.view.MyGridView;

import butterknife.BindView;

/**
 * Created by Kevin on 2017/3/17.
 * Blog:http://blog.csdn.net/student9128.
 * Description:
 */

public class HomeFragment extends BaseFragment {
    private int[] mGridViewIcon;
    private String[] mGridViewTitle;

    public static HomeFragment newInstance(String s) {
        HomeFragment homeFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.ARGS, s);
        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    @BindView(R.id.mgv_my_grid_view)
    MyGridView mMyGridView;

    @Override
    public int setLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        mGridViewIcon = new int[]{R.drawable.ic_bus, R.drawable.ic_dictionary, R.drawable.ic_joker,
                R.drawable.ic_location, R.drawable.ic_lottery, R.drawable.ic_nba};
        mGridViewTitle = new String[]{"公交", "字典&翻译", "笑话大全", "号码归属地", "彩票", "NBA"};
        HomeGridViewAdapter homeGridViewAdapter = new HomeGridViewAdapter(mActivity, mGridViewIcon, mGridViewTitle);
        mMyGridView.setAdapter(homeGridViewAdapter);
    }

    @Override
    public void initListener() {
        mMyGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                    case 1:
                    case 2:
                        showToast("正在开发中...");
                        break;
                    case 3:
                        startActivity(new Intent(mActivity, MobileLookUpActivity.class));
                        break;
                    case 4:
                    case 5:
                        showToast("正在开发中...");
                        break;
                }
            }
        });

    }

}

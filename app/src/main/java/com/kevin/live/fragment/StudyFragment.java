package com.kevin.live.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.kevin.live.R;
import com.kevin.live.adapter.TabLayoutStudyFragmentAdapter;
import com.kevin.live.base.BaseFragment;
import com.kevin.live.constant.Constant;
import com.kevin.live.fragment.study.AndroidStudyFragment;
import com.kevin.live.fragment.study.KotlinStudyFragment;
import com.kevin.live.fragment.study.ReactNativeStudyFragment;
import com.kevin.live.view.ColorTrackTabLayout;
import com.kevin.live.view.NoSmoothViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Kevin on 2017/3/17.
 * Blog:http://blog.csdn.net/student9128.
 * Description:
 */

public class StudyFragment extends BaseFragment {
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.iv_function)
    ImageView mIvFunction;
    @BindView(R.id.tool_bar)
    Toolbar mToolbar;
    @BindView(R.id.tab_layout)
    ColorTrackTabLayout mTabLayout;
    @BindView(R.id.ns_view_pager)
    NoSmoothViewPager mViewPager;
    private List<Fragment> mFragments = new ArrayList<>();
    private List<String> mTabList = new ArrayList<>();
    private TabLayoutStudyFragmentAdapter mAdapter;

    public static StudyFragment newInstance(String s) {
        StudyFragment fragment = new StudyFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.ARGS, s);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int setLayoutResId() {
        return R.layout.fragment_study;
    }

    @Override
    public void initView() {
        AppCompatActivity activity = (AppCompatActivity) this.mActivity;
//        mToolbar.setTitle(null);
        activity.setSupportActionBar(mToolbar);
        ActionBar supportActionBar = activity.getSupportActionBar();
        supportActionBar.setTitle(null);
        supportActionBar.setDisplayHomeAsUpEnabled(false);
        initTabList();
        initFragmentList();
        mAdapter = new TabLayoutStudyFragmentAdapter(getChildFragmentManager(),
                mFragments, mTabList);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    private void initTabList() {
        mTabList.clear();
        mTabList.add(getString(R.string.tab_study_android));
        mTabList.add(getString(R.string.tab_study_kotlin));
        mTabList.add(getString(R.string.tab_study_react_native));
    }

    /**
     * add Fragment
     */
    public void initFragmentList() {
        mFragments.clear();
        mFragments.add(AndroidStudyFragment.newInstance(getString(R.string.tab_study_android)));
        mFragments.add(KotlinStudyFragment.newInstance(getString(R.string.tab_study_kotlin)));
        mFragments.add(ReactNativeStudyFragment.newInstance(getString(R.string.tab_study_react_native)));

    }

    @Override
    public void initData() {
        mTvTitle.setText(getString(R.string.title_study));
    }

    @Override
    public void initListener() {
    }

}

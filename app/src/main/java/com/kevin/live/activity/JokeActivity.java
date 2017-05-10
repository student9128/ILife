package com.kevin.live.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kevin.live.R;
import com.kevin.live.adapter.JokeByTimeAdapter;
import com.kevin.live.adapter.JokeTabLayoutFragmentAdapter;
import com.kevin.live.base.BaseActivity;
import com.kevin.live.bean.JokeByTimeBean;
import com.kevin.live.fragment.HomeFragment;
import com.kevin.live.fragment.JokeByTimeFragmnet;
import com.kevin.live.fragment.MeFragment;
import com.kevin.live.fragment.NewsFragment;
import com.kevin.live.fragment.StudyFragment;
import com.kevin.live.http.Urls;
import com.kevin.live.util.LogK;
import com.kevin.live.view.NoSmoothViewPager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by <b><a style="color:#8BC34A"href="http://blog.csdn.net/student9128">Kevin</a></b> on 2017/5/9.
 * <br/><b>Blog:</b>
 * <div style="color:#8BC34A"><i>http://blog.csdn.net/student9128.</i></div>
 * <p style="color:#4CAF50"><b>Description:</b>
 * <p>
 * </p>
 */


public class JokeActivity extends BaseActivity {

    private NoSmoothViewPager mViewPager;
    private TabLayout mTabLayout;
    private List<Fragment> mFragments = new ArrayList<>();
    private List<String> mTabList = new ArrayList<>();
    private JokeTabLayoutFragmentAdapter mAdapter;
    private TextView mTitle;
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;

    @Override
    public void initView() {
        setContentView(R.layout.activity_joke);
        ButterKnife.bind(this);
        mToolBar.setTitle("");
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mTitle = (TextView) findViewById(R.id.tv_title);
        mViewPager = (NoSmoothViewPager) findViewById(R.id.ns_view_pager);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        initTabList();
        initFragmentList();
        mAdapter = new JokeTabLayoutFragmentAdapter(getSupportFragmentManager(), this, mFragments, mTabList);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    public void initData() {
        mTitle.setText("笑话大全");
    }

    @Override
    public void initListener() {
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initTabList() {
        mTabList.clear();
        mTabList.add("按时间笑话");
        mTabList.add(getString(R.string.tab_performance));
        mTabList.add(getString(R.string.tab_repository));
        mTabList.add(getString(R.string.tab_me));
    }

    /**
     * add Fragment
     */
    public void initFragmentList() {
        mFragments.clear();
        mFragments.add(JokeByTimeFragmnet.newInstance(getString(R.string.tab_home)));
        mFragments.add(NewsFragment.newInstance(getString(R.string.tab_performance)));
        mFragments.add(StudyFragment.newInstance(getString(R.string.tab_repository)));
        mFragments.add(MeFragment.newInstance(getString(R.string.tab_me)));

    }


}

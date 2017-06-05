package com.kevin.live.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kevin.live.R;
import com.kevin.live.adapter.TabLayoutFragmentAdapter;
import com.kevin.live.base.BaseActivity;
import com.kevin.live.fragment.HomeFragment;
import com.kevin.live.fragment.MeFragment;
import com.kevin.live.fragment.NewsFragment;
import com.kevin.live.fragment.StudyFragment;
import com.kevin.live.view.NoSmoothViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;


public class MainActivity extends BaseActivity implements TabLayout.OnTabSelectedListener, View.OnClickListener {
    private LinearLayout mContainer;
    private Toolbar mToolbar;
    private NoSmoothViewPager mViewPager;
    private TabLayout mTabLayout;
    private List<String> mTabList = new ArrayList<>();
    private TabLayoutFragmentAdapter mAdapter;
    private List<Fragment> mFragments = new ArrayList<>();
    private int[] mTabImgs = new int[]{R.drawable.ic_home, R.drawable.ic_news, R.drawable.ic_study, R.drawable.ic_me};

    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
    @Override
    public void initView() {

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContainer = (LinearLayout) findViewById(R.id.activity_main);
        mViewPager = (NoSmoothViewPager) findViewById(R.id.ns_view_pager);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        initTabList();
        initFragmentList();
        mAdapter = new TabLayoutFragmentAdapter(getSupportFragmentManager(), this, mFragments, mTabList, mTabImgs);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);
        mViewPager.setOffscreenPageLimit(4);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            mTabLayout.getTabAt(i).setCustomView(mAdapter.getTabView(i));
        }
        mTabLayout.addOnTabSelectedListener(this);
    }

    private void initTabList() {
        mTabList.clear();
        mTabList.add(getString(R.string.tab_home));
        mTabList.add(getString(R.string.tab_performance));
        mTabList.add(getString(R.string.tab_repository));
        mTabList.add(getString(R.string.tab_me));
    }

    /**
     * add Fragment
     */
    public void initFragmentList() {
        mFragments.clear();
        mFragments.add(HomeFragment.newInstance(getString(R.string.tab_home)));
        mFragments.add(NewsFragment.newInstance(getString(R.string.tab_performance)));
        mFragments.add(StudyFragment.newInstance(getString(R.string.tab_repository)));
        mFragments.add(MeFragment.newInstance(getString(R.string.tab_me)));

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        setTabSelectedState(tab);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        setTabUnSelectedState(tab);
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    private void setTabSelectedState(TabLayout.Tab tab) {
        View customView = tab.getCustomView();
        TextView tabText = (TextView) customView.findViewById(R.id.tv_tab_text);
        ImageView tabIcon = (ImageView) customView.findViewById(R.id.iv_tab_icon);
        tabText.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
        String s = tabText.getText().toString();
        if (getString(R.string.tab_home).equals(s)) {
            tabIcon.setImageResource(R.drawable.ic_home_fill);
        } else if (getString(R.string.tab_performance).equals(s)) {
            tabIcon.setImageResource(R.drawable.ic_news_fill);
        } else if (getString(R.string.tab_repository).equals(s)) {
            tabIcon.setImageResource(R.drawable.ic_study_fill);
        } else if (getString(R.string.tab_me).equals(s)) {
            tabIcon.setImageResource(R.drawable.ic_me_fill);
        }
    }

    private void setTabUnSelectedState(TabLayout.Tab tab) {
        View customView = tab.getCustomView();
        TextView tabText = (TextView) customView.findViewById(R.id.tv_tab_text);
        ImageView tabIcon = (ImageView) customView.findViewById(R.id.iv_tab_icon);
        tabText.setTextColor(ContextCompat.getColor(this, R.color.gray));
        String s = tabText.getText().toString();
        if (getString(R.string.tab_home).equals(s)) {
            tabIcon.setImageResource(R.drawable.ic_home);
        } else if (getString(R.string.tab_performance).equals(s)) {
            tabIcon.setImageResource(R.drawable.ic_news);
        } else if (getString(R.string.tab_repository).equals(s)) {
            tabIcon.setImageResource(R.drawable.ic_study);
        } else if (getString(R.string.tab_me).equals(s)) {
            tabIcon.setImageResource(R.drawable.ic_me);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.iv_function:
//                startActivity(new Intent(this, SearchActivity.class));
//                break;
        }
    }
}

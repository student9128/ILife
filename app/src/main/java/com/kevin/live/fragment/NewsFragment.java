package com.kevin.live.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kevin.live.R;
import com.kevin.live.adapter.TabLayoutNewsFragmentAdapter;
import com.kevin.live.base.BaseFragment;
import com.kevin.live.constant.Constant;
import com.kevin.live.fragment.news.HotNewsFragment;
import com.kevin.live.fragment.news.SocietyNewsFragment;
import com.kevin.live.fragment.news.TechNewsFragment;
import com.kevin.live.fragment.news.WorldNewsFragment;
import com.kevin.live.view.NoSmoothViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Kevin on 2017/3/17.
 * Blog:http://blog.csdn.net/student9128.
 * Description:
 */

public class NewsFragment extends BaseFragment implements TabLayout.OnTabSelectedListener, ViewPager.OnPageChangeListener {
//    @BindView(R.id.tool_bar)
//    Toolbar mToolbar;
//    @BindView(R.id.tv_title)
//    TextView mTvTitle;
//    @BindView(R.id.iv_function)
//    ImageView mIvFunction;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.ns_view_pager)
    NoSmoothViewPager mViewPager;
    private List<Fragment> mFragments = new ArrayList<>();
    private List<String> mTabList = new ArrayList<>();
    private TabLayoutNewsFragmentAdapter mAdapter;

    //灰色以及相对应的RGB值
    private int mGrayColor;
    private int mGrayRed;
    private int mGrayGreen;
    private int mGrayBlue;
    //灰色以及相对应的RGB值
    private int mGreenColor;
    private int mGreenRed;
    private int mGreenGreen;
    private int mGreenBlue;
    private int mBlackColor;
    private int mBlackRed;
    private int mBlackGreen;
    private int mBlackBlue;

    public static NewsFragment newInstance(String s) {
        NewsFragment fragment = new NewsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.ARGS, s);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int setLayoutResId() {
        return R.layout.fragment_news;
    }

    @Override
    public void initView() {
//        AppCompatActivity activity = (AppCompatActivity) this.mActivity;
//        activity.setSupportActionBar(mToolbar);
//        activity.getSupportActionBar().setTitle(null);
//        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        initTabList();
        initFragmentList();
        mAdapter = new TabLayoutNewsFragmentAdapter(getChildFragmentManager(),
                mActivity, mFragments, mTabList);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            mTabLayout.getTabAt(i).setCustomView(mAdapter.getTabView(i));
        }
    }

    private void initTabList() {
        mTabList.clear();
        mTabList.add(getString(R.string.tab_news_hot));
        mTabList.add(getString(R.string.tab_news_tech));
        mTabList.add(getString(R.string.tab_news_society));
        mTabList.add(getString(R.string.tab_news_world));
        mTabList.add(getString(R.string.tab_news_entertainment));
        mTabList.add(getString(R.string.tab_news_car));
        mTabList.add(getString(R.string.tab_news_sport));
        mTabList.add(getString(R.string.tab_news_military));
    }

    /**
     * add Fragment
     */
    public void initFragmentList() {
        mFragments.clear();
        mFragments.add(HotNewsFragment.newInstance(getString(R.string.tab_news_hot)));
        mFragments.add(TechNewsFragment.newInstance(getString(R.string.tab_news_tech)));
        mFragments.add(SocietyNewsFragment.newInstance(getString(R.string.tab_news_society)));
        mFragments.add(WorldNewsFragment.newInstance(getString(R.string.tab_news_world)));
        mFragments.add(WorldNewsFragment.newInstance(getString(R.string.tab_news_entertainment)));
        mFragments.add(WorldNewsFragment.newInstance(getString(R.string.tab_news_car)));
        mFragments.add(WorldNewsFragment.newInstance(getString(R.string.tab_news_sport)));
        mFragments.add(WorldNewsFragment.newInstance(getString(R.string.tab_news_military)));

    }

    @Override
    public void initData() {
//        mTvTitle.setText(getString(R.string.title_news));
        initColor();
    }

    @Override
    public void initListener() {
        mTabLayout.addOnTabSelectedListener(this);
        mViewPager.addOnPageChangeListener(this);

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
        tabText.setTextColor(ContextCompat.getColor(mActivity, R.color.colorPrimary));
        String s = tabText.getText().toString();
    }

    private void setTabUnSelectedState(TabLayout.Tab tab) {
        View customView = tab.getCustomView();
        TextView tabText = (TextView) customView.findViewById(R.id.tv_tab_text);
        tabText.setTextColor(ContextCompat.getColor(mActivity, R.color.black));
        String s = tabText.getText().toString();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (positionOffset > 0) {
            printLogd("Position:===============\t" + position);
            TabLayout.Tab tabAt = mTabLayout.getTabAt(position);
            TabLayout.Tab tabAt1 = mTabLayout.getTabAt(position + 1);

            View customView1 = tabAt1.getCustomView();
            TextView tabText1 = (TextView) customView1.findViewById(R.id.tv_tab_text);

            View customView = tabAt.getCustomView();
            TextView tabText = (TextView) customView.findViewById(R.id.tv_tab_text);
            tabText.setTextColor(getGreen2Black(positionOffset));
            tabText1.setTextColor(getBlack2Green(positionOffset));
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void initColor() {
        mGrayColor = ContextCompat.getColor(mActivity, R.color.gray);
        mGrayRed = Color.red(mGrayColor);
        mGrayGreen = Color.green(mGrayColor);
        mGrayBlue = Color.blue(mGrayColor);
        mGreenColor = ContextCompat.getColor(mActivity, R.color.colorPrimary);
        mGreenRed = Color.red(mGreenColor);
        mGreenGreen = Color.green(mGreenColor);
        mGreenBlue = Color.blue(mGreenColor);
        mBlackColor = ContextCompat.getColor(mActivity, R.color.black);
        mBlackRed = Color.red(mBlackColor);
        mBlackGreen = Color.green(mBlackColor);
        mBlackBlue = Color.blue(mBlackColor);
    }

    /**
     * offset from 0 to 1
     *
     * @param offset
     * @return
     */
    private int getGreen2Black(float offset) {
        int red = (int) (offset * (mBlackRed - mGreenRed) + mGreenRed);
        int green = (int) (offset * (mBlackGreen - mGreenGreen) + mGreenGreen);
        int blue = (int) (offset * (mBlackBlue - mGreenBlue) + mGreenBlue);
        return Color.argb(255, red, green, blue);
    }

    private int getBlack2Green(float offset) {
        int red = (int) (offset * (mGreenRed - mBlackRed) + mBlackRed);
        int green = (int) (offset * (mGreenGreen - mBlackGreen) + mBlackGreen);
        int blue = (int) (offset * (mGreenBlue - mBlackBlue) + mBlackBlue);
        return Color.argb(255, red, green, blue);
    }

    /**
     * offset from 0.1 to 0.9
     *
     * @param offset
     * @return
     */
    private int get2Gray(float offset) {
        int red = (int) (1.25 * offset * (mGrayRed - mGreenRed) - 0.125 * mGrayRed + 1.125 * mGreenRed);
        int green = (int) (1.25 * offset * (mGrayGreen - mGreenGreen) - 0.125 * mGrayGreen + 1.125 * mGreenGreen);
        int blue = (int) (1.25 * offset * (mGrayBlue - mGreenBlue) - 0.125 * mGrayBlue + 1.125 * mGreenBlue);
        return Color.argb(255, red, green, blue);
    }


    private int get2Green(float offset) {
        int red = (int) (1.25 * offset * (mGreenRed - mGrayRed) - 0.125 * mGreenRed + 1.125 * mGrayRed);
        int green = (int) (1.25 * offset * (mGreenGreen - mGrayGreen) - 0.125 * mGreenGreen + 1.125 * mGrayGreen);
        int blue = (int) (1.25 * offset * (mGreenBlue - mGrayBlue) - 0.125 * mGreenBlue + 1.125 * mGrayBlue);
        return Color.argb(255, red, green, blue);
    }

    /**
     * offset from 0 to 1
     *
     * @param offset
     * @return
     */
    private int get2Gray2(float offset) {
        int red = (int) (offset * (mGrayRed - mGreenRed) + mGreenRed);
        int green = (int) (offset * (mGrayGreen - mGreenGreen) + mGreenGreen);
        int blue = (int) (offset * (mGrayBlue - mGreenBlue) + mGrayBlue);
        return Color.argb(255, red, green, blue);
    }

    /**
     * offset from 0 to 1
     *
     * @param offset
     * @return
     */
    private int get2Green2(float offset) {
        int red = (int) (offset * (mGreenRed - mGrayRed) + mGrayRed);
        int green = (int) (offset * (mGreenGreen - mGrayGreen) + mGrayGreen);
        int blue = (int) (offset * (mGreenBlue - mGrayBlue) + mGrayBlue);
        return Color.argb(255, red, green, blue);
    }

    /**
     * positionOffset from 0 to 0.5 & o.5 to 1.
     *
     * @param positionOffset
     * @return
     */
    private int getGrayToGreen(float positionOffset) {
        int red = (int) (positionOffset * (mGreenRed - mGrayRed) * 2 + mGrayRed);
        int green = (int) (positionOffset * (mGreenGreen - mGrayGreen) * 2 + mGrayGreen);
        int blue = (int) ((positionOffset) * (mGreenBlue - mGrayBlue) * 2 + mGrayBlue);
        return Color.argb(255, red, green, blue);
    }

    private int getGreenToGray(float positionOffset) {
        int red = (int) (positionOffset * (mGrayRed - mGreenRed) * 2 + 2 * mGreenRed - mGrayRed);
        int green = (int) (positionOffset * (mGrayGreen - mGreenGreen) * 2 + 2 * mGreenGreen - mGrayGreen);
        int blue = (int) (positionOffset * (mGrayBlue - mGreenBlue) * 2 + 2 * mGreenBlue - mGrayBlue);
        return Color.argb(255, red, green, blue);
    }
}

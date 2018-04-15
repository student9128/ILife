package com.kevin.live.activity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
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


public class MainActivity extends BaseActivity implements TabLayout.OnTabSelectedListener, View.OnClickListener, ViewPager.OnPageChangeListener {
    private LinearLayout mContainer;
    private Toolbar mToolbar;
    private NoSmoothViewPager mViewPager;
    private TabLayout mTabLayout;
    private List<String> mTabList = new ArrayList<>();
    private TabLayoutFragmentAdapter mAdapter;
    private List<Fragment> mFragments = new ArrayList<>();
    private int[] mTabImgs = new int[]{R.drawable.ic_home, R.drawable.ic_news, R.drawable.ic_study, R.drawable.ic_me};
    private int[] mTabImgsFill = new int[]{R.drawable.ic_home_fill, R.drawable.ic_news_fill,
            R.drawable.ic_study_fill, R.drawable.ic_me_fill};

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

    @Override
    public void initView() {

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContainer = (LinearLayout) findViewById(R.id.activity_main);
        mViewPager = (NoSmoothViewPager) findViewById(R.id.ns_view_pager);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        initTabList();
        initFragmentList();
        mAdapter = new TabLayoutFragmentAdapter(getSupportFragmentManager(),
                this, mFragments, mTabList, mTabImgs, mTabImgsFill);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);
        mViewPager.setOffscreenPageLimit(0);
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
        mTabList.add(getString(R.string.tab_news));
        mTabList.add(getString(R.string.tab_study));
        mTabList.add(getString(R.string.tab_me));
    }

    /**
     * add Fragment
     */
    public void initFragmentList() {
        mFragments.clear();
        mFragments.add(HomeFragment.newInstance(getString(R.string.tab_home)));
        mFragments.add(NewsFragment.newInstance(getString(R.string.tab_news)));
        mFragments.add(StudyFragment.newInstance(getString(R.string.tab_study)));
        mFragments.add(MeFragment.newInstance(getString(R.string.tab_me)));

    }

    @Override
    public void initData() {
        initColor();
    }

    @Override
    public void initListener() {
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
        ImageView tabIcon = (ImageView) customView.findViewById(R.id.iv_tab_icon);
        ImageView tabIconFill = (ImageView) customView.findViewById(R.id.iv_tab_icon_fill);
        tabText.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
        String s = tabText.getText().toString();
        tabIcon.setColorFilter(mGreenColor, PorterDuff.Mode.SRC_IN);
        tabIconFill.setAlpha(1f);
//        if (getString(R.string.tab_home).equals(s)) {
////            tabIcon.setImageResource(R.drawable.ic_home_fill);
//            tabIcon.setColorFilter(mGreenColor, PorterDuff.Mode.SRC_IN);
//            tabIconFill.setAlpha(1f);
//        } else if (getString(R.string.tab_performance).equals(s)) {
////            tabIcon.setImageResource(R.drawable.ic_news_fill);
//            tabIcon.setColorFilter(mGreenColor, PorterDuff.Mode.SRC_IN);
//            tabIconFill.setAlpha(1f);
//        } else if (getString(R.string.tab_repository).equals(s)) {
//            tabIcon.setColorFilter(mGreenColor, PorterDuff.Mode.SRC_IN);
//            tabIconFill.setAlpha(1f);
////            tabIcon.setImageResource(R.drawable.ic_study_fill);
//        } else if (getString(R.string.tab_me).equals(s)) {
//            tabIcon.setColorFilter(mGreenColor, PorterDuff.Mode.SRC_IN);
//            tabIconFill.setAlpha(1f);
////            tabIcon.setImageResource(R.drawable.ic_me_fill);
//        }
    }

    private void setTabUnSelectedState(TabLayout.Tab tab) {
        View customView = tab.getCustomView();
        TextView tabText = (TextView) customView.findViewById(R.id.tv_tab_text);
        ImageView tabIcon = (ImageView) customView.findViewById(R.id.iv_tab_icon);
        ImageView tabIconFill = (ImageView) customView.findViewById(R.id.iv_tab_icon_fill);
        tabText.setTextColor(ContextCompat.getColor(this, R.color.gray));
        String s = tabText.getText().toString();
        tabIcon.setColorFilter(mGrayColor, PorterDuff.Mode.SRC_IN);
        tabIconFill.setAlpha(0f);
//        if (getString(R.string.tab_home).equals(s)) {
//            tabIcon.setImageResource(R.drawable.ic_home);
//        } else if (getString(R.string.tab_performance).equals(s)) {
//            tabIcon.setImageResource(R.drawable.ic_news);
//        } else if (getString(R.string.tab_repository).equals(s)) {
//            tabIcon.setImageResource(R.drawable.ic_study);
//        } else if (getString(R.string.tab_me).equals(s)) {
//            tabIcon.setImageResource(R.drawable.ic_me);
//        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.iv_function:
//                startActivity(new Intent(this, SearchActivity.class));
//                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int selectedTabPosition = mTabLayout.getSelectedTabPosition();
        printLogd("Select:\t" + selectedTabPosition);
        printLogd("Position:\t" + position);
        ColorStateList tabTextColors = mTabLayout.getTabTextColors();
        if (positionOffset > 0) {
            printLogd("Position:===============\t" + position);
            TabLayout.Tab tabAt = mTabLayout.getTabAt(position);
            TabLayout.Tab tabAt1 = mTabLayout.getTabAt(position + 1);

            View customView1 = tabAt1.getCustomView();
            TextView tabText1 = (TextView) customView1.findViewById(R.id.tv_tab_text);
            ImageView tabIcon1 = (ImageView) customView1.findViewById(R.id.iv_tab_icon);
            ImageView tabIconFill1 = (ImageView) customView1.findViewById(R.id.iv_tab_icon_fill);

            View customView = tabAt.getCustomView();
            TextView tabText = (TextView) customView.findViewById(R.id.tv_tab_text);
            ImageView tabIcon = (ImageView) customView.findViewById(R.id.iv_tab_icon);
            ImageView tabIconFill = (ImageView) customView.findViewById(R.id.iv_tab_icon_fill);
            if (positionOffset >= 0.1 && positionOffset <= 0.9) {
                tabText.setTextColor(get2Gray(positionOffset));
                tabText1.setTextColor(get2Green(positionOffset));
            }
            if (positionOffset < 0.5) {
                tabIcon.setColorFilter(mGreenColor, PorterDuff.Mode.SRC_IN);
                tabIcon1.setColorFilter(getGrayToGreen(positionOffset), PorterDuff.Mode.SRC_IN);
                tabIconFill.setAlpha(1 - 2 * positionOffset);
                tabIconFill1.setAlpha(0f);

            } else {
                tabIcon.setColorFilter(getGreenToGray(positionOffset), PorterDuff.Mode.SRC_IN);
                tabIcon1.setColorFilter(mGreenColor, PorterDuff.Mode.SRC_IN);
                tabIconFill.setAlpha(0f);
                tabIconFill1.setAlpha(2 * positionOffset - 1);
            }
        }

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void initColor() {
        mGrayColor = ContextCompat.getColor(this, R.color.gray);
        mGrayRed = Color.red(mGrayColor);
        mGrayGreen = Color.green(mGrayColor);
        mGrayBlue = Color.blue(mGrayColor);
        mGreenColor = ContextCompat.getColor(this, R.color.colorPrimary);
        mGreenRed = Color.red(mGreenColor);
        mGreenGreen = Color.green(mGreenColor);
        mGreenBlue = Color.blue(mGreenColor);
    }

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

    private int getGrayToGreen(float positionOffset) {
        int red = (int) (positionOffset * (mGreenRed - mGrayRed) * 2 + mGrayRed);
        int green = (int) (positionOffset * (mGreenGreen - mGrayGreen) * 2 + mGrayGreen);
        int blue = (int) ((positionOffset) * (mGreenBlue - mGrayBlue) * 2 + mGrayBlue);
//        Log.d("why ", "#### " + red + "  " + green + "  " + blue);
        return Color.argb(255, red, green, blue);
    }

    private int getGreenToGray(float positionOffset) {
        int red = (int) (positionOffset * (mGrayRed - mGreenRed) * 2 + 2 * mGreenRed - mGrayRed);
        int green = (int) (positionOffset * (mGrayGreen - mGreenGreen) * 2 + 2 * mGreenGreen - mGrayGreen);
        int blue = (int) (positionOffset * (mGrayBlue - mGreenBlue) * 2 + 2 * mGreenBlue - mGrayBlue);
//        Log.d("why ", "#### " + red + "  " + green + "  " + blue);
        return Color.argb(255, red, green, blue);
    }
}

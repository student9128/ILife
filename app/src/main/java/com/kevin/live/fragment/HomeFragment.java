package com.kevin.live.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kevin.live.R;
import com.kevin.live.activity.CityBusActivity;
import com.kevin.live.activity.JokeActivity;
import com.kevin.live.activity.LotteryListActivity;
import com.kevin.live.activity.MobileLookUpActivity;
import com.kevin.live.activity.TestActivity;
import com.kevin.live.adapter.HomeGridViewAdapter;
import com.kevin.live.adapter.MyViewPagerAdapter;
import com.kevin.live.base.BaseFragment;
import com.kevin.live.constant.Constant;
import com.kevin.live.util.LogK;
import com.kevin.live.view.MyGridView;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

/**
 * Created by Kevin on 2017/3/17.
 * Blog:http://blog.csdn.net/student9128.
 * Description:
 */

public class HomeFragment extends BaseFragment {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_function)
    ImageView ivFunction;
    @BindView(R.id.mgv_my_grid_view)
    MyGridView mMyGridView;
    @BindView(R.id.tool_bar)
    Toolbar mToolbar;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.indicator)
    LinearLayout mIndicator;

    private int[] mGridViewIcon;
    private String[] mGridViewTitle;

    private ImageView[] mImageView;
    private int[] mImageArr = new int[]{R.drawable.ic_place_holder, R.drawable.ic_place_holder_1, R.drawable.ic_place_holder_2};
    private MyViewPagerAdapter mAdapter;
    private Timer mTimer;
    private static final int UPDATE_VIEWPAGER = 100;
    private boolean isLoop = true;

    public static HomeFragment newInstance(String s) {
        HomeFragment homeFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.ARGS, s);
        homeFragment.setArguments(bundle);
        return homeFragment;
    }


    @Override
    public int setLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {

        AppCompatActivity activity = (AppCompatActivity) this.mActivity;
        activity.setSupportActionBar(mToolbar);
        activity.getSupportActionBar().setTitle(null);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        mTimer = new Timer();
        mAdapter = new MyViewPagerAdapter(mActivity, mImageArr);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(5000 * (mImageArr.length));

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewPagerSetting();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mTimer.cancel();
    }

    /**
     * some settings about ViewPager.
     */
    private void viewPagerSetting() {
        mViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        isLoop = false;
                        break;
                    case MotionEvent.ACTION_UP:
                        isLoop = true;
                        break;
                }
                return false;
            }
        });

        initIndicator();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = UPDATE_VIEWPAGER;
                if (isLoop) {//如果isLoop = true 才进行轮播
                    handler.sendMessage(message);
                }
            }
        }, 2000, 3000);//这里定义了轮播图切换的间隔时间
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setIndicator(position);
                LogK.i("kevin", position + "");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void initData() {
        tvTitle.setText(getString(R.string.title_home));
        mGridViewIcon = new int[]{R.drawable.ic_bus, R.drawable.ic_dictionary, R.drawable.ic_food, R.drawable.ic_joker,
                R.drawable.ic_location, R.drawable.ic_lottery, R.drawable.ic_nba, R.drawable.ic_tv};
        mGridViewTitle = new String[]{"公交", "字典&翻译", "食谱", "笑话大全", "号码归属", "彩票", "NBA", "电视节目"};
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
                        startActivity(new Intent(mActivity, CityBusActivity.class));
                        break;
                    case 1:
                    case 2:
                        startActivity(new Intent(mActivity, TestActivity.class));
                        showToast("正在开发中...");
                        break;
                    case 3://笑话大全
                        showToast("正在开发中...");
                        startActivity(new Intent(mActivity, JokeActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(mActivity, MobileLookUpActivity.class));
                        break;
                    case 5:
//                        showToast("正在开发中...");
                        startNewActivity(LotteryListActivity.class);
                        break;
                    case 6:
                        break;
                    case 7:
                        break;

                }
            }
        });

    }

    private void initIndicator() {
        mImageView = new ImageView[mImageArr.length];
        for (int i = 0; i < mImageArr.length; i++) {
            View view = LayoutInflater.from(mActivity).inflate(R.layout.indicator_image, null);
            view.findViewById(R.id.indicator_iamge).setBackgroundResource(R.drawable.shape_origin_point_pink);
            mImageView[i] = new ImageView(mActivity);
            if (i == 0) {
                mImageView[i].setBackgroundResource(R.drawable.shape_origin_point_pink);
            } else {
                mImageView[i].setBackgroundResource(R.drawable.shape_origin_point_white);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(20, 0, 0, 0);
                mImageView[i].setLayoutParams(layoutParams);
            }
            mIndicator.addView(mImageView[i]);
        }
    }

    private void setIndicator(int position) {
        position %= mImageArr.length;
        for (int i = 0; i < mImageArr.length; i++) {
            mImageView[i].setBackgroundResource(R.drawable.shape_origin_point_pink);
            if (position != i) {
                mImageView[i].setBackgroundResource(R.drawable.shape_origin_point_white);
            }

        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case UPDATE_VIEWPAGER:
                    mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, true);
                    break;
            }
        }
    };
}

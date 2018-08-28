package com.kevin.live.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.kevin.live.activity.AirInfoActivity;
import com.kevin.live.activity.CookBookActivity;
import com.kevin.live.activity.FlightInfoActivity;
import com.kevin.live.activity.PostcodeActivity;
import com.kevin.live.activity.TrainInfoActivity;
import com.kevin.live.activity.JokeActivity;
import com.kevin.live.activity.LotteryListActivity;
import com.kevin.live.activity.MobileLookUpActivity;
import com.kevin.live.activity.TestActivity;
import com.kevin.live.adapter.CurrentBoxOfficeAdapter;
import com.kevin.live.adapter.HomeGridViewAdapter;
import com.kevin.live.adapter.MyViewPagerAdapter;
import com.kevin.live.base.BaseFragment;
import com.kevin.live.bean.CurrentBoxOfficeBean;
import com.kevin.live.constant.Constant;
import com.kevin.live.http.Urls;
import com.kevin.live.http.util.HttpResponseListener;
import com.kevin.live.http.util.VolleyUtils;
import com.kevin.live.util.LogK;
import com.kevin.live.view.MyGridView;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    @BindView(R.id.rl_recycler_view)
    RecyclerView mRecyclerView;

    private int[] mGridViewIcon;
    private String[] mGridViewTitle;

    private ImageView[] mImageView;
    private int[] mImageArr = new int[]{R.drawable.ic_place_holder, R.drawable.ic_place_holder_1, R.drawable.ic_place_holder_2};
    private MyViewPagerAdapter mAdapter;
    private Timer mTimer;
    private static final int UPDATE_VIEWPAGER = 100;
    private boolean isLoop = true;
    private CurrentBoxOfficeAdapter currentBoxOfficeAdapter;
    private List<CurrentBoxOfficeBean.ResultBean> boxOfficeData = new ArrayList<>();

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
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void initData() {
        tvTitle.setText(getString(R.string.title_home));
        mGridViewIcon = new int[]{R.drawable.ic_bus, R.drawable.ic_post_code, R.drawable.ic_food, R.drawable.ic_flight,
                R.drawable.ic_location, R.drawable.ic_lottery_1, R.drawable.ic_oil, R.drawable.ic_air};
        mGridViewTitle = new String[]{"火车票", "邮编", "菜谱", "航班", "号码归属", "彩票", "油价", "空气"};
        HomeGridViewAdapter homeGridViewAdapter = new HomeGridViewAdapter(mActivity, mGridViewIcon, mGridViewTitle);
        mMyGridView.setAdapter(homeGridViewAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        mRecyclerView.setNestedScrollingEnabled(false);
//        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setFocusable(false);
        currentBoxOfficeAdapter = new CurrentBoxOfficeAdapter(boxOfficeData);
        mRecyclerView.setAdapter(currentBoxOfficeAdapter);
        doPostBoxOffice();
    }

    @Override
    public void initListener() {
        mMyGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0://火车票
                        startActivity(new Intent(mActivity, TrainInfoActivity.class));
                        break;
                    case 1://邮编
                        startActivity(new Intent(mActivity, PostcodeActivity.class));
                        break;
                    case 2://菜谱
                        startActivity(new Intent(mActivity, CookBookActivity.class));
                        break;
                    case 3://航班
                        startNewActivity(FlightInfoActivity.class);
                        break;
                    case 4://号码归属地
                        startActivity(new Intent(mActivity, MobileLookUpActivity.class));
                        break;
                    case 5://彩票
//                        showToast("正在开发中...");
                        startNewActivity(LotteryListActivity.class);
                        break;
                    case 6://油价
                        break;
                    case 7://空气
                        startNewActivity(AirInfoActivity.class);
                        break;
                    default:
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


    private void doPostBoxOffice() {
        Map<String, String> map = new HashMap<>();
        map.put("key", Urls.APP_Key);
        map.put("area", "CN");
        VolleyUtils.stringRequestByPost(mActivity, Urls.CURRENT_BOX_OFFICE, TAG, map, new HttpResponseListener<String>() {
            @Override
            public void onSuccess(String s) {
                printLogd(s);
                CurrentBoxOfficeBean currentBoxOfficeBean = JSON.parseObject(s, CurrentBoxOfficeBean.class);
                List<CurrentBoxOfficeBean.ResultBean> result = currentBoxOfficeBean.getResult();
                currentBoxOfficeAdapter.updateData(result);
            }

            @Override
            public void onFail(VolleyError volleyError) {
                printLogd(volleyError.getMessage());
            }
        });

    }
}

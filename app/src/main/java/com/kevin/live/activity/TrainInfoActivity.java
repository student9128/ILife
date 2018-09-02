package com.kevin.live.activity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.kevin.live.R;
import com.kevin.live.base.BaseActivity;
import com.kevin.live.fragment.TrainInfoByStationFragment;
import com.kevin.live.fragment.TrainInfoByTrainNoFragment;
import com.kevin.live.util.FontManager;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by <b><a style="color:#8BC34A"href="http://blog.csdn.net/student9128">Kevin</a></b> on 2017/5/8.
 * <br/><b>Blog:</b>
 * <div style="color:#8BC34A"><i>http://blog.csdn.net/student9128.</i></div>
 * <p style="color:#4CAF50"><b>Description:</b>
 * <p>
 * </p>
 */


public class TrainInfoActivity extends BaseActivity implements TrainInfoByStationFragment.OnTrainNoQueryClickListener,
        TrainInfoByTrainNoFragment.OnStationQueryClickListener {


    @BindView(R.id.fl_content)
    FrameLayout flContent;

    private TrainInfoByStationFragment mStationFragment;
    private TrainInfoByTrainNoFragment mTrainNoFragment;

    @Override
    public int setLayoutResId() {
        return R.layout.activity_train_info;
    }

    @Override
    public void initView() {
        tvTitle.setText("火车票信息");
        mStationFragment = new TrainInfoByStationFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fl_content, mStationFragment).commit();
    }

    @Override
    public void initData() {
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onTrainNoQueryClick() {
        if (mTrainNoFragment == null) {
            mTrainNoFragment = new TrainInfoByTrainNoFragment();
        }
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.fragment_slide_from_right, R.anim.fragment_slide_to_left)
                .replace(R.id.fl_content, mTrainNoFragment)
                .commit();

    }

    @Override
    public void onStationQueryClick() {
        if (mStationFragment == null) {
            mStationFragment = new TrainInfoByStationFragment();
        }
        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fragment_slide_from_left, R.anim.fragment_slide_to_right)
                .replace(R.id.fl_content, mStationFragment)
                .commit();
    }
}

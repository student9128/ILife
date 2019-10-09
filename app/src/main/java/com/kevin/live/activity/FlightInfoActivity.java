package com.kevin.live.activity;

import com.kevin.live.R;
import com.kevin.live.base.BaseActivity;

/**
 * Created by Kevin on 2018/8/28<br/>
 * Blog:http://student9128.top/<br/>
 * Describe:<br/>
 */
public class FlightInfoActivity extends BaseActivity {
    @Override
    public int setLayoutResId() {
        return R.layout.activity_flight_info;
    }

    @Override
    public void initView() {
        tvTitle.setText("航班信息");
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }
}

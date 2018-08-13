package com.kevin.live.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.kevin.live.R;
import com.kevin.live.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LotteryDetailActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_function)
    ImageView ivFunction;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;

    @Override
    public int setLayoutResId() {
        return R.layout.activity_lottery_detail;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String lotteryName = intent.getStringExtra("lotteryName");
        if (!TextUtils.isEmpty(lotteryName)) {
            tvTitle.setText(lotteryName+"开奖结果");
        } else {
            tvTitle.setText("彩票开奖结果");
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }
}

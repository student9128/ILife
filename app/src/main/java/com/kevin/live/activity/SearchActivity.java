package com.kevin.live.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.kevin.live.R;
import com.kevin.live.base.BaseActivity;

/**
 * Created by Kevin on 2017/3/17.
 * Blog:http://blog.csdn.net/student9128.
 * Description:
 */

public class SearchActivity extends BaseActivity implements View.OnClickListener {
    //    private Toolbar mToolbar;
    private ImageView mFunction, mBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
//        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
//        mToolbar.setTitle(" ");
//        setSupportActionBar(mToolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        mToolbar.setNavigationIcon(R.drawable.ic_back);
//        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });
        mBack = (ImageView) findViewById(R.id.iv_back);
        mBack.setVisibility(View.VISIBLE);
        mBack.setOnClickListener(this);
        mFunction = (ImageView) findViewById(R.id.iv_function);
        mFunction.setVisibility(View.GONE);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
        }
    }
}

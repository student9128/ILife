package com.kevin.live.base;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Kevin on 2017/3/16.
 * Blog:http://blog.csdn.net/student9128.
 * Description:
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        initView();
        initData();
    }

    public abstract void initView();

    public abstract void initData();
}

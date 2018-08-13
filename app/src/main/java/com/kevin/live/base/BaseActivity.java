package com.kevin.live.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kevin.live.R;
import com.kevin.live.view.LoadingDialog;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Kevin on 2017/3/16.
 * Blog:http://blog.csdn.net/student9128.
 * <h4>Description:</h4>
 * <br/>Base class for all the activities in the app except AppBaseActivity.
 * <p>There are some abstract methods that sub activities must override.This class
 * extends {@link AppBaseActivity} which extends AppCompatActivity.</p>
 */

public abstract class BaseActivity extends AppBaseActivity {
//    /**
//     * Tag,can be used for log or toast.
//     */
//    public String TAG = getClass().getSimpleName();

//    private DayNightHelper mDayNightHelper;

    @BindView(R.id.tv_title)
    public TextView tvTitle;
    @BindView(R.id.iv_function)
    public ImageView ivFunction;
    @BindView(R.id.tool_bar)
    public Toolbar toolBar;
    @BindView(R.id.ll_tool_bar)
    public LinearLayout llToolbar;
    public ActionBar actionBar;
    public LoadingDialog mLoadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mDayNightHelper = new DayNightHelper(this);
//        initTheme();
        setContentView(setLayoutResId());
        ButterKnife.bind(this);
        setSupportActionBar(toolBar);
        actionBar = getSupportActionBar();
        actionBar.setTitle(null);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        initView();
        initData();
        initListener();
    }

    public abstract int setLayoutResId();

    public abstract void initView();

    public abstract void initData();

    public abstract void initListener();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return true;
    }
    //===============Some Methods=================//

    public void startNewActivity(Class<? extends BaseActivity> clazz) {
        startActivity(new Intent(this, clazz));
    }

    /**
     * init Loading Dialog.
     */
    public void initLoadingDialog() {
        mLoadingDialog = new LoadingDialog();
    }

    /**
     * show Loading Dialog.
     */
    public void showLoadingDialog() {
        mLoadingDialog.show(getSupportFragmentManager(), TAG);
    }

    /**
     * dismiss Loading Dialog.
     */
    public void dismissLoadingDialog() {
        if (mLoadingDialog.isAdded()) {
            mLoadingDialog.dismiss();
        }
    }

    /**
     * 切换日夜间模式后进行刷新界面，写到initView()方法的最后
     */
//    public abstract void refreshUI();
    private void initTheme() {
//        if (mDayNightHelper.isDay()) {
//            setTheme(R.style.DayTheme);
//        } else {
//            setTheme(R.style.NightTheme);
//        }
    }

    /**
     * 刷新 StatusBar
     */
    public void refreshStatusBar() {
//        if (Build.VERSION.SDK_INT >= 21) {
//            TypedValue typedValue = new TypedValue();
//            Resources.Theme theme = getTheme();
//            theme.resolveAttribute(R.attr.colorPrimary, typedValue, true);
//            getWindow().setStatusBarColor(getResources().getColor(typedValue.resourceId));
//        }
    }

//    /**
//     * can be used for setting text color or background
//     *
//     * @param color
//     * @return
//     */
//    public int getMyColor(int color) {
//        return ContextCompat.getColor(this, color);
//    }
//
//    /**
//     * store value in SharePreferences.
//     *
//     * @param key key
//     * @param str value
//     */
//    public void setSp(String key, String str) {
//        SPUtil.setSP(key, this, str);
//    }
//
//    public void setSp(String key, boolean b) {
//        SPUtil.setSP(key, this, b);
//    }
//
//    /**
//     * get SharePreferences value.
//     *
//     * @param key key
//     * @return value
//     */
//    public String getStringSp(String key) {
//        return SPUtil.getStringSP(key, this);
//    }
//
//    public Boolean getBooleanSp(String key) {
//        return SPUtil.getBooleanSP(key,this);
//    }
//    //===============Tool=================//
//
//    private void setStatusBarColor(int color) {
//        StatusBarUtil.setColor(this, color);
//    }
//
//    public void showToast(String message) {
//        ToastUtils.showToast(this, message);
//    }
//
//    public void showLongToast(String message) {
//        ToastUtils.showLongToast(this, message);
//    }
//
//    public void printLoge(String str) {
//        LogK.e(TAG, str);
//    }
//
//    public void printLogd(String str) {
//        LogK.d(TAG, str);
//    }
//
//    public void printLogi(String str) {
//        LogK.i(TAG, str);
//    }
//
//    public void printLogv(String str) {
//        LogK.v(TAG, str);
//    }
//
//    public void printLogw(String str) {
//        LogK.w(TAG, str);
//    }
}

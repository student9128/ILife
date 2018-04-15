package com.kevin.live.fragment;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kevin.live.R;
import com.kevin.live.base.BaseFragment;
import com.kevin.live.constant.Constant;
import com.kevin.live.view.CheckDialog;

import butterknife.BindView;

/**
 * Created by Kevin on 2017/3/17.
 * Blog:http://blog.csdn.net/student9128.
 * Description:
 */

public class MeFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.iv_function)
    ImageView mIvFunction;
    @BindView(R.id.rl_user_info)
    RelativeLayout rlUserInfo;
    @BindView(R.id.tv_text)
    TextView mTvText;
    @BindView(R.id.tv_version_info)
    TextView mTvVersionInfo;
    @BindView(R.id.tv_about)
    TextView mTvAbout;
    @BindView(R.id.tool_bar)
    Toolbar mToolbar;
    @BindView(R.id.tv_about_me)
    TextView tvAboutMe;
    @BindView(R.id.btn_logout)
    Button btnLogout;
    private CheckDialog mDialog;
    private static boolean isVisible = false;

    public static MeFragment newInstance(String s) {
        MeFragment fragment = new MeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.ARGS, s);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int setLayoutResId() {
        return R.layout.fragment_me;
    }

    @Override
    public void initView() {
        AppCompatActivity activity = (AppCompatActivity) this.mActivity;
        activity.setSupportActionBar(mToolbar);
        activity.getSupportActionBar().setTitle(null);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);

    }

    @Override
    public void initData() {
        mTvTitle.setText(getString(R.string.title_me));
        mDialog = new CheckDialog(mActivity);
    }

    @Override
    public void initListener() {
        mTvAbout.setOnClickListener(this);
        btnLogout.setOnClickListener(this);

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (!isVisible) {
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(tvAboutMe, "translationX", 0.0f, -220.0f);
            objectAnimator.setDuration(500);
            objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
            objectAnimator.start();
                isVisible = !isVisible;
            }
        }
    }
//
//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//        if (hidden) {
//            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(tvAboutMe, "translationX", 0.0f, 250.0f);
//            objectAnimator.setDuration(2000);
//            objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
//            objectAnimator.start();
//        }
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_about:
                mDialog.setTitleText("关于");
                showToast("About");
                mDialog.show();
                break;
            case R.id.btn_logout:
                showToast("Logout");

                break;
            default:
                break;
        }
    }

}

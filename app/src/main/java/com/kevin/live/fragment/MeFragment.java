package com.kevin.live.fragment;

import android.os.Bundle;
import android.view.View;
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
    @BindView(R.id.iv_back)
    ImageView mIvBack;
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
    private CheckDialog mDialog;

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

    }

    @Override
    public void initData() {
        mDialog = new CheckDialog(mActivity);

    }

    @Override
    public void initListener() {
        mTvAbout.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_about:
                mDialog.setTitleText("关于");
                mDialog.show();
                break;
        }
    }

}

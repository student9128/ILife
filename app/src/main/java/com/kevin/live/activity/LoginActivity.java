package com.kevin.live.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.kevin.live.R;
import com.kevin.live.base.BaseActivity;
import com.kevin.live.util.TypeFaceUtils;
import com.kevin.live.view.CaseViewDialog;

/**
 * Created by <b><a style="color:#8BC34A"href="http://blog.csdn.net/student9128">Kevin</a></b> on 2017/3/17.
 * <br/><b>Blog:</b>
 * <div style="color:#8BC34A"><i>http://blog.csdn.net/student9128.</i></div>
 * <p style="color:#4CAF50"><b>Description:</b></p>
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener, CaseViewDialog.Callback {
    private Button mLogin;
    private CaseViewDialog caseViewDialog;
    private TextView mTvLogo;

//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_login);
        mTvLogo = (TextView) findViewById(R.id.tv_app_logo);
//        Typeface type = Typeface.createFromAsset(getAssets(), "VastShadow-Regular.ttf");
//        mTvLogo.setTypeface(type);
        TypeFaceUtils.setTypeface(this, mTvLogo);
        mLogin = (Button) findViewById(R.id.btn_login);
        mLogin.setOnClickListener(this);
//        caseViewDialog = new CaseViewDialog(this);
//        caseViewDialog.setCallBack(this);
//        caseViewDialog.show();
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
            case R.id.btn_login:
                Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }

    @Override
    public void getCallBack() {
        if (caseViewDialog.isShowing()) {
            caseViewDialog.dismiss();
        }
    }
}

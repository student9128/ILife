package com.kevin.live.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.kevin.live.R;
import com.kevin.live.util.TypeFaceUtils;

/**
 * Created by Kevin on 2017/3/17.
 * Blog:http://blog.csdn.net/student9128.
 * Description:
 */

public class SplashActivity extends AppCompatActivity {
    private TextView mTvLogo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        mTvLogo = (TextView) findViewById(R.id.tv_app_logo);
//        Typeface type = Typeface.createFromAsset(getAssets(), "VastShadow-Regular.ttf");
//        mTvLogo.setTypeface(type);
        TypeFaceUtils.setTypeface(this,mTvLogo);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                //从显示到完全透明
                Animation fadeOut = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.animation_fadeout);
                fadeOut.setFillAfter(true);
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }
        }, 2000);
    }

}

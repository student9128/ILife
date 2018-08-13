package com.kevin.live.base;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.kevin.live.http.util.VolleyUtils;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by <b><a style="color:#8BC34A"href="http://blog.csdn.net/student9128">Kevin</a></b> on 2017/3/24.
 * <br/><b>Blog:</b>
 * <div style="color:#8BC34A"><i>http://blog.csdn.net/student9128.</i></div>
 * <p style="color:#4CAF50"><b>Description:</b>
 * <br/>BaseApplication.
 * </p >
 */


public class BaseApplication extends Application {
    private static Context mContext;


    public static Context getContext() {
        return mContext;
    }


    public static RequestQueue volleyQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(getApplicationContext(), "b1ebc71639", true);
        mContext = getApplicationContext();
        volleyQueue = Volley.newRequestQueue(getApplicationContext());
        VolleyUtils.getInstance(this);
    }

    // 开放Volley的HTTP请求队列接口
    public static RequestQueue getRequestQueue() {
        return volleyQueue;
    }

}

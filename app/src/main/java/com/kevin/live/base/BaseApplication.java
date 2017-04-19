package com.kevin.live.base;

import android.app.Application;
import android.content.Context;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
    private static User user;

    public static Map<String, Object> mTotalTitleMap = new LinkedHashMap<>();//存放title和id
    public static Map<String, String> mTitleMap = new LinkedHashMap<>();//存放标题
    public static List<Map<String, String>> mValueDatas = new ArrayList<>();//存放报表中所以的内容数据
    public static List<String> mTitleList = new ArrayList<>();

    public static Context getContext() {
        return mContext;
    }

    public static void setUser(User user) {
        BaseApplication.user = user;
    }

    public static User getUser() {
        return user;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static void  clearData(){
       mTotalTitleMap.clear();
        mTitleMap.clear();
        mValueDatas.clear();
        mTitleList.clear();
    }
}

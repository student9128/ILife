package com.kevin.live.base;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.kevin.live.util.LogK;
import com.kevin.live.util.NetUtils;
import com.kevin.live.util.SPUtil;
import com.kevin.live.util.StatusBarUtil;
import com.kevin.live.util.ToastUtils;


/**
 * Created by <b><a style="color:#8BC34A"href="http://blog.csdn.net/student9128">Kevin</a></b> on 2017/3/24.
 * <br/><b>Blog:</b>
 * <div style="color:#8BC34A"><i>http://blog.csdn.net/student9128.</i></div>
 * <p style="color:#4CAF50"><b>Description:</b>
 * <br/>Base class for all the activities in the app extends {@link android.support.v7.app.AppCompatActivity}.
 * <br>There are some global methods in this class which maybe used in all the sub activities.
 * </p >
 */


public class AppBaseActivity extends AppCompatActivity {
    /**
     * Tag,can be used for log or toast.
     */
    public String TAG = getClass().getSimpleName();

    //===============Methods=================//

    public String getUserCode() {
        return BaseApplication.getUser().getUserCode();
    }

    public String getToken() {
        return BaseApplication.getUser().getToken();
    }

    /**
     * check network is available.
     *
     * @return true or false.
     */
    public boolean networkAvailable() {
        return NetUtils.isNetworkAvailable(BaseApplication.getContext());
    }

    /**
     * get the text from string
     *
     * @param ResId
     * @return
     */
    public String getMyString(int ResId) {
        return getResources().getString(ResId);
    }

    /**
     * can be used for setting text color or background
     *
     * @param color
     * @return
     */
    public int getMyColor(int color) {
        return ContextCompat.getColor(this, color);
    }

    /**
     * store value in SharePreferences.
     *
     * @param key key
     * @param str value
     */
    public void setSp(String key, String str) {
        SPUtil.setSP(key, this, str);
    }

    public void setSp(String key, boolean b) {
        SPUtil.setSP(key, this, b);
    }

    /**
     * get SharePreferences value.
     *
     * @param key key
     * @return value
     */
    public String getStringSp(String key) {
        return SPUtil.getStringSP(key, this);
    }

    public Boolean getBooleanSp(String key) {
        return SPUtil.getBooleanSP(key, this);
    }
    //===============Tool=================//

    public void setStatusBarColor(int color) {
        StatusBarUtil.setColor(this, color);
    }

    public void showToast(String message) {
        ToastUtils.showToast(this, message);
    }

    public void showLongToast(String message) {
        ToastUtils.showLongToast(this, message);
    }

    public void printLoge(String str) {
        LogK.e(TAG, str);
    }

    public void printLogd(String str) {
        LogK.d(TAG, str);
    }

    public void printLogi(String str) {
        LogK.i(TAG, str);
    }

    public void printLogv(String str) {
        LogK.v(TAG, str);
    }

    public void printLogw(String str) {
        LogK.w(TAG, str);
    }
}

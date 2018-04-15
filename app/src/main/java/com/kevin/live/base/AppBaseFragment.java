package com.kevin.live.base;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;

import com.kevin.live.util.LogK;
import com.kevin.live.util.NetUtils;
import com.kevin.live.util.ToastUtils;


/**
 * Created by <b><a style="color:#8BC34A"href="http://blog.csdn.net/student9128">Kevin</a></b> on 2017/3/24.
 * <br/><b>Blog:</b>
 * <div style="color:#8BC34A"><i>http://blog.csdn.net/student9128.</i></div>
 * <p style="color:#4CAF50"><b>Description:</b>
 * <br/>Base class for all the fragments in the app extends {@link android.support.v4.app.Fragment}.
 * <br>There are some global methods in this class which maybe used in all the sub fragments.
 * </p >
 */


public class AppBaseFragment extends Fragment {
    /**
     * Tag,can be used for log or toast.
     */
    public String TAG = getClass().getSimpleName();
    /**
     * can be prevent nullPointer.
     */
    protected FragmentActivity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = getActivity();
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
     * can be used for setting text color or background
     *
     * @param color
     * @return
     */
    public int getMyColor(int color) {
        return ContextCompat.getColor(mActivity, color);
    }

//===============Tools=================//

    public void showToast(String message) {
        ToastUtils.showToast(mActivity, message);
    }

    public void showLongToast(String message) {
        ToastUtils.showLongToast(mActivity, message);
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

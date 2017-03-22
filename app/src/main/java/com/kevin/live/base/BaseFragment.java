package com.kevin.live.base;

import android.support.v4.app.Fragment;

/**
 * Created by Kevin on 2017/3/17.
 * Blog:http://blog.csdn.net/student9128.
 * Description:
 */

public class BaseFragment extends Fragment{
    //TAG标记
    public  String TAG = getClass().getSimpleName();

//    public static BaseFragment newInstance(String s){
//        BaseFragment fragment = new BaseFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString(Constant.ARGS, s);
//        fragment.setArguments(bundle);
//        return fragment;
//    }
}

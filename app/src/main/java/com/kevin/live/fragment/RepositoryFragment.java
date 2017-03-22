package com.kevin.live.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kevin.live.R;
import com.kevin.live.base.BaseFragment;
import com.kevin.live.constant.Constant;

/**
 * Created by Kevin on 2017/3/17.
 * Blog:http://blog.csdn.net/student9128.
 * Description:
 */

public class RepositoryFragment extends BaseFragment {
    public static RepositoryFragment newInstance(String s) {
        RepositoryFragment fragment = new RepositoryFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.ARGS, s);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container,false);
        Bundle bundle = getArguments();
        String s = bundle.getString(Constant.ARGS);
        TextView mText = (TextView) view.findViewById(R.id.tv_text);
        mText.setText(s);
        return view;
    }
}

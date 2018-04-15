package com.kevin.live.fragment.news;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.kevin.live.R;
import com.kevin.live.adapter.news.HotNewsAdapter;
import com.kevin.live.base.BaseApplication;
import com.kevin.live.base.BaseFragment;
import com.kevin.live.constant.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> on 2017/8/24 for Project ILife.
 * <h3>Description:</h3>
 * <div>
 * <div/>
 */


public class HotNewsFragment extends BaseFragment {
    @BindView(R.id.rl_recycler_view)
    RecyclerView rlRecyclerView;
    private List<String> data = new ArrayList<>();

    public static HotNewsFragment newInstance(String s) {
        HotNewsFragment hotNewsFragment = new HotNewsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.ARGS, s);
        hotNewsFragment.setArguments(bundle);
        return hotNewsFragment;
    }

    @Override
    public int setLayoutResId() {
        return R.layout.fragment_hot_news;
    }

    @Override
    public void initView() {
        for (int i = 0; i < 100; i++) {
            data.add("Life:\t" + i);
        }
        HotNewsAdapter hotNewsAdapter = new HotNewsAdapter(data, mActivity);
        rlRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        rlRecyclerView.setAdapter(hotNewsAdapter);
    }

    @Override
    public void initData() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://is.snssdk.com/api/news/feed/v51/?category=news_hot&refer=1&count=20&min_behot_time=1491981025&last_refresh_sub_entrance_interval=1491981165&loc_mode=&loc_time=1491981000&latitude=&longitude=&city=&tt_from=pull&lac=&cid=&cp=&iid=0123456789&device_id=12345678952&ac=wifi&channel=&aid=&app_name=&version_code=&version_name=&device_platform=&ab_version=&ab_client=&ab_group=&ab_feature=&abflag=3&ssmix=a&device_type=&device_brand=&language=zh&os_api=&os_version=&openudid=1b8d5bf69dc4a561&manifest_version_code=&resolution=&dpi=&update_version_code=&_rticket=",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        printLogd("response:==>\t" + response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                printLogd("error:==>\t" + error.getMessage());
            }
        });
        BaseApplication.volleyQueue.add(stringRequest);

    }

    @Override
    public void initListener() {

    }

}

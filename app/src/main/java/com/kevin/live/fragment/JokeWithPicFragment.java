package com.kevin.live.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kevin.live.R;
import com.kevin.live.adapter.JokeWithPicAdapter;
import com.kevin.live.base.BaseFragment;
import com.kevin.live.bean.JokeWithPicBean;
import com.kevin.live.constant.Constant;
import com.kevin.live.http.Urls;
import com.kevin.live.util.LogK;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by <b><a style="color:#8BC34A"href="http://blog.csdn.net/student9128">Kevin</a></b> on 2017/5/11.
 * <br/><b>Blog:</b>
 * <div style="color:#8BC34A"><i>http://blog.csdn.net/student9128.</i></div>
 * <p style="color:#4CAF50"><b>Description:</b>
 * <br/>
 * </p >
 */


public class JokeWithPicFragment extends BaseFragment {
    private RequestQueue mQueue;
    private RecyclerView mRecyclerView;
    private List<JokeWithPicBean.ResultBean> mData = new ArrayList<>();
    private SwipeRefreshLayout mSwipeRefresh;

    public static JokeWithPicFragment newInstance(String s) {
        JokeWithPicFragment fragment = new JokeWithPicFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.ARGS, s);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int setLayoutResId() {
        return R.layout.fragment_joke_with_pic;
    }

    @Override
    public void initView() {
        mQueue = Volley.newRequestQueue(mActivity);
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.rl_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mSwipeRefresh = (SwipeRefreshLayout) mView.findViewById(R.id.srl_swipe_refresh_layout);
        mSwipeRefresh.setColorSchemeResources(R.color.colorPrimary, R.color.red, R.color.blue_1);
    }

    @Override
    public void initData() {
        doPostQryJoke();
    }

    @Override
    public void initListener() {
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                doPostQryJoke();
            }
        });
    }

    private void doPostQryJoke() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Urls.JOKE_NEWS_IMG, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                LogK.i("TAG", response);
                JokeWithPicBean jokeWithPicBean = JSON.parseObject(response, JokeWithPicBean.class);
                int errorCode = jokeWithPicBean.getError_code();
                String reason = jokeWithPicBean.getReason();

                if (0 == errorCode) {
                    mSwipeRefresh.setRefreshing(false);
                    List<JokeWithPicBean.ResultBean> result = jokeWithPicBean.getResult();
                    mData.clear();
                    mData.addAll(result);
                    JokeWithPicAdapter jokeAdapter = new JokeWithPicAdapter(mActivity, mData);
                    mRecyclerView.setAdapter(jokeAdapter);

                } else {
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mSwipeRefresh.setRefreshing(false);
                showToast("请求失败");
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();

                map.put("key", "0933128229dd416daf447c564d65eb92");
//                map.put("time", "1418745237");
//                map.put("time", String.valueOf(System.currentTimeMillis()).substring(0, 10));
//                map.put("sort", "desc");
//                map.put("page", 2);
//                map.put("rows", 10);
                map.put("dtype", "JSON");
                return map;
            }
        };

        mQueue.add(stringRequest);
    }
}

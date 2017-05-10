package com.kevin.live.activity;

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
import com.kevin.live.adapter.JokeByTimeAdapter;
import com.kevin.live.base.BaseActivity;
import com.kevin.live.bean.JokeByTimeBean;
import com.kevin.live.http.Urls;
import com.kevin.live.util.LogK;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by <b><a style="color:#8BC34A"href="http://blog.csdn.net/student9128">Kevin</a></b> on 2017/5/9.
 * <br/><b>Blog:</b>
 * <div style="color:#8BC34A"><i>http://blog.csdn.net/student9128.</i></div>
 * <p style="color:#4CAF50"><b>Description:</b>
 * <p>
 * </p>
 */


public class JokeActivity extends BaseActivity {
    private RequestQueue mQueue;
    private List<JokeByTimeBean.ResultBean> mData = new ArrayList<>();
    private RecyclerView mRecyclerView;

    @Override
    public void initView() {
        setContentView(R.layout.activity_joke);
        mQueue = Volley.newRequestQueue(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.lv_list_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void initData() {
        doPostQryJoke();
    }

    @Override
    public void initListener() {

    }

    private void doPostQryJoke() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Urls.JOKE_BY_TIME, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                LogK.i("TAG", response);
//                Gson gson = new Gson();
//                MobileNumberLookUpBean mobileNumberLookUpBean = gson.fromJson(response, MobileNumberLookUpBean.class);
                JokeByTimeBean jokeByTimeBean = JSON.parseObject(response, JokeByTimeBean.class);
                int errorCode = jokeByTimeBean.getError_code();
                String reason = jokeByTimeBean.getReason();

                if (0 == errorCode) {
                    List<JokeByTimeBean.ResultBean> result = jokeByTimeBean.getResult();
                    mData.addAll(result);
                    JokeByTimeAdapter jokeAdapter = new JokeByTimeAdapter(JokeActivity.this, mData);
                    mRecyclerView.setAdapter(jokeAdapter);

                } else {
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                showToast("请求失败");
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("key", "54b71bf5c34b4b038670d0c3fb52e57b");
                map.put("time", "1418745237");
                map.put("sort", "asc");
                map.put("page", "2");
                map.put("rows", "20");
//                map.put("dtype", "JSON");
                return map;
            }
        };

        mQueue.add(stringRequest);
    }
}

package com.kevin.live.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.alibaba.fastjson.JSON;
import com.android.volley.VolleyError;
import com.kevin.live.R;
import com.kevin.live.adapter.TrainInfoByTrainNumberAdapter;
import com.kevin.live.base.BaseActivity;
import com.kevin.live.bean.TrainInfoByTrainNoBean;
import com.kevin.live.http.Urls;
import com.kevin.live.http.util.HttpResponseListener;
import com.kevin.live.http.util.VolleyUtils;
import com.kevin.live.view.DividerItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Kevin on 2018/8/31<br/>
 * Blog:http://student9128.top/<br/>
 * Describe:<br/>
 */
public class TrainInfoByNumberActivity extends BaseActivity {
    @BindView(R.id.rl_recycler_view)
    RecyclerView rlRecyclerView;

    private TrainInfoByTrainNumberAdapter trainInfoByTrainNumberAdapter;
    private List<TrainInfoByTrainNoBean.ResultBean> data = new ArrayList<>();

    @Override
    public int setLayoutResId() {
        return R.layout.activity_train_info_by_station;
    }

    @Override
    public void initView() {
        String trainNumber = getIntent().getStringExtra("trainNumber");
        tvTitle.setText(trainNumber);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        dividerItemDecoration.setDivider(R.drawable.bg_divider_recycler_list);
        rlRecyclerView.setLayoutManager(linearLayoutManager);
        rlRecyclerView.addItemDecoration(dividerItemDecoration);
        trainInfoByTrainNumberAdapter = new TrainInfoByTrainNumberAdapter(this, data);
        rlRecyclerView.setAdapter(trainInfoByTrainNumberAdapter);
        trainInfoByTrainNumberAdapter.addHeaderView(LayoutInflater.from(this).inflate(R.layout.header_item_train_info_number,rlRecyclerView,false));

        doPostTrainInfo(trainNumber);

    }

    @Override
    public void initData() {


    }

    @Override
    public void initListener() {

    }

    private void doPostTrainInfo(String trainNumber) {
        Map<String, String> map = new HashMap<>();
        map.put("key", Urls.APP_Key);
        map.put("trainno", trainNumber);
        VolleyUtils.stringRequestByPost(this, Urls.QUERY_TRAIN_INFO_BY_TRAIN_NO, TAG, map, new HttpResponseListener<String>() {
            @Override
            public void onSuccess(String s) {
                printLogd(s);
                TrainInfoByTrainNoBean trainInfoByTrainNoBean = JSON.parseObject(s, TrainInfoByTrainNoBean.class);
                String retCode = trainInfoByTrainNoBean.getRetCode();
                if ("200".equals(retCode)) {
                    List<TrainInfoByTrainNoBean.ResultBean> result = trainInfoByTrainNoBean.getResult();
                    trainInfoByTrainNumberAdapter.updateData(result);
                } else if ("23201".equals(retCode) || "23203".equals(retCode)) {
                    showToast(trainInfoByTrainNoBean.getMsg());
                }
            }

            @Override
            public void onFail(VolleyError volleyError) {

            }
        });
    }

}

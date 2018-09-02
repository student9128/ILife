package com.kevin.live.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.android.volley.VolleyError;
import com.kevin.live.R;
import com.kevin.live.adapter.TrainInfoByStationAdapter;
import com.kevin.live.base.BaseActivity;
import com.kevin.live.bean.TrainInfoByStationBean;
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
public class TrainInfoByStationActivity extends BaseActivity {
    @BindView(R.id.rl_recycler_view)
    RecyclerView rlRecyclerView;

    private TrainInfoByStationAdapter trainInfoByStationAdapter;
    private List<TrainInfoByStationBean.ResultBean> data = new ArrayList<>();

    @Override
    public int setLayoutResId() {
        return R.layout.activity_train_info_by_station;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        String startStation = intent.getStringExtra("startStation");
        String endStation = intent.getStringExtra("endStation");
        tvTitle.setText(startStation + "--" + endStation);

        trainInfoByStationAdapter = new TrainInfoByStationAdapter(this, data);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        dividerItemDecoration.setDivider(R.drawable.bg_divider_recycler_list);
        rlRecyclerView.setLayoutManager(linearLayoutManager);
        rlRecyclerView.addItemDecoration(dividerItemDecoration);
        rlRecyclerView.setAdapter(trainInfoByStationAdapter);

        doPostTrainInfo(startStation, endStation);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    private void doPostTrainInfo(String startStation, String endStation) {
        Map<String, String> map = new HashMap<>();
        map.put("key", Urls.APP_Key);
        map.put("start", startStation);
        map.put("end", endStation);
        VolleyUtils.stringRequestByPost(this, Urls.QUERY_TRAIN_INFO_BY_STATION, TAG, map, new HttpResponseListener<String>() {
            @Override
            public void onSuccess(String s) {
                TrainInfoByStationBean trainInfoByStationBean = JSON.parseObject(s, TrainInfoByStationBean.class);
                String retCode = trainInfoByStationBean.getRetCode();

                if ("200".equals(retCode)) {
                    List<TrainInfoByStationBean.ResultBean> result = trainInfoByStationBean.getResult();
                    trainInfoByStationAdapter.updateData(result);
                } else if ("23201".equals(retCode)) {
                    showToast(trainInfoByStationBean.getMsg());
                }
            }

            @Override
            public void onFail(VolleyError volleyError) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

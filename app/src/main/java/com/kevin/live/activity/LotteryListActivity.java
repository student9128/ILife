package com.kevin.live.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.android.volley.VolleyError;
import com.kevin.live.R;
import com.kevin.live.adapter.LotteryListAdapter;
import com.kevin.live.base.BaseActivity;
import com.kevin.live.bean.LotteryListBean;
import com.kevin.live.http.Urls;
import com.kevin.live.http.util.HttpResponseListener;
import com.kevin.live.http.util.VolleyUtils;
import com.kevin.live.listener.OnRecyclerItemClickListener;
import com.kevin.live.view.DividerItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LotteryListActivity extends BaseActivity implements OnRecyclerItemClickListener {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_function)
    ImageView ivFunction;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.rl_recycler_view)
    RecyclerView rlRecyclerView;
    private LotteryListAdapter lotteryListAdapter;
    private List<String> lotteryList = new ArrayList<>();

    @Override
    public int setLayoutResId() {
        return R.layout.activity_lottery;
    }

    @Override
    public void initView() {
        tvTitle.setText("彩票");
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        dividerItemDecoration.setDivider(R.drawable.bg_divider_recycler_list);
        rlRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        rlRecyclerView.addItemDecoration(dividerItemDecoration);
        lotteryListAdapter = new LotteryListAdapter(this, lotteryList);
        rlRecyclerView.setAdapter(lotteryListAdapter);
    }

    @Override
    public void initData() {
        doPostLotteryList();

    }


    @Override
    public void initListener() {
        lotteryListAdapter.setOnRecyclerItemClickListener(this);

    }

    private void doPostLotteryList() {
        Map<String, String> map = new HashMap<>();
        map.put("key", Urls.APP_Key);
        VolleyUtils.stringRequestByPost(this, Urls.LOTTERY_LIST, TAG, map, new HttpResponseListener<String>() {
            @Override
            public void onSuccess(String s) {
                printLogd(s);
                LotteryListBean lotteryListBean = JSON.parseObject(s, LotteryListBean.class);
                List<String> result = lotteryListBean.getResult();
                lotteryListAdapter.updateData(result);
            }

            @Override
            public void onFail(VolleyError volleyError) {

            }
        });
    }

    @Override
    public void onRecyclerItemClickListener(int position) {
        String s = lotteryList.get(position);
        Intent intent = new Intent(this, LotteryDetailActivity.class);
        intent.putExtra("lotteryName",s);
        startActivity(intent);
    }

}

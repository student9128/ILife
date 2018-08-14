package com.kevin.live.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.android.volley.VolleyError;
import com.kevin.live.R;
import com.kevin.live.adapter.CookBookDetailAdapter;
import com.kevin.live.base.BaseActivity;
import com.kevin.live.bean.CookBookDetailBean;
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

/**
 * Created by Kevin on 2018/8/14<br/>
 * Blog:http://student9128.top/<br/>
 * Describe:<br/>
 */
public class CookBookDetailActivity extends BaseActivity implements OnRecyclerItemClickListener {

    @BindView(R.id.rl_recycler_view)
    RecyclerView rlRecyclerView;
    private CookBookDetailAdapter cookBookDetailAdapter;
    private List<CookBookDetailBean.ResultBean.ListBean> data = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;

    @Override
    public int setLayoutResId() {
        return R.layout.activity_cook_book_detail;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String categoryId = intent.getStringExtra("categoryId");
        if (!TextUtils.isEmpty(name)) {
            tvTitle.setText(name);
        } else {
            tvTitle.setText("菜谱");
        }
        doPostCookBookDetail(categoryId);
        linearLayoutManager = new LinearLayoutManager(this);
        rlRecyclerView.setLayoutManager(linearLayoutManager);
        cookBookDetailAdapter = new CookBookDetailAdapter(this, data);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        dividerItemDecoration.setDivider(R.drawable.bg_divider_recycler_list);
        rlRecyclerView.addItemDecoration(dividerItemDecoration);
        rlRecyclerView.setAdapter(cookBookDetailAdapter);
    }

    @Override
    public void initData() {
    }

    @Override
    public void initListener() {
        cookBookDetailAdapter.setOnCookItemClickListener(this);
    }

    private void doPostCookBookDetail(String categoryId) {
        Map<String, String> map = new HashMap<>();
        map.put("key", Urls.APP_Key);
        map.put("cid", categoryId);
        VolleyUtils.stringRequestByPost(this, Urls.COOK_MENU, TAG, map, new HttpResponseListener<String>() {
            @Override
            public void onSuccess(String s) {
                printLogd(s);
                CookBookDetailBean cookBookDetailBean = JSON.parseObject(s, CookBookDetailBean.class);
                CookBookDetailBean.ResultBean result = cookBookDetailBean.getResult();
                List<CookBookDetailBean.ResultBean.ListBean> list = result.getList();
                cookBookDetailAdapter.updateData(list);

            }

            @Override
            public void onFail(VolleyError volleyError) {
                printLogd(volleyError.getMessage());
            }
        });
    }

    @Override
    public void onRecyclerItemClickListener(int position) {
        CookBookDetailBean.ResultBean.ListBean listBean = data.get(position);
        Intent intent = new Intent(this, CookBookMenuActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("cookMenu",listBean);
        intent.putExtras(bundle);
//        rlRecyclerView.getChildAt(position).
        View view = linearLayoutManager.findViewByPosition(position);
        View imageView = view.findViewById(R.id.iv_thumbnail);
        View title = view.findViewById(R.id.tv_title);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this,Pair.create(imageView,"cookbook"),Pair.create(title,"cookName")).toBundle());
    }
}

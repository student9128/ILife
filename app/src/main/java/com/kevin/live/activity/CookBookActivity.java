package com.kevin.live.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import com.alibaba.fastjson.JSON;
import com.android.volley.VolleyError;
import com.kevin.live.R;
import com.kevin.live.adapter.CookBookAdapter;
import com.kevin.live.base.BaseActivity;
import com.kevin.live.bean.CookBookBean;
import com.kevin.live.http.Urls;
import com.kevin.live.http.util.HttpResponseListener;
import com.kevin.live.http.util.VolleyUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Kevin on 2018/8/14<br/>
 * Blog:http://student9128.top/<br/>
 * Describe:<br/> 菜谱分类列表
 */
public class CookBookActivity extends BaseActivity implements ExpandableListView.OnChildClickListener {
    @BindView(R.id.elv_list_view)
    ExpandableListView elvListView;

    private CookBookAdapter cookBookAdapter;
    List<CookBookBean.ResultBean.ChildsBeanX> data = new ArrayList<>();

    @Override
    public int setLayoutResId() {
        return R.layout.activity_cook_book;
    }

    @Override
    public void initView() {
        tvTitle.setText("菜谱");
        cookBookAdapter = new CookBookAdapter(this, data);
        elvListView.setAdapter(cookBookAdapter);
    }

    @Override
    public void initData() {
        doPostCookBook();

    }

    @Override
    public void initListener() {
        elvListView.setOnChildClickListener(this);

    }

    private void doPostCookBook() {
        Map<String, String> map = new HashMap<>();
        map.put("key", Urls.APP_Key);
        VolleyUtils.stringRequestByPost(this, Urls.COOK_BOOK, TAG, map, new HttpResponseListener<String>() {
            @Override
            public void onSuccess(String s) {
                printLogd(s);
                CookBookBean cookBookBean = JSON.parseObject(s, CookBookBean.class);
                CookBookBean.ResultBean result = cookBookBean.getResult();
                List<CookBookBean.ResultBean.ChildsBeanX> resultChilds = result.getChilds();
                cookBookAdapter.updateData(resultChilds);
            }

            @Override
            public void onFail(VolleyError volleyError) {
                printLogd(volleyError.getMessage());
            }
        });
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        CookBookBean.ResultBean.ChildsBeanX.ChildsBean.CategoryInfoBeanXX categoryInfo = data.get(groupPosition).getChilds().get(childPosition).getCategoryInfo();
        String ctgId = categoryInfo.getCtgId();
        String name = categoryInfo.getName();
        Intent intent = new Intent(this, CookBookDetailActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("categoryId", ctgId);
        startActivity(intent);
        return true;
    }
}

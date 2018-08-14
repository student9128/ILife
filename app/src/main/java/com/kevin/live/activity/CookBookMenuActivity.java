package com.kevin.live.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.kevin.live.R;
import com.kevin.live.adapter.CookBookMethodAdapter;
import com.kevin.live.base.BaseActivity;
import com.kevin.live.bean.CookBookDetailBean;
import com.kevin.live.bean.CookBookMethodBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Kevin on 2018/8/14<br/>
 * Blog:http://student9128.top/<br/>
 * Describe:<br/>
 */
public class CookBookMenuActivity extends BaseActivity {
    @BindView(R.id.iv_image)
    ImageView ivImage;
    @BindView(R.id.tv_cook_title)
    TextView tvCookTitle;
    @BindView(R.id.tv_cook_ingredients)
    TextView tvCookIngredients;
    @BindView(R.id.rl_recycler_view)
    RecyclerView rlRecyclerView;
    @BindView(R.id.tv_summary)
    TextView tvSummary;
    private List<CookBookMethodBean.MethodBean> data = new ArrayList<>();
    private CookBookMethodAdapter cookBookMethodAdapter;
    @Override
    public int setLayoutResId() {
        return R.layout.activity_cook_book_menu;
    }

    @Override
    public void initView() {
        CookBookDetailBean.ResultBean.ListBean listBean = getIntent().getParcelableExtra("cookMenu");
        String name = listBean.getName();
        tvTitle.setText(name);
        CookBookDetailBean.ResultBean.ListBean.RecipeBean recipe = listBean.getRecipe();
        String img = recipe.getImg();
        if (!TextUtils.isEmpty(img))
            Glide.with(this).load(img)
                    .centerCrop()
                    .placeholder(R.drawable.ic_life_place_holder)
                    .error(R.drawable.ic_image_failed_place_holder)
                    .into(ivImage);
        String ingredients = recipe.getIngredients();
//        printLogd(ingredients);
        if (!TextUtils.isEmpty(ingredients)) {
            ingredients = ingredients.replace("[\"", "")
                    .replace("\"]", "");
            if (ingredients.contains("\"")) ingredients = ingredients.replace("\"", "");
            tvCookIngredients.setText(ingredients);
        } else if (!TextUtils.isEmpty(recipe.getTitle()))
            tvCookIngredients.setText("暂无");
        tvCookTitle.setText(recipe.getTitle());
        if (!TextUtils.isEmpty(recipe.getSumary())) {
            tvSummary.setText(recipe.getSumary());
        } else {
            tvSummary.setText("暂无");
        }
        String method = recipe.getMethod();
        if (!TextUtils.isEmpty(method)) {
            method = "{\"method\":" + method + "}";
            printLogd(method);
            CookBookMethodBean cookBookMethodBean = JSON.parseObject(method, CookBookMethodBean.class);
            List<CookBookMethodBean.MethodBean> cookMethod = cookBookMethodBean.getMethod();
            data.clear();
            data.addAll(cookMethod);
            cookBookMethodAdapter = new CookBookMethodAdapter(this, data);
            rlRecyclerView.setLayoutManager(new LinearLayoutManager(this){
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            });
            rlRecyclerView.setHasFixedSize(true);
            rlRecyclerView.setNestedScrollingEnabled(false);
            rlRecyclerView.setFocusable(false);
            rlRecyclerView.setAdapter(cookBookMethodAdapter);

        }

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

}

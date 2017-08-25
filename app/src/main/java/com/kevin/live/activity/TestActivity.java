package com.kevin.live.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;

import com.kevin.live.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> for Project ILife on 2017/8/24.
 * <h3>Description:</h3>
 * <div>
 * <div/>
 */


public class TestActivity extends AppCompatActivity {
    private CoordinatorLayout mCoordinatorLayout;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
//    private FirstRecyclerAdapter mAdapter;
    private List<String> mData = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coorinator_layout);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//        initData();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mAdapter = new FirstRecyclerAdapter(mData, this);
//        mRecyclerView.setAdapter(mAdapter);
        mCollapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(this, R.color.colorAccent));
        mCollapsingToolbarLayout.setTitle("Kevin");
        mCollapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, R.color.orange_1));
        mCollapsingToolbarLayout.setExpandedTitleGravity(Gravity.CENTER);
//        mCollapsingToolbarLayout.setStatusBarScrimColor(ContextCompat.getColor(this,R.color.green));
//        mToolbar.setOnMenuItemClickListener(this);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    //    @Override
//    public void initView() {
//        setContentView(R.layout.activity_third);
//        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coorinator_layout);
//        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
//        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
//        setSupportActionBar(mToolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//        initData();
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
////        mAdapter = new FirstRecyclerAdapter(mData, this);
////        mRecyclerView.setAdapter(mAdapter);
//        mCollapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(this, R.color.colorAccent));
//        mCollapsingToolbarLayout.setTitle("Kevin");
//        mCollapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, R.color.orange_1));
//        mCollapsingToolbarLayout.setExpandedTitleGravity(Gravity.CENTER);
////        mCollapsingToolbarLayout.setStatusBarScrimColor(ContextCompat.getColor(this,R.color.green));
////        mToolbar.setOnMenuItemClickListener(this);
//        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });
//    }
//
//    @Override
//    public void initData() {
//
//    }
//
//    @Override
//    public void initListener() {
//
//    }
}

package com.kevin.live.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.android.volley.VolleyError;
import com.kevin.live.R;
import com.kevin.live.adapter.PostcodeCityAdapter;
import com.kevin.live.adapter.PostcodeDistrictAdapter;
import com.kevin.live.adapter.PostcodeProvinceAdapter;
import com.kevin.live.base.BaseActivity;
import com.kevin.live.bean.PostCodeResultBean;
import com.kevin.live.bean.PostcodeCityBean;
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
 * Created by Kevin on 2018/8/28<br/>
 * Blog:http://student9128.top/<br/>
 * Describe:<br/>
 */
public class PostcodeActivity extends BaseActivity implements PostcodeProvinceAdapter.OnProvinceClickListener, PostcodeCityAdapter.OnCityClickListener, PostcodeDistrictAdapter.OnDistrictClickListener {
    @BindView(R.id.rl_recycler_view_province)
    RecyclerView rlRecyclerViewProvince;
    @BindView(R.id.rl_recycler_view_city)
    RecyclerView rlRecyclerViewCity;
    @BindView(R.id.rl_recycler_view_district)
    RecyclerView rlRecyclerViewDistrict;

    private PostcodeProvinceAdapter postcodeProvinceAdapter;
    private List<PostcodeCityBean.ResultBean> provinceData = new ArrayList<>();

    private PostcodeCityAdapter postcodeCityAdapter;
    private List<PostcodeCityBean.ResultBean.CityBean> cityData = new ArrayList<>();

    private PostcodeDistrictAdapter postcodeDistrictAdapter;
    private List<PostcodeCityBean.ResultBean.CityBean.DistrictBean> districtData = new ArrayList<>();

    private String provinceId, cityId, districtId;
    private LinearLayoutManager linearLayoutManager;
    private LinearLayoutManager linearLayoutManagerCity;

    @Override
    public int setLayoutResId() {
        return R.layout.activity_postcode;
    }

    @Override
    public void initView() {
        tvTitle.setText("邮政编码查询");
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        dividerItemDecoration.setDivider(R.drawable.bg_divider_recycler_list);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManagerCity = new LinearLayoutManager(this);
        LinearLayoutManager linearLayoutManagerDistrict = new LinearLayoutManager(this);

        postcodeProvinceAdapter = new PostcodeProvinceAdapter(this, provinceData);
        rlRecyclerViewProvince.setLayoutManager(linearLayoutManager);
        rlRecyclerViewProvince.addItemDecoration(dividerItemDecoration);
        rlRecyclerViewProvince.setAdapter(postcodeProvinceAdapter);

        postcodeCityAdapter = new PostcodeCityAdapter(this, cityData);
        rlRecyclerViewCity.setLayoutManager(linearLayoutManagerCity);
        rlRecyclerViewCity.addItemDecoration(dividerItemDecoration);
        rlRecyclerViewCity.setAdapter(postcodeCityAdapter);

        postcodeDistrictAdapter = new PostcodeDistrictAdapter(this, districtData);
        rlRecyclerViewDistrict.setLayoutManager(linearLayoutManagerDistrict);
        rlRecyclerViewDistrict.addItemDecoration(dividerItemDecoration);
        rlRecyclerViewDistrict.setAdapter(postcodeDistrictAdapter);
    }

    @Override
    public void initData() {
        doPostQuery();
    }

    @Override
    public void initListener() {
        postcodeProvinceAdapter.setOnProvinceClickListener(this);
        postcodeCityAdapter.setOnCityClickListener(this);
        postcodeDistrictAdapter.setOnDistrictClickListener(this);

    }

    private void doPostQuery() {
        Map<String, String> map = new HashMap<>();
        map.put("key", Urls.APP_Key);
        VolleyUtils.stringRequestByPost(this, Urls.POSTCODE_CITY, TAG, map, new HttpResponseListener<String>() {
            @Override
            public void onSuccess(String s) {
                printLogd(s);
                PostcodeCityBean postcodeCityBean = JSON.parseObject(s, PostcodeCityBean.class);
                List<PostcodeCityBean.ResultBean> result = postcodeCityBean.getResult();
                postcodeProvinceAdapter.updateProvince(result);
            }

            @Override
            public void onFail(VolleyError volleyError) {

            }
        });
    }

    @Override
    public void onProvinceItemClickListener(int position) {
        PostcodeCityBean.ResultBean resultBean = provinceData.get(position);
        List<PostcodeCityBean.ResultBean.CityBean> city = resultBean.getCity();
        postcodeCityAdapter.updateProvince(city);
        postcodeDistrictAdapter.clearDistrict();
        provinceId = resultBean.getId();

        scrollToTop(rlRecyclerViewProvince, linearLayoutManager, position);

        postcodeCityAdapter.resetIndex();
        postcodeDistrictAdapter.resetIndex();
    }

    @Override
    public void onCityItemClickListener(int position) {
        PostcodeCityBean.ResultBean.CityBean cityBean = cityData.get(position);
        List<PostcodeCityBean.ResultBean.CityBean.DistrictBean> district = cityBean.getDistrict();
        postcodeDistrictAdapter.updateDistrict(district);
        cityId = cityBean.getId();

        scrollToTop(rlRecyclerViewCity, linearLayoutManagerCity, position);

//        postcodeDistrictAdapter.resetIndex();
    }

    @Override
    public void onDistrictItemClickListener(int position) {
        String district = districtData.get(position).getDistrict();
        districtId = districtData.get(position).getId();
        doPostQueryPostcode(district);
    }

    private void doPostQueryPostcode(String str) {
        Map<String, String> map = new HashMap<>();
        map.put("key", Urls.APP_Key);
        map.put("pid", provinceId);
        map.put("cid", cityId);
        map.put("did", districtId);
        map.put("word", str);
        VolleyUtils.stringRequestByPost(this, Urls.POSTCODE_RESULT, TAG, map, new HttpResponseListener<String>() {
            @Override
            public void onSuccess(String s) {
                printLogd(s);
                PostCodeResultBean postCodeResultBean = JSON.parseObject(s, PostCodeResultBean.class);
                List<PostCodeResultBean.ResultBean> result = postCodeResultBean.getResult();
                if (result.size() != 0) {
                    PostCodeResultBean.ResultBean resultBean = result.get(0);
                    List<String> address = resultBean.getAddress();
                    StringBuilder stringBuilder = new StringBuilder();
                    for (String a : address) {
                        stringBuilder.append(a + "\n");
                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(PostcodeActivity.this);
                    builder.setTitle("查询结果：邮编为" + resultBean.getPostNumber() + "，具体包括以下地区：")
                            .setMessage(stringBuilder.toString())
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.setCancelable(false);
                    alertDialog.show();
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(PostcodeActivity.this, R.color.colorPrimary));
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(PostcodeActivity.this);
                    builder.setTitle("查询结果")
                            .setMessage("暂无数据")
                            .setPositiveButton("知道了", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.setCancelable(false);
                    alertDialog.show();
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(PostcodeActivity.this, R.color.colorPrimary));
                }

            }

            @Override
            public void onFail(VolleyError volleyError) {

            }
        });

    }

    private void scrollToTop(RecyclerView recyclerView, LinearLayoutManager llm, int n) {
        //先从RecyclerView的LayoutManager中获取第一项和最后一项的Position
        int firstItem = llm.findFirstVisibleItemPosition();
        int lastItem = llm.findLastVisibleItemPosition();
        printLogd("firstItem:\t" + firstItem);
        printLogd("lastItem:\t" + lastItem);
        //然后区分情况
        if (n <= firstItem) {
            //当要置顶的项在当前显示的第一个项的前面时
//            rvRecyclerView.scrollToPosition(n);//有bug
            recyclerView.smoothScrollBy(0, recyclerView.getChildAt(n - firstItem).getTop(), new LinearInterpolator());
        } else if (n <= lastItem) {
            //当要置顶的项已经在屏幕上显示时
            int top = recyclerView.getChildAt(n - firstItem).getTop();//当前情景下其实只执行这行代码，可以不区分情况
            recyclerView.smoothScrollBy(0, top, new LinearInterpolator());
//            mRecyclerView.smoothScrollToPosition(n);
        } else {
            //当要置顶的项在当前显示的最后一项的后面时
            recyclerView.scrollToPosition(n);
        }
    }
}

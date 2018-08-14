package com.kevin.live.activity;

import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kevin.live.R;
import com.kevin.live.base.BaseActivity;
import com.kevin.live.bean.MobileNumberLookUpBean;
import com.kevin.live.http.Urls;
import com.kevin.live.util.LogK;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by <b><a style="color:#8BC34A"href="http://blog.csdn.net/student9128">Kevin</a></b> on 2017/4/24.
 * <br/><b>Blog:</b>
 * <div style="color:#8BC34A"><i>http://blog.csdn.net/student9128.</i></div>
 * <p style="color:#4CAF50"><b>Description:</b>
 * <br/>
 * </p >
 */


public class MobileLookUpActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_function)
    ImageView ivFunction;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.et_mobile_number)
    EditText etMobileNumber;
    @BindView(R.id.tv_clear)
    ImageView tvClear;
    @BindView(R.id.btn_qry)
    Button btnQry;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.tv_operator)
    TextView tvOperator;
    @BindView(R.id.tv_cityCode)
    TextView tvCityCode;
    @BindView(R.id.tv_postcode)
    TextView tvPostcode;
    @BindView(R.id.ll_container_result)
    LinearLayout llResult;
    private RequestQueue mQueue;

    @Override
    public int setLayoutResId() {
        return R.layout.activity_mobile_look_up;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        toolBar.setTitle("");
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mQueue = Volley.newRequestQueue(this);
    }

    @Override
    public void initData() {
        tvTitle.setText("手机归属地查询");
        llResult.setVisibility(View.INVISIBLE);

    }

    @Override
    public void initListener() {
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnQry.setOnClickListener(this);
        etMobileNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s)) {
                    tvClear.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        tvClear.setOnClickListener(this);
//        mIvBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_qry:
                String number = etMobileNumber.getText().toString().trim();
                if (TextUtils.isEmpty(number)) {
                    showToast("请输入手机号");
                } else if (number.length() < 7) {
                    showToast("你的手机号不正确");
                } else {
                    doPostQryMobileNumberLocation();
                }
                break;
            case R.id.tv_clear:
                etMobileNumber.setText("");
                tvClear.setVisibility(View.GONE);
                break;

        }

    }

    private void doPostQryMobileNumberLocation() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Urls.MOBILE_NUMBER_LOCATION, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                LogK.i("TAG", response);
                MobileNumberLookUpBean mobileNumberLookUpBean = JSON.parseObject(response, MobileNumberLookUpBean.class);
                String retCode = mobileNumberLookUpBean.getRetCode();
                if ("200".equals(retCode)) {
                    MobileNumberLookUpBean.ResultBean result = mobileNumberLookUpBean.getResult();
                    String city = result.getCity();
                    String cityCode = result.getCityCode();
                    String province = result.getProvince();
                    String operator = result.getOperator();
                    String zipCode = result.getZipCode();//邮编
                    llResult.setVisibility(View.VISIBLE);
                    if (city.equals(province)) {
                        tvLocation.setText(province);
                    } else {
                    tvLocation.setText(province + city);
                    }
                    tvOperator.setText(operator);
                    tvCityCode.setText(cityCode);
                    tvPostcode.setText(zipCode);


                } else if ("20101".equals(retCode)) {
                    showToast("查询不到相关数据");

                } else if ("20102".equals(retCode)) {
                    showToast("手机号码格式错误");
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                showToast("请求失败");
                llResult.setVisibility(View.INVISIBLE);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("key", Urls.APP_Key);
                map.put("phone", etMobileNumber.getText().toString().trim());
                return map;
            }
        };


        mQueue.add(stringRequest);
    }

}

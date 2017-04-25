package com.kevin.live.activity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
    @BindView(R.id.et_mobile_number)
    EditText mEtMobileNumber;

    @BindView(R.id.btn_qry)
    Button mBtnQuery;
    @BindView(R.id.tv_location)
    TextView mLocation;
    @BindView(R.id.tv_type)
    TextView mMobileNumberType;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.iv_function)
    ImageView mIvFunction;
    @BindView(R.id.tv_clear)
    ImageView mTvClear;
    @BindView(R.id.tv_zone_number)
    TextView mTvZoneNumber;
    @BindView(R.id.tv_postcode)
    TextView mTvPostcode;

    private RequestQueue mQueue;

    @Override
    public void initView() {
        setContentView(R.layout.activity_mobile_look_up);
        ButterKnife.bind(this);
        mQueue = Volley.newRequestQueue(this);
    }

    @Override
    public void initData() {
        mTvTitle.setText("手机归属地查询");

    }

    @Override
    public void initListener() {
        mBtnQuery.setOnClickListener(this);
        mEtMobileNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s)) {
                    mTvClear.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mTvClear.setOnClickListener(this);
        mIvBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_qry:
                String number = mEtMobileNumber.getText().toString().trim();
                if (TextUtils.isEmpty(number)) {
                    showToast("请输入手机号");
                } else if (number.length() < 7) {
                    showToast("你的手机号不正确");
                } else {
                    doPostQryMolbieNumberLocation();
                }
                break;
            case R.id.tv_clear:
                mEtMobileNumber.setText("");
                mTvClear.setVisibility(View.GONE);
                break;
            case R.id.iv_back:
                finish();
                break;
        }

    }

    private void doPostQryMolbieNumberLocation() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Urls.BASE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                LogK.i("TAG", response);
//                Gson gson = new Gson();
//                MobileNumberLookUpBean mobileNumberLookUpBean = gson.fromJson(response, MobileNumberLookUpBean.class);
                MobileNumberLookUpBean mobileNumberLookUpBean = JSON.parseObject(response, MobileNumberLookUpBean.class);
                int errorCode = mobileNumberLookUpBean.getError_code();
                String reason = mobileNumberLookUpBean.getReason();
                if (0==errorCode) {
                    MobileNumberLookUpBean.ResultBean result = mobileNumberLookUpBean.getResult();
                    String mobilearea = result.getMobilearea();
                    String mobiletype = result.getMobiletype();
                    String areacode = result.getAreacode();
                    String postcode = result.getPostcode();
                    mLocation.setText("归属地:\t" + mobilearea);
                    mMobileNumberType.setText("手机卡类型：\t" + mobiletype);
                    mTvZoneNumber.setText("区号：\t" + areacode);
                    mTvPostcode.setText("邮政编码：\t" + postcode);
                } else {
                    showToast("您的手机号不正确，请重新输入");
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
                map.put("key", Urls.APP_Key);
                map.put("mobileNumber", mEtMobileNumber.getText().toString().trim());
                return map;
            }
        };

        mQueue.add(stringRequest);
    }

}
